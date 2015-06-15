package tafat.site.route.handler;

import tafat.site.route.notification.NotificationServer;
import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.sgi.http.connection.model.handler.Handler;

import java.io.IOException;

public class BroadcastNotificationHandler implements Handler {
    @Override
    public Response handle(Request request) throws IOException {
        NotificationServer.instance().sendBroadcastNotification(request.getParameter("simulationId"), removeSimulationParameters(request).getBody());
        return new HttpResponse(200, "{\"message\":\"Ok\"}");
    }

    private Request removeSimulationParameters(Request request) {
        request.removeParameter("simulationId");
        request.removeParameter("username");
        return request;
    }

}
