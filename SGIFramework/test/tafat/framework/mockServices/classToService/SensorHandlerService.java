package tafat.framework.mockServices.classToService;

import tafat.framework.services.SensorService;
import model.conection.HttpResponse;
import model.conection.Request;
import model.conection.Response;

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
