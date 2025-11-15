package com.redphx.betterxc.hook.proxy;

import android.os.IBinder;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IServiceManagerProxy.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\fH\u0097\u0002¢\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/redphx/betterxc/hook/proxy/IServiceManagerProxy;", "Ljava/lang/reflect/InvocationHandler;", "remoteBinder", "Landroid/os/IBinder;", "<init>", "(Landroid/os/IBinder;)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class IServiceManagerProxy implements InvocationHandler {
    private final IBinder remoteBinder;

    public IServiceManagerProxy(IBinder remoteBinder) {
        Intrinsics.checkNotNullParameter(remoteBinder, "remoteBinder");
        this.remoteBinder = remoteBinder;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(args, "args");
        if (Intrinsics.areEqual(method.getName(), "queryLocalInterface") && Intrinsics.areEqual(args[0].toString(), "android.webkit.IWebViewUpdateService")) {
            Class<?> cls = Class.forName("android.webkit.IWebViewUpdateService");
            Class<?> cls2 = Class.forName("android.webkit.IWebViewUpdateService$Stub");
            ClassLoader classLoader = this.remoteBinder.getClass().getClassLoader();
            Class[] clsArr = {cls};
            IBinder iBinder = this.remoteBinder;
            Intrinsics.checkNotNull(cls2);
            return Proxy.newProxyInstance(classLoader, clsArr, new IWebViewUpdateServiceProxy(iBinder, cls2));
        }
        return method.invoke(this.remoteBinder, Arrays.copyOf(args, args.length));
    }
}
