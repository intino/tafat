package tafat.sgi.http.connection.model.proccesor;

import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.handler.Handler;

public interface RequestDictionary {
    public Handler find(Request request);
}
