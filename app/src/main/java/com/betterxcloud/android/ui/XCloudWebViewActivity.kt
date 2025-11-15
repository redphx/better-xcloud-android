package com.betterxcloud.android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.betterxcloud.android.BuildConfig
import com.betterxcloud.android.R
import com.betterxcloud.android.XCloudApplication
import com.betterxcloud.android.managers.GamepadManager
import com.betterxcloud.android.utils.XCloudJavaScriptInterface
import java.io.InputStreamReader

/**
 * Main WebView activity for xCloud streaming
 * Handles WebView configuration, script injection, and gamepad input
 */
class XCloudWebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var gamepadManager: GamepadManager

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xcloud_webview)

        // Keep screen on during streaming
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        webView = findViewById(R.id.webview)
        progressBar = findViewById(R.id.progress_bar)

        setupWebView()
        setupGamepadManager()

        // Load xCloud website
        webView.loadUrl(XCLOUD_URL)

        Log.i(TAG, "XCloudWebViewActivity initialized")
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

            // Set user agent to Chrome (xCloud requires Chrome)
            userAgentString = CHROME_USER_AGENT

            // Enable hardware acceleration
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            // Allow file access for script loading
            allowFileAccess = true
            allowContentAccess = true

            // Enable database
            databaseEnabled = true

            // Cache settings optimized for streaming
            cacheMode = WebSettings.LOAD_DEFAULT
            setAppCacheEnabled(true)

            // Disable safe browsing (can interfere with streaming)
            safeBrowsingEnabled = false

            // Enable mixed content (HTTP and HTTPS)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

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

            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
                Log.d(TAG, "Page started loading: $url")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
                Log.d(TAG, "Page finished loading: $url")

                // Inject Better xCloud script once page is loaded
                injectBetterXCloudScript()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.e(TAG, "WebView error: ${error?.description}")
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
        }

        // Set download listener (in case of APK downloads, etc.)
        webView.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            Log.d(TAG, "Download requested: $url")
            // Handle downloads if needed
        }
    }

    private fun setupGamepadManager() {
        gamepadManager = GamepadManager(webView)
    }

    /**
     * Inject Better xCloud userscript into the WebView
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
                Log.i(TAG, "Better xCloud script injected successfully")
            }

        } catch (e: Exception) {
            Log.e(TAG, "Failed to inject Better xCloud script", e)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
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

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        // Handle gamepad button releases
        if (gamepadManager.handleKeyEvent(event)) {
            return true
        }

        return super.onKeyUp(keyCode, event)
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
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

    override fun onDestroy() {
        super.onDestroy()

        // Clean up WebView
        webView.apply {
            loadUrl("about:blank")
            stopLoading()
            clearHistory()
            removeAllViews()
            destroy()
        }

        // Clear screen on flag
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        Log.d(TAG, "XCloudWebViewActivity destroyed")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "XCloudWebView"
        private const val XCLOUD_URL = "https://www.xbox.com/play"

        // Chrome user agent for compatibility
        private const val CHROME_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"
    }
}
