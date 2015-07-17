package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Time;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.OvenFull;

public class OvenFullBehaviorPulse implements BehaviorSimple {

	private static ArrayList<OvenFullBehaviorPulse> instanceList = new ArrayList<OvenFullBehaviorPulse>();
	private double power;
	private OvenFull oven;
	private StateChart statechart;
	
	int periodTime = 3;
	double percentageOn = 0.66;
	double powerPulseOff = 10;
	
	public static BehaviorSimple newInstance() {
		OvenFullBehaviorPulse behavior = new OvenFullBehaviorPulse();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
		if(name.equals("periodTime"))
			periodTime = Integer.parseInt(value);
		else if(name.equals("percentage"))
			percentageOn = Integer.parseInt(value);
		else if(name.equals("powerPulseOff"))
			powerPulseOff = Integer.parseInt(value);
	}	

	public void init(ModelObject target) {
		if (target instanceof OvenFull){
			oven = (OvenFull) target;
			createStatechart();
		}
		else
			Console.error(target.getFullPath() + " is not a Oven");
	}
	@Override
	public void configure(){
	}
	@Override
	public void tickOn(Long time) {
		statechart.update();
		oven.activePower = power;
	}

	@Override
	public void tickOff(Long time) {
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("OvenOff",0){
	        public void entryAction(){
		          power = 0.0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);

		State state2 = new State("OvenOn-Stop",1){
	        public void entryAction(){
		          power = powerPulseOff;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("OvenOn-Start",2){
	        public void entryAction(){
		          power = oven.installedPower;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		Transition transition02= new Transition (0, 2){
			public boolean condition(){
				return oven.mode == OvenFull.Mode.ON;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition02);
		
		Transition transition20= new Transition (2,0){
			public boolean condition(){
				return oven.mode == OvenFull.Mode.OFF;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition20);
		
		Transition transition10= new Transition (1,0){
			public boolean condition(){
				return oven.mode == OvenFull.Mode.OFF;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition10);
		
		Transition transition12 = new Transition(1, 2,(int) Math.round(periodTime * (1 - percentageOn) * Time.getInstance().minute)) {
			
			@Override
			public void action() {}
		};
		statechart.addTransition(transition12);
		
		Transition transition21 = new Transition(2, 1,(int) Math.round(periodTime * percentageOn * Time.getInstance().minute)) {
			
			@Override
			public void action() {}
		};
		statechart.addTransition(transition21);
	
		statechart.setInitialState(0);
	}
}
