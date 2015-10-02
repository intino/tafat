package tafat.engine.result.export;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import tafat.engine.DateParser;
import tafat.engine.result.select.Select;

@Root
public class Export {
	@Attribute
	private String name;
	
	@Attribute (required=false)
	private String dataType = "csv";
	
	@Attribute (required=false)
	private String from;
	
	@Attribute (required=false)
	private String to;
	
	@Attribute (required=false)
	private String step;

	
	@Attribute (required=false)
	private String splitBy;
	
	@ElementList (inline=true)
	private List<Select> selectList = new ArrayList<Select> ();
	
	public String getName(){
		return name;
	}
	
	public String getDataType(){
		return dataType;
	}
		
	public Date getFrom(){
		if (from == null)
			return null;
		
		try {
			return DateParser.parseDateAndTime(from);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getTo(){
		if (to == null)
			return null;
		
		try {
			return DateParser.parseDateAndTime(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getStep(){
		try{
			return Integer.parseInt(step);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return 1;
	}

	public String getSplitBy(){
		return splitBy;
	}
	
	public List<Select> getSelectList(){
		return selectList;
	}
}
