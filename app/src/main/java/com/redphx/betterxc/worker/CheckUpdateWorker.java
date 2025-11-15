package com.redphx.betterxc.worker;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: CheckUpdateWorker.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0097@¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lcom/redphx/betterxc/worker/CheckUpdateWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CheckUpdateWorker extends CoroutineWorker {
    public static final String DOWNLOAD_URL = "download_url";
    public static final String VERSION_CODE = "version_code";
    public static final String VERSION_NAME = "version_name";
    private static final Regex NAME_REGEX = new Regex("^com\\.redphx\\.betterxc_(?<versionName>[\\w.]+)-(?<versionCode>\\d+)\\.apk$");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckUpdateWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a6 A[Catch: all -> 0x015d, LOOP:0: B:17:0x009d->B:19:0x00a6, LOOP_END, TryCatch #3 {all -> 0x015d, blocks: (B:16:0x009a, B:17:0x009d, B:19:0x00a6, B:21:0x00b1), top: B:15:0x009a, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b1 A[EDGE_INSN: B:20:0x00b1->B:21:0x00b1 BREAK  A[LOOP:0: B:17:0x009d->B:19:0x00a6], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d1 A[Catch: Exception -> 0x016b, TryCatch #1 {Exception -> 0x016b, blocks: (B:11:0x0031, B:12:0x008e, B:23:0x00b8, B:25:0x00d1, B:29:0x00e8, B:31:0x011c, B:33:0x0142, B:35:0x0154, B:53:0x0167, B:54:0x016a, B:58:0x0040, B:60:0x005f, B:62:0x0069, B:14:0x0090, B:22:0x00b3, B:46:0x0160, B:47:0x0163, B:16:0x009a, B:17:0x009d, B:19:0x00a6, B:21:0x00b1, B:43:0x015e, B:50:0x0165), top: B:7:0x0025, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0142 A[Catch: Exception -> 0x016b, LOOP:2: B:32:0x0140->B:33:0x0142, LOOP_END, TryCatch #1 {Exception -> 0x016b, blocks: (B:11:0x0031, B:12:0x008e, B:23:0x00b8, B:25:0x00d1, B:29:0x00e8, B:31:0x011c, B:33:0x0142, B:35:0x0154, B:53:0x0167, B:54:0x016a, B:58:0x0040, B:60:0x005f, B:62:0x0069, B:14:0x0090, B:22:0x00b3, B:46:0x0160, B:47:0x0163, B:16:0x009a, B:17:0x009d, B:19:0x00a6, B:21:0x00b1, B:43:0x015e, B:50:0x0165), top: B:7:0x0025, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0118 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r10) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redphx.betterxc.worker.CheckUpdateWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
