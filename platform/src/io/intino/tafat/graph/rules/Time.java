package io.intino.tafat.graph.rules;

import io.intino.tara.lang.model.Metric;

public enum Time implements Metric<Integer> {
    minute(v -> v * 60),
    minutes(minute.converter),
	m(minute.converter),
	second(v -> v),
	seconds(second.converter),
	s(second.converter);

    private Converter<Integer> converter;

	Time(Converter<Integer> converter) {
		this.converter = converter;
	}

    @Override
    public Integer value(Integer t) {
        return this.converter.convert(t);
    }

}
