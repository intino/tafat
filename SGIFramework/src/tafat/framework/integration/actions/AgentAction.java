package tafat.framework.integration.actions;

import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

public interface AgentAction {
    Response execute(Request request);
}
