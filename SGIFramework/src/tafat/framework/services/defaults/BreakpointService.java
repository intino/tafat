package tafat.framework.services.defaults;

import tafat.framework.integration.simulation.Breakpoint;
import tafat.sgi.model.conection.HttpResponse;
import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static tafat.framework.state.ServerState.state;

public class BreakpointService implements tafat.framework.services.BreakpointService {

    public BreakpointService() {
    }

    @Override
    public Response create(Request request) {
        return createResponse(createBreakpoint(getDate(request)));
    }

    private Response createResponse(String breakpointId) {
        return new HttpResponse(200, "{\"message\":\"breakpoint created\",\"breakpointId\":\""+breakpointId+"\"}");
    }

    private Date getDate(Request request) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
        DateFormat formatWithMilliseconds = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return getSafe(() -> format.parse(request.getParameter("time")),
                ()-> getSafe(() -> formatWithMilliseconds.parse(request.getParameter("time"))));
    }

    @Override
    public Response delete(Request request) {
        return deleteResponse(delete(Integer.parseInt(request.getParameter("breakpointId"))));
    }


    private Response deleteResponse(boolean actionResult) {
        String message = "{\"message\":\""+ (actionResult ? "Breakpoint Deleted" : "Can't remove the breakpoint") +"\"}";
        return new HttpResponse(200, message);
    }

    public String createBreakpoint(Date date) {
        Breakpoint breakpoint = Breakpoint.create(date);
        state().breakpoints().add(breakpoint);
        return String.valueOf(breakpoint.id());
    }

    public boolean delete(int breakpointId) {
        state().breakpoints().removeById(breakpointId);
        return true;
    }
}
