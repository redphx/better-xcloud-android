package com.redphx.betterxc.worker;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GetGameShortcutIconWorker.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.redphx.betterxc.worker.GetGameShortcutIconWorker", f = "GetGameShortcutIconWorker.kt", i = {0, 0, 1, 1}, l = {36, 39}, m = "doWork", n = {"this", "product", "product", "iconName"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes.dex */
public final class GetGameShortcutIconWorker$doWork$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GetGameShortcutIconWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetGameShortcutIconWorker$doWork$1(GetGameShortcutIconWorker getGameShortcutIconWorker, Continuation<? super GetGameShortcutIconWorker$doWork$1> continuation) {
        super(continuation);
        this.this$0 = getGameShortcutIconWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doWork(this);
    }
}
