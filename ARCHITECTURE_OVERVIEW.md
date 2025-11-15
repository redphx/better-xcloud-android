# Better xCloud Android TV - Architecture Overview

## System Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                        Android TV UI Layer                       │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐          │
│  │ BrowseFragment│  │SearchFragment│  │DetailsFragment│         │
│  │  (Leanback)   │  │  (Leanback)  │  │  (Leanback)   │         │
│  └──────┬───────┘  └──────┬───────┘  └──────┬────────┘         │
│         │                  │                  │                   │
│         └──────────────────┴──────────────────┘                  │
│                            │                                      │
└────────────────────────────┼──────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                    WebView Activity Layer                        │
│  ┌────────────────────────────────────────────────────────┐     │
│  │              XCloudWebViewActivity                      │     │
│  │  ┌──────────────────────────────────────────────┐     │     │
│  │  │           WebView (xCloud Web App)           │     │     │
│  │  │  ┌────────────────────────────────────┐     │     │     │
│  │  │  │   Better xCloud UserScript         │     │     │     │
│  │  │  │   (Injected JavaScript)            │     │     │     │
│  │  │  └────────────────────────────────────┘     │     │     │
│  │  └──────────────────────────────────────────────┘     │     │
│  └────────────────────────────────────────────────────────┘     │
└──────────────────────────┬───────────────────────────────────────┘
                           │
          ┌────────────────┼────────────────┐
          │                │                 │
          ▼                ▼                 ▼
┌─────────────────┐ ┌─────────────┐ ┌──────────────────┐
│  GamepadManager │ │ ScriptManager│ │ PerformanceMonitor│
│                 │ │              │ │                   │
│ • Input capture │ │ • Injection  │ │ • Memory tracking│
│ • Button mapping│ │ • JS bridge  │ │ • FPS monitoring │
│ • Analog sticks │ │ • Custom     │ │ • Optimization   │
└─────────────────┘ └─────────────┘ └──────────────────┘
          │                │                 │
          └────────────────┴─────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Native Android Services                       │
│  • InputManager (Gamepad detection)                             │
│  • WindowManager (Fullscreen, UI visibility)                    │
│  • Vibrator (Haptic feedback)                                   │
│  • SharedPreferences (Settings storage)                         │
│  • NetworkManager (Connection monitoring)                       │
└─────────────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                      Xbox Cloud Gaming                           │
│                      (WebRTC Streaming)                          │
└─────────────────────────────────────────────────────────────────┘
```

## Component Breakdown

### 1. UI Layer (Leanback)

**Purpose**: Provide native Android TV navigation and browsing experience

**Components**:
- `MainActivity`: Entry point with Leanback BrowseFragment
- `BrowseFragment`: Main library view with game categories
- `SearchFragment`: Search functionality for finding games
- `DetailsFragment`: Game details and launch options
- `SettingsActivity`: App configuration

**Key Features**:
- D-pad friendly navigation
- Game library browsing
- Search with voice support
- Settings management
- Recent games history

### 2. WebView Layer

**Purpose**: Host Xbox Cloud Gaming web interface

**Components**:
- `XCloudWebViewActivity`: Full-screen WebView container
- `XCloudWebViewClient`: URL handling and page lifecycle
- `XCloudChromeClient`: Permissions and console logging
- `FocusableWebView`: Custom WebView with D-pad support

**Key Features**:
- Full-screen immersive mode
- Hardware acceleration
- WebRTC support
- Custom user agent
- Session persistence

### 3. Script Injection Layer

**Purpose**: Inject and manage Better xCloud userscript

**Components**:
- `UserScriptInjector`: Script loading and injection
- `XCloudJavaScriptInterface`: Native ↔ JavaScript bridge
- `CustomScriptManager`: User-provided script support

**Key Features**:
- Automatic script injection on page load
- Two-way communication (native ↔ web)
- Custom script support
- Settings synchronization
- Platform detection (TV mode)

### 4. Input Management Layer

**Purpose**: Handle gamepad and remote control input

**Components**:
- `GamepadManager`: Gamepad detection and event handling
- `InputMapper`: Map native input to web events
- `FocusManager`: D-pad navigation in UI

**Key Features**:
- Automatic gamepad detection
- Button and analog stick mapping
- Fire TV remote support
- Input latency optimization
- Multi-controller support

### 5. Performance Layer

**Purpose**: Optimize for Fire Stick 4K's limited resources

**Components**:
- `WebViewManager`: WebView lifecycle and memory management
- `PerformanceMonitor`: Resource usage tracking
- `CacheManager`: Cache optimization

**Key Features**:
- Memory monitoring
- Automatic cache cleanup
- Hardware acceleration
- Resource prioritization
- Low memory handling

## Data Flow

### Launch Game Flow

```
User selects game in BrowseFragment
            ↓
