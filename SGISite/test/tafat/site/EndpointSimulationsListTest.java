package tafat.site;

import connection.NetInformation;
import model.conection.HttpRequest;
import model.conection.Response;
import org.junit.Test;
import tafat.site.route.handler.SimulatorSubscribedHandler;

import static exception.ExceptionHandler.getSafe;
import static junit.framework.TestCase.assertEquals;

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
