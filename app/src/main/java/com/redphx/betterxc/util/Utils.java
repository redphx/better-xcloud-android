package com.redphx.betterxc.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import com.redphx.betterxc.activity.ExternalWebPageActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/Utils;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class Utils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: Utils.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0018\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J \u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J2\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0012\"\u0006\b\u0000\u0010\u0013\u0018\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0086\b¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/redphx/betterxc/util/Utils$Companion;", "", "<init>", "()V", "readAssetText", "", "context", "Landroid/content/Context;", "fileName", "readInternalFile", "openUrl", "", "url", "external", "", "openAppInfo", "installApk", "removeItemAtIndex", "", ExifInterface.GPS_DIRECTION_TRUE, "arr", "index", "", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String readAssetText(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            try {
                InputStream open = context.getAssets().open(fileName);
                Intrinsics.checkNotNullExpressionValue(open, "open(...)");
                Reader inputStreamReader = new InputStreamReader(open, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String readText = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    return readText;
                } finally {
                }
            } catch (Exception unused) {
                return null;
            }
        }

        public final String readInternalFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            try {
                InputStreamReader fileInputStream = new FileInputStream(new File(context.getFilesDir(), fileName));
                try {
                    fileInputStream = new InputStreamReader(fileInputStream);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(fileInputStream);
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                StringBuilder append = sb.append(readLine);
                                Intrinsics.checkNotNullExpressionValue(append, "append(...)");
                                Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append(...)");
                            } else {
                                String sb2 = sb.toString();
                                CloseableKt.closeFinally(fileInputStream, null);
                                CloseableKt.closeFinally(fileInputStream, null);
                                return sb2;
                            }
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (Exception unused) {
                return null;
            }
        }

        public static /* synthetic */ void openUrl$default(Companion companion, Context context, String str, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            companion.openUrl(context, str, z);
        }

        public final void openUrl(Context context, String url, boolean external) {
            Intent intent;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            if (external) {
                intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.setData(Uri.parse(url));
            } else {
                intent = new Intent(context, (Class<?>) ExternalWebPageActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("url", url);
            }
            context.startActivity(intent);
        }

        public final void openAppInfo(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:com.redphx.betterxc"));
            context.startActivity(intent);
        }

        public final void installApk(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            if (!StorageUtil.INSTANCE.isPermissionGranted(context)) {
                Toast.makeText(context, "Downloaded to " + fileName, 1).show();
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(335544321);
            Uri uriForFile = FileProvider.getUriForFile(context, "com.redphx.betterxc.fileprovider", new File(fileName));
            Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(...)");
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            context.startActivity(intent, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final /* synthetic */ <T> T[] removeItemAtIndex(T[] arr, int index) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            List mutableList = ArraysKt.toMutableList(arr);
            if (index >= 0 && index < mutableList.size()) {
                mutableList.remove(index);
            }
            Intrinsics.reifiedOperationMarker(0, "T?");
            return (T[]) mutableList.toArray(new Object[0]);
        }
    }
}
