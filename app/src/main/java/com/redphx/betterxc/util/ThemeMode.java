package com.redphx.betterxc.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.apache.maven.artifact.Artifact;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: UiUtil.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/redphx/betterxc/util/ThemeMode;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "LIGHT", "DARK", "SYSTEM", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ThemeMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ThemeMode[] $VALUES;
    private final String value;
    public static final ThemeMode LIGHT = new ThemeMode("LIGHT", 0, "light");
    public static final ThemeMode DARK = new ThemeMode("DARK", 1, "dark");
    public static final ThemeMode SYSTEM = new ThemeMode("SYSTEM", 2, Artifact.SCOPE_SYSTEM);

    private static final /* synthetic */ ThemeMode[] $values() {
        return new ThemeMode[]{LIGHT, DARK, SYSTEM};
    }

    public static EnumEntries<ThemeMode> getEntries() {
        return $ENTRIES;
    }

    private ThemeMode(String str, int i, String str2) {
        this.value = str2;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        ThemeMode[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public static ThemeMode valueOf(String str) {
        return (ThemeMode) Enum.valueOf(ThemeMode.class, str);
    }

    public static ThemeMode[] values() {
        return (ThemeMode[]) $VALUES.clone();
    }
}
