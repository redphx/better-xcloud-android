package com.redphx.betterxc.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.Preference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.redphx.betterxc.R;
import com.redphx.betterxc.activity.SettingsActivity;
import com.redphx.betterxc.data.GitHubApiRepoRelease;
import com.redphx.betterxc.data.GitHubApiRepoReleaseAsset;
import com.redphx.betterxc.util.Constants;
import com.redphx.betterxc.util.DownloadManagerUtil;
import com.redphx.betterxc.util.Utils;
import com.redphx.betterxc.util.WebViewUtil;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: WebViewManagerDialog.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\u0006\u0010\u001f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000eR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/redphx/betterxc/dialog/WebViewManagerDialog;", "Lcom/google/android/material/dialog/MaterialAlertDialogBuilder;", "activity", "Landroid/app/Activity;", "preference", "Landroidx/preference/Preference;", "onDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "<init>", "(Landroid/app/Activity;Landroidx/preference/Preference;Landroid/content/DialogInterface$OnDismissListener;)V", "installedAdapter", "Lcom/redphx/betterxc/dialog/WebViewInstalledAdapter;", "is64bit", "", "()Z", "onDownload", "", "button", "Landroid/widget/Button;", "fileName", "", "desc", "downloadUrl", "onDownloadGoogle", "Landroid/view/View$OnClickListener;", "onDownloadThorium", "onDownloadMulch", "webViewsImplementation", "", "Lcom/redphx/betterxc/dialog/WebViewImplementation;", "Lcom/redphx/betterxc/dialog/WebViewSource;", "changedWebView", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebViewManagerDialog extends MaterialAlertDialogBuilder {
    private final Activity activity;
    private WebViewInstalledAdapter installedAdapter;
    private final boolean is64bit;
    private final View.OnClickListener onDownloadGoogle;
    private final View.OnClickListener onDownloadMulch;
    private final View.OnClickListener onDownloadThorium;
    private final Map<WebViewImplementation, WebViewSource> webViewsImplementation;

    public /* synthetic */ WebViewManagerDialog(Activity activity, Preference preference, DialogInterface.OnDismissListener onDismissListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i & 2) != 0 ? null : preference, (i & 4) != 0 ? null : onDismissListener);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebViewManagerDialog(Activity activity, Preference preference, DialogInterface.OnDismissListener onDismissListener) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        String[] SUPPORTED_64_BIT_ABIS = Build.SUPPORTED_64_BIT_ABIS;
        Intrinsics.checkNotNullExpressionValue(SUPPORTED_64_BIT_ABIS, "SUPPORTED_64_BIT_ABIS");
        this.is64bit = !(SUPPORTED_64_BIT_ABIS.length == 0);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewManagerDialog.onDownloadGoogle$lambda$1(WebViewManagerDialog.this, view);
            }
        };
        this.onDownloadGoogle = onClickListener;
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewManagerDialog.onDownloadThorium$lambda$2(WebViewManagerDialog.this, view);
            }
        };
        this.onDownloadThorium = onClickListener2;
        View.OnClickListener onClickListener3 = new View.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewManagerDialog.onDownloadMulch$lambda$3(WebViewManagerDialog.this, view);
            }
        };
        this.onDownloadMulch = onClickListener3;
        Map<WebViewImplementation, WebViewSource> mapOf = MapsKt.mapOf(TuplesKt.to(WebViewImplementation.GOOGLE, new WebViewSource(WebViewImplementation.GOOGLE, "Android System WebView", "Google Play Store", null, onClickListener, true)), TuplesKt.to(WebViewImplementation.THORIUM, new WebViewSource(WebViewImplementation.THORIUM, "Thorium WebView", "github.com/Alex313031/Thorium-Android", "https://github.com/Alex313031/Thorium-Android", onClickListener2, !Intrinsics.areEqual(WebViewUtil.INSTANCE.getDefaultProvider().packageName, "com.android.webview"))), TuplesKt.to(WebViewImplementation.MULCH, new WebViewSource(WebViewImplementation.THORIUM, "Mulch WebView", "gitlab.com/divested-mobile/mulch", "https://gitlab.com/divested-mobile/mulch", onClickListener3, true)));
        this.webViewsImplementation = mapOf;
        final View inflate = activity.getLayoutInflater().inflate(R.layout.dialog_webview_manager, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.btn_refresh);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        Button button = (Button) findViewById;
        View findViewById2 = inflate.findViewById(R.id.tgl_tab);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.txt_unsupported);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        View findViewById4 = inflate.findViewById(R.id.lay_installed);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        final LinearLayout linearLayout = (LinearLayout) findViewById4;
        View findViewById5 = inflate.findViewById(R.id.lst_installed);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        final RecyclerView recyclerView = (RecyclerView) findViewById5;
        View findViewById6 = inflate.findViewById(R.id.txt_empty);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        TextView textView = (TextView) findViewById6;
        View findViewById7 = inflate.findViewById(R.id.lay_downloader);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        final LinearLayout linearLayout2 = (LinearLayout) findViewById7;
        View findViewById8 = inflate.findViewById(R.id.lst_downloader);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        RecyclerView recyclerView2 = (RecyclerView) findViewById8;
        ((TextView) findViewById3).setVisibility(!WebViewUtil.INSTANCE.isSupported() ? 0 : 8);
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda4
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public final void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup2, int i, boolean z) {
                WebViewManagerDialog._init_$lambda$4(inflate, linearLayout, linearLayout2, materialButtonToggleGroup2, i, z);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        WebViewInstalledAdapter webViewInstalledAdapter = new WebViewInstalledAdapter(activity, recyclerView, textView, preference);
        this.installedAdapter = webViewInstalledAdapter;
        recyclerView.setAdapter(webViewInstalledAdapter);
        recyclerView.setItemAnimator(null);
        recyclerView2.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView2.setAdapter(new WebViewDownloaderAdapter(activity, (WebViewSource[]) mapOf.values().toArray(new WebViewSource[0])));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewManagerDialog._init_$lambda$5(WebViewManagerDialog.this, recyclerView, view);
            }
        });
        setTitle(R.string.webview_manager);
        setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        setNeutralButton(R.string.learn_more, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                WebViewManagerDialog._init_$lambda$7(WebViewManagerDialog.this, dialogInterface, i);
            }
        });
        if (preference == null) {
            setNegativeButton(R.string.settings, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    WebViewManagerDialog._init_$lambda$8(WebViewManagerDialog.this, dialogInterface, i);
                }
            });
        }
        setOnDismissListener(onDismissListener);
        setView(inflate);
    }

    /* renamed from: is64bit, reason: from getter */
    public final boolean getIs64bit() {
        return this.is64bit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDownload(Button button, final String fileName, final String desc, final String downloadUrl) {
        button.setEnabled(false);
        this.activity.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WebViewManagerDialog.onDownload$lambda$0(WebViewManagerDialog.this, fileName, downloadUrl, desc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDownload$lambda$0(WebViewManagerDialog this$0, String fileName, String downloadUrl, String desc) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        Intrinsics.checkNotNullParameter(downloadUrl, "$downloadUrl");
        Intrinsics.checkNotNullParameter(desc, "$desc");
        Toast.makeText(this$0.activity, "Downloading " + fileName + "...", 1).show();
        DownloadManagerUtil.INSTANCE.download(this$0.activity, downloadUrl, fileName, desc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDownloadGoogle$lambda$1(WebViewManagerDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Utils.Companion companion = Utils.INSTANCE;
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        companion.openUrl(context, "https://play.google.com/store/apps/details?id=com.google.android.webview", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDownloadThorium$lambda$2(final WebViewManagerDialog this$0, final View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        view.setEnabled(false);
        new OkHttpClient().newCall(new Request.Builder().url("https://api.github.com/repos/Alex313031/Thorium-Android/releases/latest").header("Accept", "application/vnd.github+json").header("User-Agent", Constants.USER_AGENT).get().build()).enqueue(new Callback() { // from class: com.redphx.betterxc.dialog.WebViewManagerDialog$onDownloadThorium$1$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Activity activity;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                activity = WebViewManagerDialog.this.activity;
                Toast.makeText(activity, "Failed", 0).show();
                view.setEnabled(true);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                String str;
                String str2;
                boolean contains$default;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                JsonAdapter adapter = new Moshi.Builder().build().adapter(GitHubApiRepoRelease.class);
                Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
                ResponseBody body = response.body();
                Intrinsics.checkNotNull(body);
                Object fromJson = adapter.fromJson(body.getSource());
                Intrinsics.checkNotNull(fromJson);
                GitHubApiRepoRelease gitHubApiRepoRelease = (GitHubApiRepoRelease) fromJson;
                GitHubApiRepoReleaseAsset[] assets = gitHubApiRepoRelease.getAssets();
                int length = assets.length;
                int i = 0;
                while (true) {
                    str = null;
                    if (i >= length) {
                        str2 = null;
                        break;
                    }
                    GitHubApiRepoReleaseAsset gitHubApiRepoReleaseAsset = assets[i];
                    if (StringsKt.contains((CharSequence) gitHubApiRepoReleaseAsset.getName(), (CharSequence) "systemwebview", true) && StringsKt.contains$default((CharSequence) gitHubApiRepoReleaseAsset.getName(), (CharSequence) ".apk", false, 2, (Object) null)) {
                        if (WebViewManagerDialog.this.getIs64bit()) {
                            contains$default = StringsKt.contains$default((CharSequence) gitHubApiRepoReleaseAsset.getName(), (CharSequence) "arm64", false, 2, (Object) null);
                        } else {
                            contains$default = StringsKt.contains$default((CharSequence) gitHubApiRepoReleaseAsset.getName(), (CharSequence) "arm32", false, 2, (Object) null);
                        }
                        if (contains$default) {
                            StringBuilder sb = new StringBuilder("thorium-webview-");
                            String lowerCase = gitHubApiRepoRelease.getTagName().toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                            String sb2 = sb.append(lowerCase).append("-arm").append(WebViewManagerDialog.this.getIs64bit() ? "64" : "32").append(".apk").toString();
                            str = gitHubApiRepoReleaseAsset.getDownloadUrl();
                            str2 = sb2;
                        }
                    }
                    i++;
                }
                if (str == null || str2 == null) {
                    view.setEnabled(true);
                    Toast.makeText(WebViewManagerDialog.this.getContext(), "Could not get download link!", 0).show();
                } else {
                    WebViewManagerDialog webViewManagerDialog = WebViewManagerDialog.this;
                    View view2 = view;
                    Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type android.widget.Button");
                    webViewManagerDialog.onDownload((Button) view2, str2, "Thorium WebView " + gitHubApiRepoRelease.getTagName(), str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDownloadMulch$lambda$3(WebViewManagerDialog this$0, View view) {
        String str;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.is64bit) {
            str = "mulch-webview-arm64.apk";
            str2 = "Mulch WebView (64-bit)";
            str3 = "https://gitlab.com/divested-mobile/mulch/-/raw/master/prebuilt/arm64/webview.apk";
        } else {
            str = "mulch-webview-arm32.apk";
            str2 = "Mulch WebView (32-bit)";
            str3 = "https://gitlab.com/divested-mobile/mulch/-/raw/master/prebuilt/arm/webview.apk";
        }
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.Button");
        this$0.onDownload((Button) view, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$4(View view, LinearLayout layInstalled, LinearLayout layDownloader, MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
        Intrinsics.checkNotNullParameter(layInstalled, "$layInstalled");
        Intrinsics.checkNotNullParameter(layDownloader, "$layDownloader");
        if (z) {
            if (materialButtonToggleGroup.indexOfChild(view.findViewById(i)) == 0) {
                layInstalled.setVisibility(0);
                layDownloader.setVisibility(8);
            } else {
                layInstalled.setVisibility(8);
                layDownloader.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$5(WebViewManagerDialog this$0, RecyclerView rvInstalled, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(rvInstalled, "$rvInstalled");
        this$0.installedAdapter.reloadData();
        RecyclerView.Adapter adapter = rvInstalled.getAdapter();
        Intrinsics.checkNotNull(adapter);
        adapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$7(WebViewManagerDialog this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Utils.Companion companion = Utils.INSTANCE;
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        companion.openUrl(context, Constants.URL_CUSTOM_WEBVIEW, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$8(WebViewManagerDialog this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getContext().startActivity(new Intent(this$0.getContext(), (Class<?>) SettingsActivity.class));
    }

    public final boolean changedWebView() {
        return !Intrinsics.areEqual(this.installedAdapter.getSelectedWebView().packageName, WebViewUtil.INSTANCE.getCurrentProvider().packageName);
    }
}
