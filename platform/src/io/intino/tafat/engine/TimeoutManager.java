package io.intino.tafat.engine;

import io.intino.tafat.graph.functions.Action;
import io.intino.tafat.graph.rules.TimeScale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeoutManager {

    private static List<Timeout> timeouts;

    public static void timeout(double duration, Action action) {
        timeout(Date.getDateTime().plusSeconds((long) duration), action);
    }

    public static void timeout(LocalDateTime instant, Action action) {
		timeouts.add(new Timeout(instant, action));
        timeouts.sort((t1, t2) -> t1.duration.compareTo(t2.duration));
    }

    public static void cyclicTimeout(TimeScale timeScale, Action action) {
		timeouts.add(new CyclicTimeout(timeScale, action));
        timeouts.sort((t1, t2) -> t1.duration.compareTo(t2.duration));
    }

	static void init(){
		timeouts = new ArrayList<>();
	}

    @SuppressWarnings("Convert2streamapi")
    static void update() {
        List<Timeout> finishedTimeouts = new ArrayList<>();
        for (Timeout timeout : timeouts) {
            if(!timeout.duration.isAfter(Date.getDateTime()))
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
		LocalDateTime duration;
		final Action action;

        public Timeout(LocalDateTime duration, Action action) {
            this.duration = duration;
            this.action = action;
        }
    }

    private static class CyclicTimeout extends Timeout{

		final TimeScale timeScale;

		public CyclicTimeout(TimeScale timeScale, Action action) {
			super(Date.getDateTime().plusSeconds(timeScale.toSeconds()), action);
			this.timeScale = timeScale;
		}

    }
}
