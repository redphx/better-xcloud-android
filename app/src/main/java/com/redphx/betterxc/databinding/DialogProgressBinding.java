package com.redphx.betterxc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.redphx.betterxc.R;

/* loaded from: classes.dex */
public final class DialogProgressBinding implements ViewBinding {
    public final CircularProgressIndicator progressCircular;
    private final ConstraintLayout rootView;
    public final TextView textProgress;
    public final TextView txtProgressStatus;

    private DialogProgressBinding(ConstraintLayout constraintLayout, CircularProgressIndicator circularProgressIndicator, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.progressCircular = circularProgressIndicator;
        this.textProgress = textView;
        this.txtProgressStatus = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogProgressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogProgressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_progress, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogProgressBinding bind(View view) {
        int i = R.id.progress_circular;
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
        if (circularProgressIndicator != null) {
            i = R.id.text_progress;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.txt_progress_status;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new DialogProgressBinding((ConstraintLayout) view, circularProgressIndicator, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
