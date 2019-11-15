package io.intino.tafat.graph;

import io.intino.tafat.graph.*;
import com.google.gson.JsonElement;

public class UserInterface  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tara.magritte.Expression<java.lang.String> data;
	protected io.intino.tara.magritte.Expression<java.lang.String> values;
	protected java.lang.String title;
	protected java.net.URL logo;
	protected int port;
	protected java.util.List<io.intino.tafat.graph.UserInterface.GraphicalComponent> graphicalComponentList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.UserInterface.Heatmap> heatmapList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.UserInterface.LineChart> lineChartList = new java.util.ArrayList<>();

	public UserInterface(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public String data() {
		return data.value();
	}

	public String values() {
		return values.value();
	}

	public java.lang.String title() {
		return title;
	}

	public java.net.URL logo() {
		return logo;
	}

	public int port() {
		return port;
	}

	public UserInterface data(io.intino.tara.magritte.Expression<java.lang.String> value) {
		this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
		return (UserInterface) this;
	}

	public UserInterface values(io.intino.tara.magritte.Expression<java.lang.String> value) {
		this.values = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
		return (UserInterface) this;
	}

	public UserInterface title(java.lang.String value) {
		this.title = value;
		return (UserInterface) this;
	}

	public UserInterface logo(java.net.URL url, String destination) {
		if (url == null) this.logo = null;
		else this.logo = graph().core$().save(url, destination, this.logo, core$());
		return (UserInterface) this;
	}

	public UserInterface logo(java.io.InputStream stream, String destination) {
		if (stream == null) this.logo = null;
		else this.logo = graph().core$().save(stream, destination, this.logo, core$());
		return (UserInterface) this;
	}

	public UserInterface port(int value) {
		this.port = value;
		return (UserInterface) this;
	}

	public java.util.List<io.intino.tafat.graph.UserInterface.GraphicalComponent> graphicalComponentList() {
		return java.util.Collections.unmodifiableList(graphicalComponentList);
	}

	public io.intino.tafat.graph.UserInterface.GraphicalComponent graphicalComponent(int index) {
		return graphicalComponentList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.UserInterface.GraphicalComponent> graphicalComponentList(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.GraphicalComponent> predicate) {
		return graphicalComponentList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap> heatmapList() {
		return java.util.Collections.unmodifiableList(heatmapList);
	}

	public io.intino.tafat.graph.UserInterface.Heatmap heatmap(int index) {
		return heatmapList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap> heatmapList(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.Heatmap> predicate) {
		return heatmapList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.UserInterface.LineChart> lineChartList() {
		return java.util.Collections.unmodifiableList(lineChartList);
	}

	public io.intino.tafat.graph.UserInterface.LineChart lineChart(int index) {
		return lineChartList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.UserInterface.LineChart> lineChartList(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.LineChart> predicate) {
		return lineChartList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(graphicalComponentList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(heatmapList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(lineChartList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("data", new java.util.ArrayList(java.util.Collections.singletonList(this.data)));
		map.put("values", new java.util.ArrayList(java.util.Collections.singletonList(this.values)));
		map.put("title", new java.util.ArrayList(java.util.Collections.singletonList(this.title)));
		map.put("logo", new java.util.ArrayList(java.util.Collections.singletonList(this.logo)));
		map.put("port", new java.util.ArrayList(java.util.Collections.singletonList(this.port)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("UserInterface$GraphicalComponent")) this.graphicalComponentList.add(node.as(io.intino.tafat.graph.UserInterface.GraphicalComponent.class));
		if (node.is("UserInterface$Heatmap")) this.heatmapList.add(node.as(io.intino.tafat.graph.UserInterface.Heatmap.class));
		if (node.is("UserInterface$LineChart")) this.lineChartList.add(node.as(io.intino.tafat.graph.UserInterface.LineChart.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("UserInterface$GraphicalComponent")) this.graphicalComponentList.remove(node.as(io.intino.tafat.graph.UserInterface.GraphicalComponent.class));
	        if (node.is("UserInterface$Heatmap")) this.heatmapList.remove(node.as(io.intino.tafat.graph.UserInterface.Heatmap.class));
	        if (node.is("UserInterface$LineChart")) this.lineChartList.remove(node.as(io.intino.tafat.graph.UserInterface.LineChart.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		else if (name.equalsIgnoreCase("values")) this.values = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		else if (name.equalsIgnoreCase("title")) this.title = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("logo")) this.logo = io.intino.tara.magritte.loaders.ResourceLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("port")) this.port = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		else if (name.equalsIgnoreCase("values")) this.values = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		else if (name.equalsIgnoreCase("title")) this.title = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("logo")) this.logo = (java.net.URL) values.get(0);
		else if (name.equalsIgnoreCase("port")) this.port = (java.lang.Integer) values.get(0);
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

		public io.intino.tafat.graph.UserInterface.Heatmap heatmap(java.lang.String title, java.net.URL background, io.intino.tafat.graph.rules.Color color) {
		    io.intino.tafat.graph.UserInterface.Heatmap newElement = core$().graph().concept(io.intino.tafat.graph.UserInterface.Heatmap.class).createNode(this.name, core$()).as(io.intino.tafat.graph.UserInterface.Heatmap.class);
			newElement.core$().set(newElement, "title", java.util.Collections.singletonList(title));
			newElement.core$().set(newElement, "background", java.util.Collections.singletonList(background));
			newElement.core$().set(newElement, "color", java.util.Collections.singletonList(color));
		    return newElement;
		}

		public io.intino.tafat.graph.UserInterface.LineChart lineChart(java.lang.String title) {
		    io.intino.tafat.graph.UserInterface.LineChart newElement = core$().graph().concept(io.intino.tafat.graph.UserInterface.LineChart.class).createNode(this.name, core$()).as(io.intino.tafat.graph.UserInterface.LineChart.class);
			newElement.core$().set(newElement, "title", java.util.Collections.singletonList(title));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void heatmap(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.Heatmap> filter) {
			new java.util.ArrayList<>(heatmapList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void lineChart(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.LineChart> filter) {
			new java.util.ArrayList<>(lineChartList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static abstract class GraphicalComponent  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.lang.String title;
		protected io.intino.tafat.graph.functions.BuildJson data;
		protected io.intino.tafat.graph.functions.BuildJson values;

		public GraphicalComponent(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String title() {
			return title;
		}

		public JsonElement data() {
			return data.buildJson();
		}

		public JsonElement values() {
			return values.buildJson();
		}

		public GraphicalComponent title(java.lang.String value) {
			this.title = value;
			return (GraphicalComponent) this;
		}

		public GraphicalComponent data(io.intino.tafat.graph.functions.BuildJson value) {
			this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(data, this, io.intino.tafat.graph.functions.BuildJson.class);
			return (GraphicalComponent) this;
		}

		public GraphicalComponent values(io.intino.tafat.graph.functions.BuildJson value) {
			this.values = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.BuildJson.class);
			return (GraphicalComponent) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("title", new java.util.ArrayList(java.util.Collections.singletonList(this.title)));
			map.put("data", this.data != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.data)) : java.util.Collections.emptyList());
			map.put("values", this.values != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.values)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("title")) this.title = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.BuildJson.class).get(0);
			else if (name.equalsIgnoreCase("values")) this.values = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.BuildJson.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("title")) this.title = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.BuildJson.class);
			else if (name.equalsIgnoreCase("values")) this.values = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.BuildJson.class);
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

	public static class Heatmap extends io.intino.tafat.graph.UserInterface.GraphicalComponent implements io.intino.tara.magritte.tags.Terminal {
		protected java.net.URL background;
		protected io.intino.tafat.graph.rules.Color color;
		protected java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Region> regionList = new java.util.ArrayList<>();
		protected java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Square> squareList = new java.util.ArrayList<>();
		protected java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Circle> circleList = new java.util.ArrayList<>();

		public Heatmap(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.net.URL background() {
			return background;
		}

		public io.intino.tafat.graph.rules.Color color() {
			return color;
		}

		public Heatmap background(java.net.URL url, String destination) {
			if (url == null) this.background = null;
			else this.background = graph().core$().save(url, destination, this.background, core$());
			return (Heatmap) this;
		}

		public Heatmap background(java.io.InputStream stream, String destination) {
			if (stream == null) this.background = null;
			else this.background = graph().core$().save(stream, destination, this.background, core$());
			return (Heatmap) this;
		}

		public Heatmap color(io.intino.tafat.graph.rules.Color value) {
			this.color = value;
			return (Heatmap) this;
		}

		public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Region> regionList() {
			return java.util.Collections.unmodifiableList(regionList);
		}

		public io.intino.tafat.graph.UserInterface.Heatmap.Region region(int index) {
			return regionList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Region> regionList(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.Heatmap.Region> predicate) {
			return regionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Square> squareList() {
			return java.util.Collections.unmodifiableList(squareList);
		}

		public io.intino.tafat.graph.UserInterface.Heatmap.Square square(int index) {
			return squareList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Square> squareList(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.Heatmap.Square> predicate) {
			return squareList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Circle> circleList() {
			return java.util.Collections.unmodifiableList(circleList);
		}

		public io.intino.tafat.graph.UserInterface.Heatmap.Circle circle(int index) {
			return circleList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.UserInterface.Heatmap.Circle> circleList(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.Heatmap.Circle> predicate) {
			return circleList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}



		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
			new java.util.ArrayList<>(regionList).forEach(c -> components.add(c.core$()));
			new java.util.ArrayList<>(squareList).forEach(c -> components.add(c.core$()));
			new java.util.ArrayList<>(circleList).forEach(c -> components.add(c.core$()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("background", new java.util.ArrayList(java.util.Collections.singletonList(this.background)));
			map.put("color", new java.util.ArrayList(java.util.Collections.singletonList(this.color)));
			return map;
		}

		@Override
		protected void addNode$(io.intino.tara.magritte.Node node) {
			super.addNode$(node);
			if (node.is("UserInterface$Heatmap$Region")) this.regionList.add(node.as(io.intino.tafat.graph.UserInterface.Heatmap.Region.class));
			if (node.is("UserInterface$Heatmap$Square")) this.squareList.add(node.as(io.intino.tafat.graph.UserInterface.Heatmap.Square.class));
			if (node.is("UserInterface$Heatmap$Circle")) this.circleList.add(node.as(io.intino.tafat.graph.UserInterface.Heatmap.Circle.class));
		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);
		        if (node.is("UserInterface$Heatmap$Region")) this.regionList.remove(node.as(io.intino.tafat.graph.UserInterface.Heatmap.Region.class));
		        if (node.is("UserInterface$Heatmap$Square")) this.squareList.remove(node.as(io.intino.tafat.graph.UserInterface.Heatmap.Square.class));
		        if (node.is("UserInterface$Heatmap$Circle")) this.circleList.remove(node.as(io.intino.tafat.graph.UserInterface.Heatmap.Circle.class));
		    }

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("background")) this.background = io.intino.tara.magritte.loaders.ResourceLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("color")) this.color = io.intino.tara.magritte.loaders.WordLoader.load(values, io.intino.tafat.graph.rules.Color.class, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("background")) this.background = (java.net.URL) values.get(0);
			else if (name.equalsIgnoreCase("color")) this.color = (io.intino.tafat.graph.rules.Color) values.get(0);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends io.intino.tafat.graph.UserInterface.GraphicalComponent.Create {


			public Create(java.lang.String name) {
				super(name);
			}

			public io.intino.tafat.graph.UserInterface.Heatmap.Square square(io.intino.tara.magritte.Expression<java.lang.Double> value, int top, int left, int width, int height) {
			    io.intino.tafat.graph.UserInterface.Heatmap.Square newElement = core$().graph().concept(io.intino.tafat.graph.UserInterface.Heatmap.Square.class).createNode(this.name, core$()).as(io.intino.tafat.graph.UserInterface.Heatmap.Square.class);
				newElement.core$().set(newElement, "value", java.util.Collections.singletonList(value));
				newElement.core$().set(newElement, "top", java.util.Collections.singletonList(top));
				newElement.core$().set(newElement, "left", java.util.Collections.singletonList(left));
				newElement.core$().set(newElement, "width", java.util.Collections.singletonList(width));
				newElement.core$().set(newElement, "height", java.util.Collections.singletonList(height));
			    return newElement;
			}

			public io.intino.tafat.graph.UserInterface.Heatmap.Circle circle(io.intino.tara.magritte.Expression<java.lang.Double> value, int centerX, int centerY, int diameter) {
			    io.intino.tafat.graph.UserInterface.Heatmap.Circle newElement = core$().graph().concept(io.intino.tafat.graph.UserInterface.Heatmap.Circle.class).createNode(this.name, core$()).as(io.intino.tafat.graph.UserInterface.Heatmap.Circle.class);
				newElement.core$().set(newElement, "value", java.util.Collections.singletonList(value));
				newElement.core$().set(newElement, "centerX", java.util.Collections.singletonList(centerX));
				newElement.core$().set(newElement, "centerY", java.util.Collections.singletonList(centerY));
				newElement.core$().set(newElement, "diameter", java.util.Collections.singletonList(diameter));
			    return newElement;
			}

		}

		public Clear clear() {
			return new Clear();
		}

		public class Clear  {
			public void square(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.Heatmap.Square> filter) {
				new java.util.ArrayList<>(squareList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}

			public void circle(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.Heatmap.Circle> filter) {
				new java.util.ArrayList<>(circleList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}
		}

		public static abstract class Region  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
			protected io.intino.tafat.graph.functions.BuildJson data;
			protected io.intino.tara.magritte.Expression<java.lang.Double> value;
			protected int zIndex;

			public Region(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public JsonElement data() {
				return data.buildJson();
			}

			public Double value() {
				return value.value();
			}

			public int zIndex() {
				return zIndex;
			}

			public Region data(io.intino.tafat.graph.functions.BuildJson value) {
				this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(data, this, io.intino.tafat.graph.functions.BuildJson.class);
				return (Region) this;
			}

			public Region value(io.intino.tara.magritte.Expression<java.lang.Double> value) {
				this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
				return (Region) this;
			}

			public Region zIndex(int value) {
				this.zIndex = value;
				return (Region) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("data", this.data != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.data)) : java.util.Collections.emptyList());
				map.put("value", new java.util.ArrayList(java.util.Collections.singletonList(this.value)));
				map.put("zIndex", new java.util.ArrayList(java.util.Collections.singletonList(this.zIndex)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.BuildJson.class).get(0);
				else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
				else if (name.equalsIgnoreCase("zIndex")) this.zIndex = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.BuildJson.class);
				else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
				else if (name.equalsIgnoreCase("zIndex")) this.zIndex = (java.lang.Integer) values.get(0);
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

		public static class Square extends io.intino.tafat.graph.UserInterface.Heatmap.Region implements io.intino.tara.magritte.tags.Terminal {
			protected int top;
			protected int left;
			protected int width;
			protected int height;

			public Square(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public int top() {
				return top;
			}

			public int left() {
				return left;
			}

			public int width() {
				return width;
			}

			public int height() {
				return height;
			}

			public Square top(int value) {
				this.top = value;
				return (Square) this;
			}

			public Square left(int value) {
				this.left = value;
				return (Square) this;
			}

			public Square width(int value) {
				this.width = value;
				return (Square) this;
			}

			public Square height(int value) {
				this.height = value;
				return (Square) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
				map.put("top", new java.util.ArrayList(java.util.Collections.singletonList(this.top)));
				map.put("left", new java.util.ArrayList(java.util.Collections.singletonList(this.left)));
				map.put("width", new java.util.ArrayList(java.util.Collections.singletonList(this.width)));
				map.put("height", new java.util.ArrayList(java.util.Collections.singletonList(this.height)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("top")) this.top = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("left")) this.left = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("width")) this.width = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("height")) this.height = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("top")) this.top = (java.lang.Integer) values.get(0);
				else if (name.equalsIgnoreCase("left")) this.left = (java.lang.Integer) values.get(0);
				else if (name.equalsIgnoreCase("width")) this.width = (java.lang.Integer) values.get(0);
				else if (name.equalsIgnoreCase("height")) this.height = (java.lang.Integer) values.get(0);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
			}
		}

		public static class Circle extends io.intino.tafat.graph.UserInterface.Heatmap.Region implements io.intino.tara.magritte.tags.Terminal {
			protected int centerX;
			protected int centerY;
			protected int diameter;

			public Circle(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public int centerX() {
				return centerX;
			}

			public int centerY() {
				return centerY;
			}

			public int diameter() {
				return diameter;
			}

			public Circle centerX(int value) {
				this.centerX = value;
				return (Circle) this;
			}

			public Circle centerY(int value) {
				this.centerY = value;
				return (Circle) this;
			}

			public Circle diameter(int value) {
				this.diameter = value;
				return (Circle) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
				map.put("centerX", new java.util.ArrayList(java.util.Collections.singletonList(this.centerX)));
				map.put("centerY", new java.util.ArrayList(java.util.Collections.singletonList(this.centerY)));
				map.put("diameter", new java.util.ArrayList(java.util.Collections.singletonList(this.diameter)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("centerX")) this.centerX = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("centerY")) this.centerY = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("diameter")) this.diameter = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("centerX")) this.centerX = (java.lang.Integer) values.get(0);
				else if (name.equalsIgnoreCase("centerY")) this.centerY = (java.lang.Integer) values.get(0);
				else if (name.equalsIgnoreCase("diameter")) this.diameter = (java.lang.Integer) values.get(0);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
			}
		}


		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class LineChart extends io.intino.tafat.graph.UserInterface.GraphicalComponent implements io.intino.tara.magritte.tags.Terminal {

		protected java.util.List<io.intino.tafat.graph.UserInterface.LineChart.Line> lineList = new java.util.ArrayList<>();

		public LineChart(io.intino.tara.magritte.Node node) {
			super(node);
		}





		public java.util.List<io.intino.tafat.graph.UserInterface.LineChart.Line> lineList() {
			return java.util.Collections.unmodifiableList(lineList);
		}

		public io.intino.tafat.graph.UserInterface.LineChart.Line line(int index) {
			return lineList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.UserInterface.LineChart.Line> lineList(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.LineChart.Line> predicate) {
			return lineList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}



		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
			new java.util.ArrayList<>(lineList).forEach(c -> components.add(c.core$()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());

			return map;
		}

		@Override
		protected void addNode$(io.intino.tara.magritte.Node node) {
			super.addNode$(node);
			if (node.is("UserInterface$LineChart$Line")) this.lineList.add(node.as(io.intino.tafat.graph.UserInterface.LineChart.Line.class));
		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);
		        if (node.is("UserInterface$LineChart$Line")) this.lineList.remove(node.as(io.intino.tafat.graph.UserInterface.LineChart.Line.class));
		    }

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);

		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends io.intino.tafat.graph.UserInterface.GraphicalComponent.Create {


			public Create(java.lang.String name) {
				super(name);
			}

			public io.intino.tafat.graph.UserInterface.LineChart.Line line(java.lang.String label, io.intino.tara.magritte.Expression<java.lang.Double> value) {
			    io.intino.tafat.graph.UserInterface.LineChart.Line newElement = core$().graph().concept(io.intino.tafat.graph.UserInterface.LineChart.Line.class).createNode(this.name, core$()).as(io.intino.tafat.graph.UserInterface.LineChart.Line.class);
				newElement.core$().set(newElement, "label", java.util.Collections.singletonList(label));
				newElement.core$().set(newElement, "value", java.util.Collections.singletonList(value));
			    return newElement;
			}

		}

		public Clear clear() {
			return new Clear();
		}

		public class Clear  {
			public void line(java.util.function.Predicate<io.intino.tafat.graph.UserInterface.LineChart.Line> filter) {
				new java.util.ArrayList<>(lineList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}
		}

		public static class Line  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
			protected io.intino.tafat.graph.functions.BuildJson data;
			protected java.lang.String label;
			protected io.intino.tara.magritte.Expression<java.lang.Double> value;

			public Line(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public JsonElement data() {
				return data.buildJson();
			}

			public java.lang.String label() {
				return label;
			}

			public Double value() {
				return value.value();
			}

			public Line data(io.intino.tafat.graph.functions.BuildJson value) {
				this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(data, this, io.intino.tafat.graph.functions.BuildJson.class);
				return (Line) this;
			}

			public Line label(java.lang.String value) {
				this.label = value;
				return (Line) this;
			}

			public Line value(io.intino.tara.magritte.Expression<java.lang.Double> value) {
				this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
				return (Line) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("data", this.data != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.data)) : java.util.Collections.emptyList());
				map.put("label", new java.util.ArrayList(java.util.Collections.singletonList(this.label)));
				map.put("value", new java.util.ArrayList(java.util.Collections.singletonList(this.value)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.BuildJson.class).get(0);
				else if (name.equalsIgnoreCase("label")) this.label = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("data")) this.data = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.BuildJson.class);
				else if (name.equalsIgnoreCase("label")) this.label = (java.lang.String) values.get(0);
				else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
			}
		}


		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}