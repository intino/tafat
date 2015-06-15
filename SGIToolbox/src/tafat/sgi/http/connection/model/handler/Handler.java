package tafat.sgi.http.connection.model.handler;

import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

import java.io.IOException;

public interface Handler {
    Response handle(Request request) throws IOException;
}
