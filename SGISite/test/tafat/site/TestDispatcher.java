package tafat.site;

import org.junit.Test;
import tafat.sgi.http.connection.controller.PetitionClient;
import tafat.sgi.discovery.connection.NetInformation;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.site.route.handler.ProxyHandler;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class TestDispatcher {

    private String responseSensor = "{\n" +
            "  \"sensors\":[\n" +
            "    {\"name\":\"sensor1\"},\n" +
            "    {\"name\":\"sensor2\"}\n" +
            "  ]\n" +
            "}";

    @Test
    public void getSimulator32Sensors() throws IOException {
        ServerState.instance().addSubscription(new NetInformation("32", "192.168.1.5", 50000));
        Response response = new ProxyHandler(createClientConnection()).handle(getSafe(()->new HttpRequest("GET", "/sensors", "{\"simulationId\":\"32\"}")));
        assertEquals(responseSensor, response.getBody());
    }

    private PetitionClient createClientConnection() {
        return new PetitionClient() {
            @Override
            public Response sendRequest(Request request) throws IOException {
                return new HttpResponse(200, responseSensor);
            }

            @Override
            public void sendRequest(Request request, Action done) throws IOException {

            }
        };

    }
}
