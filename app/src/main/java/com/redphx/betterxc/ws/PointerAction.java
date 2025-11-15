package com.redphx.betterxc.ws;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: PointerProviderServer.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/redphx/betterxc/ws/PointerAction;", "", "value", "", "<init>", "(Ljava/lang/String;IB)V", "getValue", "()B", "MOVE", "BUTTON_PRESS", "BUTTON_RELEASE", "SCROLL", "POINTER_CAPTURE_CHANGED", "PROTOCOL_VERSION", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class PointerAction {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PointerAction[] $VALUES;
    private final byte value;
    public static final PointerAction MOVE = new PointerAction("MOVE", 0, (byte) 1);
    public static final PointerAction BUTTON_PRESS = new PointerAction("BUTTON_PRESS", 1, (byte) 2);
    public static final PointerAction BUTTON_RELEASE = new PointerAction("BUTTON_RELEASE", 2, (byte) 3);
    public static final PointerAction SCROLL = new PointerAction("SCROLL", 3, (byte) 4);
    public static final PointerAction POINTER_CAPTURE_CHANGED = new PointerAction("POINTER_CAPTURE_CHANGED", 4, (byte) 5);
    public static final PointerAction PROTOCOL_VERSION = new PointerAction("PROTOCOL_VERSION", 5, ByteCompanionObject.MAX_VALUE);

    private static final /* synthetic */ PointerAction[] $values() {
        return new PointerAction[]{MOVE, BUTTON_PRESS, BUTTON_RELEASE, SCROLL, POINTER_CAPTURE_CHANGED, PROTOCOL_VERSION};
    }

    public static EnumEntries<PointerAction> getEntries() {
        return $ENTRIES;
    }

    private PointerAction(String str, int i, byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }

    static {
        PointerAction[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static PointerAction valueOf(String str) {
        return (PointerAction) Enum.valueOf(PointerAction.class, str);
    }

    public static PointerAction[] values() {
        return (PointerAction[]) $VALUES.clone();
    }
}
