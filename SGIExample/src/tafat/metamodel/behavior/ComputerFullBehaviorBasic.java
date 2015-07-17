package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.ComputerFull;

public class ComputerFullBehaviorBasic implements BehaviorSimple {

	private static ArrayList<ComputerFullBehaviorBasic> instanceList = new ArrayList<ComputerFullBehaviorBasic>();
	private double power;
	private ComputerFull computer;
	private StateChart statechart;
	
	public static BehaviorSimple newInstance() {
		ComputerFullBehaviorBasic behavior = new ComputerFullBehaviorBasic();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof ComputerFull){
			computer = (ComputerFull) target;
			createStatechart();
		}
		else
			Console.error(target.getFullPath() + " is not a Computer");
	}
	@Override
	public void configure() {
	}
	@Override
	public void tickOn(Long time) {
		statechart.update();
		computer.activePower = power;
	}

	@Override
	public void tickOff(Long time) {
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("ComputerOff",0){
	        public void entryAction(){
		          power = 0.0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);
		
		State state2 = new State("ComputerStandBy",1){
	        public void entryAction(){
		          power = 7.1;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("ComputerOn",2){
	        public void entryAction(){
		          power = 100;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return computer.mode == ComputerFull.Mode.STANDBY;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition01);		 
		
		Transition transition10= new Transition (1,0){
			public boolean condition(){
				return computer.mode == ComputerFull.Mode.OFF;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition10);
		
		
		Transition transition02 = new Transition (0,2){
			public boolean condition(){
				return computer.mode == ComputerFull.Mode.ON;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition02);		 
		
		Transition transition20= new Transition (2,0){
			public boolean condition(){
				return computer.mode == ComputerFull.Mode.OFF;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition20);
		
		
		Transition transition12 = new Transition (1,2){
			public boolean condition(){
				return computer.mode == ComputerFull.Mode.ON;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition12);
		
		Transition transition21= new Transition (2,1){
			public boolean condition(){
				return computer.mode == ComputerFull.Mode.STANDBY;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition21);
		
		
		statechart.setInitialState(0);
	}
}
