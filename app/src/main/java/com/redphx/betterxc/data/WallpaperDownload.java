package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WallpaperDownload.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/redphx/betterxc/data/WallpaperDownload;", "", "type", "", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class WallpaperDownload {
    private final String type;
    private final String url;

    public static /* synthetic */ WallpaperDownload copy$default(WallpaperDownload wallpaperDownload, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wallpaperDownload.type;
        }
        if ((i & 2) != 0) {
            str2 = wallpaperDownload.url;
        }
        return wallpaperDownload.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final WallpaperDownload copy(String type, String url) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(url, "url");
        return new WallpaperDownload(type, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WallpaperDownload)) {
            return false;
        }
        WallpaperDownload wallpaperDownload = (WallpaperDownload) other;
        return Intrinsics.areEqual(this.type, wallpaperDownload.type) && Intrinsics.areEqual(this.url, wallpaperDownload.url);
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + this.url.hashCode();
    }

    public String toString() {
        return "WallpaperDownload(type=" + this.type + ", url=" + this.url + ')';
    }

    public WallpaperDownload(String type, String url) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(url, "url");
        this.type = type;
        this.url = url;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }
}
