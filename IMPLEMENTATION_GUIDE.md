# Implementation Quick Reference Guide

This document provides ready-to-use code snippets for implementing the core features of Better xCloud on Android TV.

## Table of Contents
1. [Minimal Working Example](#minimal-working-example)
2. [WebView Configuration](#webview-configuration)
3. [Script Injection](#script-injection)
4. [Gamepad Handling](#gamepad-handling)
5. [Leanback UI](#leanback-ui)
6. [Memory Optimization](#memory-optimization)
7. [Common Patterns](#common-patterns)

---

## Minimal Working Example

### Step 1: AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.betterxcloud.android">

    <!-- Permissions -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
    <uses-permission android:name="android.permission.VIBRATE" />

    <!-- TV Features -->
    <uses-feature
        android:name="android.software.leanback"
        android:required="true" />
    <uses-feature
        android:name="android.hardware.touchscreen"
        android:required="false" />
    <uses-feature
        android:name="android.hardware.gamepad"
        android:required="false" />

    <application
        android:name=".XCloudApplication"
        android:allowBackup="true"
        android:banner="@drawable/app_banner"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:hardwareAccelerated="true"
        android:theme="@style/Theme.BetterXCloud.Leanback">

        <!-- Main TV Launcher Activity -->
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.Leanback.Browse">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LEANBACK_LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- WebView Activity -->
        <activity
            android:name=".XCloudWebViewActivity"
            android:configChanges="orientation|screenSize|keyboardHidden"
            android:launchMode="singleTask"
            android:theme="@style/Theme.BetterXCloud.Fullscreen" />

    </application>
</manifest>
```

### Step 2: Application Class

```kotlin
package com.betterxcloud.android

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.webkit.WebView
import android.util.Log

class XCloudApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable WebView debugging in debug builds
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        // Register lifecycle callbacks for memory management
        registerActivityLifecycleCallbacks(ActivityLifecycleHandler())
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(TAG, "Low memory warning - clearing caches")
        clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        when (level) {
            TRIM_MEMORY_RUNNING_CRITICAL,
            TRIM_MEMORY_RUNNING_LOW -> {
                Log.w(TAG, "Memory critically low - aggressive cleanup")
                clearMemory()
            }
            TRIM_MEMORY_UI_HIDDEN -> {
                Log.d(TAG, "UI hidden - clearing non-critical data")
                // Clear less critical resources
            }
        }
    }

    private fun clearMemory() {
        // Clear WebView caches if available
        WebViewManager.instance?.apply {
            clearCache(true)
            clearFormData()
            clearHistory()
        }
        System.gc()
    }

    private inner class ActivityLifecycleHandler : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {
            if (activity is XCloudWebViewActivity) {
                activity.webView?.apply {
                    onPause()
                    pauseTimers()
                }
            }
        }

        override fun onActivityResumed(activity: Activity) {
            if (activity is XCloudWebViewActivity) {
                activity.webView?.apply {
                    onResume()
                    resumeTimers()
                }
            }
        }

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {}
    }

    companion object {
        private const val TAG = "XCloudApp"
    }
}
```

### Step 3: Main Activity (Leanback Launcher)

```kotlin
package com.betterxcloud.android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.core.content.ContextCompat

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, MainFragment())
                .commit()
        }
    }
}

class MainFragment : BrowseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
        loadRows()
        setupEventListeners()
    }

    private fun setupUI() {
        title = getString(R.string.app_name)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // Brand color
        brandColor = ContextCompat.getColor(requireContext(), R.color.xcloud_green)
        searchAffordanceColor = ContextCompat.getColor(requireContext(), R.color.white)
    }

    private fun loadRows() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // Quick Launch row
        val quickLaunchAdapter = ArrayObjectAdapter(ActionPresenter())
        quickLaunchAdapter.add(ActionItem("Launch xCloud", "Start cloud gaming now"))
        quickLaunchAdapter.add(ActionItem("Settings", "Configure app settings"))

        val quickLaunchHeader = HeaderItem(0, "Quick Actions")
        rowsAdapter.add(ListRow(quickLaunchHeader, quickLaunchAdapter))

        adapter = rowsAdapter
    }

    private fun setupEventListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
            when (item) {
                is ActionItem -> {
                    when (item.title) {
                        "Launch xCloud" -> launchXCloud()
                        "Settings" -> launchSettings()
                    }
                }
            }
        }
    }

    private fun launchXCloud() {
        val intent = Intent(requireContext(), XCloudWebViewActivity::class.java)
        startActivity(intent)
    }

    private fun launchSettings() {
        // Launch settings activity
    }
}

