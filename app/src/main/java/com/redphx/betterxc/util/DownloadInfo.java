package com.redphx.betterxc.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadManagerUtil.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\tHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\tHÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006%"}, d2 = {"Lcom/redphx/betterxc/util/DownloadInfo;", "", "id", "", "uri", "", "localUri", "mediaProviderUri", "bytesDownloaded", "", "totalSize", NotificationCompat.CATEGORY_STATUS, "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V", "getId", "()J", "getUri", "()Ljava/lang/String;", "getLocalUri", "getMediaProviderUri", "getBytesDownloaded", "()I", "getTotalSize", "getStatus", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class DownloadInfo {
    private final int bytesDownloaded;
    private final long id;
    private final String localUri;
    private final String mediaProviderUri;
    private final int status;
    private final int totalSize;
    private final String uri;

    public static /* synthetic */ DownloadInfo copy$default(DownloadInfo downloadInfo, long j, String str, String str2, String str3, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            j = downloadInfo.id;
        }
        long j2 = j;
        if ((i4 & 2) != 0) {
            str = downloadInfo.uri;
        }
        String str4 = str;
        if ((i4 & 4) != 0) {
            str2 = downloadInfo.localUri;
        }
        String str5 = str2;
        if ((i4 & 8) != 0) {
            str3 = downloadInfo.mediaProviderUri;
        }
        String str6 = str3;
        if ((i4 & 16) != 0) {
            i = downloadInfo.bytesDownloaded;
        }
        return downloadInfo.copy(j2, str4, str5, str6, i, (i4 & 32) != 0 ? downloadInfo.totalSize : i2, (i4 & 64) != 0 ? downloadInfo.status : i3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUri() {
        return this.uri;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLocalUri() {
        return this.localUri;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMediaProviderUri() {
        return this.mediaProviderUri;
    }

    /* renamed from: component5, reason: from getter */
    public final int getBytesDownloaded() {
        return this.bytesDownloaded;
    }

    /* renamed from: component6, reason: from getter */
    public final int getTotalSize() {
        return this.totalSize;
    }

    /* renamed from: component7, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final DownloadInfo copy(long id, String uri, String localUri, String mediaProviderUri, int bytesDownloaded, int totalSize, int status) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(localUri, "localUri");
        Intrinsics.checkNotNullParameter(mediaProviderUri, "mediaProviderUri");
        return new DownloadInfo(id, uri, localUri, mediaProviderUri, bytesDownloaded, totalSize, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadInfo)) {
            return false;
        }
        DownloadInfo downloadInfo = (DownloadInfo) other;
        return this.id == downloadInfo.id && Intrinsics.areEqual(this.uri, downloadInfo.uri) && Intrinsics.areEqual(this.localUri, downloadInfo.localUri) && Intrinsics.areEqual(this.mediaProviderUri, downloadInfo.mediaProviderUri) && this.bytesDownloaded == downloadInfo.bytesDownloaded && this.totalSize == downloadInfo.totalSize && this.status == downloadInfo.status;
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + this.uri.hashCode()) * 31) + this.localUri.hashCode()) * 31) + this.mediaProviderUri.hashCode()) * 31) + Integer.hashCode(this.bytesDownloaded)) * 31) + Integer.hashCode(this.totalSize)) * 31) + Integer.hashCode(this.status);
    }

    public String toString() {
        return "DownloadInfo(id=" + this.id + ", uri=" + this.uri + ", localUri=" + this.localUri + ", mediaProviderUri=" + this.mediaProviderUri + ", bytesDownloaded=" + this.bytesDownloaded + ", totalSize=" + this.totalSize + ", status=" + this.status + ')';
    }

    public DownloadInfo(long j, String uri, String localUri, String mediaProviderUri, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(localUri, "localUri");
        Intrinsics.checkNotNullParameter(mediaProviderUri, "mediaProviderUri");
        this.id = j;
        this.uri = uri;
        this.localUri = localUri;
        this.mediaProviderUri = mediaProviderUri;
        this.bytesDownloaded = i;
        this.totalSize = i2;
        this.status = i3;
    }

    public final long getId() {
        return this.id;
    }

    public final String getUri() {
        return this.uri;
    }

    public final String getLocalUri() {
        return this.localUri;
    }

    public final String getMediaProviderUri() {
        return this.mediaProviderUri;
    }

    public final int getBytesDownloaded() {
        return this.bytesDownloaded;
    }

    public final int getTotalSize() {
        return this.totalSize;
    }

    public final int getStatus() {
        return this.status;
    }
}
