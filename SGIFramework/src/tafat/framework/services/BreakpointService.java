package tafat.framework.services;

import model.conection.Request;
import model.conection.Response;

public interface BreakpointService extends FrameworkService{
    public Response create(Request breakpointId);

    public Response delete(Request request);

}
