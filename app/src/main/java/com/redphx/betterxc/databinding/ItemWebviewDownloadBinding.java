package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class ItemWebviewDownloadBinding implements ViewBinding {
    public final MaterialButton btnDownload;
    public final MaterialButton btnLink;
    private final LinearLayout rootView;
    public final TextView txtWebviewName;
    public final TextView txtWebviewSource;

    private ItemWebviewDownloadBinding(LinearLayout linearLayout, MaterialButton materialButton, MaterialButton materialButton2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.btnDownload = materialButton;
        this.btnLink = materialButton2;
        this.txtWebviewName = textView;
        this.txtWebviewSource = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemWebviewDownloadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemWebviewDownloadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_webview_download, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemWebviewDownloadBinding bind(View view) {
        int i = R.id.btn_download;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btn_link;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.txt_webview_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.txt_webview_source;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        return new ItemWebviewDownloadBinding((LinearLayout) view, materialButton, materialButton2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
