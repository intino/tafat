package tafat.framework.state;

import tafat.framework.integration.simulation.Watcher;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WatcherContainer extends HashMap<String, List<Watcher>>{


    public void addWatcher(String username, Watcher watcher) {
        if (containsKey(username))
            get(username).add(watcher);
        else
            put(username, createList(watcher));
    }

    private LinkedList<Watcher> createList(Watcher watcher) {
        LinkedList<Watcher> watchers = new LinkedList<>();
        watchers.add(watcher);
        return watchers;
    }

    public HashMap <String, List<Watcher>> getWatchers() {
        return this;
    }

    public void removeUserWatchers(String username) {
        remove(username);
    }

    public void removeUserWatcher(String username, Watcher watcher) {
        get(username).remove(watcher);
    }
}
