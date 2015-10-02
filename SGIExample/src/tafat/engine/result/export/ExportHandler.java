package tafat.engine.result.export;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.simpleframework.xml.Root;

import tafat.engine.result.ResultHandler;
import tafat.engine.result.select.Select;
import tafat.engine.result.select.SelectHandler;
import tafat.engine.result.workbook.Sheet;
import tafat.engine.result.workbook.Workbook;
import tafat.engine.result.workbook.WorkbookFactory;

@Root
public abstract class ExportHandler {
	
	public Export exportXml;
	Workbook workbook = null;
	WorkbookFactory workbookFactory = new WorkbookFactory();
	
	ArrayList<SelectHandler> select_HandlerList = new ArrayList<SelectHandler> ();
	
	public ExportHandler(Export export) {
		exportXml = export;
		File exportFolder = new File (ResultHandler.basePath + exportXml.getName());
		exportFolder.mkdir();
	}
	
	public void create (String path, Date to){
		workbook = workbookFactory.getWorkbook(exportXml.getDataType(), path, to);
		for (int i = 0; i < exportXml.getSelectList().size(); i++){
			Select select = exportXml.getSelectList().get(i);
			Sheet sheet = workbook.createSheet(select.getName());
			select_HandlerList.add(new SelectHandler(select, sheet));
		}
	}

	public void terminate() {
		workbook.save();
		for (SelectHandler select : select_HandlerList){
			select.terminate();
		}
		select_HandlerList.clear();
	}

	public abstract void activate();
	public abstract void tick();
}
