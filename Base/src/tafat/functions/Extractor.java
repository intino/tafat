package tafat.functions;

import tara.io.Instance;
import tara.magritte.Layer;

@FunctionalInterface
public interface Extractor {

	Instance extract(Layer layer);

}
