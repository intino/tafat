package tara.dsl;

import tara.language.model.Tag;

import java.util.Locale;
import static tara.language.semantics.constraints.RuleFactory.*;

public class BaseTest extends Tara {
	public BaseTest() {
		in("").def(context("").allow(multiple("Fridge", Tag.MAIN), multiple("HeatMap"), multiple("Simulation"), name()).doc("tafat.BaseTest", 0, ""));
		in("Fridge").def(context("Entity", "Concept").allow(name(), facet("SimpleStateChart")).assume(isMain()).doc("D:\\Users\\jevora\\repositories\\tafat\\tafat\\BaseTest\\model\\StateChart.tara", 3, ""));
		in("SimpleStateChart").def(context("Behavior", "Concept").allow(multiple("Task"), multiple("Behavior.Knol"), multiple("EquationSystem")).require(_parameter("step", "integer", false, 0, "", "TERMINAL_INSTANCE"), _name()).assume(isFacetInstance()).doc("D:\\Users\\jevora\\repositories\\tafat\\tafat\\BaseTest\\model\\StateChart.tara", 6, ""));
		in("SimpleStateChart_Fridge.[annonymous@StateChart].Off").def(context("StateChart.State", "Concept").allow(name()).doc("D:\\Users\\jevora\\repositories\\tafat\\tafat\\BaseTest\\model\\StateChart.tara", 9, ""));
		in("SimpleStateChart_Fridge.[annonymous@StateChart].On").def(context("StateChart.State", "Concept").allow(name()).doc("D:\\Users\\jevora\\repositories\\tafat\\tafat\\BaseTest\\model\\StateChart.tara", 10, ""));
		in("HeatMap.Region").def(context("Concept").allow(name(), parameter("coordinates", "double", true, 0, "##Tafat"), parameter("calculate", "native", false, 1, "Function#public double calculate()#Tafat")).require(_parameter("coordinates", "double", true, 0, "##Tafat"), _parameter("calculate", "native", false, 1, "Function#public double calculate()#Tafat")).assume(isTerminalInstance()));
		in("Task").def(context("Concept").allow(multiple("Task"), multiple("StartJob"), multiple("RecurrentJob"), multiple("EndJob"), single("Task.End"), single("Task.Duration"), single("Task.End"), single("Task.Duration"), parameter("days:word", new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}, true, 0, ""), name(), single("Task.Start")).require(_single("Task.Start")).assume(isFeatureInstance(), isTerminalInstance()));
		in("HeatMap").def(context("Concept").allow(multiple("HeatMap.Region"), name(), parameter("path", "string", false, 0, "##Tafat")).require(_parameter("path", "string", false, 0, "##Tafat")).assume(isMain(), isTerminalInstance()));
		in("Behavior.Knol").def(context("Concept").allow(name()).assume(isFeatureInstance(), isTerminalInstance()));
		in("Task.Start").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time##Tafat"), name(), parameter("time", "date", false, 0, "##Tafat")).require(_parameter("time", "date", false, 0, "##Tafat")).assume(isTerminalInstance()));
		in("Task.End").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time##Tafat"), name(), parameter("time", "date", false, 0, "##Tafat")).require(_parameter("time", "date", false, 0, "##Tafat")).assume(isTerminalInstance()));
		in("EquationSystem.Stock").def(context("Concept").allow(name(), parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).require(_parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).assume(isTerminalInstance()));
		in("EquationSystem.Flow").def(context("Concept").allow(name(), parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).require(_parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).assume(isTerminalInstance()));
		in("EquationSystem").def(context("Concept").allow(multiple("EquationSystem.Stock"), multiple("EquationSystem.Flow"), multiple("EquationSystem.Stock"), multiple("EquationSystem.Flow"), parameter("solver:word", new String[]{"Euler"}, false, 0, ""), parameter("step", "double", false, 1, "##Tafat"), name()).assume(isFeatureInstance(), isTerminalInstance()));
		in("Simulation").def(context("Concept").allow(name(), parameter("from", "date", false, 0, "##Tafat"), parameter("to", "date", false, 1, "##Tafat")).require(_parameter("from", "date", false, 0, "##Tafat"), _parameter("to", "date", false, 1, "##Tafat")).assume(isTerminalInstance(), isMain()));
		in("Task.Duration").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time##Tafat"), name(), parameter("time", "measure", false, 0, "Time##Tafat")).require(_parameter("time", "measure", false, 0, "Time##Tafat")).assume(isTerminalInstance()));
	}

	@Override
	public String languageName() {
		return "BaseTest";
	}

	@Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public boolean isTerminalLanguage() {
        return true;
    }
}
