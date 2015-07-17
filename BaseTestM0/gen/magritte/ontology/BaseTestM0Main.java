package magritte.ontology;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

import static magritte.Tag.*;
import magritte.primitives.Date;
import basetest.*;




public class BaseTestM0Main extends magritte.schema.Box.Scene {
	public static final magritte.schema.Box box = new BaseTestM0Main();

	@Override
	public magritte.schema.Box[] dependencies() {
		return new magritte.schema.Box[]{magritte.dsl.BaseTestDsl.box};
	}

	@Override
	public void write() {
		def(1).type("Fridge").type("SimpleStateChart+Fridge").set(Root);
	}

	private Date asDate(String date){
		try {
			return Date.date(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date).getTime());
		} catch (ParseException e) {
			return null;
		}
	}


}