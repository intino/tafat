package tafat.framework.services;

import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

public interface SensorService extends FrameworkService{
    Response installSensor(Request request);

    HttpResponse remove(Request request);
}
