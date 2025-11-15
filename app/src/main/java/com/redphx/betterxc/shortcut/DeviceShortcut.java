package com.redphx.betterxc.shortcut;

import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceShortcut.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/shortcut/DeviceShortcut;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DeviceShortcut {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int DIRECTION_LOWER = -1;
    private static final int DIRECTION_RAISE = 1;

    /* compiled from: DeviceShortcut.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0015\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\fJ \u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/redphx/betterxc/shortcut/DeviceShortcut$Companion;", "", "<init>", "()V", "DIRECTION_RAISE", "", "DIRECTION_LOWER", "increaseBrightness", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "amount", "", "decreaseBrightness", "getCurrentBrightness", "(Landroidx/appcompat/app/AppCompatActivity;)Ljava/lang/Float;", "setBrightness", "value", "adjustBrightness", "direction", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void increaseBrightness(AppCompatActivity activity, float amount) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            adjustBrightness(activity, 1, amount);
        }

        public final void decreaseBrightness(AppCompatActivity activity, float amount) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            adjustBrightness(activity, -1, amount);
        }

        public final Float getCurrentBrightness(AppCompatActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
            if (attributes != null) {
                return Float.valueOf(attributes.screenBrightness);
            }
            return null;
        }

        public final void setBrightness(AppCompatActivity activity, float value) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
            if (attributes != null) {
                float f = attributes.screenBrightness;
                attributes.screenBrightness = value;
                Window window2 = activity.getWindow();
                if (window2 != null) {
                    window2.setAttributes(attributes);
                }
            }
        }

        private final void adjustBrightness(AppCompatActivity activity, int direction, float amount) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
            if (attributes != null) {
                float f = attributes.screenBrightness;
                if (f == -1.0f) {
                    f = Settings.System.getInt(activity.getContentResolver(), "screen_brightness") / 100;
                }
                attributes.screenBrightness = Math.min(1.0f, Math.max(0.0f, f + (amount * direction)));
                Window window2 = activity.getWindow();
                if (window2 != null) {
                    window2.setAttributes(attributes);
                }
            }
        }
    }
}
