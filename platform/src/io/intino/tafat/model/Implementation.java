package io.intino.tafat.model;


import io.intino.magritte.framework.Node;

import java.util.ArrayList;
import java.util.List;

public class Implementation extends AbstractImplementation {

	private List<StateChart> stateChartList = new ArrayList<>();
	private List<SystemDynamic> systemDynamicList = new ArrayList<>();
	private List<Fmu> fmuList = new ArrayList<>();

	public Implementation(Node node) {
		super(node);
	}

	@Override
	protected void addNode$(Node node) {
		super.addNode$(node);
		super.addNode$(node);
		if (node.is("StateChart")) this.stateChartList.add(node.as(io.intino.tafat.model.StateChart.class));
		if (node.is("SystemDynamic")) this.systemDynamicList.add(node.as(io.intino.tafat.model.SystemDynamic.class));
		if (node.is("Fmu")) this.fmuList.add(node.as(io.intino.tafat.model.Fmu.class));
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

	public static class Parallelizable extends AbstractParallelizable {
		public Parallelizable(Node node) {
			super(node);
		}
	}
}