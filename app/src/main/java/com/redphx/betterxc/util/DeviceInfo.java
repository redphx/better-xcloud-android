package com.redphx.betterxc.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceInfo.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/DeviceInfo;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DeviceInfo {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isAndroidTv;
    private static boolean isFireTv;
    private static boolean isLogitechGCloud;

    /* compiled from: DeviceInfo.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\u001e\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/redphx/betterxc/util/DeviceInfo$Companion;", "", "<init>", "()V", "value", "", "isAndroidTv", "()Z", "isFireTv", "isLogitechGCloud", "init", "", "context", "Landroid/content/Context;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isAndroidTv() {
            return DeviceInfo.isAndroidTv;
        }

        public final boolean isFireTv() {
            return DeviceInfo.isFireTv;
        }

        public final boolean isLogitechGCloud() {
            return DeviceInfo.isLogitechGCloud;
        }

        public final void init(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            PackageManager packageManager = context.getPackageManager();
            DeviceInfo.isAndroidTv = packageManager.hasSystemFeature("android.software.leanback");
            DeviceInfo.isFireTv = packageManager.hasSystemFeature("amazon.hardware.fire_tv");
            DeviceInfo.isLogitechGCloud = Intrinsics.areEqual(Build.MODEL, "GR0006");
        }
    }
}
