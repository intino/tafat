package tafat.toolbox.pointset;

abstract class Definition {

    protected final PointSet pointSet;

    public Definition(PointSet pointSet) {
        this.pointSet = pointSet;
    }

    public static class Start extends Definition {

        public Start(PointSet pointSet) {
            super(pointSet);
        }


        public Y x(double... values) {
            pointSet.x(values);
            return new Y(pointSet);
        }

        public PointSet point(double x, double y) {
            return pointSet.point(x, y);
        }

    }

    public static class Y extends Definition {

        Y(PointSet pointSet) {
            super(pointSet);
        }

        public PointSet y(double... values) {
            pointSet.y(values);
            return pointSet;
        }

    }

}
