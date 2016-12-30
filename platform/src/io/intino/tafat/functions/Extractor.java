package io.intino.tafat.functions;

import io.intino.tara.magritte.Layer;

@FunctionalInterface
public interface Extractor {

	io.intino.tara.io.Node extract(Layer layer);

}
