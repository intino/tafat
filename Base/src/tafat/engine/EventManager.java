package tafat.engine;

import tafat.natives.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {

    static List<Event> events = new ArrayList<>();

    public static void register(Event event) {
        events.add(event);
    }

    public void step(long time){
        events.forEach(e -> e.step(time));
        events.removeAll(events.stream().filter(Event::finished).collect(Collectors.toList()));
    }

}
