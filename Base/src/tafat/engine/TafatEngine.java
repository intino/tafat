package tafat.engine;

import tafat.*;
import tafat.Output.Plot;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.magritte.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.util.List;

import static tafat.Output.*;


public class TafatEngine {

    private final List<Behavior> behaviors;
    private List<Plot> plots;
    private Model model;

    public TafatEngine(Model model) {
        this.model = model;
        Date.setDateTime(TafatViewer.simulation().from());
        this.behaviors = model.find(Behavior.class);
        init();
    }

    private void init() {
        initExports();
        initPlots();
        initBehaviors();
    }

    private void initPlots() {
        this.plots = model.find(Plot.class);
    }

    private void initExports() {
        toStash(model.find(Export.class));
    }

    private void toStash(List<? extends Extractor> extractors) {
        try {
            if(extractors.isEmpty()) return;
            writeStash(createStash(extractors), new File(extractors.get(0).path()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private Stash createStash(List<? extends Extractor> extractors) {
        Stash stash = new Stash();
        stash.language = TafatViewer.simulation().output().language();
        extractors.forEach(e -> stash.cases.addAll(e.buildStash()));
        return stash;
    }

    private void writeStash(Stash stash, File file) throws IOException {
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Files.write(file.toPath(), StashSerializer.serialize(stash));
    }

    private void initBehaviors() {
        behaviors.forEach(b -> TaskManager.addAll(b.taskList()));
        behaviors.forEach(behavior -> behavior.startList().forEach(Behavior.Start::start));
    }

    public void execute() {
        long steps = steps();
        for (int step = 0; step < steps; step++) run(step);
    }

    public void run(int step) {
        Date.plusSeconds(1);
        TaskManager.update();
        TimeoutManager.update(1);
        behaviors.forEach(this::run);
        if(step % 60 == 0) toStash(plots);
    }

    private void run(Behavior behavior) {
        behavior.actionList().forEach(Action::action);
        behavior.conditionalActionList().stream().filter(ConditionalAction::condition).forEach(ConditionalAction::action);
    }

    private long steps() {
        return (TafatViewer.simulation().to().toEpochSecond(ZoneOffset.UTC) -
                TafatViewer.simulation().from().toEpochSecond(ZoneOffset.UTC));
    }
}
