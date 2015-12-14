package tafat;

import tafat.Output.Plot;
import tafat.behavior.BehaviorEntity;
import tafat.engine.Date;
import tafat.engine.TaskManager;
import tafat.engine.TimeoutManager;
import tafat.parallelizable.ParallelizableBehavior;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.magritte.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TafatEngine extends tafat.ModelHandler implements tara.magritte.Engine {
	private List<Behavior> parallelBehaviors;
	private List<Behavior> behaviors;
	private List<Plot> plots;
	private Model model;

	public TafatEngine(Model model) {
		super(model);
		this.model = model;
	}


	@Override
	public void execute() {
		long steps = steps();
		for (int step = 0; step < steps; step++) run();
	}

	@Override
	public void init() {
		Date.setDateTime(simulation().from());
		initExports();
		initPlots();
		initBehaviors();
	}

	private void initPlots() {
		this.plots = model.find(Output.Plot.class);
		plots.forEach(p -> p.timeout(p.timeScale().toSeconds() - 1));
	}

	private void initExports() {
		toStash(model.find(Output.Export.class));
	}

	private void toStash(List<? extends Output.Extractor> extractors) {
		try {
			if (extractors.isEmpty()) return;
			writeStash(createStash(extractors), new File(extractors.get(0).path()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private Stash createStash(List<? extends Output.Extractor> extractors) {
		Stash stash = new Stash();
		stash.language = simulation().output().language();
		extractors.stream().forEach(e -> stash.instances.addAll(e.buildStash()));
		return stash;
	}

	private void writeStash(Stash stash, File file) throws IOException {
		if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
		Files.write(file.toPath(), StashSerializer.serialize(stash));
	}

	private void initBehaviors() {
		List<Behavior> behaviors = model.find(Behavior.class);
        TaskManager.addAll(behaviors.stream().flatMap(b -> b.taskList().stream()).collect(Collectors.toList()));
		behaviors.forEach(behavior -> behavior.startList().forEach(BehaviorEntity.Start::start));
		this.behaviors = behaviors.stream().filter(b -> !b.periodicList().isEmpty() && !b.is(ParallelizableBehavior.class)).collect(toList());
		this.parallelBehaviors = behaviors.stream().filter(b -> !b.periodicList().isEmpty() && b.is(ParallelizableBehavior.class)).collect(toList());
	}

	public void run() {
		TaskManager.update();
		TimeoutManager.update();
		parallelBehaviors.parallelStream().filter(Behavior::checkStep).forEach(this::run);
		behaviors.stream().filter(Behavior::checkStep).forEach(this::run);
		toStash(plots.stream().filter(Output.Plot::checkStep).collect(toList()));
		Date.plusSeconds(1);
	}

	private void run(Behavior behavior) {
		behavior.periodicList().forEach(p -> p.execute(behavior.step()));
	}

	private long steps() {
		return (simulation().to().toEpochSecond(ZoneOffset.UTC) -
				simulation().from().toEpochSecond(ZoneOffset.UTC));
	}
}