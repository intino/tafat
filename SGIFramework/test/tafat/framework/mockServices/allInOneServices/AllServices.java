package tafat.framework.mockServices.allInOneServices;

import tafat.framework.services.BreakpointService;
import tafat.framework.services.SensorService;
import tafat.sgi.model.conection.HttpResponse;
import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

public class AllServices implements SensorService, BreakpointService {

    public AllServices() {
        
    }

    @Override
    public Response create(Request breakpointId) {
        return null;
    }

    @Override
    public Response delete(Request request) {
        return null;
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
