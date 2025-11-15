package com.redphx.betterxc.dialog;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.redphx.betterxc.worker.DownloadFileParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebViewManagerDialog.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JG\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/redphx/betterxc/dialog/WebViewSource;", "", "id", "Lcom/redphx/betterxc/dialog/WebViewImplementation;", "name", "", DownloadFileParams.KEY_SOURCE, "url", "onDownload", "Landroid/view/View$OnClickListener;", "supported", "", "<init>", "(Lcom/redphx/betterxc/dialog/WebViewImplementation;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/view/View$OnClickListener;Z)V", "getId", "()Lcom/redphx/betterxc/dialog/WebViewImplementation;", "getName", "()Ljava/lang/String;", "getSource", "getUrl", "getOnDownload", "()Landroid/view/View$OnClickListener;", "getSupported", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class WebViewSource {
    private final WebViewImplementation id;
    private final String name;
    private final View.OnClickListener onDownload;
    private final String source;
    private final boolean supported;
    private final String url;

    public static /* synthetic */ WebViewSource copy$default(WebViewSource webViewSource, WebViewImplementation webViewImplementation, String str, String str2, String str3, View.OnClickListener onClickListener, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            webViewImplementation = webViewSource.id;
        }
        if ((i & 2) != 0) {
            str = webViewSource.name;
        }
        if ((i & 4) != 0) {
            str2 = webViewSource.source;
        }
        if ((i & 8) != 0) {
            str3 = webViewSource.url;
        }
        if ((i & 16) != 0) {
            onClickListener = webViewSource.onDownload;
        }
        if ((i & 32) != 0) {
            z = webViewSource.supported;
        }
        View.OnClickListener onClickListener2 = onClickListener;
        boolean z2 = z;
        return webViewSource.copy(webViewImplementation, str, str2, str3, onClickListener2, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final WebViewImplementation getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component5, reason: from getter */
    public final View.OnClickListener getOnDownload() {
        return this.onDownload;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getSupported() {
        return this.supported;
    }

    public final WebViewSource copy(WebViewImplementation id, String name, String source, String url, View.OnClickListener onDownload, boolean supported) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(onDownload, "onDownload");
        return new WebViewSource(id, name, source, url, onDownload, supported);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WebViewSource)) {
            return false;
        }
        WebViewSource webViewSource = (WebViewSource) other;
        return this.id == webViewSource.id && Intrinsics.areEqual(this.name, webViewSource.name) && Intrinsics.areEqual(this.source, webViewSource.source) && Intrinsics.areEqual(this.url, webViewSource.url) && Intrinsics.areEqual(this.onDownload, webViewSource.onDownload) && this.supported == webViewSource.supported;
    }

    public int hashCode() {
        int hashCode = ((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.source.hashCode()) * 31;
        String str = this.url;
        return ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.onDownload.hashCode()) * 31) + Boolean.hashCode(this.supported);
    }

    public String toString() {
        return "WebViewSource(id=" + this.id + ", name=" + this.name + ", source=" + this.source + ", url=" + this.url + ", onDownload=" + this.onDownload + ", supported=" + this.supported + ')';
    }

    public WebViewSource(WebViewImplementation id, String name, String source, String str, View.OnClickListener onDownload, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(onDownload, "onDownload");
        this.id = id;
        this.name = name;
        this.source = source;
        this.url = str;
        this.onDownload = onDownload;
        this.supported = z;
    }

    public final WebViewImplementation getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSource() {
        return this.source;
    }

    public final String getUrl() {
        return this.url;
    }

    public final View.OnClickListener getOnDownload() {
        return this.onDownload;
    }

    public final boolean getSupported() {
        return this.supported;
    }
}
