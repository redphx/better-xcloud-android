# Android TV Porting Requirements for Better xCloud

**Target Platforms:**
- Android TV OS 7.x (API Level 25)
- Fire Stick 4K (1st Generation)

---

## 1. Android TV Leanback Library

### Required Dependencies

```gradle
dependencies {
    // Leanback library for TV UI components (API 25 compatible)
    implementation 'androidx.leanback:leanback:1.0.0'

    // Preference library for TV settings
    implementation 'androidx.leanback:leanback-preference:1.0.0'

    // RecyclerView for efficient lists
    implementation 'androidx.recyclerview:recyclerview:1.1.0'

    // CardView for content cards
    implementation 'androidx.cardview:cardview:1.0.0'
}
```

### Key Leanback Components Needed

#### BrowseFragment
- Main entry point for TV navigation
- Provides horizontal category rows with vertical item lists
- Built-in search and settings support

```kotlin
class MainFragment : BrowseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUIElements()
        setupEventListeners()
        loadRows()
    }

    private fun setupUIElements() {
        title = "Better xCloud"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // Set brand color for header
        brandColor = ContextCompat.getColor(requireContext(), R.color.xcloud_green)

        // Set search icon color
        searchAffordanceColor = ContextCompat.getColor(requireContext(), R.color.white)
    }

    private fun loadRows() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // Add "Recent Games" row
        val recentGamesRow = createRecentGamesRow()
        rowsAdapter.add(recentGamesRow)

        // Add "Browse" row with WebView launcher
        val browseRow = createBrowseRow()
        rowsAdapter.add(browseRow)

        adapter = rowsAdapter
    }
}
```

#### GuidedStepFragment
- For step-by-step user flows (login, settings)
- D-pad friendly forms

```kotlin
class LoginGuidedFragment : GuidedStepSupportFragment() {
    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance(
            "Sign in to Xbox",
            "You'll be redirected to the Xbox Cloud Gaming web interface",
            "Better xCloud",
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_xbox_logo)
        )
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        actions.add(GuidedAction.Builder(context)
            .id(ACTION_CONTINUE)
            .title("Continue to Sign In")
            .build())
    }
}
```

#### DetailsFragment
- For displaying game details before launching
- Shows metadata, screenshots, and action buttons

```kotlin
class GameDetailsFragment : DetailsSupportFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailsOverview = DetailsOverviewRow(gameDetails)

        // Add action buttons
        val actionsAdapter = ArrayObjectAdapter()
        actionsAdapter.add(Action(ACTION_PLAY, "Play"))
        actionsAdapter.add(Action(ACTION_FAVORITE, "Add to Favorites"))

        detailsOverview.actionsAdapter = actionsAdapter

        val rowsAdapter = ArrayObjectAdapter(DetailsOverviewRowPresenter())
        rowsAdapter.add(detailsOverview)

        adapter = rowsAdapter
    }
}
```

### TV Manifest Requirements

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Declare this is a TV app -->
    <uses-feature
        android:name="android.software.leanback"
        android:required="true" />

    <!-- TV doesn't require touchscreen -->
    <uses-feature
        android:name="android.hardware.touchscreen"
        android:required="false" />

    <!-- Declare as a launcher app on TV -->
    <application
        android:banner="@drawable/app_banner"
        android:icon="@drawable/app_icon"
        android:label="@string/app_name"
        android:theme="@style/Theme.Leanback">

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.Leanback.Browse">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LEANBACK_LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

---

## 2. WebView Integration

### WebView Setup for Android TV

For Fire Stick 4K with limited resources, use a single WebView instance and configure it for optimal performance.

