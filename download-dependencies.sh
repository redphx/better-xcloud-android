#!/bin/bash

# Better xCloud Android - Dependency Download Script
# Downloads all Maven dependencies using curl to populate Gradle cache

set -e

GOOGLE_MAVEN="https://dl.google.com/dl/android/maven2"
MAVEN_CENTRAL="https://repo.maven.apache.org/maven2"

echo "=== Downloading Maven Dependencies with curl ==="
echo "This will pre-populate the Gradle cache..."
echo ""

# Function to download artifact
download_artifact() {
    local repo=$1
    local group=$2
    local artifact=$3
    local version=$4
    local ext=${5:-jar}

    local group_path=$(echo $group | tr '.' '/')
    local url="${repo}/${group_path}/${artifact}/${version}/${artifact}-${version}.${ext}"
    local pom_url="${repo}/${group_path}/${artifact}/${version}/${artifact}-${version}.pom"

    local cache_dir="${HOME}/.gradle/caches/modules-2/files-2.1/${group}/${artifact}/${version}"
    mkdir -p "$cache_dir"

    echo "Downloading ${group}:${artifact}:${version}"

    # Download POM
    if [ ! -f "${cache_dir}/${artifact}-${version}.pom" ]; then
        curl -f -L -o "${cache_dir}/${artifact}-${version}.pom" "$pom_url" 2>/dev/null || echo "  POM not found (optional)"
    fi

    # Download artifact
    if [ ! -f "${cache_dir}/${artifact}-${version}.${ext}" ]; then
        curl -f -L -o "${cache_dir}/${artifact}-${version}.${ext}" "$url" 2>/dev/null || echo "  ${ext} not found"
    fi
}

echo "=== 1. Android Gradle Plugin & Kotlin ==="
download_artifact "$GOOGLE_MAVEN" "com.android.tools.build" "gradle" "8.2.2" "jar"
download_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-gradle-plugin" "2.0.0" "jar"
download_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-stdlib" "2.0.0" "jar"
download_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-compiler-embeddable" "2.0.0" "jar"

echo ""
echo "=== 2. Kotlin Coroutines ==="
download_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlinx" "kotlinx-coroutines-android" "1.7.3" "jar"
download_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlinx" "kotlinx-coroutines-core" "1.7.3" "jar"

echo ""
echo "=== 3. AndroidX Core Libraries ==="
download_artifact "$GOOGLE_MAVEN" "androidx.core" "core-ktx" "1.16.0" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.core" "core" "1.16.0" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.appcompat" "appcompat" "1.7.1" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.appcompat" "appcompat-resources" "1.7.1" "aar"

echo ""
echo "=== 4. UI Components ==="
download_artifact "$GOOGLE_MAVEN" "androidx.constraintlayout" "constraintlayout" "2.2.1" "aar"
download_artifact "$GOOGLE_MAVEN" "com.google.android.material" "material" "1.12.0" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.recyclerview" "recyclerview" "1.4.0" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.cardview" "cardview" "1.0.0" "aar"

echo ""
echo "=== 5. Lifecycle Components ==="
download_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-runtime-ktx" "2.7.0" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-viewmodel-ktx" "2.7.0" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-livedata-ktx" "2.7.0" "aar"

echo ""
echo "=== 6. Activity & Fragment ==="
download_artifact "$GOOGLE_MAVEN" "androidx.activity" "activity-ktx" "1.10.1" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.activity" "activity" "1.10.1" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.fragment" "fragment-ktx" "2.7.0" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.fragment" "fragment" "2.7.0" "aar"

echo ""
echo "=== 7. Room Database ==="
download_artifact "$GOOGLE_MAVEN" "androidx.room" "room-runtime" "2.6.1" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.room" "room-ktx" "2.6.1" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.room" "room-compiler" "2.6.1" "jar"

echo ""
echo "=== 8. WorkManager ==="
download_artifact "$GOOGLE_MAVEN" "androidx.work" "work-runtime-ktx" "2.10.1" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.work" "work-runtime" "2.10.1" "aar"

echo ""
echo "=== 9. WebView ==="
download_artifact "$GOOGLE_MAVEN" "androidx.webkit" "webkit" "1.14.0" "aar"

echo ""
echo "=== 10. Networking - OkHttp ==="
download_artifact "$MAVEN_CENTRAL" "com.squareup.okhttp3" "okhttp" "4.12.0" "jar"
download_artifact "$MAVEN_CENTRAL" "com.squareup.okhttp3" "logging-interceptor" "4.12.0" "jar"
download_artifact "$MAVEN_CENTRAL" "com.squareup.okio" "okio" "3.6.0" "jar"

echo ""
echo "=== 11. JSON - Moshi ==="
download_artifact "$MAVEN_CENTRAL" "com.squareup.moshi" "moshi" "1.15.0" "jar"
download_artifact "$MAVEN_CENTRAL" "com.squareup.moshi" "moshi-kotlin" "1.15.0" "jar"
download_artifact "$MAVEN_CENTRAL" "com.squareup.moshi" "moshi-kotlin-codegen" "1.15.0" "jar"

echo ""
echo "=== 12. Image Loading - Coil ==="
download_artifact "$MAVEN_CENTRAL" "io.coil-kt" "coil" "2.7.0" "aar"
download_artifact "$MAVEN_CENTRAL" "io.coil-kt" "coil-base" "2.7.0" "aar"

echo ""
echo "=== 13. Preferences ==="
download_artifact "$GOOGLE_MAVEN" "androidx.preference" "preference-ktx" "1.2.1" "aar"
download_artifact "$GOOGLE_MAVEN" "androidx.preference" "preference" "1.2.1" "aar"

echo ""
echo "=== Download Complete! ==="
echo "Gradle cache populated at: ~/.gradle/caches/"
echo "Now try: gradle clean assembleDebug"
