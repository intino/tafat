package tafat.rules;

import tara.lang.model.Metric;

public enum Time implements Metric<Integer> {
    minute(v -> v * 60),
    minutes(minute.converter),
    m(minute.converter);

    private Converter<Integer> converter;

    @Override
    public Integer value(Integer integer) {
        return null;
    }

    Time(Converter<Integer> converter) {
        this.converter = converter;
    }

}