```kotlin
class XCloudWebViewActivity : Activity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webView = findViewById(R.id.webview)
        progressBar = findViewById(R.id.progress_bar)

        configureWebView()
        loadXCloudWebsite()
    }

    private fun configureWebView() {
        webView.apply {
            settings.apply {
                // Enable JavaScript for xCloud web app
                javaScriptEnabled = true

                // Enable DOM storage for web app state
                domStorageEnabled = true

                // Enable database for offline capability
                databaseEnabled = true

                // Media playback settings
                mediaPlaybackRequiresUserGesture = false

                // Cache settings for performance
                cacheMode = WebSettings.LOAD_DEFAULT
                setAppCacheEnabled(true)
                setAppCachePath(applicationContext.cacheDir.absolutePath)

                // User agent - Important for xCloud compatibility
                userAgentString = buildUserAgent()

                // Disable zoom controls (not needed for TV)
                setSupportZoom(false)
                displayZoomControls = false
                builtInZoomControls = false

                // Hardware acceleration
                setLayerType(View.LAYER_TYPE_HARDWARE, null)

                // Mixed content for API 25
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }

                // Enable WebRTC for game streaming
                javaScriptCanOpenWindowsAutomatically = true
            }

            // Set WebView client for URL handling
            webViewClient = XCloudWebViewClient()

            // Set Chrome client for console logs and permissions
            webChromeClient = XCloudChromeClient()

            // Add JavaScript interface for native communication
            addJavascriptInterface(
                XCloudJavaScriptInterface(this@XCloudWebViewActivity),
                "AndroidInterface"
            )
        }
    }

    private fun buildUserAgent(): String {
        // Chrome user agent for best xCloud compatibility
        val chromeVersion = "120.0.0.0"
        return "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
               "(KHTML, like Gecko) Chrome/$chromeVersion Safari/537.36"
    }

    private fun loadXCloudWebsite() {
        webView.loadUrl("https://www.xbox.com/play")
    }
}

class XCloudWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        // Handle authentication redirects
        val url = request?.url?.toString() ?: return false

        if (url.contains("login.live.com") || url.contains("login.microsoft.com")) {
            // Allow Microsoft login
            return false
        }

        return false
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        // Inject userscript after page loads
        injectUserScript(view)
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        Log.e("XCloudWebView", "Error loading: ${error?.description}")
    }
}

class XCloudChromeClient : WebChromeClient() {
    override fun onPermissionRequest(request: PermissionRequest?) {
        // Auto-grant permissions needed for streaming (camera, microphone, etc.)
        request?.grant(request.resources)
    }

    override fun onConsoleMessage(message: ConsoleMessage?): Boolean {
        Log.d("WebView", "${message?.message()} -- From line ${message?.lineNumber()} of ${message?.sourceId()}")
        return true
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        // Update loading progress
        if (newProgress == 100) {
            // Page fully loaded
        }
    }
}
```

### WebView Memory Management for Fire Stick

```kotlin
class WebViewManager {
    companion object {
        private var webView: WebView? = null

        fun getWebView(context: Context): WebView {
            if (webView == null) {
                webView = createWebView(context.applicationContext)
            }
            return webView!!
        }

        private fun createWebView(context: Context): WebView {
            return WebView(context).apply {
                // Configure as shown above
            }
        }

        fun releaseWebView() {
            webView?.apply {
                removeAllViews()
                clearHistory()
                clearCache(true)
                loadUrl("about:blank")
                onPause()
                removeAllViews()
                destroy()
            }
            webView = null
        }
    }
}

// In Application class
class XCloudApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Enable WebView debugging
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        // Reduce memory footprint
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val processName = getProcessName()
            if (processName != packageName) {
                WebView.setDataDirectorySuffix(processName)
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // Clear WebView cache on low memory
        WebViewManager.webView?.clearCache(true)
    }
}
```

---

## 3. D-pad Navigation

### Focus Management

D-pad navigation is critical for Android TV. Every interactive element must be focusable and navigable.

```kotlin
class FocusableWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    init {
        // Make WebView focusable for D-pad navigation
        isFocusable = true
        isFocusableInTouchMode = true

        // Request focus on creation
        requestFocus()

        // Handle D-pad events
        setOnKeyListener { _, keyCode, event ->
            handleDPadEvent(keyCode, event)
        }
    }

    private fun handleDPadEvent(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_DPAD_CENTER,
                KeyEvent.KEYCODE_ENTER,
                KeyEvent.KEYCODE_BUTTON_A -> {
                    // Simulate click on focused element
                    performClick()
                    return true
                }
                KeyEvent.KEYCODE_BACK,
                KeyEvent.KEYCODE_BUTTON_B -> {
                    // Handle back navigation
                    if (canGoBack()) {
                        goBack()
                        return true
                    }
                }
                KeyEvent.KEYCODE_DPAD_UP,
                KeyEvent.KEYCODE_DPAD_DOWN,
                KeyEvent.KEYCODE_DPAD_LEFT,
                KeyEvent.KEYCODE_DPAD_RIGHT -> {
                    // Let WebView handle directional navigation
                    // or inject JavaScript for custom navigation
                    injectNavigationScript(keyCode)
                    return true
                }
            }
        }
        return false
    }

    private fun injectNavigationScript(keyCode: Int) {
        val direction = when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> "up"
            KeyEvent.KEYCODE_DPAD_DOWN -> "down"
            KeyEvent.KEYCODE_DPAD_LEFT -> "left"
            KeyEvent.KEYCODE_DPAD_RIGHT -> "right"
            else -> return
        }

        // JavaScript to handle spatial navigation in the web page
        val script = """
            (function() {
                var focused = document.activeElement;
                if (!focused || focused === document.body) {
                    // Focus first interactive element
                    var firstElement = document.querySelector('button, a, input, [tabindex]');
                    if (firstElement) firstElement.focus();
                    return;
                }

                // Get all focusable elements
                var elements = Array.from(document.querySelectorAll(
                    'button:not([disabled]), a[href], input:not([disabled]), ' +
                    'select:not([disabled]), textarea:not([disabled]), ' +
                    '[tabindex]:not([tabindex="-1"])'
                ));

                // Implement spatial navigation logic
                var rect = focused.getBoundingClientRect();
                var nextElement = findNextElement(elements, rect, '$direction');
                if (nextElement) {
                    nextElement.focus();
                }
            })();
        """.trimIndent()

        evaluateJavascript(script, null)
    }
}
```

