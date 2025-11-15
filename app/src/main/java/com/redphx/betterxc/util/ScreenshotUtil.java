package com.redphx.betterxc.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Base64;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenshotUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/ScreenshotUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ScreenshotUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: ScreenshotUtil.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t¨\u0006\u000b"}, d2 = {"Lcom/redphx/betterxc/util/ScreenshotUtil$Companion;", "", "<init>", "()V", "save", "", "context", "Landroid/content/Context;", "name", "", "data", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void save(Context context, String name, String data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(data, "data");
            try {
                File file = new File(Environment.getExternalStorageDirectory(), ArraysKt.joinToString$default(new String[]{Constants.ROOT_DIR, Constants.SCREENSHOTS_DIR}, "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                File file2 = new File(file, "" + name + '-' + System.currentTimeMillis() + ".png");
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    FileOutputStream fileOutputStream2 = fileOutputStream;
                    byte[] decode = Base64.decode(data, 0);
                    Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                    fileOutputStream2.write(decode);
                    fileOutputStream2.flush();
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                    MediaScannerConnection.scanFile(context, new String[]{file2.toString()}, new String[]{"image/png"}, null);
                } finally {
                }
            } catch (Exception e) {
                Toast.makeText(context, "Error: Couldn't save screenshot", 0).show();
                e.printStackTrace();
            }
        }
    }
}
