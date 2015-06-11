package model.proccesor;

import model.conection.Request;
import model.conection.Response;
import model.handler.Handler;

import java.io.IOException;

public class RequestProcessor implements Processor {

    private RequestDictionary dictionary;

    public RequestProcessor(RequestDictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public Response process(Request request) throws IOException {
        Handler handler = dictionary.find(request);
        return handler.handle(request);
    }

}


