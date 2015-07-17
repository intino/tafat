package tafat.engine.result.aggregator;

import java.lang.reflect.Field;
import java.util.ArrayList;

import tafat.engine.Console;
import tafat.engine.ModelObject;
import tafat.engine.Tools;
import tafat.engine.result.filter.FilterHandler;

public class Average extends AggregationType {

	@Override
	public String[] calculate(ArrayList<ModelObject> toCheck, ArrayList<?> toWrite, Field field, ArrayList<FilterHandler> filters, Integer precision) {
		double average = 0;
		int noFilter = 0;
		
		for (int i = 0; i < toWrite.size(); i++){
			if (!checkFilters(filters, toCheck.get(i))){
				noFilter++;
				continue;
			}
			
			try {
				average += field.getDouble(toWrite.get(i));
			} catch (IllegalArgumentException e) {
				Console.error("Field " + field.getName() + " is not a numeric attribute (or the Double class). Sum and Average not available.");
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NullPointerException e){}
		}
		average /= (toWrite.size() - noFilter);		
		
		return new String [] {Tools.getFormatedDouble(average, precision) + ""};
	}

}
