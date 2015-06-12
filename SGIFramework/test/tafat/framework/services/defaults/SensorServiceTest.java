package tafat.framework.services.defaults;

import org.junit.Test;
import tafat.framework.state.ServerState;
import tafat.sgi.model.conection.HttpRequest;
import tafat.sgi.model.conection.Request;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class SensorServiceTest {
    @Test
    public void shouldRemoveSensor() {
        Request removeRequest = getSafe(() -> new HttpRequest("DELETE", "/Sensor", "{\"measurableAttributeName\":\"watcher1\", \"objectId\":\"objectid\",\"username\":\"ramclen\" }"));
        Request installRequest = getSafe(() -> new HttpRequest("POST", "/Sensor", ""));
        installRequest.setParameter("measurableAttributeName","watcher1");
        installRequest.setParameter("username", "ramclen");
        installRequest.setParameter("objectId", "objectid");

        new SensorsService().installSensor(installRequest);
        assertThat(ServerState.state().watcherContainer().getWatchers().size(), greaterThanOrEqualTo(1));

        new SensorsService().remove(removeRequest);
        assertThat(ServerState.state().watcherContainer().getWatchers().get("ramclen").size(), lessThanOrEqualTo(1));
    }
}
