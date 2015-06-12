package tafat.sgi.model.handler;

import tafat.sgi.model.conection.Request;
import tafat.sgi.model.conection.Response;

import java.io.IOException;

public interface Handler {
    Response handle(Request request) throws IOException;
}
