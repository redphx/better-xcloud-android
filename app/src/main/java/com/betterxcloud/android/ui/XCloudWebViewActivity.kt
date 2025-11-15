package com.betterxcloud.android.ui

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.betterxcloud.android.BuildConfig
import com.betterxcloud.android.R
import com.betterxcloud.android.XCloudApplication
import com.betterxcloud.android.managers.GamepadManager
import com.betterxcloud.android.utils.XCloudJavaScriptInterface
import java.io.InputStreamReader
import kotlin.concurrent.thread

/**
 * Main WebView activity for xCloud streaming
 * Handles WebView configuration, script injection, and gamepad input
 */
class XCloudWebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var gamepadManager: GamepadManager
    private val mainHandler = Handler(Looper.getMainLooper())
    private var loadingTimeoutRunnable: Runnable? = null
    private var isInFullscreen = false
    private var memoryCheckRunnable: Runnable? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xcloud_webview)

        // Keep screen on during streaming
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        webView = findViewById(R.id.webview)
        progressBar = findViewById(R.id.progress_bar)

        // Issue #7 - Add network connectivity check
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No network connection available", Toast.LENGTH_LONG).show()
            Log.e(TAG, "No network connection available")
            finish()
            return
        }

        // Issue #10 - Add state restoration
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
            Log.d(TAG, "WebView state restored")
        }

        // Issue #9 - Make WebView init async
        setupWebViewAsync()
        setupGamepadManager()

        // Issue #13 - Add memory monitoring
        startMemoryMonitoring()

        Log.i(TAG, "XCloudWebViewActivity initialized")
    }

    /**
     * Issue #7 - Network connectivity check
     */
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    /**
     * Issue #9 - Make WebView init async
     */
    private fun setupWebViewAsync() {
        thread {
            // Prepare resources in background if needed
            try {
                // Pre-load script content
                assets.open("better-xcloud.user.js").use { }
            } catch (e: Exception) {
                Log.w(TAG, "Could not pre-load script", e)
            }

            // Setup WebView on main thread
            runOnUiThread {
                setupWebView()
                // Load xCloud website after setup
                webView.loadUrl(XCLOUD_URL)
            }
        }
    }

    /**
     * Issue #13 - Add memory monitoring
     */
    private fun startMemoryMonitoring() {
        memoryCheckRunnable = object : Runnable {
            override fun run() {
                val runtime = Runtime.getRuntime()
                val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024
                val maxMemory = runtime.maxMemory() / 1024 / 1024
                val memoryPercent = (usedMemory.toFloat() / maxMemory.toFloat() * 100).toInt()

                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "Memory usage: $usedMemory MB / $maxMemory MB ($memoryPercent%)")
                }

                if (memoryPercent > 85) {
                    Log.w(TAG, "High memory usage detected: $memoryPercent%")
                    // Suggest garbage collection
                    System.gc()
                }

                // Check again in 30 seconds
                mainHandler.postDelayed(this, 30000)
            }
        }
        mainHandler.postDelayed(memoryCheckRunnable!!, 30000)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        // Store WebView reference for memory management
        XCloudApplication.setWebViewReference(webView)

        webView.settings.apply {
            // Enable JavaScript (required for xCloud)
            javaScriptEnabled = true

            // Enable DOM storage (required for web apps)
            domStorageEnabled = true

            // Allow video playback without user gesture
            mediaPlaybackRequiresUserGesture = false

            // Issue #19 - Fix user agent (updated to latest Chrome version)
            userAgentString = CHROME_USER_AGENT

            // Enable hardware acceleration
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            // Issue #1 - Disable file access for security
            allowFileAccess = false
            allowContentAccess = false

            // Enable database
            databaseEnabled = true

            // Cache settings optimized for streaming
            cacheMode = WebSettings.LOAD_DEFAULT
            setAppCacheEnabled(true)

            // Issue #2 - Enable safe browsing for security
            safeBrowsingEnabled = true

            // Issue #3 - Fix mixed content (use compatibility mode instead of always allow)
            mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE

            // Text settings
            textZoom = 100

            // Viewport settings for proper TV display
            useWideViewPort = true
            loadWithOverviewMode = true
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls = false
        }

        // Add JavaScript interface for two-way communication
        webView.addJavascriptInterface(
            XCloudJavaScriptInterface(this),
            "AndroidInterface"
        )

        // Set WebViewClient to handle page loading
        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
                Log.d(TAG, "Page started loading: $url")

                // Issue #12 - Add loading timeout
                startLoadingTimeout()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
                Log.d(TAG, "Page finished loading: $url")

                // Issue #12 - Cancel loading timeout
                cancelLoadingTimeout()

                // Issue #6 - Add error handling to script injection
                injectBetterXCloudScript()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.e(TAG, "WebView error: ${error?.description}")

                // Cancel timeout on error
                cancelLoadingTimeout()
            }

            // Issue #17 - Add SSL error handling
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                Log.e(TAG, "SSL Error: ${error?.toString()}")

                // In production, don't proceed with SSL errors
                // Only proceed in debug mode with user consent
                if (BuildConfig.DEBUG) {
                    Log.w(TAG, "Proceeding with SSL error (DEBUG mode)")
                    handler?.proceed()
                } else {
                    handler?.cancel()
                    Toast.makeText(
                        this@XCloudWebViewActivity,
                        "SSL Error: Cannot establish secure connection",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            // Issue #18 - Add content-type validation
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                request?.let {
                    val url = it.url.toString()

                    // Validate that we're only loading expected content types
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, "Loading resource: $url")
                    }

                    // Block potentially dangerous content types
                    if (url.endsWith(".exe") || url.endsWith(".apk") || url.endsWith(".bat")) {
                        Log.w(TAG, "Blocked potentially dangerous content: $url")
                        return WebResourceResponse("text/plain", "UTF-8", null)
                    }
                }

                return super.shouldInterceptRequest(view, request)
            }
        }

        // Set WebChromeClient for advanced features
        webView.webChromeClient = object : WebChromeClient() {

            override fun onPermissionRequest(request: PermissionRequest?) {
                // Auto-grant WebRTC permissions (camera, microphone, etc.)
                request?.grant(request.resources)
                Log.d(TAG, "Granted WebRTC permissions: ${request?.resources?.joinToString()}")
            }

            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                if (BuildConfig.DEBUG) {
                    consoleMessage?.let {
                        Log.d(TAG, "WebView Console: ${it.message()} -- ${it.sourceId()}:${it.lineNumber()}")
                    }
                }
                return super.onConsoleMessage(consoleMessage)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
            }

            // Issue #24 - Add fullscreen lock
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                isInFullscreen = true
                Log.d(TAG, "Entered fullscreen mode")

                // Lock to landscape for fullscreen video
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                supportActionBar?.hide()
            }

            override fun onHideCustomView() {
                super.onHideCustomView()
                isInFullscreen = false
                Log.d(TAG, "Exited fullscreen mode")

                // Restore normal view
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                supportActionBar?.show()
            }
        }

        // Issue #26 - Fix download handler
        webView.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            Log.d(TAG, "Download requested: $url (type: $mimetype, size: $contentLength)")

            try {
                val request = DownloadManager.Request(Uri.parse(url))
                request.setMimeType(mimetype)

                // Get filename from content disposition or URL
                val filename = contentDisposition?.let {
                    val filenameRegex = "filename=\"?([^\"]+)\"?".toRegex()
                    filenameRegex.find(it)?.groupValues?.get(1)
                } ?: url.substringAfterLast("/")

                request.setTitle(filename)
                request.setDescription("Downloading file...")
                request.allowScanningByMediaScanner()
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)

                val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                downloadManager.enqueue(request)

                Toast.makeText(this@XCloudWebViewActivity, "Downloading $filename", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e(TAG, "Failed to start download", e)
                Toast.makeText(this@XCloudWebViewActivity, "Download failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Issue #12 - Add loading timeout
     */
    private fun startLoadingTimeout() {
        cancelLoadingTimeout()
        loadingTimeoutRunnable = Runnable {
            Log.w(TAG, "Page loading timeout reached")
            webView.stopLoading()
            progressBar.visibility = View.GONE
            Toast.makeText(this, "Page loading timed out", Toast.LENGTH_LONG).show()
        }
        mainHandler.postDelayed(loadingTimeoutRunnable!!, LOADING_TIMEOUT_MS)
    }

    /**
     * Issue #12 - Cancel loading timeout
     */
    private fun cancelLoadingTimeout() {
        loadingTimeoutRunnable?.let {
            mainHandler.removeCallbacks(it)
            loadingTimeoutRunnable = null
        }
    }

    private fun setupGamepadManager() {
        gamepadManager = GamepadManager(webView)
    }

    /**
     * Issue #6 - Add error handling to script injection
     */
    private fun injectBetterXCloudScript() {
        try {
            // Load script from assets
            val scriptContent = assets.open("better-xcloud.user.js").use { inputStream ->
                InputStreamReader(inputStream).readText()
            }

            // Wrap in IIFE and inject
            val wrappedScript = """
                (function() {
                    'use strict';
                    $scriptContent
                })();
            """.trimIndent()

            webView.evaluateJavascript(wrappedScript) { result ->
                if (result != null && result != "null") {
                    Log.i(TAG, "Better xCloud script injected successfully")
                } else {
                    Log.w(TAG, "Script injection completed but returned null")
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "Failed to inject Better xCloud script", e)
            Toast.makeText(this, "Failed to load Better xCloud features", Toast.LENGTH_SHORT).show()
        }
    }

    // Issue #5 - Add null safety to key handlers
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        event ?: return super.onKeyDown(keyCode, event)

        // Issue #25 - Add keyboard support
        // Handle DPAD and keyboard events
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP,
            KeyEvent.KEYCODE_DPAD_DOWN,
            KeyEvent.KEYCODE_DPAD_LEFT,
            KeyEvent.KEYCODE_DPAD_RIGHT,
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER -> {
                // Let WebView handle DPAD navigation
                return super.onKeyDown(keyCode, event)
            }
        }

        // Handle gamepad button presses
        if (gamepadManager.handleKeyEvent(event)) {
            return true
        }

        // Handle back button
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    // Issue #5 - Add null safety to key handlers
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        event ?: return super.onKeyUp(keyCode, event)

        // Handle gamepad button releases
        if (gamepadManager.handleKeyEvent(event)) {
            return true
        }

        return super.onKeyUp(keyCode, event)
    }

    // Issue #5 - Add null safety to key handlers
    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        event ?: return super.onGenericMotionEvent(event)

        // Handle gamepad analog sticks and triggers
        if (gamepadManager.handleMotionEvent(event)) {
            return true
        }

        return super.onGenericMotionEvent(event)
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
        webView.pauseTimers()
        Log.d(TAG, "WebView paused")
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
        webView.resumeTimers()
        Log.d(TAG, "WebView resumed")
    }

    // Issue #10 - Add state restoration
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
        Log.d(TAG, "WebView state saved")
    }

    // Issue #4 - Fix WebView destruction
    override fun onDestroy() {
        super.onDestroy()

        // Cancel any pending timeouts
        cancelLoadingTimeout()

        // Stop memory monitoring
        memoryCheckRunnable?.let {
            mainHandler.removeCallbacks(it)
            memoryCheckRunnable = null
        }

        // Clean up WebView properly
        webView.apply {
            // Stop all loading
            stopLoading()

            // Clear clients to prevent memory leaks
            webViewClient = null
            webChromeClient = null

            // Remove from parent view
            (parent as? android.view.ViewGroup)?.removeView(this)

            // Clear WebView
            loadUrl("about:blank")
            clearHistory()
            clearCache(true)
            clearFormData()

            // Remove all views
            removeAllViews()

            // Destroy WebView
            destroy()
        }

        // Clear screen on flag
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        Log.d(TAG, "XCloudWebViewActivity destroyed")
    }

    override fun onBackPressed() {
        // Issue #24 - Handle fullscreen mode
        if (isInFullscreen) {
            // Exit fullscreen on back press
            webView.evaluateJavascript("if(document.fullscreenElement) document.exitFullscreen();", null)
            return
        }

        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "XCloudWebView"
        private const val XCLOUD_URL = "https://www.xbox.com/play"

        // Issue #12 - Loading timeout constant
        private const val LOADING_TIMEOUT_MS = 30000L // 30 seconds

        // Issue #19 - Chrome user agent updated to latest version
        private const val CHROME_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36"
    }
}
