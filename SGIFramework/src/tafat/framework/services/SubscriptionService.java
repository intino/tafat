package tafat.framework.services;

import model.conection.Request;
import model.conection.Response;

public interface SubscriptionService extends FrameworkService {
     Response subscribe(Request request);
}
