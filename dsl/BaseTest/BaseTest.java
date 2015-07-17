package siani.tara.dsl;

import siani.tara.semantic.model.Tara;
import java.util.Locale;
import siani.tara.semantic.model.Tag;
import static siani.tara.semantic.constraints.RuleFactory.*;

public class BaseTest extends Tara {
	public BaseTest() {
		in("").def(context("").allow(multiple("Fridge", Tag.MAIN), multiple("HeatMap"), multiple("Simulation"), name()).doc("tafat.BaseTest", 0, ""));
		in("Fridge").def(context("Entity", "Concept").allow(name(), facet("SimpleStateChart")).assume(isMain()).doc("D:\\Users\\jevora\\repositories\\tafat\\tafat\\BaseTest\\model\\StateChart.tara", 3, ""));
		in("SimpleStateChart").def(context("Behavior", "Concept").allow(multiple("Task"), multiple("Behavior.Knol"), multiple("StateChart"), multiple("EquationSystem")).require(_parameter("step", "integer", false, 0, "", "TERMINAL_INSTANCE"), _name()).assume(isFacetInstance()).doc("D:\\Users\\jevora\\repositories\\tafat\\tafat\\BaseTest\\model\\StateChart.tara", 5, ""));
		in("HeatMap.Region").def(context("Concept").allow(name(), parameter("coordinates", "double", true, 0, "##Tafat"), parameter("calculate", "native", false, 1, "Function#public double calculate()#Tafat")).require(_parameter("coordinates", "double", true, 0, "##Tafat"), _parameter("calculate", "native", false, 1, "Function#public double calculate()#Tafat")).assume(isTerminalInstance()));
		in("Task").def(context("Concept").allow(multiple("Task"), multiple("StartJob"), multiple("RecurrentJob"), multiple("EndJob"), single("Task.End"), single("Task.Duration"), single("Task.End"), single("Task.Duration"), parameter("days:word", new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}, true, 0, ""), name(), single("Task.Start")).require(_single("Task.Start")).assume(isFeatureInstance(), isTerminalInstance()));
		in("StateChart.State").def(context("Concept").allow(multiple("StateChart.State"), multiple("StateChart.Transition"), multiple("StateChart.State.EntryAction"), multiple("StateChart.State.ExitAction"), multiple("StateChart.State.EntryAction"), multiple("StateChart.State.ExitAction"), parameter("message", "string", false, 0, "##Tafat"), parameter("receiveMessage", "native", false, 1, "Message#public void receiveMessage(String message)#Tafat"), parameter("current", new String[]{"StateChart"}, false, 2, ""), name()).assume(isFeatureInstance(), isTerminalInstance()));
		in("HeatMap").def(context("Concept").allow(multiple("HeatMap.Region"), name(), parameter("path", "string", false, 0, "##Tafat")).require(_parameter("path", "string", false, 0, "##Tafat")).assume(isMain(), isTerminalInstance()));
		in("StateChart.State.EntryAction").def(context("Concept").allow(name(), parameter("action", "native", false, 0, "Action#public void execute()#Tafat")).require(_parameter("action", "native", false, 0, "Action#public void execute()#Tafat")).assume(isTerminalInstance()));
		in("StateChart.Transition.Rate").def(context("Concept").allow(parameter("timeLeft", "measure", false, 0, "Time##Tafat"), parameter("check", "native", false, 1, "Check#public boolean check()#Tafat"), parameter("activate", "native", false, 2, "Action#public void execute()#Tafat"), name(), parameter("times", "integer", false, 3, "time##Tafat"), parameter("unit", "integer", false, 4, "##Tafat")).require(_parameter("times", "integer", false, 3, "time##Tafat"), _parameter("unit", "integer", false, 4, "##Tafat")).assume(isTerminalInstance()));
		in("Behavior.Knol").def(context("Concept").allow(name()).assume(isFeatureInstance(), isTerminalInstance()));
		in("Task.Start").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time##Tafat"), name(), parameter("time", "date", false, 0, "##Tafat")).require(_parameter("time", "date", false, 0, "##Tafat")).assume(isTerminalInstance()));
		in("StateChart.Transition.After").def(context("Concept").allow(parameter("timeLeft", "measure", false, 0, "Time##Tafat"), parameter("check", "native", false, 1, "Check#public boolean check()#Tafat"), parameter("activate", "native", false, 2, "Action#public void execute()#Tafat"), name(), parameter("time", "measure", false, 3, "Time##Tafat")).require(_parameter("time", "measure", false, 3, "Time##Tafat")).assume(isTerminalInstance()));
		in("Task.End").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time##Tafat"), name(), parameter("time", "date", false, 0, "##Tafat")).require(_parameter("time", "date", false, 0, "##Tafat")).assume(isTerminalInstance()));
		in("StateChart.State.ExitAction").def(context("Concept").allow(name(), parameter("action", "native", false, 0, "Action#public void execute()#Tafat")).require(_parameter("action", "native", false, 0, "Action#public void execute()#Tafat")).assume(isTerminalInstance()));
		in("StateChart.Transition.Condition").def(context("Concept").allow(name(), parameter("check", "native", false, 0, "Check#public boolean check()#Tafat")).require(_parameter("check", "native", false, 0, "Check#public boolean check()#Tafat")).assume(isTerminalInstance()));
		in("StateChart.Transition.Message").def(context("Concept").allow(parameter("check", "native", false, 1, "Check#public boolean check()#Tafat"), name(), parameter("expectedMessage", "string", false, 0, "##Tafat")).require(_parameter("expectedMessage", "string", false, 0, "##Tafat")).assume(isTerminalInstance()));
		in("EquationSystem.Stock").def(context("Concept").allow(name(), parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).require(_parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).assume(isTerminalInstance()));
		in("EquationSystem.Flow").def(context("Concept").allow(name(), parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).require(_parameter("function", "native", false, 0, "Function#public double calculate()#Tafat")).assume(isTerminalInstance()));
		in("StateChart").def(context("Concept").allow(multiple("StateChart.State"), multiple("StateChart.Transition"), parameter("message", "string", false, 0, "##Tafat"), parameter("receiveMessage", "native", false, 1, "Message#public void receiveMessage(String message)#Tafat"), parameter("current", new String[]{"StateChart"}, false, 2, ""), name()).assume(isFeatureInstance(), isTerminalInstance()));
		in("EquationSystem").def(context("Concept").allow(multiple("EquationSystem.Stock"), multiple("EquationSystem.Flow"), multiple("EquationSystem.Stock"), multiple("EquationSystem.Flow"), parameter("solver:word", new String[]{"Euler"}, false, 0, ""), parameter("step", "double", false, 1, "##Tafat"), name()).assume(isFeatureInstance(), isTerminalInstance()));
		in("StateChart.Transition").def(context("Concept").allow(single("StateChart.Transition.Condition"), single("StateChart.Transition.Timeout"), single("StateChart.Transition.After"), single("StateChart.Transition.Rate"), single("StateChart.Transition.Message"), single("StateChart.Transition.Condition"), single("StateChart.Transition.Timeout"), single("StateChart.Transition.After"), single("StateChart.Transition.Rate"), single("StateChart.Transition.Timeout"), single("StateChart.Transition.After"), single("StateChart.Transition.Rate"), single("StateChart.Transition.Message"), parameter("action", "native", false, 2, "Action#public void execute()#Tafat"), name(), parameter("from", new String[]{"StateChart.State"}, false, 0, ""), parameter("to", new String[]{"StateChart.State"}, false, 1, "")).require(_parameter("from", new String[]{"StateChart.State"}, false, 0, ""), _parameter("to", new String[]{"StateChart.State"}, false, 1, "")).assume(isTerminalInstance()));
		in("StateChart.Transition.Timeout").def(context("Concept").allow(parameter("timeLeft", "measure", false, 0, "Time##Tafat"), parameter("check", "native", false, 1, "Check#public boolean check()#Tafat"), parameter("activate", "native", false, 2, "Action#public void execute()#Tafat"), name(), parameter("timeout", "native", false, 3, "Timeout#public int calculate()#Tafat")).require(_parameter("timeout", "native", false, 3, "Timeout#public int calculate()#Tafat")).assume(isTerminalInstance()));
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
