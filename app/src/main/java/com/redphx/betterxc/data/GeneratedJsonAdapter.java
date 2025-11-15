package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GamePassCatalogProductsJsonAdapter.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/redphx/betterxc/data/GamePassCatalogProductsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/redphx/betterxc/data/GamePassCatalogProducts;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "listOfStringAdapter", "", "", "mapOfStringGamePassCatalogProductAdapter", "", "Lcom/redphx/betterxc/data/GamePassCatalogProduct;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: com.redphx.betterxc.data.GamePassCatalogProductsJsonAdapter, reason: from toString */
/* loaded from: classes.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<GamePassCatalogProducts> {
    private final JsonAdapter<List<String>> listOfStringAdapter;
    private final JsonAdapter<Map<String, GamePassCatalogProduct>> mapOfStringGamePassCatalogProductAdapter;
    private final JsonReader.Options options;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("InvalidIds", "Products");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        JsonAdapter<List<String>> adapter = moshi.adapter(Types.newParameterizedType(List.class, String.class), SetsKt.emptySet(), "invalidIds");
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.listOfStringAdapter = adapter;
        JsonAdapter<Map<String, GamePassCatalogProduct>> adapter2 = moshi.adapter(Types.newParameterizedType(Map.class, String.class, GamePassCatalogProduct.class), SetsKt.emptySet(), "products");
        Intrinsics.checkNotNullExpressionValue(adapter2, "adapter(...)");
        this.mapOfStringGamePassCatalogProductAdapter = adapter2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(45);
        sb.append("GeneratedJsonAdapter(GamePassCatalogProducts)");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public GamePassCatalogProducts fromJson(JsonReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        List<String> list = null;
        Map<String, GamePassCatalogProduct> map = null;
        while (reader.hasNext()) {
            int selectName = reader.selectName(this.options);
            if (selectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (selectName == 0) {
                list = this.listOfStringAdapter.fromJson(reader);
                if (list == null) {
                    throw Util.unexpectedNull("invalidIds", "InvalidIds", reader);
                }
            } else if (selectName == 1 && (map = this.mapOfStringGamePassCatalogProductAdapter.fromJson(reader)) == null) {
                throw Util.unexpectedNull("products", "Products", reader);
            }
        }
        reader.endObject();
        if (list == null) {
            throw Util.missingProperty("invalidIds", "InvalidIds", reader);
        }
        if (map != null) {
            return new GamePassCatalogProducts(list, map);
        }
        throw Util.missingProperty("products", "Products", reader);
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, GamePassCatalogProducts value_) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("InvalidIds");
        this.listOfStringAdapter.toJson(writer, (JsonWriter) value_.getInvalidIds());
        writer.name("Products");
        this.mapOfStringGamePassCatalogProductAdapter.toJson(writer, (JsonWriter) value_.getProducts());
        writer.endObject();
    }
}
