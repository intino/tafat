package tafat.site.route.handler;

import tafat.sgi.http.connection.model.conection.HttpResponse;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.sgi.http.connection.model.handler.Handler;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tafat.site.ServerState;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SimulatorSubscribedHandler implements Handler {

    @Override
    public Response handle(Request request) throws IOException {
        String jsonSimulations = JSONValue.toJSONString(transformToJSONObjects(ServerState.instance().getSimulations()));
        return new HttpResponse(200, jsonSimulations);
    }

    private List<Object> transformToJSONObjects(List<String> simulationNames) {
        List<Object> simulationMap = new LinkedList<>();
        for (String simulationName : simulationNames)
            simulationMap.add(createSimulationObject(simulationName));
        return simulationMap;
    }

    private JSONObject createSimulationObject(String simulationName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", simulationName);
        return jsonObject;
    }


}
