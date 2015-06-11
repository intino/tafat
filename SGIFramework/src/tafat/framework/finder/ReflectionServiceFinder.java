package tafat.framework.finder;

import tafat.framework.services.FrameworkService;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionServiceFinder implements ServiceFinder {

    @Override
    public Map<Class<?>, Class<? extends FrameworkService>> search(String path) {
        return buildMap(searchFrameworkServices(path));
    }

    private Map<Class<?>, Class<? extends FrameworkService>> buildMap(List<Class<? extends FrameworkService>> services) {
        Map<Class<?>, Class<? extends FrameworkService>> serviceMap = new HashMap<>();
        for (Class<? extends FrameworkService> aClass : services)
            for (Class<?> interfaceService : aClass.getInterfaces())
                serviceMap.put(interfaceService, aClass);
        return serviceMap;
    }


    private List<Class<? extends FrameworkService>> searchFrameworkServices(String path) {
        Set<Class<? extends FrameworkService>> subTypesOf = new Reflections().getSubTypesOf(FrameworkService.class);
        //I use filter package, because Reflections didnt work with the configuration path --> new Reflections(path)
        Stream<Class<? extends FrameworkService>> collect = subTypesOf.parallelStream().filter((element) -> isInterface(element) && onThePackage(element, path));
        return collect.collect(Collectors.toList());
    }

    private boolean onThePackage(Class<? extends FrameworkService> element, String path) {
        if(path.equals(""))//When search in all project is the user project not Framework Project
            return !element.getPackage().getName().startsWith("framework");
        return element.getPackage().getName().startsWith(path);
    }

    private boolean isInterface(Class<? extends FrameworkService> element) {
        return element != null && !element.isInterface()  && element.getConstructors().length != 0;
    }
}
