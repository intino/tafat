package tafat.framework.integration.actions;

import com.google.gson.Gson;
import tafat.framework.integration.SimulationAgent;
import tafat.framework.integration.simulation.SimulationObject;
import model.conection.HttpResponse;
import model.conection.Request;
import model.conection.Response;

import java.util.Collection;
import java.util.stream.Collectors;

public class ChildrenOf implements AgentAction{

    private final SimulationAgent agent;

    public ChildrenOf(SimulationAgent agent) {
        this.agent = agent;
    }

    @Override
    public Response execute(Request request) {
        return createResponse(agent.childrenOf(request.getParameter("objectId")));
    }

    private Response createResponse(Collection<String> roots) {
        String message = new Gson().toJson(roots.stream()
                .map(objectName -> new SimulationObject(objectName, objectName))
                .collect(Collectors.toList()));

        return new HttpResponse(200, message);
    }
}
