package tafat.engine.result.drill;

import java.util.ArrayList;

import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.result.filter.FilterHandler;
import tafat.engine.result.filter.Filter_Serie;
import tafat.engine.result.filter.Filter_Value;
import tafat.engine.result.workbook.Sheet;
import tafat.engine.result.writer.WriteHandler;
import tafat.engine.result.writer.WriteHandlerFactory;
import tafat.engine.result.writer.Write_Serie;
import tafat.engine.result.writer.Write_Value;

public class DrillHandler {

	Drill drillXml;

	ArrayList <FilterHandler> filterSeries = new ArrayList <FilterHandler> ();
	ArrayList <FilterHandler> filterValues = new ArrayList <FilterHandler> ();
	ArrayList<WriteHandler> writers = new ArrayList<WriteHandler> ();
	ArrayList<ModelObject> objects = new ArrayList<ModelObject> ();	
	
	ArrayList <DrillHandler> drills = new ArrayList <DrillHandler> ();
	
	/* Class option*/
	Class<?> classToSearch = null;
	
	public DrillHandler(Drill drill, ArrayList <ModelObject> parentObjects, String selectObjectId, Sheet sheet) {
		configure (drill, parentObjects, selectObjectId, sheet);
	}

	public DrillHandler(Drill drill, ModelObject parentObject, String selectObjectId, Sheet sheet) {
		ArrayList<ModelObject> parent = new ArrayList<ModelObject> ();
		parent.add(parentObject);
		configure (drill, parent, selectObjectId, sheet);
	}

	private void configure(Drill drill, ArrayList <ModelObject> parentObjects, String selectObjectId, Sheet sheet){
		drillXml = drill;
		
		if (drillXml.getTypeClass() != null){
			try {
				classToSearch = Class.forName("tafat.metamodel.entity." + drillXml.getTypeClass());
			} catch (ClassNotFoundException e) {
				Console.error("Class " + drillXml.getTypeClass() + " does not exist");
			}
			
			for (Filter_Serie filter : drillXml.getFilter_Serie())
				filterSeries.add(new FilterHandler (filter, classToSearch));
			
			for (Filter_Value filter : drillXml.getFilter_Value())
				filterValues.add(new FilterHandler (filter, classToSearch));
			
			ArrayList <ModelObject> objectList = new ArrayList <ModelObject> ();  
			for (ModelObject object : parentObjects){
				if ((drill.getRecursive().toLowerCase().equals("yes")) || (drill.getRecursive().toLowerCase().equals("true")))			
					objectList.addAll(object.collect(classToSearch, true));
				else
					objectList.addAll(object.collect(classToSearch, false));
			}
			
			boolean passFilters = true;
			for (ModelObject object : objectList){
				for (FilterHandler filter : filterValues)
					if (!filter.passFilter(object)){
						passFilters = false;
						break;
					}
				if (passFilters)
					objects.add(object);
			}						
		}
		if (objects.size() > 0){
			processWrites(sheet, selectObjectId);
			for (Drill subDrill : drillXml.getDrillList())
				drills.add(new DrillHandler(subDrill, objects, selectObjectId, sheet));
		}
	}
	private void processWrites(Sheet sheet, String selectObjectId) {
		ArrayList<String> attributesSorted = sortAttributes();
		WriteHandlerFactory factory = new WriteHandlerFactory ();
		
		for (String attribute : attributesSorted){
			boolean found = false;
			for (Write_Value write : drillXml.getWrite_Value())
				if (attribute.equals(write.getAttribute() + "@" + write.getType())){
					writers.add(factory.getWriteHandler(write, objects, sheet, selectObjectId));
					found = true;
					break;
				}
			if (found)
				continue;
			for (Write_Serie write : drillXml.getWrite_Serie())
				if (attribute.equals(write.getAttribute() + "@" + write.getType())){
					writers.add(factory.getWriteHandler(write, objects, sheet, selectObjectId));
					break;
				}
		}
	}

	private ArrayList<String> sortAttributes() {
		ArrayList<String> toReturn = new ArrayList<String> ();
		
		for (Write_Value write : drillXml.getWrite_Value())
			if (write.getType().toLowerCase().equals("average"))
				toReturn.add(write.getAttribute() + "@" + "average");
		
		for (Write_Serie write : drillXml.getWrite_Serie())
			if (write.getType().toLowerCase().equals("average"))
				toReturn.add(write.getAttribute() + "@" + "average");
		
		for (Write_Value write : drillXml.getWrite_Value())
			if (write.getType().toLowerCase().equals("sum"))
				toReturn.add(write.getAttribute() + "@" + "sum");
		
		for (Write_Serie write : drillXml.getWrite_Serie())
			if (write.getType().toLowerCase().equals("sum"))
				toReturn.add(write.getAttribute() + "@" + "sum");
		
		for (Write_Value write : drillXml.getWrite_Value())
			if (write.getType().toLowerCase().equals("all"))
				toReturn.add(write.getAttribute() + "@" + "all");
		
		for (Write_Serie write : drillXml.getWrite_Serie())
			if (write.getType().toLowerCase().equals("all"))
				toReturn.add(write.getAttribute() + "@" + "all");

		return toReturn;
	}

	public void terminate() {
		for (WriteHandler writer : writers)
			writer.terminate();
		for (DrillHandler drill : drills)
			drill.terminate();
	}
	


	public void tick(Sheet sheet){
		
		for (WriteHandler writer : writers)
			writer.tick(filterSeries, sheet);
		for (DrillHandler drill : drills)
			drill.tick(sheet);
		
	}
}
