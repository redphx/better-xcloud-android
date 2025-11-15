# Better xCloud Android TV Port - Executive Summary

## Project Overview

This project aims to port the Better xCloud userscript to a native Android TV application, specifically targeting:
- **Android TV OS 7.x** (API Level 25, Nougat)
- **Fire Stick 4K (1st Generation)** with its constrained hardware

## Documentation Structure

### 1. [ANDROID_TV_REQUIREMENTS.md](./ANDROID_TV_REQUIREMENTS.md)
**Comprehensive technical requirements document covering all 8 focus areas:**
- Android TV Leanback Library components and usage
- WebView integration for xCloud web interface
- D-pad navigation implementation
- Performance optimization for Fire Stick 4K
- Video playback via WebRTC
- Userscript injection mechanisms
- Gamepad/controller support
- TV-specific UI design patterns

**Length:** ~1,500 lines of detailed documentation with code examples

### 2. [ARCHITECTURE_OVERVIEW.md](./ARCHITECTURE_OVERVIEW.md)
**High-level system architecture and design:**
- System architecture diagrams
- Component breakdown
- Data flow diagrams
- State management
- Memory management strategy
- Threading model
- Testing strategy
- Deployment planning

**Focus:** Overall design decisions and architectural patterns

### 3. [IMPLEMENTATION_GUIDE.md](./IMPLEMENTATION_GUIDE.md)
**Quick reference with ready-to-use code snippets:**
- Minimal working example
- Copy-paste code snippets
- Common patterns
- Troubleshooting guide
- Testing commands

**Focus:** Practical implementation and getting started quickly

## Key Technical Decisions

### 1. WebView as Primary Container
**Why:** Xbox Cloud Gaming is a web-based platform using WebRTC
- Native WebView hosts the xCloud web interface
- Userscript injection modifies the web UI
- No need to reverse-engineer streaming protocols

### 2. Leanback Library for TV UI
**Why:** Native Android TV experience
- BrowseFragment for game library
- GuidedStepFragment for setup flows
- DetailsFragment for game information
- Built-in D-pad navigation support

### 3. Hybrid Architecture
**Components:**
- **Native layer:** Leanback UI, gamepad handling, performance optimization
- **Web layer:** xCloud interface with Better xCloud modifications
- **Bridge:** JavaScript interface for two-way communication

### 4. Memory-First Optimization
**Constraints:** Fire Stick 4K has only 1.5GB RAM
- Single WebView instance
- Aggressive cache management
- RGB_565 bitmaps (16-bit vs 32-bit)
- onTrimMemory callbacks
- Target: <300MB during streaming

## Critical Challenges & Solutions

| Challenge | Solution | Priority |
|-----------|----------|----------|
| Limited RAM (1.5GB) | Aggressive memory management, single WebView | HIGH |
| D-pad navigation in WebView | Custom focus management + JavaScript injection | HIGH |
| Gamepad passthrough | Native capture → JavaScript events | HIGH |
| WebRTC compatibility | Chrome user agent, auto-grant permissions | MEDIUM |
| TV-optimized UI | Leanback components, 10-foot design | MEDIUM |
| Script injection timing | onPageFinished callback, wrapped IIFE | MEDIUM |

## Implementation Roadmap

### Phase 1: MVP (2-3 weeks)
- [ ] Basic project structure
- [ ] WebView with xCloud loading
- [ ] Simple Leanback launcher
- [ ] Basic script injection
- [ ] Test on Fire Stick 4K

**Deliverables:** Working app that loads xCloud

### Phase 2: Core Features (3-4 weeks)
- [ ] Full gamepad support
- [ ] D-pad navigation in WebView
- [ ] Better xCloud script integration
- [ ] Memory optimization
- [ ] Settings management

**Deliverables:** Feature-complete streaming app

### Phase 3: UI Polish (2-3 weeks)
- [ ] Complete Leanback UI
- [ ] Game library browsing
- [ ] Search functionality
- [ ] Guided setup flow
- [ ] Error handling

**Deliverables:** Polished TV experience

### Phase 4: Optimization (2 weeks)
- [ ] Performance profiling
- [ ] Memory optimization
- [ ] Input latency reduction
- [ ] Network optimization
- [ ] Fire Stick testing

**Deliverables:** Optimized for Fire Stick 4K

