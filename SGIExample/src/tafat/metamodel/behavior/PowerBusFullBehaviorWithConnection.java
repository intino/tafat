package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Notification;
import tafat.metamodel.connection.PowerConnectionFull;
import tafat.metamodel.entity.PowerBusFull;
import tafat.metamodel.entity.PowerEquipment;

public class PowerBusFullBehaviorWithConnection implements BehaviorSimple {

	private static ArrayList<PowerBusFullBehaviorWithConnection> instanceList = new ArrayList<PowerBusFullBehaviorWithConnection>();
	private PowerBusFull powerBus;
	private PowerConnectionFull powerConnection;
	
	public static BehaviorSimple newInstance() {
		PowerBusFullBehaviorWithConnection behavior = new PowerBusFullBehaviorWithConnection();
		instanceList.add(behavior);
		return behavior;
	}

	public void init(ModelObject target) {
		if (target instanceof PowerBusFull){
			powerBus = (PowerBusFull) target;
		}
		else
			Console.error(target.getFullPath() + " is not a PowerBus");
	}
	
	@Override
	public void tickOn(Long time) {
		checkNotifications();
	}
	
	private void checkNotifications() {
		ArrayList <Notification> notificationsToRemove = new ArrayList <Notification> ();
		for (Notification notification : powerBus.notifications){
			if (notification.notify.equals("CONNECTION"))
					powerConnection = (PowerConnectionFull) notification.data;
			notificationsToRemove.add(notification);
		}
		
		for (Notification notification : notificationsToRemove)
			powerBus.notifications.remove(notification);
	}

	@Override
	public void tickOff(Long time) {
		powerBus.activePower = 0;
		for (PowerEquipment powerEquipment : powerBus.powerEquipmentList)
			powerBus.activePower += powerEquipment.activePower;
		if (powerConnection != null)
			powerConnection.power = powerBus.activePower;
	}
	
	public void terminate(){
		
	}
	
	public void loadAttribute(String name, String value) {
	}	

	@Override
	public void configure() {
		checkNotifications();
	}
}
