package tafat.framework.mockServices.classToService;

import tafat.framework.services.SensorService;
import tafat.sgi.model.conection.HttpResponse;
import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

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
