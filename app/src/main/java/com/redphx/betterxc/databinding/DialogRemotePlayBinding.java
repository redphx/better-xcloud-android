package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class DialogRemotePlayBinding implements ViewBinding {
    public final MaterialButton btn1080p;
    public final MaterialButton btn720p;
    public final LinearLayout layConsoles;
    public final LinearLayout layResolutionLabel;
    private final ScrollView rootView;
    public final MaterialButtonToggleGroup tglResolution;
    public final TextView txtResolutionSummary;
    public final TextView txtResolutionTarget;

    private DialogRemotePlayBinding(ScrollView scrollView, MaterialButton materialButton, MaterialButton materialButton2, LinearLayout linearLayout, LinearLayout linearLayout2, MaterialButtonToggleGroup materialButtonToggleGroup, TextView textView, TextView textView2) {
        this.rootView = scrollView;
        this.btn1080p = materialButton;
        this.btn720p = materialButton2;
        this.layConsoles = linearLayout;
        this.layResolutionLabel = linearLayout2;
        this.tglResolution = materialButtonToggleGroup;
        this.txtResolutionSummary = textView;
        this.txtResolutionTarget = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static DialogRemotePlayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogRemotePlayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_remote_play, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogRemotePlayBinding bind(View view) {
        int i = R.id.btn_1080p;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btn_720p;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.lay_consoles;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R.id.lay_resolution_label;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout2 != null) {
                        i = R.id.tgl_resolution;
                        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) ViewBindings.findChildViewById(view, i);
                        if (materialButtonToggleGroup != null) {
                            i = R.id.txt_resolution_summary;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                i = R.id.txt_resolution_target;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    return new DialogRemotePlayBinding((ScrollView) view, materialButton, materialButton2, linearLayout, linearLayout2, materialButtonToggleGroup, textView, textView2);
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
