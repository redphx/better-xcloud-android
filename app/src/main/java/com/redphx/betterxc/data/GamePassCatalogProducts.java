package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GamePassCatalog.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J/\u0010\u0010\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0003\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/redphx/betterxc/data/GamePassCatalogProducts;", "", "invalidIds", "", "", "products", "", "Lcom/redphx/betterxc/data/GamePassCatalogProduct;", "<init>", "(Ljava/util/List;Ljava/util/Map;)V", "getInvalidIds", "()Ljava/util/List;", "getProducts", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class GamePassCatalogProducts {
    private final List<String> invalidIds;
    private final Map<String, GamePassCatalogProduct> products;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GamePassCatalogProducts copy$default(GamePassCatalogProducts gamePassCatalogProducts, List list, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            list = gamePassCatalogProducts.invalidIds;
        }
        if ((i & 2) != 0) {
            map = gamePassCatalogProducts.products;
        }
        return gamePassCatalogProducts.copy(list, map);
    }

    public final List<String> component1() {
        return this.invalidIds;
    }

    public final Map<String, GamePassCatalogProduct> component2() {
        return this.products;
    }

    public final GamePassCatalogProducts copy(@Json(name = "InvalidIds") List<String> invalidIds, @Json(name = "Products") Map<String, GamePassCatalogProduct> products) {
        Intrinsics.checkNotNullParameter(invalidIds, "invalidIds");
        Intrinsics.checkNotNullParameter(products, "products");
        return new GamePassCatalogProducts(invalidIds, products);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GamePassCatalogProducts)) {
            return false;
        }
        GamePassCatalogProducts gamePassCatalogProducts = (GamePassCatalogProducts) other;
        return Intrinsics.areEqual(this.invalidIds, gamePassCatalogProducts.invalidIds) && Intrinsics.areEqual(this.products, gamePassCatalogProducts.products);
    }

    public int hashCode() {
        return (this.invalidIds.hashCode() * 31) + this.products.hashCode();
    }

    public String toString() {
        return "GamePassCatalogProducts(invalidIds=" + this.invalidIds + ", products=" + this.products + ')';
    }

    public GamePassCatalogProducts(@Json(name = "InvalidIds") List<String> invalidIds, @Json(name = "Products") Map<String, GamePassCatalogProduct> products) {
        Intrinsics.checkNotNullParameter(invalidIds, "invalidIds");
        Intrinsics.checkNotNullParameter(products, "products");
        this.invalidIds = invalidIds;
        this.products = products;
    }

    public final List<String> getInvalidIds() {
        return this.invalidIds;
    }

    public final Map<String, GamePassCatalogProduct> getProducts() {
        return this.products;
    }
}
