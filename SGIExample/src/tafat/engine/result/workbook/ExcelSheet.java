package tafat.engine.result.workbook;

import org.apache.poi.ss.usermodel.Cell;


public class ExcelSheet extends Sheet {

	int currentRow = 0;
	int currentColumn = 0;
	
	org.apache.poi.ss.usermodel.Sheet sheet;

	public ExcelSheet (org.apache.poi.ss.usermodel.Sheet sheet){
		this.sheet = sheet;
		sheet.createRow(currentRow);
	}
	
	@Override
	public void write(String value) {
		Cell cell = sheet.getRow(currentRow).createCell(++currentColumn);
		cell.setCellValue(value);
	}

	@Override
	public void newRow() {
		sheet.createRow(++currentRow);
		currentColumn = 0;
	}
}
