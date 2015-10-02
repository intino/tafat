package tafat.engine.result.export;

import java.io.File;
import java.util.Date;

import tafat.engine.Time;
import tafat.engine.result.ResultHandler;
import tafat.engine.result.select.SelectHandler;
import tafat.engine.result.splitter.SplitCalculator;
import tafat.engine.result.splitter.SplitCalculatorFactory;

public class ExportHandlerSplitted extends ExportHandler {

	long nextSplit;
	SplitCalculator splitCalculator;
	String currentPeriod;
	
	public ExportHandlerSplitted(Export export) {
		super(export);
	}
	
	

	@Override
	public void tick() {
		if (nextSplit - Time.getInstance().getSimulationDateInLong()  <= 0){
			nextSplit = splitCalculator.calculateNextSplit();
			workbook.save();
			for (SelectHandler select : select_HandlerList)
				select.terminate();
			select_HandlerList.clear();

			File exportFolder = new File (ResultHandler.basePath + exportXml.getName() + "/" + splitCalculator.getCurrentPeriod() + "/");
			exportFolder.mkdir();
			
			create(ResultHandler.basePath + exportXml.getName() + "/" + splitCalculator.getCurrentPeriod() + "/" + exportXml.getName(), new Date (nextSplit));
		}
		for (SelectHandler select : select_HandlerList)
			select.tick();
	}



	@Override
	public void activate() {
		SplitCalculatorFactory splitCalculatorFactory = new SplitCalculatorFactory ();
		splitCalculator = splitCalculatorFactory.getSplitCalculator(exportXml.getSplitBy());
		nextSplit = splitCalculator.calculateNextSplit();
		
		File exportFolder = new File (ResultHandler.basePath + exportXml.getName() + "/" + splitCalculator.getCurrentPeriod() + "/");
		exportFolder.mkdir();
		
		create(ResultHandler.basePath + exportXml.getName() + "/" + splitCalculator.getCurrentPeriod() + "/" + exportXml.getName(), new Date (nextSplit));
	}

}
