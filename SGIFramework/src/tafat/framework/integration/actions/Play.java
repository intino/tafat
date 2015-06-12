package tafat.framework.integration.actions;

import tafat.framework.integration.SimulationAgent;
import tafat.framework.integration.simulation.SimulationState;
import tafat.sgi.model.conection.HttpResponse;
import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

import static tafat.framework.state.ServerState.state;

public class Play implements AgentAction {
    private SimulationAgent agent;

    public Play(SimulationAgent agent) {
        this.agent = agent;
    }

    @Override
    public Response execute(Request request) {
        boolean result = agent.play();
        if(result) state().setSimulationState(SimulationState.PLAY);
        return createResponse(result);
    }

    private Response createResponse(boolean result) {
        if(result)
            return new HttpResponse(200, "{\"message\":\"simulation running\"}");
        return new HttpResponse(500, "{\"error\":\"impossible play simulation\"}");
    }
}
