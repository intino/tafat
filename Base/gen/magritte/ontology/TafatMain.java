package magritte.ontology;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

import static magritte.Tag.*;

import magritte.Node;
import magritte.primitives.Date;

import tafat.*;



public class TafatMain extends magritte.schema.Box.Ontology {
	public static final magritte.schema.Box box = new TafatMain();

	@Override
	public magritte.schema.Box[] dependencies() {
		return new magritte.schema.Box[]{};
	}

	@Override
	public void write() {
		def("Simulation").type("Concept").set(Root);
		def("Agent").type("Concept");
		def("Entity").type("Concept").holds("Entity").holds("Entity.Feature");
		def("Entity.Feature").type("Concept");
		def("Facet").type("Concept").set(Abstract);
		def("Behavior").type("Concept").parent("Facet").allows("Behavior.Knol").allows("Action").allows("EquationSystem").allows("StateChart").allows("Task").allows("TableFunction").allows("PointSet").allows("Job").allows("Map").set("**step", (1));
		def("Behavior.Knol").type("Concept");
		def("Action").type("Concept").let("*condition", new Condition_vecban());
		def("EquationSystem").type("Concept").holds("EquationSystem.Element").holds("EquationSystem.Stock").holds("EquationSystem.Flow").set("**solver", ("Euler")).set("**step", (0.1));
		def("EquationSystem.Element").type("Concept").set(Abstract);
		def("EquationSystem.Stock").type("Concept").parent("EquationSystem.Element");
		def("EquationSystem.Flow").type("Concept").parent("EquationSystem.Element");
		def("StateChart").type("Concept").allows("StateChart.State").holds("StateChart.Transition").set("**message", ("")).let("**receiveMessage", new ReceiveMessage_yoszet());
		def("StateChart.State").type("Concept").parent("StateChart").allows("StateChart.State").holds("StateChart.Transition").holds("StateChart.State.Action").holds("StateChart.State.EntryAction").holds("StateChart.State.ExitAction");
		def("StateChart.State.Action").type("Concept").set(Abstract);
		def("StateChart.State.EntryAction").type("Concept").parent("StateChart.State.Action");
		def("StateChart.State.ExitAction").type("Concept").parent("StateChart.State.Action");
		def("StateChart.Transition").type("Concept").allows("StateChart.Transition.Trigger").allows("StateChart.Transition.Condition").allows("StateChart.Transition.TimeBased").allows("StateChart.Transition.Timeout").allows("StateChart.Transition.After").allows("StateChart.Transition.Rate").allows("StateChart.Transition.Message");
		def("StateChart.Transition.Trigger").type("Concept").set(Abstract);
		def("StateChart.Transition.Condition").type("Concept").parent("StateChart.Transition.Trigger");
		def("StateChart.Transition.TimeBased").type("Concept").set(Abstract).parent("StateChart.Transition.Trigger").set("**timeLeft", ("0")).let("**check", new Check_lunmitfror());
		def("StateChart.Transition.Timeout").type("Concept").parent("StateChart.Transition.TimeBased").let("**activate", new Activate_drosnecvun());
		def("StateChart.Transition.After").type("Concept").parent("StateChart.Transition.TimeBased").let("**activate", new Activate_nuncraton());
		def("StateChart.Transition.Rate").type("Concept").parent("StateChart.Transition.TimeBased").let("**activate", new Activate_drosyuc());
		def("StateChart.Transition.Message").type("Concept").parent("StateChart.Transition.Trigger").let("**check", new Check_ralnel());
		def("Task").type("Concept").requires("Task.Start").allows("Task.Finish").allows("Task.End").allows("Task.Duration").allows("Task").allows("JobAction").set("**days", multiple("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
		def("Task.Start").type("Concept").set("**deviation", ("0"));
		def("Task.Finish").type("Concept").set(Abstract);
		def("Task.End").type("Concept").parent("Task.Finish").set("**deviation", ("0"));
		def("Task.Duration").type("Concept").parent("Task.Finish").set("**deviation", ("0"));
		def("JobAction").type("Concept").set(Abstract);
		def("StartJob").type("Concept").parent("JobAction");
		def("RecurrentJob").type("Concept").parent("JobAction");
		def("EndJob").type("Concept").parent("JobAction");
		def("Job").type("Concept").allows("Job.Start").allows("Job.Duration").allows("Job").allows("JobAction").holds("Job.Action").holds("Job.StartAction").holds("Job.RecurrentAction").holds("Job.EndAction");
		def("Job.Start").type("Concept").set("*deviation", ("0"));
		def("Job.Duration").type("Concept").set("*deviation", ("0"));
		def("Job.Action").type("Concept").set(Abstract);
		def("Job.StartAction").type("Concept").parent("Job.Action");
		def("Job.RecurrentAction").type("Concept").parent("Job.Action");
		def("Job.EndAction").type("Concept").parent("Job.Action");
		def("TableFunction").type("Concept").allows("TableFunction.Interpolation").allows("TableFunction.NoneInterpolation").allows("TableFunction.LinearInterpolation").allows("TableFunction.PolynomialInterpolation").allows("TableFunction.SplineInterpolation").allows("TableFunction.StepInterpolation").allows("TableFunction.Extrapolation").allows("TableFunction.NoneExtrapolation").allows("TableFunction.CustomExtrapolation").allows("TableFunction.NearestPointExtrapolation").allows("TableFunction.RepeatSeriesExtrapolation").allows("TableFunction.LinearExtrapolation").allows("TableFunction.PolynomialExtrapolation").allows("TableFunction.SplineExtrapolation").allows("TableFunction.StepExtrapolation");
		def("TableFunction.Interpolation").type("Concept").set(Abstract);
		def("TableFunction.NoneInterpolation").type("Concept").parent("TableFunction.Interpolation");
		def("TableFunction.LinearInterpolation").type("Concept").parent("TableFunction.Interpolation");
		def("TableFunction.PolynomialInterpolation").type("Concept").parent("TableFunction.Interpolation");
		def("TableFunction.SplineInterpolation").type("Concept").parent("TableFunction.Interpolation");
		def("TableFunction.StepInterpolation").type("Concept").parent("TableFunction.Interpolation");
		def("TableFunction.Extrapolation").type("Concept").set(Abstract);
		def("TableFunction.NoneExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("TableFunction.CustomExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("TableFunction.NearestPointExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("TableFunction.RepeatSeriesExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("TableFunction.LinearExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("TableFunction.PolynomialExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("TableFunction.SplineExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("TableFunction.StepExtrapolation").type("Concept").parent("TableFunction.Extrapolation");
		def("PointSet").type("Concept").holds("PointSet.Definition").holds("PointSet.Point").allows("PointSet.X").allows("PointSet.Y");
		def("PointSet.Definition").type("Concept").set(Abstract);
		def("PointSet.Point").type("Concept").parent("PointSet.Definition");
		def("PointSet.X").type("Concept").parent("PointSet.Definition");
		def("PointSet.Y").type("Concept").parent("PointSet.Definition");
		def("Map").type("Concept").holds("Map.Entry").let("*get", new Get_mucfrurti());
		def("Map.Entry").type("Concept");
		def("View").type("Concept").set(Abstract, Root);
		def("HeatMap").type("Concept").set(Root).parent("View").holds("HeatMap.Region");
		def("HeatMap.Region").type("Concept");
	}

	private Date asDate(String date){
		try {
			return Date.date(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static class Condition_vecban extends tafat.Action implements tafat.natives.Check {
		@Override
		public boolean check() {
			return true;
		}
	}

	public static class ReceiveMessage_yoszet extends tafat.StateChart implements tafat.natives.Message {
		@Override
		public void receiveMessage(String message) {
			message(message);if(current() != null) current().receiveMessage(message);
		}
	}

	public static class Check_lunmitfror extends tafat.StateChart.Transition.TimeBased implements tafat.natives.Check {
		@Override
		public boolean check() {
			return timeLeft() <= 0;
		}
	}

	public static class Activate_drosnecvun extends tafat.StateChart.Transition.Timeout implements tafat.natives.Action {
		@Override
		public void execute() {
			timeLeft(timeout());
		}
	}

	public static class Activate_nuncraton implements tafat.natives.Action {
		@Override
		public void execute() {
			timeLeft(time());
		}

        private void timeLeft(double time) {

        }

        private double time() {
            return 0;
        }

        @Override
		public void _target(Node node) {

		}

		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}

	public static class Activate_drosyuc extends tafat.StateChart.Transition.Rate implements tafat.natives.Action {
		@Override
		public void execute() {
			timeLeft(unit() / times());
		}
	}

	public static class Check_ralnel extends tafat.StateChart.Transition.Message implements tafat.natives.Check {
		@Override
		public boolean check() {
			return _owner(StateChart.class).message().equals(expectedMessage());
		}
	}

	public static class Get_mucfrurti extends tafat.Map implements tafat.natives.Get {
		@Override
		public double get(String key) {
			return 0;
		}
	}
}