package tafat.sgi.model.proccesor;


import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

import java.io.IOException;

public interface Processor {
    Response process(Request request) throws IOException;
}
