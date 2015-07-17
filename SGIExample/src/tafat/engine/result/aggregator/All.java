package tafat.engine.result.aggregator;

import java.lang.reflect.Field;
import java.util.ArrayList;

import tafat.engine.ModelObject;
import tafat.engine.Tools;
import tafat.engine.result.filter.FilterHandler;

public class All extends AggregationType {

	@Override
	public String[] calculate(ArrayList<ModelObject> toCheck, ArrayList<?> toWrite, Field field, ArrayList<FilterHandler> filters, Integer precision) {
		String[] toReturn = new String [toWrite.size()];
		for (int i = 0; i < toWrite.size(); i++){
			if (!checkFilters(filters, toCheck.get(i))){
				toReturn[i] = "F";
				continue;
			}			
			String value = "";
			try {
				value = Tools.getFormatedDouble(field.getDouble(toWrite.get(i)), precision);
			} catch (IllegalArgumentException e2) {
				try {
					value = field.get(toWrite.get(i)).toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NullPointerException e){}
			toReturn[i] = value;
		}
		return toReturn;
	}

}
