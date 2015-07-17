package tafat.engine;

import java.util.ArrayList;

import org.apache.commons.math.util.MathUtils;

public class StepCalculator {
	long gdcTotalObjects;
	
	ArrayList <Long> steps = new ArrayList <Long> ();
	
	public StepCalculator (ModelObject scene){
		ArrayList <ModelObject> sceneObjects = scene.collect(Object.class, true);
		
		gdcTotalObjects = initialize(sceneObjects);
		
		for (ModelObject object : sceneObjects)
			for (BehaviorContainer behavior : object.behaviorList)
				gdcTotalObjects = MathUtils.gcd (gdcTotalObjects, behavior.step);
	}

	private long initialize(ArrayList<ModelObject> sceneObjects) {
		for (ModelObject object : sceneObjects){
			for (BehaviorContainer behavior : object.behaviorList){
				return behavior.step;
			}
		}
		return 10000;
	}
	
	public long getStep(){
		return gdcTotalObjects;
	}

}
