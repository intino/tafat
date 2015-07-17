package magritte.ontology;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

import static magritte.Tag.*;
import magritte.primitives.Date;
import tafat.*;
import basetest.*;



public class BaseTestStateChart extends magritte.schema.Box.Ontology {
	public static final magritte.schema.Box box = new BaseTestStateChart();

	@Override
	public magritte.schema.Box[] dependencies() {
		return new magritte.schema.Box[]{magritte.dsl.TafatDsl.box};
	}

	@Override
	public void write() {
		def("Fridge").type("Entity").set(Root);
		def("SimpleStateChart").type("Behavior").set(Facet, Abstract);
		def("SimpleStateChart+Fridge").type("Behavior").parent("SimpleStateChart").has($(3));
		def(3).type("StateChart").set(Prototype).has("SimpleStateChart+Fridge.StateChart.Off").has("SimpleStateChart+Fridge.StateChart.On").has($(6));
		def("SimpleStateChart+Fridge.StateChart.Off").type("StateChart.State").set(Prototype);
		def("SimpleStateChart+Fridge.StateChart.On").type("StateChart.State").set(Prototype);
		def(6).type("StateChart.Transition").set(Prototype).has($(7)).set("*from", (ref(""))).set("*to", (ref("")));
		def(7).type("StateChart.Transition.Condition").set(Prototype).let("*check", new Check_pansan());
	}

	private Date asDate(String date){
		try {
			return Date.date(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static class Check_pansan extends tafat.Behavior implements tafat.natives.Check {
		@Override
		public boolean check() {
			return true;
		}
	}
}