package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.statechart.State;
import tafat.engine.statechart.StateChart;
import tafat.engine.statechart.Transition;
import tafat.metamodel.entity.AudioHifiFull;

public class AudioHifiFullBehaviorBasic implements BehaviorSimple {

	private static ArrayList<AudioHifiFullBehaviorBasic> instanceList = new ArrayList<AudioHifiFullBehaviorBasic>();
	private double power;
	private AudioHifiFull audioHifi;
	private StateChart statechart;
	
	public static BehaviorSimple newInstance() {
		AudioHifiFullBehaviorBasic behavior = new AudioHifiFullBehaviorBasic();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof AudioHifiFull){
			audioHifi = (AudioHifiFull) target;
			createStatechart();
		}
		else
			Console.error(target.getFullPath() + " is not a AudioHifi");
	}

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickOn(Long time) {
		statechart.update();
		audioHifi.activePower = power;
	}

	@Override
	public void tickOff(Long time) {
	}
	
	public void terminate(){
		
	}
	
	private void createStatechart() {
		statechart = new StateChart();
		
		State state = new State("AudioHifiOff",0){
	        public void entryAction(){
		          power = 0.0;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state);
		
		State state2 = new State("AudioHifiStandBy",1){
	        public void entryAction(){
		          power = audioHifi.standbyPower;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state2);
		
		State state3 = new State("AudioHifiOn",2){
	        public void entryAction(){
		          power = audioHifi.installedPower;
		    }
	        public void exitAction(){}
		};
		statechart.addState(state3);
		
		
		
		Transition transition01 = new Transition (0,1){
			public boolean condition(){
				return audioHifi.mode == AudioHifiFull.Mode.STANDBY;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition01);		 
		
		Transition transition10= new Transition (1,0){
			public boolean condition(){
				return audioHifi.mode == AudioHifiFull.Mode.OFF;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition10);
		
		
		Transition transition02 = new Transition (0,2){
			public boolean condition(){
				return audioHifi.mode == AudioHifiFull.Mode.ON;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition02);		 
		
		Transition transition20= new Transition (2,0){
			public boolean condition(){
				return audioHifi.mode == AudioHifiFull.Mode.OFF;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition20);
		
		
		Transition transition12 = new Transition (1,2){
			public boolean condition(){
				return audioHifi.mode == AudioHifiFull.Mode.ON;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition12);
		
		Transition transition21= new Transition (2,1){
			public boolean condition(){
				return audioHifi.mode == AudioHifiFull.Mode.STANDBY;
			}

			@Override
			public void action() {}
		};
		statechart.addTransition(transition21);
		
		
		statechart.setInitialState(0);
	}

}
