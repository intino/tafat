package tafat.metrics;

public enum Time {

    minute(v -> v * 60),
    minutes(minute),
    m(minute);

    private Converter converter;

    Time(Converter converter) {
        this.converter = converter;
    }

    Time(Time time) {
        converter = time.converter;
    }

    public double value(double value) {
        return converter.convert(value);
    }

    interface Converter {
        double convert(double value);
    }
}
