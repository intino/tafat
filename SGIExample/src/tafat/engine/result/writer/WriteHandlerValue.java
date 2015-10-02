package tafat.engine.result.writer;

import java.util.ArrayList;

import tafat.engine.ModelObject;
import tafat.engine.result.filter.FilterHandler;
import tafat.engine.result.workbook.Sheet;

public class WriteHandlerValue extends WriteHandler {
	boolean written = false;
	int numberOfElements;
	
	public WriteHandlerValue(Write_Value writer, ArrayList<ModelObject> objects, Sheet sheet, String selectObjectId) {
		configure(writer.getAttribute(), writer.getType(), writer.getPrecision(), writer.getBehavior(), objects, sheet, selectObjectId);
	}

	@Override
	public void terminate() {
	}

	@Override
	public void tick(ArrayList<FilterHandler> filters, Sheet sheet) {
		if (!written){
			String[] toWrite = aggregationType.calculate(elementToCheck, elementToWrite, field, filters, precision);
			for (String value : toWrite)
				sheet.write(value);
			numberOfElements = toWrite.length;
			written = true;
		}
		else
			for (int i = 0; i < numberOfElements; i++)
				sheet.write("");
	}

}