// Simple data class for menu items
data class ActionItem(val title: String, val description: String)

// Simple presenter for action items
class ActionPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            setMainImageDimensions(313, 176)
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val action = item as ActionItem
        val cardView = viewHolder.view as ImageCardView

        cardView.titleText = action.title
        cardView.contentText = action.description
        cardView.setMainImageDrawable(
            ContextCompat.getDrawable(cardView.context, R.drawable.ic_xbox_logo)
        )
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.badgeImage = null
        cardView.mainImage = null
    }
}
```

### Step 4: WebView Activity

```kotlin
package com.betterxcloud.android

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class XCloudWebViewActivity : AppCompatActivity() {

    lateinit var webView: WebView
    private lateinit var gamepadManager: GamepadManager

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable fullscreen
        enableFullscreen()

        // Create layout
        val container = FrameLayout(this)
        setContentView(container)

        // Initialize WebView
        webView = WebView(applicationContext).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }
        container.addView(webView)

        // Configure WebView
        configureWebView()

        // Initialize gamepad manager
        gamepadManager = GamepadManager(this, webView)

        // Load xCloud
        webView.loadUrl("https://www.xbox.com/play")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView() {
        webView.settings.apply {
            // Essential settings
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            mediaPlaybackRequiresUserGesture = false

            // User agent
            userAgentString = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"

            // Performance
            cacheMode = WebSettings.LOAD_DEFAULT
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            // Disable unnecessary features
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls = false

            // Mixed content for API 25
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }

        webView.webViewClient = XCloudWebViewClient()
        webView.webChromeClient = XCloudChromeClient()

        // Add JavaScript interface
        webView.addJavascriptInterface(
            XCloudJavaScriptInterface(this),
            "Android"
        )
    }

    private fun enableFullscreen() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        // Keep screen on
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    // Gamepad input handling
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (event != null && gamepadManager.handleKeyEvent(event)) {
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return if (event != null && gamepadManager.handleKeyEvent(event)) {
            true
        } else {
            super.onKeyUp(keyCode, event)
        }
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        return if (event != null && gamepadManager.handleMotionEvent(event)) {
            true
        } else {
            super.onGenericMotionEvent(event)
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }

    inner class XCloudWebViewClient : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            // Inject Better xCloud script
            if (url?.contains("xbox.com") == true) {
                injectBetterXCloudScript()
            }
        }

        private fun injectBetterXCloudScript() {
            try {
                val script = assets.open("better-xcloud.user.js")
                    .bufferedReader()
                    .use { it.readText() }

                val wrappedScript = """
                    (function() {
                        'use strict';
                        console.log('[Better xCloud] Injecting script...');
                        $script
                        console.log('[Better xCloud] Script injected successfully');
                    })();
                """.trimIndent()

                webView.evaluateJavascript(wrappedScript) { result ->
                    android.util.Log.d("BetterXCloud", "Script injection result: $result")
                }
            } catch (e: Exception) {
                android.util.Log.e("BetterXCloud", "Failed to inject script", e)
            }
        }
    }

    inner class XCloudChromeClient : WebChromeClient() {

        override fun onPermissionRequest(request: PermissionRequest?) {
            // Auto-grant all permissions (needed for WebRTC)
            request?.grant(request.resources)
        }

        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            consoleMessage?.let {
                android.util.Log.d(
                    "WebView",
                    "${it.message()} -- From line ${it.lineNumber()} of ${it.sourceId()}"
                )
            }
            return true
        }
    }
}
```

---

## WebView Configuration

### Optimized Settings for Fire Stick 4K

```kotlin
fun configureWebViewForFireStick(webView: WebView) {
    webView.settings.apply {
        // Core features
        javaScriptEnabled = true
        domStorageEnabled = true
        databaseEnabled = true
        mediaPlaybackRequiresUserGesture = false

        // User agent - Chrome on Linux for best compatibility
        userAgentString = buildUserAgent()

        // Cache settings - optimize for limited storage
        cacheMode = WebSettings.LOAD_DEFAULT
        setAppCacheEnabled(true)
        setAppCachePath(webView.context.cacheDir.absolutePath)

        // Performance
        setRenderPriority(WebSettings.RenderPriority.HIGH)
        setLayerType(View.LAYER_TYPE_HARDWARE, null)

        // Disable unnecessary features
        setSupportZoom(false)
        builtInZoomControls = false
        displayZoomControls = false
        setSupportMultipleWindows(false)
        setGeolocationEnabled(false)

        // Mixed content (API 21+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        // Video settings
        javaScriptCanOpenWindowsAutomatically = true
        allowFileAccess = false
        allowFileAccessFromFileURLs = false
        allowUniversalAccessFromFileURLs = false

        // Reduce memory footprint
        loadsImagesAutomatically = true
        blockNetworkImage = false
    }
}

