package tafat.engine.result.select;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import tafat.engine.result.drill.Drill;
import tafat.engine.result.filter.Filter_Serie;
import tafat.engine.result.filter.Filter_Value;
import tafat.engine.result.writer.Write_Serie;
import tafat.engine.result.writer.Write_Value;

@Root
public class Select {
	@Attribute
	private String name;
	
	@Attribute(required=false)
	private String id;
	
	@Attribute(required=false)
	private String typeClass;

	@ElementList (inline=true, required=false)
	private List<Drill> drillList = new ArrayList<Drill> ();
	
	@ElementList (inline=true, required=false)
	private List<Filter_Serie> filter_SerieList = new ArrayList<Filter_Serie> ();
	
	@ElementList (inline=true, required=false)
	private List<Filter_Value> filter_ValueList = new ArrayList<Filter_Value> ();
	
	@ElementList (inline=true, required=false)
	private List<Write_Serie> write_SerieList = new ArrayList<Write_Serie> ();
	
	@ElementList (inline=true, required=false)
	private List<Write_Value> write_ValueList = new ArrayList<Write_Value> ();
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	public String getTypeClass(){
		return typeClass;
	}
	
	public List<Drill> getDrillList(){
		return drillList;
	}
	
	public List<Filter_Serie> getFilter_Serie(){
		return filter_SerieList;
	}
	
	public List<Filter_Value> getFilter_Value(){
		return filter_ValueList;
	}
	
	public List<Write_Serie> getWrite_Serie(){
		return write_SerieList;
	}
	
	public List<Write_Value> getWrite_Value(){
		return write_ValueList;
	}
}
