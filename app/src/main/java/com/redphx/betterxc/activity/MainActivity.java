package com.redphx.betterxc.activity;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.webkit.WebViewCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.redphx.betterxc.MainApplication;
import com.redphx.betterxc.R;
import com.redphx.betterxc.activity.BaseActivity;
import com.redphx.betterxc.data.BxAndroidInfo;
import com.redphx.betterxc.data.BxDeviceInfo;
import com.redphx.betterxc.data.BxFlags;
import com.redphx.betterxc.dialog.WebViewManagerDialog;
import com.redphx.betterxc.receiver.BatteryReceiver;
import com.redphx.betterxc.task.CheckUpdateTask;
import com.redphx.betterxc.task.UpdateScriptTask;
import com.redphx.betterxc.util.BatteryStatus;
import com.redphx.betterxc.util.BatteryUtil;
import com.redphx.betterxc.util.Constants;
import com.redphx.betterxc.util.CrashTracker;
import com.redphx.betterxc.util.DeepLinkData;
import com.redphx.betterxc.util.DeepLinkHost;
import com.redphx.betterxc.util.DeepLinkUtil;
import com.redphx.betterxc.util.DeviceInfo;
import com.redphx.betterxc.util.DialogUtil;
import com.redphx.betterxc.util.StorageUtil;
import com.redphx.betterxc.util.UserscriptUtil;
import com.redphx.betterxc.util.Utils;
import com.redphx.betterxc.util.WebViewUtil;
import com.redphx.betterxc.web.WebAppInterface;
import com.redphx.betterxc.web.WebViewBottomSheet;
import com.redphx.betterxc.web.XcloudWebView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u0000 g2\u00020\u0001:\u0003efgB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u00109\u001a\u00020:J\b\u0010;\u001a\u00020:H\u0002J\u0017\u0010<\u001a\u00020:2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010>J-\u0010?\u001a\u00020:2\u0006\u0010@\u001a\u00020\u00182\u000e\u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0B2\u0006\u0010C\u001a\u00020DH\u0016¢\u0006\u0002\u0010EJ\b\u0010F\u001a\u00020:H\u0002J\u0012\u0010G\u001a\u00020:2\b\u0010H\u001a\u0004\u0018\u00010IH\u0015J\u0010\u0010J\u001a\u00020:2\u0006\u0010K\u001a\u00020LH\u0014J\u0010\u0010M\u001a\u00020:2\u0006\u0010N\u001a\u00020OH\u0016J\u001a\u0010P\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00182\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\u001a\u0010T\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00182\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\b\u0010U\u001a\u00020:H\u0015J\b\u0010V\u001a\u00020:H\u0014J\b\u0010W\u001a\u00020:H\u0014J\b\u0010X\u001a\u00020:H\u0002J\u0006\u0010Y\u001a\u00020:J\b\u0010Z\u001a\u00020:H\u0002J\b\u0010[\u001a\u00020:H\u0003J\b\u0010\\\u001a\u00020:H\u0002J\b\u0010]\u001a\u00020:H\u0002J\"\u0010^\u001a\u00020:2\u0006\u0010_\u001a\u00020\u00052\b\u0010`\u001a\u0004\u0018\u00010\n2\b\u0010a\u001a\u0004\u0018\u00010\nJ\u0006\u0010b\u001a\u00020:J\b\u0010c\u001a\u00020:H\u0002J\b\u0010d\u001a\u00020:H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010/\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u000e\u00105\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082.¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Lcom/redphx/betterxc/activity/MainActivity;", "Lcom/redphx/betterxc/activity/BaseActivity;", "<init>", "()V", "isInitialized", "", "audioManager", "Landroid/media/AudioManager;", "storageRequestResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "sharedPref", "Landroid/content/SharedPreferences;", "laySwipeRefresh", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "layWebView", "Landroid/widget/FrameLayout;", "webView", "Lcom/redphx/betterxc/web/XcloudWebView;", "webAppInterface", "Lcom/redphx/betterxc/web/WebAppInterface;", "webLocale", "webViewUrl", "webViewBaseScale", "", "webViewZoomPercent", "bottomSheetWebView", "Lcom/redphx/betterxc/web/WebViewBottomSheet;", "layFabContainer", "Landroid/widget/LinearLayout;", "fabSettings", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "viewLowBatteryIndicator", "Landroid/view/View;", "batteryReceiver", "Landroid/content/BroadcastReceiver;", "isLowBattery", "layHeader", "progressBar", "Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "homeUrl", "layStorageAccess", "Landroidx/constraintlayout/widget/ConstraintLayout;", "btnGrant", "Lcom/google/android/material/button/MaterialButton;", "btnIgnore", "btnRemoteLogIn", "deepLinkData", "Lcom/redphx/betterxc/util/DeepLinkData;", "getDeepLinkData", "()Lcom/redphx/betterxc/util/DeepLinkData;", "setDeepLinkData", "(Lcom/redphx/betterxc/util/DeepLinkData;)V", "isPlaying", "remapBackButton", "bxFlags", "Lcom/redphx/betterxc/data/BxFlags;", "onStateChanged", "", "toggleFabVisibility", "updateFabContainerVisibility", "show", "(Ljava/lang/Boolean;)V", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "prepareBxFlags", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyUp", "onResume", "onPause", "onDestroy", "limitRefreshRate", "enableWebViewScaling", "disableWebViewScaling", "setupWebView", "checkHardwareAcceleratedSupport", "showNvidiaHooksWarning", "openTrueAchievementsLink", "override", "xboxTitleId", "id", "resetBottomSheetWebView", "observeBatteryLevel", "updateLowBatteryIndicator", "MyWebViewClient", "MyWebChromeClient", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MainActivity extends BaseActivity {
    public static final int REQUEST_CODE_PERMISSION_RECORD_AUDIO = 1;
    private AudioManager audioManager;
    private BroadcastReceiver batteryReceiver;
    private WebViewBottomSheet bottomSheetWebView;
    private MaterialButton btnGrant;
    private MaterialButton btnIgnore;
    private MaterialButton btnRemoteLogIn;
    private BxFlags bxFlags;
    private DeepLinkData deepLinkData;
    private FloatingActionButton fabSettings;
    private String homeUrl;
    private boolean isInitialized;
    private boolean isLowBattery;
    private boolean isPlaying;
    private LinearLayout layFabContainer;
    private LinearLayout layHeader;
    private ConstraintLayout layStorageAccess;
    private SwipeRefreshLayout laySwipeRefresh;
    private FrameLayout layWebView;
    private LinearProgressIndicator progressBar;
    private boolean remapBackButton;
    private SharedPreferences sharedPref;
    private ActivityResultLauncher<String> storageRequestResult;
    private View viewLowBatteryIndicator;
    private WebAppInterface webAppInterface;
    private String webLocale;
    private XcloudWebView webView;
    private int webViewBaseScale;
    private String webViewUrl = "";
    private int webViewZoomPercent = 100;
    private static final Regex ACCEPTED_URL_REGEX = new Regex("^https?://(?:www\\.)?xbox.com/([a-z]{2}-[A-Z]{2}/)?(play|auth)|^https://login\\.microsoftonline\\.com/|^https://login\\.live\\.com/");

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onKeyDown$lambda$11(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onKeyUp$lambda$12(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onKeyUp$lambda$13(String str) {
    }

    public final DeepLinkData getDeepLinkData() {
        return this.deepLinkData;
    }

    public final void setDeepLinkData(DeepLinkData deepLinkData) {
        this.deepLinkData = deepLinkData;
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J&\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J$\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\"\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¨\u0006\u0017"}, d2 = {"Lcom/redphx/betterxc/activity/MainActivity$MyWebViewClient;", "Landroid/webkit/WebViewClient;", "<init>", "(Lcom/redphx/betterxc/activity/MainActivity;)V", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "onPageStarted", "", "url", "", "favicon", "Landroid/graphics/Bitmap;", "onPageFinished", "doUpdateVisitedHistory", "isReload", "onScaleChanged", "oldScale", "", "newScale", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Intrinsics.checkNotNullParameter(request, "request");
            String uri = request.getUrl().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            if (MainActivity.ACCEPTED_URL_REGEX.containsMatchIn(uri)) {
                return false;
            }
            Utils.Companion companion = Utils.INSTANCE;
            Context baseContext = MainActivity.this.getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
            companion.openUrl(baseContext, uri, false);
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            SwipeRefreshLayout swipeRefreshLayout = MainActivity.this.laySwipeRefresh;
            XcloudWebView xcloudWebView = null;
            if (swipeRefreshLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("laySwipeRefresh");
                swipeRefreshLayout = null;
            }
            swipeRefreshLayout.setRefreshing(false);
            LinearProgressIndicator linearProgressIndicator = MainActivity.this.progressBar;
            if (linearProgressIndicator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator = null;
            }
            linearProgressIndicator.setIndeterminate(true);
            LinearProgressIndicator linearProgressIndicator2 = MainActivity.this.progressBar;
            if (linearProgressIndicator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator2 = null;
            }
            linearProgressIndicator2.setVisibility(0);
            CrashTracker.INSTANCE.reset(true);
            LinearLayout linearLayout = MainActivity.this.layFabContainer;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layFabContainer");
                linearLayout = null;
            }
            linearLayout.setVisibility(8);
            if (url == null || StringsKt.contains$default((CharSequence) url, (CharSequence) "/play/launch/", false, 2, (Object) null)) {
                return;
            }
            XcloudWebView xcloudWebView2 = MainActivity.this.webView;
            if (xcloudWebView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                xcloudWebView2 = null;
            }
            xcloudWebView2.setPlaying(false);
            XcloudWebView xcloudWebView3 = MainActivity.this.webView;
            if (xcloudWebView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                xcloudWebView = xcloudWebView3;
            }
            xcloudWebView.setAllowPullToRefresh(true);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            LinearProgressIndicator linearProgressIndicator = MainActivity.this.progressBar;
            String str = null;
            if (linearProgressIndicator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator = null;
            }
            linearProgressIndicator.setVisibility(8);
            LinearProgressIndicator linearProgressIndicator2 = MainActivity.this.progressBar;
            if (linearProgressIndicator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator2 = null;
            }
            linearProgressIndicator2.setProgressCompat(0, false);
            if (url != null && StringsKt.startsWith$default(url, "https://login.microsoftonline.com/consumers/oauth2/v2.0/logoutsession", false, 2, (Object) null)) {
                XcloudWebView xcloudWebView = MainActivity.this.webView;
                if (xcloudWebView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webView");
                    xcloudWebView = null;
                }
                String str2 = MainActivity.this.homeUrl;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("homeUrl");
                } else {
                    str = str2;
                }
                xcloudWebView.loadUrl(str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
            if (url == null) {
                return;
            }
            if (StringsKt.startsWith$default(url, "https://www.xbox.com", false, 2, (Object) null)) {
                url = url.substring(26);
                Intrinsics.checkNotNullExpressionValue(url, "substring(...)");
            }
            MaterialButton materialButton = MainActivity.this.btnRemoteLogIn;
            if (materialButton == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnRemoteLogIn");
                materialButton = null;
            }
            materialButton.setVisibility(StringsKt.contains$default((CharSequence) url, (CharSequence) "/oauth20_authorize.srf", false, 2, (Object) null) ? 0 : 8);
            MainActivity.this.webViewUrl = url;
            MainActivity.this.toggleFabVisibility();
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            if (MainActivity.this.webViewZoomPercent != 100 && MainActivity.this.webViewBaseScale == 0) {
                MainActivity.this.webViewBaseScale = (int) (oldScale * 100);
                MainActivity.this.enableWebViewScaling();
                XcloudWebView xcloudWebView = MainActivity.this.webView;
                if (xcloudWebView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webView");
                    xcloudWebView = null;
                }
                xcloudWebView.setVisibility(0);
                return;
            }
            super.onScaleChanged(view, oldScale, newScale);
        }
    }

    public final void onStateChanged() {
        SharedPreferences sharedPreferences = this.sharedPref;
        SharedPreferences sharedPreferences2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        String string = sharedPreferences.getString("force_landscape_mode", "playing");
        XcloudWebView xcloudWebView = this.webView;
        if (xcloudWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView = null;
        }
        this.isPlaying = xcloudWebView.getIsPlaying();
        XcloudWebView xcloudWebView2 = this.webView;
        if (xcloudWebView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView2 = null;
        }
        xcloudWebView2.setAllowBack(!this.isPlaying);
        XcloudWebView xcloudWebView3 = this.webView;
        if (xcloudWebView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView3 = null;
        }
        xcloudWebView3.setAllowPullToRefresh(!this.isPlaying);
        if (this.isPlaying) {
            LinearProgressIndicator linearProgressIndicator = this.progressBar;
            if (linearProgressIndicator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator = null;
            }
            linearProgressIndicator.setVisibility(8);
            SharedPreferences sharedPreferences3 = this.sharedPref;
            if (sharedPreferences3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            } else {
                sharedPreferences2 = sharedPreferences3;
            }
            if (!sharedPreferences2.getBoolean("web_zoom_while_playing", false)) {
                disableWebViewScaling();
            }
            updateImmersiveMode(BaseActivity.ImmersiveMode.DISABLE);
            getWindow().addFlags(128);
            if (!DeviceInfo.INSTANCE.isAndroidTv() && Intrinsics.areEqual(string, "playing")) {
                setRequestedOrientation(6);
            }
        } else {
            enableWebViewScaling();
            updateImmersiveMode(BaseActivity.ImmersiveMode.ENABLE);
            getWindow().clearFlags(128);
            if (!DeviceInfo.INSTANCE.isAndroidTv() && Intrinsics.areEqual(string, "playing")) {
                setRequestedOrientation(-1);
            }
        }
        toggleFabVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toggleFabVisibility() {
        FloatingActionButton floatingActionButton = this.fabSettings;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabSettings");
            floatingActionButton = null;
        }
        floatingActionButton.setVisibility(8);
        if (Intrinsics.areEqual(this.webViewUrl, "/play") || !StringsKt.startsWith$default(this.webViewUrl, "/play/", false, 2, (Object) null)) {
            FloatingActionButton floatingActionButton2 = this.fabSettings;
            if (floatingActionButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fabSettings");
                floatingActionButton2 = null;
            }
            floatingActionButton2.setVisibility(0);
        }
        SharedPreferences sharedPreferences = this.sharedPref;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        if (sharedPreferences.getBoolean("enable_bx_script", true)) {
            FloatingActionButton floatingActionButton3 = this.fabSettings;
            if (floatingActionButton3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fabSettings");
                floatingActionButton3 = null;
            }
            floatingActionButton3.setVisibility(8);
        }
        updateFabContainerVisibility$default(this, null, 1, null);
    }

    public static /* synthetic */ void updateFabContainerVisibility$default(MainActivity mainActivity, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = null;
        }
        mainActivity.updateFabContainerVisibility(bool);
    }

    public final void updateFabContainerVisibility(Boolean show) {
        boolean z;
        if (show == null) {
            XcloudWebView xcloudWebView = this.webView;
            if (xcloudWebView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                xcloudWebView = null;
            }
            if (!xcloudWebView.getIsPlaying()) {
                XcloudWebView xcloudWebView2 = this.webView;
                if (xcloudWebView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webView");
                    xcloudWebView2 = null;
                }
                if (!xcloudWebView2.getIsShowingDialog()) {
                    XcloudWebView xcloudWebView3 = this.webView;
                    if (xcloudWebView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("webView");
                        xcloudWebView3 = null;
                    }
                    if (xcloudWebView3.getCurrentScrollY() == 0) {
                        z = true;
                        show = Boolean.valueOf(z);
                    }
                }
            }
            z = false;
            show = Boolean.valueOf(z);
        }
        LinearLayout linearLayout = this.layFabContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layFabContainer");
            linearLayout = null;
        }
        if (Intrinsics.areEqual(show, Boolean.valueOf(linearLayout.getVisibility() == 0))) {
            return;
        }
        LinearLayout linearLayout2 = this.layFabContainer;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layFabContainer");
            linearLayout2 = null;
        }
        linearLayout2.setVisibility(show.booleanValue() ? 0 : 8);
        if (show.booleanValue()) {
            linearLayout2.setAlpha(0.0f);
            linearLayout2.clearAnimation();
            linearLayout2.animate().alpha(1.0f).setDuration(200L).setListener(null);
            return;
        }
        linearLayout2.clearAnimation();
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J \u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0002J\u0012\u0010\u0015\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016¨\u0006\u0016"}, d2 = {"Lcom/redphx/betterxc/activity/MainActivity$MyWebChromeClient;", "Landroid/webkit/WebChromeClient;", "<init>", "(Lcom/redphx/betterxc/activity/MainActivity;)V", "onProgressChanged", "", "view", "Landroid/webkit/WebView;", "newProgress", "", "onConsoleMessage", "", "consoleMessage", "Landroid/webkit/ConsoleMessage;", "onPermissionRequest", "request", "Landroid/webkit/PermissionRequest;", "askForPermission", "permission", "", "requestCode", "onPermissionRequestCanceled", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class MyWebChromeClient extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        public MyWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView view, int newProgress) {
            LinearProgressIndicator linearProgressIndicator = MainActivity.this.progressBar;
            if (linearProgressIndicator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator = null;
            }
            linearProgressIndicator.setProgressCompat(newProgress, true);
            super.onProgressChanged(view, newProgress);
        }

        @Override // android.webkit.WebChromeClient
        public void onPermissionRequest(PermissionRequest request) {
            String[] resources;
            boolean z = false;
            z = false;
            if (request != null && (resources = request.getResources()) != null) {
                boolean z2 = false;
                for (String str : resources) {
                    if (Intrinsics.areEqual(str, "android.webkit.resource.AUDIO_CAPTURE")) {
                        z2 = askForPermission(request, "android.permission.RECORD_AUDIO", 1);
                    }
                }
                z = z2;
            }
            if (z) {
                return;
            }
            super.onPermissionRequest(request);
        }

        private final boolean askForPermission(PermissionRequest request, String permission, int requestCode) {
            if (ContextCompat.checkSelfPermission(MainActivity.this.getApplicationContext(), permission) != 0) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
                return false;
            }
            request.grant(request.getResources());
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onPermissionRequestCanceled(PermissionRequest request) {
            super.onPermissionRequestCanceled(request);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if ((grantResults.length == 0) || grantResults[0] != 0) {
                return;
            }
            new MaterialAlertDialogBuilder(this).setTitle((CharSequence) getString(R.string.restart_required)).setMessage((CharSequence) getString(R.string.permission_restart)).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda15
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.onRequestPermissionsResult$lambda$1(MainActivity.this, dialogInterface, i);
                }
            }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onRequestPermissionsResult$lambda$1(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialogInterface.dismiss();
        this$0.recreate();
    }

    private final void prepareBxFlags() {
        String str;
        try {
            PackageInfo currentProvider = WebViewUtil.INSTANCE.getCurrentProvider();
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = currentProvider.applicationInfo;
            Intrinsics.checkNotNull(applicationInfo);
            CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            Intrinsics.checkNotNull(applicationLabel, "null cannot be cast to non-null type kotlin.String");
            str = ((String) applicationLabel) + ' ' + currentProvider.versionName + " (" + currentProvider.packageName + ')';
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            str = "";
        }
        String str2 = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SharedPreferences sharedPreferences = this.sharedPref;
        SharedPreferences sharedPreferences2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        String string = sharedPreferences.getString("force_landscape_mode", "playing");
        Intrinsics.checkNotNull(string);
        linkedHashMap.put("force_landscape_mode", string);
        SharedPreferences sharedPreferences3 = this.sharedPref;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences3 = null;
        }
        linkedHashMap.put("remap_back_button", Boolean.valueOf(sharedPreferences3.getBoolean("remap_back_button", false)));
        SharedPreferences sharedPreferences4 = this.sharedPref;
        if (sharedPreferences4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
        } else {
            sharedPreferences2 = sharedPreferences4;
        }
        linkedHashMap.put("webview_optimize", Boolean.valueOf(sharedPreferences2.getBoolean("webview_optimize", false)));
        String str3 = DeviceInfo.INSTANCE.isAndroidTv() ? "android-tv" : "android";
        String RELEASE = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
        int i = Build.VERSION.SDK_INT;
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.addSpread(Build.SUPPORTED_64_BIT_ABIS);
        spreadBuilder.addSpread(Build.SUPPORTED_32_BIT_ABIS);
        List listOf = CollectionsKt.listOf(spreadBuilder.toArray(new String[spreadBuilder.size()]));
        String BOARD = Build.BOARD;
        Intrinsics.checkNotNullExpressionValue(BOARD, "BOARD");
        String DEVICE = Build.DEVICE;
        Intrinsics.checkNotNullExpressionValue(DEVICE, "DEVICE");
        String HARDWARE = Build.HARDWARE;
        Intrinsics.checkNotNullExpressionValue(HARDWARE, "HARDWARE");
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        String PRODUCT = Build.PRODUCT;
        Intrinsics.checkNotNullExpressionValue(PRODUCT, "PRODUCT");
        String BRAND = Build.BRAND;
        Intrinsics.checkNotNullExpressionValue(BRAND, "BRAND");
        String MANUFACTURER = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
        this.bxFlags = new BxFlags(new BxDeviceInfo(str3, new BxAndroidInfo(RELEASE, i, listOf, BOARD, DEVICE, MODEL, HARDWARE, PRODUCT, BRAND, MANUFACTURER, str2, linkedHashMap)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.redphx.betterxc.dialog.WebViewManagerDialog] */
    @Override // com.redphx.betterxc.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        limitRefreshRate();
        Object systemService = getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.audioManager = (AudioManager) systemService;
        if (!WebViewUtil.INSTANCE.isSupported()) {
            CrashTracker.INSTANCE.reset(true);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new WebViewManagerDialog(this, null, new DialogInterface.OnDismissListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda16
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    MainActivity.onCreate$lambda$3(Ref.ObjectRef.this, this, dialogInterface);
                }
            }, 2, null);
            ((WebViewManagerDialog) objectRef.element).setCancelable(false);
            ((WebViewManagerDialog) objectRef.element).show();
            return;
        }
        showNvidiaHooksWarning();
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = this.sharedPref;
        SharedPreferences sharedPreferences2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        this.remapBackButton = sharedPreferences.getBoolean("remap_back_button", false);
        prepareBxFlags();
        SharedPreferences sharedPreferences3 = this.sharedPref;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences3 = null;
        }
        final String str = "0.24.2 (39)";
        if (!Intrinsics.areEqual(sharedPreferences3.getString("app_version", ""), "0.24.2 (39)")) {
            UpdateScriptTask.Companion.run$default(UpdateScriptTask.INSTANCE, this, null, new Function0() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit onCreate$lambda$5;
                    onCreate$lambda$5 = MainActivity.onCreate$lambda$5(MainActivity.this, str);
                    return onCreate$lambda$5;
                }
            }, 2, null);
            return;
        }
        this.layFabContainer = (LinearLayout) findViewById(R.id.lay_fab_container);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_settings);
        this.fabSettings = floatingActionButton;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabSettings");
            floatingActionButton = null;
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$6(MainActivity.this, view);
            }
        });
        Uri data = getIntent().getData();
        if (data != null) {
            this.deepLinkData = DeepLinkUtil.INSTANCE.parse(data);
        }
        checkHardwareAcceleratedSupport();
        getWindow().setStatusBarColor(getResources().getColor(R.color.action_bar_bg, null));
        setTitle("");
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.btn_remote_log_in);
        this.btnRemoteLogIn = materialButton;
        if (materialButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRemoteLogIn");
            materialButton = null;
        }
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$8(MainActivity.this, view);
            }
        });
        this.layHeader = (LinearLayout) findViewById(R.id.lay_header);
        if (Build.VERSION.SDK_INT >= 30) {
            SharedPreferences sharedPreferences4 = this.sharedPref;
            if (sharedPreferences4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            } else {
                sharedPreferences2 = sharedPreferences4;
            }
            if (sharedPreferences2.getBoolean("tv_allm", false)) {
                getWindow().setPreferMinimalPostProcessing(true);
            }
        }
        this.layWebView = (FrameLayout) findViewById(R.id.lay_webview);
        setupWebView();
        this.storageRequestResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.onCreate$lambda$9(MainActivity.this, ((Boolean) obj).booleanValue());
            }
        });
        this.layStorageAccess = (ConstraintLayout) findViewById(R.id.lay_storage_access);
        CheckUpdateTask.INSTANCE.run(this, new Function0() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        this.viewLowBatteryIndicator = findViewById(R.id.view_low_battery_indicator);
        observeBatteryLevel();
        this.isInitialized = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$3(Ref.ObjectRef dialog, final MainActivity this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        T t = dialog.element;
        Intrinsics.checkNotNull(t);
        if (((WebViewManagerDialog) t).changedWebView()) {
            DialogUtil.INSTANCE.showForceCloseRequiredDialog(this$0, new DialogInterface.OnDismissListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda8
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface2) {
                    MainActivity.onCreate$lambda$3$lambda$2(MainActivity.this, dialogInterface2);
                }
            });
        } else {
            this$0.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$2(MainActivity this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishAffinity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$5(MainActivity this$0, String versionFullName) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(versionFullName, "$versionFullName");
        SharedPreferences sharedPreferences = this$0.sharedPref;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("app_version", versionFullName);
        edit.apply();
        this$0.recreate();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getBaseContext(), (Class<?>) SettingsActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = null;
        this$0.deepLinkData = new DeepLinkData(DeepLinkHost.DEVICE_CODE, null, 2, null);
        XcloudWebView xcloudWebView = this$0.webView;
        if (xcloudWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView = null;
        }
        String str2 = this$0.homeUrl;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeUrl");
        } else {
            str = str2;
        }
        xcloudWebView.loadUrl(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(MainActivity this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            Toast.makeText(this$0.getBaseContext(), "Granted", 0).show();
            ConstraintLayout constraintLayout = this$0.layStorageAccess;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layStorageAccess");
                constraintLayout = null;
            }
            constraintLayout.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("recreate", false)) {
            recreate();
        }
    }

    @Override // com.redphx.betterxc.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        resetBottomSheetWebView();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && this.remapBackButton) {
            XcloudWebView xcloudWebView = this.webView;
            if (xcloudWebView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                xcloudWebView = null;
            }
            xcloudWebView.evaluateJavascript("window.backButtonDown()", new ValueCallback() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda5
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    MainActivity.onKeyDown$lambda$11((String) obj);
                }
            });
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            XcloudWebView xcloudWebView = null;
            if (this.remapBackButton) {
                XcloudWebView xcloudWebView2 = this.webView;
                if (xcloudWebView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webView");
                } else {
                    xcloudWebView = xcloudWebView2;
                }
                xcloudWebView.evaluateJavascript("window.backButtonUp()", new ValueCallback() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda6
                    @Override // android.webkit.ValueCallback
                    public final void onReceiveValue(Object obj) {
                        MainActivity.onKeyUp$lambda$12((String) obj);
                    }
                });
                return true;
            }
            XcloudWebView xcloudWebView3 = this.webView;
            if (xcloudWebView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                xcloudWebView = xcloudWebView3;
            }
            xcloudWebView.evaluateJavascript("window.BX_EXPOSED.backButtonPressed()", new ValueCallback() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda7
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    MainActivity.onKeyUp$lambda$13((String) obj);
                }
            });
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isInitialized) {
            MaterialButton materialButton = null;
            ConstraintLayout constraintLayout = null;
            ConstraintLayout constraintLayout2 = null;
            try {
                AudioManager audioManager = this.audioManager;
                if (audioManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioManager");
                    audioManager = null;
                }
                audioManager.setParameters("set_audio_low_latency=1");
            } catch (Exception unused) {
            }
            SharedPreferences sharedPreferences = this.sharedPref;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
                sharedPreferences = null;
            }
            if (sharedPreferences.getBoolean(Constants.PREF_IGNORE_STORAGE_PERMISSION, false)) {
                ConstraintLayout constraintLayout3 = this.layStorageAccess;
                if (constraintLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layStorageAccess");
                } else {
                    constraintLayout = constraintLayout3;
                }
                constraintLayout.setVisibility(8);
                return;
            }
            StorageUtil.Companion companion = StorageUtil.INSTANCE;
            Context baseContext = getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
            if (companion.isPermissionGranted(baseContext)) {
                ConstraintLayout constraintLayout4 = this.layStorageAccess;
                if (constraintLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layStorageAccess");
                } else {
                    constraintLayout2 = constraintLayout4;
                }
                constraintLayout2.setVisibility(8);
                return;
            }
            ConstraintLayout constraintLayout5 = this.layStorageAccess;
            if (constraintLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layStorageAccess");
                constraintLayout5 = null;
            }
            constraintLayout5.setVisibility(0);
            if (this.btnGrant != null) {
                return;
            }
            MaterialButton materialButton2 = (MaterialButton) findViewById(R.id.btn_grant);
            this.btnGrant = materialButton2;
            if (materialButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnGrant");
                materialButton2 = null;
            }
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.onResume$lambda$14(MainActivity.this, view);
                }
            });
            MaterialButton materialButton3 = (MaterialButton) findViewById(R.id.btn_ignore);
            this.btnIgnore = materialButton3;
            if (materialButton3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnIgnore");
            } else {
                materialButton = materialButton3;
            }
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.onResume$lambda$16(MainActivity.this, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$14(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StorageUtil.Companion companion = StorageUtil.INSTANCE;
        MainActivity mainActivity = this$0;
        ActivityResultLauncher<String> activityResultLauncher = this$0.storageRequestResult;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storageRequestResult");
            activityResultLauncher = null;
        }
        companion.requestPermission(mainActivity, activityResultLauncher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$16(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConstraintLayout constraintLayout = this$0.layStorageAccess;
        SharedPreferences sharedPreferences = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layStorageAccess");
            constraintLayout = null;
        }
        constraintLayout.setVisibility(8);
        SharedPreferences sharedPreferences2 = this$0.sharedPref;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
        } else {
            sharedPreferences = sharedPreferences2;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(Constants.PREF_IGNORE_STORAGE_PERMISSION, true);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.isInitialized) {
            try {
                AudioManager audioManager = this.audioManager;
                if (audioManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioManager");
                    audioManager = null;
                }
                audioManager.setParameters("set_audio_low_latency=0");
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.isInitialized) {
            XcloudWebView xcloudWebView = this.webView;
            BroadcastReceiver broadcastReceiver = null;
            if (xcloudWebView != null) {
                if (xcloudWebView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webView");
                    xcloudWebView = null;
                }
                xcloudWebView.destroy();
            }
            FrameLayout frameLayout = this.layWebView;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layWebView");
                frameLayout = null;
            }
            frameLayout.removeAllViews();
            resetBottomSheetWebView();
            if (this.batteryReceiver != null) {
                Context baseContext = getBaseContext();
                BroadcastReceiver broadcastReceiver2 = this.batteryReceiver;
                if (broadcastReceiver2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("batteryReceiver");
                } else {
                    broadcastReceiver = broadcastReceiver2;
                }
                baseContext.unregisterReceiver(broadcastReceiver);
            }
        }
    }

    private final void limitRefreshRate() {
        if (DeviceInfo.INSTANCE.isAndroidTv()) {
            return;
        }
        SharedPreferences sharedPreferences = this.sharedPref;
        Display.Mode mode = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        if (sharedPreferences.getBoolean("screen_force_60hz", true)) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            Intrinsics.checkNotNullExpressionValue(attributes, "getAttributes(...)");
            if (Build.VERSION.SDK_INT >= 34) {
                attributes.preferredRefreshRate = 60.0f;
            } else {
                Display.Mode[] supportedModes = ContextCompat.getDisplayOrDefault(this).getSupportedModes();
                Intrinsics.checkNotNullExpressionValue(supportedModes, "getSupportedModes(...)");
                Display.Mode[] modeArr = supportedModes;
                int length = modeArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Display.Mode mode2 = modeArr[i];
                    int refreshRate = (int) mode2.getRefreshRate();
                    if (58 <= refreshRate && refreshRate < 63) {
                        mode = mode2;
                        break;
                    }
                    i++;
                }
                Display.Mode mode3 = mode;
                if (mode3 != null) {
                    attributes.preferredDisplayModeId = mode3.getModeId();
                }
            }
            getWindow().setAttributes(attributes);
        }
    }

    public final void enableWebViewScaling() {
        if (this.webViewZoomPercent != 100) {
            XcloudWebView xcloudWebView = this.webView;
            if (xcloudWebView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                xcloudWebView = null;
            }
            xcloudWebView.setInitialScale((this.webViewBaseScale * this.webViewZoomPercent) / 100);
            return;
        }
        disableWebViewScaling();
    }

    private final void disableWebViewScaling() {
        XcloudWebView xcloudWebView = this.webView;
        if (xcloudWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView = null;
        }
        xcloudWebView.setInitialScale(0);
    }

    private final void setupWebView() {
        UserscriptUtil.Companion companion = UserscriptUtil.INSTANCE;
        BxFlags bxFlags = this.bxFlags;
        String str = null;
        if (bxFlags == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bxFlags");
            bxFlags = null;
        }
        String upVar = companion.setup(this, bxFlags);
        if (upVar == null) {
            return;
        }
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.lay_swipe_refresh);
        this.laySwipeRefresh = swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("laySwipeRefresh");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                MainActivity.setupWebView$lambda$19(MainActivity.this);
            }
        });
        SwipeRefreshLayout swipeRefreshLayout2 = this.laySwipeRefresh;
        if (swipeRefreshLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("laySwipeRefresh");
            swipeRefreshLayout2 = null;
        }
        swipeRefreshLayout2.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda9
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnChildScrollUpCallback
            public final boolean canChildScrollUp(SwipeRefreshLayout swipeRefreshLayout3, View view) {
                boolean z;
                z = MainActivity.setupWebView$lambda$20(MainActivity.this, swipeRefreshLayout3, view);
                return z;
            }
        });
        SharedPreferences sharedPreferences = this.sharedPref;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        this.webViewZoomPercent = sharedPreferences.getInt("web_zoom", 100);
        CrashTracker.INSTANCE.increase();
        if (CrashTracker.INSTANCE.hasCrashes()) {
            startActivity(new Intent(getBaseContext(), (Class<?>) SettingsActivity.class));
            Toast.makeText(getBaseContext(), "Select another WebView implementation to stop the crashes", 1).show();
            CrashTracker.INSTANCE.reset(true);
            finish();
            return;
        }
        XcloudWebView xcloudWebView = this.webView;
        if (xcloudWebView != null) {
            if (xcloudWebView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                xcloudWebView = null;
            }
            xcloudWebView.destroy();
        }
        FrameLayout frameLayout = this.layWebView;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layWebView");
            frameLayout = null;
        }
        frameLayout.removeAllViews();
        this.progressBar = (LinearProgressIndicator) findViewById(R.id.progress_bar);
        SharedPreferences sharedPreferences2 = this.sharedPref;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences2 = null;
        }
        String string = sharedPreferences2.getString(Constants.PREF_WEB_LOCALE, "en-US");
        Intrinsics.checkNotNull(string);
        this.webLocale = string;
        XcloudWebView xcloudWebView2 = new XcloudWebView(this, null, 2, null);
        this.webView = xcloudWebView2;
        xcloudWebView2.setup();
        XcloudWebView xcloudWebView3 = this.webView;
        if (xcloudWebView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView3 = null;
        }
        xcloudWebView3.setWebViewClient(new MyWebViewClient());
        xcloudWebView3.setWebChromeClient(new MyWebChromeClient());
        xcloudWebView3.setVerticalScrollBarEnabled(false);
        xcloudWebView3.setHorizontalScrollBarEnabled(false);
        WebSettings settings = xcloudWebView3.getSettings();
        String userAgentString = xcloudWebView3.getSettings().getUserAgentString();
        Intrinsics.checkNotNullExpressionValue(userAgentString, "getUserAgentString(...)");
        settings.setUserAgentString(StringsKt.replace$default(userAgentString, "AFT", "", false, 4, (Object) null));
        WebAppInterface webAppInterface = new WebAppInterface(xcloudWebView3, this);
        this.webAppInterface = webAppInterface;
        xcloudWebView3.addJavascriptInterface(webAppInterface, "AppInterface");
        xcloudWebView3.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                MainActivity.setupWebView$lambda$22$lambda$21(MainActivity.this, view, i, i2, i3, i4);
            }
        });
        FrameLayout frameLayout2 = this.layWebView;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layWebView");
            frameLayout2 = null;
        }
        XcloudWebView xcloudWebView4 = this.webView;
        if (xcloudWebView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView4 = null;
        }
        frameLayout2.addView(xcloudWebView4);
        if (upVar.length() > 0) {
            for (Object[] objArr : new Object[][]{new Object[]{upVar, SetsKt.setOf("https://*.xbox.com")}}) {
                XcloudWebView xcloudWebView5 = this.webView;
                if (xcloudWebView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webView");
                    xcloudWebView5 = null;
                }
                Object obj = objArr[0];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                Object obj2 = objArr[1];
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
                WebViewCompat.addDocumentStartJavaScript(xcloudWebView5, (String) obj, (Set) obj2);
            }
        }
        StringBuilder sb = new StringBuilder("https://www.xbox.com/");
        String str2 = this.webLocale;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webLocale");
            str2 = null;
        }
        this.homeUrl = sb.append(str2).append("/play").toString();
        XcloudWebView xcloudWebView6 = this.webView;
        if (xcloudWebView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView6 = null;
        }
        if (this.webViewZoomPercent != 100) {
            xcloudWebView6.setVisibility(8);
            xcloudWebView6.setInitialScale(100);
        }
        String str3 = this.homeUrl;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeUrl");
        } else {
            str = str3;
        }
        xcloudWebView6.loadUrl(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupWebView$lambda$19(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XcloudWebView xcloudWebView = this$0.webView;
        if (xcloudWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView = null;
        }
        xcloudWebView.reload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setupWebView$lambda$20(MainActivity this$0, SwipeRefreshLayout swipeRefreshLayout, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(swipeRefreshLayout, "<unused var>");
        XcloudWebView xcloudWebView = this$0.webView;
        XcloudWebView xcloudWebView2 = null;
        if (xcloudWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView = null;
        }
        if (!xcloudWebView.getAllowPullToRefresh()) {
            return true;
        }
        XcloudWebView xcloudWebView3 = this$0.webView;
        if (xcloudWebView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            xcloudWebView2 = xcloudWebView3;
        }
        return xcloudWebView2.getStartScrollY() != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupWebView$lambda$22$lambda$21(MainActivity this$0, View view, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XcloudWebView xcloudWebView = this$0.webView;
        if (xcloudWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            xcloudWebView = null;
        }
        xcloudWebView.setCurrentScrollY(i2);
        updateFabContainerVisibility$default(this$0, null, 1, null);
    }

    private final void checkHardwareAcceleratedSupport() {
        MediaCodecList mediaCodecList = new MediaCodecList(0);
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "video/avc");
        String findDecoderForFormat = mediaCodecList.findDecoderForFormat(mediaFormat);
        if (findDecoderForFormat != null) {
            String lowerCase = findDecoderForFormat.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (!StringsKt.startsWith$default(lowerCase, "omx.google", false, 2, (Object) null)) {
                return;
            }
        }
        Toast.makeText(getBaseContext(), "Your H.264 decoder (" + findDecoderForFormat + ") is not hardware-accelerated.\nIt's recommended to use Better xCloud with Kiwi Browser for better performance.", 1).show();
    }

    private final void showNvidiaHooksWarning() {
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.redphx.betterxc.MainApplication");
        MainApplication mainApplication = (MainApplication) application;
        if (mainApplication.getShowNvidiaHooksWarning()) {
            mainApplication.setShowNvidiaHooksWarning(false);
            new MaterialAlertDialogBuilder(this).setTitle((CharSequence) "NVIDIA Shield TV").setMessage((CharSequence) "Please disable or uninstall the \"com.nvidia.shieldtech.hooks\" package or you will have problems with the controller.").setPositiveButton(R.string.how_to_fix, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda14
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.showNvidiaHooksWarning$lambda$26(MainActivity.this, dialogInterface, i);
                }
            }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showNvidiaHooksWarning$lambda$26(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Utils.Companion companion = Utils.INSTANCE;
        Context baseContext = this$0.getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
        companion.openUrl(baseContext, Constants.URL_NVIDIA_SHIELD_TV, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void openTrueAchievementsLink(boolean r5, java.lang.String r6, java.lang.String r7) {
        /*
            r4 = this;
            if (r6 == 0) goto L3c
            java.lang.String r0 = "undefined"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            if (r1 != 0) goto L3c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "https://www.trueachievements.com/deeplink/"
            r1.<init>(r2)
            java.lang.StringBuilder r6 = r1.append(r6)
            java.lang.String r6 = r6.toString()
            if (r7 == 0) goto L3e
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            if (r0 != 0) goto L3e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r0 = r0.append(r6)
            r1 = 47
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r7 = r0.append(r7)
            java.lang.String r7 = r7.toString()
            r3 = r7
            r7 = r6
            r6 = r3
            goto L3f
        L3c:
            java.lang.String r6 = "https://www.trueachievements.com"
        L3e:
            r7 = r6
        L3f:
            com.redphx.betterxc.util.DeviceInfo$Companion r0 = com.redphx.betterxc.util.DeviceInfo.INSTANCE
            boolean r0 = r0.isAndroidTv()
            if (r0 == 0) goto L66
            com.redphx.betterxc.util.InputManagerUtil$Companion r0 = com.redphx.betterxc.util.InputManagerUtil.INSTANCE
            android.content.Context r1 = r4.getBaseContext()
            java.lang.String r2 = "getBaseContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r0 = r0.hasMouseDevice(r1)
            if (r0 != 0) goto L66
            com.redphx.betterxc.util.Utils$Companion r5 = com.redphx.betterxc.util.Utils.INSTANCE
            android.content.Context r7 = r4.getBaseContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            r0 = 0
            r5.openUrl(r7, r6, r0)
            return
        L66:
            com.redphx.betterxc.web.WebViewBottomSheet r0 = r4.bottomSheetWebView
            if (r0 != 0) goto L74
            com.redphx.betterxc.web.WebViewBottomSheet r0 = new com.redphx.betterxc.web.WebViewBottomSheet
            r1 = r4
            androidx.appcompat.app.AppCompatActivity r1 = (androidx.appcompat.app.AppCompatActivity) r1
            r0.<init>(r1)
            r4.bottomSheetWebView = r0
        L74:
            com.redphx.betterxc.web.WebViewBottomSheet r0 = r4.bottomSheetWebView
            if (r0 == 0) goto L7e
            r0.setHomeUrl(r7)
            r0.loadUrl(r6, r5)
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redphx.betterxc.activity.MainActivity.openTrueAchievementsLink(boolean, java.lang.String, java.lang.String):void");
    }

    public final void resetBottomSheetWebView() {
        WebViewBottomSheet webViewBottomSheet = this.bottomSheetWebView;
        if (webViewBottomSheet != null) {
            webViewBottomSheet.destroy();
        }
        this.bottomSheetWebView = null;
    }

    private final void observeBatteryLevel() {
        final int i;
        if (DeviceInfo.INSTANCE.isAndroidTv()) {
            return;
        }
        BroadcastReceiver broadcastReceiver = null;
        boolean z = false;
        try {
            SharedPreferences sharedPreferences = this.sharedPref;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
                sharedPreferences = null;
            }
            String string = sharedPreferences.getString("low_battery_indicator", "0");
            Intrinsics.checkNotNull(string);
            i = Math.max(0, Integer.parseInt(string));
        } catch (Exception unused) {
            i = 0;
        }
        BatteryUtil.Companion companion = BatteryUtil.INSTANCE;
        Context baseContext = getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
        companion.refreshStatus(baseContext);
        if (i > 0) {
            BatteryStatus status = BatteryUtil.INSTANCE.getStatus();
            if (!status.getCharging() && status.getLevel() <= i) {
                z = true;
            }
            this.isLowBattery = z;
            updateLowBatteryIndicator();
        }
        this.batteryReceiver = new BatteryReceiver(new Function2() { // from class: com.redphx.betterxc.activity.MainActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit observeBatteryLevel$lambda$28;
                observeBatteryLevel$lambda$28 = MainActivity.observeBatteryLevel$lambda$28(i, this, ((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
                return observeBatteryLevel$lambda$28;
            }
        });
        Context baseContext2 = getBaseContext();
        BroadcastReceiver broadcastReceiver2 = this.batteryReceiver;
        if (broadcastReceiver2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryReceiver");
        } else {
            broadcastReceiver = broadcastReceiver2;
        }
        baseContext2.registerReceiver(broadcastReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit observeBatteryLevel$lambda$28(int i, MainActivity mainActivity, int i2, boolean z) {
        if (i <= 0) {
            return Unit.INSTANCE;
        }
        boolean z2 = !z && i2 <= i;
        if (z2 != mainActivity.isLowBattery) {
            mainActivity.isLowBattery = z2;
            mainActivity.updateLowBatteryIndicator();
        }
        return Unit.INSTANCE;
    }

    private final void updateLowBatteryIndicator() {
        View view = this.viewLowBatteryIndicator;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewLowBatteryIndicator");
            view = null;
        }
        view.setVisibility(this.isLowBattery ? 0 : 8);
    }
}
