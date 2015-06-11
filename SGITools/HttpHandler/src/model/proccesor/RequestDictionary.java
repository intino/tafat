package model.proccesor;

import model.conection.Request;
import model.handler.Handler;

public interface RequestDictionary {
    public Handler find(Request request);
}
