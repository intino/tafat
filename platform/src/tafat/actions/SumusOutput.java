package tafat.actions;

import tara.io.Node;
import tara.io.Stash;
import tara.io.StashSerializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static tafat.engine.Date.getDateTime;

public class SumusOutput {

	public static void init(tafat.SumusOutput self) {
		self.plotList().forEach(p -> p.timeout(p.timeScale().toSeconds() - 1));
		self.toStash(self.exportList());
	}

	public static void process(tafat.SumusOutput self) {
		self.toStash(self.plotList().stream().filter(tafat.SumusOutput.Plot::checkStep).collect(toList()));
	}

	public static void toStash(tafat.SumusOutput self, List<? extends tafat.SumusOutput.Extractor> extractors) {
		if (extractors.isEmpty()) return;
		self.writeStash(self.createStash(extractors), new File(extractors.get(0).path()));
	}

	public static Stash createStash(tafat.SumusOutput self, List<? extends tafat.SumusOutput.Extractor> extractors) {
		Stash stash = new tara.io.Stash();
		stash.language = self.language();
		extractors.stream().forEach(e -> stash.nodes.addAll(e.buildStash()));
		return stash;
	}

	public static void writeStash(tafat.SumusOutput self, Stash stash, File file) {
		if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
		try {
			Files.write(file.toPath(), StashSerializer.serialize(stash));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getExportPath(tafat.SumusOutput.Export self) {
		return self.ownerAs(tafat.SumusOutput.class).rootPath() + "Dimension.stash";
	}

	public static Boolean checkStep(tafat.SumusOutput.Plot self) {
		if (self.timeout() == 0) {
			self.timeout(self.timeScale().toSeconds() - 1);
			return true;
		}
		self.timeout(self.timeout() - 1);
		return false;
	}

	public static List<tara.io.Node> buildStashOfMembers(tafat.SumusOutput.Export self) {
		return self.collect().parallelStream().map(l -> self.extractMember(l)).collect(Collectors.toList());
	}

	public static String getPlotPath(tafat.SumusOutput.Plot self) {
		LocalDateTime date = getDateTime();
		return self.ownerAs(tafat.SumusOutput.class).rootPath() +
				String.format("%04d", date.getYear()) + "/" +
				String.format("%03d", date.getDayOfYear()) + "/" +
				String.format("%02d%02d", date.getHour(), date.getMinute()) + "-" +
				self.ownerAs(tafat.SumusOutput.class).simulationId() + ".stash";
	}

	public static List<Node> buildStashOfFacts(tafat.SumusOutput.Plot self) {
		return self.collect().parallelStream().map(l -> self.extractFact(l)).collect(toList());
	}
}
