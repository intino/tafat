package tafat.site.route.handler;

import tafat.site.route.notification.NotificationServer;
import model.conection.HttpResponse;
import model.conection.Request;
import model.conection.Response;
import model.handler.Handler;

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
