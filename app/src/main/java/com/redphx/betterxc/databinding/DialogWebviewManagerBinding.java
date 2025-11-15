package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class DialogWebviewManagerBinding implements ViewBinding {
    public final MaterialButton btnDownloader;
    public final MaterialButton btnInstalled;
    public final MaterialButton btnRefresh;
    public final LinearLayout layDownloader;
    public final LinearLayout layInstalled;
    public final RecyclerView lstDownloader;
    public final RecyclerView lstInstalled;
    private final LinearLayout rootView;
    public final MaterialButtonToggleGroup tglTab;
    public final TextView txtDownloadProgress;
    public final TextView txtEmpty;
    public final TextView txtUnsupported;

    private DialogWebviewManagerBinding(LinearLayout linearLayout, MaterialButton materialButton, MaterialButton materialButton2, MaterialButton materialButton3, LinearLayout linearLayout2, LinearLayout linearLayout3, RecyclerView recyclerView, RecyclerView recyclerView2, MaterialButtonToggleGroup materialButtonToggleGroup, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.btnDownloader = materialButton;
        this.btnInstalled = materialButton2;
        this.btnRefresh = materialButton3;
        this.layDownloader = linearLayout2;
        this.layInstalled = linearLayout3;
        this.lstDownloader = recyclerView;
        this.lstInstalled = recyclerView2;
        this.tglTab = materialButtonToggleGroup;
        this.txtDownloadProgress = textView;
        this.txtEmpty = textView2;
        this.txtUnsupported = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogWebviewManagerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogWebviewManagerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_webview_manager, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogWebviewManagerBinding bind(View view) {
        int i = R.id.btn_downloader;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btn_installed;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.btn_refresh;
                MaterialButton materialButton3 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton3 != null) {
                    i = R.id.lay_downloader;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.lay_installed;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout2 != null) {
                            i = R.id.lst_downloader;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView != null) {
                                i = R.id.lst_installed;
                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView2 != null) {
                                    i = R.id.tgl_tab;
                                    MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) ViewBindings.findChildViewById(view, i);
                                    if (materialButtonToggleGroup != null) {
                                        i = R.id.txt_download_progress;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            i = R.id.txt_empty;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView2 != null) {
                                                i = R.id.txt_unsupported;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView3 != null) {
                                                    return new DialogWebviewManagerBinding((LinearLayout) view, materialButton, materialButton2, materialButton3, linearLayout, linearLayout2, recyclerView, recyclerView2, materialButtonToggleGroup, textView, textView2, textView3);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
