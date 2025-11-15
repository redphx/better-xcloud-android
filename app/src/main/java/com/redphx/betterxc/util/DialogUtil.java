package com.redphx.betterxc.util;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.redphx.betterxc.util.DialogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DialogUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/DialogUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DialogUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: DialogUtil.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lcom/redphx/betterxc/util/DialogUtil$Companion;", "", "<init>", "()V", "showForceCloseRequiredDialog", "", "context", "Landroid/content/Context;", "onDismiss", "Landroid/content/DialogInterface$OnDismissListener;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void showForceCloseRequiredDialog$default(Companion companion, Context context, DialogInterface.OnDismissListener onDismissListener, int i, Object obj) {
            if ((i & 2) != 0) {
                onDismissListener = null;
            }
            companion.showForceCloseRequiredDialog(context, onDismissListener);
        }

        public final void showForceCloseRequiredDialog(final Context context, DialogInterface.OnDismissListener onDismiss) {
            Intrinsics.checkNotNullParameter(context, "context");
            new MaterialAlertDialogBuilder(context).setTitle((CharSequence) "Restart required").setMessage((CharSequence) "Please close the app completely (force close) and open it again to apply the changes.").setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.util.DialogUtil$Companion$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogUtil.Companion.showForceCloseRequiredDialog$lambda$0(context, dialogInterface, i);
                }
            }).setOnDismissListener(onDismiss).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void showForceCloseRequiredDialog$lambda$0(Context context, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(context, "$context");
            try {
                Utils.INSTANCE.openAppInfo(context);
            } catch (Exception unused) {
            }
        }
    }
}
