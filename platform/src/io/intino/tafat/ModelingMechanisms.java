package io.intino.tafat;

import io.intino.tafat.engine.utils.StatechartUpdater;

import static io.intino.tafat.engine.Date.getInstant;
import static io.intino.tafat.engine.utils.TaskHelper.scheduledDate;

public class ModelingMechanisms {

    public static void receiveMessage(StateChart self, String message) {
        self.message(message);
        if (self.current() != null) self.current().receiveMessage(message);
    }

    public static void updateStateChart(StateChart self, int step) {
        StatechartUpdater.update(self, step);
    }

    public static void programTask(Task self) {
        self.scheduledDate(scheduledDate(self));
    }

    public static boolean checkTimeBasedTransition(StateChart.Transition.TimeBased self, int advancedTime) {
        return !self.when().isAfter(getInstant());
    }

    public static void activateTimeout(StateChart.Transition.Timeout self) {
        self.when(getInstant().plusSeconds(self.timeout()));
    }

    public static void activateAfter(StateChart.Transition.After self) {
        self.when(getInstant().plusSeconds(self.fixedTime()));
    }

    public static void activateRate(StateChart.Transition.Rate self) {
        self.when(getInstant().plusSeconds(self.unit() / self.times()));
    }

    public static boolean checkMessageTransition(StateChart.Transition.Message self, int advancedTime) {
        return self.core$().ownerAs(StateChart.class).message().equals(self.expectedMessage());
    }

    public static boolean checkTask(Task self) {
        return !self.scheduledDate().isAfter(getInstant());
    }

    public static void executeFmu(Fmu self, int step) {
        self.realInputList().forEach(f -> self.wrapper().write(f.fmuVariableName()).with(f.push()));
        self.integerInputList().forEach(f -> self.wrapper().write(f.fmuVariableName()).with(f.push()));
        self.booleanInputList().forEach(f -> self.wrapper().write(f.fmuVariableName()).with(f.push()));
        self.stringInputList().forEach(f -> self.wrapper().write(f.fmuVariableName()).with(f.push()));
        for (int i = 0; i < (int) (step / self.step()); i++) self.wrapper().doStep(self.step());
        self.realOutputList().forEach(f -> f.pull(self.wrapper().read(f.fmuVariableName()).asDouble()));
        self.integerOutputList().forEach(f -> f.pull(self.wrapper().read(f.fmuVariableName()).asInteger()));
        self.booleanOutputList().forEach(f -> f.pull(self.wrapper().read(f.fmuVariableName()).asBoolean()));
        self.stringOutputList().forEach(f -> f.pull(self.wrapper().read(f.fmuVariableName()).asString()));
    }

    public static void executeSd(SystemDynamic self, int step) {
        self.differentialEquation().pull();
        for (int i = 0; i < self.timesPerSecond(); i++) self.odeSolver().step();
        self.differentialEquation().push();
    }

    public static double getY(TableFunction self, double... inputs) {
        return self.provider().get(inputs);
    }

}
