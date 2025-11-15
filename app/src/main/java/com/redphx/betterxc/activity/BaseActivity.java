package com.redphx.betterxc.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;

/* compiled from: BaseActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0002J\b\u0010\u0013\u001a\u00020\tH\u0002J\u0012\u0010\u0014\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/redphx/betterxc/activity/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "hasDisplayCutout", "", "immersiveMode", "Lcom/redphx/betterxc/activity/BaseActivity$ImmersiveMode;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onAttachedToWindow", "onWindowFocusChanged", "hasFocus", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "disableImmersiveMode", "enableImmersiveMode", "updateImmersiveMode", "force", "ImmersiveMode", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class BaseActivity extends AppCompatActivity {
    private boolean hasDisplayCutout;
    private ImmersiveMode immersiveMode = ImmersiveMode.AUTO;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: BaseActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/redphx/betterxc/activity/BaseActivity$ImmersiveMode;", "", "<init>", "(Ljava/lang/String;I)V", "AUTO", "ENABLE", "DISABLE", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class ImmersiveMode {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ImmersiveMode[] $VALUES;
        public static final ImmersiveMode AUTO = new ImmersiveMode("AUTO", 0);
        public static final ImmersiveMode ENABLE = new ImmersiveMode("ENABLE", 1);
        public static final ImmersiveMode DISABLE = new ImmersiveMode("DISABLE", 2);

        private static final /* synthetic */ ImmersiveMode[] $values() {
            return new ImmersiveMode[]{AUTO, ENABLE, DISABLE};
        }

        public static EnumEntries<ImmersiveMode> getEntries() {
            return $ENTRIES;
        }

        private ImmersiveMode(String str, int i) {
        }

        static {
            ImmersiveMode[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static ImmersiveMode valueOf(String str) {
            return (ImmersiveMode) Enum.valueOf(ImmersiveMode.class, str);
        }

        public static ImmersiveMode[] values() {
            return (ImmersiveMode[]) $VALUES.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsControllerCompat.setSystemBarsBehavior(2);
        updateImmersiveMode$default(this, null, 1, null);
        if (Intrinsics.areEqual(PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("force_landscape_mode", ArtifactRepositoryPolicy.UPDATE_POLICY_NEVER), ArtifactRepositoryPolicy.UPDATE_POLICY_ALWAYS)) {
            setRequestedOrientation(6);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT >= 28) {
            this.hasDisplayCutout = getWindow().getDecorView().getRootWindowInsets().getDisplayCutout() != null;
        }
        if (this.hasDisplayCutout || Build.VERSION.SDK_INT < 30) {
            return;
        }
        this.hasDisplayCutout = getWindowManager().getCurrentWindowMetrics().getWindowInsets().getDisplayCutout() != null;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        updateImmersiveMode$default(this, null, 1, null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        updateImmersiveMode$default(this, null, 1, null);
    }

    private final void disableImmersiveMode() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
        if (this.hasDisplayCutout) {
            WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars());
            windowInsetsControllerCompat.show(WindowInsetsCompat.Type.statusBars());
        }
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 0;
            getWindow().setAttributes(attributes);
        }
    }

    private final void enableImmersiveMode() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        if (this.hasDisplayCutout) {
            new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView()).hide(WindowInsetsCompat.Type.systemBars());
        }
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
    }

    public static /* synthetic */ void updateImmersiveMode$default(BaseActivity baseActivity, ImmersiveMode immersiveMode, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateImmersiveMode");
        }
        if ((i & 1) != 0) {
            immersiveMode = null;
        }
        baseActivity.updateImmersiveMode(immersiveMode);
    }

    public final void updateImmersiveMode(ImmersiveMode force) {
        if (force != null) {
            this.immersiveMode = force;
        }
        if (getResources().getConfiguration().orientation == 1) {
            if (this.immersiveMode == ImmersiveMode.DISABLE) {
                enableImmersiveMode();
                return;
            } else {
                disableImmersiveMode();
                return;
            }
        }
        enableImmersiveMode();
    }
}
