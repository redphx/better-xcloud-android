#!/bin/bash

# Download AGP transitive dependencies

set -e

LOCAL_REPO="$PWD/local-maven-repo"
GOOGLE_MAVEN="https://dl.google.com/dl/android/maven2"
MAVEN_CENTRAL="https://repo.maven.apache.org/maven2"

echo "=== Downloading AGP Transitive Dependencies ==="

# Function to download artifact
download() {
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

    curl -f -L -o "${local_base}/${artifact}-${version}.pom" \
        "${url_base}/${artifact}-${version}.pom" 2>/dev/null || echo "  POM not found"

    curl -f -L -o "${local_base}/${artifact}-${version}.${ext}" \
        "${url_base}/${artifact}-${version}.${ext}" 2>/dev/null || echo "  ${ext} not found"
}

# AGP transitive dependencies
download "$GOOGLE_MAVEN" "com.android" "signflinger" "8.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools.analytics-library" "protos" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools.analytics-library" "tracker" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools" "annotations" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools" "common" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools.build" "aapt2-proto" "8.2.2-10154469" "jar"
download "$GOOGLE_MAVEN" "com.android.tools.layoutlib" "layoutlib-api" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools.ddms" "ddmlib" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools" "sdk-common" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools" "sdklib" "31.2.2" "jar"
download "$GOOGLE_MAVEN" "com.android.tools" "repository" "31.2.2" "jar"

# Apache Commons
download "$MAVEN_CENTRAL" "commons-codec" "commons-codec" "1.10" "jar"
download "$MAVEN_CENTRAL" "commons-io" "commons-io" "2.13.0" "jar"
download "$MAVEN_CENTRAL" "org.apache.commons" "commons-compress" "1.21" "jar"

# Bouncycastle
download "$MAVEN_CENTRAL" "org.bouncycastle" "bcprov-jdk15on" "1.67" "jar"
download "$MAVEN_CENTRAL" "org.bouncycastle" "bcpkix-jdk15on" "1.67" "jar"

# Google Guava
download "$MAVEN_CENTRAL" "com.google.guava" "guava" "31.1-jre" "jar"
download "$MAVEN_CENTRAL" "com.google.guava" "failureaccess" "1.0.1" "jar"

# Javax/Jakarta
download "$MAVEN_CENTRAL" "javax.inject" "javax.inject" "1" "jar"
download "$MAVEN_CENTRAL" "javax.annotation" "javax.annotation-api" "1.3.2" "jar"

# Google Protobuf
download "$MAVEN_CENTRAL" "com.google.protobuf" "protobuf-java" "3.19.6" "jar"
download "$MAVEN_CENTRAL" "com.google.protobuf" "protobuf-java-util" "3.19.6" "jar"

# JSON
download "$MAVEN_CENTRAL" "com.google.code.gson" "gson" "2.10.1" "jar"

# Jetbrains Annotations
download "$MAVEN_CENTRAL" "org.jetbrains" "annotations" "13.0" "jar"

# FastUtil
download "$MAVEN_CENTRAL" "it.unimi.dsi" "fastutil" "8.5.12" "jar"

echo ""
echo "=== AGP Dependencies Downloaded ==="
du -sh "$LOCAL_REPO"
