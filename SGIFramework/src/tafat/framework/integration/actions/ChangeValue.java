package tafat.framework.integration.actions;

import tafat.framework.integration.SimulationAgent;
import model.conection.HttpResponse;
import model.conection.Request;
import model.conection.Response;

public class ChangeValue implements AgentAction {
    private final SimulationAgent agent;

    public ChangeValue(SimulationAgent agent) {
        this.agent = agent;
    }

    @Override
    public Response execute(Request request) {
        return createResponse(agent.valueSet(request.getParameter("objectId")+"/"+ request.getParameter("measurableAttributeName"), request.getParameter("value")));
    }

    private Response createResponse(boolean result) {
        if(result)
            return new HttpResponse(200, "{\"message\":\"value change\"}");
        return new HttpResponse(500, "{\"error\":\"impossible to change value\"}");
    }
}
