package tafat.framework.services;

import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

public interface BreakpointService extends FrameworkService{
    public Response create(Request breakpointId);

    public Response delete(Request request);

}
