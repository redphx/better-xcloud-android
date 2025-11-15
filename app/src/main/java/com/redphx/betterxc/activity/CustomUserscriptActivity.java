package com.redphx.betterxc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import com.redphx.betterxc.R;
import com.redphx.betterxc.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CustomUserscriptActivity.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0015J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u0007H\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/redphx/betterxc/activity/CustomUserscriptActivity;", "Lcom/redphx/betterxc/activity/BaseActivity;", "<init>", "()V", "editUserscript", "Landroidx/appcompat/widget/AppCompatEditText;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "saveUserscript", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CustomUserscriptActivity extends BaseActivity {
    private AppCompatEditText editUserscript;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.redphx.betterxc.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_userscript);
        View findViewById = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        setSupportActionBar((Toolbar) findViewById);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setTitle(getResources().getString(R.string.custom_script));
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        this.editUserscript = (AppCompatEditText) findViewById(R.id.edit_userscript);
        String string = defaultSharedPreferences.getString(Constants.PREF_CUSTOM_USERSCRIPT, "");
        Intrinsics.checkNotNull(string);
        String obj = StringsKt.trim((CharSequence) string).toString();
        if (obj.length() > 0) {
            obj = obj + '\n';
        }
        AppCompatEditText appCompatEditText = this.editUserscript;
        if (appCompatEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editUserscript");
            appCompatEditText = null;
        }
        appCompatEditText.setText(obj);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom_userscript, menu);
        return true;
    }

    private final void saveUserscript() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
        StringBuilder sb = new StringBuilder();
        AppCompatEditText appCompatEditText = this.editUserscript;
        if (appCompatEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editUserscript");
            appCompatEditText = null;
        }
        edit.putString(Constants.PREF_CUSTOM_USERSCRIPT, sb.append(StringsKt.trim((CharSequence) String.valueOf(appCompatEditText.getText())).toString()).append('\n').toString());
        edit.apply();
        Toast.makeText(getBaseContext(), getString(R.string.saved), 0).show();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId != R.id.action_save) {
            if (itemId == 16908332) {
                finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
        saveUserscript();
        Intent intent = new Intent();
        intent.putExtra("needRestart", true);
        setResult(-1, intent);
        return true;
    }
}
