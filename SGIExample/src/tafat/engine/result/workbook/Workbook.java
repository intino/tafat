package tafat.engine.result.workbook;

import java.util.ArrayList;
import java.util.Date;

public abstract class Workbook {
	ArrayList<Sheet> sheetList = new ArrayList<Sheet> ();
	String path;
	Date to;
	
	public Workbook (String path, Date to){
		this.path = path;
		this.to = to;
	}
	
	public abstract Sheet createSheet (String name);
	
	public abstract void save();
}
