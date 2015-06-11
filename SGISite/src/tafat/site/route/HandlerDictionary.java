package tafat.site.route;

import Controller.HttpPetitionClient;
import model.conection.Request;
import model.handler.Handler;
import model.proccesor.RequestDictionary;
import tafat.site.route.handler.BroadcastNotificationHandler;
import tafat.site.route.handler.NotificationHandler;
import tafat.site.route.handler.ProxyHandler;
import tafat.site.route.handler.SimulatorSubscribedHandler;

import java.util.HashMap;

public class HandlerDictionary extends HashMap<String, Handler>implements RequestDictionary {

    public HandlerDictionary() {
        super();
        put("/notification", new NotificationHandler());
        put("/broadcast-notification", new BroadcastNotificationHandler());
        put("/Simulations", new SimulatorSubscribedHandler());
    }

    @Override
    public Handler find(Request request) {
        Handler handler = get(request.getPath());
        return (handler == null)? new ProxyHandler(new HttpPetitionClient()) : handler;
    }
}