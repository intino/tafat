package tafat.site.route.notification;

import tafat.site.route.identification.UserIdentification;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

import java.util.Hashtable;

public class NotificationServer {
    private static NotificationServer instance;
    private NotificationConnection connection;
    private Hashtable<UserIdentification, WebSocket> subscribedUser;

    private NotificationServer() {
        subscribedUser = new Hashtable<>();
    }

    public static NotificationServer instance() {
        if(instance == null)
            instance = new NotificationServer();
        return instance;
    }

    public void sendNotification(UserIdentification userIdentification, String message) throws WebsocketNotConnectedException {
        WebSocket webSocket = subscribedUser.get(userIdentification);
        if(existConnection(webSocket))
            connection.send(webSocket, message);
        else
            throw new WebsocketNotConnectedException();
    }

    private boolean existConnection(WebSocket webSocket) {
        if(webSocket != null)
            if(!webSocket.isClosed())
                return true;
        return false;
    }

    public void setConnection(NotificationConnection connection) {
        this.connection = connection;
        this.connection.onConnection(this::subscribeUser);
    }

    private void subscribeUser(UserIdentification userId, WebSocket webSocket) {
        subscribedUser.put(userId, webSocket);
    }

    public boolean isSubscribed(UserIdentification userId) {
        return subscribedUser.get(userId)!=null;
    }

    public void sendBroadcastNotification(String simulationId, String message) {
        subscribedUser.keySet().stream()
                .filter(userId->userId.getSimulationId().equals(simulationId))
                .forEach(userIdentification -> sendNotification(userIdentification, message));
    }
}
