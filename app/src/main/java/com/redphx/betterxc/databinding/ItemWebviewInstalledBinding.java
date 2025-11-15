package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class ItemWebviewInstalledBinding implements ViewBinding {
    public final ImageView imgIcon;
    public final MaterialRadioButton rdoCheck;
    private final LinearLayout rootView;
    public final TextView txtWebviewName;
    public final TextView txtWebviewPackage;
    public final TextView txtWebviewVersion;

    private ItemWebviewInstalledBinding(LinearLayout linearLayout, ImageView imageView, MaterialRadioButton materialRadioButton, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.imgIcon = imageView;
        this.rdoCheck = materialRadioButton;
        this.txtWebviewName = textView;
        this.txtWebviewPackage = textView2;
        this.txtWebviewVersion = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemWebviewInstalledBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemWebviewInstalledBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_webview_installed, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemWebviewInstalledBinding bind(View view) {
        int i = R.id.img_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.rdo_check;
            MaterialRadioButton materialRadioButton = (MaterialRadioButton) ViewBindings.findChildViewById(view, i);
            if (materialRadioButton != null) {
                i = R.id.txt_webview_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.txt_webview_package;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.txt_webview_version;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            return new ItemWebviewInstalledBinding((LinearLayout) view, imageView, materialRadioButton, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
