package tafat.metamodel.behavior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.opt4j.common.archive.CrowdingArchive;
import org.opt4j.common.random.RandomJava;
import org.opt4j.core.Individual;
import org.opt4j.core.Objective;
import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.Phenotype;
import org.opt4j.core.optimizer.Archive;
import org.opt4j.core.optimizer.Control;
import org.opt4j.core.optimizer.IndividualCompleter;
import org.opt4j.core.optimizer.Iteration;
import org.opt4j.core.optimizer.Population;
import org.opt4j.core.optimizer.StopException;
import org.opt4j.core.optimizer.TerminationException;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.problem.Decoder;
import org.opt4j.core.problem.Evaluator;
import org.opt4j.genotype.DoubleGenotype;
import org.opt4j.operator.mutate.ConstantMutationRate;
import org.opt4j.operator.mutate.MutationRate;
import org.opt4j.operator.normalize.NormalizeDoubleBorder;
import org.opt4j.optimizer.mopso.MOPSO;
import org.opt4j.optimizer.mopso.MutateDoubleNonUniform;
import org.opt4j.optimizer.mopso.MutateDoubleUniform;
import org.opt4j.optimizer.mopso.Particle;
import org.opt4j.optimizer.mopso.ParticleFactory;

import tafat.engine.BehaviorSimple;
import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Notification;
import tafat.engine.Time;
import tafat.engine.Tools;
import tafat.metamodel.connection.PowerConnection;
import tafat.metamodel.connection.PowerConnectionFull;
import tafat.metamodel.entity.ControlUnitFull;
import tafat.metamodel.entity.FreezerFull;
import tafat.metamodel.entity.LightingFull;
import tafat.metamodel.entity.RadiatorFull;
import tafat.metamodel.entity.RefrigeratorFull;
import tafat.metamodel.entity.TankWaterHeaterFull;

import com.google.inject.Provider;


public class ControlUnitFullBehaviorPSO implements BehaviorSimple {
	// CONSTANTS!
	final int LIGHTING_MAX_SHIFT_TIME = 60 * Time.getInstance().minute;
	int LIGHTING_MAX_SHIFT_SLOTS;
//	final int LIGHTING_MIN_RECOVER_SLOTS = 240; // 240 * step -> 7200 (2h)
	final int LIGHTING_MIN_RECOVER_RATE= 2; // Rate * shifted time
	
	final int REFRIGERATOR_MAX_SHIFT_TIME = 30 * Time.getInstance().minute;
	int REFRIGERATOR_MAX_SHIFT_SLOTS; // 40 * step -> 1200 (20 min)
//	final int REFRIGERATOR_MIN_RECOVER_SLOTS = 120; // 120 * step -> 1200 (1h)
	final int REFRIGERATOR_MIN_RECOVER_RATE = 2; // Rate * shifted time
	
	final int FREEZER_MAX_SHIFT_TIME =  30 * Time.getInstance().minute;
	int FREEZER_MAX_SHIFT_SLOTS; // 20 * step -> 1200 (10 min)
//	final int FREEZER_MIN_RECOVER_SLOTS = 60; // 120 * step -> 1200 (30 min)
	final int FREEZER_MIN_RECOVER_RATE = 2; // Rate * shifted time
	
	final int MAX_PILOTING_COUNTER_DISTANCE = 6;
	
	Notification currentNotification = null;
	
	MOPSO mopso = null;
	Control control = null;
	Population population = null;
	Archive archive = null;
	final int iterations = 30;
	final int populationSize = 30;
	
	private int step = 1;
	
	double powerError = 0;
	int psoCounter = 0;
	
	Objective objective1 = new Objective("Power", Sign.MIN, Objective.RANK_OBJECTIVE);
	Objective objective2 = new Objective("PilotCounting", Sign.MAX, Objective.RANK_OBJECTIVE);				
	Objective objective3 = new Objective("Stability", Sign.MIN, Objective.RANK_OBJECTIVE);

	private double currentOrderPower = 0.;
	private boolean orderInProcess = false;
	
	int timeBetweenPSO = 10 * Time.getInstance().minute; // seconds
	int lastPSOExecutionTime = 0; // seconds
	
	
	History history = null;
	ArrayList<ModelObject> controllableElements = new ArrayList<ModelObject> ();
	
	ArrayList<RefrigeratorFull> refrigeratorList = new ArrayList<RefrigeratorFull>();
	ArrayList<FreezerFull> freezerList = new ArrayList<FreezerFull>();
	ArrayList<LightingFull> lightingList = new ArrayList<LightingFull>();
	ArrayList<TankWaterHeaterFull> tankWaterHeaterList = new ArrayList<TankWaterHeaterFull>();
	ArrayList<RadiatorFull> radiatorList = new ArrayList<RadiatorFull>();
	