### Phase 5: Release (1-2 weeks)
- [ ] Documentation
- [ ] Testing across devices
- [ ] Release preparation
- [ ] App store submission (optional)

**Deliverables:** Production-ready release

**Total Estimated Time:** 10-14 weeks

## Technology Stack

### Languages
- **Kotlin** - Primary language for Android development
- **JavaScript** - For userscript and WebView injection
- **XML** - For layouts and resources

### Key Libraries
```gradle
// Android TV
androidx.leanback:leanback:1.0.0
androidx.leanback:leanback-preference:1.0.0

// AndroidX Core
androidx.core:core-ktx:1.8.0
androidx.appcompat:appcompat:1.4.2
androidx.constraintlayout:constraintlayout:2.1.4

// UI Components
androidx.recyclerview:recyclerview:1.2.1
androidx.cardview:cardview:1.0.0

// Optional: Image Loading
com.github.bumptech.glide:glide:4.13.2
```

### Minimum Requirements
- **compileSdk:** 33
- **minSdk:** 25 (Android 7.1.1)
- **targetSdk:** 33
- **Kotlin:** 1.9.22
- **Gradle:** 8.2.2

## Fire Stick 4K (1st Gen) Specifications

| Component | Specification | Implication |
|-----------|--------------|-------------|
| RAM | 1.5 GB | Aggressive memory management required |
| CPU | Quad-core 1.7 GHz | Adequate for WebView rendering |
| GPU | IMG GE8300 | Hardware acceleration essential |
| Storage | 8 GB | Minimal app size, cache management |
| Android | Fire OS 7 (based on Android 9) | API 25 compatibility important |

## Performance Targets

| Metric | Target | Critical |
|--------|--------|----------|
| App cold start | < 3 seconds | No |
| WebView init | < 2 seconds | No |
| Video playback | 60 fps @ 1080p | Yes |
| Memory (idle) | < 150 MB | Yes |
| Memory (streaming) | < 300 MB | Yes |
| Input latency | < 50ms | Yes |

## Essential Code Patterns

### 1. WebView Configuration
```kotlin
webView.settings.apply {
    javaScriptEnabled = true
    domStorageEnabled = true
    mediaPlaybackRequiresUserGesture = false
    userAgentString = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36..."
    setLayerType(View.LAYER_TYPE_HARDWARE, null)
}
```

### 2. Script Injection
```kotlin
webView.evaluateJavascript("""
    (function() {
        'use strict';
        ${loadScriptFromAssets("better-xcloud.user.js")}
    })();
""".trimIndent(), null)
```

### 3. Gamepad Input
```kotlin
override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
    return gamepadManager.handleKeyEvent(event) || super.onKeyDown(keyCode, event)
}
```

### 4. Memory Management
```kotlin
override fun onTrimMemory(level: Int) {
    super.onTrimMemory(level)
    if (level >= TRIM_MEMORY_RUNNING_LOW) {
        webView.clearCache(true)
        System.gc()
    }
}
```

## Development Workflow

### Setup
```bash
# Clone repository
git clone https://github.com/redphx/better-xcloud-android.git
cd better-xcloud-android

# Open in Android Studio
# File > Open > better-xcloud-android

# Build
./gradlew assembleDebug
```

### Testing on Fire Stick
```bash
# Enable ADB on Fire Stick
# Settings > My Fire TV > Developer Options > ADB Debugging

# Connect
adb connect <firestick-ip>:5555

# Install
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Launch
adb shell am start -n com.betterxcloud.android/.MainActivity

# Monitor logs
adb logcat | grep -E "(BetterXCloud|WebView|Gamepad)"
```

### Debugging
```bash
# Memory profiling
adb shell dumpsys meminfo com.betterxcloud.android

# Performance
adb shell dumpsys gfxinfo com.betterxcloud.android

# Enable WebView debugging (in debug builds)
WebView.setWebContentsDebuggingEnabled(true)
# Then connect Chrome DevTools to chrome://inspect
```

## Project File Structure

```
better-xcloud-android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/betterxcloud/android/
│   │   │   │   ├── XCloudApplication.kt
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── XCloudWebViewActivity.kt
│   │   │   │   ├── GamepadManager.kt
│   │   │   │   └── ...
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   ├── assets/
│   │   │   │   └── better-xcloud.user.js
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── ANDROID_TV_REQUIREMENTS.md
├── ARCHITECTURE_OVERVIEW.md
├── IMPLEMENTATION_GUIDE.md
└── SUMMARY.md (this file)
```

