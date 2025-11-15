package com.redphx.betterxc.worker;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CheckUpdateWorker.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.redphx.betterxc.worker.CheckUpdateWorker", f = "CheckUpdateWorker.kt", i = {0, 0}, l = {35}, m = "doWork", n = {"buffer", "sb"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class CheckUpdateWorker$doWork$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CheckUpdateWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckUpdateWorker$doWork$1(CheckUpdateWorker checkUpdateWorker, Continuation<? super CheckUpdateWorker$doWork$1> continuation) {
        super(continuation);
        this.this$0 = checkUpdateWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doWork(this);
    }
}