	private static ArrayList<ControlUnitFullBehaviorPSO> instanceList = new ArrayList<ControlUnitFullBehaviorPSO>();

	private ControlUnitFull controlUnit;
	
	public static BehaviorSimple newInstance() {
		ControlUnitFullBehaviorPSO behavior = new ControlUnitFullBehaviorPSO();
		instanceList.add(behavior);
		return behavior;
	}

	public void init(ModelObject target) {
		if (target instanceof ControlUnitFull){ 
			controlUnit = (ControlUnitFull) target;
			LIGHTING_MAX_SHIFT_SLOTS = (int) (LIGHTING_MAX_SHIFT_TIME / step);
			REFRIGERATOR_MAX_SHIFT_SLOTS = (int) (REFRIGERATOR_MAX_SHIFT_TIME / step);
			FREEZER_MAX_SHIFT_SLOTS = (int) (FREEZER_MAX_SHIFT_TIME / step);
		}
		else
			Console.error(target.getFullPath() + " is not a ControlUnit");
	}

	@Override
	public void configure() {
	}
	private void updateHistory() {
		if (controllableElements.size() > 0){
			history.maxTimesPiloted = Integer.MIN_VALUE; history.minTimesPiloted = Integer.MAX_VALUE;
			for (HistoryElement element : history.elements){
				if (element.pilotingCounter > history.maxTimesPiloted)
					history.maxTimesPiloted = element.pilotingCounter;
				else if (element.pilotingCounter < history.minTimesPiloted)
					history.minTimesPiloted = element.pilotingCounter;
			}
			return;
		}
		controllableElements = new ArrayList<ModelObject> ();
		for (LightingFull lighting : lightingList)
			controllableElements.add((ModelObject) lighting);
		for (RefrigeratorFull refrigerator : refrigeratorList)
			controllableElements.add((ModelObject) refrigerator);
		for (FreezerFull freezer : freezerList)
			controllableElements.add((ModelObject) freezer);
		history = new History(controllableElements);
	}

	private void createPSO() {
		ParticleFactory particleFactory = new ParticleFactory (new MyProvider (), new MyCreator (history));
		IndividualCompleter individualCompleter = new MyIndividualCompleter(history, controlUnit);
		MutateDoubleUniform mutationDoubleUniform = new MutateDoubleUniform(new RandomJava((long) Math.random()), new NormalizeDoubleBorder(), 0);
		Iteration iteration = new Iteration (iterations);
		MutateDoubleNonUniform mutationDoubleNonUniform = new MutateDoubleNonUniform(new RandomJava((long) Math.random()), new NormalizeDoubleBorder(), iteration, 0);
		MutationRate mutationRate = new ConstantMutationRate(1. / (double)history.elements.size());
		mopso = new MOPSO(population, archive, particleFactory, 
				          individualCompleter, control = new Control (), new RandomJava((long) Math.random()), 
				          mutationDoubleUniform, mutationDoubleNonUniform, mutationRate,
				          iteration, populationSize, 100);
	}

	@Override
	public void tickOn(Long time) {
		checkNotifications();
		updateHistory();
		
		if (controlUnit == null) return;
			controlUnit.realConsumption = getRealConsumption();
		
		if (currentNotification != null){
			if (currentNotification.notify.equals("MODIFY")){
				population = null; population = new Population ();
				archive = null; archive = new MyArchive1(100);
				
				lastPSOExecutionTime = step;
				
				currentOrderPower = ((Double)currentNotification.data);
				controlUnit.aimConsumption = controlUnit.realConsumption + currentOrderPower;
				
				Individual bestParticle = usePSO();
				for (int i = 0; i < bestParticle.getGenotype().size(); i++)
					configureAndRecord(history.elements.get(i), ((DoubleGenotype) bestParticle.getGenotype()).get(i), ((MyPhenotype)bestParticle.getPhenotype()).powerChangeList.get(i));
				
				printStatistics(bestParticle);
				
				orderInProcess = true;
			}
			else if (currentNotification.notify.equals("RESTORE")){
				orderInProcess = false;
				
				for (int i = 0; i < history.elements.size(); i++)
					configureAndRecord(history.elements.get(i), 1., 0.);
			}
			
			currentNotification = null;
		}
		else if (orderInProcess && lastPSOExecutionTime == timeBetweenPSO){
			lastPSOExecutionTime = 30;
			
			population = null; population = new Population ();
			
			ArrayList<Individual> sortedByPower = sortArchiveByObjective(archive, objective1);
			for (int i = 0 ; i < sortedByPower.size(); i++)
				if (i < (populationSize / 2))
					population.add(sortedByPower.get(i));
				else 
					break;
			
			controlUnit.aimConsumption = controlUnit.realConsumption + 0;
			Individual bestParticle = usePSO();			
			for (int i = 0; i < bestParticle.getGenotype().size(); i++)
				configureAndRecord(history.elements.get(i), ((DoubleGenotype) bestParticle.getGenotype()).get(i), ((MyPhenotype)bestParticle.getPhenotype()).powerChangeList.get(i));
			
			printStatistics(bestParticle);
		}
		else{
			lastPSOExecutionTime += 30;
			for (HistoryElement element : history.elements)
				element.addHistorical(element.getLastState());
		}
	}
	
