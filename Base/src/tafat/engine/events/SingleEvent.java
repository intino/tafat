package tafat.engine.events;

import tafat.engine.Event;
import tafat.functions.Action;

public class SingleEvent implements Event {

    private long timeout;
    private final Action action;

    public SingleEvent(long timeout, Action action) {
        this.timeout = timeout;
        this.action = action;
    }

    @Override
    public void step(long time) {
        timeout -= time;
        if(finished()) action.execute();
    }

    @Override
    public boolean finished() {
        return timeout <= 0;
    }

}