### Best Practices for D-pad Navigation

```kotlin
// In layout XML files, set focus order
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/btn_play"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Play"
        android:focusable="true"
        android:nextFocusDown="@+id/btn_settings"
        android:nextFocusRight="@+id/btn_favorite" />

    <Button
        android:id="@+id/btn_settings"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Settings"
        android:focusable="true"
        android:nextFocusUp="@+id/btn_play"
        android:nextFocusRight="@+id/webview" />

    <WebView
        android:id="@+id/webview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:focusable="true"
        android:nextFocusLeft="@+id/btn_settings" />
</LinearLayout>
```

```kotlin
// Programmatic focus handling
class NavigationHelper {
    companion object {
        fun setupFocusNavigation(activity: Activity) {
            val rootView = activity.findViewById<ViewGroup>(android.R.id.content)

            rootView.viewTreeObserver.addOnGlobalFocusChangeListener {
                oldFocus, newFocus ->
                // Log focus changes for debugging
                Log.d("Focus", "Focus changed from $oldFocus to $newFocus")

                // Apply visual feedback
                oldFocus?.apply {
                    scaleX = 1.0f
                    scaleY = 1.0f
                }

                newFocus?.apply {
                    scaleX = 1.1f
                    scaleY = 1.1f
                }
            }
        }

        fun requestInitialFocus(view: View) {
            view.post {
                view.requestFocus()
            }
        }
    }
}
```

---

## 4. Performance Optimization for Fire Stick 4K

### Fire Stick 4K (1st Gen) Specifications
- **RAM**: 1.5 GB
- **CPU**: Quad-core 1.7 GHz
- **GPU**: IMG GE8300
- **Storage**: 8 GB internal

### Memory Optimization Strategies

```kotlin
// Application-level optimizations
class XCloudApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Reduce memory footprint
        optimizeForLowMemory()
    }

    private fun optimizeForLowMemory() {
        // Use smaller heap sizes for bitmaps
        val options = BitmapFactory.Options().apply {
            inPreferredConfig = Bitmap.Config.RGB_565 // Use 16-bit instead of 32-bit
            inSampleSize = 2 // Downsample images
        }

        // Limit image cache size
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8 // Use 1/8th of available memory

        // Clear unused resources periodically
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                // Aggressive memory cleanup when activity is not visible
                if (activity is XCloudWebViewActivity) {
                    activity.webView?.apply {
                        onPause()
                        pauseTimers()
                    }
                }
                System.gc()
            }

            override fun onActivityResumed(activity: Activity) {
                if (activity is XCloudWebViewActivity) {
                    activity.webView?.apply {
                        onResume()
                        resumeTimers()
                    }
                }
            }

            // Other lifecycle methods...
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        when (level) {
            TRIM_MEMORY_RUNNING_CRITICAL,
            TRIM_MEMORY_RUNNING_LOW -> {
                // Clear all caches
                clearCaches()
            }
            TRIM_MEMORY_UI_HIDDEN -> {
                // UI is hidden, clear non-critical data
                clearNonCriticalData()
            }
        }
    }

    private fun clearCaches() {
        // Clear image caches
        Glide.get(this).clearMemory()

        // Clear WebView cache
        WebViewManager.webView?.apply {
            clearCache(true)
            clearFormData()
        }

        // Force garbage collection
        System.gc()
    }

    private fun clearNonCriticalData() {
        // Clear only non-essential caches
    }
}
```

### WebView Performance Tuning

```kotlin
private fun configureWebViewForPerformance(webView: WebView) {
    webView.settings.apply {
        // Disable unnecessary features
        setSupportMultipleWindows(false)
        setGeolocationEnabled(false)

        // Optimize rendering
        setRenderPriority(WebSettings.RenderPriority.HIGH)

        // Reduce memory usage
        cacheMode = WebSettings.LOAD_NO_CACHE // For streaming, don't cache

        // Limit resources
        loadsImagesAutomatically = true // But optimize images
        blockNetworkImage = false
        blockNetworkLoads = false

        // Database and storage limits
        databaseEnabled = true
        domStorageEnabled = true
    }

    // Limit memory per WebView
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        webView.settings.safeBrowsingEnabled = false // Save memory
    }
}
```

### Layout Optimization

```kotlin
// Use ConstraintLayout for flat hierarchy
// Avoid nested layouts to reduce rendering time
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <WebView
        android:id="@+id/webview"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Monitoring and Profiling

```kotlin
class PerformanceMonitor(private val context: Context) {
    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    fun logMemoryUsage() {
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val availableMB = memoryInfo.availMem / 1048576L
        val totalMB = memoryInfo.totalMem / 1048576L
        val usedMB = totalMB - availableMB
        val threshold = memoryInfo.threshold / 1048576L

        Log.d("Memory", "Available: ${availableMB}MB, Used: ${usedMB}MB, Total: ${totalMB}MB, Threshold: ${threshold}MB")

        // Warn if approaching low memory
        if (availableMB < threshold) {
            Log.w("Memory", "Low memory warning!")
            // Trigger cleanup
        }
    }

