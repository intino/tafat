package tafat.framework.integration;

import tafat.framework.integration.actions.AgentAction;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

public class SimulationAgentWrapper {
    SimulationRequestParser parser;

    public SimulationAgentWrapper(SimulationAgent simulationAgent) {
        parser = new SimulationRequestParser(simulationAgent);
    }

    public Response execute(Request request) {
        return parser.parse(request).execute(request);
    }

    private class SimulationRequestParser {
        SimulationAgent agent;
        public SimulationRequestParser(SimulationAgent simulationAgent) {
            this.agent = simulationAgent;
        }
//TODO
        public AgentAction parse(Request request) {
            return new ActionsDictionary(agent).get(request.getPath());

        }
    }

}