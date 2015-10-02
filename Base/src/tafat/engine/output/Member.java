package tafat.engine.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Member {

    String id;
    Map<String, Object> attributes = new HashMap<>();

    public String id() {
        return id;
    }

    public void id(String id) {
        this.id = id;
    }

    public void attribute(String name, Object... values) {
        attributes.put(name, new ArrayList<>(Arrays.asList(values)));
    }

    public void reference(String name, String... reference) {
        attributes.put(name, new ArrayList<>(Arrays.asList(reference).stream().map(s -> "Members#" + s).collect(toList())));
    }

    public Map<String, Object> attributes() {
        return attributes;
    }
}
