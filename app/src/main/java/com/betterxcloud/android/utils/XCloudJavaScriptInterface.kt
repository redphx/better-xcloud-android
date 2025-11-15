package com.betterxcloud.android.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast

/**
 * JavaScript interface for two-way communication between WebView and native Android code
 * This allows the Better xCloud script to communicate with the Android app
 */
class XCloudJavaScriptInterface(private val context: Context) {

    /**
     * Show a toast message from JavaScript
     * Usage: AndroidInterface.showToast("Hello from JavaScript")
     */
    @JavascriptInterface
    fun showToast(message: String) {
        Log.d(TAG, "Toast requested: $message")
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Log a message from JavaScript to Android logcat
     * Usage: AndroidInterface.log("Debug message")
     */
    @JavascriptInterface
    fun log(message: String) {
        Log.d(TAG, "JS Log: $message")
    }

    /**
     * Get device information
     * Usage: AndroidInterface.getDeviceInfo()
     */
    @JavascriptInterface
    fun getDeviceInfo(): String {
        return """
            {
                "platform": "AndroidTV",
                "osVersion": "${android.os.Build.VERSION.RELEASE}",
                "model": "${android.os.Build.MODEL}",
                "manufacturer": "${android.os.Build.MANUFACTURER}"
            }
        """.trimIndent()
    }

    /**
     * Notify native code that streaming has started
     * Usage: AndroidInterface.onStreamingStarted()
     */
    @JavascriptInterface
    fun onStreamingStarted() {
        Log.i(TAG, "Streaming started notification from JavaScript")
        // Can trigger native optimizations here
    }

    /**
     * Notify native code that streaming has stopped
     * Usage: AndroidInterface.onStreamingStopped()
     */
    @JavascriptInterface
    fun onStreamingStopped() {
        Log.i(TAG, "Streaming stopped notification from JavaScript")
        // Can trigger cleanup here
    }

    /**
     * Request vibration (for controller feedback)
     * Usage: AndroidInterface.vibrate(100)
     */
    @JavascriptInterface
    fun vibrate(durationMs: Long) {
        Log.d(TAG, "Vibration requested: ${durationMs}ms")

        // Clamp duration to reasonable values (10ms - 500ms)
        val clampedDuration = durationMs.coerceIn(10, 500)

        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            if (vibrator != null && vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // For Android Q (API 29) and above, use VibrationEffect
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            clampedDuration,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    // For older versions, use deprecated vibrate method
                    @Suppress("DEPRECATION")
                    vibrator.vibrate(clampedDuration)
                }
                Log.d(TAG, "Vibration completed")
            } else {
                Log.w(TAG, "Device does not support vibration")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to vibrate: ${e.message}", e)
        }
    }

    /**
     * Get app version
     * Usage: AndroidInterface.getAppVersion()
     */
    @JavascriptInterface
    fun getAppVersion(): String {
        return com.betterxcloud.android.BuildConfig.VERSION_NAME
    }

    /**
     * Check if running on Android TV
     * Usage: AndroidInterface.isAndroidTV()
     */
    @JavascriptInterface
    fun isAndroidTV(): Boolean {
        return context.packageManager.hasSystemFeature("android.software.leanback")
    }

    /**
     * Report performance metrics from JavaScript
     * Usage: AndroidInterface.reportMetrics('{"fps": 60, "bitrate": 15000}')
     */
    @JavascriptInterface
    fun reportMetrics(metricsJson: String) {
        if (com.betterxcloud.android.BuildConfig.DEBUG) {
            Log.d(TAG, "Metrics: $metricsJson")
        }
        // Can store/display metrics
    }

    companion object {
        private const val TAG = "JSInterface"
    }
}
