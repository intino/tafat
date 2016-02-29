package tafat;

import tafat.conditional.ConditionalTrace;
import tafat.engine.Date;
import tafat.instant.InstantTrace;
import tafat.parallelizable.ParallelizableBehavior;
import tafat.periodic.PeriodicTrace;
import tara.magritte.Model;

import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static tafat.TimeoutManager.cyclicTimeout;
import static tafat.TimeoutManager.timeout;

public class Executor {

	private static final Logger LOG = Logger.getLogger(Executor.class.getName());

	private final Model model;
	private final TafatPlatform engine;
	private List<Behavior> parallelBehaviors;
	private List<Behavior> behaviors;

	public Executor(Model model) {
		this.model = model;
		this.engine = model.platform();
	}

	public void init() {
		Date.setDateTime(engine.simulation().from());
		initEngine();
		initEvents();
		initAssertions();
		initTraces();
		initOutputs();
		initBehaviors();
	}

	public void execute() {
		long steps = steps();
		for (int step = 0; step < steps; step++) run();
	}

	private void initEngine() {
		TaskManager.init();
		TimeoutManager.init();
	}

	private void initEvents() {
		engine.eventList().forEach(e -> timeout(e.instant(), e::execute));
	}

	private void initTraces() {
		model.find(PeriodicTrace.class)
				.forEach(p -> cyclicTimeout(p.timeScale(), traceAction(p.as(Trace.class))));
		model.find(InstantTrace.class)
				.forEach(p -> p.instants().forEach(i -> timeout(i, traceAction(p.as(Trace.class)))));
	}

	private tafat.functions.Action traceAction(Trace trace) {
		return () -> {
			if (!trace.is(ConditionalTrace.class))
				LOG.info(trace.print());
			else if (trace.as(ConditionalTrace.class).check())
				LOG.info(trace.print());
		};
	}

	private void initAssertions() {
		engine.simulation().assertionList().forEach(a -> {
			timeout(a.at(), () -> {
				if (a.that().equals(a.shouldBe())) return;
				LOG.info(a.at() + ": assertion " + a._simpleName() + " failed. Expected: " + a.shouldBe() + ". Was: " + a.that());
			});
		});
	}

	private void initOutputs() {
		engine.outputList().forEach(Output::init);
	}

	private void initBehaviors() {
		List<Behavior> behaviors = model.find(Behavior.class);
		initStateCharts();
		initTableFunctions();
		TaskManager.addAll(behaviors.stream().flatMap(b -> b.taskList().stream()).collect(Collectors.toList()));
		behaviors.forEach(behavior -> behavior.startList().forEach(Behavior.Start::start));
		this.behaviors = behaviors.stream().filter(b -> !b.periodicActivityList().isEmpty() && !b.is(ParallelizableBehavior.class)).collect(toList());
		this.parallelBehaviors = behaviors.stream().filter(b -> !b.periodicActivityList().isEmpty() && b.is(ParallelizableBehavior.class)).collect(toList());
	}

	private void initStateCharts() {
		model.find(Behavior.class).stream()
				.map(Behavior::stateChartList)
				.flatMap(Collection::stream)
				.forEach(stateChart -> stateChart.current(stateChart.state(0)));
	}

	private void initTableFunctions() {
		model.find(TableFunction.class).stream()
				.forEach(t -> {
					try {
						TableFunctionProvider tableFunctionProvider = new TableFunctionProvider(t);
						t.getClass().getDeclaredField("get").set(t, tableFunctionProvider.getter());
					} catch (IllegalAccessException | NoSuchFieldException e) {
						e.printStackTrace();
					}
				});
	}

	public void run() {
		TaskManager.update();
		TimeoutManager.update();
		engine.simulation().stopList().stream().filter(Stop::when).forEach(Stop::execute);
		parallelBehaviors.parallelStream().filter(Behavior::checkStep).forEach(this::run);
		behaviors.stream().filter(Behavior::checkStep).forEach(this::run);
		engine.outputList().forEach(Output::process);
		Date.plusSeconds(1);
	}

	private void run(Behavior behavior) {
		behavior.periodicActivityList().forEach(p -> p.execute(behavior.step()));
	}

	private long steps() {
		return (engine.simulation().to().toEpochSecond(ZoneOffset.UTC) -
				engine.simulation().from().toEpochSecond(ZoneOffset.UTC));
	}

}
