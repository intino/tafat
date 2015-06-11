package tafat.framework.services.servicesFinder;

import tafat.framework.mockServices.classToService.BreakpointHandlerService;
import tafat.framework.mockServices.allInOneServices.AllServices;
import tafat.framework.services.BreakpointService;
import tafat.framework.services.FrameworkService;
import org.junit.Test;
import tafat.framework.finder.ReflectionServiceFinder;

import java.util.Map;

import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;

public class ReflectionServiceFinderTest {

    @Test
    public void shouldFindBreakpointService() throws ClassNotFoundException {
        Map<Class<?>, Class<? extends FrameworkService>> search = new ReflectionServiceFinder().search("framework.mockServices.classToService");
        assertSame(BreakpointHandlerService.class, search.get(BreakpointService.class));
    }

    @Test
    public void notReturnInnerInterfaces() {
        Map<Class<?>, Class<? extends FrameworkService>> search = new ReflectionServiceFinder().search("framework");
        for (Class<?> serviceClass : search.keySet())
            assertTrue("inner interface(FakeService) find", !serviceClass.getName().contains("FakeService"));
    }

    @Test
    public void detectALotImplementationOfAInterface() {
        Map<Class<?>, Class<? extends FrameworkService>> search = new ReflectionServiceFinder().search("framework.mockServices.allInOneServices");
        assertSame(AllServices.class, search.get(BreakpointService.class));
    }

    public interface FakeService extends FrameworkService {
        
    }

}
