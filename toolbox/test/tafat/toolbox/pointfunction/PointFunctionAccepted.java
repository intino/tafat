package tafat.toolbox.pointfunction;

import org.junit.Test;
import tafat.toolbox.pointset.PointSet;

public class PointFunctionAccepted {

    @Test
    public void should() throws Exception {
        PointSet.define().x(1,2,3,4,5,6,7).y(1,2,3,4,5,6,7);
        //PointFunction.define().linearInterpolation().errorExtrapolation().using(pointSet);
        //PointFunction.define().linearInterpolation().errorExtrapolation()
        //PointSet.define().x(1,2,3,4,5,6,7,8,9).y(1,2,3,45)
        //        .define().point(1,2)....
    }
}