    fun getAppMemoryUsage(): Long {
        val runtime = Runtime.getRuntime()
        val usedMemory = runtime.totalMemory() - runtime.freeMemory()
        return usedMemory / 1048576L // Convert to MB
    }
}
```

---

## 5. Video Playback Options

### Option 1: WebView with WebRTC (Recommended for xCloud)

Xbox Cloud Gaming uses WebRTC for video streaming. The WebView approach handles this automatically.

```kotlin
class XCloudChromeClient : WebChromeClient() {

    override fun onPermissionRequest(request: PermissionRequest?) {
        request?.let {
            // Grant permissions required for WebRTC streaming
            if (it.resources.contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE) ||
                it.resources.contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE) ||
                it.resources.contains(PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID)) {
                it.grant(it.resources)
            }
        }
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        // Handle fullscreen video
        super.onShowCustomView(view, callback)

        // Enter fullscreen mode for better streaming experience
        enterFullscreen()
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
        exitFullscreen()
    }
}

private fun enterFullscreen() {
    window.decorView.systemUiVisibility = (
        View.SYSTEM_UI_FLAG_FULLSCREEN or
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    )
}
```

### WebRTC Permissions in Manifest

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.CAMERA" />

<!-- Fire Stick doesn't have camera, make it optional -->
<uses-feature
    android:name="android.hardware.camera"
    android:required="false" />
```

### Video Performance Optimization

```kotlin
// Enable hardware acceleration for video decoding
class XCloudWebViewActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force hardware acceleration
        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )

        // Keep screen on during streaming
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
```

### Option 2: Native Video Player (Alternative)

If you need to implement custom video handling:

```kotlin
class VideoPlayerActivity : Activity() {
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoView = VideoView(this).apply {
            setOnPreparedListener { mediaPlayer ->
                // Configure media player for low latency
                mediaPlayer.setScreenOnWhilePlaying(true)

                // Use VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING for TV
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT)
                }
            }

            setOnErrorListener { _, what, extra ->
                Log.e("VideoPlayer", "Error: what=$what, extra=$extra")
                true
            }
        }
    }
}
```

---

## 6. Userscript Injection

### JavaScript Injection Methods

```kotlin
class UserScriptInjector(private val webView: WebView) {

    fun injectScript(scriptContent: String) {
        // Method 1: Direct injection
        webView.evaluateJavascript(scriptContent) { result ->
            Log.d("UserScript", "Injection result: $result")
        }
    }

    fun injectScriptFromAssets(scriptFileName: String) {
        try {
            val scriptContent = webView.context.assets.open(scriptFileName).bufferedReader().use {
                it.readText()
            }
            injectScript(scriptContent)
        } catch (e: Exception) {
            Log.e("UserScript", "Failed to load script: ${e.message}")
        }
    }

    fun injectScriptFromUrl(scriptUrl: String) {
        // Download and inject remote script
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(scriptUrl)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("UserScript", "Failed to download script: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { scriptContent ->
                    // Inject on main thread
                    webView.post {
                        injectScript(scriptContent)
                    }
                }
            }
        })
    }
}
```

### Better xCloud Script Integration

```kotlin
class XCloudWebViewClient : WebViewClient() {

    private val scriptInjector by lazy { UserScriptInjector(webView) }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        // Only inject on xCloud pages
        if (url?.contains("xbox.com/play") == true) {
            injectBetterXCloudScript(view)
        }
    }

    private fun injectBetterXCloudScript(view: WebView?) {
        view ?: return

        // Load Better xCloud script from assets
        val betterXCloudScript = loadScriptFromAssets("better-xcloud.user.js")

        // Wrap in IIFE to avoid global scope pollution
        val wrappedScript = """
            (function() {
                'use strict';
                $betterXCloudScript
            })();
        """.trimIndent()

        view.evaluateJavascript(wrappedScript) { result ->
            Log.d("BetterXCloud", "Script injected successfully")

            // Notify script that it's running in Android app
            notifyScriptEnvironment(view)
        }
    }

    private fun notifyScriptEnvironment(view: WebView) {
        val envScript = """
            window.BX_ANDROID_APP = true;
            window.BX_ANDROID_VERSION = '${BuildConfig.VERSION_NAME}';
            window.BX_PLATFORM = 'android-tv';
        """.trimIndent()

        view.evaluateJavascript(envScript, null)
    }

    private fun loadScriptFromAssets(fileName: String): String {
        return try {
            view.context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Log.e("Script", "Failed to load $fileName: ${e.message}")
            ""
        }
    }
}
```

### JavaScript Interface for Two-Way Communication

