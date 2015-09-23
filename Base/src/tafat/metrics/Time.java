package tafat.metrics;

public enum Time {

    minute(v -> v * 60);

    private Converter converter;

    Time(Converter converter) {
        this.converter = converter;
    }

    public double value(double value) {
        return converter.convert(value);
    }

    interface Converter {
        double convert(double value);
    }
}
