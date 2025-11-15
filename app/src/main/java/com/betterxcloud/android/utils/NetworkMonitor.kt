package com.betterxcloud.android.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

/**
 * NetworkMonitor - Monitors network connectivity status for the application.
 *
 * Provides real-time network state tracking and callbacks for connectivity changes.
 * Optimized for Fire Stick 4K with support for API 25+.
 *
 * Issue #23: Network monitoring for streaming stability
 */
class NetworkMonitor(private val context: Context) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    private var isConnected = false
    private var networkType = NetworkType.NONE

    private var onNetworkChangeListener: ((isConnected: Boolean, networkType: NetworkType) -> Unit)? = null

    init {
        checkInitialNetworkState()
    }

    /**
     * Registers a callback to monitor network changes
     */
    fun startMonitoring(onNetworkChange: (isConnected: Boolean, networkType: NetworkType) -> Unit) {
        this.onNetworkChangeListener = onNetworkChange

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerNetworkCallback()
        }
    }

    /**
     * Unregisters the network monitoring callback
     */
    fun stopMonitoring() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && networkCallback != null) {
            try {
                connectivityManager.unregisterNetworkCallback(networkCallback!!)
            } catch (e: Exception) {
                Log.e(TAG, "Error unregistering network callback", e)
            }
        }
        networkCallback = null
    }

    /**
     * Returns current network connectivity status
     */
    fun isNetworkAvailable(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected == true
        }
    }

    /**
     * Returns the current network type
     */
    fun getNetworkType(): NetworkType {
        return networkType
    }

    /**
     * Returns detailed network information
     */
    fun getNetworkInfo(): NetworkInfo {
        return NetworkInfo(
            isConnected = isConnected,
            networkType = networkType,
            isMetered = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.isActiveNetworkMetered
            } else {
                @Suppress("DEPRECATION")
                connectivityManager.isActiveNetworkMetered
            }
        )
    }

    /**
     * Checks if the network is metered (mobile data)
     */
    fun isNetworkMetered(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.isActiveNetworkMetered
        } else {
            @Suppress("DEPRECATION")
            connectivityManager.isActiveNetworkMetered
        }
    }

    private fun checkInitialNetworkState() {
        isConnected = isNetworkAvailable()
        networkType = detectNetworkType()
        Log.d(TAG, "Initial network state - Connected: $isConnected, Type: $networkType")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun registerNetworkCallback() {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isConnected = true
                networkType = detectNetworkType()
                Log.d(TAG, "Network available: $networkType")
                notifyNetworkChange()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isConnected = false
                networkType = NetworkType.NONE
                Log.w(TAG, "Network lost")
                notifyNetworkChange()
            }

            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val hasInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                if (!hasInternet) {
                    isConnected = false
                    networkType = NetworkType.NONE
                    Log.w(TAG, "Lost internet capability")
                    notifyNetworkChange()
                }
            }
        }

        try {
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
            connectivityManager.registerNetworkCallback(request, networkCallback!!)
        } catch (e: Exception) {
            Log.e(TAG, "Error registering network callback", e)
        }
    }

    private fun detectNetworkType(): NetworkType {
        if (!isNetworkAvailable()) {
            return NetworkType.NONE
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return NetworkType.NONE
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return NetworkType.NONE

            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.WIFI
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkType.ETHERNET
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkType.CELLULAR
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> NetworkType.BLUETOOTH
                else -> NetworkType.UNKNOWN
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            when (networkInfo?.type) {
                ConnectivityManager.TYPE_WIFI -> NetworkType.WIFI
                ConnectivityManager.TYPE_ETHERNET -> NetworkType.ETHERNET
                ConnectivityManager.TYPE_MOBILE -> NetworkType.CELLULAR
                ConnectivityManager.TYPE_BLUETOOTH -> NetworkType.BLUETOOTH
                else -> NetworkType.UNKNOWN
            }
        }
    }

    private fun notifyNetworkChange() {
        onNetworkChangeListener?.invoke(isConnected, networkType)
    }

    /**
     * Data class for network information
     */
    data class NetworkInfo(
        val isConnected: Boolean,
        val networkType: NetworkType,
        val isMetered: Boolean
    )

    /**
     * Enum for network types
     */
    enum class NetworkType {
        NONE, WIFI, ETHERNET, CELLULAR, BLUETOOTH, UNKNOWN
    }

    companion object {
        private const val TAG = "NetworkMonitor"
    }
}
