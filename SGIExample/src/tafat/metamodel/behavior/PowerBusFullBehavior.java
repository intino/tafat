package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.metamodel.entity.PowerBusFull;
import tafat.metamodel.entity.PowerEquipment;

public class PowerBusFullBehavior implements BehaviorSimple {

	private static ArrayList<PowerBusFullBehavior> instanceList = new ArrayList<PowerBusFullBehavior>();
	private PowerBusFull powerBus;
	
	public static BehaviorSimple newInstance() {
		PowerBusFullBehavior behavior = new PowerBusFullBehavior();
		instanceList.add(behavior);
		return behavior;
	}

	public void init(ModelObject target) {
		if (target instanceof PowerBusFull)
			powerBus = (PowerBusFull) target;
		else
			Console.error(target.getFullPath() + " is not a PowerBus");
	}
	@Override
	public void configure(){
	}
	@Override
	public void tickOn(Long time) {
		
	}

	@Override
	public void tickOff(Long time) {
		powerBus.activePower = 0;
		for (PowerEquipment powerEquipment : powerBus.powerEquipmentList){
//			if (powerEquipment.activePower < 0)
//				continue;
			powerBus.activePower += powerEquipment.activePower;
			// ELIMINAR CUANDO NO HAYAN CONSUMOS NEGATIVOS
		}
	}
	
	public void terminate(){
		
	}
	
	public void loadAttribute(String name, String value) {
	}	

}