GameDetailsFragment shows game info
            ↓
User clicks "Play" button
            ↓
Launch XCloudWebViewActivity with game URL
            ↓
WebView loads Xbox Cloud Gaming
            ↓
On page load, inject Better xCloud script
            ↓
Script modifies web UI for TV
            ↓
User signs in (if needed)
            ↓
Game streaming starts via WebRTC
            ↓
Native gamepad input → JavaScript events
            ↓
Game plays
```

### Input Processing Flow

```
Physical controller button press
            ↓
Android InputManager captures event
            ↓
GamepadManager.handleKeyEvent()
            ↓
Map to web gamepad button
            ↓
Inject JavaScript event via WebView
            ↓
Better xCloud script processes event
            ↓
Web Gamepad API receives input
            ↓
xCloud web app sends to game server
            ↓
Game responds to input
```

### Script Injection Flow

```
WebView starts loading xCloud page
            ↓
XCloudWebViewClient.onPageStarted()
            ↓
Page finishes loading
            ↓
XCloudWebViewClient.onPageFinished()
            ↓
Load Better xCloud script from assets
            ↓
Wrap script in IIFE
            ↓
WebView.evaluateJavascript()
            ↓
Script executes and modifies DOM
            ↓
Register JavaScript interface callbacks
            ↓
Script communicates with native code
```

## State Management

### Application State

```kotlin
sealed class AppState {
    object Loading : AppState()
    object Browse : AppState()
    data class GameDetails(val game: Game) : AppState()
    data class Playing(val gameId: String) : AppState()
    data class Error(val message: String) : AppState()
}
```

### WebView State

```kotlin
sealed class WebViewState {
    object Initializing : WebViewState()
    object Loading : WebViewState()
    object Ready : WebViewState()
    object SignInRequired : WebViewState()
    object Streaming : WebViewState()
    data class Error(val error: WebResourceError) : WebViewState()
}
```

### Gamepad State

```kotlin
data class GamepadState(
    val deviceId: Int,
    val deviceName: String,
    val isConnected: Boolean,
    val buttons: Map<String, Boolean>,
    val axes: Map<String, Float>
)
```

## Memory Management Strategy

### Fire Stick 4K Memory Constraints

Total RAM: 1.5 GB
- System reserved: ~600 MB
- Available for apps: ~900 MB
- Target app usage: < 300 MB (streaming)

### Memory Allocation Budget

```
WebView:                    150 MB
  - Page content:            50 MB
  - JavaScript heap:         40 MB
  - Media buffers:           60 MB

Native code:                 80 MB
  - Activities/Fragments:    30 MB
  - Bitmaps/Images:          20 MB
  - Other:                   30 MB

System overhead:             70 MB

Total target:               300 MB
Safety margin:               50 MB

