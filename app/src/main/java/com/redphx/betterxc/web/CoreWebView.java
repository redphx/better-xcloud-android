package com.redphx.betterxc.web;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CoreWebView.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 #2\u00020\u0001:\u0001#B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0017J(\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000fH\u0014J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\"H\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/redphx/betterxc/web/CoreWebView;", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "allowPullToRefresh", "", "getAllowPullToRefresh", "()Z", "setAllowPullToRefresh", "(Z)V", "currentScrollX", "", "currentScrollY", "startScrollY", "getStartScrollY", "()I", "setStartScrollY", "(I)V", "motionLifted", "setup", "", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onScrollChanged", "x", "y", "oldX", "oldY", "dispatchKeyEvent", "Landroid/view/KeyEvent;", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class CoreWebView extends WebView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Integer[] DPAD_KEYS = {19, 20, 21, 22};
    private boolean allowPullToRefresh;
    private int currentScrollX;
    private int currentScrollY;
    private boolean motionLifted;
    private int startScrollY;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.allowPullToRefresh = true;
    }

    public /* synthetic */ CoreWebView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final boolean getAllowPullToRefresh() {
        return this.allowPullToRefresh;
    }

    public final void setAllowPullToRefresh(boolean z) {
        this.allowPullToRefresh = z;
    }

    public final int getStartScrollY() {
        return this.startScrollY;
    }

    public final void setStartScrollY(int i) {
        this.startScrollY = i;
    }

    public final void setup() {
        setDefaultFocusHighlightEnabled(false);
        setBackgroundColor(0);
        setLayerType(2, null);
        setRendererPriorityPolicy(2, false);
        WebView.setWebContentsDebuggingEnabled(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("webview_debuggable", false));
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setMixedContentMode(0);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(false);
        settings.setSupportZoom(false);
        settings.setDisabledActionModeMenuItems(7);
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Integer valueOf = event != null ? Integer.valueOf(event.getAction()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            this.motionLifted = false;
            this.startScrollY = this.currentScrollY;
            getHandler().removeCallbacksAndMessages(null);
        } else if (valueOf != null && valueOf.intValue() == 1) {
            this.motionLifted = true;
            this.startScrollY = this.currentScrollY;
        }
        return super.onTouchEvent(event);
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        this.currentScrollX = x;
        this.currentScrollY = y;
        if (this.motionLifted) {
            getHandler().removeCallbacksAndMessages(null);
            getHandler().postDelayed(new Runnable() { // from class: com.redphx.betterxc.web.CoreWebView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CoreWebView.onScrollChanged$lambda$1(CoreWebView.this);
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onScrollChanged$lambda$1(CoreWebView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startScrollY = this$0.currentScrollY;
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getSource() == 257 && ArraysKt.contains(DPAD_KEYS, Integer.valueOf(event.getKeyCode()))) {
            return super.dispatchKeyEvent(new KeyEvent(event.getAction(), event.getKeyCode()));
        }
        return super.dispatchKeyEvent(event);
    }

    /* compiled from: CoreWebView.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/redphx/betterxc/web/CoreWebView$Companion;", "", "<init>", "()V", "DPAD_KEYS", "", "", "getDPAD_KEYS", "()[Ljava/lang/Integer;", "[Ljava/lang/Integer;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Integer[] getDPAD_KEYS() {
            return CoreWebView.DPAD_KEYS;
        }
    }
}
