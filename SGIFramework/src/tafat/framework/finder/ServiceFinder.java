package tafat.framework.finder;

import tafat.framework.services.FrameworkService;

import java.util.Map;

public interface ServiceFinder {

    Map<Class<?>, Class<? extends FrameworkService>> search(String path);

}
