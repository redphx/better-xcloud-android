package com.redphx.betterxc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import androidx.webkit.WebViewFeature;
import com.redphx.betterxc.hook.Hooker;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebViewUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/WebViewUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebViewUtil {
    private static PackageInfo CURRENT_PROVIDER = null;
    private static PackageInfo CUSTOM_PROVIDER = null;
    private static PackageInfo DEFAULT_PROVIDER = null;
    private static final String LOG_TAG = "WebViewUtil";
    private static boolean initialized;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] ALLOWED_PROVIDERS = {"com.google.android.webview", "com.google.android.webview.beta", "com.google.android.webview.dev", "com.google.android.webview.canary", "com.android.webview", "com.android.chrome", "com.chrome.beta", "com.chrome.dev", "com.chrome.canary", "com.amazon.webview.chromium", "com.huawei.webview", "us.spotco.mulch_wv", "com.thorium.webview"};
    private static final Map<String, PackageInfo> AVAILABLE_PROVIDERS = new LinkedHashMap();

    /* compiled from: WebViewUtil.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u001f\u001a\u00020\u0007J\u000e\u0010 \u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010!\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u00108F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u001d\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001a¨\u0006#"}, d2 = {"Lcom/redphx/betterxc/util/WebViewUtil$Companion;", "", "<init>", "()V", "LOG_TAG", "", "initialized", "", "ALLOWED_PROVIDERS", "", "[Ljava/lang/String;", "DEFAULT_PROVIDER", "Landroid/content/pm/PackageInfo;", "CUSTOM_PROVIDER", "CURRENT_PROVIDER", "AVAILABLE_PROVIDERS", "", "init", "", "context", "Landroid/content/Context;", "availableProviders", "getAvailableProviders", "()Ljava/util/Map;", "defaultProvider", "getDefaultProvider", "()Landroid/content/pm/PackageInfo;", "customProvider", "getCustomProvider", "currentProvider", "getCurrentProvider", "isSupported", "reloadProviderList", "toString", "packageInfo", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void init(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (WebViewUtil.initialized) {
                return;
            }
            WebViewUtil.initialized = true;
            PackageInfo currentWebViewPackage = WebView.getCurrentWebViewPackage();
            Intrinsics.checkNotNull(currentWebViewPackage);
            WebViewUtil.DEFAULT_PROVIDER = currentWebViewPackage;
            reloadProviderList(context);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            PackageInfo packageInfo = WebViewUtil.DEFAULT_PROVIDER;
            PackageInfo packageInfo2 = null;
            if (packageInfo == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_PROVIDER");
                packageInfo = null;
            }
            String string = defaultSharedPreferences.getString("webview_implementation", packageInfo.packageName);
            if (Intrinsics.areEqual(string, "default")) {
                PackageInfo packageInfo3 = WebViewUtil.DEFAULT_PROVIDER;
                if (packageInfo3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_PROVIDER");
                    packageInfo3 = null;
                }
                string = packageInfo3.packageName;
            }
            PackageInfo packageInfo4 = WebViewUtil.DEFAULT_PROVIDER;
            if (packageInfo4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_PROVIDER");
                packageInfo4 = null;
            }
            if (Intrinsics.areEqual(string, packageInfo4.packageName) || !WebViewUtil.AVAILABLE_PROVIDERS.containsKey(string)) {
                PackageInfo packageInfo5 = WebViewUtil.DEFAULT_PROVIDER;
                if (packageInfo5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_PROVIDER");
                    packageInfo5 = null;
                }
                WebViewUtil.CURRENT_PROVIDER = packageInfo5;
            } else {
                Object obj = WebViewUtil.AVAILABLE_PROVIDERS.get(string);
                Intrinsics.checkNotNull(obj);
                WebViewUtil.CUSTOM_PROVIDER = (PackageInfo) obj;
                PackageInfo packageInfo6 = WebViewUtil.CUSTOM_PROVIDER;
                if (packageInfo6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("CUSTOM_PROVIDER");
                    packageInfo6 = null;
                }
                WebViewUtil.CURRENT_PROVIDER = packageInfo6;
                Hooker.INSTANCE.hookServiceManagerService();
            }
            StringBuilder sb = new StringBuilder("Selected WebView: ");
            PackageInfo packageInfo7 = WebViewUtil.CURRENT_PROVIDER;
            if (packageInfo7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("CURRENT_PROVIDER");
            } else {
                packageInfo2 = packageInfo7;
            }
            Log.i(WebViewUtil.LOG_TAG, sb.append(packageInfo2.packageName).toString());
        }

        public final Map<String, PackageInfo> getAvailableProviders() {
            return WebViewUtil.AVAILABLE_PROVIDERS;
        }

        public final PackageInfo getDefaultProvider() {
            PackageInfo packageInfo = WebViewUtil.DEFAULT_PROVIDER;
            if (packageInfo != null) {
                return packageInfo;
            }
            Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_PROVIDER");
            return null;
        }

        public final PackageInfo getCustomProvider() {
            PackageInfo packageInfo = WebViewUtil.CUSTOM_PROVIDER;
            if (packageInfo != null) {
                return packageInfo;
            }
            Intrinsics.throwUninitializedPropertyAccessException("CUSTOM_PROVIDER");
            return null;
        }

        public final PackageInfo getCurrentProvider() {
            PackageInfo packageInfo = WebViewUtil.CURRENT_PROVIDER;
            if (packageInfo != null) {
                return packageInfo;
            }
            Intrinsics.throwUninitializedPropertyAccessException("CURRENT_PROVIDER");
            return null;
        }

        public final boolean isSupported() {
            return WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT);
        }

        public final void reloadProviderList(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WebViewUtil.AVAILABLE_PROVIDERS.clear();
            PackageInfo packageInfo = WebViewUtil.DEFAULT_PROVIDER;
            if (packageInfo == null) {
                Intrinsics.throwUninitializedPropertyAccessException("DEFAULT_PROVIDER");
                packageInfo = null;
            }
            WebViewUtil.AVAILABLE_PROVIDERS.put(packageInfo.packageName, packageInfo);
            for (String str : WebViewUtil.ALLOWED_PROVIDERS) {
                try {
                    PackageInfo packageInfo2 = context.getPackageManager().getPackageInfo(str, 1216);
                    ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                    Intrinsics.checkNotNull(applicationInfo);
                    if (applicationInfo.enabled) {
                        ApplicationInfo applicationInfo2 = packageInfo2.applicationInfo;
                        Intrinsics.checkNotNull(applicationInfo2);
                        if (applicationInfo2.metaData.getString("com.android.webview.WebViewLibrary") != null) {
                            WebViewUtil.AVAILABLE_PROVIDERS.put(str, packageInfo2);
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.i(WebViewUtil.LOG_TAG, "Not found: " + e.getMessage());
                }
            }
        }

        public final String toString(Context context, PackageInfo packageInfo) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(packageInfo, "packageInfo");
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            Intrinsics.checkNotNull(applicationInfo);
            CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            Intrinsics.checkNotNullExpressionValue(applicationLabel, "getApplicationLabel(...)");
            return ((Object) applicationLabel) + ' ' + packageInfo.versionName;
        }
    }
}
