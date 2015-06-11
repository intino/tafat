package tafat.framework.services;

import model.conection.HttpResponse;
import model.conection.Request;
import model.conection.Response;

public interface SensorService extends FrameworkService{
    Response installSensor(Request request);

    HttpResponse remove(Request request);
}
