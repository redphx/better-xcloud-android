package com.redphx.betterxc.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DeepLinkUtil.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/redphx/betterxc/util/DeepLinkHost;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "PLAY", "REMOTE_PLAY", "DEVICE_CODE", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DeepLinkHost {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DeepLinkHost[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String value;
    public static final DeepLinkHost PLAY = new DeepLinkHost("PLAY", 0, "play");
    public static final DeepLinkHost REMOTE_PLAY = new DeepLinkHost("REMOTE_PLAY", 1, "remote-play");
    public static final DeepLinkHost DEVICE_CODE = new DeepLinkHost("DEVICE_CODE", 2, "device-code");

    private static final /* synthetic */ DeepLinkHost[] $values() {
        return new DeepLinkHost[]{PLAY, REMOTE_PLAY, DEVICE_CODE};
    }

    public static EnumEntries<DeepLinkHost> getEntries() {
        return $ENTRIES;
    }

    private DeepLinkHost(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        DeepLinkHost[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: DeepLinkUtil.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0004¨\u0006\b"}, d2 = {"Lcom/redphx/betterxc/util/DeepLinkHost$Companion;", "", "<init>", "()V", TypedValues.TransitionType.S_FROM, "Lcom/redphx/betterxc/util/DeepLinkHost;", "value", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeepLinkHost from(String value) {
            Object obj;
            Intrinsics.checkNotNullParameter(value, "value");
            Iterator<E> it = DeepLinkHost.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((DeepLinkHost) obj).getValue(), value)) {
                    break;
                }
            }
            return (DeepLinkHost) obj;
        }
    }

    public static DeepLinkHost valueOf(String str) {
        return (DeepLinkHost) Enum.valueOf(DeepLinkHost.class, str);
    }

    public static DeepLinkHost[] values() {
        return (DeepLinkHost[]) $VALUES.clone();
    }
}
