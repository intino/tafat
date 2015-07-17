package tafat.engine.systemdynamic;

import java.util.HashMap;

import org.opensourcephysics.numerics.ODE;

public abstract class SystemDynamic implements ODE {

	public double[] state = null;
	public double[] rate = null;
	public double[] param = null;
	
	HashMap <String, Integer> sdElements = new HashMap <String, Integer> ();
	HashMap <String, Integer> params = new HashMap <String, Integer> ();
	
	int indexElements = 0;
	int indexParam = 0;

	public double getStockValue (int index){
		return state[index];
	}

	public double getFlowValue (int index){
		return rate[index];
	}
	
	public void setParam (int index, double value){
		param[index] = value;
	}
	
	public double getParam (int index){
		return param[index];
	}
	
	@Override
	public double[] getState() {
		return state;
	}

	public void init (){
		state = new double [sdElements.size()];
		rate = new double [sdElements.size()];
		param = new double [params.size()];
	}
	
	@Override
	public void getRate(double[] state, double[] rate) {
		calculateFlows(state, rate);
		calculateStocks(state, rate);
		for (int i = 0; i < rate.length; i++)
			this.rate[i] = rate[i];
	}
	
	public abstract void calculateFlows(double[] state, double[] rate);
	public abstract void calculateStocks(double[] state, double[] rate);
}
