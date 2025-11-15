package com.redphx.betterxc.worker;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: DownloadWorker.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/redphx/betterxc/worker/DownloadFileParams;", "", "<init>", "()V", "KEY_SOURCE", "", "KEY_SAVE_DIR", "KEY_SAVE_NAME", "KEY_SAVE_WORLD_READABLE", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DownloadFileParams {
    public static final DownloadFileParams INSTANCE = new DownloadFileParams();
    public static final String KEY_SAVE_DIR = "save_dir";
    public static final String KEY_SAVE_NAME = "save_name";
    public static final String KEY_SAVE_WORLD_READABLE = "save_world_readable";
    public static final String KEY_SOURCE = "source";

    private DownloadFileParams() {
    }
}
