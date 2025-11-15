package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class ActivityExternalBrowserBinding implements ViewBinding {
    public final LinearLayout layHeader;
    public final SwipeRefreshLayout laySwipeRefresh;
    public final FrameLayout layWebview;
    public final ConstraintLayout main;
    public final LinearProgressIndicator progressBar;
    private final ConstraintLayout rootView;
    public final MaterialToolbar toolbar;

    private ActivityExternalBrowserBinding(ConstraintLayout constraintLayout, LinearLayout linearLayout, SwipeRefreshLayout swipeRefreshLayout, FrameLayout frameLayout, ConstraintLayout constraintLayout2, LinearProgressIndicator linearProgressIndicator, MaterialToolbar materialToolbar) {
        this.rootView = constraintLayout;
        this.layHeader = linearLayout;
        this.laySwipeRefresh = swipeRefreshLayout;
        this.layWebview = frameLayout;
        this.main = constraintLayout2;
        this.progressBar = linearProgressIndicator;
        this.toolbar = materialToolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityExternalBrowserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityExternalBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_external_browser, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityExternalBrowserBinding bind(View view) {
        int i = R.id.lay_header;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.lay_swipe_refresh;
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) ViewBindings.findChildViewById(view, i);
            if (swipeRefreshLayout != null) {
                i = R.id.lay_webview;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                    i = R.id.progress_bar;
                    LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(view, i);
                    if (linearProgressIndicator != null) {
                        i = R.id.toolbar;
                        MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
                        if (materialToolbar != null) {
                            return new ActivityExternalBrowserBinding(constraintLayout, linearLayout, swipeRefreshLayout, frameLayout, constraintLayout, linearProgressIndicator, materialToolbar);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
