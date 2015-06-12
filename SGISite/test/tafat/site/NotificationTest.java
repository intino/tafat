package tafat.site;

import tafat.site.route.HandlerDictionary;
import tafat.site.route.handler.NotificationHandler;
import tafat.site.route.identification.UserIdentification;
import tafat.site.route.notification.NotificationConnection;
import tafat.site.route.notification.NotificationServer;
import tafat.site.route.notification.WebSocketConnection;
import tafat.sgi.model.conection.HttpRequest;
import tafat.sgi.model.conection.Response;
import tafat.sgi.model.handler.Handler;
import org.java_websocket.WebSocket;
import org.junit.Before;
import org.junit.Test;

import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.mock;

public class NotificationTest {

    private NotificationConnection connection;

    @Before
    public void setUp() throws Exception {
        connection = new WebSocketConnection(35000);
        NotificationServer.instance().setConnection(connection);
    }

    @Test
    public void dictionaryFindNotificationsHandler() {
        Handler handler = new HandlerDictionary().find(getSafe(() -> new HttpRequest("POST", "/notification", "{\"sensor\":14}")));
        assertSame(NotificationHandler.class, handler.getClass());
    }

    @Test
    public void sendErrorMessageWhenUserNotExist() {
        connection.newConnection(new UserIdentification("Francisco", "32"), mock(WebSocket.class));
        HttpRequest request = getSafe(() -> new HttpRequest("POST", "/notificiation", "{\"simulationId\":\"32\", \"username\":\"Juan\", \"temperature\":\"15\"}"));
        Response response = new NotificationHandler().handle(request);
        assertEquals(500, response.getStatusCode());
    }

    @Test
    public void receiveANotificationFromSimulation32ToUserFrancisco() {
        connection.newConnection(new UserIdentification("Francisco", "32"), mock(WebSocket.class));
        Response response = new NotificationHandler().handle(getSafe(() -> new HttpRequest("POST", "/notificiation", "{\"simulationId\":\"32\", \"username\":\"Francisco\", \"temperature\":\"32\"}")));
        assertEquals("{\"message\":\"Ok\"}", response.getBody());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void sendANotificationToUserJuan() {
        new NotificationHandler().handle(getSafe(() -> new HttpRequest("POST", "/notificiation", "{\"simulationId\":\"32\", \"username\":\"Juan\", \"temperature\":\"15\"}")));
//        verify(connection).send(mockSocket, "{\"simulationId\":\"32\", \"username\":\"Juan\", \"temperature\":\"15\"}");
    }

    private NotificationConnection createConnection() {
        return mock(NotificationConnection.class);
    }

}