private fun buildUserAgent(): String {
    // Use Chrome user agent for best xCloud compatibility
    return "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
           "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
}
```

---

## Script Injection

### Method 1: From Assets

```kotlin
fun injectScriptFromAssets(webView: WebView, fileName: String) {
    try {
        val script = webView.context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }

        injectScript(webView, script)
    } catch (e: Exception) {
        Log.e("ScriptInjector", "Failed to load script from assets: $fileName", e)
    }
}

fun injectScript(webView: WebView, script: String) {
    // Wrap in IIFE to avoid global scope pollution
    val wrappedScript = """
        (function() {
            'use strict';
            $script
        })();
    """.trimIndent()

    webView.evaluateJavascript(wrappedScript) { result ->
        Log.d("ScriptInjector", "Injection result: $result")
    }
}
```

### Method 2: From URL

```kotlin
fun injectScriptFromUrl(webView: WebView, url: String) {
    Thread {
        try {
            val connection = URL(url).openConnection()
            val script = connection.getInputStream()
                .bufferedReader()
                .use { it.readText() }

            webView.post {
                injectScript(webView, script)
            }
        } catch (e: Exception) {
            Log.e("ScriptInjector", "Failed to load script from URL: $url", e)
        }
    }.start()
}
```

### JavaScript Interface

```kotlin
class XCloudJavaScriptInterface(private val activity: Activity) {

    @JavascriptInterface
    fun log(message: String) {
        Log.d("WebView-JS", message)
    }

