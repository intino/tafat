package smartcities.metrics;

import magritte.Metric;

public enum Time implements Metric {

    //Add the metrics here 
    ;

    @Override
    public double value(double value) {
        return converters[this.ordinal()].convert(value);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static final Converter[] converters = { /* Add factors here */};

    private static Converter factor(final double factor) {
        return new Converter() {
            @Override
            public double convert(double value) {
                return value * factor;
            }
        };
    }
}
