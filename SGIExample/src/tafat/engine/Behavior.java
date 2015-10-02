package tafat.engine;

public abstract interface Behavior {
	public abstract void configure();
	public abstract void tickOn(Long time);
	public abstract void tickOff(Long time);
	public abstract void terminate();
	public abstract void loadAttribute(String name, String value);
}

