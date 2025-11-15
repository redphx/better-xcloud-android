package com.redphx.betterxc.task;

import android.app.Dialog;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.redphx.betterxc.R;
import com.redphx.betterxc.task.UpdateScriptTask;
import com.redphx.betterxc.util.Constants;
import com.redphx.betterxc.util.Utils;
import com.redphx.betterxc.worker.DownloadFileParams;
import com.redphx.betterxc.worker.DownloadWorker;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* compiled from: UpdateScriptTask.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/task/UpdateScriptTask;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class UpdateScriptTask {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: UpdateScriptTask.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bJ\u001e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0002JS\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00050\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0002¨\u0006\u0017"}, d2 = {"Lcom/redphx/betterxc/task/UpdateScriptTask$Companion;", "", "<init>", "()V", "run", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "url", "", "onSuccess", "Lkotlin/Function0;", "getLatestBetaUserscriptUrl", "doWork", "inputData", "Landroidx/work/Data;", "showProgress", "", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "outputData", "onFailure", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {

        /* compiled from: UpdateScriptTask.kt */
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
                try {
                    iArr[WorkInfo.State.FAILED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void run$default(Companion companion, FragmentActivity fragmentActivity, String str, Function0 function0, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            companion.run(fragmentActivity, str, function0);
        }

        public final void run(final FragmentActivity activity, String url, final Function0<Unit> onSuccess) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
            if (url == null) {
                if (Intrinsics.areEqual(PreferenceManager.getDefaultSharedPreferences(activity).getString("bx_script_variant", "stable"), "beta")) {
                    getLatestBetaUserscriptUrl(activity, onSuccess);
                    return;
                }
                url = "https://github.com/redphx/better-xcloud/releases/latest/download/better-xcloud.user.js";
            }
            doWork(activity, new Data.Builder().putString(DownloadFileParams.KEY_SOURCE, url).putString(DownloadFileParams.KEY_SAVE_NAME, Constants.MAIN_USERSCRIPT_FILE_NAME).putString(DownloadFileParams.KEY_SAVE_DIR, Constants.TMP_DIR).build(), true, new Function1() { // from class: com.redphx.betterxc.task.UpdateScriptTask$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit run$lambda$1;
                    run$lambda$1 = UpdateScriptTask.Companion.run$lambda$1(FragmentActivity.this, onSuccess, (Data) obj);
                    return run$lambda$1;
                }
            }, new Function0() { // from class: com.redphx.betterxc.task.UpdateScriptTask$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit run$lambda$2;
                    run$lambda$2 = UpdateScriptTask.Companion.run$lambda$2(FragmentActivity.this);
                    return run$lambda$2;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit run$lambda$1(FragmentActivity activity, Function0 onSuccess, Data outputData) {
            Intrinsics.checkNotNullParameter(activity, "$activity");
            Intrinsics.checkNotNullParameter(onSuccess, "$onSuccess");
            Intrinsics.checkNotNullParameter(outputData, "outputData");
            File file = new File(activity.getFilesDir(), Constants.USERSCRIPTS_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            String string = outputData.getString(DownloadFileParams.KEY_SAVE_NAME);
            Intrinsics.checkNotNull(string);
            String string2 = outputData.getString(DownloadFileParams.KEY_SAVE_DIR);
            Intrinsics.checkNotNull(string2);
            FragmentActivity fragmentActivity = activity;
            String readInternalFile = Utils.INSTANCE.readInternalFile(fragmentActivity, string2 + '/' + string);
            Intrinsics.checkNotNull(readInternalFile);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, Constants.MAIN_USERSCRIPT_FILE_NAME));
            try {
                byte[] bytes = readInternalFile.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                fileOutputStream.write(bytes);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
                new File(new File(activity.getFilesDir(), string2), string).delete();
                Toast.makeText(fragmentActivity, "Updated Better xCloud script", 0).show();
                onSuccess.invoke();
                return Unit.INSTANCE;
            } finally {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit run$lambda$2(FragmentActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "$activity");
            Toast.makeText(activity, "Failed", 0).show();
            return Unit.INSTANCE;
        }

        private final void getLatestBetaUserscriptUrl(FragmentActivity activity, Function0<Unit> onSuccess) {
            new OkHttpClient().newCall(new Request.Builder().url("https://api.github.com/repos/redphx/better-xcloud/branches/typescript").header("Accept", "application/vnd.github+json").header("User-Agent", Constants.USER_AGENT).get().build()).enqueue(new UpdateScriptTask$Companion$getLatestBetaUserscriptUrl$1(activity, onSuccess));
        }

        static /* synthetic */ void doWork$default(Companion companion, FragmentActivity fragmentActivity, Data data, boolean z, Function1 function1, Function0 function0, int i, Object obj) {
            if ((i & 4) != 0) {
                z = true;
            }
            companion.doWork(fragmentActivity, data, z, function1, function0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v11, types: [T, android.view.View, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v14, types: [T, android.view.View, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v6, types: [androidx.appcompat.app.AlertDialog, T] */
        private final void doWork(FragmentActivity activity, Data inputData, final boolean showProgress, final Function1<? super Data, Unit> onSuccess, final Function0<Unit> onFailure) {
            OneTimeWorkRequest build = new OneTimeWorkRequest.Builder((Class<? extends ListenableWorker>) DownloadWorker.class).setInputData(inputData).build();
            FragmentActivity fragmentActivity = activity;
            WorkManager companion = WorkManager.INSTANCE.getInstance(fragmentActivity);
            companion.enqueue(build);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            if (showProgress) {
                objectRef.element = new MaterialAlertDialogBuilder(fragmentActivity).setView(R.layout.dialog_progress).setCancelable(false).create();
                ((AlertDialog) objectRef.element).show();
                ?? findViewById = ((AlertDialog) objectRef.element).findViewById(R.id.progress_circular);
                Intrinsics.checkNotNull(findViewById);
                objectRef2.element = findViewById;
                ?? findViewById2 = ((AlertDialog) objectRef.element).findViewById(R.id.text_progress);
                Intrinsics.checkNotNull(findViewById2);
                objectRef3.element = findViewById2;
                ((CircularProgressIndicator) objectRef2.element).setVisibility(0);
            }
            companion.getWorkInfoByIdLiveData(build.getId()).observe(activity, new UpdateScriptTask$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.redphx.betterxc.task.UpdateScriptTask$Companion$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit doWork$lambda$3;
                    doWork$lambda$3 = UpdateScriptTask.Companion.doWork$lambda$3(Function1.this, objectRef, onFailure, showProgress, objectRef2, objectRef3, (WorkInfo) obj);
                    return doWork$lambda$3;
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit doWork$lambda$3(Function1 onSuccess, Ref.ObjectRef progressDialog, Function0 onFailure, boolean z, Ref.ObjectRef progressCircular, Ref.ObjectRef textProgress, WorkInfo workInfo) {
            Intrinsics.checkNotNullParameter(onSuccess, "$onSuccess");
            Intrinsics.checkNotNullParameter(progressDialog, "$progressDialog");
            Intrinsics.checkNotNullParameter(onFailure, "$onFailure");
            Intrinsics.checkNotNullParameter(progressCircular, "$progressCircular");
            Intrinsics.checkNotNullParameter(textProgress, "$textProgress");
            WorkInfo.State state = workInfo != null ? workInfo.getState() : null;
            int i = state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i == 1) {
                onSuccess.invoke(workInfo.getOutputData());
                Dialog dialog = (Dialog) progressDialog.element;
                if (dialog != null) {
                    dialog.dismiss();
                }
            } else if (i == 2) {
                onFailure.invoke();
                Dialog dialog2 = (Dialog) progressDialog.element;
                if (dialog2 != null) {
                    dialog2.dismiss();
                }
            } else {
                Intrinsics.checkNotNull(workInfo);
                Data progress = workInfo.getProgress();
                if (z && !Intrinsics.areEqual(progress, Data.EMPTY)) {
                    int i2 = progress.getInt("progress", 0);
                    CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) progressCircular.element;
                    if (circularProgressIndicator != null) {
                        circularProgressIndicator.setIndeterminate(false);
                    }
                    CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) progressCircular.element;
                    if (circularProgressIndicator2 != null) {
                        circularProgressIndicator2.setProgress(i2);
                    }
                    TextView textView = (TextView) textProgress.element;
                    if (textView != null) {
                        textView.setText(new StringBuilder().append(i2).append('%').toString());
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }
}
