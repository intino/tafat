package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Time;
import tafat.engine.Tools;
import tafat.engine.interpolatedfunction.Coordinate;
import tafat.engine.interpolatedfunction.InterpolatedFunction;
import tafat.engine.interpolatedfunction.NoneInterpolation;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.engine.timeout.TimeoutHandler;
import tafat.engine.timeout.TimeoutManager;
import tafat.metamodel.entity.FreezerFull;

public class FreezerFullBehaviorBasic implements BehaviorSimple {
	
	private InterpolatedFunction labellingFactor;
	private Coordinate[] labellingCoordenates = {new Coordinate(1, 0.3),
												 new Coordinate(2, 0.42),
												 new Coordinate(3, 0.55),
												 new Coordinate(4, 0.75),
												 new Coordinate(5, 0.9),
												 new Coordinate(6, 0.99),
												 new Coordinate(7, 1)};
	
	final private double periodTime = 108;

	private static ArrayList<FreezerFullBehaviorBasic> instanceList = new ArrayList<FreezerFullBehaviorBasic>();
	private double power;
	private FreezerFull freezer;
	private StateChart statechart;
	
	public double powerFactor = 200;
	
	public static BehaviorSimple newInstance() {
		FreezerFullBehaviorBasic behavior = new FreezerFullBehaviorBasic();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
		if (name.equals("powerFactor"))
			powerFactor = Double.parseDouble(value);
	}	

	public void init(ModelObject target) {
		if (target instanceof FreezerFull){
			freezer = (FreezerFull) target;
			createStatechart();
			
			NoneInterpolation labelling = new NoneInterpolation(labellingCoordenates, 0.01);
			labellingFactor = new InterpolatedFunction(labellingCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			labellingFactor.setInterpolationMethod(labelling);
		}
		else
			Console.error(target.getFullPath() + " is not a Freezer");
	}
	@Override
	public void configure() {
	}
	@Override
	public void tickOn(Long time) {
	}

	@Override
	public void tickOff(Long time) {
		statechart.update();
		freezer.activePower = power;
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("State - Off",0){
	        public void entryAction(){
	        	power = 0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);		
		
		State state1 = new State("State - Pulse On",1){
	        public void entryAction(){
	        	power= powerFactor * labellingFactor.getY(freezer.labelling.ordinal() + 1);
		    }
	        public void exitAction(){}
		};
		statechart.addState(state1);
		
		State state2 = new State("State - Pulse Off",2){
	        public void entryAction(){
	        	power = 8.8;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("Getting ready",3){
			public void entryAction(){
	        	if (Tools.randomInRangeInt(0, 1) == 0){
		        	power = 8.8;
		        	int timeToOn = (int)(Tools.randomInRangeDouble(0, periodTime) * 
					         Time.getInstance().minute *
					         (100 - 50) /
					         100);
		        	TimeoutHandler handler = new TimeoutHandler() {
						
						@Override
						public void execute(Object data) {
							statechart.setCurrentState(1);
						}
					};
					TimeoutManager.getInstance().add((int) timeToOn, handler, null); 
				}
				else{
		        	power= powerFactor * labellingFactor.getY(freezer.labelling.ordinal() + 1);
		        	int timeToOff = (int)(Tools.randomInRangeDouble(0, periodTime) * 
							 Time.getInstance().minute *
								 50 /
								 100);
		        	TimeoutHandler handler = new TimeoutHandler() {
							
							@Override
							public void execute(Object data) {
								statechart.setCurrentState(2);
							}
						};
						TimeoutManager.getInstance().add((int) timeToOff, handler, null);
				}
	        }
	        public void exitAction(){}
		};
		statechart.addState(state3);

		Transition transition03 = new Transition(0,3) {
			public boolean condition(){
				return freezer.mode == FreezerFull.Mode.ON;
			}
			
			@Override
			public void action() {}
		};
		statechart.addTransition(transition03);
		
		Transition transition10 = new Transition(1,0) {
			public boolean condition(){
				return freezer.mode == FreezerFull.Mode.OFF;
			}
			
			@Override
			public void action() {}
		};
		statechart.addTransition(transition10);
		
		Transition transition12 = new Transition (1,2, (int)(periodTime * 
		         													 Time.getInstance().minute *
		         													 50 /
		         													 100)){
			@Override
			public void action() {
			}
		};
		statechart.addTransition(transition12);		 
		
		Transition transition20 = new Transition(2,0) {
			public boolean condition(){
				return freezer.mode == FreezerFull.Mode.OFF;
			}
			
			@Override
			public void action() {}
		};
		statechart.addTransition(transition20);

		Transition transition21= new Transition (2, 1, (int)(periodTime * 
															         Time.getInstance().minute *
															         (100 - 50) /
															         100)){
			@Override
			public void action() {
			}
		};
		statechart.addTransition(transition21);
		
		statechart.setInitialState(0);
	}
}
