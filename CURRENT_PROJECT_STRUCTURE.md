# Better xCloud Android - Current Project Structure Analysis
**Generated:** 2025-11-15
**Location:** /home/user/better-xcloud-android
**Purpose:** Complete inventory for comparison with official APK structure

---

## Project Overview

- **Project Name:** BetterXCloud (from settings.gradle)
- **Package Name:** com.betterxcloud.android
- **Application ID:** com.betterxcloud.android (with flavor suffixes)
- **Version:** 0.1.0-alpha (versionCode: 1)
- **Min SDK:** 25 (API 25 - Android 7.1) for standard, 21 for firestick
- **Target SDK:** 33 (API 33 - Android 13)
- **Compile SDK:** 33
- **Total Source Files:** 33 (excluding build artifacts and .git)
- **Total Project Size:** 7.7M
- **Source Directory Size:** 565K

---

## 1. Build Configuration Files

### 1.1 Root Level Build Files
```
/home/user/better-xcloud-android/build.gradle
/home/user/better-xcloud-android/settings.gradle
/home/user/better-xcloud-android/gradle.properties
/home/user/better-xcloud-android/gradle/wrapper/gradle-wrapper.properties
```

**build.gradle** (Root):
- Kotlin version: 1.9.22
- Android Gradle Plugin: 8.2.2
- Gradle: 8.2
- Repositories: google(), mavenCentral()

**settings.gradle**:
- Repository mode: FAIL_ON_PROJECT_REPOS
- Project name: "BetterXCloud"
- Modules: ':app'

**gradle.properties**:
- Heap: 2048m
- Parallel: true
- Caching: true
- AndroidX: enabled
- Jetifier: enabled

### 1.2 App Module Build Files
```
/home/user/better-xcloud-android/app/build.gradle
/home/user/better-xcloud-android/app/proguard-rules.pro
```

**app/build.gradle** Configuration:
- Plugins: com.android.application, kotlin.android, kotlin.kapt
- Namespace: com.betterxcloud.android
- Product Flavors:
  - standard (minSdk 25)
  - firestick (minSdk 21)
- Build Types: debug, release (with minify and shrink)
- ViewBinding: enabled
- BuildConfig: enabled
- Java/Kotlin: JVM target 11

**Key Dependencies:**
- Kotlin stdlib
- AndroidX Core KTX 1.8.0
- AppCompat 1.4.2
- ConstraintLayout 2.1.4
- Leanback 1.0.0 (Android TV)
- Leanback Preference 1.0.0
- Material 1.6.1
- Lifecycle Runtime/ViewModel KTX 2.5.1
- Coroutines 1.6.4
- Glide 4.13.2 (with kapt)
- Gson 2.10.1
- Preference KTX 1.2.0

---

## 2. Android Manifest Configuration

**File:** /home/user/better-xcloud-android/app/src/main/AndroidManifest.xml

### 2.1 Permissions
```xml
- INTERNET (required)
- ACCESS_NETWORK_STATE (required)
- MODIFY_AUDIO_SETTINGS (required)
- VIBRATE (required)
- RECORD_AUDIO (required)
- CAMERA (required)
- WAKE_LOCK (required)
```

### 2.2 Features
```xml
- android.software.leanback (required=true) - Android TV
- android.hardware.touchscreen (required=false)
- android.hardware.gamepad (required=false)
- android.hardware.microphone (required=false)
- android.hardware.camera (required=false)
```

### 2.3 Application Configuration
- Name: .XCloudApplication
- Icon: @mipmap/ic_launcher
- Banner: @drawable/app_banner (TV launcher)
- Label: @string/app_name
- Theme: @style/Theme.BetterXCloud
- Hardware Accelerated: true
- Large Heap: true
- Supports RTL: true
- Allow Backup: true

### 2.4 Activities
1. **MainActivity** (.ui.MainActivity)
   - Exported: true
   - Theme: Theme.Leanback.Browse
   - Screen Orientation: landscape
   - Intent Filters: LEANBACK_LAUNCHER, LAUNCHER

2. **XCloudWebViewActivity** (.ui.XCloudWebViewActivity)
   - Exported: false
   - Theme: Theme.BetterXCloud.Fullscreen
   - Screen Orientation: landscape
   - Launch Mode: singleTask
   - Config Changes: orientation|screenSize|keyboardHidden|keyboard

