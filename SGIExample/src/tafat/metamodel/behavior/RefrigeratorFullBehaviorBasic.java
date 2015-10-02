package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Time;
import tafat.engine.Tools;
import tafat.engine.interpolatedfunction.Coordinate;
import tafat.engine.interpolatedfunction.InterpolatedFunction;
import tafat.engine.interpolatedfunction.LinearInterpolation;
import tafat.engine.interpolatedfunction.NoneInterpolation;
import tafat.engine.interpolatedfunction.StepInterpolation;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.engine.timeout.TimeoutHandler;
import tafat.engine.timeout.TimeoutManager;
import tafat.metamodel.entity.RefrigeratorFull;

public class RefrigeratorFullBehaviorBasic implements BehaviorSimple {
	
	private InterpolatedFunction labellingFactor;
	private Coordinate[] labellingCoordenates = {new Coordinate(1, 0.3),
												 new Coordinate(2, 0.42),
												 new Coordinate(3, 0.55),
												 new Coordinate(4, 0.75),
												 new Coordinate(5, 0.9),
												 new Coordinate(6, 0.99),
												 new Coordinate(7, 1)};
	private InterpolatedFunction rfIF;
	private Coordinate[] rfCoordenates = {new Coordinate(0, 34),
										  new Coordinate(8, 37),
										  new Coordinate(13, 41),
										  new Coordinate(16, 39),
										  new Coordinate(20, 41),
										  new Coordinate(22, 37)};
	private InterpolatedFunction levelF;
	private Coordinate[] levelCoordinates = {new Coordinate(1, 1.3),
											 new Coordinate(6, 0.8)};
	
	private double periodTime = 108;
	private double powerFactor = 120;
	
	private static ArrayList<RefrigeratorFullBehaviorBasic> instanceList = new ArrayList<RefrigeratorFullBehaviorBasic>();
	private double power;
	private RefrigeratorFull refrigerator;
	private StateChart statechart;
	
	Transition transition12;
	Transition transition21;
	
	public static BehaviorSimple newInstance() {
		RefrigeratorFullBehaviorBasic behavior = new RefrigeratorFullBehaviorBasic();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
		if (name.equals("powerFactor"))
			powerFactor = Double.parseDouble(value);
	}	

	public void init(ModelObject target) {
		if (target instanceof RefrigeratorFull){
			periodTime = Tools.randomInRangeDouble(periodTime - 10, periodTime + 10);
			
			refrigerator = (RefrigeratorFull) target;
	
			NoneInterpolation labelling = new NoneInterpolation(labellingCoordenates, 0.01);
			labellingFactor = new InterpolatedFunction(labellingCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			labellingFactor.setInterpolationMethod(labelling);
			
			StepInterpolation rf = new StepInterpolation(rfCoordenates);
			rfIF = new InterpolatedFunction(rfCoordenates, InterpolatedFunction.OutOfRange.NEAREST);
			rfIF.setInterpolationMethod(rf);
			
			LinearInterpolation lf = new LinearInterpolation(levelCoordinates);
			levelF = new InterpolatedFunction(levelCoordinates, InterpolatedFunction.OutOfRange.ERROR);
			levelF.setInterpolationMethod(lf);
			
			createStatechart();
		}
		else
			Console.error(target.getFullPath() + " is not a Refrigerator");
	}
	@Override
	public void configure(){
	}
	@Override
	public void tickOn(Long time) {
	}

	@Override
	public void tickOff(Long time) {
		statechart.update();
		refrigerator.activePower = power;
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
	        @SuppressWarnings("deprecation")
			public void entryAction(){
	        	power= powerFactor * labellingFactor.getY(refrigerator.labelling.ordinal() + 1);
	        	transition12.time = (int)(periodTime * 
						 				  Time.getInstance().minute *
						 				  rfIF.getY(Time.getInstance().getSimulationDate().getHours()) /
						 				  100);
		    }
	        public void exitAction(){}
		};
		statechart.addState(state1);
		
		State state2 = new State("State - Pulse Off",2){
	        @SuppressWarnings("deprecation")
			public void entryAction(){
	        	power = 8.8;
	        	double timeToOn = (periodTime * 
				          		   Time.getInstance().minute *
				          		   (100-  rfIF.getY(Time.getInstance().getSimulationDate().getHours())) /
				          		   100);
	        	transition21.time = (int)(timeToOn * levelF.getY(refrigerator.thermostatLevel.ordinal()));
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("Getting ready",3){
	        @SuppressWarnings("deprecation")
			public void entryAction(){
	        	if (Tools.randomInRangeInt(0, 1) == 0){
		        	power = 8.8;
		        	double timeToOn = (Tools.randomInRangeDouble(0, periodTime) * 
					          		   Time.getInstance().minute *
					          		   (100-  rfIF.getY(Time.getInstance().getSimulationDate().getHours())) /
					          		   100);
		        	timeToOn = (timeToOn * levelF.getY(refrigerator.thermostatLevel.ordinal()));
		        	TimeoutHandler handler = new TimeoutHandler() {
						
						@Override
						public void execute(Object data) {
							statechart.setCurrentState(1);
						}
					};
					TimeoutManager.getInstance().add((int) timeToOn, handler, null);
				}
				else{
		        	power= powerFactor * labellingFactor.getY(refrigerator.labelling.ordinal() + 1);
		        	double timeToOff = (Tools.randomInRangeDouble(0, periodTime) * 
							 				  Time.getInstance().minute *
							 				  rfIF.getY(Time.getInstance().getSimulationDate().getHours()) /
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
				return refrigerator.mode == RefrigeratorFull.Mode.ON;
			}
			@Override
			public void action() {
				
			}
		};
		statechart.addTransition(transition03);
		
		Transition transition10 = new Transition(1,0) {
			public boolean condition(){
				return refrigerator.mode == RefrigeratorFull.Mode.OFF;
			}
			
			@Override
			public void action() {}
		};
		statechart.addTransition(transition10);
		
		transition12 = new Transition (1,2,0){
			@Override
			public void action() {
			}
		};
		statechart.addTransition(transition12);		 
		
		Transition transition20 = new Transition(2,0) {
			public boolean condition(){
				return refrigerator.mode == RefrigeratorFull.Mode.OFF;
			}
			
			@Override
			public void action() {}
		};
		statechart.addTransition(transition20);

		transition21= new Transition (2, 1, 0){
			@Override
			public void action() {
			}
		};
		statechart.addTransition(transition21);
		
		statechart.setInitialState(0);
	}
}
