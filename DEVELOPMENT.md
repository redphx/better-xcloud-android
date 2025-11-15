# Better xCloud Android TV - Development Guide

## Project Overview

This is a native Android TV application that ports the Better xCloud userscript to provide an enhanced Xbox Cloud Gaming experience on Android TV OS 7.x and Amazon Fire Stick 4K (1st Generation).

## Architecture

The app uses a **hybrid architecture**:

- **Native Layer**: Android TV Leanback UI, gamepad handling, memory optimization
- **Web Layer**: WebView hosting xCloud interface with Better xCloud modifications
- **Bridge Layer**: JavaScript interface for two-way communication

### Key Components

```
app/src/main/java/com/betterxcloud/android/
├── XCloudApplication.kt              # Application class with lifecycle management
├── ui/
│   ├── MainActivity.kt                # Leanback TV launcher with browse interface
│   ├── XCloudWebViewActivity.kt       # WebView activity for xCloud streaming
│   └── SettingsActivity.kt            # Settings screen
├── managers/
│   └── GamepadManager.kt              # Gamepad input handling and injection
└── utils/
    └── XCloudJavaScriptInterface.kt   # JavaScript bridge for WebView communication
```

## Setup Instructions

### Prerequisites

1. **Android Studio** Electric Eel (2022.1.1) or later
2. **JDK** 11 or later
3. **Android SDK** with API Level 33
4. **Fire Stick 4K** or Android TV device for testing

### Clone and Build

```bash
# Clone the repository
git clone https://github.com/redphx/better-xcloud-android.git
cd better-xcloud-android

# Open in Android Studio
# File > Open > better-xcloud-android

# Build the project
./gradlew assembleDebug

# Or build release
./gradlew assembleRelease
```

### APK Output

After building, APK files will be located at:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

## Testing on Fire Stick 4K

### Enable ADB Debugging

1. On Fire Stick, go to: **Settings** > **My Fire TV** > **Developer Options**
2. Enable **ADB Debugging**
3. Enable **Apps from Unknown Sources**
4. Note the IP address from **About**

### Install via ADB

```bash
# Connect to Fire Stick
adb connect <firestick-ip>:5555

# Verify connection
adb devices

# Install the app
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Launch the app
adb shell am start -n com.betterxcloud.android.debug/.ui.MainActivity

# View logs
adb logcat | grep -E "(XCloud|Better|GamepadManager)"
```

### Debugging

```bash
# Monitor memory usage
adb shell dumpsys meminfo com.betterxcloud.android.debug

# Monitor graphics performance
adb shell dumpsys gfxinfo com.betterxcloud.android.debug

# Clear app data
adb shell pm clear com.betterxcloud.android.debug

# Uninstall
adb uninstall com.betterxcloud.android.debug
```

## WebView Debugging

The app enables WebView debugging in debug builds. To inspect the WebView:

1. Connect Fire Stick via ADB
2. Open Chrome on your computer
3. Navigate to `chrome://inspect`
4. Find the Better xCloud WebView under "Remote Target"
5. Click "inspect" to open DevTools

## Better xCloud Script Integration

### Using the Official Script

The app includes a placeholder Better xCloud script at `app/src/main/assets/better-xcloud.user.js`.

To use the official script:

```bash
# Download the latest script
curl -o app/src/main/assets/better-xcloud.user.js \
  https://raw.githubusercontent.com/redphx/better-xcloud/typescript/dist/better-xcloud.user.js

# Rebuild the app
./gradlew clean assembleDebug
```

### Custom Scripts

You can modify the script at `app/src/main/assets/better-xcloud.user.js` to add custom features:

```javascript
// Access Android interface
if (window.AndroidInterface) {
    window.AndroidInterface.log('Custom feature loaded');
}

// Expose functions to Android
window.BX_EXPOSED = window.BX_EXPOSED || {};
window.BX_EXPOSED.customFunction = function() {
    // Your code here
};
```

## Key Features Implementation

### Gamepad Support

The `GamepadManager` class handles native gamepad events and forwards them to the WebView:

```kotlin
// In XCloudWebViewActivity.kt
override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
    if (gamepadManager.handleKeyEvent(event)) {
        return true
    }
    return super.onKeyDown(keyCode, event)
}
```

Button mapping follows the Standard Gamepad layout (Xbox controller):
- A/B/X/Y buttons
- D-pad
- Bumpers and triggers
- Analog sticks
- Menu buttons

### WebView Configuration

The WebView is configured for optimal xCloud compatibility:

```kotlin
webView.settings.apply {
    javaScriptEnabled = true
    domStorageEnabled = true
    mediaPlaybackRequiresUserGesture = false
    userAgentString = CHROME_USER_AGENT  // Chrome UA required
    setLayerType(View.LAYER_TYPE_HARDWARE, null)  // Hardware acceleration
}
```

