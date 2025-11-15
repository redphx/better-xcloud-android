package com.redphx.betterxc.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.redphx.betterxc.R;
import com.redphx.betterxc.util.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WebViewBottomSheet.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001f B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0016\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0007J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u0006\u0010\u001e\u001a\u00020\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/redphx/betterxc/web/WebViewBottomSheet;", "Landroid/widget/FrameLayout;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "(Landroidx/appcompat/app/AppCompatActivity;)V", "webViewUrl", "", "actionBar", "Lcom/google/android/material/appbar/MaterialToolbar;", "progressBar", "Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "layWebView", "webView", "Lcom/redphx/betterxc/web/CoreWebView;", "bottomSheetDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "homeUrl", "actionBack", "Landroid/view/MenuItem;", "actionForward", "updateMenuItems", "", "setupBottomSheetBehaviour", "loadUrl", "url", "override", "", "setHomeUrl", "dismiss", "destroy", "MyWebViewClient", "MyWebChromeClient", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebViewBottomSheet extends FrameLayout {
    private MenuItem actionBack;
    private MaterialToolbar actionBar;
    private MenuItem actionForward;
    private final BottomSheetDialog bottomSheetDialog;
    private String homeUrl;
    private FrameLayout layWebView;
    private LinearProgressIndicator progressBar;
    private CoreWebView webView;
    private String webViewUrl;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public WebViewBottomSheet(androidx.appcompat.app.AppCompatActivity r7) {
        /*
            r6 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = r7
            android.content.Context r0 = (android.content.Context) r0
            r6.<init>(r0)
            java.lang.String r1 = ""
            r6.webViewUrl = r1
            com.google.android.material.bottomsheet.BottomSheetDialog r2 = new com.google.android.material.bottomsheet.BottomSheetDialog
            r2.<init>(r0)
            r6.bottomSheetDialog = r2
            r6.homeUrl = r1
            int r1 = com.redphx.betterxc.R.layout.bottom_sheet_webview
            r3 = r6
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            android.view.View r1 = android.widget.FrameLayout.inflate(r0, r1, r3)
            int r3 = com.redphx.betterxc.R.id.lay_webview
            android.view.View r3 = r1.findViewById(r3)
            android.widget.FrameLayout r3 = (android.widget.FrameLayout) r3
            r6.layWebView = r3
            int r3 = com.redphx.betterxc.R.id.progress_bar
            android.view.View r3 = r1.findViewById(r3)
            com.google.android.material.progressindicator.LinearProgressIndicator r3 = (com.google.android.material.progressindicator.LinearProgressIndicator) r3
            r6.progressBar = r3
            com.redphx.betterxc.web.CoreWebView r3 = new com.redphx.betterxc.web.CoreWebView
            r4 = 0
            r5 = 2
            r3.<init>(r0, r4, r5, r4)
            r6.webView = r3
            r3.setup()
            com.redphx.betterxc.web.WebViewBottomSheet$MyWebViewClient r0 = new com.redphx.betterxc.web.WebViewBottomSheet$MyWebViewClient
            r0.<init>()
            android.webkit.WebViewClient r0 = (android.webkit.WebViewClient) r0
            r3.setWebViewClient(r0)
            com.redphx.betterxc.web.WebViewBottomSheet$MyWebChromeClient r0 = new com.redphx.betterxc.web.WebViewBottomSheet$MyWebChromeClient
            r0.<init>()
            android.webkit.WebChromeClient r0 = (android.webkit.WebChromeClient) r0
            r3.setWebChromeClient(r0)
            r6.setupBottomSheetBehaviour()
            android.widget.FrameLayout r0 = r6.layWebView
            com.redphx.betterxc.web.CoreWebView r3 = r6.webView
            android.view.View r3 = (android.view.View) r3
            r0.addView(r3)
            r0 = r6
            android.view.View r0 = (android.view.View) r0
            r2.setContentView(r0)
            com.redphx.betterxc.web.WebViewBottomSheet$bottomSheetBackCallback$1 r0 = new com.redphx.betterxc.web.WebViewBottomSheet$bottomSheetBackCallback$1
            r0.<init>()
            androidx.activity.OnBackPressedDispatcher r2 = r2.getOnBackPressedDispatcher()
            androidx.lifecycle.LifecycleOwner r7 = (androidx.lifecycle.LifecycleOwner) r7
            androidx.activity.OnBackPressedCallback r0 = (androidx.activity.OnBackPressedCallback) r0
            r2.addCallback(r7, r0)
            int r7 = com.redphx.betterxc.R.id.toolbar
            android.view.View r7 = r1.findViewById(r7)
            com.google.android.material.appbar.MaterialToolbar r7 = (com.google.android.material.appbar.MaterialToolbar) r7
            r6.actionBar = r7
            int r0 = com.redphx.betterxc.R.menu.menu_bottom_sheet_webview
            r7.inflateMenu(r0)
            com.google.android.material.appbar.MaterialToolbar r7 = r6.actionBar
            android.view.Menu r7 = r7.getMenu()
            int r0 = com.redphx.betterxc.R.id.action_back
            android.view.MenuItem r7 = r7.findItem(r0)
            r6.actionBack = r7
            com.google.android.material.appbar.MaterialToolbar r7 = r6.actionBar
            android.view.Menu r7 = r7.getMenu()
            int r0 = com.redphx.betterxc.R.id.action_forward
            android.view.MenuItem r7 = r7.findItem(r0)
            r6.actionForward = r7
            com.google.android.material.appbar.MaterialToolbar r7 = r6.actionBar
            com.redphx.betterxc.web.WebViewBottomSheet$$ExternalSyntheticLambda0 r0 = new com.redphx.betterxc.web.WebViewBottomSheet$$ExternalSyntheticLambda0
            r0.<init>()
            r7.setOnMenuItemClickListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redphx.betterxc.web.WebViewBottomSheet.<init>(androidx.appcompat.app.AppCompatActivity):void");
    }

    /* compiled from: WebViewBottomSheet.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J&\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J$\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016¨\u0006\u0013"}, d2 = {"Lcom/redphx/betterxc/web/WebViewBottomSheet$MyWebViewClient;", "Landroid/webkit/WebViewClient;", "<init>", "(Lcom/redphx/betterxc/web/WebViewBottomSheet;)V", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "onPageStarted", "", "url", "", "favicon", "Landroid/graphics/Bitmap;", "onPageFinished", "doUpdateVisitedHistory", "isReload", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class MyWebViewClient extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }

        public MyWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            WebViewBottomSheet.this.progressBar.setIndeterminate(true);
            WebViewBottomSheet.this.progressBar.setVisibility(0);
            WebViewBottomSheet.this.updateMenuItems();
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            WebViewBottomSheet.this.progressBar.setVisibility(8);
            WebViewBottomSheet.this.progressBar.setProgressCompat(0, false);
            WebViewBottomSheet.this.updateMenuItems();
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
            if (url == null) {
                return;
            }
            if (StringsKt.startsWith$default(url, "https://www.trueachievements.com", false, 2, (Object) null)) {
                url = url.substring(32);
                Intrinsics.checkNotNullExpressionValue(url, "substring(...)");
            }
            if (Intrinsics.areEqual(url, "/") || Intrinsics.areEqual(url, "")) {
                url = "trueachievements.com";
            }
            WebViewBottomSheet.this.webViewUrl = url;
            WebViewBottomSheet.this.updateMenuItems();
        }
    }

    /* compiled from: WebViewBottomSheet.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"Lcom/redphx/betterxc/web/WebViewBottomSheet$MyWebChromeClient;", "Landroid/webkit/WebChromeClient;", "<init>", "(Lcom/redphx/betterxc/web/WebViewBottomSheet;)V", "onReceivedTitle", "", "view", "Landroid/webkit/WebView;", "title", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class MyWebChromeClient extends WebChromeClient {
        public MyWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            MaterialToolbar materialToolbar = WebViewBottomSheet.this.actionBar;
            WebViewBottomSheet webViewBottomSheet = WebViewBottomSheet.this;
            materialToolbar.setTitle(title);
            materialToolbar.setSubtitle(webViewBottomSheet.webViewUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$1(WebViewBottomSheet this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_home) {
            this$0.webView.loadUrl(this$0.homeUrl);
            return true;
        }
        if (itemId == R.id.action_external) {
            Utils.Companion companion = Utils.INSTANCE;
            Context context = this$0.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            String url = this$0.webView.getUrl();
            Intrinsics.checkNotNull(url);
            companion.openUrl(context, url, true);
            return true;
        }
        if (itemId == R.id.action_back) {
            if (this$0.webView.canGoBack()) {
                this$0.webView.goBack();
            }
            return true;
        }
        if (itemId == R.id.action_forward) {
            if (this$0.webView.canGoForward()) {
                this$0.webView.goForward();
            }
            return true;
        }
        if (itemId != R.id.action_refresh) {
            return false;
        }
        this$0.webView.reload();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMenuItems() {
        this.actionBack.setEnabled(this.webView.canGoBack());
        Drawable icon = this.actionBack.getIcon();
        Intrinsics.checkNotNull(icon);
        icon.setAlpha(this.actionBack.isEnabled() ? 255 : 100);
        this.actionForward.setEnabled(this.webView.canGoForward());
        Drawable icon2 = this.actionForward.getIcon();
        Intrinsics.checkNotNull(icon2);
        icon2.setAlpha(this.actionForward.isEnabled() ? 255 : 100);
    }

    private final void setupBottomSheetBehaviour() {
        final BottomSheetBehavior<FrameLayout> behavior = this.bottomSheetDialog.getBehavior();
        Intrinsics.checkNotNullExpressionValue(behavior, "getBehavior(...)");
        behavior.setFitToContents(false);
        behavior.setSaveFlags(0);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.redphx.betterxc.web.WebViewBottomSheet$setupBottomSheetBehaviour$bottomSheetCallback$1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
                Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                BottomSheetDialog bottomSheetDialog;
                CoreWebView coreWebView;
                Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
                if (newState == 1) {
                    coreWebView = WebViewBottomSheet.this.webView;
                    if (coreWebView.getScrollY() > 0) {
                        behavior.setState(3);
                        return;
                    }
                }
                if (newState == 5) {
                    bottomSheetDialog = WebViewBottomSheet.this.bottomSheetDialog;
                    bottomSheetDialog.dismiss();
                }
            }
        });
    }

    public final void loadUrl(String url, boolean override) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.bottomSheetDialog.getBehavior().setState(3);
        this.bottomSheetDialog.show();
        if (override || this.webView.getUrl() == null) {
            this.webView.loadUrl(url);
        } else {
            this.webView.postInvalidateDelayed(100L);
        }
    }

    public final void setHomeUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.homeUrl = url;
    }

    private final void dismiss() {
        this.bottomSheetDialog.dismiss();
    }

    public final void destroy() {
        dismiss();
        this.webView.destroy();
        this.layWebView.removeAllViews();
    }
}
