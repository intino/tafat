package tafat.engine;


import java.lang.reflect.Method;
import java.util.ArrayList;

public class BehaviorContainer extends ModelObject {
	public String name;
	public String release = "";
	public Behavior behavior;
	public long step = 1;
	public long countdown = 0;
	public ArrayList<String> settingList = new ArrayList<String>();
	
	private long lastTick = 0;

	protected void loadAttribute(String name, String value) {
		if (name.equals("name"))
			this.name = value;
		else if (name.equals("release"))
			this.release = value;
		else if (name.equals("step")){
			this.step = Long.parseLong(value);
			settingList.add("step=" + this.step);
		}
		else
			settingList.add(name + "=" + value);
	}

	public void init()  {
		if (name == null) {
			Console.error(this.getFullPath() + " name is not specified");
			return;			
		}
		String behaviorClassName = name + release;
		behavior = createInstance(behaviorClassName);
		if (behavior == null) return;
		if (owner == null) {
			try {
				Console.error(behaviorClassName + " target does not exist");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;			
		}
		
		for (String setting : settingList) {
			String[] nameAndValue = setting.split("=");
			behavior.loadAttribute(nameAndValue[0], nameAndValue[1]);
		}
		if (behavior instanceof BehaviorSimple)
			((BehaviorSimple)behavior).init(owner);
		else if (behavior instanceof BehaviorSingleton)
			((BehaviorSingleton)behavior).addObject(owner);
		Console.out(behaviorClassName + " initialized");
		// FORCE TO TICK AT SECOND 0!
		lastTick = - step;
	}

	public void tick(Long time) {
		if (countdown == 0)
			countdown = step;
		countdown -= (time - lastTick);
		lastTick = time;
	}
	
	public void fastTick (Long time){
		tick(time);
	}

	private Behavior createInstance(String name) {
		Class<? extends Behavior> behaviorClass = null;
		try {
			behaviorClass = Class.forName("tafat.metamodel.behavior." + name).asSubclass(Behavior.class);
		}
		catch (Throwable e) {
			Console.error(name + " not found");
			return null;
		}
		try {
			if (behaviorClass.getInterfaces()[0].equals(BehaviorSimple.class)){
			    Method createMethod = behaviorClass.getMethod("newInstance");
				Object object = createMethod.invoke(null);
				return (BehaviorSimple) object;
			}
			else if (behaviorClass.getInterfaces()[0].equals(BehaviorSingleton.class)){
				Method createMethod = behaviorClass.getMethod("getInstance");
				Object object = createMethod.invoke(null);
				return (BehaviorSingleton) object;
			}
			return null;
		}
		catch (Throwable e) {
			Console.error(e + name + " could not be instantiated");
			return null;
		}
	}
	
	public void terminate(){
		if (behavior != null)
			behavior.terminate();
	}
}
