package io.intino.tafat.toolbox;

import io.intino.tafat.toolbox.pointset.PointSet;
import io.intino.tafat.toolbox.tablefunction.TableFunction;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import io.intino.tafat.toolbox.statechart.StateChart;

import static junit.framework.TestCase.assertEquals;
import static io.intino.tafat.toolbox.statechart.StateChart.define;

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

    @Test
    public void testPointSet() throws Exception {
        PointSet points1 = PointSet.define().x(1, 2, 3).y(1, 2, 3).pointSet();
        PointSet points2 = PointSet.define().point(1, 1).point(2, 2).point(3, 3).pointSet();

        assertEquals(points1.first().x(), points2.first().x());
        assertEquals(points1.get(1).x(), points2.get(1).x());
        assertEquals(points1.last().x(), points2.last().x());
    }

    @Test
    public void testTableFunction() throws Exception {
        PointSet points = PointSet.define().x(1, 2, 3).y(1, 2, 3).pointSet();
        TestCase.assertEquals(2.0, TableFunction.define().points(points).tableFunction().y(2));
        assertEquals(1.5, TableFunction.define().linearInterpolation().points(points).tableFunction().y(1.5));
        assertEquals(7.0, TableFunction.define().linearInterpolation().linearExtrapolation().points(points).tableFunction().y(7));
        assertEquals(7.0, TableFunction.define().linearExtrapolation().points(points).tableFunction().y(7));
    }
}
