package tafat.site;

import org.junit.Test;
import tafat.sgi.discovery.connection.NetInformation;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.site.route.handler.SimulatorSubscribedHandler;

import static junit.framework.TestCase.assertEquals;
import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class EndpointSimulationsListTest {
    @Test
    public void shouldReturnJsonArrayOfSimulations() {
        ServerState.instance().addSubscription(new NetInformation("Simulation32","", 50000));
        ServerState.instance().addSubscription(new NetInformation("Simulation33","", 50000));
        ServerState.instance().addSubscription(new NetInformation("Simulation34","", 50000));

        Response response = getSafe(() -> new SimulatorSubscribedHandler().handle(new HttpRequest("GET", "/Simulations", "")));

        assertEquals("[{\"name\":\"Simulation34\"},{\"name\":\"Simulation33\"},{\"name\":\"Simulation32\"}]", response.getBody());
        assertEquals(200, response.getStatusCode());
    }
}
