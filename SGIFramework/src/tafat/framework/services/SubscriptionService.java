package tafat.framework.services;

import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

public interface SubscriptionService extends FrameworkService {
     Response subscribe(Request request);
}
