package tafat.functions;

import java.io.File;

@FunctionalInterface
public interface WriteStash {

	void writeStash(tara.io.Stash stash, File file);

}
