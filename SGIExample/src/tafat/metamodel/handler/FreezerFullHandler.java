package tafat.metamodel.handler;

import tafat.engine.Time;
import tafat.engine.social.ActionMaker;
import tafat.engine.social.DeviceHandler;
import tafat.engine.social.RecipeLine;
import tafat.engine.timeout.TimeoutHandler;
import tafat.engine.timeout.TimeoutManager;
import tafat.metamodel.entity.FreezerFull;

public class FreezerFullHandler extends DeviceHandler {

	FreezerFull freezer;
	int mission;
	int actionId;
	boolean notify;
	
	public FreezerFullHandler (ActionMaker actionMaker, FreezerFull freezer, int mission, boolean notify){
		this.actionMakerCaller = actionMaker;
		this.freezer = freezer;
		this.mission = mission;
		this.notify = notify;
	}
	
	@Override
	public void startDevice(RecipeLine recipeLine) {
		String[] specialHandling = (String[]) recipeLine.getSpecialHandling();
		if (recipeLine.getStartTime() == 0){
			if (specialHandling[0].equals("OFF"))
				freezer.mode = FreezerFull.Mode.OFF;
			else if (specialHandling[0].equals("ON"))
				freezer.mode = FreezerFull.Mode.ON;
			else if (specialHandling[0].equals("STANDBY"))
				freezer.mode = FreezerFull.Mode.STANDBY;
		}
		else{
			Object[] data = {specialHandling[0], recipeLine.getDurationTime()};
			TimeoutHandler timeoutOn = new TimeoutHandler() {
				
				@Override
				public void execute(Object data) {
					String specialHandling = (String) ((Object[]) data)[0];
					int durationTime = (Integer) ((Object[]) data)[1];
					if (specialHandling.equals("OFF"))
						freezer.mode = FreezerFull.Mode.OFF;
					else if (specialHandling.equals("ON"))
						freezer.mode = FreezerFull.Mode.ON;
					else if (specialHandling.equals("STANDBY"))
						freezer.mode = FreezerFull.Mode.STANDBY;
					if (durationTime == -1){
						if (notify){
							Object[] dataToSend = {"FINISHED", mission};
							actionMakerCaller.receiveMessage(dataToSend);
						}
					}
				}
				
			};
			TimeoutManager.getInstance().add(recipeLine.getStartTime() * Time.getInstance().minute, timeoutOn, data);
		}
		if (recipeLine.getDurationTime() == -1){
			if (notify){
				Object[] dataToSend = {"FINISHED", mission};
				actionMakerCaller.receiveMessage(dataToSend);
			}
			return;
		}
		
		TimeoutHandler timeoutOn = new TimeoutHandler() {
			
			@Override
			public void execute(Object data) {
				String specialHandling = (String) data;
				if (specialHandling.equals("OFF"))
					freezer.mode = FreezerFull.Mode.OFF;
				else if (specialHandling.equals("ON"))
					freezer.mode = FreezerFull.Mode.ON;
				else if (specialHandling.equals("STANDBY"))
					freezer.mode = FreezerFull.Mode.STANDBY;
				if (notify){
					Object[] dataToSend = {"FINISHED", mission};
					actionMakerCaller.receiveMessage(dataToSend);
				}
			}
			
		};
		TimeoutManager.getInstance().add((recipeLine.getStartTime() + recipeLine.getDurationTime()) * Time.getInstance().minute, timeoutOn, specialHandling[1]);
	}

}