```kotlin
class XCloudJavaScriptInterface(private val activity: Activity) {

    @JavascriptInterface
    fun showToast(message: String) {
        activity.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun saveSettings(key: String, value: String) {
        val prefs = activity.getSharedPreferences("xcloud_settings", Context.MODE_PRIVATE)
        prefs.edit().putString(key, value).apply()
    }

    @JavascriptInterface
    fun loadSettings(key: String): String {
        val prefs = activity.getSharedPreferences("xcloud_settings", Context.MODE_PRIVATE)
        return prefs.getString(key, "") ?: ""
    }

    @JavascriptInterface
    fun vibrate(duration: Int) {
        val vibrator = activity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(duration.toLong(), VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(duration.toLong())
        }
    }

    @JavascriptInterface
    fun getTVMode(): Boolean {
        // Let script know we're on TV
        return true
    }

    @JavascriptInterface
    fun requestFullscreen() {
        activity.runOnUiThread {
            activity.window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
        }
    }
}
```

### Custom UserScript Support

```kotlin
class CustomScriptManager(private val context: Context) {

    private val scriptsDir = File(context.filesDir, "userscripts")

    init {
        if (!scriptsDir.exists()) {
            scriptsDir.mkdirs()
        }
    }

    fun saveCustomScript(scriptName: String, scriptContent: String) {
        val scriptFile = File(scriptsDir, scriptName)
        scriptFile.writeText(scriptContent)
    }

    fun loadCustomScript(scriptName: String): String? {
        val scriptFile = File(scriptsDir, scriptName)
        return if (scriptFile.exists()) {
            scriptFile.readText()
        } else {
            null
        }
    }

    fun listCustomScripts(): List<String> {
        return scriptsDir.listFiles()?.map { it.name } ?: emptyList()
    }

    fun deleteCustomScript(scriptName: String) {
        val scriptFile = File(scriptsDir, scriptName)
        scriptFile.delete()
    }
}
```

---

## 7. Input Handling - Controller/Gamepad Support

### Gamepad Detection and Mapping

```kotlin
class GamepadManager(private val activity: Activity) {

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
                Log.d("Gamepad", "Found gamepad: ${device.name}")
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
        InputManager.getInstance().registerInputDeviceListener(
            object : InputManager.InputDeviceListener {
                override fun onInputDeviceAdded(deviceId: Int) {
                    val device = InputDevice.getDevice(deviceId)
                    if (isGamepad(device)) {
                        connectedGamepads[deviceId] = device
                        Log.d("Gamepad", "Gamepad connected: ${device.name}")
                        notifyWebView("gamepadConnected", deviceId)
                    }
                }

                override fun onInputDeviceRemoved(deviceId: Int) {
                    connectedGamepads.remove(deviceId)
                    Log.d("Gamepad", "Gamepad disconnected: $deviceId")
                    notifyWebView("gamepadDisconnected", deviceId)
                }

                override fun onInputDeviceChanged(deviceId: Int) {
                    // Handle device changes
                }
            },
            null
        )
    }

    fun handleKeyEvent(event: KeyEvent): Boolean {
        // Map gamepad buttons to web gamepad API
        if (event.source and InputDevice.SOURCE_GAMEPAD == InputDevice.SOURCE_GAMEPAD) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_BUTTON_A -> sendToWebView("buttonA", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_B -> sendToWebView("buttonB", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_X -> sendToWebView("buttonX", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_Y -> sendToWebView("buttonY", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_L1 -> sendToWebView("leftShoulder", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_R1 -> sendToWebView("rightShoulder", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_L2 -> sendToWebView("leftTrigger", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_R2 -> sendToWebView("rightTrigger", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_THUMBL -> sendToWebView("leftStick", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_THUMBR -> sendToWebView("rightStick", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_START -> sendToWebView("start", event.action == KeyEvent.ACTION_DOWN)
                KeyEvent.KEYCODE_BUTTON_SELECT -> sendToWebView("select", event.action == KeyEvent.ACTION_DOWN)
                else -> return false
            }
            return true
        }
        return false
    }

    fun handleMotionEvent(event: MotionEvent): Boolean {
        if (event.source and InputDevice.SOURCE_JOYSTICK == InputDevice.SOURCE_JOYSTICK) {
            // Process joystick input
            val leftStickX = event.getAxisValue(MotionEvent.AXIS_X)
            val leftStickY = event.getAxisValue(MotionEvent.AXIS_Y)
            val rightStickX = event.getAxisValue(MotionEvent.AXIS_Z)
            val rightStickY = event.getAxisValue(MotionEvent.AXIS_RZ)

            val leftTrigger = event.getAxisValue(MotionEvent.AXIS_LTRIGGER)
            val rightTrigger = event.getAxisValue(MotionEvent.AXIS_RTRIGGER)

            // Send to WebView
            sendAnalogToWebView(leftStickX, leftStickY, rightStickX, rightStickY, leftTrigger, rightTrigger)

            return true
        }
        return false
    }

    private fun sendToWebView(button: String, pressed: Boolean) {
        val script = "window.dispatchEvent(new CustomEvent('androidGamepad', { detail: { button: '$button', pressed: $pressed } }));"
        (activity as? XCloudWebViewActivity)?.webView?.evaluateJavascript(script, null)
    }

    private fun sendAnalogToWebView(lx: Float, ly: Float, rx: Float, ry: Float, lt: Float, rt: Float) {
        val script = """
            window.dispatchEvent(new CustomEvent('androidGamepadAnalog', {
                detail: {
                    leftStick: { x: $lx, y: $ly },
                    rightStick: { x: $rx, y: $ry },
                    triggers: { left: $lt, right: $rt }
                }
            }));
        """.trimIndent()
        (activity as? XCloudWebViewActivity)?.webView?.evaluateJavascript(script, null)
    }

    private fun notifyWebView(event: String, deviceId: Int) {
        val script = "window.dispatchEvent(new CustomEvent('$event', { detail: { deviceId: $deviceId } }));"
        (activity as? XCloudWebViewActivity)?.webView?.evaluateJavascript(script, null)
    }
}
```

