package tafat.framework.integration.simulation;

public class SimulationState {
    public static final SimulationState PAUSE = new SimulationState("paused");
    public static final SimulationState PLAY = new SimulationState("play");
    public static final SimulationState RESET = new SimulationState("stop");
    private String message;

    private SimulationState(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}