package tafat.control;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tafat.engine.*;
import tafat.engine.cache.CacheHandler;
import tafat.engine.result.ResultHandler;
import tafat.engine.statechart.StatechartUpdater;
import tafat.engine.systemdynamic.SystemDynamicManager;
import tafat.engine.timeout.TimeoutManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Main implements Control {

	static private Main instance = new Main();
	
	public static boolean debugMode = false;
	
	public static String modelPath;
	private static Date from = null;
	private static Date to = null;
	
	public static Scene scene = new Scene();
	public static Topology topology = new Topology();
	public static ResultHandler result_Handler = null;
	
	public static int occurrences = 1;
	private boolean running = false;
	private SimulationViewer simulationViewer;

	public static void main(String args[]) {

		Console.out("Heap size " + Runtime.getRuntime().totalMemory());

		if (args.length > 0)
			modelPath = args[0];
		else
			modelPath = "model.xml";

		DocumentBuilder builder;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		
		try {			
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(modelPath);
			
			if (document.getChildNodes().item(0).getAttributes().getNamedItem("timeZone") != null)
				TimeZone.setDefault(new SimpleTimeZone(0,document.getChildNodes().item(0).getAttributes().getNamedItem("timeZone").getNodeValue()));
			
			/* GET OCCURRENCES */
			if (document.getChildNodes().item(0).getAttributes().getNamedItem("occurrences") != null)
				occurrences = Integer.parseInt(document.getChildNodes().item(0).getAttributes().getNamedItem("occurrences").getNodeValue());
			if (document.getChildNodes().item(0).getAttributes().getNamedItem("from") != null)
				from = DateParser.parseDateAndTime(document.getChildNodes().item(0).getAttributes().getNamedItem("from").getNodeValue());
			else{
				Console.error("Initial time not set");
				return;
			}
			if (document.getChildNodes().item(0).getAttributes().getNamedItem("to") != null)
				to = DateParser.parseDateAndTime(document.getChildNodes().item(0).getAttributes().getNamedItem("to").getNodeValue());
			else{
				Console.error("Final time not set");
				return;
			}			
		} catch (SAXException | IOException | DOMException | ParserConfigurationException | ParseException e) {
			e.printStackTrace();
		}


		for (int occurrence = 0; occurrence < occurrences; occurrence++){
			Time.createInstance(from, to);
			instance.loadModel();
			instance.execute();
			Time.clear();
		}
	}
	
	private void loadModel() {
		DocumentBuilder builder;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();


		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(modelPath);
			NodeList nodeList = document.getDocumentElement().getChildNodes();
					
			TimeoutManager.clear();
			StatechartUpdater.clear();
			scene = null;
			topology = null;
			scene = new Scene();
			topology = new Topology();
			if (CacheHandler.getInstance() != null)
				CacheHandler.getInstance().clear();
			CacheHandler.createInstance();
			
			TimeoutManager.createInstance();
			StatechartUpdater.createInstance();
			SystemDynamicManager.createInstance();
			
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String nodeName = node.getNodeName().toLowerCase();
				short nodeType = node.getNodeType();
				if (nodeType == Node.ELEMENT_NODE){
					switch (nodeName) {
						case "scene":
							scene.load(node);
							break;
						case "topology":
							topology.load(node);
							break;
						case "result":
							if (node.getAttributes().getNamedItem("filename") != null)
								result_Handler = new ResultHandler(node.getAttributes().getNamedItem("filename").getNodeValue());
							break;
					}
				}
			}
			Console.out("Model loaded");
		} catch (ParseException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void execute() {
			Date initialDate = new Date();
			
			Console.out("Starting simulation " + initialDate.toString());
			
			TimeoutManager timeoutManagerInstance = TimeoutManager.getInstance();
			StatechartUpdater statechartUpdaterInstance = StatechartUpdater.getInstance();
			SystemDynamicManager systemDynamicManager = SystemDynamicManager.getInstance();
			
			scene.configure();
			topology.configure();
			
			int percentage = 1;
			long next = (percentage * Time.getInstance().getSimulationDurationTicks()) / 100;
			StepCalculator stepCalculator = new StepCalculator(scene);
			long stepCountdown = 0;
			Console.out("Steps: " + stepCalculator.getStep());
			Console.out("Simulation starts on: " + Time.getInstance().getInitialSimulationDate() +
					    " and finish on: " + Time.getInstance().getFinishSimulationDate());

			simulationViewer = new SimulationViewer(instance, scene);

			/* SIMULATION DURATION */
			for (long i=0; i < Time.getInstance().getSimulationDurationTicks(); i++) {
				if(!running) waitForPlay();

				Time.getInstance().tick();
				if (i > next){
					Console.out("Simulation percentage: " + percentage++ + ". Current time: " + new Date () + ". Simulation time: " + Time.getInstance().getSimulationDate());

					next = (percentage * Time.getInstance().getSimulationDurationTicks()) / 100;
				}
				timeoutManagerInstance.tick();
				statechartUpdaterInstance.tick();
				
				systemDynamicManager.calculate();
				
				if (stepCountdown == 0){
					stepCountdown = stepCalculator.getStep();
					scene.tick(i); // Tick on and off in order (first all the tick on and later the tick off for all behaviors)
//					scene.fastTick(i); // Tick on  and tick off executed sequentially for each behavior
					
//					multithreadHandler.tick(i);
					
//					topology.tick(i);
//					topology.fastTick(i);
				}
				stepCountdown--;
				


				if (result_Handler != null)
					result_Handler.tick();

				if(i%100 == 0) {
					simulationViewer.refresh(Time.getInstance().getSimulationDate());

				}
			}
			
			scene.terminate();
			if (result_Handler != null)
				result_Handler.terminate();
			
			Date finalDate  = new Date();
			Console.out("Simulation finished " + finalDate.toString() + ". Simulation duration (s) = " + ((finalDate.getTime() - initialDate.getTime())/1000));
//		} catch (SAXException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}



	}

	private void waitForPlay() {
		while(true){
			try {
				Thread.sleep(500);
				simulationViewer.refresh(Time.getInstance().getSimulationDate());
				if(running) break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Date initialDate() {
		return instance.from;
	}

	@Override
	public Date finalDate() {
		return instance.to;
	}

	@Override
	public void play() {
		running = true;
	}

	@Override
	public void pause() {
		running = false;
	}

	@Override
	public void reset() {
		loadModel();
		running = false;
	}
}