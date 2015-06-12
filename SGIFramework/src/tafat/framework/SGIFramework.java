package tafat.framework;

import tafat.framework.finder.ReflectionServiceFinder;
import tafat.framework.handler.HandlerDictionary;
import tafat.framework.integration.SimulationAgent;
import tafat.framework.integration.SimulationAgentWrapper;
import tafat.framework.integration.simulation.Breakpoint;
import tafat.framework.integration.simulation.SimulationStateListener;
import tafat.framework.services.NotificationService;
import tafat.framework.services.ServicesManager;
import tafat.framework.state.WatcherManager;
import tafat.sgi.controller.HttpService;
import tafat.sgi.discovery.ClientProtocol;
import tafat.sgi.discovery.connection.DatagramConnection;
import tafat.sgi.discovery.connection.NetInformation;
import tafat.sgi.discovery.handler.DiscoveryProtocolHandler;
import tafat.sgi.model.proccesor.RequestProcessor;

import java.net.URI;
import java.util.Date;

import static tafat.framework.state.ServerState.state;
import static tafat.sgi.exception.ExceptionHandler.runSafe;

public abstract class SGIFramework implements SimulationAgent{
    private HttpService httpService;
    private WatcherManager watcherManager;
    private Date lastDate;

    public SGIFramework(String label, Date initialDate, Date finalDate) {
        runSafe(() -> start(label, new URI("")));
        state().setDates(initialDate, finalDate);
        lastDate = initialDate;
        watcherManager = new WatcherManager(this::valueGet);
    }

    private void start(String simulationName, URI servicesPath) throws Exception {
        NetInformation result = new DiscoveryProtocolHandler(new ClientProtocol(simulationName), new DatagramConnection(35001)).start();
        httpService = new HttpService(new RequestProcessor(new HandlerDictionary(new SimulationAgentWrapper(this))), result.getPort());
        setupFramework(simulationName, servicesPath, result);
        httpService.start();
    }

    private void setupFramework(String simulationName, URI servicesPath, NetInformation result) {
        httpService.enableRequestQueue();
        state().setSimulationName(simulationName);
        state().setRemoteAddress(result.getRemoteIP());
        state().setSimulationStateListener(new SimulationStateListener());
        System.out.println("port assigned: " + result.getPort());
        ServicesManager.setUp(new ReflectionServiceFinder(), servicesPath.getPath());
    }

    public void refresh(Date date) {
        httpService.executeStoreRequests();
        if(isPaused(date)) {
            treatBreakpoints(date);
            watcherManager.sendWatcherValues(date);
            sendPercentageLoaded(date);
        }
    }

    private void treatBreakpoints(Date date) {
        if (state().breakpoints().haveBreakpointNotPassed(date)) {
            ServicesManager.get(NotificationService.class).broadcast("{\"type\":\"Breakpoint\"}");
            markBreakpointPassed(date);
            pause();
        }
    }

    private void markBreakpointPassed(Date date) {
        state().breakpoints().getBreakpointsNotPassed(date).stream()
                .forEach(Breakpoint::passed);
    }

    private boolean isPaused(Date date) {
        boolean isPaused = date.after(lastDate);
        lastDate = date;
        return isPaused;
    }

    private void sendPercentageLoaded(Date date) {
        Float percentage = calculatePercentage(state().getFinalDate(), state().getInitialDate(), date);
        ServicesManager.get(NotificationService.class).broadcast("{\"type\":\"progress\",\"info\":{\"percentage\":" + percentage + "}}");//TODO
    }

    private Float calculatePercentage(Date finalDate, Date initialDate, Date date) {
        if(date.after(initialDate)) {
            Float percentage = (float)(date.getTime() - initialDate.getTime())/(finalDate.getTime()-initialDate.getTime());
            return percentage * 100;
        }
        return 0F;
    }

    public void waitForRequest(boolean wait) {
        if(wait)
            httpService.enableRequestQueue();
        else
            httpService.disableRequestQueue();
    }

}
