package io.intino.tafat.functions;

import io.intino.tafat.SumusOutput;

import java.util.List;

@FunctionalInterface
public interface ToStash {

	void toStash(List<? extends SumusOutput.Extractor> extractors);

}
