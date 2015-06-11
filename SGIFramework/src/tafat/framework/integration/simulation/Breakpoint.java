package tafat.framework.integration.simulation;

import java.util.Date;

public class Breakpoint {
    public static Breakpoint INVALID = new Breakpoint(null);
    private static int LAST_ID = 0;
    private boolean passed = false;
    private int id;
    private Date date;

    public static Breakpoint create(Date date){
        return new Breakpoint(date);
    }

    public Breakpoint(Date date) {
        this.id = LAST_ID++;
        this.date = date;
    }

    public boolean isPassed() {
        return passed;
    }

    public void passed() {
        passed = true;
    }

    public void reset() {
        passed = false;
    }

    public int id() {
        return id;
    }

    public Date date() {
        return date;
    }
}