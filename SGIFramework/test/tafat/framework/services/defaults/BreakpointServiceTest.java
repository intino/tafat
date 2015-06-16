package tafat.framework.services.defaults;

import org.junit.Test;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.conection.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class BreakpointServiceTest {
    @Test
    public void shouldRespondWithABreakpointCreation() {
        Response response = new BreakpointService().create(getSafe(() -> new HttpRequest("POST", "/Breakpoint", "{\"time\":\"01/30/2015 11:00:00\"}")));
        assertEquals(200, response.getStatusCode());
        assertThat(response.getBody(), containsString("{\"message\":\"breakpoint created\",\"breakpointId\":\""));
    }
    //TODO
    @Test
    public void shouldRespondWithABreakpointDeletion() {
//        new BreakpointService().create(getSafe(() -> new HttpRequest("POST", "/Breakpoint", "{\"time\":\"01/30/2015 11:00:00:952\"}")));
//        assertNotNull(ServerState.state().breakpoints().get("0"));
//        Response response = new BreakpointService().delete(getSafe(() -> new HttpRequest("DELETE", "/Breakpoint", "{\"breakpointId\":\"0\"}")));
//        assertEquals(200, response.getStatusCode());
//        assertThat(response.getBody(), containsString("{\"message\":\"Breakpoint Deleted\""));
//        assertNull(ServerState.state().breakpoints().get("0"));
    }

    private Date createDate(String time) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:S");
        return getSafe(() -> format.parse(time));
    }


}