## Key Resources

### Official Documentation
- [Android TV Developer Guide](https://developer.android.com/training/tv)
- [Leanback Library](https://developer.android.com/reference/androidx/leanback/app/package-summary)
- [WebView Guide](https://developer.android.com/guide/webapps/webview)
- [Game Controllers](https://developer.android.com/training/game-controllers)
- [Fire TV Development](https://developer.amazon.com/docs/fire-tv/getting-started-developing-apps-and-games.html)

### Better xCloud
- [Better xCloud Homepage](https://better-xcloud.github.io)
- [Userscript Repository](https://github.com/redphx/better-xcloud)
- [Android App Repository](https://github.com/redphx/better-xcloud-android)

### Community
- [Telegram Channel](https://t.me/betterxcloud)
- [GitHub Issues](https://github.com/redphx/better-xcloud-android/issues)

## Risk Assessment

### High Risk
1. **Memory constraints** - Fire Stick has very limited RAM
   - *Mitigation:* Extensive profiling, aggressive cleanup, memory monitoring

2. **WebRTC compatibility** - Streaming protocol may have issues
   - *Mitigation:* Thorough testing, fallback options, proper user agent

### Medium Risk
3. **Gamepad compatibility** - Various controllers behave differently
   - *Mitigation:* Test with multiple controllers, use standard mappings

4. **Performance on older devices** - API 25 devices vary widely
   - *Mitigation:* Target Fire Stick specifically, set minimum requirements

### Low Risk
5. **Script injection timing** - Race conditions possible
   - *Mitigation:* Use onPageFinished callback, implement retries

6. **UI complexity** - Leanback has learning curve
   - *Mitigation:* Start simple, iterate based on feedback

## Success Criteria

### Must Have
- [ ] App launches on Fire Stick 4K
- [ ] xCloud web interface loads and functions
- [ ] Better xCloud script injects successfully
- [ ] Gamepad input works in games
- [ ] Memory usage stays under 350MB
- [ ] Stable streaming at 1080p/60fps

### Should Have
- [ ] Leanback browse UI with game library
- [ ] Settings screen
- [ ] D-pad navigation throughout app
- [ ] Search functionality
- [ ] Error handling and recovery

### Nice to Have
- [ ] Voice search support
- [ ] Recent games tracking
- [ ] Custom userscript support
- [ ] Multiple account support
- [ ] Performance statistics overlay

## Next Steps

1. **Review Documentation**
   - Read ANDROID_TV_REQUIREMENTS.md for detailed technical specs
   - Review ARCHITECTURE_OVERVIEW.md for design decisions
   - Use IMPLEMENTATION_GUIDE.md for code snippets

2. **Set Up Development Environment**
   - Install Android Studio Electric Eel or later
   - Configure Fire Stick for ADB debugging
   - Clone repository (when source is available)

3. **Build MVP**
   - Follow Phase 1 roadmap
   - Start with IMPLEMENTATION_GUIDE.md minimal example
   - Test early and often on Fire Stick

4. **Iterate**
   - Gather feedback from testing
   - Optimize based on profiling data
   - Add features incrementally

5. **Test & Release**
   - Comprehensive testing on target hardware
   - Performance optimization
   - Prepare for distribution

## Questions & Support

For technical questions or implementation help:
- **GitHub Issues:** https://github.com/redphx/better-xcloud-android/issues
- **Telegram:** https://t.me/betterxcloud
- **Email:** (check repository for contact info)

## Document Information

- **Created:** 2025-11-15
- **Version:** 1.0
- **Target Platform:** Android TV 7.x (API 25) / Fire Stick 4K (1st Gen)
- **Status:** Ready for implementation

---

## Quick Start Checklist

- [ ] Read all documentation files
- [ ] Set up Android Studio
- [ ] Configure Fire Stick for development
- [ ] Create project structure
- [ ] Implement minimal WebView example
- [ ] Test on Fire Stick
- [ ] Add script injection
- [ ] Implement gamepad support
- [ ] Build Leanback UI
- [ ] Optimize performance
- [ ] Prepare for release

Good luck with the implementation! This is a well-defined project with clear requirements and a solid architectural foundation.
