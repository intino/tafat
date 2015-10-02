package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Notification;
import tafat.engine.Time;
import tafat.engine.timeout.TimeoutHandler;
import tafat.engine.timeout.TimeoutManager;
import tafat.metamodel.entity.ControlUnitFull;



public class ControlUnitFullBehaviorIntermediary implements BehaviorSimple {
	ArrayList<ControlUnitFull> controlUnitsConnected = new ArrayList<ControlUnitFull> ();
	
	private static ArrayList<ControlUnitFullBehaviorIntermediary> instanceList = new ArrayList<ControlUnitFullBehaviorIntermediary>();

	private ControlUnitFull controlUnit;

	private Notification currentNotification = null;

	private Double currentOrder = 0.;
	@SuppressWarnings("unused")
	private Double previousOrder = 0.;
	
	public static BehaviorSimple newInstance() {
		ControlUnitFullBehaviorIntermediary behavior = new ControlUnitFullBehaviorIntermediary();
		instanceList.add(behavior);
		return behavior;
	}

	public void init(ModelObject target) {
		if (target instanceof ControlUnitFull){ 
			controlUnit = (ControlUnitFull) target;
		}
		else
			Console.error(target.getFullPath() + " is not a ControlUnit");
	}
	@Override
	public void configure() {
	}
	@Override
	public void tickOn(Long time) {
		checkNotifications();
		
		controlUnit.realConsumption = 0;
		for (ControlUnitFull cu : controlUnitsConnected)
			controlUnit.realConsumption += cu.realConsumption;

		if (currentNotification != null){
			previousOrder = currentOrder;
			currentOrder  = (Double)currentNotification.data;
			
			controlUnit.aimConsumption = controlUnit.realConsumption + currentOrder;
		
			double proportion = controlUnitsConnected.get(0).realConsumption / controlUnit.realConsumption;
			controlUnitsConnected.get(0).receiveNotification(new Notification(0, currentNotification.notify, proportion * currentOrder));
			
			for (int i = 1 ; i < controlUnitsConnected.size(); i++){
				TimeoutHandler handler = new TimeoutHandler() {
					
					@Override
					public void execute(Object data) {
						int index = (Integer)((Object[])data)[0];
						String notification = (String)((Object[])data)[1];
						double proportion = controlUnitsConnected.get(index).realConsumption / controlUnit.realConsumption;
						controlUnitsConnected.get(index).receiveNotification(new Notification(0, notification, proportion * currentOrder));
					}
				};
				TimeoutManager.getInstance().add(Time.getInstance().minute / 2 * i, handler, new Object [] {i, currentNotification.notify});
			}

			currentNotification = null;
		}
	}
	
	private void checkNotifications() {
		ArrayList <Notification> notificationsToRemove = new ArrayList <Notification> ();
		for (Notification notification : controlUnit.notifications){
			if (notification.notify.equals("CONNECTION")){
				if (notification.data instanceof ControlUnitFull)
					if (!((ModelObject)notification.data).id.equals("super"))
						controlUnitsConnected.add((ControlUnitFull)notification.data);
			}
			else if (notification.notify.equals("MODIFY") || notification.notify.equals("RESTORE"))
				currentNotification  = notification;
			notificationsToRemove.add(notification);
		}
		
		for (Notification notification : notificationsToRemove)
			controlUnit.notifications.remove(notification);
	}

	@Override
	public void tickOff(Long time) {
	}

	public void terminate(){
	}
	
	public void loadAttribute(String name, String value) {
	}	
	
}
