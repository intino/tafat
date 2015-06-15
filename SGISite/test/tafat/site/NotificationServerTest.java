package tafat.site;

import tafat.site.route.identification.UserIdentification;
import tafat.site.route.notification.NotificationConnection;
import tafat.site.route.notification.NotificationServer;
import tafat.site.route.notification.WebSocketConnection;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class NotificationServerTest {

    private NotificationConnection connection;
    private NotificationServer notificationServer;

    @Before
    public void setUp() throws Exception {
        connection = mock(NotificationConnection.class);
        notificationServer = NotificationServer.instance();
    }

    @Test
    public void receiveNewConnectionFromUserToSimulation3() throws UnknownHostException {
        UserIdentification userId = new UserIdentification("user", "simulation3");
        WebSocket webSocket = mock(WebSocket.class);

        WebSocketConnection connection = new WebSocketConnection();
        notificationServer.setConnection(connection);
        connection.newConnection(userId, webSocket);

        assertTrue(notificationServer.isSubscribed(userId));
    }

    @Test
    public void sendNotificationToUserOnSimulation4() throws UnknownHostException {
        UserIdentification userId = new UserIdentification("user", "simulation3");
        WebSocket webSocket = mock(WebSocket.class);
        launchNewConnection(userId, webSocket);

        notificationServer.setConnection(this.connection);
        notificationServer.sendNotification(userId, "hello");

        verify(this.connection).send(webSocket, "hello");
    }

    @Test
    public void sendMessageToUser2OnSimulation1WithMoreSimulationsAndUsers() throws UnknownHostException {
        launchNewConnection(new UserIdentification("user", "simulation1"), mock(WebSocket.class));
        launchNewConnection(new UserIdentification("user2", "simulation1"), mock(WebSocket.class));
        launchNewConnection(new UserIdentification("user", "simulation2"), mock(WebSocket.class));
        WebSocket webSocket = mock(WebSocket.class);
        UserIdentification user2 = new UserIdentification("user2", "simulation2");
        launchNewConnection(user2, webSocket);

        notificationServer.sendNotification(user2, "Hello user2 of Simulation2");

        verify(webSocket).send("Hello user2 of Simulation2");
    }

    @Test
    public void whenSocketConnectionIsClosedThrowException() throws UnknownHostException {
        UserIdentification userId = new UserIdentification("user", "simulation3");
        WebSocket webSocket = mock(WebSocket.class);
        when(webSocket.isClosed()).thenReturn(true);
        launchNewConnection(userId, webSocket);

        notificationServer.setConnection(this.connection);

        try {
            notificationServer.sendNotification(userId, "hello");
            assertTrue(false);
        } catch (WebsocketNotConnectedException exception) {
            assertTrue(true);
        }

        verify(this.connection, never()).send(webSocket, "hello");
    }

    private void launchNewConnection(UserIdentification userId, WebSocket webSocket) throws UnknownHostException {
        WebSocketConnection connection = new WebSocketConnection();
        notificationServer.setConnection(connection);
        connection.newConnection(userId, webSocket);
    }

}