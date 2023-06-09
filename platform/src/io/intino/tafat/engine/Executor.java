package io.intino.tafat.engine;

import io.intino.tafat.model.*;
import io.intino.tafat.engine.tablefunction.TableFunctionProvider;
import io.intino.tafat.engine.utils.StatechartUpdater;
import io.intino.tafat.model.functions.Action;
import io.intino.magritte.framework.Graph;
import io.intino.magritte.framework.Layer;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.tafat.engine.TimeoutManager.cyclicTimeout;
import static io.intino.tafat.engine.TimeoutManager.timeout;
import static java.util.stream.Collectors.toList;
import static org.opensourcephysics.numerics.ODESolverFactory.createODESolver;

public class Executor {

    private static final Logger LOG = Logger.getLogger(Executor.class.getName());

    private final Graph graph;
    private final TafatGraph platform;
    private List<Implementation> parallelImplementations;
    private List<Implementation> implementations;
    private int minStepSize;

    public Executor(Graph graph) {
        this.graph = graph;
        this.platform = graph.as(TafatGraph.class);
    }

    public void init() {
        Date.setInstant(platform.simulation().from());
        initEngine();
        initEvents();
        initAssertions();
        initTraces();
        initOutputs();
        initImplementations();
    }

    public void execute() {
        long steps = steps() / minStepSize;
        for (int step = 0; step < steps; step++) run(minStepSize);
    }

    private void initEngine() {
        TaskManager.init();
        TimeoutManager.init();
    }

    private void initEvents() {
        platform.eventList().forEach(e -> timeout(e.instantDate(), e::execute));
    }

    private void initTraces() {
        graph.find(Trace.Periodic.class)
                .forEach(p -> cyclicTimeout(p.timeScale(), traceAction(p.core$().as(Trace.class))));
        graph.find(Trace.Instant.class)
                .forEach(p -> p.instants().forEach(i -> timeout(i, traceAction(p.core$().as(Trace.class)))));
    }

    private Action traceAction(Trace trace) {
        return () -> {
            if (!trace.core$().is(Trace.Conditional.class))
                LOG.info(trace.print());
            else if (trace.core$().as(Trace.Conditional.class).check())
                LOG.info(trace.print());
        };
    }

    private void initAssertions() {
        platform.simulation().assertionList().forEach(a -> timeout(a.at(), () -> {
            if (a.that().equals(a.shouldBe())) return;
            LOG.info(a.at() + ": assertion " + a.name$() + " failed. Expected: " + a.shouldBe() + ". Was: " + a.that());
        }));
    }

    private void initOutputs() {
        platform.outputList().forEach(Output::init);
    }

    private void initImplementations() {
        purgeImplementations();
        List<Behavior> behaviors = graph.find(Behavior.class);
        minStepSize = behaviors.parallelStream().mapToInt(Behavior::step).min().orElse(1);
        initTableFunctions(behaviors);
        initSystemDynamics(behaviors);
        initFmu(behaviors);
        initStateCharts(behaviors);
        TaskManager.addAll(behaviors.stream().flatMap(b -> b.implementation(0).taskList().stream()).collect(Collectors.toList()));
        behaviors.forEach(behavior -> behavior.implementationList().forEach(i -> i.startList().forEach(Start::start)));
        this.implementations = graph.find(Implementation.class).stream().filter(i -> !isParallelizable(i)).collect(toList());
        this.parallelImplementations = graph.find(Implementation.class).stream().filter(this::isParallelizable).collect(toList());
    }

    private void initTableFunctions(List<Behavior> behaviors) {
        behaviors.forEach(b -> b.tableFunctionList().forEach(tableFunction -> {
            if (!tableFunction.dataList().isEmpty())
                tableFunction.provider(new TableFunctionProvider(tableFunction));
            else throw new RuntimeException("There is no data in table function " + tableFunction.name$());
        }));
    }

    private void initSystemDynamics(List<Behavior> behaviors) {
        behaviors.forEach(b -> b.implementationList().forEach(i -> i.systemDynamicList().forEach(systemDynamic -> {
            systemDynamic.differentialEquation(systemDynamic.odeProvider());
            systemDynamic.odeSolver(createODESolver(systemDynamic.differentialEquation(), systemDynamic.solver().toString()));
            systemDynamic.odeSolver().setStepSize(systemDynamic.step());
        })));
    }

    private void initFmu(List<Behavior> behaviors) {
        behaviors.forEach(b -> b.implementationList().forEach(i -> i.fmuList().forEach(fmu -> {
            fmu.wrapper(new org.javafmi.wrapper.Simulation(fmu.file()));
            fmu.wrapper().init(0);
        })));
    }

    private void initStateCharts(List<Behavior> behaviors) {
        behaviors.forEach(b -> b.implementationList().forEach(i -> i.stateChartList().forEach(this::initStateChart)));
    }

    private void initStateChart(StateChart stateChart) {
        if (stateChart.stateList().isEmpty()) return;
        stateChart.current(stateChart.state(0));
        initStateChart(stateChart.current());
        StatechartUpdater.activateTransitions(stateChart);
    }

    private boolean isParallelizable(Implementation implementation) {
        return !implementation.periodicActivityList().isEmpty() &&
                implementation.core$().is(Implementation.Parallelizable.class);
    }

    private void purgeImplementations() {
        graph.find(Behavior.class).stream()
                .filter(b -> !b.implementation().isEmpty())
                .forEach(this::purgeImplementations);
        graph.find(Behavior.class).forEach(b -> b.implementationList().forEach(i -> i.step(b.step())));
    }

    private void purgeImplementations(Behavior behavior) {
        behavior.implementationList().stream()
                .filter(i -> !i.name$().equalsIgnoreCase(behavior.implementation()))
                .collect(toList()).forEach(Layer::delete$);
        if (behavior.implementationList().isEmpty())
            throw new RuntimeException("Behavior at " + behavior.name$() + " must have an implementation selected");
    }

    @SuppressWarnings("WeakerAccess")
    public void run(int stepSize) {
        TaskManager.update();
        TimeoutManager.update();
        processStopList();
        processParallelImplementations(stepSize);
        processBehaviors(stepSize);
        processOutputList();
        Date.plusSeconds(stepSize);
    }

    @SuppressWarnings("Convert2streamapi")
    private void processStopList() {
        for (Stop stop : platform.simulation().stopList())
            if (stop.when())
                stop.execute();
    }

    private void processParallelImplementations(int stepSize) {
        parallelImplementations.parallelStream().filter(i -> i.checkStep(stepSize)).forEach(this::run);
    }

    @SuppressWarnings("Convert2streamapi")
    private void processBehaviors(int stepSize) {
        for (Implementation implementation : implementations)
            if (implementation.checkStep(stepSize))
                run(implementation);
    }

    private void processOutputList() {
        platform.outputList().forEach(Output::process);
    }

    private void run(Implementation implementation) {
        implementation.periodicActivityList().forEach(p -> p.execute(implementation.step()));
    }

    private long steps() {
		return (platform.simulation().from().until(platform.simulation().to(), ChronoUnit.SECONDS));
	}

}
