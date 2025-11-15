package com.redphx.betterxc.util;

import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import com.redphx.betterxc.activity.MainActivity;
import com.redphx.betterxc.data.BxFlags;
import com.redphx.betterxc.task.UpdateScriptTask;
import com.redphx.betterxc.util.UserscriptUtil;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: UserscriptUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/UserscriptUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class UserscriptUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: UserscriptUtil.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/redphx/betterxc/util/UserscriptUtil$Companion;", "", "<init>", "()V", "setup", "", "activity", "Lcom/redphx/betterxc/activity/MainActivity;", "bxFlags", "Lcom/redphx/betterxc/data/BxFlags;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String setup(final MainActivity activity, BxFlags bxFlags) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(bxFlags, "bxFlags");
            MainActivity mainActivity = activity;
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
            String string = defaultSharedPreferences.getString(Constants.PREF_CUSTOM_USERSCRIPT, "");
            if (!defaultSharedPreferences.getBoolean("enable_bx_script", true)) {
                return string != null ? string : "";
            }
            String readAssetText = Intrinsics.areEqual(defaultSharedPreferences.getString("bx_script_variant", "stable"), "local") ? Utils.INSTANCE.readAssetText(mainActivity, "userscripts/test.user.js") : null;
            if (readAssetText == null) {
                readAssetText = Utils.INSTANCE.readInternalFile(mainActivity, "userscripts/better-xcloud.user.js");
            }
            String str = readAssetText;
            if (str == null) {
                UpdateScriptTask.Companion.run$default(UpdateScriptTask.INSTANCE, activity, null, new Function0() { // from class: com.redphx.betterxc.util.UserscriptUtil$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = UserscriptUtil.Companion.setup$lambda$0(MainActivity.this);
                        return unit;
                    }
                }, 2, null);
                return null;
            }
            String readAssetText2 = Utils.INSTANCE.readAssetText(mainActivity, "userscripts/additional.user.js");
            Intrinsics.checkNotNull(readAssetText2);
            JsonAdapter adapter = new Moshi.Builder().build().adapter(BxFlags.class);
            Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
            String json = adapter.toJson(bxFlags);
            Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
            String replace$default = StringsKt.replace$default(StringsKt.replace$default(readAssetText2, "[[BX_FLAGS]]", json, false, 4, (Object) null), "[[REMAP_BACK_BUTTON]]", String.valueOf(defaultSharedPreferences.getBoolean("remap_back_button", false)), false, 4, (Object) null);
            if (string != null) {
                replace$default = string + ";\n" + replace$default;
            }
            return StringsKt.replace$default(str, "/* ADDITIONAL CODE */", replace$default, false, 4, (Object) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit setup$lambda$0(MainActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "$activity");
            activity.recreate();
            return Unit.INSTANCE;
        }
    }
}