    @JavascriptInterface
    fun showToast(message: String) {
        activity.runOnUiThread {
            android.widget.Toast.makeText(activity, message, android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun saveSetting(key: String, value: String) {
        val prefs = activity.getSharedPreferences("settings", android.content.Context.MODE_PRIVATE)
        prefs.edit().putString(key, value).apply()
    }

    @JavascriptInterface
    fun loadSetting(key: String): String {
        val prefs = activity.getSharedPreferences("settings", android.content.Context.MODE_PRIVATE)
        return prefs.getString(key, "") ?: ""
    }

    @JavascriptInterface
    fun isTVMode(): Boolean = true

    @JavascriptInterface
    fun getAppVersion(): String = BuildConfig.VERSION_NAME

    @JavascriptInterface
    fun vibrate(durationMs: Int) {
        val vibrator = activity.getSystemService(android.content.Context.VIBRATOR_SERVICE)
            as android.os.Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                android.os.VibrationEffect.createOneShot(
                    durationMs.toLong(),
                    android.os.VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(durationMs.toLong())
        }
    }
}
```

---

## Gamepad Handling

### Complete Gamepad Manager

```kotlin
package com.betterxcloud.android

import android.hardware.input.InputManager
import android.view.InputDevice
import android.view.KeyEvent
import android.view.MotionEvent
import android.webkit.WebView
import android.util.Log

class GamepadManager(
    private val activity: XCloudWebViewActivity,
    private val webView: WebView
) {

    private val connectedGamepads = mutableMapOf<Int, InputDevice>()

    init {
        detectGamepads()
        registerGamepadListener()
    }

    private fun detectGamepads() {
        val deviceIds = InputDevice.getDeviceIds()
        for (deviceId in deviceIds) {
            val device = InputDevice.getDevice(deviceId)
            if (isGamepad(device)) {
                connectedGamepads[deviceId] = device
                Log.d(TAG, "Found gamepad: ${device.name}")
            }
        }
    }

    private fun isGamepad(device: InputDevice?): Boolean {
        device ?: return false
        val sources = device.sources
        return (sources and InputDevice.SOURCE_GAMEPAD == InputDevice.SOURCE_GAMEPAD) ||
               (sources and InputDevice.SOURCE_JOYSTICK == InputDevice.SOURCE_JOYSTICK)
    }

    private fun registerGamepadListener() {
        val inputManager = activity.getSystemService(android.content.Context.INPUT_SERVICE) as InputManager

        inputManager.registerInputDeviceListener(
            object : InputManager.InputDeviceListener {
                override fun onInputDeviceAdded(deviceId: Int) {
                    val device = InputDevice.getDevice(deviceId)
                    if (isGamepad(device)) {
                        connectedGamepads[deviceId] = device
                        Log.d(TAG, "Gamepad connected: ${device.name}")
                    }
                }

                override fun onInputDeviceRemoved(deviceId: Int) {
                    connectedGamepads.remove(deviceId)
                    Log.d(TAG, "Gamepad disconnected: $deviceId")
                }

                override fun onInputDeviceChanged(deviceId: Int) {}
            },
            null
        )
    }

    fun handleKeyEvent(event: KeyEvent): Boolean {
        if (event.source and InputDevice.SOURCE_GAMEPAD != InputDevice.SOURCE_GAMEPAD) {
            return false
        }

        val button = mapKeyCodeToButton(event.keyCode) ?: return false
        val pressed = event.action == KeyEvent.ACTION_DOWN

        sendButtonEventToWebView(button, pressed)
        return true
    }

    fun handleMotionEvent(event: MotionEvent): Boolean {
        if (event.source and InputDevice.SOURCE_JOYSTICK != InputDevice.SOURCE_JOYSTICK) {
            return false
        }

        val leftStickX = event.getAxisValue(MotionEvent.AXIS_X)
        val leftStickY = event.getAxisValue(MotionEvent.AXIS_Y)
        val rightStickX = event.getAxisValue(MotionEvent.AXIS_Z)
        val rightStickY = event.getAxisValue(MotionEvent.AXIS_RZ)
        val leftTrigger = event.getAxisValue(MotionEvent.AXIS_LTRIGGER)
        val rightTrigger = event.getAxisValue(MotionEvent.AXIS_RTRIGGER)

        sendAnalogEventToWebView(
            leftStickX, leftStickY,
            rightStickX, rightStickY,
            leftTrigger, rightTrigger
        )

        return true
    }

    private fun mapKeyCodeToButton(keyCode: Int): String? {
        return when (keyCode) {
            KeyEvent.KEYCODE_BUTTON_A -> "A"
            KeyEvent.KEYCODE_BUTTON_B -> "B"
            KeyEvent.KEYCODE_BUTTON_X -> "X"
            KeyEvent.KEYCODE_BUTTON_Y -> "Y"
            KeyEvent.KEYCODE_BUTTON_L1 -> "LB"
            KeyEvent.KEYCODE_BUTTON_R1 -> "RB"
            KeyEvent.KEYCODE_BUTTON_L2 -> "LT"
            KeyEvent.KEYCODE_BUTTON_R2 -> "RT"
            KeyEvent.KEYCODE_BUTTON_THUMBL -> "LS"
            KeyEvent.KEYCODE_BUTTON_THUMBR -> "RS"
            KeyEvent.KEYCODE_BUTTON_START -> "Start"
            KeyEvent.KEYCODE_BUTTON_SELECT -> "Select"
            KeyEvent.KEYCODE_DPAD_UP -> "DPadUp"
            KeyEvent.KEYCODE_DPAD_DOWN -> "DPadDown"
            KeyEvent.KEYCODE_DPAD_LEFT -> "DPadLeft"
            KeyEvent.KEYCODE_DPAD_RIGHT -> "DPadRight"
            else -> null
        }
    }

    private fun sendButtonEventToWebView(button: String, pressed: Boolean) {
        val script = """
            window.dispatchEvent(new CustomEvent('androidGamepadButton', {
                detail: { button: '$button', pressed: $pressed }
            }));
        """.trimIndent()

        webView.post {
            webView.evaluateJavascript(script, null)
        }
    }

    private fun sendAnalogEventToWebView(
        lx: Float, ly: Float,
        rx: Float, ry: Float,
        lt: Float, rt: Float
    ) {
        val script = """
            window.dispatchEvent(new CustomEvent('androidGamepadAnalog', {
                detail: {
                    leftStick: { x: $lx, y: $ly },
                    rightStick: { x: $rx, y: $ry },
                    triggers: { left: $lt, right: $rt }
                }
            }));
        """.trimIndent()

        webView.post {
            webView.evaluateJavascript(script, null)
        }
    }

    companion object {
        private const val TAG = "GamepadManager"
    }
}
```

---

## Leanback UI

### Simple Browse Fragment

```kotlin
class SimpleBrowseFragment : BrowseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Better xCloud"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        setupRows()
        setupListeners()
    }

    private fun setupRows() {
        val adapter = ArrayObjectAdapter(ListRowPresenter())

        // Quick Actions
        val quickActions = ArrayObjectAdapter(CardPresenter())
        quickActions.add(MenuItem("Launch xCloud", "Start streaming"))
        quickActions.add(MenuItem("Settings", "Configure app"))

        adapter.add(ListRow(HeaderItem("Quick Actions"), quickActions))

        this.adapter = adapter
    }

    private fun setupListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
            when ((item as MenuItem).title) {
                "Launch xCloud" -> {
                    startActivity(Intent(requireContext(), XCloudWebViewActivity::class.java))
                }
                "Settings" -> {
                    // Open settings
                }
            }
        }
    }
}