3. **SettingsActivity** (.ui.SettingsActivity)
   - Exported: false
   - Theme: Theme.Leanback.Settings
   - Label: @string/settings

---

## 3. Source Code Structure

### 3.1 Package: com.betterxcloud.android (root)
**Location:** /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/

#### XCloudApplication.kt (150 lines)
- **Extends:** Application
- **Purpose:** Application class for global lifecycle and memory management
- **Key Features:**
  - Global crash handler
  - WebView debugging in debug mode
  - Activity lifecycle callbacks
  - Memory cleanup on low memory events
  - Singleton instance pattern
  - WebView reference management

### 3.2 Package: com.betterxcloud.android.managers
**Location:** /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/managers/

#### GamepadManager.kt (354 lines)
- **Purpose:** Manages gamepad input and forwards to WebView
- **Key Features:**
  - Button and analog stick handling
  - Deadzone filtering (15%)
  - JavaScript injection for gamepad state
  - InputDevice listener for connectivity
  - Support for Xbox-style controllers
  - D-pad and trigger support

### 3.3 Package: com.betterxcloud.android.ui
**Location:** /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/ui/

#### MainActivity.kt (198 lines)
- **Classes:** MainActivity (FragmentActivity), MainFragment, MenuItem (data class), CardPresenter
- **Purpose:** TV launcher with Leanback browse interface
- **Key Features:**
  - Browse fragment with row-based layout
  - Quick Launch row with xCloud launch button
  - Settings row with settings and about buttons
  - Image card presenter for menu items
  - About dialog with version info

#### SettingsActivity.kt (66 lines)
- **Classes:** SettingsActivity, SettingsFragment, PrefsFragment
- **Purpose:** Settings UI using Leanback preferences
- **Key Features:**
  - Leanback settings framework
  - Preference screen from XML
  - Fragment-based navigation

#### XCloudWebViewActivity.kt (561 lines)
- **Extends:** AppCompatActivity
- **Purpose:** Main WebView activity for xCloud streaming
- **Key Features:**
  - WebView setup with JavaScript enabled
  - Better xCloud script injection
  - Gamepad input integration
  - Network connectivity checks
  - Memory monitoring (30s intervals)
  - Loading timeout (30s)
  - SSL error handling
  - Content-type validation
  - Download manager integration
  - WebRTC permission handling
  - State restoration
  - Fullscreen support
  - Custom User-Agent (Chrome 131.0.0.0)

### 3.4 Package: com.betterxcloud.android.utils
**Location:** /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/utils/

#### Logger.kt (278 lines)
- **Type:** Utility class (companion object)
- **Purpose:** Centralized logging utility
- **Key Features:**
  - Multiple log levels (VERBOSE, DEBUG, INFO, WARN, ERROR)
  - File logging support (placeholder)
  - Method entry/exit logging
  - Performance timing logs
  - Memory info logging
  - Extension functions for easy logging

#### NetworkMonitor.kt (207 lines)
- **Purpose:** Network connectivity monitoring
- **Key Features:**
  - Real-time network state tracking
  - Network type detection (WiFi, Ethernet, Cellular, Bluetooth)
  - Metered network detection
  - NetworkCallback for API 24+
  - Fallback for older APIs

#### PreferencesHelper.kt (247 lines)
- **Purpose:** SharedPreferences abstraction
- **Key Features:**
  - Type-safe preference methods
  - Error handling
  - Singleton pattern
  - Predefined preference keys (Xbox account, stream settings, etc.)
  - Support for String, Boolean, Int, Long, Float, StringSet

#### XCloudJavaScriptInterface.kt (141 lines)
- **Purpose:** JavaScript-to-native bridge for WebView
- **Key Features:**
  - Toast messages from JS
  - Logging from JS to logcat
  - Device info exposure
  - Streaming lifecycle callbacks
  - Vibration control (10-500ms)
  - App version exposure
  - Android TV detection
  - Performance metrics reporting

---

## 4. Resource Files

### 4.1 Layouts
**Location:** /home/user/better-xcloud-android/app/src/main/res/layout/

1. **activity_main.xml** (6 lines)
   - Single FrameLayout container
   - ID: main_frame
   - Full screen

2. **activity_xcloud_webview.xml** (31 lines)
   - ConstraintLayout root
   - WebView (full screen)
   - ProgressBar (horizontal, 4dp, Xbox green)