### Activity Integration

```kotlin
class XCloudWebViewActivity : Activity() {
    private lateinit var webView: WebView
    private lateinit var gamepadManager: GamepadManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gamepadManager = GamepadManager(this)

        // ... setup webView
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        event?.let {
            if (gamepadManager.handleKeyEvent(it)) {
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        event?.let {
            if (gamepadManager.handleKeyEvent(it)) {
                return true
            }
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        event?.let {
            if (gamepadManager.handleMotionEvent(it)) {
                return true
            }
        }
        return super.onGenericMotionEvent(event)
    }
}
```

### JavaScript Bridge for Gamepad

```kotlin
// In the injected userscript, handle Android gamepad events
val gamepadBridgeScript = """
    (function() {
        // Store gamepad state
        window.androidGamepads = {};

        // Listen for Android gamepad events
        window.addEventListener('androidGamepad', function(e) {
            const { button, pressed } = e.detail;

            // Create synthetic gamepad event for web compatibility
            const gamepadEvent = new Event(pressed ? 'gamepadbuttondown' : 'gamepadbuttonup');
            gamepadEvent.button = button;
            window.dispatchEvent(gamepadEvent);
        });

        window.addEventListener('androidGamepadAnalog', function(e) {
            const { leftStick, rightStick, triggers } = e.detail;

            // Update gamepad state
            window.androidGamepads[0] = {
                axes: [leftStick.x, leftStick.y, rightStick.x, rightStick.y],
                buttons: triggers
            };
        });

        // Override navigator.getGamepads() to include Android gamepads
        const originalGetGamepads = navigator.getGamepads.bind(navigator);
        navigator.getGamepads = function() {
            const webGamepads = originalGetGamepads();
            // Merge Android gamepads with web gamepads
            return [...webGamepads, ...Object.values(window.androidGamepads)];
        };
    })();
""".trimIndent()
```

---

## 8. TV-Specific UI - Leanback Design Patterns

### Design Principles for Android TV

1. **10-foot UI**: Design for viewing from 10 feet away
2. **Focus-based navigation**: Everything must be navigable with D-pad
3. **Overscan safe area**: Keep important content within 10% margins
4. **Large touch targets**: Minimum 48dp, recommended 64dp or larger
5. **High contrast**: Ensure text is readable from distance

### Custom Theme for TV

```xml
<!-- res/values/styles.xml -->
<resources>
    <style name="Theme.BetterXCloud.Leanback" parent="Theme.Leanback">
        <!-- Brand colors -->
        <item name="colorPrimary">@color/xcloud_green</item>
        <item name="colorPrimaryDark">@color/xcloud_dark_green</item>
        <item name="colorAccent">@color/xcloud_accent</item>

        <!-- Search orb color -->
        <item name="searchOrbViewColor">@color/xcloud_green</item>

        <!-- Default brand color -->
        <item name="defaultBrandColor">@color/xcloud_green</item>

        <!-- Window background -->
        <item name="android:windowBackground">@color/black</item>

        <!-- Focus colors -->
        <item name="itemFocusedBackground">@color/xcloud_green_30</item>
        <item name="itemSelectedBackground">@color/xcloud_green_50</item>
    </style>

    <style name="Theme.BetterXCloud.Browse" parent="Theme.Leanback.Browse">
        <item name="defaultBrandColorDark">@color/xcloud_dark_green</item>
        <item name="overlayDimMaskColor">@color/black_80</item>
    </style>
</resources>

<!-- res/values/colors.xml -->
<resources>
    <color name="xcloud_green">#107C10</color>
    <color name="xcloud_dark_green">#0B5A0B</color>
    <color name="xcloud_accent">#00B4FF</color>
    <color name="xcloud_green_30">#4D107C10</color>
    <color name="xcloud_green_50">#80107C10</color>
    <color name="black">#000000</color>
    <color name="black_80">#CC000000</color>
    <color name="white">#FFFFFF</color>
</resources>
```

### Custom Card Presenter

