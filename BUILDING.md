# Building Better xCloud for Android TV

## Quick Start

```bash
# Clean build
./gradlew clean assembleDebug

# Install on connected Fire Stick
./gradlew installDebug

# Build release APK
./gradlew assembleRelease
```

## Build Requirements

- **Android Studio**: Electric Eel (2022.1.1) or newer
- **JDK**: 11 or later
- **Gradle**: 8.2 (included in wrapper)
- **Android SDK**:
  - compileSdk: 33
  - minSdk: 25
  - targetSdk: 33

## Project Structure

```
better-xcloud-android/
├── app/
│   ├── build.gradle                   # App module configuration
│   ├── proguard-rules.pro             # ProGuard configuration
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml    # App manifest
│           ├── assets/                # Better xCloud script
│           ├── java/                  # Kotlin source code
│           └── res/                   # Resources (layouts, drawables, etc.)
├── build.gradle                       # Root build configuration
├── gradle.properties                  # Gradle properties
└── settings.gradle                    # Project settings
```

## Build Types

### Debug Build
- **Package ID**: `com.betterxcloud.android.debug`
- **Signing**: Debug keystore
- **Obfuscation**: None
- **WebView Debugging**: Enabled
- **Logging**: Verbose

```bash
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk
```

### Release Build
- **Package ID**: `com.betterxcloud.android`
- **Signing**: Release keystore (required)
- **Obfuscation**: ProGuard/R8 enabled
- **WebView Debugging**: Disabled
- **Logging**: Minimal

```bash
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release-unsigned.apk
```

## Signing Configuration

### Generate Keystore

```bash
keytool -genkey -v -keystore better-xcloud.keystore \
  -alias better-xcloud \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000
```

### Configure Signing

Create `keystore.properties` in project root:

```properties
storeFile=/path/to/better-xcloud.keystore
storePassword=YourKeystorePassword
keyAlias=better-xcloud
keyPassword=YourKeyPassword
```

Add to `app/build.gradle`:

```gradle
def keystorePropertiesFile = rootProject.file("keystore.properties")
def keystoreProperties = new Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(new FileInputStream(keystorePropertiesFile))
}

android {
    signingConfigs {
        release {
            storeFile file(keystoreProperties['storeFile'])
            storePassword keystoreProperties['storePassword']
            keyAlias keystoreProperties['keyAlias']
            keyPassword keystoreProperties['keyPassword']
        }
    }

    buildTypes {
        release {
            signingConfig signingConfigs.release
        }
    }
}
```

## Gradle Tasks

```bash
# List all tasks
./gradlew tasks

# Clean build outputs
./gradlew clean

# Assemble debug APK
./gradlew assembleDebug

# Assemble release APK
./gradlew assembleRelease

# Install debug on connected device
./gradlew installDebug

# Uninstall from device
./gradlew uninstallDebug

# Run tests
./gradlew test

# Generate lint report
./gradlew lint
```

## Optimizing APK Size

The app is designed to be <10MB. Current optimizations:

1. **ProGuard/R8**: Removes unused code
2. **Resource Shrinking**: Removes unused resources
3. **Vector Drawables**: No raster images
4. **No Native Libraries**: Pure Kotlin/Java
5. **Minimal Dependencies**: Only essential libraries

Check APK size:
```bash
./gradlew assembleRelease
ls -lh app/build/outputs/apk/release/app-release.apk
```

Analyze APK contents:
```bash
# In Android Studio: Build > Analyze APK
# Or use bundletool:
java -jar bundletool.jar dump manifest --apk=app-release.apk
```

## Build Troubleshooting

### Issue: "SDK location not found"

**Solution:**
Create `local.properties` in project root:
```properties
sdk.dir=/path/to/Android/Sdk
```

### Issue: "Gradle sync failed"

**Solution:**
```bash
# Invalidate caches and restart Android Studio
# Or clean Gradle cache:
rm -rf ~/.gradle/caches
./gradlew clean build --refresh-dependencies
```

### Issue: "Build tools version not found"

**Solution:**
```bash
# Install required build tools via SDK Manager
sdkmanager "build-tools;33.0.0"
```

### Issue: "Duplicate class error"

**Solution:**
Check `app/build.gradle` for conflicting dependencies:
```bash
./gradlew app:dependencies
```

## Continuous Integration

### GitHub Actions Example

```yaml
name: Build APK

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3

    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'

    - name: Grant execute permission for gradlew
      run: chmod +x gradlew

    - name: Build with Gradle
      run: ./gradlew assembleDebug

    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

## Building for Different Architectures

The app supports all Android architectures (ARM, ARM64, x86, x86_64) since it's pure Kotlin/Java with no native libraries.

To create architecture-specific APKs (smaller size):

```gradle
android {
    splits {
        abi {
            enable true
            reset()
            include 'armeabi-v7a', 'arm64-v8a'
            universalApk true
        }
    }
}
```

## Build Performance

Speed up builds:

1. **Enable Gradle Daemon** (in `gradle.properties`):
   ```properties
   org.gradle.daemon=true
   org.gradle.parallel=true
   org.gradle.caching=true
   ```

2. **Increase Heap Size**:
   ```properties
   org.gradle.jvmargs=-Xmx2048m
   ```

3. **Use Build Cache**:
   ```bash
   ./gradlew build --build-cache
   ```

## Verifying Build

After building, verify the APK:

```bash
# Check APK validity
aapt dump badging app/build/outputs/apk/debug/app-debug.apk

# Check minimum SDK version
aapt dump badging app-debug.apk | grep sdkVersion

# Check permissions
aapt dump permissions app-debug.apk

# Verify signing (release)
jarsigner -verify -verbose -certs app-release.apk
```

## Next Steps

After building:
1. Test on Fire Stick 4K (see DEVELOPMENT.md)
2. Check memory usage and performance
3. Run lint checks: `./gradlew lint`
4. Generate release build for distribution

## Support

For build issues:
- Check [GitHub Issues](https://github.com/redphx/better-xcloud-android/issues)
- Join [Telegram](https://t.me/betterxcloud)
