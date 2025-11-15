# Android 7.x (API 24-25) - Required Code Fixes
## Quick Reference Guide

This document provides the exact code changes needed for Android 7.x compatibility.

---

## CRITICAL FIXES (Must Implement)

### 1. Fix safeBrowsingEnabled API Check

**File:** `app/src/main/java/com/betterxcloud/android/ui/XCloudWebViewActivity.kt`
**Line:** 173

**Current Code:**
```kotlin
// Issue #2 - Enable safe browsing for security
safeBrowsingEnabled = true
```

**Fixed Code:**
```kotlin
// Issue #2 - Enable safe browsing for security (API 26+)
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    safeBrowsingEnabled = true
}
```

---

### 2. Add WRITE_EXTERNAL_STORAGE Permission

**File:** `app/src/main/AndroidManifest.xml`
**Location:** After line 12 (with other permissions)

**Add This:**
```xml
<!-- Storage permission for downloads (required for API 24-28) -->
<uses-permission
    android:name="android.permission.WRITE_EXTERNAL_STORAGE"
    android:maxSdkVersion="28" />
```

---

### 3. Implement Runtime Permissions

**File:** `app/src/main/java/com/betterxcloud/android/ui/XCloudWebViewActivity.kt`

**Add to companion object:**
```kotlin
companion object {
    // ... existing constants ...

    private const val PERMISSIONS_REQUEST_CODE = 100
    private val REQUIRED_PERMISSIONS = arrayOf(
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.CAMERA
    )
}
```

**Add these methods to XCloudWebViewActivity class:**
```kotlin
private fun hasRequiredPermissions(): Boolean {
    return REQUIRED_PERMISSIONS.all { permission ->
        androidx.core.content.ContextCompat.checkSelfPermission(this, permission) ==
            android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}

private fun requestRequiredPermissions() {
    val permissionsToRequest = REQUIRED_PERMISSIONS.filter { permission ->
        androidx.core.content.ContextCompat.checkSelfPermission(this, permission) !=
            android.content.pm.PackageManager.PERMISSION_GRANTED
    }.toTypedArray()

    if (permissionsToRequest.isNotEmpty()) {
        androidx.core.app.ActivityCompat.requestPermissions(
            this,
            permissionsToRequest,
            PERMISSIONS_REQUEST_CODE
        )
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    when (requestCode) {
        PERMISSIONS_REQUEST_CODE -> {
            if (grantResults.all { it == android.content.pm.PackageManager.PERMISSION_GRANTED }) {
                // All permissions granted
                Log.i(TAG, "All permissions granted")
            } else {
                // Permissions denied
                Toast.makeText(
                    this,
                    "Camera and microphone permissions are required for streaming",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
```

**Modify onCreate method - Replace line 71:**
```kotlin
// OLD:
setupWebViewAsync()

// NEW:
if (!hasRequiredPermissions()) {
    requestRequiredPermissions()
}
setupWebViewAsync()
```

---

### 4. Fix InputDevice Listener Implementation

**File:** `app/src/main/java/com/betterxcloud/android/managers/GamepadManager.kt`

**Add import:**
```kotlin
import android.hardware.input.InputManager
```

**Replace inputDeviceListener (lines 44-50):**
```kotlin
// OLD CODE - REMOVE:
private val inputDeviceListener = InputDevice.InputDeviceListener { deviceId, action ->
    when (action) {
        InputDevice.ACTION_REMOVED -> {
            onGamepadDisconnected(deviceId)
        }
    }
}

// NEW CODE - ADD:
private var inputManager: InputManager? = null

private val inputDeviceListener = object : InputManager.InputDeviceListener {
    override fun onInputDeviceAdded(deviceId: Int) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Gamepad connected: $deviceId")
        }
    }

    override fun onInputDeviceRemoved(deviceId: Int) {
        onGamepadDisconnected(deviceId)
    }

    override fun onInputDeviceChanged(deviceId: Int) {
        // Handle gamepad configuration changed
    }
}
```

