package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class SystemDynamic extends io.intino.tafat.graph.PeriodicActivity implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tara.magritte.Expression<io.intino.tafat.engine.DifferentialEquation> odeProvider;
	protected boolean enabled;
	protected Solver solver;

	public enum Solver {
		Euler;
	}
	protected double step;
	protected int timesPerSecond;
	protected io.intino.tafat.engine.DifferentialEquation differentialEquation;
	protected org.opensourcephysics.numerics.ODESolver odeSolver;

	public SystemDynamic(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public io.intino.tafat.engine.DifferentialEquation odeProvider() {
		return odeProvider.value();
	}

	public boolean enabled() {
		return enabled;
	}

	public Solver solver() {
		return solver;
	}

	public double step() {
		return step;
	}

	public int timesPerSecond() {
		return timesPerSecond;
	}

	public io.intino.tafat.engine.DifferentialEquation differentialEquation() {
		return differentialEquation;
	}

	public org.opensourcephysics.numerics.ODESolver odeSolver() {
		return odeSolver;
	}

	public SystemDynamic odeProvider(io.intino.tara.magritte.Expression<io.intino.tafat.engine.DifferentialEquation> value) {
		this.odeProvider = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
		return (SystemDynamic) this;
	}

	public SystemDynamic enabled(boolean value) {
		this.enabled = value;
		return (SystemDynamic) this;
	}

	public SystemDynamic solver(io.intino.tafat.graph.SystemDynamic.Solver value) {
		this.solver = value;
		return (SystemDynamic) this;
	}

	public SystemDynamic step(double value) {
		this.step = value;
		return (SystemDynamic) this;
	}

	public SystemDynamic timesPerSecond(int value) {
		this.timesPerSecond = value;
		return (SystemDynamic) this;
	}

	public SystemDynamic differentialEquation(io.intino.tafat.engine.DifferentialEquation value) {
		this.differentialEquation = value;
		return (SystemDynamic) this;
	}

	public SystemDynamic odeSolver(org.opensourcephysics.numerics.ODESolver value) {
		this.odeSolver = value;
		return (SystemDynamic) this;
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
		map.put("odeProvider", new java.util.ArrayList(java.util.Collections.singletonList(this.odeProvider)));
		map.put("enabled", new java.util.ArrayList(java.util.Collections.singletonList(this.enabled)));
		map.put("solver", new java.util.ArrayList(java.util.Collections.singletonList(this.solver)));
		map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this.step)));
		map.put("timesPerSecond", new java.util.ArrayList(java.util.Collections.singletonList(this.timesPerSecond)));
		map.put("differentialEquation", new java.util.ArrayList(java.util.Collections.singletonList(this.differentialEquation)));
		map.put("odeSolver", new java.util.ArrayList(java.util.Collections.singletonList(this.odeSolver)));
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("odeProvider")) this.odeProvider = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		else if (name.equalsIgnoreCase("enabled")) this.enabled = io.intino.tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("solver")) this.solver = io.intino.tara.magritte.loaders.WordLoader.load(values, Solver.class, this).get(0);
		else if (name.equalsIgnoreCase("step")) this.step = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("timesPerSecond")) this.timesPerSecond = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("differentialEquation")) this.differentialEquation = io.intino.tara.magritte.loaders.ObjectLoader.load(values,io.intino.tafat.engine.DifferentialEquation.class, this).get(0);
		else if (name.equalsIgnoreCase("odeSolver")) this.odeSolver = io.intino.tara.magritte.loaders.ObjectLoader.load(values,org.opensourcephysics.numerics.ODESolver.class, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("odeProvider")) this.odeProvider = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		else if (name.equalsIgnoreCase("enabled")) this.enabled = (java.lang.Boolean) values.get(0);
		else if (name.equalsIgnoreCase("solver")) this.solver = (Solver) values.get(0);
		else if (name.equalsIgnoreCase("step")) this.step = (java.lang.Double) values.get(0);
		else if (name.equalsIgnoreCase("timesPerSecond")) this.timesPerSecond = (java.lang.Integer) values.get(0);
		else if (name.equalsIgnoreCase("differentialEquation")) this.differentialEquation = (io.intino.tafat.engine.DifferentialEquation) values.get(0);
		else if (name.equalsIgnoreCase("odeSolver")) this.odeSolver = (org.opensourcephysics.numerics.ODESolver) values.get(0);
	}

	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}