Maximum allowed:            350 MB
```

### Memory Optimization Techniques

1. **Lazy initialization**
   - Create WebView only when needed
   - Load images on-demand

2. **Resource cleanup**
   - Clear WebView cache on low memory
   - Release bitmaps when off-screen
   - Remove unused adapters

3. **Efficient data structures**
   - Use SparseArray instead of HashMap
   - Use ArrayMap for small collections
   - Avoid object allocations in loops

4. **Image optimization**
   - Use RGB_565 format (16-bit)
   - Downsample large images
   - Cache with LRU policy

## Threading Model

### Main Thread (UI Thread)
- UI updates
- Fragment transitions
- User input handling
- WebView JavaScript evaluation

### Background Thread
- Network requests
- File I/O
- Database operations
- Image loading

### WebView Thread
- Page rendering
- JavaScript execution
- WebRTC streaming

## Network Architecture

### Connections

1. **Xbox Cloud Gaming**
   - Main connection: HTTPS to xbox.com/play
   - Authentication: OAuth via Microsoft
   - Streaming: WebRTC (DTLS-SRTP)

2. **Better xCloud Updates**
   - Script updates: HTTPS to GitHub
   - Configuration: Cloud settings sync

### Network Optimization

```kotlin
class NetworkOptimizer {
    fun optimizeForStreaming() {
        // Prioritize WebRTC traffic
        TrafficStats.setThreadStatsTag(STREAMING_TAG)

        // Request high-speed network
        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
            .build()

        // Monitor connection quality
        connectivityManager.registerNetworkCallback(request, callback)
    }
}
```

## Security Considerations

### WebView Security

```kotlin
webView.settings.apply {
    // Prevent file access exploits
    allowFileAccess = false
    allowFileAccessFromFileURLs = false
    allowUniversalAccessFromFileURLs = false

    // Enable safe browsing (API 27+, but prepare for future)
    // safeBrowsingEnabled = true

    // Prevent mixed content attacks
    mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
}
```

### Script Validation

```kotlin
class ScriptValidator {
    fun validateScript(script: String): Boolean {
        // Check for malicious patterns
        val dangerousPatterns = listOf(
            "eval\\(",
            "Function\\(",
            "XMLHttpRequest\\(",
            // Add more patterns
        )

        return dangerousPatterns.none { script.contains(Regex(it)) }
    }
}
```

## Testing Strategy

### Unit Tests
- Input mapping logic
- Script injection
- State management
- Memory management

### Integration Tests
- WebView integration
- Gamepad detection
- Navigation flows
- Settings persistence

### UI Tests (Espresso)
- Leanback navigation
- D-pad interaction
- Focus management

### Device Tests
- Fire Stick 4K (1st gen)
- Other Android TV devices
- Various controllers

### Performance Tests
- Memory profiling
- Frame rate monitoring
- Input latency measurement
- Network performance

## Build Variants

### Debug Build
```gradle
debug {
    applicationIdSuffix ".debug"
    debuggable true
    minifyEnabled false

    buildConfigField "boolean", "ENABLE_LOGGING", "true"
    buildConfigField "String", "SCRIPT_URL", "\"http://localhost:8080/dev-script.js\""
}
```

### Release Build
```gradle
release {
    minifyEnabled true
    shrinkResources true
    proguardFiles getDefaultProguardFile('proguard-android-optimize.txt')

    buildConfigField "boolean", "ENABLE_LOGGING", "false"
    buildConfigField "String", "SCRIPT_URL", "\"https://cdn.jsdelivr.net/gh/redphx/better-xcloud/\""
}
```

### Fire Stick Optimized Build
```gradle
fireStick {
    initWith release
    applicationIdSuffix ".firestick"

    buildConfigField "int", "MAX_MEMORY_MB", "300"
    buildConfigField "boolean", "AGGRESSIVE_MEMORY_MANAGEMENT", "true"
}
```

## Deployment Strategy

### App Distribution

1. **GitHub Releases**
   - Primary distribution channel
   - APK downloads
   - Release notes

2. **Amazon Appstore**
   - Native Fire TV distribution
   - Easier installation for users
   - App updates

3. **Sideloading**
   - APK via ADB
   - For beta testing

### Update Mechanism

```kotlin
class UpdateChecker {
    suspend fun checkForUpdates(): UpdateInfo? {
        val latestRelease = fetchLatestRelease()
        val currentVersion = BuildConfig.VERSION_CODE

        return if (latestRelease.versionCode > currentVersion) {
            UpdateInfo(
                version = latestRelease.versionName,
                downloadUrl = latestRelease.apkUrl,
                releaseNotes = latestRelease.notes
            )
        } else {
            null
        }
    }
}
```

## Monitoring and Analytics

### Performance Metrics

```kotlin
class AnalyticsTracker {
    fun trackPerformance() {
        // App launch time
        trackMetric("app_launch_time", launchDuration)

        // WebView load time
        trackMetric("webview_load_time", loadDuration)

        // Average FPS
        trackMetric("average_fps", fps)

        // Memory usage
        trackMetric("memory_usage_mb", memoryMB)

        // Input latency
        trackMetric("input_latency_ms", latencyMs)
    }

