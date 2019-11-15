package test.graph;

import test.graph.*;

public class Environment  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected double temperature;
	protected java.util.List<test.graph.Environment.Building> buildingList = new java.util.ArrayList<>();

	public Environment(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public double temperature() {
		return temperature;
	}

	public Environment temperature(double value) {
		this.temperature = value;
		return (Environment) this;
	}

	public java.util.List<test.graph.Environment.Building> buildingList() {
		return java.util.Collections.unmodifiableList(buildingList);
	}

	public test.graph.Environment.Building building(int index) {
		return buildingList.get(index);
	}

	public java.util.List<test.graph.Environment.Building> buildingList(java.util.function.Predicate<test.graph.Environment.Building> predicate) {
		return buildingList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(buildingList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("temperature", new java.util.ArrayList(java.util.Collections.singletonList(this.temperature)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Environment$Building")) this.buildingList.add(node.as(test.graph.Environment.Building.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Environment$Building")) this.buildingList.remove(node.as(test.graph.Environment.Building.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("temperature")) this.temperature = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("temperature")) this.temperature = (java.lang.Double) values.get(0);
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

		public test.graph.Environment.Building building(double temperature) {
		    test.graph.Environment.Building newElement = core$().graph().concept(test.graph.Environment.Building.class).createNode(this.name, core$()).as(test.graph.Environment.Building.class);
			newElement.core$().set(newElement, "temperature", java.util.Collections.singletonList(temperature));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void building(java.util.function.Predicate<test.graph.Environment.Building> filter) {
			new java.util.ArrayList<>(buildingList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Building  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected double temperature;
		protected java.util.List<test.graph.Environment.Building.Radiator> radiatorList = new java.util.ArrayList<>();
		protected test.graph.Environment.Building.Thermal _thermal;

		public Building(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public double temperature() {
			return temperature;
		}

		public Building temperature(double value) {
			this.temperature = value;
			return (Building) this;
		}

		public java.util.List<test.graph.Environment.Building.Radiator> radiatorList() {
			return java.util.Collections.unmodifiableList(radiatorList);
		}

		public test.graph.Environment.Building.Radiator radiator(int index) {
			return radiatorList.get(index);
		}

		public java.util.List<test.graph.Environment.Building.Radiator> radiatorList(java.util.function.Predicate<test.graph.Environment.Building.Radiator> predicate) {
			return radiatorList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}



		public test.graph.Environment.Building.Thermal asThermal() {
			return a$(test.graph.Environment.Building.Thermal.class);
		}

		public test.graph.Environment.Building.Thermal asThermal(double temperature) {
			test.graph.Environment.Building.Thermal newElement = core$().addAspect(test.graph.Environment.Building.Thermal.class);
			newElement.core$().set(newElement, "temperature", java.util.Collections.singletonList(temperature));
		    return newElement;
		}

		public boolean isThermal() {
			return core$().is(test.graph.Environment.Building.Thermal.class);
		}

		public void removeThermal() {
			core$().removeAspect(test.graph.Environment.Building.Thermal.class);
		}

		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
			new java.util.ArrayList<>(radiatorList).forEach(c -> components.add(c.core$()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("temperature", new java.util.ArrayList(java.util.Collections.singletonList(this.temperature)));
			return map;
		}

		@Override
		protected void addNode$(io.intino.tara.magritte.Node node) {
			super.addNode$(node);
			if (node.is("Environment$Building$Radiator")) this.radiatorList.add(node.as(test.graph.Environment.Building.Radiator.class));
		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);
		        if (node.is("Environment$Building$Radiator")) this.radiatorList.remove(node.as(test.graph.Environment.Building.Radiator.class));
		    }

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("temperature")) this.temperature = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("temperature")) this.temperature = (java.lang.Double) values.get(0);
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

			public test.graph.Environment.Building.Radiator radiator(double temperature) {
			    test.graph.Environment.Building.Radiator newElement = core$().graph().concept(test.graph.Environment.Building.Radiator.class).createNode(this.name, core$()).as(test.graph.Environment.Building.Radiator.class);
				newElement.core$().set(newElement, "temperature", java.util.Collections.singletonList(temperature));
			    return newElement;
			}

		}

		public Clear clear() {
			return new Clear();
		}

		public class Clear  {
			public void radiator(java.util.function.Predicate<test.graph.Environment.Building.Radiator> filter) {
				new java.util.ArrayList<>(radiatorList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}
		}

		public static class Radiator  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
			protected double temperature;
			protected test.graph.Environment.Building.Radiator.Thermal _thermal;

			public Radiator(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public double temperature() {
				return temperature;
			}

			public Radiator temperature(double value) {
				this.temperature = value;
				return (Radiator) this;
			}





			public test.graph.Environment.Building.Radiator.Thermal asThermal() {
				return a$(test.graph.Environment.Building.Radiator.Thermal.class);
			}

			public test.graph.Environment.Building.Radiator.Thermal asThermal(double temperature) {
				test.graph.Environment.Building.Radiator.Thermal newElement = core$().addAspect(test.graph.Environment.Building.Radiator.Thermal.class);
				newElement.core$().set(newElement, "temperature", java.util.Collections.singletonList(temperature));
			    return newElement;
			}

			public boolean isThermal() {
				return core$().is(test.graph.Environment.Building.Radiator.Thermal.class);
			}

			public void removeThermal() {
				core$().removeAspect(test.graph.Environment.Building.Radiator.Thermal.class);
			}

			protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
				java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

				return new java.util.ArrayList<>(components);
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("temperature", new java.util.ArrayList(java.util.Collections.singletonList(this.temperature)));
				return map;
			}

			@Override
			protected void addNode$(io.intino.tara.magritte.Node node) {
				super.addNode$(node);

			}

			@Override
			    protected void removeNode$(io.intino.tara.magritte.Node node) {
			        super.removeNode$(node);

			    }

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("temperature")) this.temperature = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("temperature")) this.temperature = (java.lang.Double) values.get(0);
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

			public Clear clear() {
				return new Clear();
			}

			public class Clear  {

			}

			public static class Thermal  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {

				protected test.graph.Environment.Building.Radiator _radiator;
				protected io.intino.tafat.graph.Entity.Behavior _metaType;

				public Thermal(io.intino.tara.magritte.Node node) {
					super(node);
					_metaType = a$(io.intino.tafat.graph.Entity.Behavior.class);
				}

				public double temperature() {
					return _radiator.temperature();
				}

				public int step() {
					return _metaType.step();
				}

				public Thermal temperature(double value) {
					this._radiator.temperature(value);
					return (Thermal) this;
				}

				public Thermal step(int value) {
					this._metaType.step(value);
					return (Thermal) this;
				}
				public test.graph.Environment.Building.Radiator asRadiator() {
					return (test.graph.Environment.Building.Radiator) a$(test.graph.Environment.Building.Radiator.class);
				}

				@Override
				protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
					java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
					map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this._metaType.step())));
					return map;
				}

				@Override
				protected void load$(java.lang.String name, java.util.List<?> values) {
					super.load$(name, values);

					core$().load(_metaType, name, values);

				}

				@Override
				protected void set$(java.lang.String name, java.util.List<?> values) {
					super.set$(name, values);

					core$().set(_metaType, name, values);

				}

				@Override
				protected void sync$(io.intino.tara.magritte.Layer layer) {
					super.sync$(layer);
				    if (layer instanceof test.graph.Environment.Building.Radiator) _radiator = (test.graph.Environment.Building.Radiator) layer;

				}

				public test.graph.TestGraph graph() {
					return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
				}
			}


			public test.graph.TestGraph graph() {
				return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
			}
		}

		public static class Thermal  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {


			protected test.graph.Environment.Building _building;
			protected io.intino.tafat.graph.Entity.Behavior _metaType;

			public Thermal(io.intino.tara.magritte.Node node) {
				super(node);
				_metaType = a$(io.intino.tafat.graph.Entity.Behavior.class);
			}

			public double temperature() {
				return _building.temperature();
			}

			public int step() {
				return _metaType.step();
			}

			public Thermal temperature(double value) {
				this._building.temperature(value);
				return (Thermal) this;
			}

			public Thermal step(int value) {
				this._metaType.step(value);
				return (Thermal) this;
			}

			public java.util.List<test.graph.Environment.Building.Radiator> radiatorList() {
				return (java.util.List<test.graph.Environment.Building.Radiator>) _building.radiatorList();
			}

			public test.graph.Environment.Building.Radiator radiatorList(int index) {
				return _building.radiatorList().get(index);
			}


			public test.graph.Environment.Building asBuilding() {
				return (test.graph.Environment.Building) a$(test.graph.Environment.Building.class);
			}

			protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
				java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

				return new java.util.ArrayList<>(components);
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this._metaType.step())));
				return map;
			}

			@Override
			protected void addNode$(io.intino.tara.magritte.Node node) {
				super.addNode$(node);

			}

			@Override
			    protected void removeNode$(io.intino.tara.magritte.Node node) {
			        super.removeNode$(node);

			    }

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);

				core$().load(_metaType, name, values);

			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);

				core$().set(_metaType, name, values);

			}

			@Override
			protected void sync$(io.intino.tara.magritte.Layer layer) {
				super.sync$(layer);
			    if (layer instanceof test.graph.Environment.Building) _building = (test.graph.Environment.Building) layer;

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

				public test.graph.Environment.Building.Radiator radiator(double temperature) {
				    test.graph.Environment.Building.Radiator newElement = core$().graph().concept(test.graph.Environment.Building.Radiator.class).createNode(this.name, core$()).as(test.graph.Environment.Building.Radiator.class);
					newElement.core$().set(newElement, "temperature", java.util.Collections.singletonList(temperature));
				    return newElement;
				}

			}

			public Clear clear() {
				return new Clear();
			}

			public class Clear  {
				public void radiator(java.util.function.Predicate<test.graph.Environment.Building.Radiator> filter) {
					new java.util.ArrayList<>(radiatorList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
				}
			}



			public test.graph.TestGraph graph() {
				return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
			}
		}


		public test.graph.TestGraph graph() {
			return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
		}
	}


	public test.graph.TestGraph graph() {
		return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
	}
}