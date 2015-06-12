package tafat.framework.services.defaults;

import tafat.sgi.model.conection.HttpRequest;
import tafat.sgi.model.conection.Response;
import org.junit.Test;
import tafat.framework.state.ServerState;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BreakpointServiceTest {
    @Test
    public void shouldRespondWithABreakpointCreation() {
        Response response = new BreakpointService().create(getSafe(() -> new HttpRequest("POST", "/Breakpoint", "{\"time\":\"01/30/2015 11:00:00\"}")));
        assertEquals(200, response.getStatusCode());
        assertThat(response.getBody(), containsString("{\"message\":\"breakpoint created\",\"breakpointId\":\""));
    }

    @Test
    public void shouldRespondWithABreakpointDeletion() {
        Response response = new BreakpointService().create(getSafe(() -> new HttpRequest("POST", "/Breakpoint", "{\"time\":\"01/30/2015 11:00:00:952\"}")));
        assertNotNull(ServerState.state().breakpoints().get("0"));
        response = new BreakpointService().delete(getSafe(() -> new HttpRequest("DELETE", "/Breakpoint", "{\"breakpointId\":\"0\"}")));
        assertEquals(200, response.getStatusCode());
        assertThat(response.getBody(), containsString("{\"message\":\"Breakpoint Deleted\""));
        assertNull(ServerState.state().breakpoints().get("0"));
    }

    private Date createDate(String time) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:S");
        return getSafe(() -> format.parse(time));
    }


}
