package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.interpolatedfunction.Coordinate;
import tafat.engine.interpolatedfunction.InterpolatedFunction;
import tafat.engine.interpolatedfunction.LinearInterpolation;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.TvFull;

public class TvFullBehaviorComplex implements BehaviorSimple {
	
	private static ArrayList<TvFullBehaviorComplex> instanceList = new ArrayList<TvFullBehaviorComplex>();
	private double power;
	private TvFull tv;
	private StateChart statechart;
	public double powerON;
	
	public static BehaviorSimple newInstance() {
		TvFullBehaviorComplex behavior = new TvFullBehaviorComplex();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof TvFull){
			tv = (TvFull) target;
			createStatechart();
			if (tv.typeTV == TvFull.TypeTV.CRT)
					powerON = 80; // 19" inchs (No longer information
			else if (tv.typeTV == TvFull.TypeTV.LCD){
					Coordinate[] lcdCoordinates = {new Coordinate(32, 125),
												   new Coordinate(42, 176),
												   new Coordinate(52, 260)};
					LinearInterpolation linearMethod = new LinearInterpolation(lcdCoordinates);
					InterpolatedFunction lcdFunction = new InterpolatedFunction(lcdCoordinates, InterpolatedFunction.OutOfRange.EXTRAPOLATE);
					lcdFunction.setInterpolationMethod(linearMethod);
					powerON = lcdFunction.getY(tv.size);
			}
			else if (tv.typeTV == TvFull.TypeTV.LED)
					powerON = 127; // 42" inchs (No longer information)
			else if (tv.typeTV == TvFull.TypeTV.DLP){
					Coordinate[] dlpCoordinates = {new Coordinate(56, 170),
												   new Coordinate(61, 232),
												   new Coordinate(65, 242)};
					LinearInterpolation dlpLinearMethod = new LinearInterpolation(dlpCoordinates);
					InterpolatedFunction dlpFunction = new InterpolatedFunction(dlpCoordinates, InterpolatedFunction.OutOfRange.EXTRAPOLATE);
					dlpFunction.setInterpolationMethod(dlpLinearMethod);
					powerON = dlpFunction.getY(tv.size);
			}
			else if (tv.typeTV == TvFull.TypeTV.PDP){
					Coordinate[] plasmaCoordinates = {new Coordinate(32, 125),
												   new Coordinate(42, 270),
												   new Coordinate(52, 340)};
					LinearInterpolation plasmaLinearMethod = new LinearInterpolation(plasmaCoordinates);
					InterpolatedFunction plasmaFunction = new InterpolatedFunction(plasmaCoordinates, InterpolatedFunction.OutOfRange.EXTRAPOLATE);
					plasmaFunction.setInterpolationMethod(plasmaLinearMethod);
					powerON = plasmaFunction.getY(tv.size);
			}
		}
		else
			Console.error(target.getFullPath() + " is not a Tv");
	}
	@Override
	public void configure(){
	}	
	@Override
	public void tickOn(Long time) {
		statechart.update();
		tv.activePower = power;
	}

	@Override
	public void tickOff(Long time) {
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("TvOff",0){
	        public void entryAction(){
		          power = 0.0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);
		
		State state2 = new State("TvStandBy",1){
	        public void entryAction(){
		          power = tv.standbyPower;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("TvOn",2){
	        public void entryAction(){
		          power = powerON;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return tv.mode == TvFull.Mode.STANDBY;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition01);		 
		
		Transition transition10= new Transition (1,0){
			public boolean condition(){
				return tv.mode == TvFull.Mode.OFF;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition10);
		
		
		Transition transition02 = new Transition (0,2){
			public boolean condition(){
				return tv.mode == TvFull.Mode.ON;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition02);		 
		
		Transition transition20= new Transition (2,0){
			public boolean condition(){
				return tv.mode == TvFull.Mode.OFF;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition20);
		
		
		Transition transition12 = new Transition (1,2){
			public boolean condition(){
				return tv.mode == TvFull.Mode.ON;
			}

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
			}
		};
		statechart.addTransition(transition12);
		
		Transition transition21= new Transition (2,1){
			public boolean condition(){
				return tv.mode == TvFull.Mode.STANDBY;
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
