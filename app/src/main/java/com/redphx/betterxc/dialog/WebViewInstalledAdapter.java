package com.redphx.betterxc.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.redphx.betterxc.R;
import com.redphx.betterxc.util.WebViewUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebViewManagerDialog.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001&B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0017J\b\u0010\"\u001a\u00020!H\u0016J\u0018\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020!H\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006'"}, d2 = {"Lcom/redphx/betterxc/dialog/WebViewInstalledAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/redphx/betterxc/dialog/WebViewInstalledAdapter$ViewHolder;", "context", "Landroid/content/Context;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "emptyView", "Landroid/view/View;", "preference", "Landroidx/preference/Preference;", "<init>", "(Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;Landroid/view/View;Landroidx/preference/Preference;)V", "selectedWebView", "Landroid/content/pm/PackageInfo;", "getSelectedWebView", "()Landroid/content/pm/PackageInfo;", "setSelectedWebView", "(Landroid/content/pm/PackageInfo;)V", "entries", "", "", "values", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "Landroid/content/SharedPreferences;", "reloadData", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "getItemCount", "onBindViewHolder", "holder", "position", "ViewHolder", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebViewInstalledAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final View emptyView;
    private final List<CharSequence> entries;
    private final Preference preference;
    private final RecyclerView recyclerView;
    public PackageInfo selectedWebView;
    private final SharedPreferences sharedPreferences;
    private final List<PackageInfo> values;

    public WebViewInstalledAdapter(Context context, RecyclerView recyclerView, View emptyView, Preference preference) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(emptyView, "emptyView");
        this.context = context;
        this.recyclerView = recyclerView;
        this.emptyView = emptyView;
        this.preference = preference;
        this.entries = new ArrayList();
        this.values = new ArrayList();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        reloadData();
    }

    public final PackageInfo getSelectedWebView() {
        PackageInfo packageInfo = this.selectedWebView;
        if (packageInfo != null) {
            return packageInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("selectedWebView");
        return null;
    }

    public final void setSelectedWebView(PackageInfo packageInfo) {
        Intrinsics.checkNotNullParameter(packageInfo, "<set-?>");
        this.selectedWebView = packageInfo;
    }

    /* compiled from: WebViewManagerDialog.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/redphx/betterxc/dialog/WebViewInstalledAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "txtName", "Landroid/widget/TextView;", "getTxtName", "()Landroid/widget/TextView;", "txtPackage", "getTxtPackage", "txtVersion", "getTxtVersion", "imgIcon", "Landroid/widget/ImageView;", "getImgIcon", "()Landroid/widget/ImageView;", "rdoCheck", "Landroid/widget/RadioButton;", "getRdoCheck", "()Landroid/widget/RadioButton;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgIcon;
        private final RadioButton rdoCheck;
        private final TextView txtName;
        private final TextView txtPackage;
        private final TextView txtVersion;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.txt_webview_name);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.txtName = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.txt_webview_package);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.txtPackage = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.txt_webview_version);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.txtVersion = (TextView) findViewById3;
            View findViewById4 = view.findViewById(R.id.img_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.imgIcon = (ImageView) findViewById4;
            View findViewById5 = view.findViewById(R.id.rdo_check);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.rdoCheck = (RadioButton) findViewById5;
        }

        public final TextView getTxtName() {
            return this.txtName;
        }

        public final TextView getTxtPackage() {
            return this.txtPackage;
        }

        public final TextView getTxtVersion() {
            return this.txtVersion;
        }

        public final ImageView getImgIcon() {
            return this.imgIcon;
        }

        public final RadioButton getRdoCheck() {
            return this.rdoCheck;
        }
    }

    public final void reloadData() {
        WebViewUtil.INSTANCE.reloadProviderList(this.context);
        this.entries.clear();
        this.values.clear();
        for (Map.Entry<String, PackageInfo> entry : WebViewUtil.INSTANCE.getAvailableProviders().entrySet()) {
            entry.getKey();
            PackageInfo value = entry.getValue();
            this.entries.add(WebViewUtil.INSTANCE.toString(this.context, value));
            this.values.add(value);
        }
        String string = this.sharedPreferences.getString("webview_implementation", WebViewUtil.INSTANCE.getDefaultProvider().packageName);
        Iterator<PackageInfo> it = this.values.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PackageInfo next = it.next();
            if (Intrinsics.areEqual(next.packageName, string)) {
                setSelectedWebView(next);
                break;
            }
        }
        if (this.selectedWebView == null) {
            setSelectedWebView(WebViewUtil.INSTANCE.getDefaultProvider());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_webview_installed, parent, false);
        Intrinsics.checkNotNull(inflate);
        final ViewHolder viewHolder = new ViewHolder(inflate);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewInstalledAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewInstalledAdapter.onCreateViewHolder$lambda$1(WebViewInstalledAdapter.this, viewHolder, view);
            }
        });
        viewHolder.getRdoCheck().setClickable(false);
        return viewHolder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateViewHolder$lambda$1(WebViewInstalledAdapter this$0, ViewHolder vh, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(vh, "$vh");
        PackageInfo packageInfo = this$0.values.get(vh.getAdapterPosition());
        this$0.setSelectedWebView(packageInfo);
        SharedPreferences.Editor edit = this$0.sharedPreferences.edit();
        edit.putString("webview_implementation", packageInfo.packageName);
        edit.commit();
        if (this$0.preference != null) {
            String companion = WebViewUtil.INSTANCE.toString(this$0.context, packageInfo);
            if (!Intrinsics.areEqual(this$0.getSelectedWebView().packageName, WebViewUtil.INSTANCE.getCurrentProvider().packageName)) {
                companion = "*" + companion;
            }
            this$0.preference.setSummary(companion);
        }
        this$0.notifyItemRangeChanged(0, this$0.values.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int size = this.values.size();
        this.emptyView.setVisibility(size == 0 ? 0 : 8);
        this.recyclerView.setVisibility(size > 0 ? 0 : 8);
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        PackageManager packageManager = this.context.getPackageManager();
        PackageInfo packageInfo = this.values.get(position);
        TextView txtName = holder.getTxtName();
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        Intrinsics.checkNotNull(applicationInfo);
        txtName.setText(String.valueOf(packageManager.getApplicationLabel(applicationInfo)));
        holder.getTxtPackage().setText(packageInfo.packageName);
        holder.getTxtVersion().setText(packageInfo.versionName + " (" + packageInfo.versionCode + ')');
        ImageView imgIcon = holder.getImgIcon();
        ApplicationInfo applicationInfo2 = packageInfo.applicationInfo;
        Intrinsics.checkNotNull(applicationInfo2);
        imgIcon.setImageDrawable(packageManager.getApplicationIcon(applicationInfo2));
        holder.getRdoCheck().setChecked(Intrinsics.areEqual(getSelectedWebView().packageName, packageInfo.packageName));
    }
}
