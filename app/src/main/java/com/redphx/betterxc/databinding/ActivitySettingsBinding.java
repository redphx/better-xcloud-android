package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class ActivitySettingsBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final FragmentContainerView settingsContainer;
    public final MaterialToolbar toolbar;

    private ActivitySettingsBinding(ConstraintLayout constraintLayout, FragmentContainerView fragmentContainerView, MaterialToolbar materialToolbar) {
        this.rootView = constraintLayout;
        this.settingsContainer = fragmentContainerView;
        this.toolbar = materialToolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivitySettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivitySettingsBinding bind(View view) {
        int i = R.id.settings_container;
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
        if (fragmentContainerView != null) {
            i = R.id.toolbar;
            MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
            if (materialToolbar != null) {
                return new ActivitySettingsBinding((ConstraintLayout) view, fragmentContainerView, materialToolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
