package com.redphx.betterxc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashTracker.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/CrashTracker;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CrashTracker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PREF_KEY = "crashes";
    private static boolean initialized;
    private static boolean isWorking;
    private static SharedPreferences sharedPref;

    /* compiled from: CrashTracker.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\fJ\u0010\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/redphx/betterxc/util/CrashTracker$Companion;", "", "<init>", "()V", "initialized", "", "isWorking", "sharedPref", "Landroid/content/SharedPreferences;", "PREF_KEY", "", "init", "", "context", "Landroid/content/Context;", "increase", "reset", "force", "hasCrashes", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void init(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (CrashTracker.initialized) {
                return;
            }
            CrashTracker.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        }

        public final void increase() {
            SharedPreferences sharedPreferences = CrashTracker.sharedPref;
            SharedPreferences sharedPreferences2 = null;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
                sharedPreferences = null;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            SharedPreferences sharedPreferences3 = CrashTracker.sharedPref;
            if (sharedPreferences3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
                sharedPreferences3 = null;
            }
            edit.putInt(CrashTracker.PREF_KEY, sharedPreferences3.getInt(CrashTracker.PREF_KEY, 0) + 1);
            edit.apply();
            SharedPreferences sharedPreferences4 = CrashTracker.sharedPref;
            if (sharedPreferences4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            } else {
                sharedPreferences2 = sharedPreferences4;
            }
            Log.i("CRASH", String.valueOf(sharedPreferences2.getInt(CrashTracker.PREF_KEY, 0)));
        }

        public static /* synthetic */ void reset$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            companion.reset(z);
        }

        public final void reset(boolean force) {
            if (force || !CrashTracker.isWorking) {
                CrashTracker.isWorking = true;
                Log.i("CRASH", "RESET");
                SharedPreferences sharedPreferences = CrashTracker.sharedPref;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
                    sharedPreferences = null;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt(CrashTracker.PREF_KEY, 0);
                edit.apply();
            }
        }

        public final boolean hasCrashes() {
            SharedPreferences sharedPreferences = CrashTracker.sharedPref;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
                sharedPreferences = null;
            }
            return sharedPreferences.getInt(CrashTracker.PREF_KEY, 0) >= 5;
        }
    }
}
