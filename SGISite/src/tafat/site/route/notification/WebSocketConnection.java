package tafat.site.route.notification;

import tafat.site.route.identification.UserIdentification;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class WebSocketConnection extends WebSocketServer implements NotificationConnection {
    private Clojure onConnection;

    public WebSocketConnection() throws UnknownHostException {
    }

    public WebSocketConnection(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onClose(Clojure function) {

    }

    @Override
    public void send(WebSocket mockSocket, String message) {
        mockSocket.send(message);
    }

    @Override
    public void onConnection(Clojure clojure) {
        this.onConnection = clojure;
    }

    @Override
    public void newConnection(UserIdentification userId, WebSocket webSocket) {
        onConnection.execute(userId, webSocket);
    }


    //WEBSOCKET
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        handshake.getResourceDescriptor();
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        UserIdentification userId = new UserIdentification(message.split(":")[0], message.split(":")[1]);
        onConnection.execute(userId, conn);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println(ex.getCause());
    }


}
