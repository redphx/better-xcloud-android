package com.redphx.betterxc.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherKt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.redphx.betterxc.R;
import com.redphx.betterxc.activity.ExternalWebPageActivity;
import com.redphx.betterxc.task.UpdateScriptTask;
import com.redphx.betterxc.web.CoreWebView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: ExternalWebPageActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001a2\u00020\u0001:\u0003\u0018\u0019\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0014J\b\u0010\u0017\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/redphx/betterxc/activity/ExternalWebPageActivity;", "Lcom/redphx/betterxc/activity/BaseActivity;", "<init>", "()V", "laySwipeRefresh", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "layWebView", "Landroid/widget/FrameLayout;", "webView", "Lcom/redphx/betterxc/web/CoreWebView;", "progressBar", "Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "webViewUrl", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onDestroy", "setupWebView", "MyWebViewClient", "MyWebChromeClient", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ExternalWebPageActivity extends BaseActivity {
    private static final Regex MAIN_SCRIPT_REGEX = new Regex("^https://github\\.com/redphx/better-xcloud/(?:raw/(?:[^/]+/)*|releases/download/[^/]+/)better-xcloud\\.user\\.js$|^https://raw\\.githubusercontent\\.com/redphx/better-xcloud/(?:[^/]+/)*better-xcloud\\.user\\.js$");
    private SwipeRefreshLayout laySwipeRefresh;
    private FrameLayout layWebView;
    private LinearProgressIndicator progressBar;
    private CoreWebView webView;
    private String webViewUrl = "";

    /* compiled from: ExternalWebPageActivity.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J&\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J$\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016¨\u0006\u0013"}, d2 = {"Lcom/redphx/betterxc/activity/ExternalWebPageActivity$MyWebViewClient;", "Landroid/webkit/WebViewClient;", "<init>", "(Lcom/redphx/betterxc/activity/ExternalWebPageActivity;)V", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "onPageStarted", "", "url", "", "favicon", "Landroid/graphics/Bitmap;", "onPageFinished", "doUpdateVisitedHistory", "isReload", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Intrinsics.checkNotNullParameter(request, "request");
            String uri = request.getUrl().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            if (!ExternalWebPageActivity.MAIN_SCRIPT_REGEX.matches(uri)) {
                return false;
            }
            UpdateScriptTask.Companion companion = UpdateScriptTask.INSTANCE;
            final ExternalWebPageActivity externalWebPageActivity = ExternalWebPageActivity.this;
            companion.run(externalWebPageActivity, uri, new Function0() { // from class: com.redphx.betterxc.activity.ExternalWebPageActivity$MyWebViewClient$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit shouldOverrideUrlLoading$lambda$0;
                    shouldOverrideUrlLoading$lambda$0 = ExternalWebPageActivity.MyWebViewClient.shouldOverrideUrlLoading$lambda$0(ExternalWebPageActivity.this);
                    return shouldOverrideUrlLoading$lambda$0;
                }
            });
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit shouldOverrideUrlLoading$lambda$0(ExternalWebPageActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intent intent = new Intent(this$0.getBaseContext(), (Class<?>) MainActivity.class);
            intent.setFlags(604045312);
            intent.putExtra("recreate", true);
            this$0.startActivity(intent);
            this$0.finish();
            return Unit.INSTANCE;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            SwipeRefreshLayout swipeRefreshLayout = ExternalWebPageActivity.this.laySwipeRefresh;
            LinearProgressIndicator linearProgressIndicator = null;
            if (swipeRefreshLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("laySwipeRefresh");
                swipeRefreshLayout = null;
            }
            swipeRefreshLayout.setRefreshing(false);
            LinearProgressIndicator linearProgressIndicator2 = ExternalWebPageActivity.this.progressBar;
            if (linearProgressIndicator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator2 = null;
            }
            linearProgressIndicator2.setProgress(0);
            LinearProgressIndicator linearProgressIndicator3 = ExternalWebPageActivity.this.progressBar;
            if (linearProgressIndicator3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                linearProgressIndicator = linearProgressIndicator3;
            }
            linearProgressIndicator.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            LinearProgressIndicator linearProgressIndicator = ExternalWebPageActivity.this.progressBar;
            LinearProgressIndicator linearProgressIndicator2 = null;
            if (linearProgressIndicator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                linearProgressIndicator = null;
            }
            linearProgressIndicator.setVisibility(8);
            LinearProgressIndicator linearProgressIndicator3 = ExternalWebPageActivity.this.progressBar;
            if (linearProgressIndicator3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                linearProgressIndicator2 = linearProgressIndicator3;
            }
            linearProgressIndicator2.setProgress(0);
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
            if (url == null) {
                return;
            }
            if (StringsKt.startsWith$default(url, "https://www.xbox.com", false, 2, (Object) null)) {
                url = url.substring(26);
                Intrinsics.checkNotNullExpressionValue(url, "substring(...)");
            }
            ExternalWebPageActivity.this.webViewUrl = StringsKt.substringBefore$default(url, "#", (String) null, 2, (Object) null);
        }
    }

    /* compiled from: ExternalWebPageActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"Lcom/redphx/betterxc/activity/ExternalWebPageActivity$MyWebChromeClient;", "Landroid/webkit/WebChromeClient;", "<init>", "(Lcom/redphx/betterxc/activity/ExternalWebPageActivity;)V", "onReceivedTitle", "", "view", "Landroid/webkit/WebView;", "title", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class MyWebChromeClient extends WebChromeClient {
        public MyWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            ActionBar supportActionBar = ExternalWebPageActivity.this.getSupportActionBar();
            if (supportActionBar != null) {
                ExternalWebPageActivity externalWebPageActivity = ExternalWebPageActivity.this;
                supportActionBar.setTitle(title);
                supportActionBar.setSubtitle(externalWebPageActivity.webViewUrl);
            }
        }
    }

    @Override // com.redphx.betterxc.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_browser);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        String stringExtra = getIntent().getStringExtra("url");
        if (stringExtra == null) {
            stringExtra = "about:blank";
        }
        this.webViewUrl = stringExtra;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.lay_swipe_refresh);
        this.laySwipeRefresh = swipeRefreshLayout;
        SwipeRefreshLayout swipeRefreshLayout2 = null;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("laySwipeRefresh");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.redphx.betterxc.activity.ExternalWebPageActivity$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ExternalWebPageActivity.onCreate$lambda$1(ExternalWebPageActivity.this);
            }
        });
        SwipeRefreshLayout swipeRefreshLayout3 = this.laySwipeRefresh;
        if (swipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("laySwipeRefresh");
        } else {
            swipeRefreshLayout2 = swipeRefreshLayout3;
        }
        swipeRefreshLayout2.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() { // from class: com.redphx.betterxc.activity.ExternalWebPageActivity$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnChildScrollUpCallback
            public final boolean canChildScrollUp(SwipeRefreshLayout swipeRefreshLayout4, View view) {
                boolean onCreate$lambda$2;
                onCreate$lambda$2 = ExternalWebPageActivity.onCreate$lambda$2(ExternalWebPageActivity.this, swipeRefreshLayout4, view);
                return onCreate$lambda$2;
            }
        });
        this.layWebView = (FrameLayout) findViewById(R.id.lay_webview);
        setupWebView();
        OnBackPressedDispatcherKt.addCallback$default(getOnBackPressedDispatcher(), null, false, new Function1() { // from class: com.redphx.betterxc.activity.ExternalWebPageActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$3;
                onCreate$lambda$3 = ExternalWebPageActivity.onCreate$lambda$3(ExternalWebPageActivity.this, (OnBackPressedCallback) obj);
                return onCreate$lambda$3;
            }
        }, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(ExternalWebPageActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoreWebView coreWebView = this$0.webView;
        if (coreWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            coreWebView = null;
        }
        coreWebView.reload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$2(ExternalWebPageActivity this$0, SwipeRefreshLayout swipeRefreshLayout, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(swipeRefreshLayout, "<unused var>");
        CoreWebView coreWebView = this$0.webView;
        if (coreWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            coreWebView = null;
        }
        return coreWebView.getStartScrollY() != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3(ExternalWebPageActivity this$0, OnBackPressedCallback addCallback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(addCallback, "$this$addCallback");
        CoreWebView coreWebView = this$0.webView;
        CoreWebView coreWebView2 = null;
        if (coreWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            coreWebView = null;
        }
        if (coreWebView.canGoBack()) {
            CoreWebView coreWebView3 = this$0.webView;
            if (coreWebView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                coreWebView2 = coreWebView3;
            }
            coreWebView2.goBack();
        } else {
            this$0.finish();
        }
        return Unit.INSTANCE;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CoreWebView coreWebView = this.webView;
        FrameLayout frameLayout = null;
        if (coreWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            coreWebView = null;
        }
        coreWebView.destroy();
        FrameLayout frameLayout2 = this.layWebView;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layWebView");
        } else {
            frameLayout = frameLayout2;
        }
        frameLayout.removeAllViews();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setupWebView() {
        this.progressBar = (LinearProgressIndicator) findViewById(R.id.progress_bar);
        CoreWebView coreWebView = null;
        CoreWebView coreWebView2 = new CoreWebView(this, 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0);
        this.webView = coreWebView2;
        coreWebView2.setup();
        CoreWebView coreWebView3 = this.webView;
        if (coreWebView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            coreWebView3 = null;
        }
        coreWebView3.setWebViewClient(new MyWebViewClient());
        coreWebView3.setWebChromeClient(new MyWebChromeClient());
        FrameLayout frameLayout = this.layWebView;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layWebView");
            frameLayout = null;
        }
        CoreWebView coreWebView4 = this.webView;
        if (coreWebView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            coreWebView4 = null;
        }
        frameLayout.addView(coreWebView4);
        CoreWebView coreWebView5 = this.webView;
        if (coreWebView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            coreWebView = coreWebView5;
        }
        coreWebView.loadUrl(this.webViewUrl);
    }
}
