package model.handler;

import model.conection.Request;
import model.conection.Response;

import java.io.IOException;

public interface Handler {
    Response handle(Request request) throws IOException;
}
