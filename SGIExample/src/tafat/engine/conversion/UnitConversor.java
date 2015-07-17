package tafat.engine.conversion;

import java.util.HashMap;

public class UnitConversor {
	private HashMap<String,Object> conversionMap = new HashMap<String,Object>();
	private static UnitConversor instance = new UnitConversor();
	
	public UnitConversor() {
		conversionMap.put("w-w", 1.);
		conversionMap.put("w-kw", 0.001);
		conversionMap.put("kw-w", 1000.0);
		conversionMap.put("m-km", 0.001);
		conversionMap.put("km-m", 1000.0);
		conversionMap.put("cm-m", 0.01);
		conversionMap.put("m-cm", 100.0);
		conversionMap.put("cm-km", 0.00001);
		conversionMap.put("km-cm", 100000.0);
		conversionMap.put("dc-df", new CelsiusFarenheitFormula());
		conversionMap.put("df-dc", new FarenheitCelsiusFormula());
		conversionMap.put("s-m", 1.0/60);
		conversionMap.put("m-s", 60.0);
		conversionMap.put("s-h", 1.0/3600);
		conversionMap.put("h-s", 3600.0);
		conversionMap.put("m-h", 1/60.0);
		conversionMap.put("h-m", 60.0);
		conversionMap.put("l-m^3", 1.0/1000.0);
		conversionMap.put("m^3-l", 1000.0);
	}

	public static double parse(String data, String unit) {
		Integer index = data.indexOf(" ");
		if (index < 0) return Double.parseDouble(data);
		
		String conversion = data.substring(index+1).toLowerCase() + "-" + unit.toLowerCase();
		Double value = Double.parseDouble(data.substring(0,index)); 
		Object object = instance.conversionMap.get(conversion);
		if (object instanceof Double) {
			Double factor = (Double) object;
			return value * factor;
		}
		else if (object instanceof UnitFormula) {
			UnitFormula formula = (UnitFormula) object;
			return formula.convert(value);
		}
		else
			return -1;		
	}

}
