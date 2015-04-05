package tafat.toolbox.pointfunction.functions;

import org.junit.Test;
import tafat.toolbox.pointfunction.Function;
import tafat.toolbox.pointset.PointSet;

import static junit.framework.TestCase.assertEquals;

public class LinearOutAccepted {

    @Test
    public void should_work_properly_with_extrapolation() throws Exception {
        PointSet set = PointSet.define().x(1, 2, 3, 4, 5, 6, 7).y(1, 2, 1, 2, 10, 1, -6);
        Function function = new LinearOut();
        function.set(set);

        assertEquals(-1.0, function.y(-1));
        assertEquals(-27.0, function.y(10));
    }

}
