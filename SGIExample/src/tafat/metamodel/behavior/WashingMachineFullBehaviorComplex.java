package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Time;
import tafat.engine.interpolatedfunction.Coordinate;
import tafat.engine.interpolatedfunction.InterpolatedFunction;
import tafat.engine.interpolatedfunction.NoneInterpolation;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.WashingMachineFull;

public class WashingMachineFullBehaviorComplex implements BehaviorSimple {
	
	private InterpolatedFunction labellingFactor;
	private Coordinate[] labellingCoordenates = {new Coordinate(1, 0.133),
												 new Coordinate(2, 0.15),
												 new Coordinate(3, 0.17),
												 new Coordinate(4, 0.19),
												 new Coordinate(5, 0.21),
												 new Coordinate(6, 0.25),
												 new Coordinate(7, 0.29),
												 new Coordinate(8, 0.33),
												 new Coordinate(9, 0.37),
												 new Coordinate(10, 0.39)};
	private InterpolatedFunction heatFactor;
	private Coordinate[] heatCoordenates = {new Coordinate(1, 1.9),
										 	new Coordinate(2, 1.9),
										 	new Coordinate(3, 1.9)};
	private InterpolatedFunction washFactor;
	private Coordinate[] washCoordenates = {new Coordinate(1, 0.07),
									 		new Coordinate(2, 0.075),
									 		new Coordinate(3, 0.08)};
	private InterpolatedFunction dryingoFactor;
	private Coordinate[] dryingoCoordenates = {new Coordinate(1, 0.14),
											   new Coordinate(2, 0.145),
											   new Coordinate(3, 0.15)};

	private static ArrayList<WashingMachineFullBehaviorComplex> instanceList = new ArrayList<WashingMachineFullBehaviorComplex>();
	private double power;
	private WashingMachineFull washingMachine;
	private StateChart statechart;
	
	double timeoutHeatWater;
	double timeoutWash;
	double timeoutDry;
	
	private Transition transition12;
	private Transition transition23;
	private Transition transition30;

