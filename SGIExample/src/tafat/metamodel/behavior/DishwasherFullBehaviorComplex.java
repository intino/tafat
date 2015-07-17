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
import tafat.metamodel.entity.DishwasherFull;

public class DishwasherFullBehaviorComplex implements BehaviorSimple {
	
	private InterpolatedFunction labellingFactor;
	private Coordinate[] labellingCoordenates = {new Coordinate(1, 0.36),
												 new Coordinate(2, 0.41),
												 new Coordinate(3, 0.47),
												 new Coordinate(4, 0.52),
												 new Coordinate(5, 0.58),
												 new Coordinate(6, 0.68),
												 new Coordinate(7, 0.78),
												 new Coordinate(8, 0.88),
												 new Coordinate(9, 0.98),
												 new Coordinate(10, 1.08)};
	private InterpolatedFunction heatFactor;
	private Coordinate[] heatCoordenates = {new Coordinate(1, 1300),
										 	new Coordinate(2, 1700),
										 	new Coordinate(3, 2000)};
	private InterpolatedFunction washFactor;
	private Coordinate[] washCoordenates = {new Coordinate(1, 50),
									 		new Coordinate(2, 70),
									 		new Coordinate(3, 90)};
	private InterpolatedFunction dryingoFactor;
	private Coordinate[] dryingoCoordenates = {new Coordinate(1, 1300),
											   new Coordinate(2, 1700),
											   new Coordinate(3, 2000)};

	private double tHW;
	private InterpolatedFunction timeoutHeatWater;
	private Coordinate[] timeoutHeatWaterCoordenates = {new Coordinate(1, 15),
											   		    new Coordinate(2, 20),
											   		    new Coordinate(3, 24)};
	private double tWR;
	private InterpolatedFunction timeoutWashRinse;
	private Coordinate[] timeoutWashRinseCoordenates = {new Coordinate(1, 54),
											   			new Coordinate(2, 50),
											   			new Coordinate(3, 50)};
	private double tD;
	private InterpolatedFunction timeoutDryingo;
	private Coordinate[] timeoutDryingoCoordenates = {new Coordinate(1, 15),
											   		  new Coordinate(2, 20),
											   		  new Coordinate(3, 20)};
	
	
	private double CR;
	private static ArrayList<DishwasherFullBehaviorComplex> instanceList = new ArrayList<DishwasherFullBehaviorComplex>();
	private double power;
	private DishwasherFull dishwasher;
	private StateChart statechart;
	
	Transition transition12;
	Transition transition23;
	Transition transition30;


	public static BehaviorSimple newInstance() {
		DishwasherFullBehaviorComplex behavior = new DishwasherFullBehaviorComplex();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof DishwasherFull){
			dishwasher = (DishwasherFull) target;
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
			
			
			NoneInterpolation tHeat  = new NoneInterpolation(timeoutHeatWaterCoordenates, 0.01);
			timeoutHeatWater = new InterpolatedFunction(timeoutHeatWaterCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			timeoutHeatWater.setInterpolationMethod(tHeat);
			
			NoneInterpolation tWash  = new NoneInterpolation(timeoutWashRinseCoordenates, 0.01);
			timeoutWashRinse = new InterpolatedFunction(timeoutWashRinseCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			timeoutWashRinse.setInterpolationMethod(tWash);
			
			NoneInterpolation tDryingo  = new NoneInterpolation(timeoutDryingoCoordenates, 0.01);
			timeoutDryingo = new InterpolatedFunction(timeoutDryingoCoordenates, InterpolatedFunction.OutOfRange.ERROR);
			timeoutDryingo.setInterpolationMethod(tDryingo);
			
			if(dishwasher.capacity <= 9) 
				CR = 0.45 + 0.09 * dishwasher.capacity;
			else 
				CR = 1.35 + 0.025 * dishwasher.capacity;
		}
		else
			Console.error(target.getFullPath() + " is not a Dishwasher");
	}
	@Override
	public void configure() {
	}
	@Override
	public void tickOn(Long time) {
		statechart.update();
		dishwasher.activePower = power;
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
	        	power = heatFactor.getY(dishwasher.mode.ordinal()) * labellingFactor.getY(dishwasher.labelling.ordinal() + 1) * CR;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("Wash Rinse N",2){
	        public void entryAction(){
	        	power = washFactor.getY(dishwasher.mode.ordinal()) * labellingFactor.getY(dishwasher.labelling.ordinal() + 1) * CR;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		State state4 = new State("Dryingo N",3){
	        public void entryAction(){
	        	power = dryingoFactor.getY(dishwasher.mode.ordinal()) * labellingFactor.getY(dishwasher.labelling.ordinal() + 1) * CR;
		    }
	        public void exitAction(){
	        	dishwasher.mode = DishwasherFull.Mode.OFF;
	        }
		};
		statechart.addState(state4);
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return ((dishwasher.mode == DishwasherFull.Mode.ON_ECO) || 
						(dishwasher.mode == DishwasherFull.Mode.ON_NORMAL) ||
						(dishwasher.mode == DishwasherFull.Mode.ON_INTENSIVE));
			}

			@Override
			public void action() {
				timeout();
	        	transition12.time = (int) (tHW * Time.getInstance().minute);
	        	transition23.time = (int) (tWR * Time.getInstance().minute);
	        	transition30.time = (int) (tD * Time.getInstance().minute);
			}
		};
		statechart.addTransition(transition01);		 
		
		transition12= new Transition (1,2,(int) (tHW * Time.getInstance().minute)){
			@Override
			public void action() {}
		};
		statechart.addTransition(transition12);
		
		transition23= new Transition (2,3,(int) (tWR * Time.getInstance().minute)){
			@Override
			public void action() {}
		};
		statechart.addTransition(transition23);
	
		transition30= new Transition (3,0,(int) (tD * Time.getInstance().minute)){
			@Override
			public void action() {}
		};
		statechart.addTransition(transition30);
		
		statechart.setInitialState(0);
	}
	
	private void timeout () {
		tHW = timeoutHeatWater.getY(dishwasher.mode.ordinal());
		tWR = timeoutWashRinse.getY(dishwasher.mode.ordinal());
		tD = timeoutDryingo.getY(dishwasher.mode.ordinal());
	}
}
