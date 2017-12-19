package io.intino.tafat.graph.rules;

import io.intino.tara.lang.model.Rule;

public enum Color implements Rule<Enum> {

	Red("#FF0000"),
	Blue("#0000FF"),
	Green("#00FF00");

	private String code;

	Color(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}

	@Override
	public boolean accept(Enum value) {
		return Color.valueOf(value.name()) != null;
	}
}
