package tafat.engine.result.writer;

import java.util.ArrayList;

import tafat.engine.ModelObject;
import tafat.engine.result.filter.FilterHandler;
import tafat.engine.result.workbook.Sheet;

public class WriteHandlerSerie extends WriteHandler {

	public WriteHandlerSerie(Write_Serie writer, ArrayList<ModelObject> objects, Sheet sheet, String selectObjectId) {
		configure(writer.getAttribute(), writer.getType(), writer.getPrecision(), writer.getBehavior(), objects, sheet, selectObjectId);
	}

	@Override
	public void terminate() {
	}

	@Override
	public void tick(ArrayList<FilterHandler> filters, Sheet sheet) {
		String[] toWrite = aggregationType.calculate(elementToCheck, elementToWrite, field, filters, precision);
		for (String value : toWrite)
			sheet.write(value);	
	}
}
