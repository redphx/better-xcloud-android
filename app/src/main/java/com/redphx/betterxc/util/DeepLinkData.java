package com.redphx.betterxc.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeepLinkUtil.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/redphx/betterxc/util/DeepLinkData;", "", "host", "Lcom/redphx/betterxc/util/DeepLinkHost;", "data", "", "", "<init>", "(Lcom/redphx/betterxc/util/DeepLinkHost;Ljava/util/List;)V", "getHost", "()Lcom/redphx/betterxc/util/DeepLinkHost;", "getData", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class DeepLinkData {
    private final List<String> data;
    private final DeepLinkHost host;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeepLinkData copy$default(DeepLinkData deepLinkData, DeepLinkHost deepLinkHost, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            deepLinkHost = deepLinkData.host;
        }
        if ((i & 2) != 0) {
            list = deepLinkData.data;
        }
        return deepLinkData.copy(deepLinkHost, list);
    }

    /* renamed from: component1, reason: from getter */
    public final DeepLinkHost getHost() {
        return this.host;
    }

    public final List<String> component2() {
        return this.data;
    }

    public final DeepLinkData copy(DeepLinkHost host, List<String> data) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(data, "data");
        return new DeepLinkData(host, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeepLinkData)) {
            return false;
        }
        DeepLinkData deepLinkData = (DeepLinkData) other;
        return this.host == deepLinkData.host && Intrinsics.areEqual(this.data, deepLinkData.data);
    }

    public int hashCode() {
        return (this.host.hashCode() * 31) + this.data.hashCode();
    }

    public String toString() {
        return "DeepLinkData(host=" + this.host + ", data=" + this.data + ')';
    }

    public DeepLinkData(DeepLinkHost host, List<String> data) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(data, "data");
        this.host = host;
        this.data = data;
    }

    public final DeepLinkHost getHost() {
        return this.host;
    }

    public /* synthetic */ DeepLinkData(DeepLinkHost deepLinkHost, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deepLinkHost, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<String> getData() {
        return this.data;
    }
}
