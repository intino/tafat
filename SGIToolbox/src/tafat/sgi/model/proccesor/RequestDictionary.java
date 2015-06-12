package tafat.sgi.model.proccesor;

import tafat.sgi.model.conection.Request;
import tafat.sgi.model.handler.Handler;

public interface RequestDictionary {
    public Handler find(Request request);
}
