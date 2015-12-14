package tafat.engine;

import tafat.functions.Action;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static tafat.engine.Date.getDateTime;
import static tafat.engine.Date.with;

public class TimeoutManager {

    private static final List<Timeout> timeouts = new ArrayList<>();

    public static void timeout(double duration, Action action) {
        timeouts.add(new Timeout(getDateTime().plusSeconds((long) duration), action));
        timeouts.sort((t1, t2) -> t1.duration.compareTo(t2.duration));
    }

    public static void update() {
        List<Timeout> finishedTimeouts = new ArrayList<>();
        for (Timeout timeout : timeouts) {
            if(!timeout.duration.isAfter(getDateTime()))
                finishedTimeouts.add(timeout);
            else
                break;
        }
        finishedTimeouts.forEach(t -> t.action.execute());
        timeouts.removeAll(finishedTimeouts);
    }

    private static class Timeout {
        private LocalDateTime duration;
        private final Action action;

        public Timeout(LocalDateTime duration, Action action) {
            this.duration = duration;
            this.action = action;
        }
    }
}
