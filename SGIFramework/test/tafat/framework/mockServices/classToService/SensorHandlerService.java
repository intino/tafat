package tafat.framework.mockServices.classToService;

import tafat.framework.services.SensorService;
import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

public class SensorHandlerService implements SensorService {
    public SensorHandlerService() {
    }

    @Override
    public Response installSensor(Request request) {
        return null;
    }

    @Override
    public HttpResponse remove(Request request) {
        return null;
    }
}
