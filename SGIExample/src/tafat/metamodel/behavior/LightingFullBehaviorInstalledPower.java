package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.metamodel.entity.LightingFull;

public class LightingFullBehaviorInstalledPower implements BehaviorSimple {

	private static ArrayList<LightingFullBehaviorInstalledPower> instanceList = new ArrayList<LightingFullBehaviorInstalledPower>();
	private double power;
	private LightingFull lighting;
	
	public static BehaviorSimple newInstance() {
		LightingFullBehaviorInstalledPower behavior = new LightingFullBehaviorInstalledPower();
		instanceList.add(behavior);
		return behavior;
	}
	
	public void loadAttribute(String name, String value) {
	}	

	public void init(ModelObject target) {
		if (target instanceof LightingFull){
			lighting = (LightingFull) target;
		}
		else
			Console.error(target.getFullPath() + " is not a Lighting");
	}
	@Override
	public void configure(){
	}
	@Override
	public void tickOn(Long time) {
	}

	@Override
	public void tickOff(Long time) {
		power = lighting.installedPower * lighting.installedPowerUsageRate * lighting.intensityRate;
		if (power == 0)
			lighting.mode = LightingFull.Mode.OFF;
		else
			lighting.mode = LightingFull.Mode.ON;
		lighting.activePower = power;
	}
	
	public void terminate(){
		
	}
}
