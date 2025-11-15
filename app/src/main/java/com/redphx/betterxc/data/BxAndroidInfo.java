package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BxFlags.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010$\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\f\u001a\u00020\u0003\u0012\b\b\u0001\u0010\r\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0003\u0012\u0014\b\u0001\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011HÆ\u0003J\u0093\u0001\u00100\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u00032\b\b\u0003\u0010\n\u001a\u00020\u00032\b\b\u0003\u0010\u000b\u001a\u00020\u00032\b\b\u0003\u0010\f\u001a\u00020\u00032\b\b\u0003\u0010\r\u001a\u00020\u00032\b\b\u0003\u0010\u000e\u001a\u00020\u00032\b\b\u0003\u0010\u000f\u001a\u00020\u00032\u0014\b\u0003\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011HÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u00020\u0005HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00066"}, d2 = {"Lcom/redphx/betterxc/data/BxAndroidInfo;", "", "osVersion", "", "sdk", "", "abis", "", "board", "device", "model", "hardware", "product", "brand", "manufacturer", "webview", "settings", "", "<init>", "(Ljava/lang/String;ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getOsVersion", "()Ljava/lang/String;", "getSdk", "()I", "getAbis", "()Ljava/util/List;", "getBoard", "getDevice", "getModel", "getHardware", "getProduct", "getBrand", "getManufacturer", "getWebview", "getSettings", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class BxAndroidInfo {
    private final List<String> abis;
    private final String board;
    private final String brand;
    private final String device;
    private final String hardware;
    private final String manufacturer;
    private final String model;
    private final String osVersion;
    private final String product;
    private final int sdk;
    private final Map<String, Object> settings;
    private final String webview;

    public static /* synthetic */ BxAndroidInfo copy$default(BxAndroidInfo bxAndroidInfo, String str, int i, List list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Map map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bxAndroidInfo.osVersion;
        }
        if ((i2 & 2) != 0) {
            i = bxAndroidInfo.sdk;
        }
        if ((i2 & 4) != 0) {
            list = bxAndroidInfo.abis;
        }
        if ((i2 & 8) != 0) {
            str2 = bxAndroidInfo.board;
        }
        if ((i2 & 16) != 0) {
            str3 = bxAndroidInfo.device;
        }
        if ((i2 & 32) != 0) {
            str4 = bxAndroidInfo.model;
        }
        if ((i2 & 64) != 0) {
            str5 = bxAndroidInfo.hardware;
        }
        if ((i2 & 128) != 0) {
            str6 = bxAndroidInfo.product;
        }
        if ((i2 & 256) != 0) {
            str7 = bxAndroidInfo.brand;
        }
        if ((i2 & 512) != 0) {
            str8 = bxAndroidInfo.manufacturer;
        }
        if ((i2 & 1024) != 0) {
            str9 = bxAndroidInfo.webview;
        }
        if ((i2 & 2048) != 0) {
            map = bxAndroidInfo.settings;
        }
        String str10 = str9;
        Map map2 = map;
        String str11 = str7;
        String str12 = str8;
        String str13 = str5;
        String str14 = str6;
        String str15 = str3;
        String str16 = str4;
        return bxAndroidInfo.copy(str, i, list, str2, str15, str16, str13, str14, str11, str12, str10, map2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getOsVersion() {
        return this.osVersion;
    }

    /* renamed from: component10, reason: from getter */
    public final String getManufacturer() {
        return this.manufacturer;
    }

    /* renamed from: component11, reason: from getter */
    public final String getWebview() {
        return this.webview;
    }

    public final Map<String, Object> component12() {
        return this.settings;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSdk() {
        return this.sdk;
    }

    public final List<String> component3() {
        return this.abis;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBoard() {
        return this.board;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDevice() {
        return this.device;
    }

    /* renamed from: component6, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* renamed from: component7, reason: from getter */
    public final String getHardware() {
        return this.hardware;
    }

    /* renamed from: component8, reason: from getter */
    public final String getProduct() {
        return this.product;
    }

    /* renamed from: component9, reason: from getter */
    public final String getBrand() {
        return this.brand;
    }

    public final BxAndroidInfo copy(@Json(name = "osVersion") String osVersion, @Json(name = "sdk") int sdk, @Json(name = "abis") List<String> abis, @Json(name = "board") String board, @Json(name = "device") String device, @Json(name = "model") String model, @Json(name = "hardware") String hardware, @Json(name = "product") String product, @Json(name = "brand") String brand, @Json(name = "manufacturer") String manufacturer, @Json(name = "webview") String webview, @Json(name = "settings") Map<String, ? extends Object> settings) {
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(abis, "abis");
        Intrinsics.checkNotNullParameter(board, "board");
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(hardware, "hardware");
        Intrinsics.checkNotNullParameter(product, "product");
        Intrinsics.checkNotNullParameter(brand, "brand");
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(webview, "webview");
        Intrinsics.checkNotNullParameter(settings, "settings");
        return new BxAndroidInfo(osVersion, sdk, abis, board, device, model, hardware, product, brand, manufacturer, webview, settings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BxAndroidInfo)) {
            return false;
        }
        BxAndroidInfo bxAndroidInfo = (BxAndroidInfo) other;
        return Intrinsics.areEqual(this.osVersion, bxAndroidInfo.osVersion) && this.sdk == bxAndroidInfo.sdk && Intrinsics.areEqual(this.abis, bxAndroidInfo.abis) && Intrinsics.areEqual(this.board, bxAndroidInfo.board) && Intrinsics.areEqual(this.device, bxAndroidInfo.device) && Intrinsics.areEqual(this.model, bxAndroidInfo.model) && Intrinsics.areEqual(this.hardware, bxAndroidInfo.hardware) && Intrinsics.areEqual(this.product, bxAndroidInfo.product) && Intrinsics.areEqual(this.brand, bxAndroidInfo.brand) && Intrinsics.areEqual(this.manufacturer, bxAndroidInfo.manufacturer) && Intrinsics.areEqual(this.webview, bxAndroidInfo.webview) && Intrinsics.areEqual(this.settings, bxAndroidInfo.settings);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.osVersion.hashCode() * 31) + Integer.hashCode(this.sdk)) * 31) + this.abis.hashCode()) * 31) + this.board.hashCode()) * 31) + this.device.hashCode()) * 31) + this.model.hashCode()) * 31) + this.hardware.hashCode()) * 31) + this.product.hashCode()) * 31) + this.brand.hashCode()) * 31) + this.manufacturer.hashCode()) * 31) + this.webview.hashCode()) * 31) + this.settings.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BxAndroidInfo(osVersion=");
        sb.append(this.osVersion).append(", sdk=").append(this.sdk).append(", abis=").append(this.abis).append(", board=").append(this.board).append(", device=").append(this.device).append(", model=").append(this.model).append(", hardware=").append(this.hardware).append(", product=").append(this.product).append(", brand=").append(this.brand).append(", manufacturer=").append(this.manufacturer).append(", webview=").append(this.webview).append(", settings=");
        sb.append(this.settings).append(')');
        return sb.toString();
    }

    public BxAndroidInfo(@Json(name = "osVersion") String osVersion, @Json(name = "sdk") int i, @Json(name = "abis") List<String> abis, @Json(name = "board") String board, @Json(name = "device") String device, @Json(name = "model") String model, @Json(name = "hardware") String hardware, @Json(name = "product") String product, @Json(name = "brand") String brand, @Json(name = "manufacturer") String manufacturer, @Json(name = "webview") String webview, @Json(name = "settings") Map<String, ? extends Object> settings) {
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(abis, "abis");
        Intrinsics.checkNotNullParameter(board, "board");
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(hardware, "hardware");
        Intrinsics.checkNotNullParameter(product, "product");
        Intrinsics.checkNotNullParameter(brand, "brand");
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(webview, "webview");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.osVersion = osVersion;
        this.sdk = i;
        this.abis = abis;
        this.board = board;
        this.device = device;
        this.model = model;
        this.hardware = hardware;
        this.product = product;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.webview = webview;
        this.settings = settings;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final int getSdk() {
        return this.sdk;
    }

    public final List<String> getAbis() {
        return this.abis;
    }

    public final String getBoard() {
        return this.board;
    }

    public final String getDevice() {
        return this.device;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getHardware() {
        return this.hardware;
    }

    public final String getProduct() {
        return this.product;
    }

    public final String getBrand() {
        return this.brand;
    }

    public final String getManufacturer() {
        return this.manufacturer;
    }

    public final String getWebview() {
        return this.webview;
    }

    public final Map<String, Object> getSettings() {
        return this.settings;
    }
}
