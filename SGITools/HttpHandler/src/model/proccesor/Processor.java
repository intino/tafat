package model.proccesor;


import model.conection.Request;
import model.conection.Response;

import java.io.IOException;

public interface Processor {
    Response process(Request request) throws IOException;
}
