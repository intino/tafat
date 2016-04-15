package tafat;

import tafat.conditional.ConditionalTrace;
import tafat.engine.Date;
import tafat.instant.InstantTrace;
import tafat.parallelizable.ParallelizableBehavior;
import tafat.periodic.PeriodicTrace;
import tara.magritte.Graph;

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

	private final Graph graph;
	private final TafatPlatform platform;
	private List<Behavior> parallelBehaviors;
	private List<Behavior> behaviors;

	public Executor(Graph graph) {
		this.graph = graph;
		this.platform = graph.platform();
	}

	public void init() {
		Date.setDateTime(platform.simulation().from());
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
		platform.eventList().forEach(e -> timeout(e.instant(), e::execute));
	}

	private void initTraces() {
		graph.find(PeriodicTrace.class)
				.forEach(p -> cyclicTimeout(p.timeScale(), traceAction(p.as(Trace.class))));
		graph.find(InstantTrace.class)
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
		platform.simulation().assertionList().forEach(a -> {
			timeout(a.at(), () -> {
				if (a.that().equals(a.shouldBe())) return;
				LOG.info(a.at() + ": assertion " + a.name() + " failed. Expected: " + a.shouldBe() + ". Was: " + a.that());
			});
		});
	}

	private void initOutputs() {
		platform.outputList().forEach(Output::init);
	}

	private void initBehaviors() {
		List<Behavior> behaviors = graph.find(Behavior.class);
		initStateCharts();
		initTableFunctions();
		TaskManager.addAll(behaviors.stream().flatMap(b -> b.taskList().stream()).collect(Collectors.toList()));
		behaviors.forEach(behavior -> behavior.startList().forEach(Behavior.Start::start));
		this.behaviors = behaviors.stream().filter(b -> !b.periodicActivityList().isEmpty() && !b.is(ParallelizableBehavior.class)).collect(toList());
		this.parallelBehaviors = behaviors.stream().filter(b -> !b.periodicActivityList().isEmpty() && b.is(ParallelizableBehavior.class)).collect(toList());
	}

	private void initStateCharts() {
		graph.find(Behavior.class).stream()
				.map(Behavior::stateChartList)
				.flatMap(Collection::stream)
				.forEach(stateChart -> stateChart.current(stateChart.state(0)));
	}

	private void initTableFunctions() {
		graph.find(TableFunction.class).stream()
				.filter(t -> !t.dataList().isEmpty())
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
		platform.simulation().stopList().stream().filter(Stop::when).forEach(Stop::execute);
		parallelBehaviors.parallelStream().filter(Behavior::checkStep).forEach(this::run);
		behaviors.stream().filter(Behavior::checkStep).forEach(this::run);
		platform.outputList().forEach(Output::process);
		Date.plusSeconds(1);
	}

	private void run(Behavior behavior) {
		behavior.periodicActivityList().forEach(p -> p.execute(behavior.step()));
	}

	private long steps() {
		return (platform.simulation().to().toEpochSecond(ZoneOffset.UTC) -
				platform.simulation().from().toEpochSecond(ZoneOffset.UTC));
	}

}
