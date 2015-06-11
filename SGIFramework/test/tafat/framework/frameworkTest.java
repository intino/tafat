package tafat.framework;

import Controller.HttpPetitionClient;
import Controller.HttpService;
import tafat.framework.handler.HandlerDictionary;
import tafat.framework.integration.SimulationAgentWrapper;
import tafat.framework.services.ServicesManager;
import tafat.framework.finder.ReflectionServiceFinder;
import model.conection.HttpRequest;
import model.conection.Response;
import model.proccesor.RequestProcessor;
import org.junit.Test;

import java.net.URI;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class frameworkTest {

    @Test
    public void getResponseFromRootObjectsEndPoint() throws Exception {
        ServicesManager.setUp(new ReflectionServiceFinder(), new URI("framework.mockServices.classToService").getPath());
        new HttpService(new RequestProcessor(new HandlerDictionary(new SimulationAgentWrapper(null))), 50000).start();

        Response response = new HttpPetitionClient().sendRequest(new HttpRequest("GET", "localhost:50000/RootObjects", ""));
        assertEquals(200, response.getStatusCode());
        assertEquals("Ok rootObjects", response.getBody());
    }

    @Test
    public void startFramework() {
        try {
//            new Framework().start("simulation3", new URI("framework.mockServices.classToService"));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