data class MenuItem(val title: String, val description: String)

class CardPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val menuItem = item as MenuItem
        val cardView = viewHolder.view as ImageCardView
        cardView.titleText = menuItem.title
        cardView.contentText = menuItem.description
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}
```

---

## Memory Optimization

### Memory Monitor

```kotlin
class MemoryMonitor(private val context: android.content.Context) {

    private val activityManager = context.getSystemService(android.content.Context.ACTIVITY_SERVICE)
        as android.app.ActivityManager

    fun checkMemory(): MemoryInfo {
        val memInfo = android.app.ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memInfo)

        val availMB = memInfo.availMem / 1048576L
        val totalMB = memInfo.totalMem / 1048576L
        val usedMB = totalMB - availMB
        val isLow = memInfo.lowMemory

        return MemoryInfo(availMB, totalMB, usedMB, isLow)
    }

    fun logMemoryUsage() {
        val info = checkMemory()
        android.util.Log.d(
            "Memory",
            "Available: ${info.availableMB}MB, Used: ${info.usedMB}MB, " +
            "Total: ${info.totalMB}MB, Low: ${info.isLow}"
        )
    }

    data class MemoryInfo(
        val availableMB: Long,
        val totalMB: Long,
        val usedMB: Long,
        val isLow: Boolean
    )
}
```

### Aggressive Cleanup

```kotlin
fun performAggressiveCleanup(webView: WebView) {
    webView.apply {
        // Clear all caches
        clearCache(true)
        clearFormData()
        clearHistory()

        // Clear SSL preferences
        clearSslPreferences()

        // Pause WebView
        onPause()
        pauseTimers()
    }

    // Force garbage collection
    System.gc()
}
```

---

## Common Patterns

### Launch Game by URL

```kotlin
fun launchGame(gameUrl: String) {
    val intent = Intent(context, XCloudWebViewActivity::class.java).apply {
        putExtra("game_url", gameUrl)
    }
    context.startActivity(intent)
}