### 4.2 Drawables
**Location:** /home/user/better-xcloud-android/app/src/main/res/drawable/

1. **app_banner.xml** - TV app banner (320x180dp, Xbox green with rounded corners)
2. **ic_info.xml** - Info icon (24dp, gray)
3. **ic_settings.xml** - Settings gear icon (24dp, gray)
4. **ic_xbox.xml** - Xbox icon (24dp, Xbox green)

### 4.3 Mipmaps
**Location:** /home/user/better-xcloud-android/app/src/main/res/mipmap-hdpi/

1. **ic_launcher.xml** - Adaptive icon (Xbox green background + Xbox icon foreground)

**ISSUE:** Only hdpi density provided. Missing: mdpi, xhdpi, xxhdpi, xxxhdpi

### 4.4 Values
**Location:** /home/user/better-xcloud-android/app/src/main/res/values/

#### arrays.xml
- Stream quality options (720p, 1080p, 1440p, 4K)
- Cache size options (25MB, 50MB, 100MB, 200MB)

#### colors.xml
- Xbox brand colors (green, dark, light)
- Basic colors (black, white, grays)
- UI colors (background_dark, background_card, text)
- Status colors (success, error, warning, info)

#### strings.xml (95 lines)
**Categories:**
- App metadata
- Menu items
- Settings categories and preferences
- Error messages (9 different errors)
- Dialogs (OK, Cancel, Clear cache, Loading, etc.)
- Game library strings
- Buttons and actions
- Status messages
- Connectivity strings
- Performance metrics

**MISSING STRINGS** (referenced in MainActivity.kt):
- quick_launch
- start_xbox_cloud_gaming
- configure_better_xcloud
- version_and_information
- about_title
- about_message
- button_ok

#### styles.xml
1. **Theme.BetterXCloud** (Base theme)
   - Parent: Theme.AppCompat
   - Xbox green color scheme
   - Dark background
   - Custom text colors

2. **Theme.BetterXCloud.Fullscreen**
   - Full screen mode
   - No action bar
   - Black background

### 4.5 XML Preferences
**Location:** /home/user/better-xcloud-android/app/src/main/res/xml/

**preferences.xml** (119 lines)
**Categories:**
1. Stream Settings
   - Stream quality (ListPreference)
   - Show stats (SwitchPreference)
   - Enable HDR (SwitchPreference)
   - Enable 5G (SwitchPreference)

2. Input Settings
   - Gamepad vibration (SwitchPreference)
   - Gamepad deadzone (SeekBarPreference, 0-30, default 15)
   - Button remapping (SwitchPreference)
   - Reverse Y axis (SwitchPreference)

3. Advanced Settings
   - Hardware acceleration (SwitchPreference)
   - Cache size (ListPreference)
   - Clear cache (Preference)
   - Debug mode (SwitchPreference)
   - Keep screen on (SwitchPreference)
   - Disable idle timeout (SwitchPreference)

4. About
   - App version (Preference, hardcoded "1.0.0")
   - Check for updates (Preference)

---

## 5. Assets

**Location:** /home/user/better-xcloud-android/app/src/main/assets/

### better-xcloud.user.js
- **Size:** 462KB (323 lines - minified)
- **Version:** 6.7.4
- **Author:** redphx
- **License:** MIT
- **Purpose:** Enhanced Xbox Cloud Gaming userscript
- **Repository:** https://github.com/redphx/better-xcloud
- **Key Features:**
  - BxLogger for debugging
  - User agent spoofing
  - Stream optimization
  - Gamepad customization
  - Touch control layouts
  - Performance monitoring
  - Feature flags and preferences

---

## 6. Directory Tree Structure

