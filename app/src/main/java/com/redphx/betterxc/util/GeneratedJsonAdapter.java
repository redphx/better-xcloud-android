package com.redphx.betterxc.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeepLinkDataJsonAdapter.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/redphx/betterxc/util/DeepLinkDataJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/redphx/betterxc/util/DeepLinkData;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "deepLinkHostAdapter", "Lcom/redphx/betterxc/util/DeepLinkHost;", "listOfStringAdapter", "", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: com.redphx.betterxc.util.DeepLinkDataJsonAdapter, reason: from toString */
/* loaded from: classes.dex */
public final class GeneratedJsonAdapter extends JsonAdapter<DeepLinkData> {
    private volatile Constructor<DeepLinkData> constructorRef;
    private final JsonAdapter<DeepLinkHost> deepLinkHostAdapter;
    private final JsonAdapter<List<String>> listOfStringAdapter;
    private final JsonReader.Options options;

    public GeneratedJsonAdapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("host", "data");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        JsonAdapter<DeepLinkHost> adapter = moshi.adapter(DeepLinkHost.class, SetsKt.emptySet(), "host");
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.deepLinkHostAdapter = adapter;
        JsonAdapter<List<String>> adapter2 = moshi.adapter(Types.newParameterizedType(List.class, String.class), SetsKt.emptySet(), "data");
        Intrinsics.checkNotNullExpressionValue(adapter2, "adapter(...)");
        this.listOfStringAdapter = adapter2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(34);
        sb.append("GeneratedJsonAdapter(DeepLinkData)");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.JsonAdapter
    public DeepLinkData fromJson(JsonReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        DeepLinkHost deepLinkHost = null;
        List<String> list = null;
        int i = -1;
        while (reader.hasNext()) {
            int selectName = reader.selectName(this.options);
            if (selectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (selectName == 0) {
                deepLinkHost = this.deepLinkHostAdapter.fromJson(reader);
                if (deepLinkHost == null) {
                    throw Util.unexpectedNull("host", "host", reader);
                }
            } else if (selectName == 1) {
                list = this.listOfStringAdapter.fromJson(reader);
                if (list == null) {
                    throw Util.unexpectedNull("data_", "data", reader);
                }
                i = -3;
            } else {
                continue;
            }
        }
        reader.endObject();
        if (i == -3) {
            if (deepLinkHost == null) {
                throw Util.missingProperty("host", "host", reader);
            }
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
            return new DeepLinkData(deepLinkHost, list);
        }
        Constructor<DeepLinkData> constructor = this.constructorRef;
        if (constructor == null) {
            constructor = DeepLinkData.class.getDeclaredConstructor(DeepLinkHost.class, List.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER);
            this.constructorRef = constructor;
            Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
        }
        if (deepLinkHost == null) {
            throw Util.missingProperty("host", "host", reader);
        }
        DeepLinkData newInstance = constructor.newInstance(deepLinkHost, list, Integer.valueOf(i), null);
        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
        return newInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, DeepLinkData value_) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("host");
        this.deepLinkHostAdapter.toJson(writer, (JsonWriter) value_.getHost());
        writer.name("data");
        this.listOfStringAdapter.toJson(writer, (JsonWriter) value_.getData());
        writer.endObject();
    }
}
