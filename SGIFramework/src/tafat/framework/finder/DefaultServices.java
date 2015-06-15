package tafat.framework.finder;

import tafat.framework.services.*;

import java.util.HashMap;
import java.util.Map;

public class DefaultServices implements ServiceFinder{
    @Override
    public Map<Class<?>, Class<? extends FrameworkService>> search(String path) {
        HashMap<Class<?>, Class<? extends FrameworkService>> defaultServices = new HashMap<>();
        defaultServices.put(BreakpointService.class, tafat.framework.services.defaults.BreakpointService.class);
        defaultServices.put(NotificationService.class, tafat.framework.services.defaults.PushNotification.class);
        defaultServices.put(SensorService.class, tafat.framework.services.defaults.SensorsService.class);
        defaultServices.put(SubscriptionService.class, tafat.framework.services.defaults.SubscribeService.class);
        return defaultServices;
    }
}