	private void printStatistics(Individual particle) {
		try{
			Console.out("\nBEST - " + particle.getObjectives().toString() + " " + Time.getInstance().getSimulationDate().toString() + " Modify: " + currentOrderPower + " CU Id: " + controlUnit.id);
		}
		catch (NullPointerException e){ Console.out("BEST NOT FOUND");}
		String pilotedCounter = "";
		int max = Integer.MIN_VALUE; int min = Integer.MAX_VALUE;
		double average = 0;
		double[] allValues = new double[history.elements.size()];
		int index = 0;
		for (HistoryElement element : history.elements){
			pilotedCounter += element.pilotingCounter + ";";
			if (element.pilotingCounter > max)
				max = element.pilotingCounter;
			if (element.pilotingCounter < min)
				min = element.pilotingCounter;
			average += element.pilotingCounter;
			allValues[index++] = element.pilotingCounter;
		}
		average /= 265.;
		double stDev = new StandardDeviation().evaluate(allValues, average);
		Console.out("History : max -> " + max + " min -> " + min + " average -> " + average + " STDEV -> " + stDev);
		Console.out(pilotedCounter + "\n");
	}

	private Individual usePSO() {
		try {
			createPSO();
			archive.clear();
			mopso.optimize();
			ArrayList<Individual> sortedByPower = sortArchiveByObjective(archive, objective1);
			Individual bestParticle = sortedByPower.get(0);
			for (Individual individual : sortedByPower){
				try{
					Console.out(individual.getObjectives().toString() + " " + Time.getInstance().getSimulationDate().toString() + " Reduce: " + reduce);
				}
				catch (NullPointerException e){ break;}
				if (individual.getObjectives().get(objective1).getDouble() < Math.abs(currentOrderPower * 0.01))
					bestParticle = individual;
			}
			powerError += bestParticle.getObjectives().get(objective1).getDouble() / Math.abs(currentOrderPower);
			psoCounter++;
			return bestParticle;
		} catch (StopException e) {
			e.printStackTrace();
			return null;
		} catch (TerminationException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<Individual> sortArchiveByObjective(Archive archive,Objective objective) {
		class IndividualComparatorByObjective implements Comparator <Individual>{
			Objective objective;
			public IndividualComparatorByObjective (Objective objective){
				this.objective = objective;
			}
			
			@Override
			public int compare (Individual first, Individual second){
				double firstObjectiveValue = first.getObjectives().get(objective).getDouble();
				double secondObjectiveValue = second.getObjectives().get(objective).getDouble();
				if (firstObjectiveValue > secondObjectiveValue)
					return 1;
				else if (secondObjectiveValue > firstObjectiveValue)
					return -1;
				else
					return 0;
			}
		}
		
		ArrayList<Individual> archiveSorted = new ArrayList<Individual>();
		Iterator<Individual> iterator = archive.iterator();
		while(iterator.hasNext())
			archiveSorted.add(iterator.next());
		Collections.sort(archiveSorted, new IndividualComparatorByObjective(objective1));
		return archiveSorted;
	}

	private void checkNotifications() {
		ArrayList <Notification> notificationsToRemove = new ArrayList <Notification> ();
		for (Notification notification : controlUnit.notifications){
			if (notification.notify.equals("CONNECTION")){
				if (notification.data instanceof PowerConnectionFull)
					controlUnit.powerConnectionList.add((PowerConnectionFull) notification.data);
				else{
					controlUnit.controlableDevicesList.add((ModelObject) notification.data);
					if (notification.data instanceof RefrigeratorFull)
						refrigeratorList.add((RefrigeratorFull) notification.data);
					else if (notification.data instanceof FreezerFull)
						freezerList.add((FreezerFull) notification.data);
					else if (notification.data instanceof LightingFull)
						lightingList.add((LightingFull) notification.data);
					else if (notification.data instanceof TankWaterHeaterFull)
						tankWaterHeaterList.add((TankWaterHeaterFull) notification.data);
					else if (notification.data instanceof RadiatorFull)
						radiatorList.add((RadiatorFull) notification.data);
				}
			}
			else if (notification.notify.equals("MODIFY") || notification.notify.equals("RESTORE")){
				currentNotification = notification;
				notificationsToRemove.add(notification);
			}
			notificationsToRemove.add(notification);
		}
		
		for (Notification notification : notificationsToRemove)
			controlUnit.notifications.remove(notification);
	}
	

	private double getRealConsumption() {
		double realConsumption = 0;
		for (PowerConnection powerConnection : controlUnit.powerConnectionList)
			realConsumption += ((PowerConnectionFull)powerConnection).power;
		return realConsumption;
	}
	
	int counter = 0;
	boolean reduce = true;

	@Override
	public void tickOff(Long time) {
	}
	
	private void configureAndRecord(HistoryElement historyElement, Double genotypeValue, Double phenotypeValue) {
		if (historyElement.element instanceof LightingFull)
			configureLighting(historyElement, genotypeValue, phenotypeValue);
		else if (historyElement.element instanceof RefrigeratorFull)
			configureRefrigerator(historyElement, genotypeValue, phenotypeValue);
		else if (historyElement.element instanceof FreezerFull)
			configureFreezer(historyElement, genotypeValue, phenotypeValue);
	}

	private void configureLighting(HistoryElement historyElement, Double genotypeValue, Double phenotypeValue) {
		LightingFull lighting = ((LightingFull)historyElement.getElement());

		double processedGenotype = processLightingGenotype(genotypeValue);
		
		if ((!historyElement.existsData()) && (processedGenotype != lighting.intensityRate)){
			// No piloted and intensity change --> go piloted!
			LightingHistoryData data = new LightingHistoryData(lighting.intensityRate, processedGenotype);
			historyElement.setData(data);
			historyElement.addHistorical(1);
			lighting.intensityRate = processedGenotype;
		}
		else if ((historyElement.existsData()) && (processedGenotype != lighting.intensityRate)){
			// Piloted and intensity change --> go no piloted!
			LightingHistoryData data = (LightingHistoryData) historyElement.data;
			historyElement.deleteData();
			historyElement.addHistorical(0);
			lighting.intensityRate = data.intensityBeforePiloting;
		}
		else
			historyElement.addHistorical(historyElement.getLastState());
	}
	
	private void configureRefrigerator (HistoryElement historyElement, Double genotypeValue, Double phenotypeValue) {
		RefrigeratorFull refrigerator = ((RefrigeratorFull)historyElement.getElement());

		double processedGenotype = processRefrigeratorGenotype(genotypeValue);
		
		if (processedGenotype == 0){
			refrigerator.mode = RefrigeratorFull.Mode.OFF;
			historyElement.addHistorical(1); // PILOTED
		}
		else if (processedGenotype == 1){
			refrigerator.mode = RefrigeratorFull.Mode.ON;
			historyElement.addHistorical(0); // NO PILOTED
		}
		else
			historyElement.addHistorical(historyElement.getLastState());
	}
	
	private void configureFreezer (HistoryElement historyElement, Double genotypeValue, Double phenotypeValue) {
		FreezerFull freezer = ((FreezerFull)historyElement.getElement());

		double processedGenotype = processFreezerGenotype(genotypeValue);
		
		if (processedGenotype == 0){
			freezer.mode = FreezerFull.Mode.OFF;
			historyElement.addHistorical(1); // PILOTED
		}
		else if (processedGenotype == 1){
			freezer.mode = FreezerFull.Mode.ON;
			historyElement.addHistorical(0); // NO PILOTED
		}
		else
			historyElement.addHistorical(historyElement.getLastState());
	}	

	public void terminate(){
		Console.out("Power error: " + powerError / (double)psoCounter);
	}
	
	public void loadAttribute(String name, String value) {
		if(name.equals("step"))
			step = Integer.parseInt(value);
	}	
	
	
	class History{
		
		ArrayList <HistoryElement> elements = new ArrayList <HistoryElement> ();
		int maxTimesPiloted = Integer.MIN_VALUE;
		int minTimesPiloted = Integer.MAX_VALUE;
		
		public History (ArrayList<ModelObject> elements){
			for (ModelObject element : elements)
				this.elements.add(new HistoryElement(element));
		}
	}
	
	class HistoryElement{
		private ModelObject element;
		private Object data;
		private ArrayList<Integer> historicalStates = new ArrayList<Integer> ();
		
		private int pilotingCounter = 0;
		
		public HistoryElement (ModelObject element){
			this.element = element;
			/* Every appliance has not been piloted before the simulation starts */
			for (int i = 0; i < 100; i++)
				historicalStates.add(0);
		}
		
		public ModelObject getElement(){
			return element;
		}
		
		public void addHistorical (int state){
			if (this.getLastState() == 0 && state == 1)
				pilotingCounter++;
			historicalStates.add(state);
		}
		
		public ArrayList<Integer> getHistoricalStates (){
			return historicalStates;
		}
		
		public int getLastState (){
			return historicalStates.get(historicalStates.size() - 1);
		}
		
		public int getLastStateSlotCount(){
			for (int i = historicalStates.size() - 1; i > -1; i--)
				if (historicalStates.get(i) != this.getLastState())
					return historicalStates.size() - 1 - i;
			return historicalStates.size();
		}
		
		public void setData (Object data) {
			this.data = data;
		}
		
		public Object getData (){
			return data;
		}
		
		public void deleteData (){
			this.data = null;
		}
		
		public boolean existsData (){
			if (data == null)
				return false;
			return true;
		}
		
		public int slotsSinceLastPiloting(){
			int slots = 0;
			for (int i = historicalStates.size() - 1 ; i > - 1; i--)
				if (historicalStates.get(i) == 0)
					slots++;
				else
					break;
			return slots;
		}
		
		public int slotsDurationInLastPiloting(){
			int slots = 0;
			boolean pilotFound = false;
			for (int i = historicalStates.size() - 1 ; i > - 1; i--)
				if (historicalStates.get(i) == 1){
					pilotFound = true;
					slots++;
				}
				else if (pilotFound)
					if (historicalStates.get(i) == 1)
						slots++;
					else
						break;
			return slots;
		}
	}
	
	class LightingHistoryData {
		double intensityBeforePiloting;
		double pilotedIntensity;
		
		public LightingHistoryData (double intensityBeforePiloting, double pilotedIntensity){
			this.intensityBeforePiloting = intensityBeforePiloting;
			this.pilotedIntensity = pilotedIntensity;
		}
	}
		
	class MyCreator implements Creator <DoubleGenotype> {
		
		int genotypeSize;
		History history;
		int creations = 0;
		
		public MyCreator (History history){
			this.genotypeSize = history.elements.size();
			this.history = history;
		}

		@Override
		public DoubleGenotype create() {
			DoubleGenotype genotype = null;
			genotype = new DoubleGenotype(0., 1.);
			for (int i = 0;  i < genotypeSize; i++){
				double gen;
//				int internalCreations = creations;
				do{
//					if (internalCreations == 0)
//						gen = 1;
//					else if (internalCreations == 1)
//						gen = 0;
//					else
						gen = Tools.randomInRangeDouble(0, 1);
//					internalCreations += 2;
				} while (!specificGenotypeRestrictions(i, gen));
				genotype.add(gen);
			}
//			creations++;
			return genotype;
		}
	}
	
	class MyIndividualCompleter implements IndividualCompleter{

		MyDecoder decoder;
		MyEvaluator evaluator;
		
		public MyIndividualCompleter (History history, ControlUnitFull controlUnit){
			decoder = new MyDecoder (history);
			evaluator = new MyEvaluator (controlUnit);
		}
		
		@Override
		public void complete(Iterable<? extends Individual> arg0)
				throws TerminationException {
			Iterator<? extends Individual> iterator = arg0.iterator();
			while (iterator.hasNext()){
				Individual individual = iterator.next();
				individual.setPhenotype(decoder.decode((DoubleGenotype)individual.getGenotype()));
				individual.setObjectives(evaluator.evaluate((MyPhenotype)individual.getPhenotype()));
			}
		}

		@Override
		public void complete(Individual... arg0) throws TerminationException {
			for (Individual individual : arg0){
				individual.setPhenotype(decoder.decode((DoubleGenotype)individual.getGenotype()));
				individual.setObjectives(evaluator.evaluate((MyPhenotype)individual.getPhenotype()));
			}

		}
		
		class MyDecoder implements Decoder <DoubleGenotype, MyPhenotype> {

			History history;
			
			
			public MyDecoder(History history) {
				this.history = history;
			}

			@Override
			public MyPhenotype decode(DoubleGenotype arg0) {
				
				MyPhenotype phenotype = new MyPhenotype ();

				for (int i = 0; i < arg0.size(); i++){
					if (history.elements.get(i).element instanceof LightingFull){
						double phenotypeValue = lightingPowerChange((LightingFull) history.elements.get(i).element, arg0.get(i));
						phenotype.add(phenotypeValue, arg0.get(i));
					}
					else if (history.elements.get(i).element instanceof RefrigeratorFull){
						double phenotypeValue = refrigeratorPowerChange((RefrigeratorFull) history.elements.get(i).element, arg0.get(i));
						phenotype.add(phenotypeValue, arg0.get(i));
					}
					else if (history.elements.get(i).element instanceof FreezerFull){
						double phenotypeValue = freezerPowerChange((FreezerFull) history.elements.get(i).element, arg0.get(i));
						phenotype.add(phenotypeValue, arg0.get(i));
					}
				}
				
				return phenotype;
			}

			private double lightingPowerChange(LightingFull lighting, double genotype) {
				double processedGenotype = processLightingGenotype(genotype);

				if (lighting.mode == LightingFull.Mode.ON){
					double currentPower = lighting.activePower;
					double futurePower = lighting.installedPower * lighting.installedPowerUsageRate * processedGenotype;
					return futurePower - currentPower;
				}
				return 0;
			}
			
			private double refrigeratorPowerChange(RefrigeratorFull refrigerator, double genotype) {
				double processedGenotype = processRefrigeratorGenotype(genotype);

				if (refrigerator.mode == RefrigeratorFull.Mode.ON){
					double currentPower = refrigerator.activePower;
					double futurePower = (processedGenotype == 0 ? 0 : refrigerator.activePower);
					return futurePower - currentPower;
				}
				else if (refrigerator.mode == RefrigeratorFull.Mode.OFF){
					double currentPower = refrigerator.activePower;
//					double futurePower = (processedGenotype == 0 ? refrigerator.activePower : 49.4); // (90 + 8.8) / 2
					double futurePower = (processedGenotype == 0 ? refrigerator.activePower : 116.9); // (225 + 8.8) / 2
					return futurePower - currentPower;
				}
				return 0;
			}
			
			private double freezerPowerChange(FreezerFull freezer, double genotype) {
				double processedGenotype = processFreezerGenotype(genotype);
				
				if (freezer.mode == FreezerFull.Mode.ON){
					double currentPower = freezer.activePower;
					double futurePower = (processedGenotype == 0 ? 0 : freezer.activePower);
					return futurePower - currentPower;
				}
				else if (freezer.mode == FreezerFull.Mode.OFF){
					double currentPower = freezer.activePower;
//					double futurePower = (processedGenotype == 0 ? freezer.activePower : 79.4); // (150 + 8.8) / 2
					double futurePower = (processedGenotype == 0 ? freezer.activePower : 154.4); // (300 + 8.8) / 2
					return futurePower - currentPower;
				}
				return 0;
			}

			
		}
		
		class MyEvaluator implements Evaluator <MyPhenotype> {
			
			ControlUnitFull controlUnit;
			double powerMin = Double.MAX_VALUE;
			int iteration = 0;
			
			public MyEvaluator(ControlUnitFull controlUnit) {
				this.controlUnit = controlUnit;
			}

			@Override
			public Objectives evaluate(MyPhenotype arg0) {
				Objectives objectives = new Objectives ();
			
				if (!specificRestrictions(arg0.genotypeList)){
					objectives.add(objective1, Objective.INFEASIBLE);
					objectives.add(objective2, Objective.INFEASIBLE);
					objectives.add(objective3, Objective.INFEASIBLE);
					return objectives;
				}
				
				/* objective 1 calculus */
				double totalPowerChange = 0;
				for (Double power : arg0.powerChangeList)
					totalPowerChange += power;
				
				double powerPrediction = controlUnit.aimConsumption - (controlUnit.realConsumption + totalPowerChange);
				double error = Math.abs(powerPrediction);
				
				objectives.add(objective1, error);
				
				/* objective 2 calculus */
				int aux = 0;
					
				for (int i = 0; i < arg0.size(); i++){
					if (history.elements.get(i).getLastState() == 0)
						if (isProposedToBePiloted(history.elements.get(i), arg0.genotypeList.get(i)))
							aux += Math.pow((history.maxTimesPiloted - history.elements.get(i).pilotingCounter), 5);
				}		
				objectives.add(objective2, aux);
//				
				/* objective 3 calculus */

				int leavePilotingWhenAvailable = 0;
				int startPiloting = 0;
				
//				if (controlUnit.aimConsumption < controlUnit.realConsumption){					
//					for (int i = 0; i < arg0.size(); i++){
//						int numberOfPilotings = history.elements.get(i).pilotingCounter;
//						if (isProposedToBePiloted(history.elements.get(i), arg0.genotypeList.get(i)))
//							aux += Math.pow((history.maxTimesPiloted - numberOfPilotings), 5);
//					}
//				}
//				else if (controlUnit.aimConsumption > controlUnit.realConsumption){
//					for (int i = 0; i < arg0.size(); i++){
//						int numberOfPilotings = history.elements.get(i).pilotingCounter;
//						if (isProposedToBePiloted(history.elements.get(i), arg0.genotypeList.get(i)))
//							if (history.elements.get(i).getLastState() == 0)
//								aux += (-1000 * numberOfPilotings);
//					}
//				}
//				else{ // aim == real
					for (int i = 0; i < arg0.size(); i++){
						// If already piloted
						if (history.elements.get(i).getLastState() == 1){
							// No proposed to go on
							if (!isProposedToBePiloted(history.elements.get(i), arg0.genotypeList.get(i)))
								// Can be piloted
								if (canBePiloted(i))
									leavePilotingWhenAvailable++;
						}
						else
							if (isProposedToBePiloted(history.elements.get(i), arg0.genotypeList.get(i)))
								startPiloting++;
					}
//				}
				objectives.add(objective3, Math.min(leavePilotingWhenAvailable, startPiloting));

				return objectives;
			}
		}
	}
	
	class MyArchive1 extends CrowdingArchive{

		public MyArchive1(int capacity) {
			super(capacity);
		}
		
		@Override
		protected boolean updateWithNondominated(Collection<Individual> candidates) {
			boolean changed = super.updateWithNondominated(candidates);
			int toDelete = (int) ((3./5.) * this.size() - 10.);
			if (toDelete <= 0)
				return changed;
			else{
				ArrayList<Individual> sorted = sortArchiveByObjective(this, objective1);
				int toSave = this.size() - toDelete;
				this.clear();
				for (int i = 0; i  < toSave; i++)
					this.addCheckedIndividual(sorted.get(i));
				return true;
			}
		}		
		
	}
	
	class MyArchive2 extends CrowdingArchive{

		public MyArchive2(int capacity) {
			super(capacity);
		}
		
		@Override
		protected boolean updateWithNondominated(Collection<Individual> candidates) {
			super.updateWithNondominated(candidates);
			Iterator <Individual> iterator = this.iterator();
			ArrayList<Individual> toRemove = new ArrayList<Individual>();
			while (iterator.hasNext()){
				Individual toCheck = iterator.next();
				if (toCheck.getObjectives().get(objective1).getDouble()  != null)
					if (toCheck.getObjectives().get(objective1).getDouble() > Math.abs(currentOrderPower * 0.10))
						toRemove.add(toCheck);
			}
			for (Individual individual : toRemove)
				this.remove(individual);
			return true;
		}		
		
	}
	
	class MyPhenotype implements Phenotype{
		
		private ArrayList <Double> genotypeList = new ArrayList <Double> ();
		private ArrayList <Double> powerChangeList = new ArrayList <Double> ();
		
		public void add (double powerChange, double genotype){
			powerChangeList.add(powerChange);
			genotypeList.add(genotype);	
		}
		
		public double[] get (int index){
			return new double [] {powerChangeList.get(index), genotypeList.get(index)};
		}
		
		public int size (){
			return powerChangeList.size();
		}
	}
	
	class MyProvider implements Provider <Particle> {

		@Override
		public Particle get() {
			return new Particle();
		}
		
	}
	
	private boolean isProposedToBePiloted(HistoryElement historyElement, Double genotype) {
		if (historyElement.getElement() instanceof LightingFull)
			return isLightingToBePiloted(genotype);
		else if (historyElement.getElement() instanceof RefrigeratorFull)
			return isRefrigeratorToBePiloted(genotype);
		else if (historyElement.getElement() instanceof FreezerFull)
			return isFreezerToBePiloted(genotype);
		else
			return false;
	}
	
	private boolean specificRestrictions(ArrayList<Double> genotype) {
		for (int i = 0; i < genotype.size(); i++)
			if (!specificGenotypeRestrictions(i, genotype.get(i)))
				return false;
		return true;
	}
	
	private boolean canBePiloted(int index) {
		if (history.elements.get(index).getElement() instanceof LightingFull){
			if (!lightingRestrictions(history.elements.get(index), 0.7))
				return false;
		}
		else if (history.elements.get(index).getElement() instanceof RefrigeratorFull){
			if (!refrigeratorRestrictions(history.elements.get(index), 0))
				return false;
		}
		else if (history.elements.get(index).getElement() instanceof FreezerFull){
			if (!freezerRestrictions(history.elements.get(index), 0))
				return false;
		}
		return true;
	}
	
	private boolean specificGenotypeRestrictions(int index, double genotype) {
		if (history.elements.get(index).getElement() instanceof LightingFull){
			if (!lightingRestrictions(history.elements.get(index), genotype))
				return false;
		}
		else if (history.elements.get(index).getElement() instanceof RefrigeratorFull){
			if (!refrigeratorRestrictions(history.elements.get(index), genotype))
				return false;
		}
		else if (history.elements.get(index).getElement() instanceof FreezerFull){
			if (!freezerRestrictions(history.elements.get(index), genotype))
				return false;
		}
		return true;
	}
	
	public double processLightingGenotype(double genotype){		
		if (genotype <= 0.5)
			return 0.7;
		else
			return 1.;
	}
	
	private boolean isLightingToBePiloted(Double genotype) {
		double processedGenotype = processLightingGenotype(genotype);
		
		if (processedGenotype == 0.7)
			// No piloted and intensity change --> go piloted!
			return true;
		else
			return false;
	}

	private boolean lightingRestrictions(HistoryElement historyElement,	double genotype) {
		double proposedIntensity = processLightingGenotype(genotype);
		
		if (((LightingFull)historyElement.getElement()).mode == LightingFull.Mode.ON){
			if (historyElement.getLastState() == 1){
				// If piloted
				if (((LightingHistoryData)historyElement.data).intensityBeforePiloting != proposedIntensity) // If go on piloted is proposed...
					if (historyElement.getLastStateSlotCount() >= LIGHTING_MAX_SHIFT_SLOTS) // if max time reached
						return false;
					else if (((LightingFull)historyElement.element).intensityRate != proposedIntensity) // if a change of intensity is proposed meanwhile the piloting go on....
						return false;
			}
			else if (historyElement.getLastState() == 0){
				// if not piloted
				if (((LightingFull)historyElement.element).intensityRate != proposedIntensity) // if piloting proposed
//					if (historyElement.getLastStateSlotCount() <= LIGHTING_MIN_RECOVER_SLOTS) // if not recovered
					if (historyElement.slotsSinceLastPiloting() <= historyElement.slotsDurationInLastPiloting() * LIGHTING_MIN_RECOVER_RATE)
						return false;
					else if (historyElement.pilotingCounter >= (history.minTimesPiloted + MAX_PILOTING_COUNTER_DISTANCE)) //too much times piloted
						return false;
			}
		}
		else{
			if (historyElement.getLastState() == 1){ // if piloted
				if (((LightingHistoryData)historyElement.data).intensityBeforePiloting != proposedIntensity) // intensity must be recovered
					return false;
			}
			else{ // if not piloted
				if (((LightingFull)historyElement.getElement()).intensityRate != proposedIntensity) // intensity must not be touched
					return false;
			}
		}
		return true;
	}
	
	public double processRefrigeratorGenotype (double genotype){		
		if (genotype <= 0.5)
			return 0.;
		else
			return 1.;
	}
	
	private boolean isRefrigeratorToBePiloted(Double genotype) {
		double processedGenotype = processRefrigeratorGenotype(genotype);
		
		if (processedGenotype == 0)
			return true;
		else
			return false;
	}
	
	private boolean refrigeratorRestrictions(HistoryElement historyElement,	double genotype) {
		double proposedModeOn = processRefrigeratorGenotype(genotype);
		if (historyElement.getLastState() == 1){// If piloted
			if (proposedModeOn == 0) // Go on piloted
				if (historyElement.getLastStateSlotCount() >= REFRIGERATOR_MAX_SHIFT_SLOTS) // If time exceeded...
				return false;
		}
		else if (historyElement.getLastState() == 0){// if not piloted
			if (proposedModeOn == 0) // Propose to be piloted
				//if (historyElement.getLastStateSlotCount() <= REFRIGERATOR_MIN_RECOVER_SLOTS) //Not recovered
				if (historyElement.slotsSinceLastPiloting() <= historyElement.slotsDurationInLastPiloting() * REFRIGERATOR_MIN_RECOVER_RATE)
					return false;
				else if (historyElement.pilotingCounter >= (history.minTimesPiloted + MAX_PILOTING_COUNTER_DISTANCE))
					return false;
		}
		return true;
	}
	
	public double processFreezerGenotype (double genotype){		
		if (genotype <= 0.5)
			return 0.;
		else
			return 1.;
	}
	
	private boolean isFreezerToBePiloted(Double genotype) {
		double processedGenotype = processRefrigeratorGenotype(genotype);
		
		if (processedGenotype == 0)
			return true;
		else
			return false;
	}
	
	private boolean freezerRestrictions(HistoryElement historyElement,	double genotype) {
		double proposedModeOn = processRefrigeratorGenotype(genotype);
		if (historyElement.getLastState() == 1){// If piloted
			if (proposedModeOn == 0) // Go on piloted
				if (historyElement.getLastStateSlotCount() >= FREEZER_MAX_SHIFT_SLOTS) // If time exceeded...
					return false;
		}
		else if (historyElement.getLastState() == 0){// if not piloted
			if (proposedModeOn == 0) // Propose to be piloted
				//if (historyElement.getLastStateSlotCount() <= FREEZER_MIN_RECOVER_SLOTS) //Not recovered
				if (historyElement.slotsSinceLastPiloting() <= historyElement.slotsDurationInLastPiloting() * FREEZER_MIN_RECOVER_RATE)
					return false;
				else if (historyElement.pilotingCounter >= (history.minTimesPiloted + MAX_PILOTING_COUNTER_DISTANCE))
					return false;
		}
		return true;
	}
}
