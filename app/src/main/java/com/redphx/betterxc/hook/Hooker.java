package com.redphx.betterxc.hook;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.IBinder;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.redphx.betterxc.BuildConfig;
import com.redphx.betterxc.hook.proxy.IPackageMangerProxy;
import com.redphx.betterxc.hook.proxy.IServiceManagerProxy;
import com.redphx.betterxc.util.WebViewUtil;
import com.redphx.betterxc.web.DeveloperModeContentProvider;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: Hooker.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/hook/Hooker;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class Hooker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String WEBVIEW_UPDATE_SERVICE_NAME = "webviewupdate";

    /* compiled from: Hooker.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0005H\u0002J\b\u0010\u000e\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/redphx/betterxc/hook/Hooker$Companion;", "", "<init>", "()V", "WEBVIEW_UPDATE_SERVICE_NAME", "", "hookPackageManager", "", "context", "Landroid/content/Context;", "hookInstallContentProviders", "createProviderInfo", "Landroid/content/pm/ProviderInfo;", "authority", "hookServiceManagerService", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void hookPackageManager(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object invoke = cls.getDeclaredMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("sPackageManager");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(invoke);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Any");
                Class<?> cls2 = Class.forName("android.content.pm.IPackageManager");
                Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new IPackageMangerProxy(context, obj));
                declaredField.set(invoke, newProxyInstance);
                PackageManager packageManager = context.getPackageManager();
                Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
                Field declaredField2 = packageManager.getClass().getDeclaredField("mPM");
                declaredField2.setAccessible(true);
                declaredField2.set(packageManager, newProxyInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public final void hookInstallContentProviders(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Field declaredField = cls.getDeclaredField("sCurrentActivityThread");
                Intrinsics.checkNotNullExpressionValue(declaredField, "getDeclaredField(...)");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(null);
                List listOf = CollectionsKt.listOf(createProviderInfo(context, WebViewUtil.INSTANCE.getCurrentProvider().packageName + ".DeveloperModeContentProvider"));
                Method declaredMethod = cls.getDeclaredMethod("installContentProviders", Context.class, List.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(obj, context, listOf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private final ProviderInfo createProviderInfo(Context context, String authority) {
            ProviderInfo providerInfo = new ProviderInfo();
            providerInfo.name = Reflection.getOrCreateKotlinClass(DeveloperModeContentProvider.class).getQualifiedName();
            providerInfo.packageName = BuildConfig.APPLICATION_ID;
            providerInfo.applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            providerInfo.authority = authority;
            return providerInfo;
        }

        public final void hookServiceManagerService() {
            try {
                Class<?> cls = Class.forName("android.os.ServiceManager");
                Object invoke = cls.getMethod("getService", String.class).invoke(null, Hooker.WEBVIEW_UPDATE_SERVICE_NAME);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.os.IBinder");
                Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{IBinder.class}, new IServiceManagerProxy((IBinder) invoke));
                Field declaredField = cls.getDeclaredField("sCache");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(null);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any>");
                TypeIntrinsics.asMutableMap(obj).put(Hooker.WEBVIEW_UPDATE_SERVICE_NAME, newProxyInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
