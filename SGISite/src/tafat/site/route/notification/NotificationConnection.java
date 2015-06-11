package tafat.site.route.notification;

import tafat.site.route.identification.UserIdentification;
import org.java_websocket.WebSocket;

public interface NotificationConnection {

    void onClose(Clojure function);

    void send(WebSocket mockSocket, String message);

    void newConnection(UserIdentification userId, WebSocket webSocket);

    void onConnection(Clojure clojure);

    interface Clojure {
        void execute(UserIdentification userId, WebSocket webSocket);
    }
}

