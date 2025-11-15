package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class BottomSheetWebviewBinding implements ViewBinding {
    public final FrameLayout layWebview;
    public final ConstraintLayout main;
    public final LinearProgressIndicator progressBar;
    private final ConstraintLayout rootView;
    public final MaterialToolbar toolbar;

    private BottomSheetWebviewBinding(ConstraintLayout constraintLayout, FrameLayout frameLayout, ConstraintLayout constraintLayout2, LinearProgressIndicator linearProgressIndicator, MaterialToolbar materialToolbar) {
        this.rootView = constraintLayout;
        this.layWebview = frameLayout;
        this.main = constraintLayout2;
        this.progressBar = linearProgressIndicator;
        this.toolbar = materialToolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetWebviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BottomSheetWebviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_webview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static BottomSheetWebviewBinding bind(View view) {
        int i = R.id.lay_webview;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.progress_bar;
            LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(view, i);
            if (linearProgressIndicator != null) {
                i = R.id.toolbar;
                MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                if (materialToolbar != null) {
                    return new BottomSheetWebviewBinding(constraintLayout, frameLayout, constraintLayout, linearProgressIndicator, materialToolbar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
