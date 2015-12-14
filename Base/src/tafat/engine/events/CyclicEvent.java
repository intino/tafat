package tafat.engine.events;

import tafat.engine.Event;
import tafat.functions.Action;
import tafat.functions.Timeout;

public class CyclicEvent implements Event {

    private long timeout;
    private final Timeout timeoutCalculator;
    private final Action action;

    public CyclicEvent(Timeout timeoutCalculator, Action action) {
        this.timeoutCalculator = timeoutCalculator;
        this.action = action;
        this.timeout = timeoutCalculator.calculate();
    }

    @Override
    public void step(long time) {
        timeout -= time;
        if(timeout > 0) return;
        timeout = timeoutCalculator.calculate();
        action.execute();
    }

    @Override
    public boolean finished() {
        return false;
    }
}
