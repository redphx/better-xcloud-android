package com.betterxcloud.android

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.os.Bundle
import android.util.Log
import android.webkit.WebView

/**
 * Application class for Better xCloud Android TV
 * Handles global lifecycle management and memory optimization
 */
class XCloudApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Enable WebView debugging in debug builds
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
            Log.d(TAG, "WebView debugging enabled")
        }

        // Register lifecycle callbacks for memory management
        registerActivityLifecycleCallbacks(ActivityLifecycleHandler())

        Log.i(TAG, "Better xCloud Application initialized - Version ${BuildConfig.VERSION_NAME}")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(TAG, "Low memory warning - performing aggressive cleanup")
        performMemoryCleanup(true)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {
                Log.w(TAG, "Memory critically low (TRIM_MEMORY_RUNNING_CRITICAL)")
                performMemoryCleanup(true)
            }
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW -> {
                Log.w(TAG, "Memory running low (TRIM_MEMORY_RUNNING_LOW)")
                performMemoryCleanup(true)
            }
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE -> {
                Log.d(TAG, "Memory moderate (TRIM_MEMORY_RUNNING_MODERATE)")
                performMemoryCleanup(false)
            }
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
                Log.d(TAG, "UI hidden - clearing non-critical data")
                performMemoryCleanup(false)
            }
        }
    }

    /**
     * Perform memory cleanup
     * @param aggressive If true, perform aggressive cleanup including WebView caches
     */
    private fun performMemoryCleanup(aggressive: Boolean) {
        if (aggressive) {
            // Clear WebView data if available
            webViewReference?.get()?.apply {
                clearCache(true)
                clearFormData()
                clearHistory()
                Log.d(TAG, "WebView caches cleared")
            }
            System.gc()
        }
    }

    /**
     * Activity lifecycle handler for memory optimization
     */
    private inner class ActivityLifecycleHandler : ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "Activity created: ${activity.localClassName}")
        }

        override fun onActivityStarted(activity: Activity) {
            // No-op
        }

        override fun onActivityResumed(activity: Activity) {
            Log.d(TAG, "Activity resumed: ${activity.localClassName}")
        }

        override fun onActivityPaused(activity: Activity) {
            Log.d(TAG, "Activity paused: ${activity.localClassName}")
        }

        override fun onActivityStopped(activity: Activity) {
            Log.d(TAG, "Activity stopped: ${activity.localClassName}")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            // No-op
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "Activity destroyed: ${activity.localClassName}")
        }
    }

    companion object {
        private const val TAG = "XCloudApp"

        @Volatile
        private var instance: XCloudApplication? = null

        @Volatile
        var webViewReference: java.lang.ref.WeakReference<WebView>? = null
            private set

        fun getInstance(): XCloudApplication {
            return instance ?: throw IllegalStateException("Application not initialized")
        }

        fun setWebViewReference(webView: WebView) {
            webViewReference = java.lang.ref.WeakReference(webView)
        }
    }
}
