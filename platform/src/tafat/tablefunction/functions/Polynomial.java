package tafat.tablefunction.functions;

import tafat.pointset.PointSet;
import tafat.tablefunction.Function;

public class Polynomial implements Function {

    private final int order;
    private Interpolator interpolator;

    public Polynomial(int order) {
        this.order = order;
    }

    @Override
    public void set(PointSet set) {
        if (interpolator == null) this.interpolator = new Interpolator(set, order);
    }

    @Override
    public double y(double x) {
        return interpolator.interpolate(x);
    }

    private class Interpolator {
        double coefficients[];

        public Interpolator(PointSet set, int order) {
            if (order > set.size()) order = set.size();
            coefficients = new double[order + 1];
            calculateCoefficients(set, order);
        }

        private void calculateCoefficients(PointSet set, int order) {
            calculateCoefficients(allX(set), allY(set), order);
        }

        private double[] allX(PointSet set) {
            double[] x = new double[set.size()];
            for (int i = 0; i < x.length; i++) x[i] = set.get(i).x();
            return x;
        }

        private double[] allY(PointSet set) {
            double[] y = new double[set.size()];
            for (int i = 0; i < y.length; i++) y[i] = set.get(i).y();
            return y;
        }

        private void calculateCoefficients(double x[], double y[], int order) {
            int n = x.length;
            double A[][] = new double[order + 1][order + 1];
            A[0][0] = (double) n;
            double Y[] = new double[order + 1];
            Y[0] = vecSum(y);
            double XP[] = new double[n];
            double sum;

            System.arraycopy(x, 0, XP, 0, x.length);
            for (int k = 0; k <= 2 * order - 1; k++) {
                if (k < order){
                    sum = 0.0;
                    for (int j = 0; j < n; j++) sum = sum + XP[j] * y[j];
                    Y[k + 1] = sum;
                }
                sum = vecSum(XP);
                int ii = Math.max(0, k - order + 1);
                int jj = Math.min(k + 1, order);
                for (int ij = 0; ij < k + 2; ij++) {
                    A[ii][jj] = sum;
                    ii++;
                    jj--;
                    if (ii > order || jj < 0) break;
                }
                for (int j = 0; j < n; j++) XP[j] = XP[j] * x[j];
            }
            solve(A, Y, coefficients);
        }

        public double interpolate(double x) {
            int n = coefficients.length;
            double val = coefficients[n - 1];
            for (int i = n - 2; i >= 0; i--) val = coefficients[i] + x * val;
            return val;
        }

        private double vecSum(double x[]) {
            double val = 0.0;
            for (double aX : x) val = val + aX;
            return val;
        }

        private void solve(final double A[][], final double Y[], double X[]) {
            int n = A.length;
            int m = n + 1;
            double B[][] = new double[n][m];
            int row[] = new int[n];
            int hold, I_pivot;
            double pivot, abs_pivot;

            for (int i = 0; i < n; i++) {
                System.arraycopy(A[i], 0, B[i], 0, n);
                B[i][n] = Y[i];
            }

            for (int k = 0; k < n; k++) row[k] = k;
            for (int k = 0; k < n; k++) {
                pivot = B[row[k]][k];
                abs_pivot = Math.abs(pivot);
                I_pivot = k;
                for (int i = k; i < n; i++) {
                    if (Math.abs(B[row[i]][k]) > abs_pivot) {
                        I_pivot = i;
                        pivot = B[row[i]][k];
                        abs_pivot = Math.abs(pivot);
                    }
                }
                hold = row[k];
                row[k] = row[I_pivot];
                row[I_pivot] = hold;
                if (abs_pivot < 1.0E-10) {
                    for (int j = k + 1; j < n + 1; j++) {
                        B[row[k]][j] = 0.0;
                    }
                    System.out.println("redundant row (singular) " + row[k]);
                }
                else {
                    for (int j = k + 1; j < n + 1; j++) B[row[k]][j] = B[row[k]][j] / B[row[k]][k];
                    for (int i = 0; i < n; i++) {
                        if (i != k) {
                            for (int j = k + 1; j < n + 1; j++) {
                                B[row[i]][j] = B[row[i]][j] - B[row[i]][k] * B[row[k]][j];
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                X[i] = B[row[i]][n];
            }
        }

    }
}
