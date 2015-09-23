package tafat.engine;

import tafat.*;
import tara.magritte.Model;

import java.time.ZoneOffset;
import java.util.List;


public class TafatEngine {

    private final List<Behavior> behaviors;
    private Model model;

    public TafatEngine(Model model) {
        this.model = model;
        Date.setDateTime(model.viewer(TafatViewer.class).simulation().from());
        this.behaviors = model.findComponents(Behavior.class);
        initBehaviors();
    }

    private void initBehaviors() {
        behaviors.forEach(b -> TaskManager.addAll(b.taskList()));
        behaviors.forEach(behavior -> behavior.startList().forEach(Behavior.Start::start));
    }

    public void execute() {
        long steps = steps();
        for (int step = 0; step < steps; step++) run();
    }

    public void run() {
        Date.plusSeconds(1);
        TaskManager.update();
        TimeoutManager.update(1);
        behaviors.forEach(this::run);
    }

    private void run(Behavior behavior) {
        behavior.actionList().forEach(Action::action);
        behavior.conditionalActionList().stream().filter(ConditionalAction::condition).forEach(ConditionalAction::action);
    }

    private long steps() {
        return (model.viewer(TafatViewer.class).simulation().to().toEpochSecond(ZoneOffset.UTC) -
                model.viewer(TafatViewer.class).simulation().from().toEpochSecond(ZoneOffset.UTC));
    }
}
