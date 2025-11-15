package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class PreferenceWidgetSwitchCompatBinding implements ViewBinding {
    private final MaterialSwitch rootView;
    public final MaterialSwitch switchWidget;

    private PreferenceWidgetSwitchCompatBinding(MaterialSwitch materialSwitch, MaterialSwitch materialSwitch2) {
        this.rootView = materialSwitch;
        this.switchWidget = materialSwitch2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialSwitch getRoot() {
        return this.rootView;
    }

    public static PreferenceWidgetSwitchCompatBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PreferenceWidgetSwitchCompatBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.preference_widget_switch_compat, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PreferenceWidgetSwitchCompatBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        MaterialSwitch materialSwitch = (MaterialSwitch) view;
        return new PreferenceWidgetSwitchCompatBinding(materialSwitch, materialSwitch);
    }
}