### Memory Optimization

The app implements aggressive memory management for Fire Stick's limited RAM (1.5GB):

```kotlin
override fun onTrimMemory(level: Int) {
    when (level) {
        TRIM_MEMORY_RUNNING_CRITICAL -> {
            webView.clearCache(true)
            System.gc()
        }
    }
}
```

**Memory Budget:**
- Target: <300MB during streaming
- Single WebView instance
- RGB_565 bitmap format
- Aggressive cache cleanup

## Performance Optimization

### Fire Stick 4K Specific

The Fire Stick 4K (1st Gen) has these constraints:
- **RAM**: 1.5 GB total (~900MB available for apps)
- **CPU**: Quad-core 1.7 GHz
- **GPU**: IMG GE8300
- **Storage**: 8 GB

Optimizations applied:
1. Hardware-accelerated WebView rendering
2. Single WebView instance (no recreation)
3. Minimal UI overhead with Leanback
4. Aggressive memory cleanup on low memory warnings
5. No background services

### Performance Targets

| Metric | Target | Critical |
|--------|--------|----------|
| App cold start | < 3s | No |
| WebView init | < 2s | No |
| Video playback | 60fps @ 1080p | Yes |
| Memory (idle) | < 150MB | Yes |
| Memory (streaming) | < 300MB | Yes |
| Input latency | < 50ms | Yes |

## Build Variants

The app supports two build types:

### Debug
- Package: `com.betterxcloud.android.debug`
- WebView debugging enabled
- Verbose logging
- No obfuscation

### Release
- Package: `com.betterxcloud.android`
- ProGuard/R8 enabled
- Minimal logging
- Optimized and shrunk

## ProGuard Configuration

The release build uses ProGuard rules in `app/proguard-rules.pro`:

- Keeps WebView JavaScript interfaces
- Preserves Leanback classes
- Maintains line numbers for crash reports

## Common Issues & Solutions

### Issue: WebView shows blank page

**Solution:**
```bash
# Check internet connection
adb shell ping -c 4 www.xbox.com

# Check WebView version
adb shell dumpsys package com.android.webview | grep versionName

# Clear app data and retry
adb shell pm clear com.betterxcloud.android.debug
```

### Issue: Gamepad not working

**Solution:**
1. Check gamepad is paired in Fire Stick settings
2. Test gamepad in other apps
3. Check logcat for gamepad events:
   ```bash
   adb logcat | grep "GamepadManager"
   ```

### Issue: High memory usage

**Solution:**
1. Monitor with: `adb shell dumpsys meminfo com.betterxcloud.android.debug`
2. Check for memory leaks in logcat
3. Ensure only one WebView instance exists

### Issue: Stream not loading

**Solution:**
1. Verify xCloud works in regular Chrome on the device
2. Check user agent is set correctly
3. Enable WebView debugging and check console errors

## Testing Checklist

Before releasing, test the following:

### Basic Functionality
- [ ] App launches on Fire Stick
- [ ] Leanback UI navigable with D-pad
- [ ] xCloud website loads in WebView
- [ ] Can sign in to Xbox account
- [ ] Can select and launch a game

### Gamepad Input
- [ ] All buttons register correctly
- [ ] Analog sticks work smoothly
- [ ] Triggers function properly
- [ ] D-pad works both in UI and in-game

### Performance
- [ ] Memory stays under 300MB during streaming
- [ ] No frame drops during gameplay
- [ ] Audio/video stay in sync
- [ ] No crashes during extended play

### Edge Cases
- [ ] Handle network disconnection gracefully
- [ ] Resume after app backgrounding
- [ ] Handle low memory conditions
- [ ] Back button navigation works

## Contributing

### Code Style

- Follow Kotlin coding conventions
- Use descriptive variable names
- Add comments for complex logic
- Keep functions small and focused

### Submitting Changes

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test on Fire Stick 4K
5. Submit a pull request

## Resources

### Documentation
- [Android TV Developer Guide](https://developer.android.com/training/tv)
- [Leanback Library](https://developer.android.com/reference/androidx/leanback/app/package-summary)
- [WebView Guide](https://developer.android.com/guide/webapps/webview)
- [Fire TV Development](https://developer.amazon.com/docs/fire-tv/getting-started-developing-apps-and-games.html)

### Better xCloud
- [Homepage](https://better-xcloud.github.io)
- [Userscript Repository](https://github.com/redphx/better-xcloud)
- [Telegram Channel](https://t.me/betterxcloud)

## License

MIT License - See LICENSE file for details

## Support

- **GitHub Issues**: https://github.com/redphx/better-xcloud-android/issues
- **Telegram**: https://t.me/betterxcloud
