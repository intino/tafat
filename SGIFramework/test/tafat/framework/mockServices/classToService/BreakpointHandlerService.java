package tafat.framework.mockServices.classToService;

import tafat.framework.services.BreakpointService;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

public class BreakpointHandlerService implements BreakpointService {
    public BreakpointHandlerService() {
    }

    @Override
    public Response create(Request breakpointId) {
        return null;
    }

    @Override
    public Response delete(Request request) {
        return null;
    }
}
