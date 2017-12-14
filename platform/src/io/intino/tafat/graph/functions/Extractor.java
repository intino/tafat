package io.intino.tafat.graph.functions;

import io.intino.tara.magritte.Layer;

@FunctionalInterface
public interface Extractor {

	io.intino.tara.io.Node extract(Layer layer);

}
