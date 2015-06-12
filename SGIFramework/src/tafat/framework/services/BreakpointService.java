package tafat.framework.services;

import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

public interface BreakpointService extends FrameworkService{
    public Response create(Request breakpointId);

    public Response delete(Request request);

}
