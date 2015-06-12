package tafat.framework.services.defaults;

import tafat.framework.state.ServerState;
import tafat.sgi.model.conection.HttpRequest;
import tafat.sgi.model.conection.Request;
import org.junit.Test;

import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static org.junit.Assert.assertEquals;

public class SensorServiceTest {
    @Test
    public void shouldRemoveSensor() {
        Request removeRequest = getSafe(() -> new HttpRequest("DELETE", "/Sensor", "{\"measurableAttributeName\":\"watcher1\", \"objectId\":\"objectid\",\"username\":\"ramclen\" }"));
        Request installRequest = getSafe(() -> new HttpRequest("DELETE", "/Sensor", ""));
        installRequest.setParameter("measurableAttributeName","watcher1");
        installRequest.setParameter("username","ramclen");
        installRequest.setParameter("objectId","objectid");

        new SensorsService().installSensor(installRequest);
        assertEquals(1, ServerState.state().watcherContainer().getWatchers().size());

        new SensorsService().remove(removeRequest);
        assertEquals(0, ServerState.state().watcherContainer().getWatchers().get("ramclen").size());
    }
}
