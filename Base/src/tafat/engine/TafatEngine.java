package tafat.engine;

import tafat.Behavior;
import tafat.Output.Plot;
import tafat.Parallelizable;
import tafat.TafatViewer;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.magritte.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static tafat.Output.Export;
import static tafat.Output.Extractor;


public class TafatEngine {

    private List<Behavior> parallelBehaviors;
    private List<Behavior> behaviors;
    private List<Plot> plots;
    private Model model;

    public TafatEngine(Model model) {
        this.model = model;
        Date.setDateTime(TafatViewer.simulation().from());
        init();
    }

    private void init() {
        initExports();
        initPlots();
        initBehaviors();
    }

    private void initPlots() {
        this.plots = model.find(Plot.class);
        plots.forEach(p -> p.timeout(p.timeScale().toSeconds() - 1));
    }

    private void initExports() {
        toStash(model.find(Export.class));
    }

    private void toStash(List<? extends Extractor> extractors) {
        try {
            if (extractors.isEmpty()) return;
            writeStash(createStash(extractors), new File(extractors.get(0).path()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private Stash createStash(List<? extends Extractor> extractors) {
        Stash stash = new Stash();
        stash.language = TafatViewer.simulation().output().language();
        extractors.parallelStream().forEach(e -> stash.cases.addAll(e.buildStash()));
        return stash;
    }

    private void writeStash(Stash stash, File file) throws IOException {
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Files.write(file.toPath(), StashSerializer.serialize(stash));
    }

    private void initBehaviors() {
        List<Behavior> behaviors = model.find(Behavior.class);
        TaskManager.addAll(behaviors.stream().flatMap(b -> b.taskList().stream()).collect(toList()));
        behaviors.forEach(behavior -> behavior.startList().forEach(Behavior.Start::start));
        this.behaviors = behaviors.stream().filter(b -> !b.periodicList().isEmpty() && !b.is(Parallelizable.class)).collect(toList());
        this.parallelBehaviors = behaviors.stream().filter(b -> !b.periodicList().isEmpty() && b.is(Parallelizable.class)).collect(toList());
        initStatecharts();
    }

    private void initStatecharts() {
        this.behaviors.stream()
                .flatMap(b -> b.stateChartList().stream())
                .forEach(sc -> sc.stateList()
                        .forEach(s -> s.exitTransitions().addAll(sc.transitionList().stream()
                                .filter(t -> t.from().equals(s)).collect(toList()))));
    }

    public void execute() {
        long steps = steps();
        for (int step = 0; step < steps; step++) run();
    }

    public void run() {
        TaskManager.update();
        TimeoutManager.update();
        parallelBehaviors.parallelStream().filter(Behavior::checkStep).forEach(this::run);
        behaviors.stream().filter(Behavior::checkStep).forEach(this::run);
        toStash(plots.stream().filter(Plot::checkStep).collect(toList()));
        Date.plusSeconds(1);
    }

    private void run(Behavior behavior) {
        behavior.periodicList().forEach(p -> p.execute(behavior.step()));
    }

    private long steps() {
        return (TafatViewer.simulation().to().toEpochSecond(ZoneOffset.UTC) -
                TafatViewer.simulation().from().toEpochSecond(ZoneOffset.UTC));
    }
}
