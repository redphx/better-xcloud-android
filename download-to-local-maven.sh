#!/bin/bash

# Download dependencies to local Maven repository structure

set -e

LOCAL_REPO="$PWD/local-maven-repo"
GOOGLE_MAVEN="https://dl.google.com/dl/android/maven2"
MAVEN_CENTRAL="https://repo.maven.apache.org/maven2"

echo "=== Downloading to Local Maven Repository ==="
echo "Location: $LOCAL_REPO"
echo ""

# Function to download artifact to local Maven repo
download_maven_artifact() {
    local repo=$1
    local group=$2
    local artifact=$3
    local version=$4
    local ext=${5:-jar}

    local group_path=$(echo $group | tr '.' '/')
    local url_base="${repo}/${group_path}/${artifact}/${version}"
    local local_base="${LOCAL_REPO}/${group_path}/${artifact}/${version}"

    mkdir -p "$local_base"

    echo "Downloading ${group}:${artifact}:${version}"

    # Download POM
    curl -f -L -o "${local_base}/${artifact}-${version}.pom" \
        "${url_base}/${artifact}-${version}.pom" 2>/dev/null || echo "  POM not found (optional)"

    # Download main artifact
    curl -f -L -o "${local_base}/${artifact}-${version}.${ext}" \
        "${url_base}/${artifact}-${version}.${ext}" 2>/dev/null || echo "  ${ext} not found"

    # Download sources if available
    curl -f -L -o "${local_base}/${artifact}-${version}-sources.jar" \
        "${url_base}/${artifact}-${version}-sources.jar" 2>/dev/null || true
}

echo "=== 1. Build Tools ==="
download_maven_artifact "$GOOGLE_MAVEN" "com.android.tools.build" "gradle" "8.2.2" "jar"
download_maven_artifact "$GOOGLE_MAVEN" "com.android.tools.build" "gradle-api" "8.2.2" "jar"
download_maven_artifact "$GOOGLE_MAVEN" "com.android.tools.build" "builder" "8.2.2" "jar"
download_maven_artifact "$GOOGLE_MAVEN" "com.android.tools.build" "builder-model" "8.2.2" "jar"

echo ""
echo "=== 2. Kotlin ==="
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-gradle-plugin" "2.0.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-stdlib" "2.0.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-stdlib-common" "2.0.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-stdlib-jdk8" "2.0.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-stdlib-jdk7" "2.0.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlin" "kotlin-reflect" "2.0.0" "jar"

echo ""
echo "=== 3. Kotlin Coroutines ==="
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlinx" "kotlinx-coroutines-core" "1.7.3" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlinx" "kotlinx-coroutines-android" "1.7.3" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "org.jetbrains.kotlinx" "kotlinx-coroutines-core-jvm" "1.7.3" "jar"

echo ""
echo "=== 4. AndroidX Core ==="
download_maven_artifact "$GOOGLE_MAVEN" "androidx.core" "core" "1.16.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.core" "core-ktx" "1.16.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.appcompat" "appcompat" "1.7.1" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.appcompat" "appcompat-resources" "1.7.1" "aar"

echo ""
echo "=== 5. Material Design ==="
download_maven_artifact "$GOOGLE_MAVEN" "com.google.android.material" "material" "1.12.0" "aar"

echo ""
echo "=== 6. ConstraintLayout ==="
download_maven_artifact "$GOOGLE_MAVEN" "androidx.constraintlayout" "constraintlayout" "2.2.1" "aar"

echo ""
echo "=== 7. Lifecycle ==="
download_maven_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-runtime" "2.7.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-runtime-ktx" "2.7.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-viewmodel" "2.7.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-viewmodel-ktx" "2.7.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-livedata" "2.7.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-livedata-ktx" "2.7.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.lifecycle" "lifecycle-common" "2.7.0" "jar"

echo ""
echo "=== 8. Activity & Fragment ==="
download_maven_artifact "$GOOGLE_MAVEN" "androidx.activity" "activity" "1.10.1" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.activity" "activity-ktx" "1.10.1" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.fragment" "fragment" "2.7.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.fragment" "fragment-ktx" "2.7.0" "aar"

echo ""
echo "=== 9. RecyclerView & CardView ==="
download_maven_artifact "$GOOGLE_MAVEN" "androidx.recyclerview" "recyclerview" "1.4.0" "aar"
download_maven_artifact "$GOOGLE_MAVEN" "androidx.cardview" "cardview" "1.0.0" "aar"

echo ""
echo "=== 10. Networking ==="
download_maven_artifact "$MAVEN_CENTRAL" "com.squareup.okhttp3" "okhttp" "4.12.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "com.squareup.okhttp3" "logging-interceptor" "4.12.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "com.squareup.okio" "okio" "3.6.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "com.squareup.okio" "okio-jvm" "3.6.0" "jar"

echo ""
echo "=== 11. JSON Processing ==="
download_maven_artifact "$MAVEN_CENTRAL" "com.squareup.moshi" "moshi" "1.15.0" "jar"
download_maven_artifact "$MAVEN_CENTRAL" "com.squareup.moshi" "moshi-kotlin" "1.15.0" "jar"

echo ""
echo "=== Complete! ==="
echo "Local Maven repository created at: $LOCAL_REPO"
echo ""
echo "Total size:"
du -sh "$LOCAL_REPO"
