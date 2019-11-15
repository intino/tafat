package io.intino.tafat.graph;

import io.intino.tafat.graph.*;
import java.util.List;
import io.intino.tafat.graph.SumusOutput;
import io.intino.tara.magritte.Layer;
import java.io.File;

public class SumusOutput extends io.intino.tafat.graph.Output implements io.intino.tara.magritte.tags.Terminal {
	protected java.lang.String language;
	protected java.lang.String simulationId;
	protected java.lang.String rootPath;
	protected io.intino.tafat.graph.functions.ToStash toStash;
	protected io.intino.tafat.graph.functions.CreateStash createStash;
	protected io.intino.tafat.graph.functions.WriteStash writeStash;
	protected java.util.List<io.intino.tafat.graph.SumusOutput.Extractor> extractorList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.SumusOutput.Export> exportList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.SumusOutput.Plot> plotList = new java.util.ArrayList<>();

	public SumusOutput(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String language() {
		return language;
	}

	public java.lang.String simulationId() {
		return simulationId;
	}

	public java.lang.String rootPath() {
		return rootPath;
	}

	public void toStash(List<? extends SumusOutput.Extractor> extractors) {
		 toStash.toStash(extractors);
	}

	public io.intino.tara.io.Stash createStash(List<? extends SumusOutput.Extractor> extractors) {
		return createStash.create(extractors);
	}

	public void writeStash(io.intino.tara.io.Stash stash, File file) {
		 writeStash.writeStash(stash, file);
	}

	public SumusOutput language(java.lang.String value) {
		this.language = value;
		return (SumusOutput) this;
	}

	public SumusOutput simulationId(java.lang.String value) {
		this.simulationId = value;
		return (SumusOutput) this;
	}

	public SumusOutput rootPath(java.lang.String value) {
		this.rootPath = value;
		return (SumusOutput) this;
	}

	public java.util.List<io.intino.tafat.graph.SumusOutput.Extractor> extractorList() {
		return java.util.Collections.unmodifiableList(extractorList);
	}

	public io.intino.tafat.graph.SumusOutput.Extractor extractor(int index) {
		return extractorList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.SumusOutput.Extractor> extractorList(java.util.function.Predicate<io.intino.tafat.graph.SumusOutput.Extractor> predicate) {
		return extractorList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.SumusOutput.Export> exportList() {
		return java.util.Collections.unmodifiableList(exportList);
	}

	public io.intino.tafat.graph.SumusOutput.Export export(int index) {
		return exportList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.SumusOutput.Export> exportList(java.util.function.Predicate<io.intino.tafat.graph.SumusOutput.Export> predicate) {
		return exportList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.SumusOutput.Plot> plotList() {
		return java.util.Collections.unmodifiableList(plotList);
	}

	public io.intino.tafat.graph.SumusOutput.Plot plot(int index) {
		return plotList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.SumusOutput.Plot> plotList(java.util.function.Predicate<io.intino.tafat.graph.SumusOutput.Plot> predicate) {
		return plotList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(extractorList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(exportList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(plotList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
		map.put("language", new java.util.ArrayList(java.util.Collections.singletonList(this.language)));
		map.put("simulationId", new java.util.ArrayList(java.util.Collections.singletonList(this.simulationId)));
		map.put("rootPath", new java.util.ArrayList(java.util.Collections.singletonList(this.rootPath)));
		map.put("toStash", this.toStash != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.toStash)) : java.util.Collections.emptyList());
		map.put("createStash", this.createStash != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.createStash)) : java.util.Collections.emptyList());
		map.put("writeStash", this.writeStash != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.writeStash)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("SumusOutput$Extractor")) this.extractorList.add(node.as(io.intino.tafat.graph.SumusOutput.Extractor.class));
		if (node.is("SumusOutput$Export")) this.exportList.add(node.as(io.intino.tafat.graph.SumusOutput.Export.class));
		if (node.is("SumusOutput$Plot")) this.plotList.add(node.as(io.intino.tafat.graph.SumusOutput.Plot.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("SumusOutput$Extractor")) this.extractorList.remove(node.as(io.intino.tafat.graph.SumusOutput.Extractor.class));
	        if (node.is("SumusOutput$Export")) this.exportList.remove(node.as(io.intino.tafat.graph.SumusOutput.Export.class));
	        if (node.is("SumusOutput$Plot")) this.plotList.remove(node.as(io.intino.tafat.graph.SumusOutput.Plot.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("language")) this.language = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("simulationId")) this.simulationId = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("rootPath")) this.rootPath = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("toStash")) this.toStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.ToStash.class).get(0);
		else if (name.equalsIgnoreCase("createStash")) this.createStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.CreateStash.class).get(0);
		else if (name.equalsIgnoreCase("writeStash")) this.writeStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.WriteStash.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("language")) this.language = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("simulationId")) this.simulationId = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("rootPath")) this.rootPath = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("toStash")) this.toStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.ToStash.class);
		else if (name.equalsIgnoreCase("createStash")) this.createStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.CreateStash.class);
		else if (name.equalsIgnoreCase("writeStash")) this.writeStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.WriteStash.class);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends io.intino.tafat.graph.Output.Create {


		public Create(java.lang.String name) {
			super(name);
		}

		public io.intino.tafat.graph.SumusOutput.Export export(java.lang.String label, io.intino.tafat.graph.functions.Collect collect, io.intino.tafat.graph.functions.Extractor extractMember) {
		    io.intino.tafat.graph.SumusOutput.Export newElement = core$().graph().concept(io.intino.tafat.graph.SumusOutput.Export.class).createNode(this.name, core$()).as(io.intino.tafat.graph.SumusOutput.Export.class);
			newElement.core$().set(newElement, "label", java.util.Collections.singletonList(label));
			newElement.core$().set(newElement, "collect", java.util.Collections.singletonList(collect));
			newElement.core$().set(newElement, "extractMember", java.util.Collections.singletonList(extractMember));
		    return newElement;
		}

		public io.intino.tafat.graph.SumusOutput.Plot plot(java.lang.String label, io.intino.tafat.graph.functions.Collect collect, io.intino.tafat.graph.rules.TimeScale timeScale, io.intino.tafat.graph.functions.Extractor extractFact) {
		    io.intino.tafat.graph.SumusOutput.Plot newElement = core$().graph().concept(io.intino.tafat.graph.SumusOutput.Plot.class).createNode(this.name, core$()).as(io.intino.tafat.graph.SumusOutput.Plot.class);
			newElement.core$().set(newElement, "label", java.util.Collections.singletonList(label));
			newElement.core$().set(newElement, "collect", java.util.Collections.singletonList(collect));
			newElement.core$().set(newElement, "timeScale", java.util.Collections.singletonList(timeScale));
			newElement.core$().set(newElement, "extractFact", java.util.Collections.singletonList(extractFact));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void export(java.util.function.Predicate<io.intino.tafat.graph.SumusOutput.Export> filter) {
			new java.util.ArrayList<>(exportList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void plot(java.util.function.Predicate<io.intino.tafat.graph.SumusOutput.Plot> filter) {
			new java.util.ArrayList<>(plotList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static abstract class Extractor  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.lang.String label;
		protected io.intino.tara.magritte.Expression<java.lang.String> path;
		protected io.intino.tafat.graph.functions.Collect collect;
		protected io.intino.tafat.graph.functions.Stash buildStash;

		public Extractor(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String label() {
			return label;
		}

		public String path() {
			return path.value();
		}

		public List<? extends io.intino.tara.magritte.Layer> collect() {
			return collect.collect();
		}

		public List<io.intino.tara.io.Node> buildStash() {
			return buildStash.build();
		}

		public Extractor path(io.intino.tara.magritte.Expression<java.lang.String> value) {
			this.path = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
			return (Extractor) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("label", new java.util.ArrayList(java.util.Collections.singletonList(this.label)));
			map.put("path", new java.util.ArrayList(java.util.Collections.singletonList(this.path)));
			map.put("collect", this.collect != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.collect)) : java.util.Collections.emptyList());
			map.put("buildStash", this.buildStash != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.buildStash)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("label")) this.label = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("path")) this.path = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
			else if (name.equalsIgnoreCase("collect")) this.collect = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Collect.class).get(0);
			else if (name.equalsIgnoreCase("buildStash")) this.buildStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Stash.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("label")) this.label = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("path")) this.path = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
			else if (name.equalsIgnoreCase("collect")) this.collect = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Collect.class);
			else if (name.equalsIgnoreCase("buildStash")) this.buildStash = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Stash.class);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create  {
			protected final java.lang.String name;

			public Create(java.lang.String name) {
				this.name = name;
			}



		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Export extends io.intino.tafat.graph.SumusOutput.Extractor implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.Extractor extractMember;

		public Export(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public io.intino.tara.io.Node extractMember(Layer layer) {
			return extractMember.extract(layer);
		}



		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("extractMember", this.extractMember != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.extractMember)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("extractMember")) this.extractMember = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Extractor.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("extractMember")) this.extractMember = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Extractor.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Plot extends io.intino.tafat.graph.SumusOutput.Extractor implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.rules.TimeScale timeScale;
		protected int timeout;
		protected io.intino.tara.magritte.Expression<java.lang.Boolean> checkStep;
		protected io.intino.tafat.graph.functions.Extractor extractFact;

		public Plot(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public io.intino.tafat.graph.rules.TimeScale timeScale() {
			return timeScale;
		}

		public int timeout() {
			return timeout;
		}

		public Boolean checkStep() {
			return checkStep.value();
		}

		public io.intino.tara.io.Node extractFact(Layer layer) {
			return extractFact.extract(layer);
		}

		public Plot timeout(int value) {
			this.timeout = value;
			return (Plot) this;
		}

		public Plot checkStep(io.intino.tara.magritte.Expression<java.lang.Boolean> value) {
			this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
			return (Plot) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("timeScale", new java.util.ArrayList(java.util.Collections.singletonList(this.timeScale)));
			map.put("timeout", new java.util.ArrayList(java.util.Collections.singletonList(this.timeout)));
			map.put("checkStep", new java.util.ArrayList(java.util.Collections.singletonList(this.checkStep)));
			map.put("extractFact", this.extractFact != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.extractFact)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("timeScale")) this.timeScale = io.intino.tara.magritte.loaders.WordLoader.load(values, io.intino.tafat.graph.rules.TimeScale.class, this).get(0);
			else if (name.equalsIgnoreCase("timeout")) this.timeout = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
			else if (name.equalsIgnoreCase("extractFact")) this.extractFact = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Extractor.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("timeScale")) this.timeScale = (io.intino.tafat.graph.rules.TimeScale) values.get(0);
			else if (name.equalsIgnoreCase("timeout")) this.timeout = (java.lang.Integer) values.get(0);
			else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
			else if (name.equalsIgnoreCase("extractFact")) this.extractFact = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Extractor.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}