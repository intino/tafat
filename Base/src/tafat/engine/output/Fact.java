package tafat.engine.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Fact {

    Map<String, Object> attributes = new HashMap<>();

    public Fact(String instant) {
        attributes.put("instant", instant);
    }

    public void reference(String name, String... reference) {
        attributes.put(name, new ArrayList<>(Arrays.asList(reference).stream().map(s -> "Members#" + s).collect(toList())));
    }

    public void attribute(String name, Object... values) {
        attributes.put(name, new ArrayList<>(Arrays.asList(values)));
    }

    public Map<String, Object> attributes() {
        return attributes;
    }
}