	public static BehaviorSimple newInstance() {
		WashingMachineFullBehaviorComplex behavior = new WashingMachineFullBehaviorComplex();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof WashingMachineFull){
			washingMachine = (WashingMachineFull) target;
			createStatechart();
			
			NoneInterpolation labelling = new NoneInterpolation(labellingCoordenates, 0.01);
			labellingFactor = new InterpolatedFunction(labellingCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			labellingFactor.setInterpolationMethod(labelling);
			
			NoneInterpolation heat  = new NoneInterpolation(heatCoordenates, 0.01);
			heatFactor = new InterpolatedFunction(heatCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			heatFactor.setInterpolationMethod(heat);
			
			NoneInterpolation wash  = new NoneInterpolation(washCoordenates, 0.01);
			washFactor = new InterpolatedFunction(washCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			washFactor.setInterpolationMethod(wash);
			
			NoneInterpolation dryingo  = new NoneInterpolation(dryingoCoordenates, 0.01);
			dryingoFactor = new InterpolatedFunction(dryingoCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			dryingoFactor.setInterpolationMethod(dryingo);
		}
		else
			Console.error(target.getFullPath() + " is not a WashingMachine");
	}
	@Override
	public void configure(){	
	}
	@Override
	public void tickOn(Long time) {
		statechart.update();
		washingMachine.activePower = power;
	}

	@Override
	public void tickOff(Long time) {
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("Off",0){
	        public void entryAction(){
		          power = 0.0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);
		
		State state2 = new State("Heat Water N",1){
	        public void entryAction(){
	        	timeout();
	        	transition12.time = (int) (timeoutHeatWater * Time.getInstance().minute);
	        	transition23.time = (int) (timeoutWash * Time.getInstance().minute);
	        	transition30.time = (int) (timeoutDry * Time.getInstance().minute);
	        	power = heatFactor.getY(washingMachine.mode.ordinal()) * 1000;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("Wash Rinse N",2){
	        public void entryAction(){
	        	power = washFactor.getY(washingMachine.mode.ordinal()) * 1000;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		State state4 = new State("Dryingo N",3){
	        public void entryAction(){
	        	power = dryingoFactor.getY(washingMachine.mode.ordinal()) * 1000;
		    }
	        public void exitAction(){
	        	washingMachine.mode = WashingMachineFull.Mode.OFF;
	        }
		};
		statechart.addState(state4);
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return ((washingMachine.mode == WashingMachineFull.Mode.ON40) || 
						(washingMachine.mode == WashingMachineFull.Mode.ON60) ||
						(washingMachine.mode == WashingMachineFull.Mode.ON95));
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition01);
		
		transition12 = new Transition (1,2, (int) (timeoutHeatWater * Time.getInstance().minute)){
			@Override
			public void action() {
			}
		};
		statechart.addTransition(transition12);
		
		transition23 = new Transition (2,3, (int) timeoutWash * Time.getInstance().minute){
			@Override
			public void action() {}
		};
		statechart.addTransition(transition23);
	
		transition30 = new Transition (3,0, (int) timeoutDry * Time.getInstance().minute){
			@Override
			public void action() {}
		};
		statechart.addTransition(transition30);
		
		statechart.setInitialState(0);
	}
	
	private void timeout () {
		if (washingMachine.mode == WashingMachineFull.Mode.ON40)
		{
			double consumption =(washingMachine.load * labellingFactor.getY(washingMachine.labelling.ordinal()) * 40) / 60;
			double cycle = consumption /(heatFactor.getY(1) * 0.21 + washFactor.getY(1) * 0.58 + dryingoFactor.getY(1) * 0.21) * 60;
			if (cycle < 210)
			{
				//double a1 = 0.2;
				timeoutHeatWater = cycle * 0.21;
				timeoutWash = cycle * 0.58;
				timeoutDry = cycle * 0.21;
		    }
		    else 
		    {
		     	cycle = 210;
		     	//double t = cycle / 60;
		     	double a1 =(consumption - 0.29645) / 6.405;
		     	double a2 = 0.79 - a1;
				timeoutHeatWater=cycle*a1;
				timeoutWash=cycle*a2;
			    timeoutDry=cycle*0.21;
		     	
		    }
		}
		else if (washingMachine.mode == WashingMachineFull.Mode.ON60)
		{
			double consumption = washingMachine.load * labellingFactor.getY(washingMachine.labelling.ordinal());
			double cycle = consumption / (heatFactor.getY(2) * 0.22 + washFactor.getY(2) * 0.56 + dryingoFactor.getY(2) * 0.22) * 60;
			if (cycle < 210)
			{
				//double a1 = (2 / 9);
				timeoutHeatWater = cycle* 0.22;
				timeoutWash = cycle * 0.56;
				timeoutDry = cycle * 0.22;
			}
			else 
		    {
			    cycle = 210;
			    //double t= cycle / 60;
				double a1 = (consumption - 0.3164) / 6.3875;
				double a2 = 0.78 - a1;
				timeoutHeatWater=cycle*a1;
				timeoutWash=cycle*a2;
			    timeoutDry=cycle*0.22;
			
		    }
		}
		else if (washingMachine.mode == WashingMachineFull.Mode.ON95)
		{
			double consumption = washingMachine.load * labellingFactor.getY(washingMachine.labelling.ordinal()) * 95 / 60 * 1.2;
			double cycle = consumption / (heatFactor.getY(3) * 0.23 + washFactor.getY(3) * 0.62 + 0.15 * dryingoFactor.getY(3)) * 60;
			if (cycle < 210)
			{
					//double a1=0.27;
					timeoutHeatWater=cycle*0.23;
					timeoutWash=cycle*0.62;
					timeoutDry=cycle*0.15;
			}
		    else 
		    {
			    	cycle = 210;
			     	//double t = cycle / 60;
			     	double  a1 = (consumption - 0.31675) / 6.37;
		     		double  a2 = 0.85 - a1;
					timeoutHeatWater=cycle*a1;
					timeoutWash=cycle*a2;
				    timeoutDry=cycle*0.15;
			}
		}
	}
}
