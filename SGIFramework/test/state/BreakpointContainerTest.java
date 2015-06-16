package state;

import org.junit.Test;
import tafat.framework.integration.simulation.Breakpoint;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tafat.framework.state.ServerState.state;

public class BreakpointContainerTest {

    @Test
    public void shouldDetectThatBreakpointHaveBeenNotFired() {
        Date lastDate = new Date(System.currentTimeMillis()-1000);
        Date date = new Date(System.currentTimeMillis());
        state().breakpoints().add(Breakpoint.create(date));
        assertTrue(state().breakpoints().haveBreakpointNotPassed(lastDate, date));
    }

    @Test
    public void shouldDetectThatBreakpointHaveBeenFired() {
        long timeInMillis = System.currentTimeMillis();
        Date breakpointDate = new Date(timeInMillis);
        Date lastDate = new Date(timeInMillis-1);
        Date actualDate = new Date(timeInMillis );
        state().breakpoints().add(Breakpoint.create(breakpointDate));
        assertTrue(state().breakpoints().haveBreakpointNotPassed(lastDate, actualDate));
    }

    @Test
    public void shouldDetectThatBreakpointHaveBeenNotFiredTimeBefore() {
        Date date = new Date(System.currentTimeMillis());
        Date lastDate = new Date(System.currentTimeMillis()-2000);
        Date actualDate = new Date(System.currentTimeMillis()-1000);
        state().breakpoints().add(Breakpoint.create(date));
        state().breakpoints().clear();
        assertFalse(state().breakpoints().haveBreakpointNotPassed(lastDate, actualDate));
    }

}
