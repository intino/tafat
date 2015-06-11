package tafat.framework.integration.actions;

import model.conection.Request;
import model.conection.Response;

public interface AgentAction {
    Response execute(Request request);
}
