package tafat.framework.integration.actions;

import com.google.gson.Gson;
import tafat.framework.integration.SimulationAgent;
import tafat.framework.integration.simulation.SimulationObject;
import tafat.sgi.model.conection.HttpResponse;
import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

import java.util.Collection;
import java.util.stream.Collectors;

public class AttributesOf implements AgentAction{

    private final SimulationAgent agent;

    public AttributesOf(SimulationAgent agent) {
        this.agent = agent;
    }

    @Override
    public Response execute(Request request) {
        return createResponse(agent.attributesOf(request.getParameter("objectId")));
    }

    private Response createResponse(Collection<String> roots) {
        String message = new Gson().toJson(roots.stream()
                .map(objectName -> new SimulationObject(objectName, objectName))
                .collect(Collectors.toList()));

        return new HttpResponse(200, message);
    }
}