// In XCloudWebViewActivity.onCreate()
val gameUrl = intent.getStringExtra("game_url")
if (gameUrl != null) {
    webView.loadUrl(gameUrl)
} else {
    webView.loadUrl("https://www.xbox.com/play")
}
```

### Save/Load Settings

```kotlin
object Settings {
    private const val PREFS_NAME = "xcloud_settings"

    fun save(context: android.content.Context, key: String, value: String) {
        context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun load(context: android.content.Context, key: String, default: String = ""): String {
        return context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
            .getString(key, default) ?: default
    }

    fun saveBoolean(context: android.content.Context, key: String, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun loadBoolean(context: android.content.Context, key: String, default: Boolean = false): Boolean {
        return context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
            .getBoolean(key, default)
    }
}
```

### Fullscreen Toggle

```kotlin
fun toggleFullscreen(window: android.view.Window, fullscreen: Boolean) {
    if (fullscreen) {
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )
    } else {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}
```

### Network Check

```kotlin
fun isNetworkAvailable(context: android.content.Context): Boolean {
    val connectivityManager = context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE)
        as android.net.ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        capabilities.hasCapability(android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        @Suppress("DEPRECATION")
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected == true
    }
}
```

---

## Build Configuration

### build.gradle (Module level)

```gradle
plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

android {
    namespace 'com.betterxcloud.android'
    compileSdk 33

    defaultConfig {
        applicationId "com.betterxcloud.android"
        minSdk 25  // Android TV 7.x
        targetSdk 33
        versionCode 1
        versionName "1.0.0"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            minifyEnabled true
            shrinkResources true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
        debug {
            applicationIdSuffix ".debug"
            debuggable true
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {
    // Leanback
    implementation 'androidx.leanback:leanback:1.0.0'

    // AndroidX
    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.4.2'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

    // Kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.9.22"
}
```

---

## Testing on Fire Stick

### ADB Commands

```bash
# Connect to Fire Stick
adb connect <ip-address>:5555

# Install APK
adb install -r app-debug.apk

# Launch app
adb shell am start -n com.betterxcloud.android/.MainActivity

# View logs
adb logcat | grep -E "(BetterXCloud|WebView)"

# Check memory
adb shell dumpsys meminfo com.betterxcloud.android

# Take screenshot
adb exec-out screencap -p > screenshot.png
```

---

## Troubleshooting

### WebView Not Loading

```kotlin
// Add error handling
webViewClient = object : WebViewClient() {
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        Log.e("WebView", "Error: ${error?.description}")
        // Show error message to user
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        Log.e("WebView", "HTTP Error: ${errorResponse?.statusCode}")
    }
}
```

### Gamepad Not Working

```kotlin
// Debug gamepad detection
InputDevice.getDeviceIds().forEach { deviceId ->
    val device = InputDevice.getDevice(deviceId)
    Log.d("Input", "Device: ${device.name}, Sources: ${device.sources}")
}
```

### Out of Memory

```kotlin
// In Application class
override fun onLowMemory() {
    super.onLowMemory()

    // Clear all caches
    clearWebViewCache()

    // Force GC
    System.gc()

    // Log memory state
    val runtime = Runtime.getRuntime()
    Log.w("Memory", "Low memory! Used: ${runtime.totalMemory() / 1048576}MB")
}
```

---

This guide provides the essential code snippets to get started with Better xCloud on Android TV. For complete implementation details, refer to the ANDROID_TV_REQUIREMENTS.md document.
