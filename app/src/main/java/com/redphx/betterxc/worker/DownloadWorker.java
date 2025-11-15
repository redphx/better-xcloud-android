package com.redphx.betterxc.worker;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadWorker.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0097@¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/redphx/betterxc/worker/DownloadWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DownloadWorker extends CoroutineWorker {
    public static final String PROGRESS = "progress";
    public static final String TAG = "DownloadWorker";
    private final Context context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.context = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x025e, code lost:
    
        r3 = r7;
        r0.element += r9.element;
        r7 = new kotlin.Pair[]{kotlin.TuplesKt.to("progress", kotlin.coroutines.jvm.internal.Boxing.boxInt((int) ((100 * r0.element) / r4)))};
        r3 = new androidx.work.Data.Builder();
        r6 = r7[0];
        r3.put((java.lang.String) r6.getFirst(), r6.getSecond());
        r3 = r3.build();
        r2.L$0 = r13;
        r2.L$1 = r12;
        r2.L$2 = r11;
        r2.L$3 = r0;
        r2.L$4 = r9;
        r2.L$5 = r1;
        r2.L$6 = r14;
        r2.L$7 = r10;
        r2.L$8 = r15;
        r2.L$9 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x02b6, code lost:
    
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x02b8, code lost:
    
        r2.L$10 = r6;
        r7 = r3;
        r2.L$11 = r7;
        r2.I$0 = r4;
        r24 = r0;
        r2.label = 3;
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x02cb, code lost:
    
        if (r13.setProgress(r3, r2) != r0) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x02da, code lost:
    
        r6 = r24;
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x02e0, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r6, r21);
        r1 = new kotlin.Pair[]{kotlin.TuplesKt.to(r20, r12), kotlin.TuplesKt.to(r19, r11)};
        r0 = new androidx.work.Data.Builder();
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0302, code lost:
    
        if (r13 >= 2) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0304, code lost:
    
        r2 = r1[r13];
        r0.put((java.lang.String) r2.getFirst(), r2.getSecond());
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0316, code lost:
    
        r14.element = r0.build();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0320, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r15, r17);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0327, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r10, r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0332, code lost:
    
        return androidx.work.ListenableWorker.Result.success((androidx.work.Data) r14.element);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0333, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0334, code lost:
    
        r1 = r0;
        r14 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0337, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0338, code lost:
    
        r1 = r0;
        r14 = r10;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x020b A[Catch: all -> 0x0355, TryCatch #6 {all -> 0x0355, blocks: (B:98:0x01f1, B:100:0x020b, B:101:0x020e, B:103:0x0223, B:104:0x0227), top: B:97:0x01f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0223 A[Catch: all -> 0x0355, TryCatch #6 {all -> 0x0355, blocks: (B:98:0x01f1, B:100:0x020b, B:101:0x020e, B:103:0x0223, B:104:0x0227), top: B:97:0x01f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0255 A[Catch: all -> 0x033b, TryCatch #10 {all -> 0x033b, blocks: (B:22:0x024c, B:24:0x0255, B:28:0x025e), top: B:21:0x024c }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02da A[EDGE_INSN: B:34:0x02da->B:35:0x02da BREAK  A[LOOP:0: B:18:0x0248->B:26:0x02d4], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    /* JADX WARN: Type inference failed for: r0v47, types: [T, androidx.work.Data] */
    /* JADX WARN: Type inference failed for: r13v18, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r15v17, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x02cb -> B:16:0x02ce). Please report as a decompilation issue!!! */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r29) {
        /*
            Method dump skipped, instructions count: 888
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redphx.betterxc.worker.DownloadWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
