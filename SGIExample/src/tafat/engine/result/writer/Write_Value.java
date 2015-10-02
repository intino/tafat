package tafat.engine.result.writer;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Write_Value {
	@Attribute
	private String attribute;
	
	@Attribute (required=false)
	private String type;
	
	@Attribute (required=false)
	private String precision;
	
	@Attribute (required=false)
	private String behavior;
	
	public String getAttribute(){
		return attribute;
	}
	
	public String getType(){
		if (type == null)
			return "all";
		return type;
	}
	
	public String getPrecision(){
		return precision;
	}
	
	public String getBehavior(){
		return behavior;
	}
}
