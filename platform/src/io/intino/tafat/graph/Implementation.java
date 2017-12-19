package io.intino.tafat.graph;

import io.intino.tara.magritte.Node;

import java.util.ArrayList;
import java.util.List;

public class Implementation extends AbstractImplementation {

	private List<StateChart> stateChartList = new ArrayList<>();
	private List<SystemDynamic> systemDynamicList = new ArrayList<>();
	private List<Fmu> fmuList = new ArrayList<>();

	public Implementation(io.intino.tara.magritte.Node node) {
		super(node);
	}

	@Override
	protected void addNode$(Node node) {
		super.addNode$(node);
		super.addNode$(node);
		if (node.is("StateChart")) this.stateChartList.add(node.as(io.intino.tafat.graph.StateChart.class));
		if (node.is("SystemDynamic")) this.systemDynamicList.add(node.as(io.intino.tafat.graph.SystemDynamic.class));
		if (node.is("Fmu")) this.fmuList.add(node.as(io.intino.tafat.graph.Fmu.class));
	}

	public List<StateChart> stateChartList() {
		return stateChartList;
	}

	public List<SystemDynamic> systemDynamicList() {
		return systemDynamicList;
	}

	public List<Fmu> fmuList() {
		return fmuList;
	}

	public StateChart stateChart(int index) {
		return stateChartList.get(0);
	}
}