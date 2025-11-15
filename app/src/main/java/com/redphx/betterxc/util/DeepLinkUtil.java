package com.redphx.betterxc.util;

import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.redphx.betterxc.util.DeepLinkHost;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeepLinkUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/DeepLinkUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DeepLinkUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String SCHEME = "bxc";

    /* compiled from: DeepLinkUtil.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/redphx/betterxc/util/DeepLinkUtil$Companion;", "", "<init>", "()V", "SCHEME", "", "generateUri", "Landroid/net/Uri;", "host", "Lcom/redphx/betterxc/util/DeepLinkHost;", "data", "", "parse", "Lcom/redphx/betterxc/util/DeepLinkData;", "uri", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Uri generateUri$default(Companion companion, DeepLinkHost deepLinkHost, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = CollectionsKt.emptyList();
            }
            return companion.generateUri(deepLinkHost, list);
        }

        public final Uri generateUri(DeepLinkHost host, List<String> data) {
            Intrinsics.checkNotNullParameter(host, "host");
            Intrinsics.checkNotNullParameter(data, "data");
            Uri parse = Uri.parse("bxc://" + host.getValue() + '/' + CollectionsKt.joinToString$default(data, "/", null, null, 0, null, null, 62, null));
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            return parse;
        }

        public final DeepLinkData parse(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            if (!Intrinsics.areEqual(uri.getScheme(), DeepLinkUtil.SCHEME) || uri.getPath() == null) {
                return null;
            }
            String path = uri.getPath();
            Intrinsics.checkNotNull(path);
            String substring = path.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            List split$default = StringsKt.split$default((CharSequence) substring, new String[]{"/"}, false, 0, 6, (Object) null);
            DeepLinkHost.Companion companion = DeepLinkHost.INSTANCE;
            String host = uri.getHost();
            Intrinsics.checkNotNull(host);
            DeepLinkHost from = companion.from(host);
            Intrinsics.checkNotNull(from);
            return new DeepLinkData(from, split$default);
        }
    }
}
