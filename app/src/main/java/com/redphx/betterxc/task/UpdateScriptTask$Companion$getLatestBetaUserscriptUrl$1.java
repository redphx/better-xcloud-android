package com.redphx.betterxc.task;

import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.redphx.betterxc.data.GitHubApiRepoBranch;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: UpdateScriptTask.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/redphx/betterxc/task/UpdateScriptTask$Companion$getLatestBetaUserscriptUrl$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class UpdateScriptTask$Companion$getLatestBetaUserscriptUrl$1 implements Callback {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ Function0<Unit> $onSuccess;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UpdateScriptTask$Companion$getLatestBetaUserscriptUrl$1(FragmentActivity fragmentActivity, Function0<Unit> function0) {
        this.$activity = fragmentActivity;
        this.$onSuccess = function0;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        Toast.makeText(this.$activity, "Failed", 0).show();
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        JsonAdapter adapter = new Moshi.Builder().build().adapter(GitHubApiRepoBranch.class);
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        ResponseBody body = response.body();
        Intrinsics.checkNotNull(body);
        Object fromJson = adapter.fromJson(body.getSource());
        Intrinsics.checkNotNull(fromJson);
        final String str = "https://raw.githubusercontent.com/redphx/better-xcloud/" + ((GitHubApiRepoBranch) fromJson).getCommit().getSha() + "/dist/better-xcloud.user.js?t=" + System.currentTimeMillis();
        final FragmentActivity fragmentActivity = this.$activity;
        final Function0<Unit> function0 = this.$onSuccess;
        fragmentActivity.runOnUiThread(new Runnable() { // from class: com.redphx.betterxc.task.UpdateScriptTask$Companion$getLatestBetaUserscriptUrl$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdateScriptTask$Companion$getLatestBetaUserscriptUrl$1.onResponse$lambda$0(FragmentActivity.this, str, function0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$0(FragmentActivity activity, String url, Function0 onSuccess) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(onSuccess, "$onSuccess");
        UpdateScriptTask.INSTANCE.run(activity, url, onSuccess);
    }
}
