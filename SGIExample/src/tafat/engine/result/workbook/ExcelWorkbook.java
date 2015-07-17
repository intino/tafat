package tafat.engine.result.workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWorkbook extends Workbook {
	
	org.apache.poi.ss.usermodel.Workbook workbook;

	public ExcelWorkbook(String dataType, String path, Date to) {
		super(path, to);
		if (dataType.equals("xlsx"))
			workbook = new XSSFWorkbook();
		else if (dataType.equals("xls"))
			workbook = new HSSFWorkbook();
	}

	@Override
	public Sheet createSheet(String name) {
		Sheet sheet = new ExcelSheet (workbook.createSheet(name));
		sheetList.add(sheet);
		return sheet;
	}

	@Override
	public void save() {
		path = path.replace(".xml", "");
		OutputStream out = null;
		try {
			if (workbook instanceof XSSFWorkbook)
				out = new FileOutputStream(path + ".xlsx");
			else
				out = new FileOutputStream(path + ".xls");
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
