package tafat.engine;

import tafat.*;
import tafat.conditional.ConditionalTrace;
import tafat.engine.tablefunction.TableFunctionProvider;
import tafat.instant.InstantTrace;
import tafat.parallelizable.ParallelizableImplementation;
import tafat.periodic.PeriodicTrace;
import tara.magritte.Graph;
import tara.magritte.Layer;

import java.time.ZoneOffset;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.opensourcephysics.numerics.ODESolverFactory.createODESolver;
import static tafat.engine.TimeoutManager.cyclicTimeout;
import static tafat.engine.TimeoutManager.timeout;
import static tafat.engine.utils.StatechartUpdater.activateTransitions;

public class Executor {

    private static final Logger LOG = Logger.getLogger(Executor.class.getName());

    private final Graph graph;
    private final TafatPlatform platform;
    private List<Implementation> parallelImplementations;
    private List<Implementation> implementations;

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
        initImplementations();
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
        platform.simulation().assertionList().forEach(a -> timeout(a.at(), () -> {
            if (a.that().equals(a.shouldBe())) return;
            LOG.info(a.at() + ": assertion " + a.name() + " failed. Expected: " + a.shouldBe() + ". Was: " + a.that());
        }));
    }

    private void initOutputs() {
        platform.outputList().forEach(Output::init);
    }

    private void initImplementations() {
        purgeImplementations();
        List<Behavior> behaviors = graph.find(Behavior.class);
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
            else throw new RuntimeException("There is no data in table function " + tableFunction.name());
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
        activateTransitions(stateChart);
    }

    private boolean isParallelizable(Implementation implementation) {
        return !implementation.periodicActivityList().isEmpty() &&
                implementation.is(ParallelizableImplementation.class);
    }

    private void purgeImplementations() {
        graph.find(Behavior.class).stream()
                .filter(b -> !b.implementation().isEmpty())
                .forEach(this::purgeImplementations);
        graph.find(Behavior.class).forEach(b -> b.implementationList().forEach(i -> i.step(b.step())));
    }

    private void purgeImplementations(Behavior behavior) {
        behavior.implementationList().stream()
                .filter(i -> !i.name().equalsIgnoreCase(behavior.implementation()))
                .collect(toList()).forEach(Layer::delete);
        if (behavior.implementationList().isEmpty())
            throw new RuntimeException("Behavior at " + behavior.name() + " must have an implementation selected");
    }

    @SuppressWarnings("WeakerAccess")
    public void run() {
        TaskManager.update();
        TimeoutManager.update();
        processStopList();
        processParallelImplementations();
        processBehaviors();
        processOutputList();
        Date.plusSeconds(1);
    }

    @SuppressWarnings("Convert2streamapi")
    private void processStopList() {
        for (Stop stop : platform.simulation().stopList())
            if (stop.when())
                stop.execute();
    }

    private void processParallelImplementations() {
        parallelImplementations.parallelStream().filter(Implementation::checkStep).forEach(this::run);
    }

    @SuppressWarnings("Convert2streamapi")
    private void processBehaviors() {
        for (Implementation implementation : implementations)
            if (implementation.checkStep())
                run(implementation);
    }

    private void processOutputList() {
        platform.outputList().forEach(Output::process);
    }

    private void run(Implementation implementation) {
        implementation.periodicActivityList().forEach(p -> p.execute(implementation.step()));
    }

    private long steps() {
        return (platform.simulation().to().toEpochSecond(ZoneOffset.UTC) -
                platform.simulation().from().toEpochSecond(ZoneOffset.UTC));
    }

}
