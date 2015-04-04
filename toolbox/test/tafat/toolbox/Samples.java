package tafat.toolbox;

import org.junit.Before;
import org.junit.Test;
import tafat.toolbox.statechart.StateChart;

import static junit.framework.TestCase.assertEquals;
import static tafat.toolbox.statechart.StateChart.define;

public class Samples {

    private int value = 0;

    @Before
    public void setUp() throws Exception {
        value = 0;
    }

    @Test
    public void testStateChart() throws Exception {
        StateChart stateChart = define().
                state("OFF").in(() -> value = 0).
                state("ON").include(define().
                    state("COOLING").in(() -> value = 120).
                    state("IDLE").in(() -> value = 10).
                    transition().from("COOLING").to("IDLE").timeout(() -> 20).
                    transition().from("IDLE").to("COOLING").timeout(() -> 20).stateChart()).
                transition().from("OFF").to("ON").message("SWITCH_ON").
                transition().from("ON").to("OFF").message("SWITCH_OFF").stateChart();

        stateChart.receive("SWITCH_ON");
        stateChart.update();
        assertEquals(120, value);

        stateChart.update(20);
        assertEquals(10, value);

        stateChart.update(20);
        assertEquals(120, value);

        stateChart.receive("SWITCH_OFF");
        stateChart.update();
        assertEquals(0, value);
    }
}
