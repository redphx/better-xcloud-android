package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final Button btnGrant;
    public final Button btnIgnore;
    public final Button btnRemoteLogIn;
    public final FloatingActionButton fabSettings;
    public final LinearLayout layFabContainer;
    public final LinearLayout layHeader;
    public final ConstraintLayout layStorageAccess;
    public final SwipeRefreshLayout laySwipeRefresh;
    public final FrameLayout layWebview;
    public final LinearProgressIndicator progressBar;
    private final CoordinatorLayout rootView;
    public final View viewLowBatteryIndicator;

    private ActivityMainBinding(CoordinatorLayout coordinatorLayout, Button button, Button button2, Button button3, FloatingActionButton floatingActionButton, LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout, SwipeRefreshLayout swipeRefreshLayout, FrameLayout frameLayout, LinearProgressIndicator linearProgressIndicator, View view) {
        this.rootView = coordinatorLayout;
        this.btnGrant = button;
        this.btnIgnore = button2;
        this.btnRemoteLogIn = button3;
        this.fabSettings = floatingActionButton;
        this.layFabContainer = linearLayout;
        this.layHeader = linearLayout2;
        this.layStorageAccess = constraintLayout;
        this.laySwipeRefresh = swipeRefreshLayout;
        this.layWebview = frameLayout;
        this.progressBar = linearProgressIndicator;
        this.viewLowBatteryIndicator = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMainBinding bind(View view) {
        View findChildViewById;
        int i = R.id.btn_grant;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.btn_ignore;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                i = R.id.btn_remote_log_in;
                Button button3 = (Button) ViewBindings.findChildViewById(view, i);
                if (button3 != null) {
                    i = R.id.fab_settings;
                    FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(view, i);
                    if (floatingActionButton != null) {
                        i = R.id.lay_fab_container;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.lay_header;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.lay_storage_access;
                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                if (constraintLayout != null) {
                                    i = R.id.lay_swipe_refresh;
                                    SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) ViewBindings.findChildViewById(view, i);
                                    if (swipeRefreshLayout != null) {
                                        i = R.id.lay_webview;
                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                        if (frameLayout != null) {
                                            i = R.id.progress_bar;
                                            LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(view, i);
                                            if (linearProgressIndicator != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_low_battery_indicator))) != null) {
                                                return new ActivityMainBinding((CoordinatorLayout) view, button, button2, button3, floatingActionButton, linearLayout, linearLayout2, constraintLayout, swipeRefreshLayout, frameLayout, linearProgressIndicator, findChildViewById);
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
