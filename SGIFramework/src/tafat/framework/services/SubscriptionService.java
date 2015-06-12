package tafat.framework.services;

import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

public interface SubscriptionService extends FrameworkService {
     Response subscribe(Request request);
}
