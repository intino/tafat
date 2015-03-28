package tafat.toolbox.statechart;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class StateChartAccepted {

    private int counter;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        counter = 0;
    }

    @Test
    public void should_store_state() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0);
        assertEquals(1, stateChart.states.size());
        assertEquals(0, stateChart.states.get(0).id());
    }

    @Test
    public void should_store_states() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(3);
        assertEquals(2, stateChart.states.size());
        assertEquals(0, stateChart.states.get(0).id());
        assertEquals(3, stateChart.states.get(1).id());
    }

    @Test
    public void should_set_in_to_state() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).in(() -> counter++);
        stateChart.states.get(0).in();
        assertEquals(1, counter);
    }

    @Test
    public void should_set_correctly_different_ins_to_state() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).in(() -> counter++)
                .state(1).in(() -> counter = 5);
        stateChart.states.get(1).in();
        assertEquals(5, counter);
        stateChart.states.get(0).in();
        assertEquals(6, counter);
    }

    @Test
    public void should_set_out_to_state() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).out(() -> counter++)
                .state(1).out(() -> counter = 5);
        stateChart.states.get(1).out();
        assertEquals(5, counter);
        stateChart.states.get(0).out();
        assertEquals(6, counter);
    }

    @Test
    public void should_reject_state_with_same_id() throws Exception {
        exception.expect(StateChartException.class);
        exception.expectMessage("State with identifier 0 has been already defined");
        new StateChart().state(0).state(0);
    }

    @Test
    public void should_store_transition() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(10).from(0).to(10);
        assertEquals(1, stateChart.transitions.size());
        assertEquals(0, stateChart.transitions.get(0).from());
        assertEquals(10, stateChart.transitions.get(0).to());
    }

    @Test
    public void should_reject_transition_with_invalid_state_at_from() throws Exception {
        exception.expect(StateChartException.class);
        exception.expectMessage("Transition has a non-existing from state: 1");
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(10).from(1).to(10);
    }

    @Test
    public void should_reject_transition_with_invalid_state_at_to() throws Exception {
        exception.expect(StateChartException.class);
        exception.expectMessage("Transition has a non-existing to state: 1");
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(10).from(0).to(1);
    }

    @Test
    public void should_transition_to_next_state() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(10).from(0).to(10).condition(() -> true);
        assertEquals(0, stateChart.currentState());
        stateChart.update();
        assertEquals(10, stateChart.currentState());
    }

    @Test
    public void should_execute_first_transition() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(10).
                from(0).to(10).condition(() -> true).action(() -> counter = 5).
                from(0).to(10).condition(() -> true).action(() -> counter = 20);
        stateChart.update();
        assertEquals(5, counter);
    }

    @Test
    public void should_pass_reward_test() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).out(() -> counter++).state(1).in(() -> counter++).
                from(0).to(1).condition(() -> counter == 0).action(() -> counter++);
        stateChart.update();
        assertEquals(3, counter);
    }

    @Test
    public void should_transition_when_receiving_a_message() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).out(() -> counter++).state(1).in(() -> counter++).
                from(0).to(1).message("pass").action(() -> counter++);
        stateChart.receive("pass");
        stateChart.update();
        assertEquals(3, counter);
    }

    @Test
    public void should_not_transition_when_receiving_a_wrong_message() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).out(() -> counter++).state(1).in(() -> counter++).
                from(0).to(1).message("pass").action(() -> counter++);
        stateChart.receive("x");
        stateChart.update();
        assertEquals(0, counter);
    }

    @Test
    public void should_transition_using_proper_message() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(1).
                from(0).to(1).message("pass").action(() -> counter = 5).
                from(0).to(1).message("pass2").action(() -> counter = 20);
        stateChart.receive("pass2");
        stateChart.update();
        assertEquals(20, counter);
    }

    @Test
    public void should_transition_after_timeout() throws Exception {
        StateChart stateChart = new StateChart();
        stateChart.state(0).state(1).
                from(0).to(1).timeout(() -> 45).action(() -> counter = 10);
        stateChart.update();
        assertEquals(10, counter);
    }

    // TODO DIFFERENT TYPES OF TRANSITIONS
    // TODO STATES WITH STATECHARTS INSIDE

}
