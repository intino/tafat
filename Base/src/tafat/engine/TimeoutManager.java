package tafat.engine;

import tafat.natives.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeoutManager {

    private static final List<Timeout> timeouts = new ArrayList<>();

    public static void timeout(double duration, Action action) {
        timeouts.add(new Timeout(duration, action));
    }

    public static void update(int time) {
        timeouts.forEach(t -> t.duration -= time);
        List<Timeout> finishedTimeouts = timeouts.stream().filter(t -> t.duration <= 0).collect(Collectors.toList());
        finishedTimeouts.forEach(t -> t.action.execute());
        timeouts.removeAll(finishedTimeouts);
    }

    private static class Timeout {
        private double duration;
        private final Action action;

        public Timeout(double duration, Action action) {
            this.duration = duration;
            this.action = action;
        }


    }
}
