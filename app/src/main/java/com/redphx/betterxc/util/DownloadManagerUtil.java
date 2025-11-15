package com.redphx.betterxc.util;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadManagerUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/DownloadManagerUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DownloadManagerUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: DownloadManagerUtil.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J&\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e¨\u0006\u0011"}, d2 = {"Lcom/redphx/betterxc/util/DownloadManagerUtil$Companion;", "", "<init>", "()V", "getDownloadInfo", "Lcom/redphx/betterxc/util/DownloadInfo;", "context", "Landroid/content/Context;", "downloadId", "", "download", "activity", "Landroid/app/Activity;", "url", "", "fileName", "desc", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadInfo getDownloadInfo(Context context, long downloadId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("download");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
            Cursor query = ((DownloadManager) systemService).query(new DownloadManager.Query().setFilterById(downloadId));
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("uri"));
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = query.getString(query.getColumnIndex("local_uri"));
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String string3 = query.getString(query.getColumnIndex("mediaprovider_uri"));
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            DownloadInfo downloadInfo = new DownloadInfo(downloadId, string, string2, string3, query.getInt(query.getColumnIndex("bytes_so_far")), query.getInt(query.getColumnIndex("total_size")), query.getInt(query.getColumnIndex(NotificationCompat.CATEGORY_STATUS)));
            query.close();
            return downloadInfo;
        }

        public final long download(Activity activity, String url, String fileName, String desc) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(desc, "desc");
            DownloadManager downloadManager = (DownloadManager) activity.getSystemService("download");
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle(fileName);
            request.setDescription(desc);
            request.setNotificationVisibility(1);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
            Intrinsics.checkNotNull(downloadManager);
            return downloadManager.enqueue(request);
        }
    }
}
