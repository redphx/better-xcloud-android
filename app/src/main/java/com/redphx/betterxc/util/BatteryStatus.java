package com.redphx.betterxc.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: BatteryUtil.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/redphx/betterxc/util/BatteryStatus;", "", "level", "", "charging", "", "<init>", "(FZ)V", "getLevel", "()F", "getCharging", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class BatteryStatus {
    private final boolean charging;
    private final float level;

    public static /* synthetic */ BatteryStatus copy$default(BatteryStatus batteryStatus, float f, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            f = batteryStatus.level;
        }
        if ((i & 2) != 0) {
            z = batteryStatus.charging;
        }
        return batteryStatus.copy(f, z);
    }

    /* renamed from: component1, reason: from getter */
    public final float getLevel() {
        return this.level;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getCharging() {
        return this.charging;
    }

    public final BatteryStatus copy(float level, boolean charging) {
        return new BatteryStatus(level, charging);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BatteryStatus)) {
            return false;
        }
        BatteryStatus batteryStatus = (BatteryStatus) other;
        return Float.compare(this.level, batteryStatus.level) == 0 && this.charging == batteryStatus.charging;
    }

    public int hashCode() {
        return (Float.hashCode(this.level) * 31) + Boolean.hashCode(this.charging);
    }

    public String toString() {
        return "BatteryStatus(level=" + this.level + ", charging=" + this.charging + ')';
    }

    public BatteryStatus(float f, boolean z) {
        this.level = f;
        this.charging = z;
    }

    public final float getLevel() {
        return this.level;
    }

    public final boolean getCharging() {
        return this.charging;
    }
}
