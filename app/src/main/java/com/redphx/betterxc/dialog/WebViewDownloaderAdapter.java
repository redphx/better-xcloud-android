package com.redphx.betterxc.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.redphx.betterxc.R;
import com.redphx.betterxc.util.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebViewManagerDialog.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/redphx/betterxc/dialog/WebViewDownloaderAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/redphx/betterxc/dialog/WebViewDownloaderAdapter$ViewHolder;", "context", "Landroid/content/Context;", "values", "", "Lcom/redphx/betterxc/dialog/WebViewSource;", "<init>", "(Landroid/content/Context;[Lcom/redphx/betterxc/dialog/WebViewSource;)V", "[Lcom/redphx/betterxc/dialog/WebViewSource;", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "ViewHolder", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebViewDownloaderAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final WebViewSource[] values;

    public WebViewDownloaderAdapter(Context context, WebViewSource[] values) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(values, "values");
        this.context = context;
        this.values = values;
    }

    /* compiled from: WebViewManagerDialog.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/redphx/betterxc/dialog/WebViewDownloaderAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "txtName", "Landroid/widget/TextView;", "getTxtName", "()Landroid/widget/TextView;", "txtSource", "getTxtSource", "btnDownload", "Lcom/google/android/material/button/MaterialButton;", "getBtnDownload", "()Lcom/google/android/material/button/MaterialButton;", "btnLink", "getBtnLink", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialButton btnDownload;
        private final MaterialButton btnLink;
        private final TextView txtName;
        private final TextView txtSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.txt_webview_name);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.txtName = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.txt_webview_source);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.txtSource = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.btn_download);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.btnDownload = (MaterialButton) findViewById3;
            View findViewById4 = view.findViewById(R.id.btn_link);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.btnLink = (MaterialButton) findViewById4;
        }

        public final TextView getTxtName() {
            return this.txtName;
        }

        public final TextView getTxtSource() {
            return this.txtSource;
        }

        public final MaterialButton getBtnDownload() {
            return this.btnDownload;
        }

        public final MaterialButton getBtnLink() {
            return this.btnLink;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_webview_download, parent, false);
        Intrinsics.checkNotNull(inflate);
        return new ViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.values.length;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final WebViewSource webViewSource = this.values[position];
        holder.getTxtName().setText(webViewSource.getName());
        holder.getTxtSource().setText(webViewSource.getSource());
        holder.getBtnDownload().setOnClickListener(webViewSource.getOnDownload());
        if (webViewSource.getSupported()) {
            holder.getBtnDownload().setEnabled(true);
            holder.getBtnDownload().setText(this.context.getString(R.string.download));
        } else {
            holder.getBtnDownload().setEnabled(false);
            holder.getBtnDownload().setText(this.context.getString(R.string.unsupported));
        }
        if (webViewSource.getUrl() == null) {
            holder.getBtnLink().setVisibility(8);
        } else {
            holder.getBtnLink().setVisibility(0);
            holder.getBtnLink().setOnClickListener(new View.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewDownloaderAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WebViewDownloaderAdapter.onBindViewHolder$lambda$0(WebViewDownloaderAdapter.this, webViewSource, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(WebViewDownloaderAdapter this$0, WebViewSource webView, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(webView, "$webView");
        Utils.INSTANCE.openUrl(this$0.context, webView.getUrl(), false);
    }
}
