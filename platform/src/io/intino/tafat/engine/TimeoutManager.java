package io.intino.tafat.engine;

import io.intino.tafat.model.functions.Action;
import io.intino.tafat.model.rules.TimeScale;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static io.intino.tafat.engine.Date.getInstant;

public class TimeoutManager {

    private static List<Timeout> timeouts;

    public static void timeout(double duration, Action action) {
        timeout(Date.getInstant().plusSeconds((long) duration), action);
    }

    public static void timeout(Instant instant, Action action) {
		timeouts.add(new Timeout(instant, action));
        timeouts.sort(Comparator.comparing(t -> t.duration));
    }

    public static void cyclicTimeout(TimeScale timeScale, Action action) {
		timeouts.add(new CyclicTimeout(timeScale, action));
        timeouts.sort(Comparator.comparing(t -> t.duration));
    }

	static void init(){
		timeouts = new ArrayList<>();
	}

    @SuppressWarnings("Convert2streamapi")
    static void update() {
        List<Timeout> finishedTimeouts = new ArrayList<>();
        for (Timeout timeout : timeouts) {
            if(!timeout.duration.isAfter(Date.getInstant()))
                finishedTimeouts.add(timeout);
            else
                break;
        }
        finishedTimeouts.forEach(t -> t.action.execute());
        timeouts.removeAll(finishedTimeouts);
        for (Timeout timeout : finishedTimeouts)
            if (timeout instanceof CyclicTimeout) {
                CyclicTimeout cyclicTimeout = (CyclicTimeout) timeout;
                cyclicTimeout(cyclicTimeout.timeScale, cyclicTimeout.action);
            }
    }

    private static class Timeout {
		Instant duration;
		final Action action;

        public Timeout(Instant duration, Action action) {
            this.duration = duration;
            this.action = action;
        }
    }

    private static class CyclicTimeout extends Timeout{

		final TimeScale timeScale;

		public CyclicTimeout(TimeScale timeScale, Action action) {
			super(getInstant().plusSeconds(timeScale.toSeconds()), action);
			this.timeScale = timeScale;
		}

    }
}
