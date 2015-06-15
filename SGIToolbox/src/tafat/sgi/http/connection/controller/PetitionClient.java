package tafat.sgi.http.connection.controller;

import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;

import java.io.IOException;

public interface PetitionClient {
    Response sendRequest(Request request) throws IOException;
    void sendRequest(Request request, Action done) throws IOException;

    interface Action{
        void execute(Response response);
    }
}
