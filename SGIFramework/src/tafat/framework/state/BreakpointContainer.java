package tafat.framework.state;

import tafat.framework.integration.simulation.Breakpoint;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreakpointContainer {
    LinkedList<Breakpoint> breakpoints;

    public BreakpointContainer() {
        this.breakpoints = new LinkedList<>();
    }

    public void add(Breakpoint breakpoint) {
        breakpoints.add(breakpoint);
    }

    public void removeById(int id) {
        breakpoints.stream()
                .filter(element-> element.id()==id).findFirst()
                .ifPresent(breakpoints::remove);
    }

    public boolean haveBreakpointNotPassed(Date date) {
        return filterBreakpointNotPassed(date)
                .findFirst().isPresent();
    }

    private Stream<Breakpoint> filterBreakpointNotPassed(Date date) {
        return breakpoints.stream()
                .filter(element -> (element.date().compareTo(date) <= 0)&&(!element.isPassed()));
    }

    public Breakpoint get(String breakpointId) {
        return breakpoints.stream()
                .filter(element -> element.id() == Integer.valueOf(breakpointId))
                .findFirst()
                .orElse(null);
    }

    public Stream<Breakpoint> stream() {
        return breakpoints.stream();
    }

    public List<Breakpoint> getBreakpointsNotPassed(Date date) {
        return filterBreakpointNotPassed(date).collect(Collectors.toList());
    }
}