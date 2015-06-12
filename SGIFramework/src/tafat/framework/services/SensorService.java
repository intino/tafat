package tafat.framework.services;

import tafat.sgi.model.conection.HttpResponse;
import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

public interface SensorService extends FrameworkService{
    Response installSensor(Request request);

    HttpResponse remove(Request request);
}
