package tafat.toolbox.statechart;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StateChartAccepted {

    private int counter;

    @Before
    public void setUp() throws Exception {
        counter = 0;
    }

    @Test
    public void api_statechart_test() throws Exception {
        StateChart stateChart = new Statechart();
        stateChart.state(0).out(() -> counter++).state(1).in(() -> counter++).
                from(0).to(1).condition(() -> counter == 0).action(() -> counter++);
        stateChart.update();
        assertEquals(3, counter);
    }
}
