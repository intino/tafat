package tafat.engine.multithread;

import java.util.ArrayList;

import tafat.engine.ModelObject;

public class SingleThread extends Thread{
	ArrayList <ModelObject> objectsToTick = null;
	Long time;
	
	public SingleThread(ArrayList<ModelObject> objectsToThread, Long time) {
		this.objectsToTick = objectsToThread;
		this.time = time;
	}


	@Override
	public void run(){
		for (ModelObject object : objectsToTick)
			object.tick(time);
	}
}
