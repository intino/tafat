package io.intino.tafat.graph.functions;

import java.io.File;

@FunctionalInterface
public interface WriteStash {

	void writeStash(io.intino.tara.io.Stash stash, File file);

}
