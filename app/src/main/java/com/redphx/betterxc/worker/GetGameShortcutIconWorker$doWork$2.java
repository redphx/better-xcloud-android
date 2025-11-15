package com.redphx.betterxc.worker;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GetGameShortcutIconWorker.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.redphx.betterxc.worker.GetGameShortcutIconWorker$doWork$2", f = "GetGameShortcutIconWorker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class GetGameShortcutIconWorker$doWork$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Bitmap $iconBitmap;
    final /* synthetic */ String $iconName;
    int label;
    final /* synthetic */ GetGameShortcutIconWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetGameShortcutIconWorker$doWork$2(GetGameShortcutIconWorker getGameShortcutIconWorker, String str, Bitmap bitmap, Continuation<? super GetGameShortcutIconWorker$doWork$2> continuation) {
        super(2, continuation);
        this.this$0 = getGameShortcutIconWorker;
        this.$iconName = str;
        this.$iconBitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GetGameShortcutIconWorker$doWork$2(this.this$0, this.$iconName, this.$iconBitmap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((GetGameShortcutIconWorker$doWork$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            context = this.this$0.context;
            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getCacheDir(), this.$iconName));
            try {
                Boolean boxBoolean = Boxing.boxBoolean(this.$iconBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream));
                CloseableKt.closeFinally(fileOutputStream, null);
                return boxBoolean;
            } finally {
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
