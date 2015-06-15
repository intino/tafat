package tafat.framework.services.defaults;

import tafat.framework.services.NotificationService;
import tafat.framework.state.ServerState;
import tafat.sgi.http.connection.controller.HttpPetitionClient;
import tafat.sgi.http.connection.controller.PetitionClient;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.conection.Response;

import static tafat.framework.state.ServerState.state;
import static tafat.sgi.exception.ExceptionHandler.getSafe;
import static tafat.sgi.exception.ExceptionHandler.runSafe;

public class PushNotification implements NotificationService {

    private final ServerState serverState;
    private PetitionClient petitionClient;

    public PushNotification() {
        petitionClient = new HttpPetitionClient();
        serverState = state();
    }

    public PushNotification(PetitionClient petitionClient) {
        this.petitionClient = petitionClient;
        serverState = state();
    }

    @Override
    public void push(String username, String message) {
        HttpRequest request = getSafe(() -> new HttpRequest("POST", serverState.getRemoteAddress() + ":8080/api/" + serverState.simulationId() + "/notification", message));
        System.out.println("notification request-> " +request.getBody() + " " + request.getPath());
        request.setParameter("username", username);
        runSafe(() -> petitionClient.sendRequest(request, response -> treatResponse(response, username)));
    }

    @Override
    public void broadcast(String message) {
        HttpRequest request = getSafe(() -> new HttpRequest("POST", serverState.getRemoteAddress() + ":8080/api/" + serverState.simulationId() + "/broadcast-notification", message));
        System.out.println("broadcast request-> " +request.getBody() + " " + request.getPath());
        runSafe(() -> petitionClient.sendRequest(request, response -> runSafe(()->{})));
    }

    public void treatResponse(Response response, String username) {
        System.out.println("broadcast/Not response-> " +response.getBody() + " " + response.getStatusCode());
        if(response.getStatusCode()==500)
            state().removeUser(username);
    }
}
