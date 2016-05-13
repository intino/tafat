package tafat.output;

import tara.magritte.Layer;

import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;
import static tafat.engine.Date.getDateTime;

public class LogOutput {
	public static void init(tafat.LogOutput self) {
		StringBuilder builder = new StringBuilder("%19s |");
		List<String> names = self.lineList().stream().map(Layer::name).collect(toList());
		names.forEach(n -> builder.append(" %").append(n.length()).append("f |"));
		names.add(0, "Time");
		self.format(builder.append("%n").toString());
		System.out.format(self.format().replace("f", "s"), names.toArray(new String[names.size()]));
	}

	public static void process(tafat.LogOutput self) {
		if (self.checkStep()) {
			List<Object> collect = collectValues(self);
			collect.add(0, getDateTime().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
			System.out.format(self.format(), collect.toArray(new Object[collect.size()]));
		}
	}

	public static boolean checkStep(tafat.LogOutput self) {
		if (self.timeout() == 0) {
			self.timeout(self.timeScale().toSeconds() - 1);
			return true;
		}
		self.timeout(self.timeout() - 1);
		return false;
	}

	@SuppressWarnings("Convert2streamapi")
	private static List<Object> collectValues(tafat.LogOutput self) {
		List<Object> result = new ArrayList<>();
		for (tafat.LogOutput.Line line : self.lineList()) result.add(line.value());
		return result;
	}
}
