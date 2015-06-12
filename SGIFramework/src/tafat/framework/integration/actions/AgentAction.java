package tafat.framework.integration.actions;

import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

public interface AgentAction {
    Response execute(Request request);
}
