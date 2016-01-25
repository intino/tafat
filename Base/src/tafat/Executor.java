package tafat;

import tafat.engine.Date;
import tafat.parallelizable.ParallelizableBehavior;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.magritte.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Executor {

	private final Model model;
	private final TafatEngine engine;
	private List<Behavior> parallelBehaviors;
	private List<Behavior> behaviors;
	private List<Output.Plot> plots;

	public Executor(Model model) {
		this.model = model;
		this.engine = model.engine(TafatEngine.class);
	}

	public void init() {
		Date.setDateTime(engine.simulation().from());
		initEngine();
		initExports();
		initPlots();
		initBehaviors();
	}

	public void execute() {
		if (engine.simulation().userInterface() != null) return;
		long steps = steps();
		for (int step = 0; step < steps; step++) run();
	}

	private void initEngine() {
		TaskManager.init();
		TimeoutManager.init();
	}

	private void initPlots() {
		plots = model.find(Output.Plot.class);
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
		stash.language = engine.simulation().output().language();
		extractors.stream().forEach(e -> stash.instances.addAll(e.buildStash()));
		return stash;
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	private void writeStash(Stash stash, File file) throws IOException {
		if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
		Files.write(file.toPath(), StashSerializer.serialize(stash));
	}

	private void initBehaviors() {
		List<Behavior> behaviors = model.find(Behavior.class);
		initStateCharts();
		TaskManager.addAll(behaviors.stream().flatMap(b -> b.taskList().stream()).collect(Collectors.toList()));
		behaviors.forEach(behavior -> behavior.startList().forEach(Behavior.Start::start));
		this.behaviors = behaviors.stream().filter(b -> !b.periodicList().isEmpty() && !b.is(ParallelizableBehavior.class)).collect(toList());
		this.parallelBehaviors = behaviors.stream().filter(b -> !b.periodicList().isEmpty() && b.is(ParallelizableBehavior.class)).collect(toList());
	}

	private void initStateCharts() {
		model.find(Behavior.class).stream()
				.map(Behavior::stateChartList)
				.flatMap(Collection::stream)
				.forEach(stateChart -> stateChart.current(stateChart.state(0)));
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
		return (engine.simulation().to().toEpochSecond(ZoneOffset.UTC) -
				engine.simulation().from().toEpochSecond(ZoneOffset.UTC));
	}

}
