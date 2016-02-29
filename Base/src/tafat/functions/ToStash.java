package tafat.functions;

import tafat.SumusOutput;

import java.util.List;

@FunctionalInterface
public interface ToStash {

	void toStash(List<? extends SumusOutput.Extractor> extractors);

}