```
better-xcloud-android/
├── .git/                           # Git repository
├── .github/                        # GitHub configuration
│   ├── ISSUE_TEMPLATE/
│   └── workflows/
├── .gradle/                        # Gradle cache (gitignored)
├── app/
│   ├── build.gradle               # App module build config
│   ├── proguard-rules.pro         # ProGuard configuration
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── assets/
│           │   └── better-xcloud.user.js
│           ├── java/com/betterxcloud/android/
│           │   ├── XCloudApplication.kt
│           │   ├── managers/
│           │   │   └── GamepadManager.kt
│           │   ├── ui/
│           │   │   ├── MainActivity.kt
│           │   │   ├── SettingsActivity.kt
│           │   │   └── XCloudWebViewActivity.kt
│           │   └── utils/
│           │       ├── Logger.kt
│           │       ├── NetworkMonitor.kt
│           │       ├── PreferencesHelper.kt
│           │       └── XCloudJavaScriptInterface.kt
│           └── res/
│               ├── drawable/
│               │   ├── app_banner.xml
│               │   ├── ic_info.xml
│               │   ├── ic_settings.xml
│               │   └── ic_xbox.xml
│               ├── layout/
│               │   ├── activity_main.xml
│               │   └── activity_xcloud_webview.xml
│               ├── mipmap-hdpi/
│               │   └── ic_launcher.xml
│               ├── values/
│               │   ├── arrays.xml
│               │   ├── colors.xml
│               │   ├── strings.xml
│               │   └── styles.xml
│               └── xml/
│                   └── preferences.xml
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
├── build.gradle                   # Root build config
├── gradle.properties              # Gradle properties
├── settings.gradle                # Project settings
├── .gitignore                     # Git ignore rules
├── LICENSE                        # License file
├── README.md                      # Project readme
├── ANDROID_TV_REQUIREMENTS.md     # Documentation
├── ARCHITECTURE_OVERVIEW.md       # Documentation
├── BUILDING.md                    # Documentation
├── DEVELOPMENT.md                 # Documentation
├── IMPLEMENTATION_GUIDE.md        # Documentation
├── SUMMARY.md                     # Documentation
└── VALIDATION_REPORT.md           # Documentation
```

---

## 7. Code Metrics

### 7.1 Kotlin Source Files by Size
```
XCloudWebViewActivity.kt    561 lines
GamepadManager.kt           354 lines
Logger.kt                   278 lines
PreferencesHelper.kt        247 lines
NetworkMonitor.kt           207 lines
MainActivity.kt             198 lines
XCloudApplication.kt        150 lines
XCloudJavaScriptInterface.kt 141 lines
SettingsActivity.kt         66 lines
-------------------------
Total:                      2,202 lines
```

### 7.2 Resource Files by Size
```
preferences.xml             119 lines
strings.xml                 95 lines
activity_xcloud_webview.xml 31 lines
colors.xml                  27 lines
styles.xml                  23 lines
arrays.xml                  34 lines
activity_main.xml           6 lines
```

---

## 8. Identified Issues and Gaps

### 8.1 Missing String Resources
The following strings are referenced in MainActivity.kt but missing from strings.xml:
- `quick_launch`
- `start_xbox_cloud_gaming`
- `configure_better_xcloud`
- `version_and_information`
- `about_title`
- `about_message`
- `button_ok`

### 8.2 Missing Icon Densities
Only mipmap-hdpi is provided. Missing:
- mipmap-mdpi/
- mipmap-xhdpi/
- mipmap-xxhdpi/
- mipmap-xxxhdpi/

### 8.3 Hardcoded Version
In preferences.xml line 110, app version is hardcoded as "1.0.0" instead of using BuildConfig.VERSION_NAME

### 8.4 Missing Gradle Wrapper JAR
The gradle/wrapper/ directory is missing gradle-wrapper.jar (typically gitignored)

### 8.5 No Test Source Sets
Missing:
- app/src/test/ (unit tests)
- app/src/androidTest/ (instrumented tests)

### 8.6 No Alternative Resources
Missing alternative resource qualifiers:
- res/values-night/ (dark theme)
- res/layout-land/ (landscape layouts, though orientation is locked)
- res/values-{locale}/ (translated strings)

---

## 9. Comparison Readiness

### 9.1 What We Have
✅ Complete source code structure
✅ All Kotlin classes documented
✅ All resources cataloged
✅ Build configuration documented
✅ Manifest structure analyzed
✅ Asset files identified
✅ Dependencies listed
✅ Package structure mapped

### 9.2 Ready for APK Comparison
This structure can be compared against an official APK by:
1. Decompiling APK with apktool
2. Comparing AndroidManifest.xml permissions, activities, features
3. Comparing resource files (layouts, drawables, values)
4. Comparing package structure
5. Analyzing DEX files for class structure
6. Comparing asset files
7. Checking for missing/extra files
8. Validating naming conventions
9. Checking ProGuard/R8 obfuscation differences

