package com.redphx.betterxc.ws;

import android.util.Log;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;

/* compiled from: PointerProviderServer.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 '2\u00020\u0001:\u0001'B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0016J,\u0010\u0011\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001c\u0010\u0018\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0015H\u0016J'\u0010\u001a\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\u001b\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001cH\u0016¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u000fH\u0016J\b\u0010 \u001a\u00020!H\u0002J\u000e\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u0017J\u0010\u0010$\u001a\u0004\u0018\u00010!2\u0006\u0010%\u001a\u00020&¨\u0006("}, d2 = {"Lcom/redphx/betterxc/ws/PointerProviderServer;", "Lorg/java_websocket/server/WebSocketServer;", "address", "Ljava/net/InetSocketAddress;", "<init>", "(Ljava/net/InetSocketAddress;)V", "onWebsocketHandshakeReceivedAsServer", "Lorg/java_websocket/handshake/ServerHandshakeBuilder;", "conn", "Lorg/java_websocket/WebSocket;", "draft", "Lorg/java_websocket/drafts/Draft;", "request", "Lorg/java_websocket/handshake/ClientHandshake;", "onOpen", "", "handshake", "onClose", "code", "", "reason", "", "remote", "", "onMessage", "message", "onError", "ex", "Lkotlin/Exception;", "Ljava/lang/Exception;", "(Lorg/java_websocket/WebSocket;Ljava/lang/Exception;)V", "onStart", "pointerProtocolVersion", "", "pointerCaptureChangeMessage", "hasCapture", "motionEventToMessage", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "Companion", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class PointerProviderServer extends WebSocketServer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOG_TAG = "PointerProviderSever";
    public static final short PROTOCOL_VERSION = 2;
    public static final int SIZE_MOVE = 5;
    public static final int SIZE_POINTER_CAPTURE_CHANGED = 2;
    public static final int SIZE_PRESS = 2;
    public static final int SIZE_PROTOCOL_VERSION = 3;
    public static final int SIZE_SCROLL = 5;

    @Override // org.java_websocket.server.WebSocketServer
    public void onMessage(WebSocket conn, String message) {
    }

    /* compiled from: PointerProviderServer.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\n\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/redphx/betterxc/ws/PointerProviderServer$Companion;", "", "<init>", "()V", "LOG_TAG", "", "PORT", "", "getPORT", "()I", "SIZE_PROTOCOL_VERSION", "SIZE_MOVE", "SIZE_PRESS", "SIZE_SCROLL", "SIZE_POINTER_CAPTURE_CHANGED", "PROTOCOL_VERSION", "", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getPORT() {
            return 9269;
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PointerProviderServer(InetSocketAddress address) {
        super(address, 1);
        Intrinsics.checkNotNullParameter(address, "address");
    }

    @Override // org.java_websocket.WebSocketAdapter, org.java_websocket.WebSocketListener
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer = super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
        onWebsocketHandshakeReceivedAsServer.put("Access-Control-Allow-Origin", "*");
        Intrinsics.checkNotNull(onWebsocketHandshakeReceivedAsServer);
        return onWebsocketHandshakeReceivedAsServer;
    }

    @Override // org.java_websocket.server.WebSocketServer
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Log.d(LOG_TAG, "onOpen()");
        broadcast(pointerProtocolVersion());
    }

    @Override // org.java_websocket.server.WebSocketServer
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Log.d(LOG_TAG, "onClose()");
    }

    @Override // org.java_websocket.server.WebSocketServer
    public void onError(WebSocket conn, Exception ex) {
        String str;
        if (ex == null || (str = ex.getMessage()) == null) {
            str = "";
        }
        Log.d("PointerProviderSever onError", str);
    }

    @Override // org.java_websocket.server.WebSocketServer
    public void onStart() {
        Log.d(LOG_TAG, "onStart()");
    }

    private final byte[] pointerProtocolVersion() {
        byte[] array = ByteBuffer.allocate(3).put(PointerAction.PROTOCOL_VERSION.getValue()).putShort((short) 2).array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        return array;
    }

    public final byte[] pointerCaptureChangeMessage(boolean hasCapture) {
        byte[] array = ByteBuffer.allocate(2).put(PointerAction.POINTER_CAPTURE_CHANGED.getValue()).put(hasCapture ? (byte) 1 : (byte) 0).array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        return array;
    }

    public final byte[] motionEventToMessage(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int action = event.getAction();
        if (action == 2) {
            return ByteBuffer.allocate(5).put(PointerAction.MOVE.getValue()).putShort((short) MathKt.roundToInt(event.getX())).putShort((short) MathKt.roundToInt(event.getY())).array();
        }
        if (action == 8) {
            return ByteBuffer.allocate(5).put(PointerAction.SCROLL.getValue()).putShort((short) event.getAxisValue(9)).putShort((short) event.getAxisValue(10)).array();
        }
        if (action == 11) {
            return ByteBuffer.allocate(2).put(PointerAction.BUTTON_PRESS.getValue()).put((byte) event.getActionButton()).array();
        }
        if (action != 12) {
            return null;
        }
        return ByteBuffer.allocate(2).put(PointerAction.BUTTON_RELEASE.getValue()).put((byte) event.getActionButton()).array();
    }
}
