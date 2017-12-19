package io.intino.tafat.graph.functions;

import io.intino.tara.magritte.Graph;

import java.util.Random;

@FunctionalInterface
public interface Profile {

	void execute(Graph graph, Random random);

}
