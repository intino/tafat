package tafat.functions;

import tara.magritte.Layer;

@FunctionalInterface
public interface Extractor {

	tara.io.Node extract(Layer layer);

}
