package com.redphx.betterxc.hook.proxy;

import android.os.IBinder;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.redphx.betterxc.util.WebViewUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IWebViewUpdateServiceProxy.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J2\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rH\u0096\u0002¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/redphx/betterxc/hook/proxy/IWebViewUpdateServiceProxy;", "Ljava/lang/reflect/InvocationHandler;", "remoteBinder", "", "stubClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Object;Ljava/lang/Class;)V", "invoke", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class IWebViewUpdateServiceProxy implements InvocationHandler {
    private Object remoteBinder;

    public IWebViewUpdateServiceProxy(Object remoteBinder, Class<?> stubClass) {
        Intrinsics.checkNotNullParameter(remoteBinder, "remoteBinder");
        Intrinsics.checkNotNullParameter(stubClass, "stubClass");
        this.remoteBinder = remoteBinder;
        try {
            Object invoke = stubClass.getMethod("asInterface", IBinder.class).invoke(null, this.remoteBinder);
            Intrinsics.checkNotNull(invoke);
            this.remoteBinder = invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Intrinsics.checkNotNullParameter(method, "method");
        if (Intrinsics.areEqual(method.getName(), "waitForAndGetProvider")) {
            Object invoke = method.invoke(this.remoteBinder, new Object[0]);
            Field declaredField = invoke.getClass().getDeclaredField("packageInfo");
            Intrinsics.checkNotNullExpressionValue(declaredField, "getDeclaredField(...)");
            declaredField.setAccessible(true);
            declaredField.set(invoke, WebViewUtil.INSTANCE.getCustomProvider());
            return invoke;
        }
        return method.invoke(this.remoteBinder, new Object[0]);
    }
}
