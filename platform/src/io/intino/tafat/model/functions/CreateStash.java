package io.intino.tafat.model.functions;

import io.intino.tafat.model.SumusOutput;

import java.util.List;

@FunctionalInterface
public interface CreateStash {

	io.intino.magritte.io.Stash create(List<? extends SumusOutput.Extractor> extractors);

}
