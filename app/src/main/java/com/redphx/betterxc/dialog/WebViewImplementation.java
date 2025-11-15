package com.redphx.betterxc.dialog;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: WebViewManagerDialog.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/redphx/betterxc/dialog/WebViewImplementation;", "", "<init>", "(Ljava/lang/String;I)V", "GOOGLE", "THORIUM", "MULCH", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebViewImplementation {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ WebViewImplementation[] $VALUES;
    public static final WebViewImplementation GOOGLE = new WebViewImplementation("GOOGLE", 0);
    public static final WebViewImplementation THORIUM = new WebViewImplementation("THORIUM", 1);
    public static final WebViewImplementation MULCH = new WebViewImplementation("MULCH", 2);

    private static final /* synthetic */ WebViewImplementation[] $values() {
        return new WebViewImplementation[]{GOOGLE, THORIUM, MULCH};
    }

    public static EnumEntries<WebViewImplementation> getEntries() {
        return $ENTRIES;
    }

    private WebViewImplementation(String str, int i) {
    }

    static {
        WebViewImplementation[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static WebViewImplementation valueOf(String str) {
        return (WebViewImplementation) Enum.valueOf(WebViewImplementation.class, str);
    }

    public static WebViewImplementation[] values() {
        return (WebViewImplementation[]) $VALUES.clone();
    }
}
