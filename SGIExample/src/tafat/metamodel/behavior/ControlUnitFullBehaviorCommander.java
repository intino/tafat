package tafat.metamodel.behavior;

import java.util.ArrayList;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Notification;
import tafat.engine.Time;
import tafat.engine.interoperability.XLSHandler;
import tafat.engine.timeout.TimeoutCyclic;
import tafat.engine.timeout.TimeoutHandler;
import tafat.engine.timeout.TimeoutManager;
import tafat.metamodel.entity.ControlUnitFull;



public class ControlUnitFullBehaviorCommander implements BehaviorSimple {
	ArrayList<ControlUnitFull> controlUnitsConnected = new ArrayList<ControlUnitFull> ();
	
	private static ArrayList<ControlUnitFullBehaviorCommander> instanceList = new ArrayList<ControlUnitFullBehaviorCommander>();

	private String ordersPath;
	
	private ControlUnitFull controlUnit;

	private XLSHandler curveReader;
	
	private boolean flagOrder = false;
	
	private int timeSlide = 0;
	
	private int currentRow = 1;
	
	private double currentOrder = 0;
	private double previousOrder = 0;

	private String[] columns = new String[] {"A","B","C"};
	private int columnIndex = 0;
	private String currentColumn = "";
	
	public static BehaviorSimple newInstance() {
		ControlUnitFullBehaviorCommander behavior = new ControlUnitFullBehaviorCommander();
		instanceList.add(behavior);
		return behavior;
	}

	public void init(ModelObject target) {
		if (target instanceof ControlUnitFull){ 
			controlUnit = (ControlUnitFull) target;

			curveReader = new XLSHandler(ordersPath, 0);
			
			currentColumn = columns[columnIndex++];
			
			new TimeoutCyclic(Time.getInstance().day) {
				
				@Override
				public void action() {
					currentColumn = columns[columnIndex++];
				}
			};
			
			int rows = curveReader.getNumberOfEntries() - 2;
			timeSlide = Time.getInstance().day / rows;
						
			flagOrder = true;
			currentOrder = curveReader.readDoubleCellofRow(currentRow++, currentColumn);
			
			new TimeoutCyclic(timeSlide) {
				
				@Override
				public void action() {
					flagOrder = true;
					previousOrder = currentOrder;
					currentOrder = curveReader.readDoubleCellofRow(currentRow++, currentColumn);
				}
			};
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
		
		if (flagOrder){
			if (currentOrder != 0 && currentOrder != previousOrder){
				controlUnit.realConsumption = 0;
				for (ControlUnitFull cu : controlUnitsConnected)
					controlUnit.realConsumption += cu.realConsumption;
				
				
				double proportion = controlUnitsConnected.get(0).realConsumption / controlUnit.realConsumption;
				controlUnitsConnected.get(0).receiveNotification(new Notification(0, "MODIFY", proportion * currentOrder));
				
				for (int i = 1 ; i < controlUnitsConnected.size(); i++){
					TimeoutHandler handler = new TimeoutHandler() {
						
						@Override
						public void execute(Object data) {
							int index = (Integer) data;
							double proportion = controlUnitsConnected.get(index).realConsumption / controlUnit.realConsumption;
							controlUnitsConnected.get(index).receiveNotification(new Notification(0, "MODIFY", proportion * currentOrder));
						}
					};
					TimeoutManager.getInstance().add(Time.getInstance().minute * i, handler, i);
				}
			}
			else if (currentOrder == 0 && previousOrder != 0){
				controlUnitsConnected.get(0).receiveNotification(new Notification(0, "RESTORE", 0.));
				
				for (int i = 1 ; i < controlUnitsConnected.size(); i++){
					TimeoutHandler handler = new TimeoutHandler() {
						
						@Override
						public void execute(Object data) {
							int index = (Integer) data;
							controlUnitsConnected.get(index).receiveNotification(new Notification(0, "RESTORE", 0.));
						}
					};
					TimeoutManager.getInstance().add(Time.getInstance().minute * i, handler, i);
				}
			}
			flagOrder = false;
		}
	}
	
	private void checkNotifications() {
		ArrayList <Notification> notificationsToRemove = new ArrayList <Notification> ();
		for (Notification notification : controlUnit.notifications){
			if (notification.notify.equals("CONNECTION")){
				if (notification.data instanceof ControlUnitFull)
					controlUnitsConnected.add((ControlUnitFull)notification.data);
			}
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
		if (name.equals("orders"))
			ordersPath = value;
		else if (name.equals("columns"))
			columns = value.split(",");
	}	
	
}
