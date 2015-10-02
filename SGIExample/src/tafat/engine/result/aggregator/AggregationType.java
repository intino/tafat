package tafat.engine.result.aggregator;

import java.lang.reflect.Field;
import java.util.ArrayList;

import tafat.engine.ModelObject;
import tafat.engine.result.filter.FilterHandler;

public abstract class AggregationType {

	public abstract String[] calculate (ArrayList<ModelObject> toCheck, ArrayList<?> toWrite, Field field, ArrayList<FilterHandler> filters, Integer precision);
	
	public boolean checkFilters (ArrayList <FilterHandler> filters, ModelObject object){
		if (filters != null){
			boolean passFilters = true;
			for (FilterHandler filter : filters)
				if (!filter.passFilter(object)){
					passFilters = false;
					break;
				}
			return passFilters;
		}
		return true;
	}
}
