package tafat.framework.state;


import tafat.framework.integration.simulation.SimulationState;
import tafat.framework.integration.simulation.SimulationStateListener;
import tafat.framework.integration.simulation.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class  ServerState {
    private static ServerState instance;

    private final BreakpointContainer breakpointContainer;
    private WatcherContainer watcherContainer;
    private List<User> subscribedUsers;

    private String simulationName;
    private String routeAddress;
    private Date finalDate;
    private Date initialDate;
    private SimulationState simulationState;
    private SimulationStateListener simulationStateListener;

    private ServerState() {
        subscribedUsers = new LinkedList<>();
        breakpointContainer = new BreakpointContainer();
        watcherContainer = new WatcherContainer();
    }

    public static ServerState state(){
        if(instance == null)
            instance = new ServerState();
        return instance;
    }

    public String simulationId() {
        return simulationName;
    }

    public List<User> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSimulationName(String simulationName) {
        this.simulationName = simulationName;
    }

    public String getRemoteAddress() {
        return routeAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.routeAddress = remoteAddress;
    }

    public void setDates(Date initialDate, Date finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public BreakpointContainer breakpoints() {
        return breakpointContainer;
    }

    public WatcherContainer watcherContainer() {
        return watcherContainer;
    }

    public void setSimulationState(SimulationState simulationState) {
        this.simulationState = simulationState;
        simulationStateListener.change(simulationState);
    }

    public SimulationState getSimulationState() {
        return simulationState;
    }


    public void setSimulationStateListener(SimulationStateListener simulationStateListener) {
        this.simulationStateListener = simulationStateListener;
    }

    public void removeUser(String username) {
        watcherContainer().removeUserWatchers(username);
    }
}
