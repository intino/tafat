package tafat.site.route.handler;

import Controller.PetitionClient;
import tafat.site.ServerState;
import connection.NetInformation;
import model.conection.HttpRequest;
import model.conection.Request;
import model.conection.Response;
import model.handler.Handler;

import java.io.IOException;

import static exception.ExceptionHandler.getSafe;

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
