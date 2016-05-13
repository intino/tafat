package tafat.output;

import tara.io.Node;
import tara.io.Stash;
import tara.io.StashSerializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static tafat.engine.utils.Date.getDateTime;

public class SumusOutput {

	public static void init(tafat.SumusOutput self) {
		self.plotList().forEach(p -> p.timeout(p.timeScale().toSeconds() - 1));
		self.toStash(self.exportList());
	}

	public static void process(tafat.SumusOutput self) {
		self.toStash(plotListToWrite(self));
	}

	public static void toStash(tafat.SumusOutput self, List<? extends tafat.SumusOutput.Extractor> extractors) {
		if (extractors.isEmpty()) return;
		self.writeStash(self.createStash(extractors), new File(extractors.get(0).path()));
	}

	public static Stash createStash(tafat.SumusOutput self, List<? extends tafat.SumusOutput.Extractor> extractors) {
		Stash stash = new tara.io.Stash();
		stash.language = self.language();
		for (tafat.SumusOutput.Extractor extractor : extractors) stash.nodes.addAll(extractor.buildStash());
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
		return self.collect().parallelStream().map(self::extractMember).collect(Collectors.toList());
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
		return self.collect().parallelStream().map(self::extractFact).collect(toList());
	}

	@SuppressWarnings("Convert2streamapi")
	private static List<tafat.SumusOutput.Plot> plotListToWrite(tafat.SumusOutput self) {
		List<tafat.SumusOutput.Plot> result = new ArrayList<>();
		for (tafat.SumusOutput.Plot plot : self.plotList())
			if (plot.checkStep())
				result.add(plot);
		return result;
	}

}
