package tafat.rules;

import tara.lang.model.Rule;

import java.util.List;

public enum DayOfWeek implements Rule<Enum> {

    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;

    public DayOfWeek nextDay(List<DayOfWeek> dayOfWeeks){
        int index = dayOfWeeks.indexOf(this);
        return index == -1 ? null : index == dayOfWeeks.size() - 1 ? dayOfWeeks.get(0) : dayOfWeeks.get(index + 1);
    }

    @Override
    public boolean accept(Enum value) {
        return DayOfWeek.valueOf(value.name()) != null;
    }

}
