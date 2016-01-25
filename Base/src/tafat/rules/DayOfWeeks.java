package tafat.rules;

import tara.lang.model.Rule;

public enum DayOfWeeks implements Rule<Enum> {

	Day, Week;

	@Override
	public boolean accept(Enum value) {
		return DayOfWeeks.valueOf(value.name()) != null;
	}
}