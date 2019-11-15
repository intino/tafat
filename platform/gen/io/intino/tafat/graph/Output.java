package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Output  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.functions.Action init;
	protected io.intino.tafat.graph.functions.Action process;
	protected io.intino.tafat.graph.functions.Action terminate;

	public Output(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public void init() {
		 init.execute();
	}

	public void process() {
		 process.execute();
	}

	public void terminate() {
		 terminate.execute();
	}



	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("init", this.init != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.init)) : java.util.Collections.emptyList());
		map.put("process", this.process != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.process)) : java.util.Collections.emptyList());
		map.put("terminate", this.terminate != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.terminate)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("init")) this.init = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
		else if (name.equalsIgnoreCase("process")) this.process = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
		else if (name.equalsIgnoreCase("terminate")) this.terminate = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("init")) this.init = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
		else if (name.equalsIgnoreCase("process")) this.process = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
		else if (name.equalsIgnoreCase("terminate")) this.terminate = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
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