package tafat.framework.services;

import tafat.framework.finder.ServiceFinder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class ServicesManager {
    private static Map<Class<?>, Object >services;

    public static void setUp() {
        services = new HashMap<>();
    }

    public static <T extends FrameworkService> T get(Class<T>  frameworkService) {
        Object service = services.get(frameworkService);
        return service == null ? null : (T) service;
    }

    public static void set(Class<? extends FrameworkService> frameworkServiceClass, FrameworkService implementationClass) {
        services.put(frameworkServiceClass, implementationClass);
    }

    //TODO Testear el setup
    public static void setUp(ServiceFinder finder, String path) {
        services = new HashMap<>();
        String defaultServicesLocation = getSafe(()->new URI("framework.integrationTest.services.defaults").getPath(), "Error on the default integrationTest.services path location");
        assignServices(finder.search(defaultServicesLocation));
        assignServices(finder.search(path));
    }

    private static void assignServices(Map<Class<?>, Class<? extends FrameworkService>> defaultSearch) {
        for (Class<?> classes : defaultSearch.keySet())
            addNewService(defaultSearch.get(classes), classes);
    }

    private static void addNewService(Class<? extends FrameworkService> finderSearch, Class<?> serviceInterface) {
        FrameworkService serviceImplementation = getSafe(()->finderSearch.newInstance());
        if (serviceImplementation!= null)
            services.put(serviceInterface, serviceImplementation);
    }
}
