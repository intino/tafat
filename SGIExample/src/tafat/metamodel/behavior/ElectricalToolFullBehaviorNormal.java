package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.ElectricalToolFull;

public class ElectricalToolFullBehaviorNormal implements BehaviorSimple {

	private static ArrayList<ElectricalToolFullBehaviorNormal> instanceList = new ArrayList<ElectricalToolFullBehaviorNormal>();
	private double power;
	private ElectricalToolFull electricalTool;
	private StateChart statechart;
	
	public static BehaviorSimple newInstance() {
		ElectricalToolFullBehaviorNormal behavior = new ElectricalToolFullBehaviorNormal();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof ElectricalToolFull){
			electricalTool = (ElectricalToolFull) target;
			createStatechart();
		}
		else
			Console.error(target.getFullPath() + " is not a ElectricalTool");
	}
	
	@Override
	public void configure() {
	}
	
	@Override
	public void tickOn(Long time) {
		statechart.update();
		electricalTool.activePower = power;
	}

	@Override
	public void tickOff(Long time) {
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("ElectricalToolOff",0){
	        public void entryAction(){
		          power = 0.0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);

		State state3 = new State("ElectricalToolOn",1){
	        public void entryAction(){
		          power = electricalTool.installedPower;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return electricalTool.mode == ElectricalToolFull.Mode.ON;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition01);		 
		
		Transition transition10= new Transition (1,0){
			public boolean condition(){
				return electricalTool.mode == ElectricalToolFull.Mode.OFF;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition10);
	
		statechart.setInitialState(0);
	}
}
