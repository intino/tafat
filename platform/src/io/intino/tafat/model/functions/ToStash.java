package io.intino.tafat.model.functions;

import io.intino.tafat.model.SumusOutput;

import java.util.List;

@FunctionalInterface
public interface ToStash {

	void toStash(List<? extends SumusOutput.Extractor> extractors);

}