```kotlin
class GameCardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true

            // Set card dimensions for TV
            setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
        }

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val game = item as Game
        val cardView = viewHolder.view as ImageCardView

        cardView.apply {
            titleText = game.title
            contentText = game.description

            // Load game image
            Glide.with(context)
                .load(game.imageUrl)
                .placeholder(R.drawable.default_game_card)
                .error(R.drawable.error_game_card)
                .into(mainImageView)

            // Set card background color
            setInfoAreaBackgroundColor(ContextCompat.getColor(context, R.color.xcloud_dark_green))
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.apply {
            badgeImage = null
            mainImage = null
        }
    }

    companion object {
        private const val CARD_WIDTH = 313
        private const val CARD_HEIGHT = 176
    }
}
```

### Focus Highlight Customization

```kotlin
class CustomFocusHighlight : FocusHighlightHandler() {

    override fun onItemFocused(view: View?, hasFocus: Boolean) {
        view?.let {
            if (hasFocus) {
                // Scale up and elevate when focused
                it.animate()
                    .scaleX(1.1f)
                    .scaleY(1.1f)
                    .translationZ(10f)
                    .setDuration(150)
                    .start()
            } else {
                // Scale down when focus is lost
                it.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .translationZ(0f)
                    .setDuration(150)
                    .start()
            }
        }
    }
}
```

### Search Implementation

```kotlin
class SearchFragment : SearchSupportFragment(),
    SearchSupportFragment.SearchResultProvider {

    private lateinit var rowsAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        setSearchResultProvider(this)

        setOnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item is Game) {
                launchGame(item)
            }
        }
    }

    override fun getResultsAdapter(): ObjectAdapter {
        return rowsAdapter
    }

    override fun onQueryTextChange(newQuery: String): Boolean {
        if (newQuery.isNotEmpty()) {
            performSearch(newQuery)
        }
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        performSearch(query)
        return true
    }

    private fun performSearch(query: String) {
        // Search implementation
        val searchResults = searchGames(query)

        rowsAdapter.clear()

        if (searchResults.isNotEmpty()) {
            val header = HeaderItem("Search Results")
            val listRowAdapter = ArrayObjectAdapter(GameCardPresenter())

            searchResults.forEach { game ->
                listRowAdapter.add(game)
            }

            rowsAdapter.add(ListRow(header, listRowAdapter))
        }
    }

    private fun searchGames(query: String): List<Game> {
        // Implement game search logic
        return emptyList()
    }

    private fun launchGame(game: Game) {
        // Launch xCloud with specific game
        val intent = Intent(requireContext(), XCloudWebViewActivity::class.java).apply {
            putExtra("game_id", game.id)
            putExtra("game_url", game.playUrl)
        }
        startActivity(intent)
    }
}
```

### Guided Sequence for First-Time Setup

```kotlin
class SetupGuidedStepFragment : GuidedStepSupportFragment() {

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance(
            "Welcome to Better xCloud",
            "Let's get you set up to stream Xbox games on your Android TV",
            "Step 1 of 3",
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_xbox_logo)
        )
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        actions.add(GuidedAction.Builder(context)
            .id(ACTION_CONTINUE)
            .title("Continue")
            .build())
    }

    override fun onGuidedActionClicked(action: GuidedAction) {
        when (action.id) {
            ACTION_CONTINUE -> {
                // Move to next step
                GuidedStepSupportFragment.add(
                    fragmentManager!!,
                    PermissionsGuidedStepFragment()
                )
            }
        }
    }

    companion object {
        const val ACTION_CONTINUE = 1L
    }
}
```

### Overscan Safe Layout

```xml
<!-- Use padding to account for overscan -->
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="48dp">

    <!-- Critical UI elements stay within safe area -->
    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="36sp"
        android:textColor="@color/white" />

</FrameLayout>
```

---

## Additional Recommendations

### Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/betterxcloud/
│   │   │   ├── activities/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── XCloudWebViewActivity.kt
│   │   │   │   └── SettingsActivity.kt
│   │   │   ├── fragments/
│   │   │   │   ├── MainFragment.kt
│   │   │   │   ├── SearchFragment.kt
│   │   │   │   └── GameDetailsFragment.kt
│   │   │   ├── presenters/
│   │   │   │   ├── GameCardPresenter.kt
│   │   │   │   └── SettingsPresenter.kt
│   │   │   ├── managers/
│   │   │   │   ├── GamepadManager.kt
│   │   │   │   ├── WebViewManager.kt
│   │   │   │   └── UserScriptManager.kt
│   │   │   ├── models/
│   │   │   │   ├── Game.kt
│   │   │   │   └── UserPreferences.kt
│   │   │   └── utils/
│   │   │       ├── FocusHelper.kt
│   │   │       └── PerformanceMonitor.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── drawable/
│   │   │   ├── values/
│   │   │   └── xml/
│   │   ├── assets/
│   │   │   └── better-xcloud.user.js
│   │   └── AndroidManifest.xml
│   └── tv/
│       └── AndroidManifest.xml (TV-specific manifest)
```

### Gradle Configuration for API 25

```gradle
// app/build.gradle
android {
    namespace 'com.betterxcloud.android'
    compileSdk 33

    defaultConfig {
        applicationId "com.betterxcloud.android"
        minSdk 25  // Android TV 7.x
        targetSdk 33
        versionCode 1
        versionName "1.0.0"
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
    // Android TV Leanback
    implementation 'androidx.leanback:leanback:1.0.0'
    implementation 'androidx.leanback:leanback-preference:1.0.0'

    // AndroidX Core
    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.4.2'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

    // UI Components
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'com.google.android.material:material:1.6.1'

    // Image Loading (optimized for TV)
    implementation 'com.github.bumptech.glide:glide:4.13.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.13.2'

    // Networking
    implementation 'com.squareup.okhttp3:okhttp:4.9.3'

    // Kotlin Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'

    // Lifecycle
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
}
```

### ProGuard Rules for WebView

```proguard
# app/proguard-rules.pro

# Keep JavaScript interface methods
-keepclassmembers class com.betterxcloud.android.** {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep WebView classes
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}

-keepclassmembers class * extends android.webkit.WebChromeClient {
    public void *(android.webkit.WebView, java.lang.String);
}

# Keep Leanback classes
-keep class androidx.leanback.** { *; }

# Keep game models (if using reflection)
-keep class com.betterxcloud.android.models.** { *; }
```

### Testing on Fire Stick 4K

To test on Fire Stick 4K:

1. Enable ADB debugging on Fire Stick:
   - Settings > My Fire TV > Developer Options > ADB Debugging

2. Connect via ADB:
   ```bash
   adb connect <firestick-ip-address>:5555
   adb devices
   ```

3. Install APK:
   ```bash
   adb install -r app-release.apk
   ```

4. Monitor performance:
   ```bash
   # Check memory usage
   adb shell dumpsys meminfo com.betterxcloud.android

   # Monitor CPU usage
   adb shell top -n 1 | grep betterxcloud

   # Check frame rate
   adb shell dumpsys gfxinfo com.betterxcloud.android
   ```

### Performance Benchmarks for Fire Stick 4K

Target metrics:
- **App cold start**: < 3 seconds
- **WebView initialization**: < 2 seconds
- **Video playback**: 60 fps (1080p)
- **Memory usage**: < 150 MB (idle), < 300 MB (streaming)
- **Input latency**: < 50ms (controller to screen)

---

## Key Challenges and Solutions

### Challenge 1: Limited RAM (1.5GB)
**Solution**:
- Use single WebView instance
- Aggressive memory cleanup
- Disable unused WebView features
- Use RGB_565 for images
- Implement onTrimMemory() callbacks

### Challenge 2: WebRTC Compatibility
**Solution**:
- Use Chrome user agent
- Grant all WebRTC permissions automatically
- Enable hardware acceleration
- Keep screen on during streaming

### Challenge 3: D-pad Navigation in WebView
**Solution**:
- Inject spatial navigation JavaScript
- Override key events
- Implement custom focus management
- Provide visual focus indicators

### Challenge 4: Gamepad Input Passthrough
**Solution**:
- Capture gamepad events natively
- Bridge to JavaScript via custom events
- Override navigator.getGamepads()
- Support both native and web gamepad APIs

### Challenge 5: TV-Optimized UI
**Solution**:
- Use Leanback components throughout
- Design for 10-foot viewing distance
- Implement overscan-safe layouts
- Provide guided setup flow

---

## Next Steps for Implementation

1. **Phase 1: Basic WebView App**
   - Create project structure
   - Implement WebView with xCloud
   - Add basic navigation
   - Test on Fire Stick

2. **Phase 2: Leanback UI**
   - Implement BrowseFragment
   - Add game library UI
   - Create settings screens
   - Implement search

3. **Phase 3: Script Injection**
   - Bundle Better xCloud script
   - Implement injection mechanism
   - Add JavaScript bridge
   - Support custom scripts

4. **Phase 4: Input Handling**
   - Implement gamepad support
   - Add D-pad navigation
   - Test with various controllers
   - Optimize input latency

5. **Phase 5: Optimization**
   - Profile memory usage
   - Optimize rendering
   - Reduce app size
   - Test on Fire Stick 4K

6. **Phase 6: Polish**
   - Add error handling
   - Implement analytics
   - Create user documentation
   - Prepare for release

---

## References and Resources

- [Android TV Developer Guide](https://developer.android.com/training/tv)
- [Leanback Library Documentation](https://developer.android.com/reference/androidx/leanback/app/package-summary)
- [WebView Best Practices](https://developer.android.com/guide/webapps/webview)
- [Android Game Controller Support](https://developer.android.com/training/game-controllers)
- [Fire TV Development](https://developer.amazon.com/docs/fire-tv/getting-started-developing-apps-and-games.html)
- [Better xCloud Script Repository](https://github.com/redphx/better-xcloud)

---

**Document Version**: 1.0
**Last Updated**: 2025-11-15
**Target API Level**: 25 (Android 7.1.1)
**Target Device**: Fire Stick 4K (1st Generation)
