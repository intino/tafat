package io.intino.tafat.functions;

import tara.magritte.Graph;

import java.util.Random;

@FunctionalInterface
public interface Profile {

	void execute(Graph graph, Random random);

}
