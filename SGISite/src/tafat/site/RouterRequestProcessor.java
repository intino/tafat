package tafat.site;

import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.sgi.http.connection.model.handler.Handler;
import tafat.sgi.http.connection.model.proccesor.Processor;
import tafat.sgi.http.connection.model.proccesor.RequestDictionary;

import java.io.IOException;

import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class RouterRequestProcessor implements Processor {
    private RequestDictionary dictionary;

    public RouterRequestProcessor(RequestDictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public Response process(Request request) throws IOException {
        request = moveSimulationIdToBody(request);
        Handler handler = dictionary.find(request);
        return handler.handle(request);
    }

    public HttpRequest moveSimulationIdToBody(Request request) {
        return moveFromPathToBody(request, request.getPath().split("/")[1]);
    }

    private HttpRequest moveFromPathToBody(Request request, String valueOnPath) {
        String path = removeValueFromPath(request, valueOnPath);
        HttpRequest httpRequest = getSafe(() -> new HttpRequest(request.getMethod(), path, request.getBody()));
        httpRequest.setParameter("simulationId", valueOnPath);
        return httpRequest;
    }

    private String removeValueFromPath(Request request, String simulationId) {
        return request.getPath().replace("/" + simulationId, "");
    }
}
