package tafat.framework.services;

import org.junit.Test;
import tafat.framework.finder.ServiceFinder;
import tafat.framework.mockServices.classToService.BreakpointHandlerService;
import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceManagerTest {
    @Test
    public void shouldReturnABreakpointService() {
        ServiceFinder finder = mock(ServiceFinder.class);
        when(finder.search("tafat.framework")).thenReturn(getServiceList(BreakpointHandlerService.class));
        ServicesManager.setUp(finder, "tafat.framework");
        assertSame(ServicesManager.get(BreakpointService.class).getClass(), BreakpointHandlerService.class);
    }

    @Test
    public void shouldReturnAServiceBreakpoint() {
        ServiceFinder finder = mock(ServiceFinder.class);
        when(finder.search("tafat.framework")).thenReturn(getServiceList(BreakpointHandlerService.class));
        ServicesManager.setUp(finder, "tafat.framework");
        assertSame(ServicesManager.get(BreakpointService.class).getClass(), BreakpointHandlerService.class);
    }

    @Test
    public void shouldHasAllReferencesToUltraFakeClass() {
        ServiceFinder finder = mock(ServiceFinder.class);
        when(finder.search("tafat.framework")).thenReturn(getServiceList(AllServicesImplementation.class));
        ServicesManager.setUp(finder, "tafat.framework");
        assertSame(AllServicesImplementation.class, ServicesManager.get(BreakpointService.class).getClass());
        assertSame(AllServicesImplementation.class, ServicesManager.get(NotificationService.class).getClass());
        assertSame(AllServicesImplementation.class, ServicesManager.get(SensorService.class).getClass());
        assertSame(AllServicesImplementation.class, ServicesManager.get(SubscriptionService.class).getClass());
    }

    private Map<Class<?>, Class<? extends FrameworkService>> getServiceList(Class<? extends FrameworkService> service) {
        Map<Class<?>, Class<? extends FrameworkService>> classes = new HashMap<>();
        for (Class<?> aClass : service.getInterfaces()) {
            classes.put(aClass, service);
        }
        return classes;
    }

    public static class AllServicesImplementation implements BreakpointService, SensorService, NotificationService, SubscriptionService{
        public AllServicesImplementation() {
        }

        @Override
        public Response create(Request breakpointId) {
            return null;
        }

        @Override
        public Response delete(Request request) {
            return null;
        }

        @Override
        public Response installSensor(Request request) {
            return null;
        }

        @Override
        public HttpResponse remove(Request request) {
            return null;
        }

        @Override
        public void push(String username, String message) {

        }

        @Override
        public void broadcast(String message) {

        }

        @Override
        public Response subscribe(Request request) {
            return null;
        }
    }
}