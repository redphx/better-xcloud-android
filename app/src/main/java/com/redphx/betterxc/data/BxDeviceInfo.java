package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BxFlags.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/redphx/betterxc/data/BxDeviceInfo;", "", "deviceType", "", "androidInfo", "Lcom/redphx/betterxc/data/BxAndroidInfo;", "<init>", "(Ljava/lang/String;Lcom/redphx/betterxc/data/BxAndroidInfo;)V", "getDeviceType", "()Ljava/lang/String;", "getAndroidInfo", "()Lcom/redphx/betterxc/data/BxAndroidInfo;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class BxDeviceInfo {
    private final BxAndroidInfo androidInfo;
    private final String deviceType;

    public static /* synthetic */ BxDeviceInfo copy$default(BxDeviceInfo bxDeviceInfo, String str, BxAndroidInfo bxAndroidInfo, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bxDeviceInfo.deviceType;
        }
        if ((i & 2) != 0) {
            bxAndroidInfo = bxDeviceInfo.androidInfo;
        }
        return bxDeviceInfo.copy(str, bxAndroidInfo);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component2, reason: from getter */
    public final BxAndroidInfo getAndroidInfo() {
        return this.androidInfo;
    }

    public final BxDeviceInfo copy(@Json(name = "deviceType") String deviceType, @Json(name = "androidInfo") BxAndroidInfo androidInfo) {
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(androidInfo, "androidInfo");
        return new BxDeviceInfo(deviceType, androidInfo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BxDeviceInfo)) {
            return false;
        }
        BxDeviceInfo bxDeviceInfo = (BxDeviceInfo) other;
        return Intrinsics.areEqual(this.deviceType, bxDeviceInfo.deviceType) && Intrinsics.areEqual(this.androidInfo, bxDeviceInfo.androidInfo);
    }

    public int hashCode() {
        return (this.deviceType.hashCode() * 31) + this.androidInfo.hashCode();
    }

    public String toString() {
        return "BxDeviceInfo(deviceType=" + this.deviceType + ", androidInfo=" + this.androidInfo + ')';
    }

    public BxDeviceInfo(@Json(name = "deviceType") String deviceType, @Json(name = "androidInfo") BxAndroidInfo androidInfo) {
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(androidInfo, "androidInfo");
        this.deviceType = deviceType;
        this.androidInfo = androidInfo;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final BxAndroidInfo getAndroidInfo() {
        return this.androidInfo;
    }
}
