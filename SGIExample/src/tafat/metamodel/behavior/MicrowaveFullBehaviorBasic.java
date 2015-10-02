package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.MicrowaveFull;

public class MicrowaveFullBehaviorBasic implements BehaviorSimple {

	private static ArrayList<MicrowaveFullBehaviorBasic> instanceList = new ArrayList<MicrowaveFullBehaviorBasic>();
	private double power;
	private MicrowaveFull microwave;
	private StateChart statechart;
	
	public static BehaviorSimple newInstance() {
		MicrowaveFullBehaviorBasic behavior = new MicrowaveFullBehaviorBasic();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof MicrowaveFull){
			microwave = (MicrowaveFull) target;
			createStatechart();
		}
		else
			Console.error(target.getFullPath() + " is not a Microwave");
	}
	@Override
	public void configure(){
	}
	@Override
	public void tickOn(Long time) {
		statechart.update();
		microwave.activePower = power;
	}

	@Override
	public void tickOff(Long time) {
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("MicrowaveOff",0){
	        public void entryAction(){
		          power = 0.0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);
		
		State state2 = new State("MicrowaveStandBy",1){
	        public void entryAction(){
		          power = 4;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("MicrowaveOn",2){
	        public void entryAction(){
	        	if (microwave.mode == MicrowaveFull.Mode.ON_DEFROST) power=800.;
	        	else if (microwave.mode == MicrowaveFull.Mode.ON_800) power=800.;
	        	else if (microwave.mode == MicrowaveFull.Mode.ON_900) power=900.;
	        	else if (microwave.mode == MicrowaveFull.Mode.ON_1000) power=1000.;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return microwave.mode == MicrowaveFull.Mode.STANDBY;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition01);		 
		
		Transition transition10= new Transition (1,0){
			public boolean condition(){
				return microwave.mode == MicrowaveFull.Mode.OFF;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition10);
		
		
		Transition transition02 = new Transition (0,2){
			public boolean condition(){
				return ((microwave.mode == MicrowaveFull.Mode.ON_DEFROST) || 
						(microwave.mode == MicrowaveFull.Mode.ON_800) || 
						(microwave.mode == MicrowaveFull.Mode.ON_900) || 
						(microwave.mode == MicrowaveFull.Mode.ON_1000));
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition02);		 
		
		Transition transition20= new Transition (2,0){
			public boolean condition(){
				return microwave.mode == MicrowaveFull.Mode.OFF;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition20);
		
		
		Transition transition12 = new Transition (1,2){
			public boolean condition(){
				return ((microwave.mode == MicrowaveFull.Mode.ON_DEFROST) || 
						(microwave.mode == MicrowaveFull.Mode.ON_800) || 
						(microwave.mode == MicrowaveFull.Mode.ON_900) || 
						(microwave.mode == MicrowaveFull.Mode.ON_1000));
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition12);
		
		Transition transition21= new Transition (2,1){
			public boolean condition(){
				return microwave.mode == MicrowaveFull.Mode.STANDBY;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition21);
		
		statechart.setInitialState(0);
	}
}
