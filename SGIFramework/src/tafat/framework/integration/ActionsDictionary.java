package tafat.framework.integration;

import tafat.framework.integration.actions.*;

import java.util.HashMap;

public class ActionsDictionary extends HashMap<String, AgentAction> {
    private final SimulationAgent agent;

    private void init(){
        put("/Play", new Play(agent));
        put("/Stop", new Reset(agent) );
        put("/Pause", new Pause(agent));

        put("/RootObjects", new Roots(agent));
        put("/ObjectComponents", new ChildrenOf(agent));
        put("/MeasurableAttributes", new AttributesOf(agent));

        put("/Change", new ChangeValue(agent));
    }

    public ActionsDictionary(SimulationAgent agent) {
        this.agent = agent;
        init();
    }
}
