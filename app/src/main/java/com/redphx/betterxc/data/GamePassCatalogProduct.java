package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GamePassCatalog.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003JK\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010¨\u0006!"}, d2 = {"Lcom/redphx/betterxc/data/GamePassCatalogProduct;", "", "productTitle", "", "storeId", "imageTile", "Lcom/redphx/betterxc/data/GamePassCatalogProductImage;", "imagePoster", "imageHero", "imageTitledHero", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/redphx/betterxc/data/GamePassCatalogProductImage;Lcom/redphx/betterxc/data/GamePassCatalogProductImage;Lcom/redphx/betterxc/data/GamePassCatalogProductImage;Lcom/redphx/betterxc/data/GamePassCatalogProductImage;)V", "getProductTitle", "()Ljava/lang/String;", "getStoreId", "getImageTile", "()Lcom/redphx/betterxc/data/GamePassCatalogProductImage;", "getImagePoster", "getImageHero", "getImageTitledHero", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class GamePassCatalogProduct {
    private final GamePassCatalogProductImage imageHero;
    private final GamePassCatalogProductImage imagePoster;
    private final GamePassCatalogProductImage imageTile;
    private final GamePassCatalogProductImage imageTitledHero;
    private final String productTitle;
    private final String storeId;

    public static /* synthetic */ GamePassCatalogProduct copy$default(GamePassCatalogProduct gamePassCatalogProduct, String str, String str2, GamePassCatalogProductImage gamePassCatalogProductImage, GamePassCatalogProductImage gamePassCatalogProductImage2, GamePassCatalogProductImage gamePassCatalogProductImage3, GamePassCatalogProductImage gamePassCatalogProductImage4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gamePassCatalogProduct.productTitle;
        }
        if ((i & 2) != 0) {
            str2 = gamePassCatalogProduct.storeId;
        }
        if ((i & 4) != 0) {
            gamePassCatalogProductImage = gamePassCatalogProduct.imageTile;
        }
        if ((i & 8) != 0) {
            gamePassCatalogProductImage2 = gamePassCatalogProduct.imagePoster;
        }
        if ((i & 16) != 0) {
            gamePassCatalogProductImage3 = gamePassCatalogProduct.imageHero;
        }
        if ((i & 32) != 0) {
            gamePassCatalogProductImage4 = gamePassCatalogProduct.imageTitledHero;
        }
        GamePassCatalogProductImage gamePassCatalogProductImage5 = gamePassCatalogProductImage3;
        GamePassCatalogProductImage gamePassCatalogProductImage6 = gamePassCatalogProductImage4;
        return gamePassCatalogProduct.copy(str, str2, gamePassCatalogProductImage, gamePassCatalogProductImage2, gamePassCatalogProductImage5, gamePassCatalogProductImage6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductTitle() {
        return this.productTitle;
    }

    /* renamed from: component2, reason: from getter */
    public final String getStoreId() {
        return this.storeId;
    }

    /* renamed from: component3, reason: from getter */
    public final GamePassCatalogProductImage getImageTile() {
        return this.imageTile;
    }

    /* renamed from: component4, reason: from getter */
    public final GamePassCatalogProductImage getImagePoster() {
        return this.imagePoster;
    }

    /* renamed from: component5, reason: from getter */
    public final GamePassCatalogProductImage getImageHero() {
        return this.imageHero;
    }

    /* renamed from: component6, reason: from getter */
    public final GamePassCatalogProductImage getImageTitledHero() {
        return this.imageTitledHero;
    }

    public final GamePassCatalogProduct copy(@Json(name = "ProductTitle") String productTitle, @Json(name = "StoreId") String storeId, @Json(name = "Image_Tile") GamePassCatalogProductImage imageTile, @Json(name = "Image_Poster") GamePassCatalogProductImage imagePoster, @Json(name = "Image_Hero") GamePassCatalogProductImage imageHero, @Json(name = "Image_TitledHero") GamePassCatalogProductImage imageTitledHero) {
        Intrinsics.checkNotNullParameter(productTitle, "productTitle");
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(imageTile, "imageTile");
        return new GamePassCatalogProduct(productTitle, storeId, imageTile, imagePoster, imageHero, imageTitledHero);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GamePassCatalogProduct)) {
            return false;
        }
        GamePassCatalogProduct gamePassCatalogProduct = (GamePassCatalogProduct) other;
        return Intrinsics.areEqual(this.productTitle, gamePassCatalogProduct.productTitle) && Intrinsics.areEqual(this.storeId, gamePassCatalogProduct.storeId) && Intrinsics.areEqual(this.imageTile, gamePassCatalogProduct.imageTile) && Intrinsics.areEqual(this.imagePoster, gamePassCatalogProduct.imagePoster) && Intrinsics.areEqual(this.imageHero, gamePassCatalogProduct.imageHero) && Intrinsics.areEqual(this.imageTitledHero, gamePassCatalogProduct.imageTitledHero);
    }

    public int hashCode() {
        int hashCode = ((((this.productTitle.hashCode() * 31) + this.storeId.hashCode()) * 31) + this.imageTile.hashCode()) * 31;
        GamePassCatalogProductImage gamePassCatalogProductImage = this.imagePoster;
        int hashCode2 = (hashCode + (gamePassCatalogProductImage == null ? 0 : gamePassCatalogProductImage.hashCode())) * 31;
        GamePassCatalogProductImage gamePassCatalogProductImage2 = this.imageHero;
        int hashCode3 = (hashCode2 + (gamePassCatalogProductImage2 == null ? 0 : gamePassCatalogProductImage2.hashCode())) * 31;
        GamePassCatalogProductImage gamePassCatalogProductImage3 = this.imageTitledHero;
        return hashCode3 + (gamePassCatalogProductImage3 != null ? gamePassCatalogProductImage3.hashCode() : 0);
    }

    public String toString() {
        return "GamePassCatalogProduct(productTitle=" + this.productTitle + ", storeId=" + this.storeId + ", imageTile=" + this.imageTile + ", imagePoster=" + this.imagePoster + ", imageHero=" + this.imageHero + ", imageTitledHero=" + this.imageTitledHero + ')';
    }

    public GamePassCatalogProduct(@Json(name = "ProductTitle") String productTitle, @Json(name = "StoreId") String storeId, @Json(name = "Image_Tile") GamePassCatalogProductImage imageTile, @Json(name = "Image_Poster") GamePassCatalogProductImage gamePassCatalogProductImage, @Json(name = "Image_Hero") GamePassCatalogProductImage gamePassCatalogProductImage2, @Json(name = "Image_TitledHero") GamePassCatalogProductImage gamePassCatalogProductImage3) {
        Intrinsics.checkNotNullParameter(productTitle, "productTitle");
        Intrinsics.checkNotNullParameter(storeId, "storeId");
        Intrinsics.checkNotNullParameter(imageTile, "imageTile");
        this.productTitle = productTitle;
        this.storeId = storeId;
        this.imageTile = imageTile;
        this.imagePoster = gamePassCatalogProductImage;
        this.imageHero = gamePassCatalogProductImage2;
        this.imageTitledHero = gamePassCatalogProductImage3;
    }

    public final String getProductTitle() {
        return this.productTitle;
    }

    public final String getStoreId() {
        return this.storeId;
    }

    public final GamePassCatalogProductImage getImageTile() {
        return this.imageTile;
    }

    public final GamePassCatalogProductImage getImagePoster() {
        return this.imagePoster;
    }

    public final GamePassCatalogProductImage getImageHero() {
        return this.imageHero;
    }

    public final GamePassCatalogProductImage getImageTitledHero() {
        return this.imageTitledHero;
    }
}