    fun trackError(error: Throwable) {
        // Log errors for debugging
        trackEvent("error", mapOf(
            "message" to error.message,
            "stack_trace" to error.stackTraceToString()
        ))
    }
}
```

## Future Enhancements

### Phase 1 (MVP)
- Basic WebView with xCloud
- Script injection
- Gamepad support
- Simple Leanback UI

### Phase 2
- Game library management
- Recent games tracking
- Search functionality
- Voice search support

### Phase 3
- Multiple account support
- Cloud save sync status
- Achievement tracking
- Friends list integration

### Phase 4
- Picture-in-picture mode
- Multi-window support
- Chromecast integration
- Enhanced controller support

### Phase 5
- Offline mode (cached games)
- Background download
- Advanced video settings
- Streaming quality presets

## Development Tools

### Recommended IDE Setup
- Android Studio Electric Eel or later
- Kotlin plugin 1.9.22
- Android SDK Platform 33
- Android SDK Build-Tools 33.0.1

### Debugging Tools

```bash
# Enable WebView debugging
adb shell am set-debug-app --persistent com.betterxcloud.android

# Monitor logs
adb logcat | grep -E "(BetterXCloud|WebView|Gamepad)"

# Profile memory
adb shell dumpsys meminfo com.betterxcloud.android

# Capture layout inspector
adb shell uiautomator dump

# Record screen
adb shell screenrecord /sdcard/demo.mp4
```

### Testing Commands

```bash
# Install debug build
./gradlew installDebug

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Generate APK
./gradlew assembleRelease

# Profile startup time
adb shell am start -W -n com.betterxcloud.android/.MainActivity
```

## Troubleshooting Guide

### Common Issues

**Issue**: WebView shows blank page
**Solution**: Check user agent, enable JavaScript, grant permissions

**Issue**: Gamepad not detected
**Solution**: Verify InputDevice.SOURCE_GAMEPAD, check USB/Bluetooth connection

**Issue**: High memory usage
**Solution**: Clear WebView cache, reduce image quality, check for leaks

**Issue**: Poor streaming quality
**Solution**: Check network speed, disable bandwidth-heavy features

**Issue**: D-pad navigation not working
**Solution**: Verify focusable attributes, check nextFocus* properties

## Conclusion

This architecture provides a solid foundation for porting Better xCloud to Android TV while addressing the unique constraints of the Fire Stick 4K platform. The modular design allows for iterative development and easy maintenance.

Key success factors:
1. Efficient memory management
2. Optimized WebView configuration
3. Robust input handling
4. Native TV UI with Leanback
5. Performance monitoring and optimization

The architecture is designed to be extensible for future enhancements while maintaining backward compatibility with API Level 25.

---

**Document Version**: 1.0
**Last Updated**: 2025-11-15
**Status**: Draft
