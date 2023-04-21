package io.intino.tafat.model.functions;

import io.intino.magritte.framework.Graph;

import java.util.Random;

@FunctionalInterface
public interface Profile {

	void execute(Graph graph, Random random);

}