**Replace init block (lines 52-57):**
```kotlin
init {
    // Register InputDevice listener to track gamepad connectivity
    context?.let {
        inputManager = it.getSystemService(Context.INPUT_SERVICE) as? InputManager
        inputManager?.registerInputDeviceListener(inputDeviceListener, null)
    }
}
```

**Update cleanup method (line 321):**
```kotlin
fun cleanup() {
    context?.let {
        try {
            inputManager?.unregisterInputDeviceListener(inputDeviceListener)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "Error unregistering input device listener", e)
            }
        }
    }
    gamepadState.clear()
    if (BuildConfig.DEBUG) {
        Log.d(TAG, "GamepadManager cleanup completed")
    }
}
```

---

## RECOMMENDED FIXES (Should Implement)

### 5. Create Network Security Config

**Create File:** `app/src/main/res/xml/network_security_config.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <!-- Base configuration for API 24+ -->
    <base-config cleartextTrafficPermitted="false">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>

    <!-- Allow cleartext traffic to xbox.com for xCloud streaming -->
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">xbox.com</domain>
        <domain includeSubdomains="true">xboxlive.com</domain>
    </domain-config>

    <!-- Debug configuration for localhost testing -->
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">localhost</domain>
        <domain includeSubdomains="true">127.0.0.1</domain>
        <domain includeSubdomains="true">10.0.2.2</domain>
    </domain-config>
</network-security-config>
```

**Update File:** `app/src/main/AndroidManifest.xml`

**Add to application tag (line 31):**
```xml
<application
    android:name=".XCloudApplication"
    android:networkSecurityConfig="@xml/network_security_config"
    android:allowBackup="true"
    ...>
```

---

### 6. Update build.gradle SDK Levels

**File:** `app/build.gradle`

**Replace lines 9-14:**
```gradle
android {
    namespace 'com.betterxcloud.android'
    compileSdk 33

    defaultConfig {
        applicationId "com.betterxcloud.android"
        minSdk 24      // Changed from 25 to support Android 7.0
        targetSdk 28   // Changed from 33 to optimize for Fire OS 6
        versionCode 1
        versionName "0.1.0-alpha"

        vectorDrawables.useSupportLibrary = true
    }
```

**Replace firestick flavor (lines 43-48):**
```gradle
firestick {
    dimension 'platform'
    applicationIdSuffix '.firestick'
    versionNameSuffix '-firestick'
    minSdk 24  // Changed from 21 to match Fire Stick 4K 1st Gen
}
```

---

## OPTIONAL FIXES (Nice to Have)

### 7. Migrate onBackPressed to OnBackPressedCallback

**File:** `app/src/main/java/com/betterxcloud/android/ui/XCloudWebViewActivity.kt`

**Add to onCreate method (after line 77):**
```kotlin
// Modern back press handling
onBackPressedDispatcher.addCallback(this, object : androidx.activity.OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
        // Issue #24 - Handle fullscreen mode
        if (isInFullscreen) {
            webView.evaluateJavascript(
                "if(document.fullscreenElement) document.exitFullscreen();",
                null
            )
            return
        }

        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            // Disable this callback and trigger system back
            isEnabled = false
            onBackPressedDispatcher.onBackPressed()
        }
    }
})
```

**Remove the old onBackPressed override (lines 535-548):**
```kotlin
// DELETE THIS METHOD:
override fun onBackPressed() {
    // ...
}
```

---

### 8. Handle setAppCacheEnabled Deprecation

**File:** `app/src/main/java/com/betterxcloud/android/ui/XCloudWebViewActivity.kt`
**Line:** 170

**Current Code:**
```kotlin
// Cache settings optimized for streaming
cacheMode = WebSettings.LOAD_DEFAULT
setAppCacheEnabled(true)
```

