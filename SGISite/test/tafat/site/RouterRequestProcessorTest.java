package tafat.site;

import tafat.sgi.model.conection.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import tafat.site.route.HandlerDictionary;

import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static junit.framework.TestCase.assertEquals;

public class RouterRequestProcessorTest {

    private RouterRequestProcessor routerRequestProcessor;

    @Before
    public void setUp() throws Exception {
        routerRequestProcessor = new RouterRequestProcessor(new HandlerDictionary());
    }

    @Test
    public void simulationIdToBodyWithBody() {
        HttpRequest request = routerRequestProcessor.moveSimulationIdToBody(getSafe(()->new HttpRequest("POST", "/13S/Sensors", "{\"sensor2\":13}")));
        assertEquals("{\"sensor2\":13,\"simulationId\":\"13S\"}", request.getBody());
        assertEquals("13S", request.getParameter("simulationId"));
    }

    @Test
    public void simulationIdToBodyWithEmptyBody() {
        HttpRequest request = routerRequestProcessor.moveSimulationIdToBody(getSafe(()->new HttpRequest("POST", "/13S/Sensors", "")));
        assertEquals("{\"simulationId\":\"13S\"}", request.getBody());
        assertEquals("13S", request.getParameter("simulationId"));
    }

}
