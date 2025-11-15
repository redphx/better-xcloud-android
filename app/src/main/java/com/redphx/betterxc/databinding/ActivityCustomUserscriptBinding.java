package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.redphx.betterxc.R;
import com.redphx.betterxc.custom_view.PlainEditText;

/* loaded from: classes.dex */
public final class ActivityCustomUserscriptBinding implements ViewBinding {
    public final PlainEditText editUserscript;
    private final ConstraintLayout rootView;
    public final MaterialToolbar toolbar;

    private ActivityCustomUserscriptBinding(ConstraintLayout constraintLayout, PlainEditText plainEditText, MaterialToolbar materialToolbar) {
        this.rootView = constraintLayout;
        this.editUserscript = plainEditText;
        this.toolbar = materialToolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCustomUserscriptBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityCustomUserscriptBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_custom_userscript, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityCustomUserscriptBinding bind(View view) {
        int i = R.id.edit_userscript;
        PlainEditText plainEditText = (PlainEditText) ViewBindings.findChildViewById(view, i);
        if (plainEditText != null) {
            i = R.id.toolbar;
            MaterialToolbar materialToolbar = (MaterialToolbar) ViewBindings.findChildViewById(view, i);
            if (materialToolbar != null) {
                return new ActivityCustomUserscriptBinding((ConstraintLayout) view, plainEditText, materialToolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
