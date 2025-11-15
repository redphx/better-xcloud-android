package com.redphx.betterxc.web;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.redphx.betterxc.ws.PointerProviderServer;
import java.net.InetSocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: XcloudWebView.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010!\u001a\u00020\u000bH\u0016J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u000bH\u0016J\u0006\u0010(\u001a\u00020\u001cJ\b\u0010)\u001a\u00020&H\u0002J\b\u0010*\u001a\u00020&H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006+"}, d2 = {"Lcom/redphx/betterxc/web/XcloudWebView;", "Lcom/redphx/betterxc/web/CoreWebView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "pointerProviderServer", "Lcom/redphx/betterxc/ws/PointerProviderServer;", "allowBack", "", "getAllowBack", "()Z", "setAllowBack", "(Z)V", "isPlaying", "setPlaying", "isShowingDialog", "setShowingDialog", "initialBrightness", "", "getInitialBrightness", "()Ljava/lang/Float;", "setInitialBrightness", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "currentScrollY", "", "getCurrentScrollY", "()I", "setCurrentScrollY", "(I)V", "canGoBack", "onCapturedPointerEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onPointerCaptureChange", "", "hasCapture", "startPointerServer", "stopPointerServer", "destroy", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class XcloudWebView extends CoreWebView {
    private boolean allowBack;
    private int currentScrollY;
    private Float initialBrightness;
    private boolean isPlaying;
    private boolean isShowingDialog;
    private PointerProviderServer pointerProviderServer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public XcloudWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.allowBack = true;
    }

    public /* synthetic */ XcloudWebView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final boolean getAllowBack() {
        return this.allowBack;
    }

    public final void setAllowBack(boolean z) {
        this.allowBack = z;
    }

    /* renamed from: isPlaying, reason: from getter */
    public final boolean getIsPlaying() {
        return this.isPlaying;
    }

    public final void setPlaying(boolean z) {
        this.isPlaying = z;
    }

    /* renamed from: isShowingDialog, reason: from getter */
    public final boolean getIsShowingDialog() {
        return this.isShowingDialog;
    }

    public final void setShowingDialog(boolean z) {
        this.isShowingDialog = z;
    }

    public final Float getInitialBrightness() {
        return this.initialBrightness;
    }

    public final void setInitialBrightness(Float f) {
        this.initialBrightness = f;
    }

    public final int getCurrentScrollY() {
        return this.currentScrollY;
    }

    public final void setCurrentScrollY(int i) {
        this.currentScrollY = i;
    }

    @Override // android.webkit.WebView
    public boolean canGoBack() {
        if (this.allowBack) {
            return super.canGoBack();
        }
        return false;
    }

    @Override // android.view.View
    public boolean onCapturedPointerEvent(MotionEvent event) {
        byte[] motionEventToMessage;
        Intrinsics.checkNotNullParameter(event, "event");
        PointerProviderServer pointerProviderServer = this.pointerProviderServer;
        if (pointerProviderServer == null || (motionEventToMessage = pointerProviderServer.motionEventToMessage(event)) == null) {
            return true;
        }
        pointerProviderServer.broadcast(motionEventToMessage);
        return true;
    }

    @Override // android.view.View
    public void onPointerCaptureChange(boolean hasCapture) {
        PointerProviderServer pointerProviderServer = this.pointerProviderServer;
        if (pointerProviderServer != null) {
            pointerProviderServer.broadcast(pointerProviderServer.pointerCaptureChangeMessage(hasCapture));
        }
        super.onPointerCaptureChange(hasCapture);
    }

    public final int startPointerServer() {
        PointerProviderServer pointerProviderServer = this.pointerProviderServer;
        if (pointerProviderServer != null) {
            return pointerProviderServer.getPort();
        }
        PointerProviderServer pointerProviderServer2 = new PointerProviderServer(new InetSocketAddress(PointerProviderServer.INSTANCE.getPORT()));
        this.pointerProviderServer = pointerProviderServer2;
        pointerProviderServer2.setTcpNoDelay(true);
        pointerProviderServer2.setReuseAddr(true);
        pointerProviderServer2.start();
        return pointerProviderServer2.getPort();
    }

    private final void stopPointerServer() {
        try {
            PointerProviderServer pointerProviderServer = this.pointerProviderServer;
            if (pointerProviderServer != null) {
                pointerProviderServer.stop();
            }
        } catch (Exception unused) {
        }
        this.pointerProviderServer = null;
    }

    @Override // android.webkit.WebView
    public void destroy() {
        stopPointerServer();
        this.pointerProviderServer = null;
        super.destroy();
    }
}
