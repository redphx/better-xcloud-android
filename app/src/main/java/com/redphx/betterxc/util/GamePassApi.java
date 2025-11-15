package com.redphx.betterxc.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.redphx.betterxc.data.GamePassCatalogProduct;
import com.redphx.betterxc.data.GamePassCatalogProducts;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: GamePassApi.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/GamePassApi;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class GamePassApi {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: GamePassApi.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/redphx/betterxc/util/GamePassApi$Companion;", "", "<init>", "()V", "getTitleInfo", "Lcom/redphx/betterxc/data/GamePassCatalogProduct;", "productId", "", "webLocale", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ GamePassCatalogProduct getTitleInfo$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "en-US";
            }
            return companion.getTitleInfo(str, str2);
        }

        public final GamePassCatalogProduct getTitleInfo(String productId, String webLocale) {
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(webLocale, "webLocale");
            Request build = new Request.Builder().url("https://catalog.gamepass.com/v3/products?market=US&language=" + webLocale + "&hydration=RemoteHighSapphire0").header("Content-Type", "application/json").header("MS-CV", "Vv/EvHKpi6ljo23Os1JK1+.0").header("Calling-App-Name", "Xbox Cloud Gaming Web").header("Calling-App-Version", "24.1.51").post(RequestBody.INSTANCE.create("{\"Products\":[\"" + productId + "\"]}", MediaType.INSTANCE.get("application/json"))).build();
            JsonAdapter adapter = new Moshi.Builder().build().adapter(GamePassCatalogProducts.class);
            Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
            Response execute = new OkHttpClient().newCall(build).execute();
            try {
                ResponseBody body = execute.body();
                Intrinsics.checkNotNull(body);
                Object fromJson = adapter.fromJson(body.getSource());
                Intrinsics.checkNotNull(fromJson);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(execute, null);
                GamePassCatalogProduct gamePassCatalogProduct = ((GamePassCatalogProducts) fromJson).getProducts().get(productId);
                Intrinsics.checkNotNull(gamePassCatalogProduct);
                return gamePassCatalogProduct;
            } finally {
            }
        }
    }
}
