package tafat.framework.integration.actions;

import tafat.framework.integration.SimulationAgent;
import tafat.framework.integration.simulation.SimulationState;
import model.conection.HttpResponse;
import model.conection.Request;
import model.conection.Response;

import static tafat.framework.state.ServerState.state;

public class Reset implements AgentAction {
    private SimulationAgent agent;

    public Reset(SimulationAgent agent) {
        this.agent = agent;
    }

    @Override
    public Response execute(Request request) {
        boolean result = agent.reset();
        if(result) state().setSimulationState(SimulationState.RESET);
        return createResponse(result);
    }

    private Response createResponse(boolean result) {
        if(result)
            return new HttpResponse(200, "{\"message\":\"simulation stopped\"}");
        return new HttpResponse(500, "{\"error\":\"impossible reset simulation\"}");
    }
}
