package com.redphx.betterxc;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import com.google.android.material.color.DynamicColors;
import com.redphx.betterxc.hook.Hooker;
import com.redphx.betterxc.util.CrashTracker;
import com.redphx.betterxc.util.DeviceInfo;
import com.redphx.betterxc.util.UiUtil;
import com.redphx.betterxc.util.WebViewUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainApplication.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/redphx/betterxc/MainApplication;", "Landroid/app/Application;", "<init>", "()V", "sharedPref", "Landroid/content/SharedPreferences;", "showNvidiaHooksWarning", "", "getShowNvidiaHooksWarning", "()Z", "setShowNvidiaHooksWarning", "(Z)V", "onCreate", "", "setupTheme", "checkShieldHooks", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MainApplication extends Application {
    private SharedPreferences sharedPref;
    private boolean showNvidiaHooksWarning;

    public final boolean getShowNvidiaHooksWarning() {
        return this.showNvidiaHooksWarning;
    }

    public final void setShowNvidiaHooksWarning(boolean z) {
        this.showNvidiaHooksWarning = z;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        DeviceInfo.Companion companion = DeviceInfo.INSTANCE;
        Context baseContext = getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
        companion.init(baseContext);
        WebViewUtil.Companion companion2 = WebViewUtil.INSTANCE;
        Context baseContext2 = getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext2, "getBaseContext(...)");
        companion2.init(baseContext2);
        SharedPreferences sharedPreferences = this.sharedPref;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        boolean z = sharedPreferences.getBoolean("webview_optimize", false);
        try {
            Hooker.Companion companion3 = Hooker.INSTANCE;
            Context baseContext3 = getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext3, "getBaseContext(...)");
            companion3.hookPackageManager(baseContext3);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), 0).show();
            e.printStackTrace();
        }
        if (z) {
            try {
                Hooker.Companion companion4 = Hooker.INSTANCE;
                Context baseContext4 = getBaseContext();
                Intrinsics.checkNotNullExpressionValue(baseContext4, "getBaseContext(...)");
                companion4.hookInstallContentProviders(baseContext4);
            } catch (Exception e2) {
                Toast.makeText(getBaseContext(), e2.getMessage(), 0).show();
                e2.printStackTrace();
            }
        }
        CrashTracker.Companion companion5 = CrashTracker.INSTANCE;
        Context baseContext5 = getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext5, "getBaseContext(...)");
        companion5.init(baseContext5);
        setupTheme();
        checkShieldHooks();
    }

    private final void setupTheme() {
        SharedPreferences sharedPreferences = this.sharedPref;
        SharedPreferences sharedPreferences2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            sharedPreferences = null;
        }
        if (sharedPreferences.getBoolean("ui_material_you", false)) {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }
        UiUtil.Companion companion = UiUtil.INSTANCE;
        SharedPreferences sharedPreferences3 = this.sharedPref;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
        } else {
            sharedPreferences2 = sharedPreferences3;
        }
        String string = sharedPreferences2.getString("theme", "dark");
        Intrinsics.checkNotNull(string);
        companion.setNightMode(string);
    }

    private final void checkShieldHooks() {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo("com.nvidia.shieldtech.hooks", 0);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            this.showNvidiaHooksWarning = applicationInfo.enabled;
        } catch (Exception unused) {
        }
    }
}
