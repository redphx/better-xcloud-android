package com.redphx.betterxc.custom_view;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.ListPreference;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MaterialListPreference.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/redphx/betterxc/custom_view/MaterialListPreference;", "Landroidx/preference/ListPreference;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "neutralText", "", "neutralAction", "Landroid/content/DialogInterface$OnClickListener;", "onClick", "", "getValueIndex", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MaterialListPreference extends ListPreference {
    private DialogInterface.OnClickListener neutralAction;
    private String neutralText;

    public /* synthetic */ MaterialListPreference(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MaterialListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    public void onClick() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext());
        materialAlertDialogBuilder.setSingleChoiceItems(getEntries(), getValueIndex(), new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.custom_view.MaterialListPreference$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MaterialListPreference.onClick$lambda$2$lambda$0(MaterialListPreference.this, dialogInterface, i);
            }
        });
        materialAlertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.custom_view.MaterialListPreference$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        materialAlertDialogBuilder.setTitle(getTitle());
        String str = this.neutralText;
        if (str != null) {
            materialAlertDialogBuilder.setNeutralButton((CharSequence) str, this.neutralAction);
        }
        materialAlertDialogBuilder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$2$lambda$0(MaterialListPreference this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.callChangeListener(this$0.getEntryValues()[i].toString())) {
            this$0.setValueIndex(i);
        }
        dialogInterface.dismiss();
    }

    private final int getValueIndex() {
        CharSequence[] entryValues = getEntryValues();
        Intrinsics.checkNotNullExpressionValue(entryValues, "getEntryValues(...)");
        return ArraysKt.indexOf((String[]) entryValues, getValue());
    }
}
