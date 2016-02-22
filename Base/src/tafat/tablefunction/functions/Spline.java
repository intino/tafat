package tafat.tablefunction.functions;

import tafat.pointset.PointSet;
import tafat.tablefunction.Function;

public class Spline implements Function {

    private Interpolator interpolator;

    @Override
    public void set(PointSet set) {
        if(interpolator == null) this.interpolator = new Interpolator(set);
    }

    @Override
    public double y(double x) {
        return interpolator.interpolate(x);
    }

    public class Interpolator {
        private PointSet set;
        private double[] mA;
        private double[] mB;
        private double[] mC;

        public Interpolator(PointSet set) {
            this.set = set;
            calcCoefficients();
        }

        private void calcCoefficients() {
            mA = new double[set.size() - 1];
            mB = new double[set.size() - 1];
            mC = new double[set.size()- 1];

            double dx1 = set.get(1).x() - set.first().x();
            double dy1 = set.get(1).y() - set.first().y();
            for (int i = 1; i < set.size() - 1; i++) {
                double dx2 = set.get(i + 1).x() - set.get(i).x();
                double dy2 = set.get(i + 1).y() - set.get(i).y();
                mC[i] = dx2 / (dx1 + dx2);
                mB[i] = 1.0f - mC[i];
                mA[i] = 6.0f * (dy2 / dx2 - dy1 / dx1) / (dx1 + dx2);
                dx1 = dx2;
                dy1 = dy2;
            }

            for (int i = 1; i < set.size() - 1; i++) {
                double p = mB[i] * mC[i - 1] + 2.0f;
                mC[i] = -mC[i] / p;
                mB[i] = (mA[i] - mB[i] * mB[i - 1]) / p;
            }

            dy1 = 0;
            for (int i = set.size() - 2; i >= 0; i--) {
                dx1 = set.get(i + 1).x() - set.get(i).x();
                double dy2 = mC[i] * dy1 + mB[i];
                mA[i] = (dy1 - dy2) / (6.0f * dx1);
                mB[i] = dy2 / 2.0f;
                mC[i] = (set.get(i + 1).y() - set.get(i).y()) / dx1 - dx1 * (mB[i] + dx1 * mA[i]);
                dy1 = dy2;
            }
        }

        public double interpolate(double x) {
            int i = GetSegmentNumb(x);
            double t = x - set.get(i).x();
            return mA[i] * t * t * t + mB[i] * t * t + mC[i] * t + set.get(i).y();
        }

        public int GetSegmentNumb(double x) {
            int left = 0;
            int right = set.size() - 1;

            while (left + 1 < right) {
                int middle = (left + right) / 2;
                if (set.get(middle).x() <= x) left = middle;
                else right = middle;
            }

            return left;
        }

    }

}
