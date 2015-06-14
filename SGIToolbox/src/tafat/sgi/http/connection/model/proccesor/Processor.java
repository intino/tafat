package tafat.sgi.http.connection.model.proccesor;


import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

import java.io.IOException;

public interface Processor {
    Response process(Request request) throws IOException;
}
