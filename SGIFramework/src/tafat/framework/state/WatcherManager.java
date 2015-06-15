package tafat.framework.state;

import tafat.framework.integration.simulation.Watcher;
import tafat.framework.services.ServicesManager;
import tafat.framework.services.NotificationService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WatcherManager {
    private ValueGetter valueGetter;

    public WatcherManager(ValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    public void sendWatcherValues(Date date) {
        for (String username : ServerState.state().watcherContainer().getWatchers().keySet())
            for (Watcher watcher : ServerState.state().watcherContainer().getWatchers().get(username))
                sendSensor(username, watcher, date);
    }

    private void sendSensor(String username, Watcher watcher, Date date){
        ServicesManager.get(NotificationService.class).push(username, buildMessage(watcher, date));
    }

    private String buildMessage(Watcher watcher, Date date) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
        return "{\"type\":\"sensorValues\", \"info\":{\"time\":\""+format.format(date)+"\", " +
                "\"measurableAttributeName\":\"" + watcher.getMeasurableAttribute() + "\"," +
                "\"value\":"+valueGetter.get(watcher.path())+", \"objectId\":\"" + watcher.getObjectId() +"\"}}";
    }

    public interface ValueGetter{
        String get(String path);
    }
}
