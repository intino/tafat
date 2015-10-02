package tafat.engine.result.export;

import java.io.File;

import tafat.engine.result.ResultHandler;
import tafat.engine.result.select.SelectHandler;


public class ExportHandlerContinued extends ExportHandler {

	public ExportHandlerContinued(Export export) {
		super(export);
		File exportFolder = new File (ResultHandler.basePath + exportXml.getName() + "/");
		exportFolder.mkdir();
		
		create(ResultHandler.basePath + exportXml.getName() + "/" + exportXml.getName(), export.getTo());
	}

	@Override
	public void tick() {
		for (SelectHandler select : select_HandlerList)
			select.tick();
	}

	@Override
	public void activate() {
	}
}
