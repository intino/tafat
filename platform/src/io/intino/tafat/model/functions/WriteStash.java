package io.intino.tafat.model.functions;

import java.io.File;

@FunctionalInterface
public interface WriteStash {

	void writeStash(io.intino.magritte.io.Stash stash, File file);

}
