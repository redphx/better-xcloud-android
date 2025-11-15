package com.redphx.betterxc.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/BatteryUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class BatteryUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int level = 100;
    private static boolean charging = true;

    /* compiled from: BatteryUtil.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lcom/redphx/betterxc/util/BatteryUtil$Companion;", "", "<init>", "()V", "level", "", "getLevel", "()I", "setLevel", "(I)V", "charging", "", "getCharging", "()Z", "setCharging", "(Z)V", "getStatus", "Lcom/redphx/betterxc/util/BatteryStatus;", "refreshStatus", "", "context", "Landroid/content/Context;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getLevel() {
            return BatteryUtil.level;
        }

        public final void setLevel(int i) {
            BatteryUtil.level = i;
        }

        public final boolean getCharging() {
            return BatteryUtil.charging;
        }

        public final void setCharging(boolean z) {
            BatteryUtil.charging = z;
        }

        public final BatteryStatus getStatus() {
            return new BatteryStatus(getLevel() / 100.0f, getCharging());
        }

        public final void refreshStatus(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            Intrinsics.checkNotNull(registerReceiver);
            setLevel(registerReceiver.getIntExtra("level", 0));
            setCharging(registerReceiver.getIntExtra("plugged", 0) != 0);
        }
    }
}