### 9.3 Key Comparison Points
- **Package naming:** com.betterxcloud.android vs official
- **Resource structure:** Custom vs standard Android TV patterns
- **Activity declarations:** TV leanback vs standard mobile
- **Permissions:** Streaming-specific vs general
- **Dependencies:** Custom Better xCloud integration vs vanilla
- **Build flavors:** standard/firestick vs official variants
- **Minimum SDK:** API 21/25 vs official requirements
- **Target SDK:** API 33 vs official (likely newer)

---

## 10. Summary

This is a **custom-built Android TV application** for Xbox Cloud Gaming that:

1. **Wraps xCloud in a WebView** with the Better xCloud userscript injected
2. **Targets Fire TV/Android TV** with Leanback UI components
3. **Supports gamepad input** via custom GamepadManager
4. **Includes streaming optimizations** for low-end devices
5. **Uses WebView as the rendering engine** instead of native streaming protocols
6. **Implements custom JavaScript bridge** for enhanced functionality
7. **Supports multiple flavors** (standard and firestick)

**This is NOT the official Xbox app** but rather a community-built solution that enhances the web-based xCloud experience for Android TV devices.

---

## 11. File Inventory (Complete List)

### Build Files (4)
1. /home/user/better-xcloud-android/build.gradle
2. /home/user/better-xcloud-android/settings.gradle
3. /home/user/better-xcloud-android/gradle.properties
4. /home/user/better-xcloud-android/app/build.gradle

### Configuration Files (3)
5. /home/user/better-xcloud-android/app/proguard-rules.pro
6. /home/user/better-xcloud-android/gradle/wrapper/gradle-wrapper.properties
7. /home/user/better-xcloud-android/.gitignore

### Manifest (1)
8. /home/user/better-xcloud-android/app/src/main/AndroidManifest.xml

### Kotlin Source Files (9)
9. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/XCloudApplication.kt
10. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/managers/GamepadManager.kt
11. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/ui/MainActivity.kt
12. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/ui/SettingsActivity.kt
13. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/ui/XCloudWebViewActivity.kt
14. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/utils/Logger.kt
15. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/utils/NetworkMonitor.kt
16. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/utils/PreferencesHelper.kt
17. /home/user/better-xcloud-android/app/src/main/java/com/betterxcloud/android/utils/XCloudJavaScriptInterface.kt

### Resource Files - Layout (2)
18. /home/user/better-xcloud-android/app/src/main/res/layout/activity_main.xml
19. /home/user/better-xcloud-android/app/src/main/res/layout/activity_xcloud_webview.xml

### Resource Files - Drawable (4)
20. /home/user/better-xcloud-android/app/src/main/res/drawable/app_banner.xml
21. /home/user/better-xcloud-android/app/src/main/res/drawable/ic_info.xml
22. /home/user/better-xcloud-android/app/src/main/res/drawable/ic_settings.xml
23. /home/user/better-xcloud-android/app/src/main/res/drawable/ic_xbox.xml

### Resource Files - Mipmap (1)
24. /home/user/better-xcloud-android/app/src/main/res/mipmap-hdpi/ic_launcher.xml

### Resource Files - Values (4)
25. /home/user/better-xcloud-android/app/src/main/res/values/arrays.xml
26. /home/user/better-xcloud-android/app/src/main/res/values/colors.xml
27. /home/user/better-xcloud-android/app/src/main/res/values/strings.xml
28. /home/user/better-xcloud-android/app/src/main/res/values/styles.xml

### Resource Files - XML (1)
29. /home/user/better-xcloud-android/app/src/main/res/xml/preferences.xml

### Assets (1)
30. /home/user/better-xcloud-android/app/src/main/assets/better-xcloud.user.js

### Documentation (7)
31. /home/user/better-xcloud-android/README.md
32. /home/user/better-xcloud-android/LICENSE
33. /home/user/better-xcloud-android/ANDROID_TV_REQUIREMENTS.md
34. /home/user/better-xcloud-android/ARCHITECTURE_OVERVIEW.md
35. /home/user/better-xcloud-android/BUILDING.md
36. /home/user/better-xcloud-android/DEVELOPMENT.md
37. /home/user/better-xcloud-android/IMPLEMENTATION_GUIDE.md
38. /home/user/better-xcloud-android/SUMMARY.md
39. /home/user/better-xcloud-android/VALIDATION_REPORT.md

**Total Tracked Files:** 39

---

## End of Report

This comprehensive analysis provides a complete baseline for comparing the current project structure against the official Xbox Game Pass APK structure when it becomes available.