**Future-proof Code:**
```kotlin
// Cache settings optimized for streaming
cacheMode = WebSettings.LOAD_DEFAULT

// Application cache (deprecated in API 33 but functional in API 24-28)
if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
    @Suppress("DEPRECATION")
    setAppCacheEnabled(true)
}
```

---

### 9. Optimize VibrationEffect API Level

**File:** `app/src/main/java/com/betterxcloud/android/utils/XCloudJavaScriptInterface.kt`
**Line:** 86

**Current Code:**
```kotlin
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
```

**Optimized Code:**
```kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    // For Android O (API 26) and above, use VibrationEffect
    vibrator.vibrate(
        VibrationEffect.createOneShot(
            clampedDuration,
            VibrationEffect.DEFAULT_AMPLITUDE
        )
    )
} else {
    // For API 24-25, use deprecated vibrate method
    @Suppress("DEPRECATION")
    vibrator.vibrate(clampedDuration)
}
```

---

## Build and Test Commands

```bash
# Clean build
./gradlew clean

# Build debug APK for Fire Stick
./gradlew assembleFirestickDebug

# Build release APK for Fire Stick
./gradlew assembleFirestickRelease

# Install on connected Fire Stick
adb connect <fire-stick-ip>:5555
adb install -r app/build/outputs/apk/firestick/debug/app-firestick-debug.apk

# Check for API compatibility issues
./gradlew lintDebug

# View logs
adb logcat | grep -E "XCloud|WebView|Gamepad"
```

---

## Validation Checklist

After implementing fixes:

- [ ] App builds successfully with `./gradlew assembleFirestickRelease`
- [ ] No lint errors for API compatibility with `./gradlew lintDebug`
- [ ] safeBrowsingEnabled is wrapped in API check
- [ ] Runtime permissions are requested on first launch
- [ ] WRITE_EXTERNAL_STORAGE permission added to manifest
- [ ] InputDevice listener uses InputManager correctly
- [ ] Network security config created and referenced
- [ ] targetSdk changed to 28
- [ ] minSdk changed to 24
- [ ] Test on Android 7.0/7.1 emulator or Fire Stick

---

## Priority Order

Implement in this order:

1. **safeBrowsingEnabled fix** (30 minutes) - Prevents crash
2. **Runtime permissions** (1-2 hours) - Enables camera/mic
3. **WRITE_EXTERNAL_STORAGE** (5 minutes) - Enables downloads
4. **InputDevice listener fix** (30 minutes) - Fixes gamepad detection
5. **Network security config** (30 minutes) - Best practice
6. **build.gradle updates** (15 minutes) - Optimize for Fire Stick
7. **Optional fixes** (2-4 hours) - Future-proofing

**Total Critical Fixes Time:** ~3-4 hours
**Total All Fixes Time:** ~6-8 hours

---

## Testing on Fire Stick 4K

### Enable ADB on Fire Stick
1. Go to Settings > My Fire TV > Developer Options
2. Turn on "ADB Debugging"
3. Turn on "Apps from Unknown Sources"

### Connect and Install
```bash
# Find your Fire Stick IP in Settings > My Fire TV > About > Network

# Connect via ADB
adb connect 192.168.1.XXX:5555

# Verify connection
adb devices

# Install APK
adb install -r app/build/outputs/apk/firestick/release/app-firestick-release.apk

# Launch app
adb shell am start -n com.betterxcloud.android.firestick/.ui.MainActivity
```

### Monitor on Fire Stick
```bash
# View logs
adb logcat -s XCloudWebView:* XCloudApp:* GamepadManager:*

# Monitor memory
adb shell dumpsys meminfo com.betterxcloud.android.firestick

# Check app info
adb shell dumpsys package com.betterxcloud.android.firestick
```

---

**End of Quick Reference Guide**

For detailed explanations, see `ANDROID_7_COMPATIBILITY_REPORT.md`
