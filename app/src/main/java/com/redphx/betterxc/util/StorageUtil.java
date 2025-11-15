package com.redphx.betterxc.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StorageUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/StorageUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class StorageUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: StorageUtil.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¨\u0006\u0010"}, d2 = {"Lcom/redphx/betterxc/util/StorageUtil$Companion;", "", "<init>", "()V", "useNewStorageApi", "", "isPermissionGranted", "context", "Landroid/content/Context;", "requestPermission", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "activityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final boolean useNewStorageApi() {
            if (DeviceInfo.INSTANCE.isFireTv()) {
                return Build.VERSION.SDK_INT >= 30;
            }
            if (DeviceInfo.INSTANCE.isAndroidTv()) {
                if (Build.VERSION.SDK_INT >= 33) {
                    return true;
                }
            } else if (Build.VERSION.SDK_INT >= 30) {
                return true;
            }
            return false;
        }

        public final boolean isPermissionGranted(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (useNewStorageApi()) {
                return Environment.isExternalStorageManager();
            }
            return ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        }

        public final void requestPermission(AppCompatActivity activity, ActivityResultLauncher<String> activityLauncher) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(activityLauncher, "activityLauncher");
            if (useNewStorageApi()) {
                try {
                    activity.startActivity(new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.parse("package:com.redphx.betterxc")));
                    return;
                } catch (Exception unused) {
                    activity.startActivity(new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION"));
                    return;
                }
            }
            activityLauncher.launch("android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }
}
