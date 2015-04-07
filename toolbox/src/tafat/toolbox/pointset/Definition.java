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

        public Point point(double x, double y) {
            pointSet.point(x, y);
            return new Point(pointSet);
        }

    }

    public static class Y extends Definition {

        Y(PointSet pointSet) {
            super(pointSet);
        }

        public Finished y(double... values) {
            pointSet.y(values);
            return new Finished(pointSet);
        }


    }

    public static class Point extends Finished{

        public Point(PointSet pointSet) {
            super(pointSet);
        }
        public Point point(double x, double y) {
            pointSet.point(x, y);
            return this;
        }

    }

    public static class Finished extends Definition{

        public Finished(PointSet pointSet) {
            super(pointSet);
        }

        public PointSet pointSet() {
            pointSet.commit();
            return pointSet;
        }
    }
}
