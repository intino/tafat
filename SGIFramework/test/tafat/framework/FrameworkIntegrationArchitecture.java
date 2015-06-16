package tafat.framework;

import org.junit.Before;
import org.junit.Test;
import tafat.framework.integration.SimulationAgent;
import tafat.framework.integration.SimulationAgentWrapper;
import tafat.framework.integration.simulation.SimulationState;
import tafat.framework.integration.simulation.SimulationStateListener;
import tafat.framework.integration.simulation.Watcher;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static tafat.framework.state.ServerState.state;
import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class FrameworkIntegrationArchitecture {

    public static final String ROOTS = "[{\"name\":\"happy-building\",\"objectId\":\"happy-building\"},{\"name\":\"bored-building\",\"objectId\":\"bored-building\"},{\"name\":\"unhappy-building\",\"objectId\":\"unhappy-building\"}]";
    private static final String CHILDREN = "[{\"name\":\"room1\",\"objectId\":\"room1\"},{\"name\":\"room2\",\"objectId\":\"room2\"},{\"name\":\"room3\",\"objectId\":\"room3\"}]";
    private static final String SENSORS = "[{\"name\":\"sensor1\",\"objectId\":\"sensor1\"},{\"name\":\"sensor2\",\"objectId\":\"sensor2\"},{\"name\":\"sensor3\",\"objectId\":\"sensor3\"}]";
    private static final String IMPOSIBLE_STOP = "{\"error\":\"impossible reset simulation\"}";
    private static final String IMPOSIBLE_PAUSE = "{\"error\":\"impossible pause simulation\"}";
    private static final String IMPOSIBLE_PLAY = "{\"error\":\"impossible play simulation\"}";

    private SimulationAgent agent;
    private SimulationAgentWrapper wrapper;

    @Before
    public void setUp() throws Exception {
        agent = new MySimulationAgent();
        wrapper = new SimulationAgentWrapper(agent);
    }



    @Test
    public void shouldReturnTheRootsOnSimulationAgent() {
        Request request= getSafe(()->new HttpRequest("GET", "/RootObjects", ""));
        Response response = wrapper.execute(request);
        assertEquals(200, response.getStatusCode());
        assertEquals(ROOTS, response.getBody());
    }

    @Test
    public void shouldReturnTheChildrenOf() {
        Request request= getSafe(()->new HttpRequest("GET", "/ObjectComponents", "{\"objectId\":\"happy-building\"}"));
        Response response = wrapper.execute(request);
        assertEquals(200, response.getStatusCode());
        assertEquals(CHILDREN, response.getBody());
    }

    @Test
    public void shouldReturnTheAttributes() {
        Request request= getSafe(()->new HttpRequest("GET", "/MeasurableAttributes", "{\"objectId\":\"happy-building\"}"));
        Response response = wrapper.execute(request);
        assertEquals(200, response.getStatusCode());
        assertEquals(SENSORS, response.getBody());
    }

    @Test
    public void shouldResponseWithImpossibleStop() {
        Request request= getSafe(()->new HttpRequest("GET", "/Stop", "{\"objectId\":\"happy-building\"}"));
        Response response = wrapper.execute(request);
        assertEquals(500, response.getStatusCode());
        assertEquals(IMPOSIBLE_STOP, response.getBody());
    }

    @Test
    public void shouldResponseWithImpossiblePlay() {
        Request request= getSafe(()->new HttpRequest("GET", "/Play", "{\"objectId\":\"happy-building\"}"));
        Response response = wrapper.execute(request);
        assertEquals(500, response.getStatusCode());
        assertEquals(IMPOSIBLE_PLAY, response.getBody());
    }

    @Test
    public void shouldResponseWithImpossiblePause() {
        Request request= getSafe(()->new HttpRequest("GET", "/Pause", "{\"objectId\":\"happy-building\"}"));
        Response response = wrapper.execute(request);
        assertEquals(500, response.getStatusCode());
        assertEquals(IMPOSIBLE_PAUSE, response.getBody());
    }

    @Test
    public void shouldBeInstalledASensorToAUserRamclen() {
        Watcher watcher = new Watcher("objectId", "measurableAttributeName");
        state().watcherContainer().addWatcher("Ramclen", watcher);
        HashMap<String, List<Watcher>> watchers = state().watcherContainer().getWatchers();
        assertNotNull(watchers.get("Ramclen"));
        assertSame(watcher, watchers.get("Ramclen").get(0));
    }

    @Test
    public void shouldReturnPausedSimulationState() {
        state().setSimulationState(SimulationState.PAUSE);
        assertSame(state().getSimulationState(), SimulationState.PAUSE);
    }

    @Test
    public void shouldFiredTheListenerWhenChangeTheSimulationState() {
        SimulationStateListener listener = mock(SimulationStateListener.class);
        state().setSimulationStateListener(listener);
        state().setSimulationState(SimulationState.PAUSE);
        verify(listener).change(SimulationState.PAUSE);
    }

    private class MySimulationAgent implements SimulationAgent {

        @Override
        public List<String> roots() {
            LinkedList<String> list = new LinkedList<>();
            list.add("happy-building");
            list.add("bored-building");
            list.add("unhappy-building");
            return list;
        }

        @Override
        public List<String> childrenOf(String objectId) {
            LinkedList<String> list = new LinkedList<>();
            list.add("room1");
            list.add("room2");
            list.add("room3");
            return list;
        }

        @Override
        public List<String> attributesOf(String objectId) {
            LinkedList<String> list = new LinkedList<>();
            list.add("sensor1");
            list.add("sensor2");
            list.add("sensor3");
            return list;
        }

        @Override
        public String valueGet(String objectId) {
            return null;
        }

        @Override
        public boolean valueSet(String objectId, String value) {
            return false;
        }

        @Override
        public boolean play() {
            return false;
        }

        @Override
        public boolean pause() {
            return false;
        }

        @Override
        public boolean reset() {
            return false;
        }
    }

}
