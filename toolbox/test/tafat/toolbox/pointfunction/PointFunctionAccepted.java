package tafat.toolbox.pointfunction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tafat.toolbox.pointfunction.functions.LinearOut;
import tafat.toolbox.pointset.PointSet;

import static junit.framework.TestCase.assertEquals;

public class PointFunctionAccepted {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_accept_values_within_point_set() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3).y(1, 2, 3);
        PointFunction function = PointFunction.define().using(set);

        assertEquals(1.0, function.y(1));
        assertEquals(2.0, function.y(2));
        assertEquals(3.0, function.y(3));
    }

    @Test
    public void should_reject_points_not_explicitly_declared_in_point_set() throws Exception {
        exception.expect(PointFunction.Exception.class);
        exception.expectMessage("Value 1.5 cannot be interpolated with none interpolation. Please define an interpolator");
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 3, 4, 5, 6, 7);
        PointFunction function = PointFunction.define().using(set);
        function.y(1.5);
    }

    @Test
    public void should_reject_points_out_of_point_set() throws Exception {
        exception.expect(PointFunction.Exception.class);
        exception.expectMessage("Values out of the point set cannot be extrapolated. Please defined an extrapolator");
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 3, 4, 5, 6, 7);
        PointFunction function = PointFunction.define().using(set);
        function.y(0);
    }


    @Test
    public void should_reject_point_sets_smaller_than_two_points_when_interpolating() throws Exception {
        exception.expect(PointFunction.Exception.class);
        exception.expectMessage("Point set must contain two points at least");
        PointFunction.define().using(PointSet.define().x(1).y(1));
    }

}
