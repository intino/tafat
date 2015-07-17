package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Time;
import tafat.engine.conversion.UnitConversor;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.DryerFull;

public class DryerFullBehaviorBasic implements BehaviorSimple {

	private static ArrayList<DryerFullBehaviorBasic> instanceList = new ArrayList<DryerFullBehaviorBasic>();
	private double power;
	private DryerFull dryer;
	private StateChart statechart;
	private double cycleTime = 72;
	
	public static BehaviorSimple newInstance() {
		DryerFullBehaviorBasic behavior = new DryerFullBehaviorBasic();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
		if (name.equals("cycleTime"))
			cycleTime = UnitConversor.parse(value, "m");
	}	

	public void init(ModelObject target) {
		if (target instanceof DryerFull){
			dryer = (DryerFull) target;
			createStatechart();
		}
		else
			Console.error(target.getFullPath() + " is not a Dryer");
	}
	@Override
	public void configure() {
	}
	@Override
	public void tickOn(Long time) {
		statechart.update();
		dryer.activePower = power;
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
		
		State state2 = new State("On",1){
	        public void entryAction(){
	        	power = dryer.installedPower;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return (dryer.mode == DryerFull.Mode.ON);
			}

			@Override
			public void action() {
			}
		};
		statechart.addTransition(transition01);		 

		Transition transition30= new Transition (1,0, (int) (cycleTime * Time.getInstance().minute)){

			@Override
			public void action() {
				dryer.mode = DryerFull.Mode.OFF;
			}};
		statechart.addTransition(transition30);
		
		statechart.setInitialState(0);
	}
}
