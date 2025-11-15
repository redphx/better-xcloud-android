package com.redphx.betterxc.shortcut;

import android.content.Context;
import android.media.AudioManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SoundShortcut.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/shortcut/SoundShortcut;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SoundShortcut {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: SoundShortcut.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"}, d2 = {"Lcom/redphx/betterxc/shortcut/SoundShortcut$Companion;", "", "<init>", "()V", "toggleMute", "", "context", "Landroid/content/Context;", "increaseVolume", "decreaseVolume", "adjustVolume", "direction", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void toggleMute(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            AudioManager audioManager = (AudioManager) ContextCompat.getSystemService(context, AudioManager.class);
            if (audioManager != null) {
                audioManager.adjustStreamVolume(3, TypedValues.TYPE_TARGET, 1);
            }
        }

        public final void increaseVolume(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            adjustVolume(context, 1);
        }

        public final void decreaseVolume(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            adjustVolume(context, -1);
        }

        private final void adjustVolume(Context context, int direction) {
            AudioManager audioManager = (AudioManager) ContextCompat.getSystemService(context, AudioManager.class);
            if (audioManager != null) {
                audioManager.adjustStreamVolume(3, direction, 5);
            }
        }
    }
}
