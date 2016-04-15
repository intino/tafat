package tafat.functions;

import tara.magritte.Layer;
import tara.magritte.Node;

@FunctionalInterface
public interface Extractor {

	Node extract(Layer layer);

}
