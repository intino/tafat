package io.intino.tafat.functions;

import io.intino.tara.magritte.Graph;

import java.util.Random;

@FunctionalInterface
public interface Profile {

	void execute(Graph graph, Random random);

}
