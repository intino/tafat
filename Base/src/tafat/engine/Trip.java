package tafat.engine;

public class Trip {

    private final double distance;
    private final double duration;

    public Trip(double distance, double duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public Trip(String distance, String duration) {
        this(Double.parseDouble(distance), Double.parseDouble(duration));
    }

    public double distance() {
        return distance;
    }

    public double duration() {
        return duration;
    }
}
