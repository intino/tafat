package tafat.toolbox.statechart;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static tafat.toolbox.statechart.StateChart.define;

public class StateChartAccepted {

    private int value;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        value = 0;
    }

    @Test
    public void should_store_state() throws Exception {
        StateChart stateChart = define().state("0").stateChart();
        assertEquals(1, stateChart.states.size());
        assertEquals("0", stateChart.states.get(0).id);
    }

    @Test
    public void should_store_states() throws Exception {
        StateChart stateChart = define().state("0").state("3").stateChart();
        assertEquals(2, stateChart.states.size());
        assertEquals("0", stateChart.states.get(0).id);
        assertEquals("3", stateChart.states.get(1).id);
    }

    @Test
    public void should_set_in_to_state() throws Exception {
        StateChart stateChart = define().state("0").in(() -> value++).stateChart();
        stateChart.states.get(0).in.execute();
        assertEquals(1, value);
    }

    @Test
    public void should_set_correctly_different_ins_to_state() throws Exception {
        StateChart stateChart = define().state("0").in(() -> value++).state("1").in(() -> value = 5).stateChart();
        stateChart.states.get(1).in.execute();
        assertEquals(5, value);
        stateChart.states.get(0).in.execute();
        assertEquals(6, value);
    }

    @Test
    public void should_set_out_to_state() throws Exception {
        StateChart stateChart = define().state("0").out(() -> value++).state("1").out(() -> value = 5).stateChart();
        stateChart.states.get(1).out.execute();
        assertEquals(5, value);
        stateChart.states.get(0).out.execute();
        assertEquals(6, value);
    }

    @Test
    public void should_reject_state_with_same_id() throws Exception {
        exception.expect(StateChart.Exception.class);
        exception.expectMessage("State uses an identifier 0 that has been already defined");
        define().state("0").state("0");
    }

    @Test
    public void should_store_transition() throws Exception {
        StateChart stateChart = define().state("0").state("10").transition().
                from("0").to("10").condition(() -> true).stateChart();
        assertEquals(1, stateChart.transitions.size());
        assertEquals("0", stateChart.transitions.get(0).from.id);
        assertEquals("10", stateChart.transitions.get(0).to.id);
    }

    @Test
    public void should_reject_transition_with_invalid_state_at_from() throws Exception {
        exception.expect(StateChart.Exception.class);
        exception.expectMessage("Transition has a non-existing from state: 1. From states in transition has to be inside the state chart where the transition is declared");
        define().state("0").state("10").transition().from("1").to("10").condition(() -> true).stateChart();
    }

    @Test
    public void should_reject_transition_with_invalid_state_at_to() throws Exception {
        exception.expect(StateChart.Exception.class);
        exception.expectMessage("State 1 does not exist");
        define().state("0").state("10").transition().from("0").to("1").condition(() -> true).stateChart().update();
    }

    @Test
    public void should_transition_to_next_state() throws Exception {
        StateChart stateChart = define().state("0").state("10").
                transition().from("0").to("10").condition(() -> true).stateChart();
        assertEquals("0", stateChart.currentState());
        stateChart.update();
        assertEquals("10", stateChart.currentState());
    }

    @Test
    public void should_execute_first_transition() throws Exception {
        StateChart stateChart = define().state("0").state("10").
                transition().from("0").to("10").condition(() -> true).action(() -> value = 5).
                transition().from("0").to("10").condition(() -> true).action(() -> value = 20).stateChart();
        stateChart.update();
        assertEquals(5, value);
    }

    @Test
    public void should_pass_reward_test() throws Exception {
        StateChart stateChart = define().state("0").out(() -> value++).state("1").in(() -> value++).
                transition().from("0").to("1").condition(() -> value == 0).action(() -> value++).stateChart();
        stateChart.update();
        assertEquals(3, value);
    }

    @Test
    public void should_transition_when_receiving_a_message() throws Exception {
        StateChart stateChart = define().state("0").out(() -> value++).state("1").in(() -> value++).
                transition().from("0").to("1").message("pass").action(() -> value++).stateChart();
        stateChart.receive("pass");
        stateChart.update();
        assertEquals(3, value);
    }

    @Test
    public void should_not_transition_when_receiving_a_wrong_message() throws Exception {
        StateChart stateChart = define().state("0").out(() -> value++).state("1").in(() -> value++).
                transition().from("0").to("1").message("pass").action(() -> value++).stateChart();
        stateChart.receive("x");
        stateChart.update();
        assertEquals(0, value);
    }

    @Test
    public void should_transition_using_proper_message() throws Exception {
        StateChart stateChart = define().state("0").state("1").
                transition().from("0").to("1").message("pass").action(() -> value = 5).
                transition().from("0").to("1").message("pass2").action(() -> value = 20).stateChart();
        stateChart.receive("pass2");
        stateChart.update();
        assertEquals(20, value);
    }

    @Test
    public void should_transition_after_timeout() throws Exception {
        StateChart stateChart = define().state("0").state("1").
                transition().from("0").to("1").timeout(() -> 60).action(() -> value = 10).stateChart();
        stateChart.update(30);
        assertEquals(0, value);
        stateChart.update(29);
        assertEquals(0, value);
        stateChart.update(1);
        assertEquals(10, value);
    }

    @Test
    public void should_transition_three_times_with_timeouts() throws Exception {
        StateChart stateChart = define().state("0").state("1").
                transition().from("0").to("1").timeout(() -> 20).action(() -> value += 10).
                transition().from("1").to("0").timeout(() -> 20).action(() -> value += 10).stateChart();
        stateChart.update(20);
        stateChart.update(0);
        assertEquals(10, value);
        stateChart.update(20);
        stateChart.update(0);
        assertEquals(20, value);
        stateChart.update(20);
        stateChart.update(0);
        assertEquals(30, value);
    }


    @Test
    public void should_transition_expected_times_per_unit() throws Exception {
        int time = 0;
        StateChart stateChart = define().state("0").state("1").
                transition().from("0").to("1").rate(3, 60).action(() -> value += 10).
                transition().from("1").to("0").rate(3, 60).action(() -> value += 10).stateChart();
        for (int i = 0; i < 40; i++, time++) {
            stateChart.update(1);
            if (stateChart.currentState().equals("1")) break;
        }
        assertEquals(10, value);
        for (int i = 0; i < 40; i++, time++) {
            stateChart.update(1);
            if (stateChart.currentState().equals("0")) break;
        }
        assertEquals(20, value);
        for (int i = 0; i < 40; i++, time++) {
            stateChart.update(1);
            if (stateChart.currentState().equals("1")) break;
        }
        assertEquals(30, value);
    }

    @Test
    public void should_work_with_internal_state_charts() throws Exception {
        StateChart stateChart = define().state("0").
                state("1").include(
                define().state("2").state("3").
                        transition().from("2").to("3").timeout(() -> 10).action(() -> value += 10).
                        transition().from("3").to("2").timeout(() -> 10).action(() -> value += 10).stateChart()).
                transition().from("0").to("1").condition(() -> true).stateChart();
        stateChart.update();
        assertEquals(0, value);
        stateChart.update(10);
        assertEquals(10, value);
        stateChart.update(10);
        assertEquals(20, value);
    }

    @Test
    public void should_work_with_washing_machine_state_chart() throws Exception {
        String OFF = "0", ON = "1", HEATING = "2", WASHING = "3", DRYING = "4";
        StateChart stateChart = define().
                state(OFF).in(() -> value = 0).
                state(ON).include(define().
                    state(HEATING).in(() -> value = 1000).
                    state(WASHING).in(() -> value = 500).
                    state(DRYING).in(() -> value = 800).
                    transition().from(HEATING).to(WASHING).timeout(() -> 20).
                    transition().from(WASHING).to(DRYING).timeout(() -> 20).
                    transition().from(DRYING).to(OFF).timeout(() -> 20).stateChart()).
                transition().from(OFF).to(ON).message("ON").stateChart();
        //---------------------
        assertEquals(0, value);
        assertEquals(OFF, stateChart.currentState());

        stateChart.receive("ON");
        stateChart.update();
        assertEquals(1000, value);
        assertEquals(ON + "." + HEATING, stateChart.currentState());

        stateChart.update(20);
        assertEquals(500, value);
        assertEquals(ON + "." + WASHING, stateChart.currentState());

        stateChart.update(20);
        assertEquals(800, value);
        assertEquals(ON + "." + DRYING, stateChart.currentState());

        stateChart.update(20);
        assertEquals(0, value);
        assertEquals(OFF, stateChart.currentState());

        stateChart.update(20);
        assertEquals(0, value);
        assertEquals(OFF, stateChart.currentState());
    }

    @Test
    public void should_pass_state_chart_with_several_state_charts_inside() throws Exception {
        String OFF = "0", TOTALLY_OFF = "0", WAKEABLE = "1", ON = "1", NORMAL = "0", CONTROLLED = "1", COOLING = "0", IDLE = "1";

        StateChart stateChart = define().
                state(OFF).in(() -> value += 1).out(() -> value += 1).include(define().
                    state(TOTALLY_OFF).in(() -> value += 2).out(() -> value += 2).
                    state(WAKEABLE).in(() -> value += 3).out(() -> value += 3).
                    transition().from(TOTALLY_OFF).to(WAKEABLE).message("WAKEABLE").
                    transition().from(WAKEABLE).to(TOTALLY_OFF).message("TOTALLY_OFF").stateChart()).
                state(ON).in(() -> value += 4).out(() -> value += 4).include(define().
                    state(NORMAL).in(() -> value += 6).out(() -> value += 6).include(define().
                        state(COOLING).in(() -> value += 7).out(() -> value += 7).
                        state(IDLE).in(() -> value += 8).out(() -> value += 8).
                        transition().from(COOLING).to(IDLE).timeout(() -> 20).
                        transition().from(IDLE).to(COOLING).timeout(() -> 20).stateChart()).
                    state(CONTROLLED).in(() -> value += 5).out(() -> value += 5).
                    transition().from(CONTROLLED).to(NORMAL).message("CONTROL_OFF").
                    transition().from(NORMAL).to(CONTROLLED).message("CONTROL_ON").stateChart()).
                transition().from(OFF).to(ON).message("ON").
                transition().from(ON).to(OFF).message("OFF").stateChart();

        //----------
        stateChart.receive("WAKEABLE");
        stateChart.update();
        assertEquals(5, value);

        stateChart.receive("TOTALLY_OFF");
        stateChart.update();
        assertEquals(10, value);

        stateChart.receive("ON");
        stateChart.update();
        assertEquals(30, value);

        stateChart.update(19);
        assertEquals(30, value);

        stateChart.update(20);
        assertEquals(45, value);

        stateChart.update(19);
        assertEquals(45, value);

        stateChart.update(20);
        assertEquals(60, value);

        stateChart.receive("CONTROL_ON");
        stateChart.update(20);
        assertEquals(94, value);

        stateChart.receive("ON");
        stateChart.update();
        assertEquals(94, value);

        stateChart.receive("OFF");
        stateChart.update();
        assertEquals(106, value);
    }

    @Test
    public void should_work_with_transitions_to_inner_state() throws Exception {
        StateChart stateChart = define().
                state("0").state("1").include(define().
                    state("0").in(() -> value += 1).out(() -> value += 3).state("1").include(define().
                        state("0").state("1").in(() -> value += 2).stateChart()).
                    transition().from("0").to("1.1").condition(() -> true).action(() -> value += 20).stateChart()).
                transition().from("0").to("1").condition(() -> true).action(() -> value += 20).stateChart();
        stateChart.update();
        assertEquals(46, value);
    }

    // TODO TRANSITIONS TO INNER STATE
}
