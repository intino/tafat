package tafat.toolbox.pointset;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;
import static tafat.toolbox.pointset.PointSet.define;

public class PointSetAccepted {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_accept_x_and_y_definition() throws Exception {
        PointSet set = define().x(1, 2, 3, 4, 5).y(1, 2, 3, 4, 5);

        assertEquals(1.0, set.get(0).x());
        assertEquals(1.0, set.get(0).y());

        assertEquals(3.0, set.get(2).x());
        assertEquals(3.0, set.get(2).y());

        assertEquals(5.0, set.get(4).x());
        assertEquals(5.0, set.get(4).y());
    }

    @Test
    public void should_accept_points_definition() throws Exception {
        PointSet set = define().point(1, 1).point(2, 2).point(3, 3).point(4, 4).point(5, 5);

        assertEquals(1.0, set.get(0).x());
        assertEquals(1.0, set.get(0).y());

        assertEquals(3.0, set.get(2).x());
        assertEquals(3.0, set.get(2).y());

        assertEquals(5.0, set.get(4).x());
        assertEquals(5.0, set.get(4).y());
    }

    @Test
    public void should_reject_different_x_and_y_sizes() throws Exception {
        exception.expect(PointSet.Exception.class);
        exception.expectMessage("X and Y coordinates do not have same size");
        define().x(1,2,3).y(1,2);
    }
}
