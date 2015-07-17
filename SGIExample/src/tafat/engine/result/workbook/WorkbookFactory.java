package tafat.engine.result.workbook;

import java.util.Date;

import tafat.engine.Console;

public class WorkbookFactory {

	public Workbook getWorkbook (String dataType, String path, Date to){
		if (dataType.equals("xlsx") || dataType.equals("xls"))
			return new ExcelWorkbook (dataType, path, to);
		else if (dataType.equals("csv"))
			return new CsvWorkbook (path, to);
		else{
			Console.error("File extension not recognized for a Excel file or Csv file");
			return null;
		}
	}
}
