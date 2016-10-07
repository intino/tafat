package io.intino.tafat.toolbox.timeout;

import io.intino.tafat.toolbox.Checker;

public class Timeout implements Checker {

    private final TimeoutFunction function;
    private long timeout;

    public Timeout(TimeoutFunction function) {
        this.function = function;
        activate();
    }

    public void step(long time){
        timeout -= time;
    }

    public void activate() {
        this.timeout = function.calculate();
    }

    @Override
    public boolean check() {
        return timeout <= 0;
    }
}
