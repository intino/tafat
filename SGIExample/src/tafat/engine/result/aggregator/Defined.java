package tafat.engine.result.aggregator;

import java.lang.reflect.Field;
import java.util.ArrayList;

import tafat.engine.ModelObject;
import tafat.engine.result.filter.FilterHandler;


public class Defined extends AggregationType {

	@Override
	public String[] calculate(ArrayList<ModelObject> toCheck, ArrayList<?> toWrite, Field field, ArrayList<FilterHandler> filters, Integer precision) {
		String[] toReturn = new String [toWrite.size()];
		for (int i = 0; i < toWrite.size(); i++){
			if (!checkFilters(filters, toCheck.get(i))){
				toReturn[i] = "F";
				continue;
			}
			try {
				if (field.get(toWrite.get(i)) == null)
					toReturn[i] = "0";
				else
					toReturn[i] = "1";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NullPointerException e){}
		}
		return toReturn;
	}

}
