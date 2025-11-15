package com.redphx.betterxc.task;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.redphx.betterxc.R;
import com.redphx.betterxc.task.CheckUpdateTask;
import com.redphx.betterxc.util.Constants;
import com.redphx.betterxc.util.DownloadManagerUtil;
import com.redphx.betterxc.util.Utils;
import com.redphx.betterxc.worker.CheckUpdateWorker;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

/* compiled from: CheckUpdateTask.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/task/CheckUpdateTask;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CheckUpdateTask {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String downloadFileName = "";

    /* compiled from: CheckUpdateTask.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bJ \u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/redphx/betterxc/task/CheckUpdateTask$Companion;", "", "<init>", "()V", "downloadFileName", "", "run", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "onSuccess", "Lkotlin/Function0;", "askForUpdate", "Landroid/app/Activity;", "versionName", "downloadUrl", "compareVersion", "", "newCode", "", "newName", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {

        /* compiled from: CheckUpdateTask.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[WorkInfo.State.values().length];
                try {
                    iArr[WorkInfo.State.SUCCEEDED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void run(final FragmentActivity activity, Function0<Unit> onSuccess) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
            OneTimeWorkRequest build = new OneTimeWorkRequest.Builder((Class<? extends ListenableWorker>) CheckUpdateWorker.class).build();
            WorkManager companion = WorkManager.INSTANCE.getInstance(activity);
            companion.enqueue(build);
            companion.getWorkInfoByIdLiveData(build.getId()).observe(activity, new CheckUpdateTask$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.redphx.betterxc.task.CheckUpdateTask$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit run$lambda$0;
                    run$lambda$0 = CheckUpdateTask.Companion.run$lambda$0(FragmentActivity.this, (WorkInfo) obj);
                    return run$lambda$0;
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit run$lambda$0(FragmentActivity activity, WorkInfo workInfo) {
            Intrinsics.checkNotNullParameter(activity, "$activity");
            WorkInfo.State state = workInfo != null ? workInfo.getState() : null;
            if ((state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) == 1) {
                Data outputData = workInfo.getOutputData();
                int i = outputData.getInt(CheckUpdateWorker.VERSION_CODE, -1);
                String string = outputData.getString(CheckUpdateWorker.VERSION_NAME);
                Intrinsics.checkNotNull(string);
                String string2 = outputData.getString(CheckUpdateWorker.DOWNLOAD_URL);
                Intrinsics.checkNotNull(string2);
                FragmentActivity fragmentActivity = activity;
                if (CheckUpdateTask.INSTANCE.compareVersion(fragmentActivity, i, string)) {
                    CheckUpdateTask.INSTANCE.askForUpdate(fragmentActivity, string, string2);
                }
            }
            return Unit.INSTANCE;
        }

        private final void askForUpdate(final Activity activity, final String versionName, final String downloadUrl) {
            new MaterialAlertDialogBuilder(activity).setTitle((CharSequence) activity.getString(R.string.update_available)).setMessage((CharSequence) ("Version " + versionName + " is available. Do you want to update?")).setIcon(android.R.drawable.ic_dialog_info).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.redphx.betterxc.task.CheckUpdateTask$Companion$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CheckUpdateTask.Companion.askForUpdate$lambda$1(activity, downloadUrl, versionName, dialogInterface, i);
                }
            }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void askForUpdate$lambda$1(Activity activity, String downloadUrl, String versionName, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(activity, "$activity");
            Intrinsics.checkNotNullParameter(downloadUrl, "$downloadUrl");
            Intrinsics.checkNotNullParameter(versionName, "$versionName");
            Activity activity2 = activity;
            Toast.makeText(activity2, R.string.downloading, 0).show();
            Companion companion = CheckUpdateTask.INSTANCE;
            CheckUpdateTask.downloadFileName = (String) CollectionsKt.last(StringsKt.split$default((CharSequence) downloadUrl, new String[]{"/"}, false, 0, 6, (Object) null));
            DownloadManagerUtil.INSTANCE.download(activity, downloadUrl, CheckUpdateTask.downloadFileName, "Better xCloud " + versionName);
            dialogInterface.dismiss();
            Utils.Companion.openUrl$default(Utils.INSTANCE, activity2, Constants.URL_APP_RELEASES, false, 4, null);
        }

        private final boolean compareVersion(Activity activity, int newCode, String newName) {
            long longVersionCode;
            String str;
            try {
                PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
                Intrinsics.checkNotNullExpressionValue(packageInfo, "getPackageInfo(...)");
                longVersionCode = PackageInfoCompat.getLongVersionCode(packageInfo);
                str = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (newCode > longVersionCode) {
                return true;
            }
            return new DefaultArtifactVersion(newName).compareTo((ArtifactVersion) new DefaultArtifactVersion(str)) > 0;
        }
    }
}
