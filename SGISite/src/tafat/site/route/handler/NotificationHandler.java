package tafat.site.route.handler;

import tafat.site.route.notification.NotificationServer;
import tafat.site.route.identification.UserIdentification;
import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.sgi.http.connection.model.handler.Handler;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

public class NotificationHandler implements Handler{
    @Override
    public Response handle(Request request) {
        try {
            NotificationServer.instance().sendNotification(getUserIdentification(request), removeSimulationParameters(request).getBody());
        } catch (WebsocketNotConnectedException exception) {
            return new HttpResponse(500, "{\"error\":\"user "+request.getParameter("username")+" socket closed\"}");
        }
        return new HttpResponse(200, "{\"message\":\"Ok\"}");
    }

    private Request removeSimulationParameters(Request request) {
        request.removeParameter("simulationId");
        request.removeParameter("username");
        return request;
    }

    private UserIdentification getUserIdentification(Request request) {
        return new UserIdentification(request.getParameter("username"), request.getParameter("simulationId"));
    }
}