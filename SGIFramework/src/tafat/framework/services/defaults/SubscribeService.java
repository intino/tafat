package tafat.framework.services.defaults;

import tafat.framework.state.ServerState;
import tafat.framework.services.ServicesManager;
import tafat.framework.services.NotificationService;
import tafat.framework.services.SubscriptionService;
import tafat.sgi.model.conection.HttpResponse;
import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static tafat.sgi.exception.ExceptionHandler.runSafe;

public class SubscribeService implements SubscriptionService {

    private ServerState serverState;

    public SubscribeService() {
        this.serverState = ServerState.state();
    }

    @Override
    public Response subscribe(Request request) {
        if(request.getParameter("username")==null)
            return new HttpResponse(400, "{\"error\":\"error parameter username not found\"}");

        sendInitGraphicNotification(request.getParameter("username"), buildMessage(serverState.getInitialDate(), serverState.getFinalDate()));
        return new HttpResponse(200, "{\"message\":\"Ok\"}");
    }

    private String buildMessage(Date initialDate, Date finalDate) {
        return "{\"type\":\"initGraphic\", \"info\":{\"init\":\"" + formatDate(initialDate) + "\", \"end\":\"" + formatDate(finalDate) + "\",\"precision\":"+1000+"}}";
    }

    private String formatDate(Date initialDate) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return getSafe(() -> format.format(initialDate));
    }

    private void sendInitGraphicNotification(String username, String message) {
        new Thread(() -> {
            runSafe(() -> {
                Thread.sleep(500);
                ServicesManager.get(NotificationService.class).push(username, message);
            });
        }).start();
    }
}
