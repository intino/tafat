package tafat.engine.result.writer;

import java.lang.reflect.Field;
import java.util.ArrayList;

import tafat.engine.BehaviorContainer;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Tools;
import tafat.engine.result.aggregator.AggregationType;
import tafat.engine.result.aggregator.All;
import tafat.engine.result.aggregator.Average;
import tafat.engine.result.aggregator.Defined;
import tafat.engine.result.aggregator.Sum;
import tafat.engine.result.filter.FilterHandler;
import tafat.engine.result.workbook.Sheet;

public abstract class WriteHandler {

	AggregationType aggregationType;
	Field field;
	String type;
	
	Integer precision;
	
	ArrayList<?> elementToWrite = null;
	ArrayList<ModelObject> elementToCheck = null;
	
	protected void configure (String attributeName, String type, String precision, String behavior, ArrayList<ModelObject> objects, Sheet sheet, String selectObjectId) {
		this.elementToCheck = objects;
		if (behavior == null)
			this.elementToWrite = objects;
		else{
			Class<?> classToSearch = null;
			ArrayList<Object> behaviorObjectList = new ArrayList<Object> ();
			try {
				classToSearch = Class.forName("tafat.metamodel.behavior." + behavior);
				for (ModelObject modelElement : objects)
					for (BehaviorContainer behaviorContainer : modelElement.behaviorList)
						if (classToSearch.isInstance(behaviorContainer.behavior))
							behaviorObjectList.add(behaviorContainer.behavior);
				this.elementToWrite = behaviorObjectList;
				if (this.elementToWrite.size() == 0){
					Console.error("The behavior " + behavior + " is not used for the selected object(s): " + objects.get(0).getClass().getSimpleName());
					this.elementToWrite = objects;
				}
			} catch (ClassNotFoundException e) {
					Console.error("The " + behavior + " behavior class does not exist in this simulator");
					return;
			}
		}
		
		this.field = Tools.getField(elementToWrite.get(0).getClass(), attributeName);
		if (field == null)
			Console.error("The field does not exist " + attributeName + "in class " + objects.get(0).getClass().toString());
		
		this.type = type;
		if (precision != null)
			this.precision = Integer.parseInt(precision);
		
//		switch (type){
//		case "all":
//			aggregationType = new All();
//			break;
//		case "sum":
//			aggregationType = new Sum();
//			break;
//		case "average":
//			aggregationType = new Average();
//			break;
//		case "defined":
//			aggregationType = new Defined();
//			break;
//		}
		
		if (type.equals("all"))
			aggregationType = new  All();
		else if (type.equals("sum"))
			aggregationType = new  Sum();
		else if (type.equals("average"))
			aggregationType = new  Average();
		else if (type.equals("defined"))
			aggregationType = new  Defined();
		
		writeHeaders(objects, sheet, selectObjectId);
	}
	
	protected void writeHeaders(ArrayList<ModelObject> objects, Sheet sheet, String selectObjectId) {
		String fieldName = "FieldNotFound";
		if (field != null)
			fieldName = field.getName();
		
		if (type.equals("all") || type.equals("defined")){
			for (ModelObject object : objects){
				String id = object.id;
				if (id.equals("")){
					id = getParentId(object);
					sheet.write(id + "_" + objects.get(0).getClass().getSimpleName() + "_"+ fieldName);
				}
				else
					sheet.write(id + "_"+ fieldName);
			}
		}
		else{
			sheet.write(selectObjectId + "_" +  objects.get(0).getClass().getSimpleName() + "_"+ fieldName + "_" + type);
		}
	}
	
	public String getParentId(ModelObject object) {
		ModelObject aux = object;
		while (aux.getFirstOwner() != null){
			if (!aux.id.equals(""))
				return aux.id;
			aux = aux.getFirstOwner();
		}
		return "unknown";
	}

	public abstract void terminate();

	public abstract void tick(ArrayList<FilterHandler> filters, Sheet sheet);		
}
