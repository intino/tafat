package tafat.metamodel.behavior;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tafat.engine.*;
import tafat.engine.social.*;
import tafat.engine.timeout.TimeoutHandler;
import tafat.engine.timeout.TimeoutManager;
import tafat.metamodel.entity.*;
import tafat.metamodel.handler.*;
import tafat.metamodel.recipe.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class HouseholdFullBehaviorSocialGroups implements BehaviorSimple {

	private static ArrayList<HouseholdFullBehaviorSocialGroups> instanceList = new ArrayList<HouseholdFullBehaviorSocialGroups>();
	private HouseholdFull household;
	private enum Missions {INITIALIZE, COMPOSEDRECIPESUNDAYS, COMPOSEDRECIPESATURDAYS, COMPOSEDRECIPEWEEKDAYS};
		
	private MissionMaker missionMaker;
	private DecisionMaker decisionMaker;
	private ActionMaker actionMaker;
	private String socialGroup;
	private String filePath;
	private String recipeName;
	private int recipeCount;
	
	public static BehaviorSimple newInstance() {
		HouseholdFullBehaviorSocialGroups behavior = new HouseholdFullBehaviorSocialGroups();
		instanceList.add(behavior);
		return behavior;
	}

	public void init(ModelObject target) {
		if (target instanceof HouseholdFull){ 
			household = (HouseholdFull) target;
			createMissionMaker();
			createDecisionMaker();
			createActionMaker();
			readXmlFile();
		}
		else
			Console.error(target.getFullPath() + " is not a Household");
	}
	public void configure(){
		actionMaker.execute(new OrderAndData (Missions.INITIALIZE.ordinal(), new HouseInitializerRecipe(0)));	
	}
	private void readXmlFile() {
		DocumentBuilder builder;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(filePath);
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String nodeName = node.getNodeName().toLowerCase();
				short nodeType = node.getNodeType();
				if (nodeType == Node.ELEMENT_NODE)
					if (nodeName.equals("socialgroup")){
						String name = node.getAttributes().getNamedItem("id").getNodeValue();
						if (name.equals(socialGroup)){
							recipeName = node.getAttributes().getNamedItem("recipe").getNodeValue();
							recipeCount = Integer.parseInt(node.getAttributes().getNamedItem("count").getNodeValue());
							break;
						}
					}
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/* MISSION MAKER */
	
	private void createMissionMaker() {
		missionMaker = new MissionMaker(){			

			boolean actionInProcess = false;
			
			@Override
			public OrderAndData checkMissions() {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(Time.getInstance().getSimulationDate());
				@SuppressWarnings("deprecation")
				int hour = calendar.getTime().getHours();
				
				if (hour == 4)
					actionInProcess = false;
				if (!actionInProcess){
					if (hour == 6){
						actionInProcess = true;
						return new OrderAndData (Missions.COMPOSEDRECIPEWEEKDAYS.ordinal(), null);
					}
				}
				return null;
			}

			@Override
			public void receiveMessage(Object data) {
			}
		};
	}

	/* DECISION MAKER */
	
	private void createDecisionMaker() {
		decisionMaker = new DecisionMaker(){

			@Override
			public ArrayList<OrderAndData> checkActions(OrderAndData orderAndData) {			
				ArrayList <OrderAndData> orders = new ArrayList <OrderAndData> ();

			int recipeNumber = Tools.randomInRangeInt(0, recipeCount - 1);
				
			if (orderAndData.order == Missions.COMPOSEDRECIPEWEEKDAYS.ordinal()){
				if (recipeName.equals("SingleJuniorRecipe"))
					orders.add(new OrderAndData (orderAndData.order, new SingleJuniorRecipe(recipeNumber)));
				else if (recipeName.equals("SingleSeniorRecipe"))
					orders.add(new OrderAndData (orderAndData.order, new SingleSeniorRecipe(recipeNumber)));
				else if (recipeName.equals("CoupleWithoutChildrenRecipe"))
					orders.add(new OrderAndData (orderAndData.order, new CoupleWithoutChildrenRecipe(recipeNumber)));
				else if (recipeName.equals("FamilyWithChildrenRecipe"))
					orders.add(new OrderAndData (orderAndData.order, new FamilyWithChildrenRecipe(recipeNumber)));
				else if (recipeName.equals("SeniorCoupleRecipe"))
					orders.add(new OrderAndData (orderAndData.order, new SeniorCoupleRecipe(recipeNumber)));				
			}
				return orders;
			}
			
			@Override
			public void receiveMessage(Object data) {
			}
		};
	}

	/* ACTION MAKER */
	
	private void createActionMaker() {
		actionMaker = new ActionMaker(){
				class ApplianceScheduler{
					PowerEquipment powerEquipment;
					int startTime;
					int durationTime;
					
					public ApplianceScheduler(PowerEquipment powerEquipment, int startTime, int durationTime) {
						this.powerEquipment = powerEquipment;
						this.startTime = startTime;
						this.durationTime = durationTime;
					}
				}
				
				ArrayList <ApplianceScheduler> appliancesInUse = new ArrayList <ApplianceScheduler> ();
				//Household household;
				
				@Override
				public OrderAndData execute(OrderAndData orderAndData) {
					Recipe recipe = (Recipe) orderAndData.data;
					int mission = orderAndData.order;
					
					executeRecipe (recipe, mission, 0);
					
					return null;
				}
				
				private void executeRecipe(Recipe recipe, int mission, int recipeStartTime) {


					int startTime = 0;
					int durationTime = 0;
					
					if (recipe.isSynchronized()){
						int[] startRange = recipe.getRecipeLine(0).getStartTimeRange();
						startTime = Tools.randomInRangeInt(startRange[0], startRange[1]) + recipeStartTime;
						
						int[] durationRange = recipe.getRecipeLine(0).getDurationTimeRange();
						durationTime = Tools.randomInRangeInt(durationRange[0], durationRange[1]);
					}
					for (int i = 0; i < recipe.getRecipeLenght(); i++){
						RecipeLine line = recipe.getRecipeLine(i);
						if (recipe.isSynchronized()){
							line.setStartTime(startTime);
							line.setDurationTime(durationTime);
						}
						else{
							int[] startRange = line.getStartTimeRange();
							startTime = Tools.randomInRangeInt(startRange[0], startRange[1]) + recipeStartTime;
							line.setStartTime(startTime);
							
							int[] durationRange = line.getDurationTimeRange();
							durationTime = Tools.randomInRangeInt(durationRange[0], durationRange[1]);
							line.setDurationTime(durationTime);
						}
					    if (line.getPowerConsumer() == AudioHifi.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof AudioHifiFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									AudioHifiFullHandler audioHifiHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										audioHifiHandler = new AudioHifiFullHandler (this, (AudioHifiFull)powerEquipment, mission, false);
									else
										audioHifiHandler = new AudioHifiFullHandler (this, (AudioHifiFull)powerEquipment, mission, true);
									audioHifiHandler.startDevice(line);
									break;
								}
							}
						}
					    else if (line.getPowerConsumer() == CoffeeMaker.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof CoffeeMakerFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									CoffeeMakerFullHandler coffeeMakerHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										coffeeMakerHandler = new CoffeeMakerFullHandler (this, (CoffeeMakerFull)powerEquipment, mission, false);
									else
										coffeeMakerHandler = new CoffeeMakerFullHandler (this, (CoffeeMakerFull)powerEquipment, mission, true);
									coffeeMakerHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == Computer.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof ComputerFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									ComputerFullHandler computerHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										computerHandler = new ComputerFullHandler (this, (ComputerFull)powerEquipment, mission, false);
									else
										computerHandler = new ComputerFullHandler (this, (ComputerFull)powerEquipment, mission, true);
									computerHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == CookingStove.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof CookingStoveFull){									
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;									
									CookingStoveFullHandler cookingStoveHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										cookingStoveHandler = new CookingStoveFullHandler (this, (CookingStoveFull)powerEquipment, mission, false);
									else
										cookingStoveHandler = new CookingStoveFullHandler (this, (CookingStoveFull)powerEquipment, mission, true);
									cookingStoveHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == Dishwasher.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof DishwasherFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									DishwasherFullHandler dishwasherHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										dishwasherHandler = new DishwasherFullHandler (this, (DishwasherFull)powerEquipment, mission, false);
									else
										dishwasherHandler = new DishwasherFullHandler (this, (DishwasherFull)powerEquipment, mission, true);
									dishwasherHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == Dryer.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof DryerFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									DryerFullHandler dryerHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										dryerHandler = new DryerFullHandler (this, (DryerFull)powerEquipment, mission, false);
									else
										dryerHandler = new DryerFullHandler (this, (DryerFull)powerEquipment, mission, true);
									dryerHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == ElectricalTool.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof ElectricalToolFull){
									ElectricalTool electricalTool = (ElectricalToolFull) powerEquipment;
									if (electricalTool.title.equals(line.getSpecialHandling())){
										if (isInUse(powerEquipment, startTime, durationTime))
											continue;	
										ElectricalToolsFullHandler electricalHandler;
										if (i != (recipe.getRecipeLenght() - 1))
											electricalHandler = new ElectricalToolsFullHandler (this, (ElectricalToolFull)powerEquipment, mission, false);
										else
											electricalHandler = new ElectricalToolsFullHandler (this, (ElectricalToolFull)powerEquipment, mission, true);
										electricalHandler.startDevice(line);
										break;
									}
								}
							}
						}
						else if (line.getPowerConsumer() == Freezer.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof FreezerFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									FreezerFullHandler freezerHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										freezerHandler = new FreezerFullHandler (this, (FreezerFull)powerEquipment, mission, false);
									else
										freezerHandler = new FreezerFullHandler (this, (FreezerFull)powerEquipment, mission, true);
									freezerHandler.startDevice(line);
									break;
								}
							}
						}
					    else if (line.getPowerConsumer() == Lighting.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof LightingFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									LightingFullHandler lightingHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										lightingHandler = new LightingFullHandler (this, (LightingFull)powerEquipment, mission, false);
									else
										lightingHandler = new LightingFullHandler (this, (LightingFull)powerEquipment, mission, true);
									lightingHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == Microwave.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof MicrowaveFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									MicrowaveFullHandler microwaveHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										microwaveHandler = new MicrowaveFullHandler (this, (MicrowaveFull)powerEquipment, mission, false);
									else
										microwaveHandler = new MicrowaveFullHandler (this, (MicrowaveFull)powerEquipment, mission, true);
									microwaveHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == Oven.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof OvenFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									OvenFullHandler ovenHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										ovenHandler = new OvenFullHandler (this, (OvenFull)powerEquipment, mission, false);
									else
										ovenHandler = new OvenFullHandler (this, (OvenFull)powerEquipment, mission, true);
									ovenHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == Radiator.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof RadiatorFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									RadiatorFullHandler radiatorHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										radiatorHandler = new RadiatorFullHandler (this, (RadiatorFull)powerEquipment, mission, false);
									else
										radiatorHandler = new RadiatorFullHandler (this, (RadiatorFull)powerEquipment, mission, true);
									radiatorHandler.startDevice(line);
								}
							}
						}
						else if (line.getPowerConsumer() == Refrigerator.class){
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof RefrigeratorFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									RefrigeratorFullHandler refrigeratorHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										refrigeratorHandler = new RefrigeratorFullHandler (this, (RefrigeratorFull)powerEquipment, mission, false);
									else
										refrigeratorHandler = new RefrigeratorFullHandler (this, (RefrigeratorFull)powerEquipment, mission, true);
									refrigeratorHandler.startDevice(line);
									break;
								}
							}
						}
						else if (line.getPowerConsumer() == Tv.class)
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof TvFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									TvFullHandler tvHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										tvHandler = new TvFullHandler (this, (TvFull)powerEquipment, mission, false);
									else
										tvHandler = new TvFullHandler (this, (TvFull)powerEquipment, mission, true);
									tvHandler.startDevice(line);
									break;
								}
							}
						else if (line.getPowerConsumer() == WashingMachine.class)
							for (PowerEquipment powerEquipment : household.powerEquipmentList){
								if (powerEquipment instanceof WashingMachineFull){
									if (isInUse(powerEquipment, startTime, durationTime))
										continue;	
									WashingMachineFullHandler washingMachineHandler;
									if (i != (recipe.getRecipeLenght() - 1))
										washingMachineHandler = new WashingMachineFullHandler (this, (WashingMachineFull)powerEquipment, mission, false);
									else
										washingMachineHandler = new WashingMachineFullHandler (this, (WashingMachineFull)powerEquipment, mission, true);
									washingMachineHandler.startDevice(line);
									break;
								}
							}
						else
							recipeHandler (line, mission, startTime);
					}
				}


				private void recipeHandler(RecipeLine line, int mission, int recipeStartTime) {
					int  recipeNumber = (Integer) line.getSpecialHandling();
					if (line.getPowerConsumer() == CookRecipe.class){
						executeRecipe(new CookRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == EntertainmentRecipe.class){
						executeRecipe(new EntertainmentRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == HouseInitializerRecipe.class){
						executeRecipe(new HouseInitializerRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == LightingOffRecipe.class){
						executeRecipe(new LightingOffRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == LightingOnRecipe.class){
						executeRecipe(new LightingOnRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == UseIronRecipe.class){
						executeRecipe(new UseIronRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == UseVacuumRecipe.class){
						executeRecipe(new UseVacuumRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == UseWashingMachineRecipe.class){
						executeRecipe(new UseWashingMachineRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == WakeUpRecipe.class){
						executeRecipe(new WakeUpRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
					if (line.getPowerConsumer() == CleanClothesRecipe.class){
						executeRecipe(new CleanClothesRecipe(recipeNumber), mission, recipeStartTime);
						return;
					}
				}
				
				public boolean isInUse (PowerEquipment powerEquipment, int startTime, int durationTime){
					for (ApplianceScheduler appliance : appliancesInUse){
						if (appliance.powerEquipment == powerEquipment)
							if (((startTime >= appliance.startTime) && (startTime <= (appliance.startTime + appliance.durationTime))) ||
								(((startTime + durationTime) >= appliance.startTime) && ((startTime + durationTime) <= (appliance.startTime + appliance.durationTime))) ||
								((appliance.startTime <= startTime) && ((appliance.startTime + appliance.durationTime)) >= startTime) ||
								((appliance.startTime <= (startTime + durationTime)) && ((appliance.startTime + appliance.durationTime)) >= (startTime + durationTime))){
								return true;
							}
					}
					appliancesInUse.add(new ApplianceScheduler(powerEquipment, startTime, durationTime));
					return false;
				}
				@Override
				public void createContext(ModelObject object) {
					if (object instanceof HouseholdFull)
						household = (HouseholdFull) object;
					else
						Console.error(object.getFullPath() + " is not a Household");
				}


				@Override
				public void receiveMessage(Object data){
	
				}
				
			};
	}

	double powerAdition = 0;
	int counter = -1;
	private ArrayList<OrderAndData> orders = null;
	
	@Override
	public void tickOn(Long time) {


		OrderAndData order = missionMaker.checkMissions();
		if (order == null) return;
		orders  = decisionMaker.checkActions(order);
		if (orders == null) return;
		
		TimeoutHandler handler = new TimeoutHandler() {
			
			@Override
			public void execute(Object data) {
				for (OrderAndData orderAndData : orders){
					actionMaker.execute(orderAndData);
				}
				orders = null;
			}
		};
		
		TimeoutManager.getInstance().add(Tools.randomInRangeInt(0, 60), handler, null);

	}

	@Override
	public void tickOff(Long time) {
		
	}
	
	public void terminate(){
	}
	
	public void loadAttribute(String name, String value) {
		if (name.equals("socialGroup"))
			socialGroup= value;
		else if (name.equals("filePath"))
			filePath = value;
	}
}
