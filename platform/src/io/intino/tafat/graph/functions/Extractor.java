package io.intino.tafat.graph.functions;

import io.intino.tara.io.Node;
import io.intino.tara.magritte.Layer;

@FunctionalInterface
public interface Extractor {

	Node extract(Layer layer);

}
