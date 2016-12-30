package io.intino.tafat.functions;

import io.intino.tafat.SumusOutput;

import java.util.List;

@FunctionalInterface
public interface CreateStash {

	io.intino.tara.io.Stash create(List<? extends SumusOutput.Extractor> extractors);

}
