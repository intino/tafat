package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Fmu extends io.intino.tafat.graph.PeriodicActivity implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected java.lang.String file;
	protected double step;
	protected org.javafmi.wrapper.Simulation wrapper;
	protected java.util.List<io.intino.tafat.graph.Fmu.Input> inputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.RealInput> realInputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.IntegerInput> integerInputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.BooleanInput> booleanInputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.StringInput> stringInputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.Output> outputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.RealOutput> realOutputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.IntegerOutput> integerOutputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.BooleanOutput> booleanOutputList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Fmu.StringOutput> stringOutputList = new java.util.ArrayList<>();

	public Fmu(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String file() {
		return file;
	}

	public double step() {
		return step;
	}

	public org.javafmi.wrapper.Simulation wrapper() {
		return wrapper;
	}

	public Fmu file(java.lang.String value) {
		this.file = value;
		return (Fmu) this;
	}

	public Fmu step(double value) {
		this.step = value;
		return (Fmu) this;
	}

	public Fmu wrapper(org.javafmi.wrapper.Simulation value) {
		this.wrapper = value;
		return (Fmu) this;
	}

	public java.util.List<io.intino.tafat.graph.Fmu.Input> inputList() {
		return java.util.Collections.unmodifiableList(inputList);
	}

	public io.intino.tafat.graph.Fmu.Input input(int index) {
		return inputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.Input> inputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.Input> predicate) {
		return inputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.RealInput> realInputList() {
		return java.util.Collections.unmodifiableList(realInputList);
	}

	public io.intino.tafat.graph.Fmu.RealInput realInput(int index) {
		return realInputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.RealInput> realInputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.RealInput> predicate) {
		return realInputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.IntegerInput> integerInputList() {
		return java.util.Collections.unmodifiableList(integerInputList);
	}

	public io.intino.tafat.graph.Fmu.IntegerInput integerInput(int index) {
		return integerInputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.IntegerInput> integerInputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.IntegerInput> predicate) {
		return integerInputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.BooleanInput> booleanInputList() {
		return java.util.Collections.unmodifiableList(booleanInputList);
	}

	public io.intino.tafat.graph.Fmu.BooleanInput booleanInput(int index) {
		return booleanInputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.BooleanInput> booleanInputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.BooleanInput> predicate) {
		return booleanInputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.StringInput> stringInputList() {
		return java.util.Collections.unmodifiableList(stringInputList);
	}

	public io.intino.tafat.graph.Fmu.StringInput stringInput(int index) {
		return stringInputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.StringInput> stringInputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.StringInput> predicate) {
		return stringInputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.Output> outputList() {
		return java.util.Collections.unmodifiableList(outputList);
	}

	public io.intino.tafat.graph.Fmu.Output output(int index) {
		return outputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.Output> outputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.Output> predicate) {
		return outputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.RealOutput> realOutputList() {
		return java.util.Collections.unmodifiableList(realOutputList);
	}

	public io.intino.tafat.graph.Fmu.RealOutput realOutput(int index) {
		return realOutputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.RealOutput> realOutputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.RealOutput> predicate) {
		return realOutputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.IntegerOutput> integerOutputList() {
		return java.util.Collections.unmodifiableList(integerOutputList);
	}

	public io.intino.tafat.graph.Fmu.IntegerOutput integerOutput(int index) {
		return integerOutputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.IntegerOutput> integerOutputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.IntegerOutput> predicate) {
		return integerOutputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.BooleanOutput> booleanOutputList() {
		return java.util.Collections.unmodifiableList(booleanOutputList);
	}

	public io.intino.tafat.graph.Fmu.BooleanOutput booleanOutput(int index) {
		return booleanOutputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.BooleanOutput> booleanOutputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.BooleanOutput> predicate) {
		return booleanOutputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Fmu.StringOutput> stringOutputList() {
		return java.util.Collections.unmodifiableList(stringOutputList);
	}

	public io.intino.tafat.graph.Fmu.StringOutput stringOutput(int index) {
		return stringOutputList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Fmu.StringOutput> stringOutputList(java.util.function.Predicate<io.intino.tafat.graph.Fmu.StringOutput> predicate) {
		return stringOutputList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(inputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(realInputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(integerInputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(booleanInputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(stringInputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(outputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(realOutputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(integerOutputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(booleanOutputList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(stringOutputList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
		map.put("file", new java.util.ArrayList(java.util.Collections.singletonList(this.file)));
		map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this.step)));
		map.put("wrapper", new java.util.ArrayList(java.util.Collections.singletonList(this.wrapper)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Fmu$Input")) this.inputList.add(node.as(io.intino.tafat.graph.Fmu.Input.class));
		if (node.is("Fmu$RealInput")) this.realInputList.add(node.as(io.intino.tafat.graph.Fmu.RealInput.class));
		if (node.is("Fmu$IntegerInput")) this.integerInputList.add(node.as(io.intino.tafat.graph.Fmu.IntegerInput.class));
		if (node.is("Fmu$BooleanInput")) this.booleanInputList.add(node.as(io.intino.tafat.graph.Fmu.BooleanInput.class));
		if (node.is("Fmu$StringInput")) this.stringInputList.add(node.as(io.intino.tafat.graph.Fmu.StringInput.class));
		if (node.is("Fmu$Output")) this.outputList.add(node.as(io.intino.tafat.graph.Fmu.Output.class));
		if (node.is("Fmu$RealOutput")) this.realOutputList.add(node.as(io.intino.tafat.graph.Fmu.RealOutput.class));
		if (node.is("Fmu$IntegerOutput")) this.integerOutputList.add(node.as(io.intino.tafat.graph.Fmu.IntegerOutput.class));
		if (node.is("Fmu$BooleanOutput")) this.booleanOutputList.add(node.as(io.intino.tafat.graph.Fmu.BooleanOutput.class));
		if (node.is("Fmu$StringOutput")) this.stringOutputList.add(node.as(io.intino.tafat.graph.Fmu.StringOutput.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Fmu$Input")) this.inputList.remove(node.as(io.intino.tafat.graph.Fmu.Input.class));
	        if (node.is("Fmu$RealInput")) this.realInputList.remove(node.as(io.intino.tafat.graph.Fmu.RealInput.class));
	        if (node.is("Fmu$IntegerInput")) this.integerInputList.remove(node.as(io.intino.tafat.graph.Fmu.IntegerInput.class));
	        if (node.is("Fmu$BooleanInput")) this.booleanInputList.remove(node.as(io.intino.tafat.graph.Fmu.BooleanInput.class));
	        if (node.is("Fmu$StringInput")) this.stringInputList.remove(node.as(io.intino.tafat.graph.Fmu.StringInput.class));
	        if (node.is("Fmu$Output")) this.outputList.remove(node.as(io.intino.tafat.graph.Fmu.Output.class));
	        if (node.is("Fmu$RealOutput")) this.realOutputList.remove(node.as(io.intino.tafat.graph.Fmu.RealOutput.class));
	        if (node.is("Fmu$IntegerOutput")) this.integerOutputList.remove(node.as(io.intino.tafat.graph.Fmu.IntegerOutput.class));
	        if (node.is("Fmu$BooleanOutput")) this.booleanOutputList.remove(node.as(io.intino.tafat.graph.Fmu.BooleanOutput.class));
	        if (node.is("Fmu$StringOutput")) this.stringOutputList.remove(node.as(io.intino.tafat.graph.Fmu.StringOutput.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("file")) this.file = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("step")) this.step = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("wrapper")) this.wrapper = io.intino.tara.magritte.loaders.ObjectLoader.load(values,org.javafmi.wrapper.Simulation.class, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("file")) this.file = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("step")) this.step = (java.lang.Double) values.get(0);
		else if (name.equalsIgnoreCase("wrapper")) this.wrapper = (org.javafmi.wrapper.Simulation) values.get(0);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends io.intino.tafat.graph.PeriodicActivity.Create {


		public Create(java.lang.String name) {
			super(name);
		}

		public io.intino.tafat.graph.Fmu.RealInput realInput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PushReal push) {
		    io.intino.tafat.graph.Fmu.RealInput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.RealInput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.RealInput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "push", java.util.Collections.singletonList(push));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu.IntegerInput integerInput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PushInteger push) {
		    io.intino.tafat.graph.Fmu.IntegerInput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.IntegerInput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.IntegerInput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "push", java.util.Collections.singletonList(push));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu.BooleanInput booleanInput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PushBoolean push) {
		    io.intino.tafat.graph.Fmu.BooleanInput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.BooleanInput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.BooleanInput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "push", java.util.Collections.singletonList(push));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu.StringInput stringInput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PushString push) {
		    io.intino.tafat.graph.Fmu.StringInput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.StringInput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.StringInput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "push", java.util.Collections.singletonList(push));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu.RealOutput realOutput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PullReal pull) {
		    io.intino.tafat.graph.Fmu.RealOutput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.RealOutput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.RealOutput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "pull", java.util.Collections.singletonList(pull));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu.IntegerOutput integerOutput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PullInteger pull) {
		    io.intino.tafat.graph.Fmu.IntegerOutput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.IntegerOutput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.IntegerOutput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "pull", java.util.Collections.singletonList(pull));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu.BooleanOutput booleanOutput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PullBoolean pull) {
		    io.intino.tafat.graph.Fmu.BooleanOutput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.BooleanOutput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.BooleanOutput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "pull", java.util.Collections.singletonList(pull));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu.StringOutput stringOutput(java.lang.String fmuVariableName, io.intino.tafat.graph.functions.PullString pull) {
		    io.intino.tafat.graph.Fmu.StringOutput newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.StringOutput.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.StringOutput.class);
			newElement.core$().set(newElement, "fmuVariableName", java.util.Collections.singletonList(fmuVariableName));
			newElement.core$().set(newElement, "pull", java.util.Collections.singletonList(pull));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void realInput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.RealInput> filter) {
			new java.util.ArrayList<>(realInputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void integerInput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.IntegerInput> filter) {
			new java.util.ArrayList<>(integerInputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void booleanInput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.BooleanInput> filter) {
			new java.util.ArrayList<>(booleanInputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void stringInput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.StringInput> filter) {
			new java.util.ArrayList<>(stringInputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void realOutput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.RealOutput> filter) {
			new java.util.ArrayList<>(realOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void integerOutput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.IntegerOutput> filter) {
			new java.util.ArrayList<>(integerOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void booleanOutput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.BooleanOutput> filter) {
			new java.util.ArrayList<>(booleanOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void stringOutput(java.util.function.Predicate<io.intino.tafat.graph.Fmu.StringOutput> filter) {
			new java.util.ArrayList<>(stringOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static abstract class Input  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected java.lang.String fmuVariableName;

		public Input(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String fmuVariableName() {
			return fmuVariableName;
		}

		public Input fmuVariableName(java.lang.String value) {
			this.fmuVariableName = value;
			return (Input) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("fmuVariableName", new java.util.ArrayList(java.util.Collections.singletonList(this.fmuVariableName)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("fmuVariableName")) this.fmuVariableName = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("fmuVariableName")) this.fmuVariableName = (java.lang.String) values.get(0);
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

	public static class RealInput extends io.intino.tafat.graph.Fmu.Input implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PushReal push;

		public RealInput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public double push() {
			return push.value();
		}

		public RealInput push(io.intino.tafat.graph.functions.PushReal value) {
			this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(push, this, io.intino.tafat.graph.functions.PushReal.class);
			return (RealInput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("push", this.push != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.push)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PushReal.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PushReal.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class IntegerInput extends io.intino.tafat.graph.Fmu.Input implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PushInteger push;

		public IntegerInput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public int push() {
			return push.value();
		}

		public IntegerInput push(io.intino.tafat.graph.functions.PushInteger value) {
			this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(push, this, io.intino.tafat.graph.functions.PushInteger.class);
			return (IntegerInput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("push", this.push != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.push)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PushInteger.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PushInteger.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class BooleanInput extends io.intino.tafat.graph.Fmu.Input implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PushBoolean push;

		public BooleanInput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public boolean push() {
			return push.value();
		}

		public BooleanInput push(io.intino.tafat.graph.functions.PushBoolean value) {
			this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(push, this, io.intino.tafat.graph.functions.PushBoolean.class);
			return (BooleanInput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("push", this.push != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.push)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PushBoolean.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PushBoolean.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class StringInput extends io.intino.tafat.graph.Fmu.Input implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PushString push;

		public StringInput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public String push() {
			return push.value();
		}

		public StringInput push(io.intino.tafat.graph.functions.PushString value) {
			this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(push, this, io.intino.tafat.graph.functions.PushString.class);
			return (StringInput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("push", this.push != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.push)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PushString.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("push")) this.push = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PushString.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static abstract class Output  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected java.lang.String fmuVariableName;

		public Output(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String fmuVariableName() {
			return fmuVariableName;
		}

		public Output fmuVariableName(java.lang.String value) {
			this.fmuVariableName = value;
			return (Output) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("fmuVariableName", new java.util.ArrayList(java.util.Collections.singletonList(this.fmuVariableName)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("fmuVariableName")) this.fmuVariableName = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("fmuVariableName")) this.fmuVariableName = (java.lang.String) values.get(0);
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

	public static class RealOutput extends io.intino.tafat.graph.Fmu.Output implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PullReal pull;

		public RealOutput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public void pull(double value) {
			 pull.pull(value);
		}

		public RealOutput pull(io.intino.tafat.graph.functions.PullReal value) {
			this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(pull, this, io.intino.tafat.graph.functions.PullReal.class);
			return (RealOutput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("pull", this.pull != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.pull)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PullReal.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PullReal.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class IntegerOutput extends io.intino.tafat.graph.Fmu.Output implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PullInteger pull;

		public IntegerOutput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public void pull(int value) {
			 pull.pull(value);
		}

		public IntegerOutput pull(io.intino.tafat.graph.functions.PullInteger value) {
			this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(pull, this, io.intino.tafat.graph.functions.PullInteger.class);
			return (IntegerOutput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("pull", this.pull != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.pull)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PullInteger.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PullInteger.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class BooleanOutput extends io.intino.tafat.graph.Fmu.Output implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PullBoolean pull;

		public BooleanOutput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public void pull(boolean value) {
			 pull.pull(value);
		}

		public BooleanOutput pull(io.intino.tafat.graph.functions.PullBoolean value) {
			this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(pull, this, io.intino.tafat.graph.functions.PullBoolean.class);
			return (BooleanOutput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("pull", this.pull != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.pull)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PullBoolean.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PullBoolean.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class StringOutput extends io.intino.tafat.graph.Fmu.Output implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.PullString pull;

		public StringOutput(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public void pull(String value) {
			 pull.pull(value);
		}

		public StringOutput pull(io.intino.tafat.graph.functions.PullString value) {
			this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(pull, this, io.intino.tafat.graph.functions.PullString.class);
			return (StringOutput) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("pull", this.pull != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.pull)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.PullString.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("pull")) this.pull = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.PullString.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}