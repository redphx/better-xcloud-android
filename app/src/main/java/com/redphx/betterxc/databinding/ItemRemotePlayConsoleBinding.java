package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.chip.Chip;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class ItemRemotePlayConsoleBinding implements ViewBinding {
    public final Button btnConnect;
    public final Button btnShortcut;
    public final Chip chipDeviceType;
    public final ConstraintLayout layInfo;
    private final ConstraintLayout rootView;
    public final TextView txtDeviceName;
    public final TextView txtPowerState;

    private ItemRemotePlayConsoleBinding(ConstraintLayout constraintLayout, Button button, Button button2, Chip chip, ConstraintLayout constraintLayout2, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.btnConnect = button;
        this.btnShortcut = button2;
        this.chipDeviceType = chip;
        this.layInfo = constraintLayout2;
        this.txtDeviceName = textView;
        this.txtPowerState = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemRemotePlayConsoleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRemotePlayConsoleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_remote_play_console, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRemotePlayConsoleBinding bind(View view) {
        int i = R.id.btn_connect;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.btn_shortcut;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                i = R.id.chip_device_type;
                Chip chip = (Chip) ViewBindings.findChildViewById(view, i);
                if (chip != null) {
                    i = R.id.lay_info;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout != null) {
                        i = R.id.txt_device_name;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.txt_power_state;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                return new ItemRemotePlayConsoleBinding((ConstraintLayout) view, button, button2, chip, constraintLayout, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
