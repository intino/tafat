package tafat.engine;

import org.opensourcephysics.numerics.ODE;

public abstract class SystemDynamic implements ODE {

    protected double[] state = null;
    protected double[] rate = null;
    protected double[] param = null;

    public void init(int numberOfSdElements, int numberOfParams) {
        state = new double[numberOfSdElements];
        rate = new double[numberOfSdElements];
        param = new double[numberOfParams];
    }

    @Override
    public double[] getState() {
        return state;
    }

    @Override
    public void getRate(double[] state, double[] rate) {
        calculateFlows(state, rate);
        calculateStocks(state, rate);
        for (int i = 0; i < rate.length; i++)
            this.rate[i] = rate[i];
    }

    public abstract void pull();

    protected abstract void calculateFlows(double[] state, double[] rate);

    protected abstract void calculateStocks(double[] state, double[] rate);

    public abstract void push();
}

