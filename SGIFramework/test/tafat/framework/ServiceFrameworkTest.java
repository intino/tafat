package tafat.framework;

import tafat.framework.finder.ServiceFinder;
import tafat.framework.services.ServicesManager;
import tafat.framework.services.BreakpointService;
import tafat.framework.services.FrameworkService;
import tafat.framework.services.SensorService;
import org.junit.Before;
import org.junit.Test;
import tafat.framework.mockServices.classToService.BreakpointHandlerService;
import tafat.framework.mockServices.classToService.NotificationHandlerService;
import tafat.framework.mockServices.classToService.SensorHandlerService;
import tafat.framework.mockServices.classToService.SubscribeHandlerService;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceFrameworkTest {

    @Before
    public void setUp() throws Exception {
        ServicesManager.setUp(buildMockFinder(), "framework");
    }

    @Test
    public void addAllServicesOnSetup() {
        ServicesManager.setUp(buildMockFinder(), "framework");
        assertNotNull(ServicesManager.get(BreakpointService.class));
    }

    @Test
    public void addABreakpointServiceHandler() {
        BreakpointService breakpointService = mock(BreakpointService.class);
        ServicesManager.set(BreakpointService.class, breakpointService);
        assertEquals(breakpointService, ServicesManager.get(BreakpointService.class));
    }

    @Test
    public void returnASensorHandler() {
        assertNotNull(ServicesManager.get(SensorService.class));
    }

    @Test
    public void shouldReturnNullWithAServicesNotAdded() {
        assertEquals(null, ServicesManager.get(FakeService.class));
    }

    @Test
    public void acceptNewService() {
        FakeService fakeService = new FakeService() {};
        ServicesManager.set(FakeService.class, fakeService);
        assertEquals(fakeService, ServicesManager.get(FakeService.class));
    }

    private ServiceFinder buildMockFinder() {
        ServiceFinder finder = mock(ServiceFinder.class);
        when(finder.search("framework")).thenReturn(buildServiceList());
        return finder;
    }

    private Map<Class<?>, Class<? extends FrameworkService>> buildServiceList() {
        Map<Class<?>, Class<? extends FrameworkService>> classes = new HashMap<>();
        classes.put(BreakpointHandlerService.class.getInterfaces()[0], BreakpointHandlerService.class);
        classes.put(NotificationHandlerService.class.getInterfaces()[0], NotificationHandlerService.class);
        classes.put(SensorHandlerService.class.getInterfaces()[0],SensorHandlerService.class);
        classes.put(SubscribeHandlerService.class.getInterfaces()[0], SubscribeHandlerService.class);
        return classes;
    }



    public interface FakeService extends FrameworkService {

    }

}
