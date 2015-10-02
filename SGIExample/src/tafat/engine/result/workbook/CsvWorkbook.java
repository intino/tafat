package tafat.engine.result.workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;


public class CsvWorkbook extends Workbook {

	public CsvWorkbook(String path, Date to) {
		super(path, to);
	}

	@Override
	public Sheet createSheet(String name) {
		CsvSheet sheet = new CsvSheet(name, to);
		sheetList.add(sheet);
		return sheet;
	}

	@Override
	public void save() {
		for (Sheet sheet : sheetList){
			try {
				path = path.replace(".xml", "");
				OutputStream out = new FileOutputStream(path + "_" + ((CsvSheet)sheet).name + ".csv");
				out.write(((CsvSheet)sheet).getContent());
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
