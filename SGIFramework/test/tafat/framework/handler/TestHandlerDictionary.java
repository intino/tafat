package tafat.framework.handler;

import tafat.framework.integration.SimulationAgentWrapper;
import tafat.framework.services.ServicesManager;
import tafat.framework.services.BreakpointService;
import tafat.framework.services.FrameworkService;
import tafat.framework.finder.ServiceFinder;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.handler.Handler;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestHandlerDictionary {

    @Test
    public void givePlayHandler() {
        ServiceFinder finder = mock(ServiceFinder.class);
        when(finder.search("framework")).thenReturn(getServiceList(BreakpointService.class));
        ServicesManager.setUp(finder, "framework");

        Handler handler = new HandlerDictionary(new SimulationAgentWrapper(null)).find(getSafe(()->new HttpRequest("POST", "/Play", "")));
        //assertSame(Play.class, handler);
    }

    private Map<Class<?>, Class<? extends FrameworkService>> getServiceList(Class<BreakpointService> service) {
        Map<Class<?>, Class<? extends FrameworkService>> classes = new HashMap<>();
        classes.put(service.getInterfaces()[0], service);
        return classes;
    }


}
