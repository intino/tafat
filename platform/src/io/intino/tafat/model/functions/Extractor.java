package io.intino.tafat.model.functions;

import io.intino.magritte.framework.Layer;

@FunctionalInterface
public interface Extractor {

	io.intino.magritte.io.Node extract(Layer layer);

}
