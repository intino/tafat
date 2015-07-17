package tafat.engine.result.select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import tafat.control.Main;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Time;
import tafat.engine.result.drill.Drill;
import tafat.engine.result.drill.DrillHandler;
import tafat.engine.result.filter.FilterHandler;
import tafat.engine.result.filter.Filter_Serie;
import tafat.engine.result.filter.Filter_Value;
import tafat.engine.result.workbook.Sheet;
import tafat.engine.result.writer.WriteHandler;
import tafat.engine.result.writer.WriteHandlerFactory;
import tafat.engine.result.writer.Write_Serie;
import tafat.engine.result.writer.Write_Value;

public class SelectHandler {

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	Select selectXml;
	Sheet sheet = null;

	ArrayList <FilterHandler> filterSeries = new ArrayList <FilterHandler> ();
	ArrayList <FilterHandler> filterValues = new ArrayList <FilterHandler> ();
	ArrayList<WriteHandler> writers = new ArrayList<WriteHandler> ();
	ArrayList<ModelObject> objects = new ArrayList<ModelObject> ();
	
	ArrayList <DrillHandler> drills = new ArrayList <DrillHandler> ();
	
	/* Class option*/
	Class<?> classToSearch = null;
	
	public SelectHandler(Select select, Sheet sheet) {
		selectXml = select;
		this.sheet = sheet;
		sheet.write("Date");
		sheet.write("Time");
		
		if (selectXml.getTypeClass() != null){
			ArrayList <ModelObject> objectList = null;
			try {
				classToSearch = Class.forName("tafat.metamodel.entity." + selectXml.getTypeClass());
				objectList = Main.scene.collect(classToSearch, true);
			} catch (ClassNotFoundException e) {
				try {
					classToSearch = Class.forName("tafat.metamodel.connection." + selectXml.getTypeClass());
					objectList = Main.topology.collect(classToSearch, true);
				} catch (ClassNotFoundException e1) {
					Console.error("Class " + select.getTypeClass() + " does not exist");
					return;
				}
			}
			
			for (Filter_Serie filter : selectXml.getFilter_Serie())
				filterSeries.add(new FilterHandler (filter, classToSearch));
			
			for (Filter_Value filter : selectXml.getFilter_Value())
				filterValues.add(new FilterHandler (filter, classToSearch));
			
			for (ModelObject object : objectList){
				boolean passFilters = true;
				for (FilterHandler filter : filterValues)
					if (!filter.passFilter(object)){
						passFilters = false;
						break;
					}
				if (passFilters)
					objects.add(object);
			}						
		}
		else if (selectXml.getId() != null){
			objects.add(Main.scene.item.get(selectXml.getId()));
		}
		if (objects.size() > 0){
			processWrites();
			for (Drill drill : selectXml.getDrillList())
				for (ModelObject object : objects)
					drills.add(new DrillHandler(drill, object, object.id, sheet));
//			if (excelSheet == null)
//				csvSheet.newLine();
		}

	}

	private void processWrites() {
		ArrayList<String> attributesSorted = sortAttributes();
		WriteHandlerFactory factory = new WriteHandlerFactory ();
		
		for (String attribute : attributesSorted){
			boolean found = false;
			for (Write_Value write : selectXml.getWrite_Value())
				if (attribute.equals(write.getAttribute() + "@" + write.getType())){
					WriteHandler writeHandler = factory.getWriteHandler(write, objects, sheet, "");
					writers.add(writeHandler);
					found = true;
					break;
				}
			if (found)
				continue;
			for (Write_Serie write : selectXml.getWrite_Serie())
				if (attribute.equals(write.getAttribute() + "@" + write.getType())){
					writers.add(factory.getWriteHandler(write, objects, sheet, ""));
					break;
				}
		}
	}

	private ArrayList<String> sortAttributes() {
		ArrayList<String> toReturn = new ArrayList<String> ();
		
		for (Write_Value write : selectXml.getWrite_Value())
			if (write.getType().toLowerCase().equals("average"))
				toReturn.add(write.getAttribute() + "@" + "average");
		
		for (Write_Serie write : selectXml.getWrite_Serie())
			if (write.getType().toLowerCase().equals("average"))
				toReturn.add(write.getAttribute() + "@" + "average");
		
		for (Write_Value write : selectXml.getWrite_Value())
			if (write.getType().toLowerCase().equals("sum"))
				toReturn.add(write.getAttribute() + "@" + "sum");
		
		for (Write_Serie write : selectXml.getWrite_Serie())
			if (write.getType().toLowerCase().equals("sum"))
				toReturn.add(write.getAttribute() + "@" + "sum");
		
		for (Write_Value write : selectXml.getWrite_Value())
			if (write.getType().toLowerCase().equals("all"))
				toReturn.add(write.getAttribute() + "@" + "all");
		
		for (Write_Serie write : selectXml.getWrite_Serie())
			if (write.getType().toLowerCase().equals("all"))
				toReturn.add(write.getAttribute() + "@" + "all");
		
		for (Write_Value write : selectXml.getWrite_Value())
			if (write.getType().toLowerCase().equals("defined"))
				toReturn.add(write.getAttribute() + "@" + "defined");
		
		for (Write_Serie write : selectXml.getWrite_Serie())
			if (write.getType().toLowerCase().equals("defined"))
				toReturn.add(write.getAttribute() + "@" + "defined");
		
		return toReturn;
	}

	public void terminate() {
//		if (excelSheet != null)
//			for (int i = 0; i < excelSheet.getRow(0).getLastCellNum(); i++)
//				excelSheet.autoSizeColumn(i);
		
		for (WriteHandler writer : writers)
			writer.terminate();
		for (DrillHandler drill : drills)
			drill.terminate();
		
//		dateFormat = null;
//		timeFormat = null;
//		
//		selectXml = null;
//		excelSheet = null;
//		csvSheet.terminate();
//		csvSheet = null;
//
//		filterSeries.clear();
//		filterValues.clear();
//		writers.clear();
//		objects.clear();
	}
	


	public void tick(){
		sheet.newRow();
		sheet.write(dateFormat.format(Time.getInstance().getSimulationDate()));
		sheet.write(timeFormat.format(Time.getInstance().getSimulationDate()));
		
		for (WriteHandler writer : writers)
			writer.tick(filterSeries, sheet);
		
		for (DrillHandler drill : drills)
			drill.tick(sheet);
	}
}
