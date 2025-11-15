package com.redphx.betterxc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.redphx.betterxc.util.DownloadInfo;
import com.redphx.betterxc.util.DownloadManagerUtil;
import com.redphx.betterxc.util.Utils;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DownloadCompletedReceiver.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/redphx/betterxc/receiver/DownloadCompletedReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "validateDownload", "", "downloadInfo", "Lcom/redphx/betterxc/util/DownloadInfo;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DownloadCompletedReceiver extends BroadcastReceiver {
    private final boolean validateDownload(DownloadInfo downloadInfo) {
        String uri = downloadInfo.getUri();
        if (StringsKt.endsWith$default(uri, ".apk", false, 2, (Object) null)) {
            return StringsKt.startsWith$default(uri, "https://github.com/redphx/better-xcloud-android/", false, 2, (Object) null) || StringsKt.startsWith$default(uri, "https://github.com/Alex313031/Thorium-Android/", false, 2, (Object) null) || StringsKt.startsWith$default(uri, "https://gitlab.com/divested-mobile/mulch/", false, 2, (Object) null);
        }
        return false;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        DownloadInfo downloadInfo = DownloadManagerUtil.INSTANCE.getDownloadInfo(context, intent.getLongExtra("extra_download_id", -1L));
        if (downloadInfo == null || downloadInfo.getStatus() != 8) {
            str = "Could not download file";
        } else if (!validateDownload(downloadInfo)) {
            str = "";
        } else {
            String path = Uri.parse(downloadInfo.getLocalUri()).getPath();
            Intrinsics.checkNotNull(path);
            String absolutePath = new File(path).getAbsolutePath();
            Utils.Companion companion = Utils.INSTANCE;
            Intrinsics.checkNotNull(absolutePath);
            companion.installApk(context, absolutePath);
            str = "Download completed";
        }
        String str2 = str;
        if (str2.length() > 0) {
            Toast.makeText(context, str2, 0).show();
        }
    }
}
