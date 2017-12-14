package io.intino.tafat.graph.functions;

import io.intino.tafat.graph.SumusOutput;

import java.util.List;

@FunctionalInterface
public interface CreateStash {

	tara.io.Stash create(List<? extends SumusOutput.Extractor> extractors);

}
