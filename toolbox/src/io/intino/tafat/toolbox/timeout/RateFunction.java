package io.intino.tafat.toolbox.timeout;

public class RateFunction implements TimeoutFunction {

    private final long ratio;

    public RateFunction(int times, long period) {
        this.ratio = period / times;
    }

    @Override
    public long calculate() {
        return (long) (Math.random() * ratio * 2);
    }
}
