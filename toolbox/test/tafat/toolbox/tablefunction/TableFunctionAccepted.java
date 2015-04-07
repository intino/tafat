package tafat.toolbox.tablefunction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tafat.toolbox.pointset.PointSet;

import static junit.framework.TestCase.assertEquals;

public class TableFunctionAccepted {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_accept_values_within_point_set() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3).y(1, 2, 3).pointSet();
        TableFunction function = TableFunction.define().points(set).tableFunction();

        assertEquals(1.0, function.y(1));
        assertEquals(2.0, function.y(2));
        assertEquals(3.0, function.y(3));
    }

    @Test
    public void should_reject_points_not_explicitly_declared_in_point_set() throws Exception {
        exception.expect(TableFunction.Exception.class);
        exception.expectMessage("Value 1.5 cannot be interpolated with none interpolation. Please define an interpolator");
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 3, 4, 5, 6, 7).pointSet();
        TableFunction function = TableFunction.define().points(set).tableFunction();
        function.y(1.5);
    }

    @Test
    public void should_reject_points_out_of_point_set() throws Exception {
        exception.expect(TableFunction.Exception.class);
        exception.expectMessage("Values out of the point set cannot be extrapolated. Please defined an extrapolator");
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 3, 4, 5, 6, 7).pointSet();
        TableFunction function = TableFunction.define().points(set).tableFunction();
        function.y(0);
    }


    @Test
    public void should_reject_point_sets_smaller_than_two_points_when_interpolating() throws Exception {
        exception.expect(TableFunction.Exception.class);
        exception.expectMessage("Point set must contain two points at least");
        TableFunction.define().points(PointSet.define().x(1).y(1).pointSet()).tableFunction();
    }

    @Test
    public void should_work_properly_with_custom_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet();
        TableFunction function = TableFunction.define().customExtrapolation(3).points(set).tableFunction();
        assertEquals(1.0, function.y(1));
        assertEquals(3.0, function.y(21312414));
    }

    @Test
    public void should_work_properly_with_linear_interpolation_and_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet();
        TableFunction function = TableFunction.define().linearInterpolation().linearExtrapolation().points(set).tableFunction();

        assertEquals(1.0, function.y(1));
        assertEquals(1.5, function.y(1.5));
        assertEquals(-2.5, function.y(6.5));

        assertEquals(-1.0, function.y(-1));
        assertEquals(-27.0, function.y(10));
    }

    @Test
    public void should_work_properly_with_nearest_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet();
        TableFunction function = TableFunction.define().nearestExtrapolation().points(set).tableFunction();

        assertEquals(-6.0, function.y(232523));
        assertEquals(1.0, function.y(-124124));
    }

    @Test
    public void should_work_properly_with_repeat_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet();
        TableFunction function = TableFunction.define().repeatSerieExtrapolation().points(set).tableFunction();

        assertEquals(1.0, function.y(1));
        assertEquals(10.0, function.y(-1));
        assertEquals(2.0, function.y(10));
        assertEquals(2.0, function.y(14));
        assertEquals(1.0, function.y(21));
        assertEquals(1.0, function.y(15));
        assertEquals(10.0, function.y(-7));
        assertEquals(2.0, function.y(-14));
        assertEquals(10.0, function.y(-1));
    }

    @Test
    public void should_work_properly_with_step_interpolation_and_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet() ;
        TableFunction function = TableFunction.define().stepInterpolation().nearestPointExtrapolation().points(set).tableFunction();

        assertEquals(1.0, function.y(1), 0.001);
        assertEquals(1.0, function.y(1.5), 0.001);
        assertEquals(1.0, function.y(6.5), 0.001);
        assertEquals(1.0, function.y(-1), 0.001);
        assertEquals(-6.0, function.y(10), 0.001);
    }

    @Test
    public void should_work_properly_with_spline_interpolation_and_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet() ;
        TableFunction function = TableFunction.define().splineInterpolation().splineExtrapolation().points(set).tableFunction();

        assertEquals(1.0, function.y(1), 0.001);
        assertEquals(1.670, function.y(1.5), 0.001);
        assertEquals(-3.204, function.y(6.5), 0.001);
        assertEquals(1.730, function.y(-1), 0.001);
        assertEquals(-72.076, function.y(10), 0.001);
        assertEquals(-686.077, function.y(14), 0.001);
        assertEquals(-5231.500, function.y(21), 0.001);
        assertEquals(-1008.615, function.y(15), 0.001);
        assertEquals(222.384, function.y(-7), 0.001);
        assertEquals(1515.230, function.y(-14), 0.001);
        assertEquals(1.730, function.y(-1), 0.001);
    }

    @Test
    public void should_work_properly_with_polynomial_interpolation_and_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet() ;
        TableFunction function = TableFunction.define().polynomialInterpolation(3).polynomialExtrapolation(3).points(set).tableFunction();

        assertEquals(1.642, function.y(1), 0.001);
        assertEquals(0.272, function.y(1.5), 0.001);
        assertEquals(-0.665, function.y(6.5), 0.001);
        assertEquals(25.071, function.y(-1), 0.001);
        assertEquals(-99.071, function.y(10), 0.001);
        assertEquals(-466.357, function.y(14), 0.001);
        assertEquals(-2228.357, function.y(21), 0.001);
        assertEquals(-618.357, function.y(15), 0.001);
        assertEquals(437.642, function.y(-7), 0.001);
        assertEquals(2136.642, function.y(-14), 0.001);
        assertEquals(25.0714, function.y(-1), 0.001);
    }

    @Test
    public void should_work_properly_with_polynomial_interpolation_and_extrapolation_order_five() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6).pointSet() ;
        TableFunction function = TableFunction.define().polynomialInterpolation(5).polynomialExtrapolation(5).points(set).tableFunction();

        assertEquals(0.889, function.y(1), 0.001);
        assertEquals(4.682, function.y(1.5), 0.001);
        assertEquals(-4.0439, function.y(6.5), 0.001);
        assertEquals(-307.928, function.y(-1), 0.001);
        assertEquals(799.571, function.y(10), 0.001);
        assertEquals(14589.844, function.y(14), 0.001);
        assertEquals(232313.071, function.y(21), 0.001);
        assertEquals(24231.707, function.y(15), 0.001);
        assertEquals(-25741.292, function.y(-7), 0.001);
        assertEquals(-321726.428, function.y(-14), 0.001);
        assertEquals(-307.928, function.y(-1), 0.001);
    }

}
