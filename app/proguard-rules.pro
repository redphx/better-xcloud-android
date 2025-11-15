# Better xCloud Android - ProGuard Rules
# Target: Fire Stick 4K (1st Gen), Android TV OS 7.x (API 25)

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

# Keep all WebView-related classes
-keep class android.webkit.** { *; }
-keep class androidx.webkit.** { *; }

# Keep Kotlin metadata
-keepattributes *Annotation*
-keep class kotlin.Metadata { *; }

# Preserve line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable

# Keep custom classes that may be used via reflection
-keep class com.betterxcloud.android.** { *; }

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Parcelables
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Keep Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep view constructors for layout inflation
-keepclassmembers class * extends android.view.View {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Keep AndroidX classes
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Optimization flags
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-dontoptimize
-dontpreverify
-verbose

# Keep generic signatures and line numbers for better debugging
-keepattributes Signature,LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile
