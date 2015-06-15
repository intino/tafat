package tafat.framework.handler;

import tafat.framework.integration.SimulationAgentWrapper;
import tafat.sgi.http.connection.model.conection.Request;
import tafat.sgi.http.connection.model.handler.Handler;
import tafat.sgi.http.connection.model.proccesor.RequestDictionary;
import tafat.framework.services.ServicesManager;
import tafat.framework.services.BreakpointService;
import tafat.framework.services.SensorService;
import tafat.framework.services.SubscriptionService;

import java.util.HashMap;

public class HandlerDictionary extends HashMap<String, Handler>implements RequestDictionary {

    private final SimulationAgentWrapper agent;

    public HandlerDictionary(SimulationAgentWrapper simulationAgentWrapper) {
        super();
        this.agent = simulationAgentWrapper;
        init();
    }

    private void init(){
        put("/Breakpoint:DELETE", request -> breakpointService().delete(request));
        put("/Breakpoint:POST", request -> breakpointService().create(request));

        put("/Sensor:POST",   request -> sensorService().installSensor(request));
        put("/Sensor:DELETE",   request -> sensorService().remove(request));
        put("/Subscribe:POST", request -> subscriptionService().subscribe(request));
    }

    @Override
    public Handler find(Request request) {
        if(get(request.getPath() + ":" + request.getMethod())!= null)
            return get(request.getPath() + ":" + request.getMethod());
        return agent::execute;
    }

    private SubscriptionService subscriptionService() {
        return ServicesManager.get(SubscriptionService.class);
    }

    private SensorService sensorService() {
        return ServicesManager.get(SensorService.class);
    }

    private BreakpointService breakpointService() {
        return ServicesManager.get(BreakpointService.class);
    }



}


