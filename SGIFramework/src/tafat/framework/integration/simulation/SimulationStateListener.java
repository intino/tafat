package tafat.framework.integration.simulation;

import tafat.framework.services.ServicesManager;
import tafat.framework.services.NotificationService;

public class SimulationStateListener {

    public void change(SimulationState state){
        ServicesManager.get(NotificationService.class).broadcast(buildMessage(state.message()));
    }

    private String buildMessage(String state) {
        return "{\"type\":\"SimulationState\", \"info\":{\"state\":\"" + state + "\"}}";
    }
}
