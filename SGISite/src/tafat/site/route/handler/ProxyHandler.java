package tafat.site.route.handler;

import tafat.sgi.http.connection.controller.PetitionClient;
import tafat.sgi.discovery.connection.NetInformation;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.conection.Response;
import tafat.sgi.http.connection.model.handler.Handler;
import tafat.site.ServerState;

import java.io.IOException;

import static tafat.sgi.exception.ExceptionHandler.getSafe;

public class ProxyHandler implements Handler {
    private final PetitionClient petitionClient;

    public ProxyHandler(PetitionClient petitionClient) {
        this.petitionClient = petitionClient;
    }

    @Override
    public Response handle(Request request) throws IOException {
        NetInformation simulationLocation = ServerState.instance().getSubscription(request.getParameter("simulationId"));
        return petitionClient.sendRequest(makeRedirectionRequest(request, simulationLocation));
    }

    private HttpRequest makeRedirectionRequest(Request request, NetInformation remoteDirection) {
        return getSafe(()->
                new HttpRequest(request.getMethod(),
                remoteDirection.getRemoteIP()+":"+remoteDirection.getPort()+request.getPath(),
                request.getBody()));
    }
}
