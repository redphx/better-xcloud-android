package com.redphx.betterxc.hook.proxy;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: IPackageMangerProxy.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J0\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rH\u0096\u0002¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/redphx/betterxc/hook/proxy/IPackageMangerProxy;", "Ljava/lang/reflect/InvocationHandler;", "context", "Landroid/content/Context;", "packageManager", "", "<init>", "(Landroid/content/Context;Ljava/lang/Object;)V", "invoke", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class IPackageMangerProxy implements InvocationHandler {
    private final Context context;
    private final Object packageManager;

    public IPackageMangerProxy(Context context, Object packageManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        this.context = context;
        this.packageManager = packageManager;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) {
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(method, "method");
        if (args == null) {
            return method.invoke(this.packageManager, new Object[0]);
        }
        String name = method.getName();
        if (Intrinsics.areEqual(name, "hasSystemFeature")) {
            if (Intrinsics.areEqual(args[0].toString(), "android.hardware.audio.low_latency")) {
                return true;
            }
        } else if (Intrinsics.areEqual(name, "getComponentEnabledSetting") && StringsKt.contains$default((CharSequence) args[0].toString(), (CharSequence) "devui.DeveloperModeState", false, 2, (Object) null) && PreferenceManager.getDefaultSharedPreferences(this.context).getBoolean("webview_optimize", false)) {
            return 1;
        }
        return method.invoke(this.packageManager, Arrays.copyOf(args, args.length));
    }
}
