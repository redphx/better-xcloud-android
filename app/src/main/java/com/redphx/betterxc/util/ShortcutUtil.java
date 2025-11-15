package com.redphx.betterxc.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.redphx.betterxc.R;
import com.redphx.betterxc.util.ShortcutUtil;
import com.redphx.betterxc.worker.GetGameShortcutIconWorker;
import java.io.File;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShortcutUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/redphx/betterxc/util/ShortcutUtil;", "", "<init>", "()V", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ShortcutUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: ShortcutUtil.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0013J.\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00132\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u0002J&\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¨\u0006 "}, d2 = {"Lcom/redphx/betterxc/util/ShortcutUtil$Companion;", "", "<init>", "()V", "requestPinShortcut", "", "context", "Landroid/content/Context;", "shortcutId", "", "shortcutLabel", "icon", "Landroidx/core/graphics/drawable/IconCompat;", "intent", "Landroid/content/Intent;", "createGameShortcut", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "productInfo", "", "onIconDownloaded", "iconName", "productTitle", "createConsoleShortcut", "consoleName", "serverId", "resolution", "generateAdaptiveIcon", "Landroid/graphics/Bitmap;", "fgDrawable", "Landroid/graphics/drawable/Drawable;", "bgDrawable", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {

        /* compiled from: ShortcutUtil.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[WorkInfo.State.values().length];
                try {
                    iArr[WorkInfo.State.SUCCEEDED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[WorkInfo.State.FAILED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final void requestPinShortcut(Context context, String shortcutId, String shortcutLabel, IconCompat icon, Intent intent) {
            ShortcutManagerCompat.requestPinShortcut(context, new ShortcutInfoCompat.Builder(context, shortcutId).setShortLabel(shortcutLabel).setIcon(icon).setIntent(intent).build(), null);
        }

        public final void createGameShortcut(final AppCompatActivity activity, final List<String> productInfo) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(productInfo, "productInfo");
            AppCompatActivity appCompatActivity = activity;
            if (!ShortcutManagerCompat.isRequestPinShortcutSupported(appCompatActivity)) {
                Toast.makeText(appCompatActivity, "Your Home Launcher doesn't support creating shortcut", 0).show();
                return;
            }
            final AlertDialog create = new MaterialAlertDialogBuilder(appCompatActivity).setView(R.layout.dialog_progress).setCancelable(false).create();
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            create.show();
            View findViewById = create.findViewById(R.id.txt_progress_status);
            Intrinsics.checkNotNull(findViewById);
            ((TextView) findViewById).setText("Generating shortcut...");
            View findViewById2 = create.findViewById(R.id.progress_circular);
            Intrinsics.checkNotNull(findViewById2);
            ((CircularProgressIndicator) findViewById2).setVisibility(0);
            Pair[] pairArr = {TuplesKt.to(GetGameShortcutIconWorker.FIELD_PRODUCT_ID, productInfo.get(1))};
            Data.Builder builder = new Data.Builder();
            Pair pair = pairArr[0];
            builder.put((String) pair.getFirst(), pair.getSecond());
            OneTimeWorkRequest build = new OneTimeWorkRequest.Builder((Class<? extends ListenableWorker>) GetGameShortcutIconWorker.class).setInputData(builder.build()).build();
            WorkManager companion = WorkManager.INSTANCE.getInstance(appCompatActivity);
            companion.enqueue(build);
            companion.getWorkInfoByIdLiveData(build.getId()).observe(activity, new ShortcutUtil$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.redphx.betterxc.util.ShortcutUtil$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit createGameShortcut$lambda$0;
                    createGameShortcut$lambda$0 = ShortcutUtil.Companion.createGameShortcut$lambda$0(AlertDialog.this, activity, productInfo, (WorkInfo) obj);
                    return createGameShortcut$lambda$0;
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit createGameShortcut$lambda$0(AlertDialog progressDialog, AppCompatActivity activity, List productInfo, WorkInfo workInfo) {
            Intrinsics.checkNotNullParameter(progressDialog, "$progressDialog");
            Intrinsics.checkNotNullParameter(activity, "$activity");
            Intrinsics.checkNotNullParameter(productInfo, "$productInfo");
            WorkInfo.State state = workInfo != null ? workInfo.getState() : null;
            int i = state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i == 1) {
                progressDialog.dismiss();
                Data outputData = workInfo.getOutputData();
                String string = outputData.getString(GetGameShortcutIconWorker.FIELD_ICON_NAME);
                Intrinsics.checkNotNull(string);
                String string2 = outputData.getString(GetGameShortcutIconWorker.FIELD_PRODUCT_TITLE);
                Intrinsics.checkNotNull(string2);
                ShortcutUtil.INSTANCE.onIconDownloaded(activity, productInfo, string, string2);
            } else if (i == 2) {
                progressDialog.dismiss();
                Toast.makeText(activity, "Failed to create shortcut", 0).show();
            }
            return Unit.INSTANCE;
        }

        private final void onIconDownloaded(Context context, List<String> productInfo, String iconName, String productTitle) {
            Bitmap decodeFile = BitmapFactory.decodeFile(new File(context.getCacheDir(), iconName).getAbsolutePath());
            Bitmap createBitmap = Bitmap.createBitmap(Constants.SHORTCUT_ICON_SIZE, Constants.SHORTCUT_ICON_SIZE, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(decodeFile, new Rect(0, 0, decodeFile.getWidth(), decodeFile.getHeight()), new Rect(Constants.SHORTCUT_ICON_PADDING, Constants.SHORTCUT_ICON_PADDING, canvas.getWidth() - Constants.SHORTCUT_ICON_PADDING, canvas.getHeight() - Constants.SHORTCUT_ICON_PADDING), paint);
            Resources resources = context.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
            IconCompat createWithAdaptiveBitmap = IconCompat.createWithAdaptiveBitmap(generateAdaptiveIcon(new BitmapDrawable(resources, createBitmap), new ColorDrawable(-1)));
            Intent intent = new Intent("android.intent.action.VIEW", DeepLinkUtil.INSTANCE.generateUri(DeepLinkHost.PLAY, productInfo));
            intent.setFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            StringBuilder sb = new StringBuilder("xcloud-");
            String lowerCase = productInfo.get(1).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String sb2 = sb.append(lowerCase).append('-').append(System.currentTimeMillis()).toString();
            Intrinsics.checkNotNull(createWithAdaptiveBitmap);
            requestPinShortcut(context, sb2, productTitle, createWithAdaptiveBitmap, intent);
            decodeFile.recycle();
        }

        public final void createConsoleShortcut(Context context, String consoleName, String serverId, String resolution) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(consoleName, "consoleName");
            Intrinsics.checkNotNullParameter(serverId, "serverId");
            Intrinsics.checkNotNullParameter(resolution, "resolution");
            StringBuilder sb = new StringBuilder("xhome-");
            String lowerCase = serverId.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String sb2 = sb.append(lowerCase).append('-').append(resolution).append('-').append(System.currentTimeMillis()).toString();
            Drawable drawable = AppCompatResources.getDrawable(context, Intrinsics.areEqual(resolution, "720p") ? R.drawable.ic_remote_play_720p_shortcut_foreground : R.drawable.ic_remote_play_shortcut_foreground);
            Intrinsics.checkNotNull(drawable);
            Bitmap createBitmap = Bitmap.createBitmap(Constants.SHORTCUT_ICON_SIZE, Constants.SHORTCUT_ICON_SIZE, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            Resources resources = context.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
            IconCompat createWithAdaptiveBitmap = IconCompat.createWithAdaptiveBitmap(generateAdaptiveIcon(new BitmapDrawable(resources, createBitmap), new ColorDrawable(context.getResources().getColor(R.color.ic_remote_play_shortcut_background, context.getTheme()))));
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(32768);
            intent.setData(DeepLinkUtil.INSTANCE.generateUri(DeepLinkHost.REMOTE_PLAY, CollectionsKt.listOf((Object[]) new String[]{serverId, resolution})));
            Intrinsics.checkNotNull(createWithAdaptiveBitmap);
            requestPinShortcut(context, sb2, consoleName, createWithAdaptiveBitmap, intent);
            createBitmap.recycle();
        }

        private final Bitmap generateAdaptiveIcon(Drawable fgDrawable, Drawable bgDrawable) {
            AdaptiveIconDrawable adaptiveIconDrawable = new AdaptiveIconDrawable(bgDrawable, fgDrawable);
            Bitmap createBitmap = Bitmap.createBitmap(fgDrawable.getIntrinsicWidth(), fgDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            adaptiveIconDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            adaptiveIconDrawable.draw(canvas);
            return createBitmap;
        }
    }
}
