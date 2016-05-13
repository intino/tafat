package tafat.engine;

import org.javafmi.wrapper.*;
import tafat.*;
import tafat.conditional.ConditionalTrace;
import tafat.engine.tablefunction.TableFunctionProvider;
import tafat.instant.InstantTrace;
import tafat.parallelizable.behavior.ParallelizableImplementation;
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
        TaskManager.addAll(behaviors.stream().flatMap(b -> b.implementation(0).taskList().stream()).collect(Collectors.toList()));
        behaviors.forEach(behavior -> behavior.implementation(0).startList().forEach(Start::start));
        this.behaviors = behaviors.stream().filter(b -> !isParallelizable(b)).collect(toList());
        this.parallelBehaviors = behaviors.stream().filter(this::isParallelizable).collect(toList());
    }

    private void initTableFunctions(List<Behavior> behaviors) {
        behaviors.forEach(b -> b.implementation(0).tableFunctionList().forEach(tableFunction -> {
            if (!tableFunction.dataList().isEmpty())
                tableFunction.provider(new TableFunctionProvider(tableFunction));
            else throw new RuntimeException("There is no data in table function " + tableFunction.name());
        }));
    }

    private void initSystemDynamics(List<Behavior> behaviors) {
        behaviors.forEach(b -> b.implementation(0).systemDynamicList().forEach(systemDynamic -> {
            systemDynamic.odeSolver(createODESolver(systemDynamic.differentialEquation(), systemDynamic.solver().toString()));
            systemDynamic.odeSolver().setStepSize(systemDynamic.step());
        }));
    }

    private void initFmu(List<Behavior> behaviors) {
        behaviors.forEach(b -> b.implementation(0).fmuList().forEach(fmu -> {
            fmu.wrapper(new org.javafmi.wrapper.Simulation(fmu.file()));
            fmu.wrapper().init(0);
        }));
    }

    private boolean isParallelizable(Behavior behavior) {
        return !behavior.implementation(0).periodicActivityList().isEmpty() &&
                behavior.implementation(0).is(ParallelizableImplementation.class);
    }

    private void purgeImplementations() {
        graph.find(Behavior.class).stream()
                .filter(b -> !b.implementation().isEmpty())
                .forEach(this::purgeImplementations);
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
        processParallelBehaviors();
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

    private void processParallelBehaviors() {
        parallelBehaviors.parallelStream().filter(Behavior::checkStep).forEach(this::run);
    }

    @SuppressWarnings("Convert2streamapi")
    private void processBehaviors() {
        for (Behavior behavior : behaviors)
            if (behavior.checkStep())
                run(behavior);
    }

    private void processOutputList() {
        platform.outputList().forEach(Output::process);
    }

    private void run(Behavior behavior) {
        behavior.implementation(0).periodicActivityList().forEach(p -> p.execute(behavior.step()));
    }

    private long steps() {
        return (platform.simulation().to().toEpochSecond(ZoneOffset.UTC) -
                platform.simulation().from().toEpochSecond(ZoneOffset.UTC));
    }

}
