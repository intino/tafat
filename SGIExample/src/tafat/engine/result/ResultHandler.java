package tafat.engine.result;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import tafat.control.Main;
import tafat.engine.Time;
import tafat.engine.result.export.Export;
import tafat.engine.result.export.ExportHandler;
import tafat.engine.result.export.ExportHandlerContinued;
import tafat.engine.result.export.ExportHandlerSplitted;

public class ResultHandler {
	public static String basePath;
	
	Result result;
	// Undefined from and to in these exports
	ArrayList<ExportCountdown> export_HandlerList = new ArrayList<ExportCountdown> ();
	
	// Defined from and to in these exports
	ArrayList<ExportCountdown> activeExport_HandlerList = new ArrayList<ExportCountdown> ();
	ArrayList<ExportCountdown> idleExport_HandlerList = new ArrayList<ExportCountdown> ();

	private class ExportCountdown {
		ExportHandler exportHandler;
		Long from;
		Long to;
		int step;
		int countdown;
		
		public ExportCountdown (ExportHandler exportHandler){
			this.exportHandler = exportHandler;
			if (exportHandler.exportXml.getFrom() != null)
				from = exportHandler.exportXml.getFrom().getTime();
			if (exportHandler.exportXml.getTo() != null)
				to = exportHandler.exportXml.getTo().getTime();
			step = exportHandler.exportXml.getStep();
			countdown = 0;
		}
	}
	
	public ResultHandler(String fileXmlPath){
		File model = new File (Main.modelPath);
		File folderModelResults = new File (model.getName().replace(".xml", "/"));
		folderModelResults.mkdir();
		File folderSimulationResults = new File (basePath = folderModelResults.getPath() + "/" + new Date ().toString().replace(":", ".") + "/");
		folderSimulationResults.mkdir();
		
		Serializer serializer = new Persister();
		File source = new File(fileXmlPath);
		try {
			result = serializer.read(Result.class, source);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for (Export export: result.getExportList())
			if (export.getSplitBy() == null)
				if (export.getFrom() == null || export.getTo() == null)
					export_HandlerList.add(new ExportCountdown(new ExportHandlerContinued (export)));
				else
					idleExport_HandlerList.add(new ExportCountdown (new ExportHandlerContinued (export)));
			else
				if (export.getFrom() == null || export.getTo() == null)
					export_HandlerList.add(new ExportCountdown (new ExportHandlerSplitted (export)));
				else
					idleExport_HandlerList.add(new ExportCountdown (new ExportHandlerSplitted (export)));
		for (ExportCountdown export : export_HandlerList)
			export.exportHandler.activate();
	}

	public void terminate() {
		for (ExportCountdown export: export_HandlerList)
			export.exportHandler.terminate();
		
		for (ExportCountdown export: activeExport_HandlerList)
			export.exportHandler.terminate();
		
		export_HandlerList.clear();
		idleExport_HandlerList.clear();
		activeExport_HandlerList.clear();
	}

	public void tick() {
		for (ExportCountdown export: export_HandlerList)
			if (export.countdown == 0){
				export.countdown = export.step - 1;
				export.exportHandler.tick();
			}
			else
				export.countdown--;

		ArrayList<ExportCountdown> toRemoveFromIdle = new ArrayList<ExportCountdown> ();
		for (ExportCountdown export: idleExport_HandlerList){
			if (Time.getInstance().getSimulationDateInLong() - export.from >= 0){
				activeExport_HandlerList.add(export);
				export.exportHandler.activate();
				toRemoveFromIdle.add(export);
			}
		}
		
		for (ExportCountdown export : toRemoveFromIdle)
			idleExport_HandlerList.remove(export);
			
		ArrayList<ExportCountdown> toRemoveFromActive = new ArrayList<ExportCountdown> ();
		for (ExportCountdown export: activeExport_HandlerList){
			if (Time.getInstance().getSimulationDateInLong()- export.to < 0)
				if (export.countdown == 0){
					export.countdown = export.step - 1;
					export.exportHandler.tick();
				}
				else
					export.countdown--;
			else{
				export.exportHandler.terminate();
				toRemoveFromActive.add(export);
			}
		}
		
		for (ExportCountdown export : toRemoveFromActive)
			activeExport_HandlerList.remove(export);
	}
}
