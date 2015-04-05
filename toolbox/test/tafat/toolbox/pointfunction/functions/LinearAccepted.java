package tafat.toolbox.pointfunction.functions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tafat.toolbox.pointfunction.Function;
import tafat.toolbox.pointfunction.PointFunction;
import tafat.toolbox.pointset.PointSet;

import static junit.framework.TestCase.assertEquals;

public class LinearAccepted {

    @Test
    public void should_work_properly_with_interpolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6);
        Function function = new Linear();
        function.set(set);

        assertEquals(1.0, function.y(1));
        assertEquals(1.5, function.y(1.5));
        assertEquals(-2.5, function.y(6.5));
    }

}
