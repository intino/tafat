package tafat.words;

public enum TimeScale {

    Years(365 * 24 * 60 * 60),
    Quarters(91 * 24 * 60 * 60),
    Months(30 * 24 * 60 * 60),
    Weeks(7 * 24 * 60 * 60),
    Days(24 * 60 * 60),
    Hours(60 * 60),
    Minutes(60),
    Seconds(1);

    private final int toSeconds;

    TimeScale(int toSeconds) {
        this.toSeconds = toSeconds;
    }

    public int toSeconds() {
        return toSeconds;
    }

    public int toSeconds(int amount){
        return amount * toSeconds;
    }
}
