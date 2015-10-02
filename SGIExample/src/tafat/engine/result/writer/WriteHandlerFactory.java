package tafat.engine.result.writer;

import java.util.ArrayList;

import tafat.engine.ModelObject;
import tafat.engine.result.workbook.Sheet;

public class WriteHandlerFactory {

	public WriteHandler getWriteHandler (Write_Serie writer, ArrayList<ModelObject> objects, Sheet sheet, String selectObjectId){
		return new WriteHandlerSerie(writer, objects, sheet, selectObjectId);		
	}
	
	public WriteHandler getWriteHandler (Write_Value writer, ArrayList<ModelObject> objects, Sheet sheet, String selectObjectId){
		return new WriteHandlerValue(writer, objects, sheet, selectObjectId);		
	}
}
