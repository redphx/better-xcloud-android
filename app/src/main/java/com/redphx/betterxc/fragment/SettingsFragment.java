package com.redphx.betterxc.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import com.redphx.betterxc.R;
import com.redphx.betterxc.activity.CustomUserscriptActivity;
import com.redphx.betterxc.activity.SettingsActivity;
import com.redphx.betterxc.dialog.WebViewManagerDialog;
import com.redphx.betterxc.task.UpdateScriptTask;
import com.redphx.betterxc.util.Constants;
import com.redphx.betterxc.util.CrashTracker;
import com.redphx.betterxc.util.DeviceInfo;
import com.redphx.betterxc.util.StorageUtil;
import com.redphx.betterxc.util.UiUtil;
import com.redphx.betterxc.util.Utils;
import com.redphx.betterxc.util.WebViewUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: SettingsFragment.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001c\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016J\b\u0010\u0013\u001a\u00020\tH\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/redphx/betterxc/fragment/SettingsFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "<init>", "()V", "startForResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "removePreference", "", "category", "Landroidx/preference/PreferenceCategory;", "key", "", "onCreatePreferences", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "onResume", "onPause", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private ActivityResultLauncher<Intent> startForResult;

    private final void removePreference(PreferenceCategory category, String key) {
        Preference findPreference = category.findPreference(key);
        if (findPreference != null) {
            category.removePreference(findPreference);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        ListPreference listPreference;
        Preference findPreference;
        PreferenceCategory preferenceCategory;
        Display.Mode mode;
        setPreferencesFromResource(R.xml.preferences, rootKey);
        Preference findPreference2 = findPreference("check_for_update");
        if (findPreference2 != null) {
            findPreference2.setSummary("0.24.2 (39)");
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        this.startForResult = ((AppCompatActivity) activity).registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.redphx.betterxc.fragment.SettingsFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                SettingsFragment.onCreatePreferences$lambda$1(SettingsFragment.this, (ActivityResult) obj);
            }
        });
        PreferenceCategory preferenceCategory2 = (PreferenceCategory) getPreferenceManager().findPreference("category_general");
        if (preferenceCategory2 != null) {
            if (DeviceInfo.INSTANCE.isAndroidTv()) {
                removePreference(preferenceCategory2, "force_landscape_mode");
                removePreference(preferenceCategory2, "low_battery_indicator");
                removePreference(preferenceCategory2, "screen_force_60hz");
                removePreference(preferenceCategory2, "remap_back_button");
            } else {
                Display.Mode[] supportedModes = ContextCompat.getDisplayOrDefault(requireContext()).getSupportedModes();
                Intrinsics.checkNotNullExpressionValue(supportedModes, "getSupportedModes(...)");
                Display.Mode[] modeArr = supportedModes;
                int length = modeArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        mode = null;
                        break;
                    }
                    mode = modeArr[i];
                    if (((int) mode.getRefreshRate()) > 62) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (mode == null) {
                    removePreference(preferenceCategory2, "screen_force_60hz");
                }
                removePreference(preferenceCategory2, "tv_allm");
            }
            StorageUtil.Companion companion = StorageUtil.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            if (companion.isPermissionGranted(requireContext)) {
                removePreference(preferenceCategory2, "grant_storage_permission");
            }
            if (preferenceCategory2.getPreferenceCount() == 0) {
                getPreferenceScreen().removePreference(preferenceCategory2);
            }
        }
        if (Build.VERSION.SDK_INT < 31 && (preferenceCategory = (PreferenceCategory) getPreferenceManager().findPreference("category_ui")) != null) {
            removePreference(preferenceCategory, "ui_material_you");
        }
        PreferenceCategory preferenceCategory3 = (PreferenceCategory) getPreferenceManager().findPreference("category_webview");
        if (preferenceCategory3 != null && (findPreference = preferenceCategory3.findPreference("webview_manager")) != null) {
            WebViewUtil.Companion companion2 = WebViewUtil.INSTANCE;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
            findPreference.setSummary(companion2.toString(requireContext2, WebViewUtil.INSTANCE.getCurrentProvider()));
        }
        PreferenceCategory preferenceCategory4 = (PreferenceCategory) getPreferenceManager().findPreference("category_userscript");
        if (preferenceCategory4 == null || (listPreference = (ListPreference) preferenceCategory4.findPreference("bx_script_variant")) == null) {
            return;
        }
        Utils.Companion companion3 = Utils.INSTANCE;
        CharSequence[] entries = listPreference.getEntries();
        Intrinsics.checkNotNullExpressionValue(entries, "getEntries(...)");
        int length2 = listPreference.getEntries().length - 1;
        List mutableList = ArraysKt.toMutableList(entries);
        if (length2 >= 0 && length2 < mutableList.size()) {
            mutableList.remove(length2);
        }
        listPreference.setEntries((CharSequence[]) mutableList.toArray(new CharSequence[0]));
        Utils.Companion companion4 = Utils.INSTANCE;
        CharSequence[] entryValues = listPreference.getEntryValues();
        Intrinsics.checkNotNullExpressionValue(entryValues, "getEntryValues(...)");
        int length3 = listPreference.getEntryValues().length - 1;
        List mutableList2 = ArraysKt.toMutableList(entryValues);
        if (length3 >= 0 && length3 < mutableList2.size()) {
            mutableList2.remove(length3);
        }
        listPreference.setEntryValues((CharSequence[]) mutableList2.toArray(new CharSequence[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$lambda$1(SettingsFragment this$0, ActivityResult result) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            Intrinsics.checkNotNull(data);
            boolean booleanExtra = data.getBooleanExtra("needRestart", false);
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
            ((SettingsActivity) activity).setNeedRestart(booleanExtra);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v9, types: [T, com.redphx.betterxc.dialog.WebViewManagerDialog] */
    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        String key = preference.getKey();
        if (key != null) {
            switch (key.hashCode()) {
                case -2041683484:
                    if (key.equals(Constants.PREF_CUSTOM_USERSCRIPT)) {
                        Intent intent = new Intent(getContext(), (Class<?>) CustomUserscriptActivity.class);
                        ActivityResultLauncher<Intent> activityResultLauncher = this.startForResult;
                        if (activityResultLauncher == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("startForResult");
                            activityResultLauncher = null;
                        }
                        activityResultLauncher.launch(intent);
                        return true;
                    }
                    break;
                case -1100827527:
                    if (key.equals("web_homepage")) {
                        Utils.Companion companion = Utils.INSTANCE;
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
                        Utils.Companion.openUrl$default(companion, requireContext, "https://better-xcloud.github.io", false, 4, null);
                        return true;
                    }
                    break;
                case -1023352610:
                    if (key.equals("update_bx_script")) {
                        UpdateScriptTask.Companion companion2 = UpdateScriptTask.INSTANCE;
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                        UpdateScriptTask.Companion.run$default(companion2, requireActivity, null, new Function0() { // from class: com.redphx.betterxc.fragment.SettingsFragment$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                Unit onPreferenceTreeClick$lambda$10;
                                onPreferenceTreeClick$lambda$10 = SettingsFragment.onPreferenceTreeClick$lambda$10(SettingsFragment.this);
                                return onPreferenceTreeClick$lambda$10;
                            }
                        }, 2, null);
                        return true;
                    }
                    break;
                case -805769411:
                    if (key.equals("web_keyjs")) {
                        Utils.Companion companion3 = Utils.INSTANCE;
                        Context requireContext2 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
                        Utils.Companion.openUrl$default(companion3, requireContext2, "https://keyjs.dev/", false, 4, null);
                        return true;
                    }
                    break;
                case -718720206:
                    if (key.equals("web_kofi")) {
                        Utils.Companion companion4 = Utils.INSTANCE;
                        Context requireContext3 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext(...)");
                        companion4.openUrl(requireContext3, "https://ko-fi.com/redphx", true);
                        return true;
                    }
                    break;
                case -663164569:
                    if (key.equals("webview_manager")) {
                        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        FragmentActivity requireActivity2 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                        objectRef.element = new WebViewManagerDialog(requireActivity2, preference, new DialogInterface.OnDismissListener() { // from class: com.redphx.betterxc.fragment.SettingsFragment$$ExternalSyntheticLambda2
                            @Override // android.content.DialogInterface.OnDismissListener
                            public final void onDismiss(DialogInterface dialogInterface) {
                                SettingsFragment.onPreferenceTreeClick$lambda$9(SettingsFragment.this, objectRef, dialogInterface);
                            }
                        });
                        ((WebViewManagerDialog) objectRef.element).show();
                        return true;
                    }
                    break;
                case -601578160:
                    if (key.equals("web_telegram_channel")) {
                        Utils.Companion companion5 = Utils.INSTANCE;
                        Context requireContext4 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext(...)");
                        companion5.openUrl(requireContext4, "https://t.me/betterxcloud", true);
                        return true;
                    }
                    break;
                case 956570536:
                    if (key.equals("web_gamepad_tester")) {
                        Utils.Companion companion6 = Utils.INSTANCE;
                        Context requireContext5 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext(...)");
                        Utils.Companion.openUrl$default(companion6, requireContext5, "https://hardwaretester.com/gamepad", false, 4, null);
                        return true;
                    }
                    break;
                case 1400141406:
                    if (key.equals("web_github_android")) {
                        Utils.Companion companion7 = Utils.INSTANCE;
                        Context requireContext6 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext(...)");
                        companion7.openUrl(requireContext6, "https://github.com/redphx/better-xcloud-android", true);
                        return true;
                    }
                    break;
                case 1913186412:
                    if (key.equals("webview_devtools")) {
                        try {
                            startActivity(new Intent("com.android.webview.SHOW_DEV_UI"));
                        } catch (Exception unused) {
                            Toast.makeText(getContext(), "No WebView DevTools found", 0).show();
                        }
                        return true;
                    }
                    break;
                case 2080138774:
                    if (key.equals("grant_storage_permission")) {
                        FragmentActivity activity = getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
                        SettingsActivity settingsActivity = (SettingsActivity) activity;
                        StorageUtil.INSTANCE.requestPermission(settingsActivity, settingsActivity.getStorageRequestResult());
                        return true;
                    }
                    break;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onPreferenceTreeClick$lambda$9(SettingsFragment this$0, Ref.ObjectRef dialog, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
        T t = dialog.element;
        Intrinsics.checkNotNull(t);
        ((SettingsActivity) activity).setNeedRestart(((WebViewManagerDialog) t).changedWebView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onPreferenceTreeClick$lambda$10(SettingsFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
        ((SettingsActivity) activity).setNeedRefresh(true);
        return Unit.INSTANCE;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        if (key != null) {
            int hashCode = key.hashCode();
            if (hashCode != -774244326) {
                if (hashCode != 57298104) {
                    if (hashCode == 110327241 && key.equals("theme")) {
                        UiUtil.Companion companion = UiUtil.INSTANCE;
                        String string = sharedPreferences.getString(key, "dark");
                        Intrinsics.checkNotNull(string);
                        companion.setNightMode(string);
                    }
                } else if (key.equals("webview_implementation")) {
                    CrashTracker.INSTANCE.reset(true);
                }
            } else if (key.equals("bx_script_variant") && !Intrinsics.areEqual(sharedPreferences.getString(key, ""), "local")) {
                UpdateScriptTask.Companion companion2 = UpdateScriptTask.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                UpdateScriptTask.Companion.run$default(companion2, requireActivity, null, new Function0() { // from class: com.redphx.betterxc.fragment.SettingsFragment$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                }, 2, null);
            }
        }
        if (key != null) {
            switch (key.hashCode()) {
                case -2041683484:
                    if (!key.equals(Constants.PREF_CUSTOM_USERSCRIPT)) {
                        return;
                    }
                    break;
                case -1775412076:
                    if (!key.equals("remap_back_button")) {
                        return;
                    }
                    break;
                case -1177933614:
                    if (!key.equals("ui_material_you")) {
                        return;
                    }
                    FragmentActivity activity = getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
                    ((SettingsActivity) activity).setNeedRestart(true);
                    return;
                case -954784119:
                    if (!key.equals("tv_allm")) {
                        return;
                    }
                    FragmentActivity activity2 = getActivity();
                    Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
                    ((SettingsActivity) activity2).setNeedRestart(true);
                    return;
                case -774244326:
                    if (!key.equals("bx_script_variant")) {
                        return;
                    }
                    break;
                case -718273058:
                    if (!key.equals("web_zoom")) {
                        return;
                    }
                    break;
                case -265958277:
                    if (!key.equals("force_landscape_mode")) {
                        return;
                    }
                    break;
                case 25734424:
                    if (!key.equals("enable_bx_script")) {
                        return;
                    }
                    break;
                case 57298104:
                    if (!key.equals("webview_implementation")) {
                        return;
                    }
                    FragmentActivity activity22 = getActivity();
                    Intrinsics.checkNotNull(activity22, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
                    ((SettingsActivity) activity22).setNeedRestart(true);
                    return;
                case 366460031:
                    if (!key.equals("web_zoom_while_playing")) {
                        return;
                    }
                    break;
                case 418896434:
                    if (!key.equals("low_battery_indicator")) {
                        return;
                    }
                    break;
                case 480412628:
                    if (!key.equals("webview_debuggable")) {
                        return;
                    }
                    FragmentActivity activity222 = getActivity();
                    Intrinsics.checkNotNull(activity222, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
                    ((SettingsActivity) activity222).setNeedRestart(true);
                    return;
                case 714387555:
                    if (!key.equals("webview_optimize")) {
                        return;
                    }
                    FragmentActivity activity2222 = getActivity();
                    Intrinsics.checkNotNull(activity2222, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
                    ((SettingsActivity) activity2222).setNeedRestart(true);
                    return;
                case 828152229:
                    if (!key.equals(Constants.PREF_WEB_LOCALE)) {
                        return;
                    }
                    break;
                case 1220407251:
                    if (!key.equals("screen_force_60hz")) {
                        return;
                    }
                    FragmentActivity activity22222 = getActivity();
                    Intrinsics.checkNotNull(activity22222, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
                    ((SettingsActivity) activity22222).setNeedRestart(true);
                    return;
                default:
                    return;
            }
            FragmentActivity activity3 = getActivity();
            Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.redphx.betterxc.activity.SettingsActivity");
            ((SettingsActivity) activity3).setNeedRefresh(true);
        }
    }
}
