package tafat.framework.integration.actions;

import tafat.framework.integration.SimulationAgent;
import tafat.framework.integration.simulation.SimulationState;
import model.conection.HttpResponse;
import model.conection.Request;
import model.conection.Response;
import tafat.framework.state.ServerState;

public class Pause implements AgentAction {
    private final SimulationAgent agent;

    public Pause(SimulationAgent agent) {
        this.agent = agent;
    }

    @Override
    public Response execute(Request request) {
        boolean result = agent.pause();
        if (result) ServerState.state().setSimulationState(SimulationState.PAUSE);
        return createResponse(result);
    }

    private Response createResponse(boolean result) {
        if(result)
            return new HttpResponse(200, "{\"message\":\"simulation paused\"}");
        return new HttpResponse(500, "{\"error\":\"impossible pause simulation\"}");
    }
}
