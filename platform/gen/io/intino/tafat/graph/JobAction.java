package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public abstract class JobAction  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected java.util.List<io.intino.tafat.graph.Job> job = new java.util.ArrayList<>();

	public JobAction(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<io.intino.tafat.graph.Job> job() {
		return job;
	}

	public io.intino.tafat.graph.Job job(int index) {
		return job.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Job> job(java.util.function.Predicate<io.intino.tafat.graph.Job> predicate) {
		return job().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("job", this.job);
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("job")) this.job = io.intino.tara.magritte.loaders.NodeLoader.load(values,  io.intino.tafat.graph.Job.class, this);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("job")) this.job = ((java.util.List<java.lang.Object>) values).stream().
			map(s -> graph().core$().load(((io.intino.tara.magritte.Layer) s).core$().id()).as(io.intino.tafat.graph.Job.class)).collect(java.util.stream.Collectors.toList());
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