package com.redphx.betterxc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherKt;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.redphx.betterxc.R;
import com.redphx.betterxc.fragment.SettingsFragment;
import com.redphx.betterxc.util.DialogUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsActivity.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0014J\u0018\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Lcom/redphx/betterxc/activity/SettingsActivity;", "Lcom/redphx/betterxc/activity/BaseActivity;", "Landroidx/preference/PreferenceFragmentCompat$OnPreferenceStartFragmentCallback;", "<init>", "()V", "needRefresh", "", "getNeedRefresh", "()Z", "setNeedRefresh", "(Z)V", "needRestart", "getNeedRestart", "setNeedRestart", "storageRequestResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "getStorageRequestResult", "()Landroidx/activity/result/ActivityResultLauncher;", "setStorageRequestResult", "(Landroidx/activity/result/ActivityResultLauncher;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showRestartDialog", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onDestroy", "onPreferenceStartFragment", "caller", "Landroidx/preference/PreferenceFragmentCompat;", "pref", "Landroidx/preference/Preference;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SettingsActivity extends BaseActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    private boolean needRefresh;
    private boolean needRestart;
    public ActivityResultLauncher<String> storageRequestResult;

    @Override // androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(pref, "pref");
        return true;
    }

    public final boolean getNeedRefresh() {
        return this.needRefresh;
    }

    public final void setNeedRefresh(boolean z) {
        this.needRefresh = z;
    }

    public final boolean getNeedRestart() {
        return this.needRestart;
    }

    public final void setNeedRestart(boolean z) {
        this.needRestart = z;
    }

    public final ActivityResultLauncher<String> getStorageRequestResult() {
        ActivityResultLauncher<String> activityResultLauncher = this.storageRequestResult;
        if (activityResultLauncher != null) {
            return activityResultLauncher;
        }
        Intrinsics.throwUninitializedPropertyAccessException("storageRequestResult");
        return null;
    }

    public final void setStorageRequestResult(ActivityResultLauncher<String> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
        this.storageRequestResult = activityResultLauncher;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.redphx.betterxc.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        View findViewById = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        setSupportActionBar((Toolbar) findViewById);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setTitle(getResources().getString(R.string.settings));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, new SettingsFragment()).commit();
        OnBackPressedDispatcherKt.addCallback$default(getOnBackPressedDispatcher(), null, false, new Function1() { // from class: com.redphx.betterxc.activity.SettingsActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$1;
                onCreate$lambda$1 = SettingsActivity.onCreate$lambda$1(SettingsActivity.this, (OnBackPressedCallback) obj);
                return onCreate$lambda$1;
            }
        }, 3, null);
        setStorageRequestResult(registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.redphx.betterxc.activity.SettingsActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SettingsActivity.onCreate$lambda$2(SettingsActivity.this, ((Boolean) obj).booleanValue());
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$1(SettingsActivity this$0, OnBackPressedCallback addCallback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(addCallback, "$this$addCallback");
        this$0.showRestartDialog();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(SettingsActivity this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            Toast.makeText(this$0.getBaseContext(), "Granted", 0).show();
        }
    }

    private final void showRestartDialog() {
        if (!this.needRestart) {
            finish();
        } else {
            DialogUtil.Companion.showForceCloseRequiredDialog$default(DialogUtil.INSTANCE, this, null, 2, null);
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            showRestartDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.needRefresh) {
            Intent intent = new Intent(getBaseContext(), (Class<?>) MainActivity.class);
            intent.setFlags(604045312);
            intent.putExtra("recreate", true);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Settings changed. Restarting web page...", 0).show();
        }
        super.onDestroy();
    }
}
