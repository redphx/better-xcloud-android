package com.redphx.betterxc.web;

import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import com.redphx.betterxc.activity.MainActivity;
import com.redphx.betterxc.activity.SettingsActivity;
import com.redphx.betterxc.data.GamePassCatalogProduct;
import com.redphx.betterxc.data.WallpaperDownload;
import com.redphx.betterxc.shortcut.DeviceShortcut;
import com.redphx.betterxc.shortcut.SoundShortcut;
import com.redphx.betterxc.task.UpdateScriptTask;
import com.redphx.betterxc.util.BatteryStatus;
import com.redphx.betterxc.util.BatteryUtil;
import com.redphx.betterxc.util.Constants;
import com.redphx.betterxc.util.DeepLinkData;
import com.redphx.betterxc.util.DeviceInfo;
import com.redphx.betterxc.util.DownloadManagerUtil;
import com.redphx.betterxc.util.GamePassApi;
import com.redphx.betterxc.util.ScreenshotUtil;
import com.redphx.betterxc.util.ShortcutUtil;
import com.redphx.betterxc.util.Utils;
import com.redphx.betterxc.util.XcloudUtil;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: WebAppInterface.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0006\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007J\b\u0010\u0018\u001a\u00020\u0011H\u0007J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0013H\u0007J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0013H\u0007J\b\u0010\u001c\u001a\u00020\u0011H\u0007J\b\u0010\u001d\u001a\u00020\u0011H\u0007J\b\u0010\u001e\u001a\u00020\u0011H\u0007J\b\u0010\u001f\u001a\u00020\u0011H\u0007J\u0018\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0013H\u0007J\u0010\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0013H\u0007J\b\u0010)\u001a\u00020\u0011H\u0007J\b\u0010*\u001a\u00020\u0011H\u0007J\b\u0010+\u001a\u00020\u0015H\u0007J\b\u0010,\u001a\u00020\u0011H\u0007J\b\u0010-\u001a\u00020\u0013H\u0007J\u0010\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u0013H\u0007J \u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u0013H\u0007J\b\u00104\u001a\u00020\u0013H\u0007J$\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u00132\b\u00109\u001a\u0004\u0018\u00010\u0013H\u0007J\u0018\u0010:\u001a\u00020\u00112\u0006\u0010;\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u0013H\u0007J\b\u0010=\u001a\u00020\u0011H\u0007J\u0010\u0010>\u001a\u00020\u00112\u0006\u0010?\u001a\u000207H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\u00020\u00158GX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006@"}, d2 = {"Lcom/redphx/betterxc/web/WebAppInterface;", "", "webView", "Lcom/redphx/betterxc/web/XcloudWebView;", "context", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "(Lcom/redphx/betterxc/web/XcloudWebView;Landroidx/appcompat/app/AppCompatActivity;)V", "getContext", "()Landroidx/appcompat/app/AppCompatActivity;", "setContext", "(Landroidx/appcompat/app/AppCompatActivity;)V", "lastVibrationTime", "", "vibrator", "Landroid/os/Vibrator;", "showToast", "", "toast", "", "androidVersion", "", "getAndroidVersion", "()I", "openAppSettings", "onEvent", NotificationCompat.CATEGORY_EVENT, "onEventBus", "onStreamPlaying", "onStreamStopped", "onDialogShown", "onDialogDismissed", "vibrate", "json", "intensity", "", "saveScreenshot", "name", "data", "runShortcut", "action", "requestPointerCapture", "releasePointerCapture", "startPointerServer", "closeApp", "getBatteryStatus", "createShortcut", "pathName", "createConsoleShortcut", "serverId", "deviceName", "optionsJson", "getDeepLinkData", "openTrueAchievementsLink", "override", "", "xboxTitleId", "id", "downloadWallpapers", "titleSlug", "productId", "updateLatestScript", "onShowingDialog", "showing", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebAppInterface {
    private final int androidVersion;
    private AppCompatActivity context;
    private long lastVibrationTime;
    private Vibrator vibrator;
    private XcloudWebView webView;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean vibrate$lambda$4(double d) {
        return d == 0.0d;
    }

    @JavascriptInterface
    public final void onEvent(String event) {
        Intrinsics.checkNotNullParameter(event, "event");
    }

    @JavascriptInterface
    public final void onEventBus(String event) {
        Intrinsics.checkNotNullParameter(event, "event");
    }

    public WebAppInterface(XcloudWebView webView, AppCompatActivity context) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        Intrinsics.checkNotNullParameter(context, "context");
        this.webView = webView;
        this.context = context;
        Object systemService = context.getSystemService(Vibrator.class);
        Intrinsics.checkNotNullExpressionValue(systemService, "getSystemService(...)");
        this.vibrator = (Vibrator) systemService;
        this.androidVersion = Build.VERSION.SDK_INT;
    }

    public final AppCompatActivity getContext() {
        return this.context;
    }

    public final void setContext(AppCompatActivity appCompatActivity) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<set-?>");
        this.context = appCompatActivity;
    }

    @JavascriptInterface
    public final void showToast(String toast) {
        Toast.makeText(this.context, toast, 0).show();
    }

    @JavascriptInterface
    public final int getAndroidVersion() {
        return this.androidVersion;
    }

    @JavascriptInterface
    public final void openAppSettings() {
        this.context.startActivity(new Intent(this.context, (Class<?>) SettingsActivity.class));
    }

    @JavascriptInterface
    public final void onStreamPlaying() {
        if (this.webView.getIsPlaying()) {
            return;
        }
        this.webView.setPlaying(true);
        this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                WebAppInterface.onStreamPlaying$lambda$1(WebAppInterface.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStreamPlaying$lambda$1(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.webView.setInitialBrightness(DeviceShortcut.INSTANCE.getCurrentBrightness(this$0.context));
        AppCompatActivity appCompatActivity = this$0.context;
        Intrinsics.checkNotNull(appCompatActivity, "null cannot be cast to non-null type com.redphx.betterxc.activity.MainActivity");
        MainActivity mainActivity = (MainActivity) appCompatActivity;
        mainActivity.onStateChanged();
        MainActivity.updateFabContainerVisibility$default(mainActivity, null, 1, null);
    }

    @JavascriptInterface
    public final void onStreamStopped() {
        if (this.webView.getIsPlaying()) {
            this.webView.setPlaying(false);
            this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    WebAppInterface.onStreamStopped$lambda$3(WebAppInterface.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStreamStopped$lambda$3(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.webView.getInitialBrightness() != null && !Intrinsics.areEqual(DeviceShortcut.INSTANCE.getCurrentBrightness(this$0.context), this$0.webView.getInitialBrightness())) {
            DeviceShortcut.INSTANCE.setBrightness(this$0.context, -1.0f);
        }
        AppCompatActivity appCompatActivity = this$0.context;
        Intrinsics.checkNotNull(appCompatActivity, "null cannot be cast to non-null type com.redphx.betterxc.activity.MainActivity");
        MainActivity mainActivity = (MainActivity) appCompatActivity;
        mainActivity.resetBottomSheetWebView();
        mainActivity.onStateChanged();
        MainActivity.updateFabContainerVisibility$default(mainActivity, null, 1, null);
    }

    @JavascriptInterface
    public final void onDialogShown() {
        onShowingDialog(true);
    }

    @JavascriptInterface
    public final void onDialogDismissed() {
        onShowingDialog(false);
    }

    @JavascriptInterface
    public final void vibrate(String json, double intensity) {
        Intrinsics.checkNotNullParameter(json, "json");
        if (intensity == 0.0d) {
            this.vibrator.cancel();
            return;
        }
        JSONObject jSONObject = new JSONObject(json);
        if (DeviceInfo.INSTANCE.isLogitechGCloud()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.lastVibrationTime < 100) {
                return;
            } else {
                this.lastVibrationTime = currentTimeMillis;
            }
        }
        double d = jSONObject.getDouble("leftMotorPercent");
        List mutableListOf = CollectionsKt.mutableListOf(Double.valueOf(jSONObject.getDouble("leftTriggerMotorPercent")), Double.valueOf(jSONObject.getDouble("rightMotorPercent")), Double.valueOf(jSONObject.getDouble("rightTriggerMotorPercent")));
        List list = mutableListOf;
        double maxOrThrow = CollectionsKt.maxOrThrow((Iterable<Double>) list);
        CollectionsKt.removeAll(mutableListOf, new Function1() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean vibrate$lambda$4;
                vibrate$lambda$4 = WebAppInterface.vibrate$lambda$4(((Double) obj).doubleValue());
                return Boolean.valueOf(vibrate$lambda$4);
            }
        });
        mutableListOf.remove(Double.valueOf(maxOrThrow));
        double averageOfDouble = CollectionsKt.averageOfDouble(list);
        if (Double.isNaN(averageOfDouble)) {
            averageOfDouble = 0.0d;
        }
        double min = Math.min(d + ((maxOrThrow + averageOfDouble) / 2), 100.0d) * intensity;
        if (min == 0.0d) {
            this.vibrator.cancel();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.vibrator.vibrate(VibrationEffect.createWaveform(new long[]{1200, 0}, new int[]{Math.max(1, (int) ((255 * min) / 100)), 0}, -1));
            } else {
                // Fallback for API 24-25
                this.vibrator.vibrate(new long[]{0, 1200}, -1);
            }
        }
    }

    @JavascriptInterface
    public final void saveScreenshot(final String name, final String data) {
        long j;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(data, "data");
        ThreadsKt.thread$default(true, false, null, null, 0, new Function0() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit saveScreenshot$lambda$5;
                saveScreenshot$lambda$5 = WebAppInterface.saveScreenshot$lambda$5(WebAppInterface.this, name, data);
                return saveScreenshot$lambda$5;
            }
        }, 30, null);
        if (DeviceInfo.INSTANCE.isAndroidTv()) {
            return;
        }
        if (DeviceInfo.INSTANCE.isLogitechGCloud()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.lastVibrationTime < 100) {
                return;
            }
            this.lastVibrationTime = currentTimeMillis;
            j = 200;
        } else {
            j = 180;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.vibrator.vibrate(VibrationEffect.createWaveform(new long[]{j, 0}, new int[]{10, 0}, -1));
        } else {
            // Fallback for API 24-25
            this.vibrator.vibrate(new long[]{0, j}, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit saveScreenshot$lambda$5(WebAppInterface this$0, String name, String data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(name, "$name");
        Intrinsics.checkNotNullParameter(data, "$data");
        ScreenshotUtil.INSTANCE.save(this$0.context, name, data);
        return Unit.INSTANCE;
    }

    @JavascriptInterface
    public final void runShortcut(String action) {
        Intrinsics.checkNotNullParameter(action, "action");
        switch (action.hashCode()) {
            case -2032481411:
                if (action.equals("device.brightness.dec")) {
                    this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda6
                        @Override // java.lang.Runnable
                        public final void run() {
                            WebAppInterface.runShortcut$lambda$7(WebAppInterface.this);
                        }
                    });
                    return;
                }
                return;
            case -2032476327:
                if (action.equals("device.brightness.inc")) {
                    this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            WebAppInterface.runShortcut$lambda$6(WebAppInterface.this);
                        }
                    });
                    return;
                }
                return;
            case -1885036117:
                if (action.equals("device.sound.toggle")) {
                    SoundShortcut.INSTANCE.toggleMute(this.context);
                    return;
                }
                return;
            case 977274310:
                if (action.equals("device.volume.dec")) {
                    SoundShortcut.INSTANCE.decreaseVolume(this.context);
                    return;
                }
                return;
            case 977279394:
                if (action.equals("device.volume.inc")) {
                    SoundShortcut.INSTANCE.increaseVolume(this.context);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runShortcut$lambda$6(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceShortcut.INSTANCE.increaseBrightness(this$0.context, 0.1f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runShortcut$lambda$7(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceShortcut.INSTANCE.decreaseBrightness(this$0.context, 0.1f);
    }

    @JavascriptInterface
    public final void requestPointerCapture() {
        this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                WebAppInterface.requestPointerCapture$lambda$8(WebAppInterface.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPointerCapture$lambda$8(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.webView.requestPointerCapture();
    }

    @JavascriptInterface
    public final void releasePointerCapture() {
        this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                WebAppInterface.releasePointerCapture$lambda$9(WebAppInterface.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void releasePointerCapture$lambda$9(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.webView.releasePointerCapture();
    }

    @JavascriptInterface
    public final int startPointerServer() {
        return this.webView.startPointerServer();
    }

    @JavascriptInterface
    public final void closeApp() {
        this.context.finishAffinity();
    }

    @JavascriptInterface
    public final String getBatteryStatus() {
        BatteryStatus status = BatteryUtil.INSTANCE.getStatus();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("level", Float.valueOf(status.getLevel()));
        jSONObject.put("charging", status.getCharging());
        String jSONObject2 = jSONObject.toString(0);
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }

    @JavascriptInterface
    public final void createShortcut(String pathName) {
        Intrinsics.checkNotNullParameter(pathName, "pathName");
        final List<String> productInfoFromUrl = XcloudUtil.INSTANCE.getProductInfoFromUrl(pathName);
        if (productInfoFromUrl != null) {
            this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    WebAppInterface.createShortcut$lambda$10(WebAppInterface.this, productInfoFromUrl);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createShortcut$lambda$10(WebAppInterface this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShortcutUtil.INSTANCE.createGameShortcut(this$0.context, list);
    }

    @JavascriptInterface
    public final void createConsoleShortcut(String serverId, String deviceName, String optionsJson) {
        Intrinsics.checkNotNullParameter(serverId, "serverId");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(optionsJson, "optionsJson");
        JSONObject jSONObject = new JSONObject(optionsJson);
        ShortcutUtil.Companion companion = ShortcutUtil.INSTANCE;
        AppCompatActivity appCompatActivity = this.context;
        String optString = jSONObject.optString("resolution", "1080p-hq");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        companion.createConsoleShortcut(appCompatActivity, deviceName, serverId, optString);
    }

    @JavascriptInterface
    public final String getDeepLinkData() {
        AppCompatActivity appCompatActivity = this.context;
        Intrinsics.checkNotNull(appCompatActivity, "null cannot be cast to non-null type com.redphx.betterxc.activity.MainActivity");
        DeepLinkData deepLinkData = ((MainActivity) appCompatActivity).getDeepLinkData();
        if (deepLinkData != null) {
            JsonAdapter adapter = new Moshi.Builder().build().adapter(DeepLinkData.class);
            Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
            String json = adapter.toJson(deepLinkData);
            AppCompatActivity appCompatActivity2 = this.context;
            Intrinsics.checkNotNull(appCompatActivity2, "null cannot be cast to non-null type com.redphx.betterxc.activity.MainActivity");
            ((MainActivity) appCompatActivity2).setDeepLinkData(null);
            Intrinsics.checkNotNull(json);
            return json;
        }
        return "{}";
    }

    @JavascriptInterface
    public final void openTrueAchievementsLink(final boolean override, final String xboxTitleId, final String id) {
        this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                WebAppInterface.openTrueAchievementsLink$lambda$11(WebAppInterface.this, override, xboxTitleId, id);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openTrueAchievementsLink$lambda$11(WebAppInterface this$0, boolean z, String str, String str2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppCompatActivity appCompatActivity = this$0.context;
        Intrinsics.checkNotNull(appCompatActivity, "null cannot be cast to non-null type com.redphx.betterxc.activity.MainActivity");
        ((MainActivity) appCompatActivity).openTrueAchievementsLink(z, str, str2);
    }

    @JavascriptInterface
    public final void downloadWallpapers(String titleSlug, String productId) {
        Intrinsics.checkNotNullParameter(titleSlug, "titleSlug");
        Intrinsics.checkNotNullParameter(productId, "productId");
        String string = PreferenceManager.getDefaultSharedPreferences(this.context).getString(Constants.PREF_WEB_LOCALE, "en-US");
        Intrinsics.checkNotNull(string);
        GamePassCatalogProduct titleInfo = GamePassApi.INSTANCE.getTitleInfo(productId, string);
        ArrayList<WallpaperDownload> arrayList = new ArrayList();
        if (titleInfo.getImageHero() != null) {
            arrayList.add(new WallpaperDownload("banner", titleInfo.getImageHero().getUrl()));
        }
        if (titleInfo.getImageTitledHero() != null) {
            arrayList.add(new WallpaperDownload("banner-titled", titleInfo.getImageTitledHero().getUrl()));
        }
        if (titleInfo.getImagePoster() != null) {
            arrayList.add(new WallpaperDownload("poster", titleInfo.getImagePoster().getUrl()));
        }
        if (arrayList.size() == 0) {
            this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    WebAppInterface.downloadWallpapers$lambda$12(WebAppInterface.this);
                }
            });
            return;
        }
        for (WallpaperDownload wallpaperDownload : arrayList) {
            DownloadManagerUtil.INSTANCE.download(this.context, "https:" + wallpaperDownload.getUrl() + "?format=png", titleSlug + " (" + wallpaperDownload.getType() + ").png", titleInfo.getProductTitle());
        }
        this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                WebAppInterface.downloadWallpapers$lambda$13(WebAppInterface.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadWallpapers$lambda$12(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.context, "Cannot get wallpapers", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadWallpapers$lambda$13(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.context, "Started downloading wallpaper(s)", 0).show();
    }

    @JavascriptInterface
    public final void updateLatestScript() {
        this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                WebAppInterface.updateLatestScript$lambda$15(WebAppInterface.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateLatestScript$lambda$15(final WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdateScriptTask.Companion.run$default(UpdateScriptTask.INSTANCE, this$0.context, null, new Function0() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit updateLatestScript$lambda$15$lambda$14;
                updateLatestScript$lambda$15$lambda$14 = WebAppInterface.updateLatestScript$lambda$15$lambda$14(WebAppInterface.this);
                return updateLatestScript$lambda$15$lambda$14;
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateLatestScript$lambda$15$lambda$14(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Utils.Companion.openUrl$default(Utils.INSTANCE, this$0.context, Constants.URL_SCRIPT_LATEST_RELEASE, false, 4, null);
        AppCompatActivity appCompatActivity = this$0.context;
        Intrinsics.checkNotNull(appCompatActivity, "null cannot be cast to non-null type com.redphx.betterxc.activity.MainActivity");
        ((MainActivity) appCompatActivity).recreate();
        return Unit.INSTANCE;
    }

    private final void onShowingDialog(boolean showing) {
        this.webView.setShowingDialog(showing);
        if (!this.webView.getIsPlaying()) {
            this.webView.setAllowPullToRefresh(!showing);
        }
        this.context.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.web.WebAppInterface$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                WebAppInterface.onShowingDialog$lambda$16(WebAppInterface.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onShowingDialog$lambda$16(WebAppInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppCompatActivity appCompatActivity = this$0.context;
        Intrinsics.checkNotNull(appCompatActivity, "null cannot be cast to non-null type com.redphx.betterxc.activity.MainActivity");
        MainActivity.updateFabContainerVisibility$default((MainActivity) appCompatActivity, null, 1, null);
    }
}
