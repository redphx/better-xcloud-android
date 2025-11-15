package com.redphx.betterxc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.redphx.betterxc.util.BatteryUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryReceiver.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B?\u00126\u0010\u0002\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R>\u0010\u0002\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/redphx/betterxc/receiver/BatteryReceiver;", "Landroid/content/BroadcastReceiver;", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "level", "", "charging", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class BatteryReceiver extends BroadcastReceiver {
    private final Function2<Integer, Boolean, Unit> callback;

    /* JADX WARN: Multi-variable type inference failed */
    public BatteryReceiver(Function2<? super Integer, ? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        BatteryUtil.INSTANCE.setLevel(intent.getIntExtra("level", 0));
        BatteryUtil.INSTANCE.setCharging(intent.getIntExtra("plugged", 0) != 0);
        this.callback.invoke(Integer.valueOf(BatteryUtil.INSTANCE.getLevel()), Boolean.valueOf(BatteryUtil.INSTANCE.getCharging()));
    }
}
