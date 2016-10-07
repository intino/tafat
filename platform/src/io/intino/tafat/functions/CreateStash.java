package io.intino.tafat.functions;

import io.intino.tafat.SumusOutput;

import java.util.List;

@FunctionalInterface
public interface CreateStash {

	tara.io.Stash create(List<? extends SumusOutput.Extractor> extractors);

}
