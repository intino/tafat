package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class TableFunction extends magritte.wraps.Definition {
    public tafat.PointSet pointSet() {
        return _get("pointSet").as(tafat.PointSet.class);
    }

    public void pointSet(tafat.PointSet value) {
        _edit().set("pointSet", value);
    }

    public magritte.Set<Type> interpolationTypes() {
        return _aggregables(tafat.TableFunction.Interpolation.class);
    }

    public Type interpolationType(int index) {
        return interpolationTypes().get(index);
    }

    public magritte.Set<Type> noneInterpolationTypes() {
        return _aggregables(tafat.TableFunction.NoneInterpolation.class);
    }

    public Type noneInterpolationType(int index) {
        return noneInterpolationTypes().get(index);
    }

    public magritte.Set<Type> linearInterpolationTypes() {
        return _aggregables(tafat.TableFunction.LinearInterpolation.class);
    }

    public Type linearInterpolationType(int index) {
        return linearInterpolationTypes().get(index);
    }

    public magritte.Set<Type> polynomialInterpolationTypes() {
        return _aggregables(tafat.TableFunction.PolynomialInterpolation.class);
    }

    public Type polynomialInterpolationType(int index) {
        return polynomialInterpolationTypes().get(index);
    }

    public magritte.Set<Type> splineInterpolationTypes() {
        return _aggregables(tafat.TableFunction.SplineInterpolation.class);
    }

    public Type splineInterpolationType(int index) {
        return splineInterpolationTypes().get(index);
    }

    public magritte.Set<Type> stepInterpolationTypes() {
        return _aggregables(tafat.TableFunction.StepInterpolation.class);
    }

    public Type stepInterpolationType(int index) {
        return stepInterpolationTypes().get(index);
    }

    public magritte.Set<Type> extrapolationTypes() {
        return _aggregables(tafat.TableFunction.Extrapolation.class);
    }

    public Type extrapolationType(int index) {
        return extrapolationTypes().get(index);
    }

    public magritte.Set<Type> noneExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.NoneExtrapolation.class);
    }

    public Type noneExtrapolationType(int index) {
        return noneExtrapolationTypes().get(index);
    }

    public magritte.Set<Type> customExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.CustomExtrapolation.class);
    }

    public Type customExtrapolationType(int index) {
        return customExtrapolationTypes().get(index);
    }

    public magritte.Set<Type> nearestPointExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.NearestPointExtrapolation.class);
    }

    public Type nearestPointExtrapolationType(int index) {
        return nearestPointExtrapolationTypes().get(index);
    }

    public magritte.Set<Type> repeatSeriesExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.RepeatSeriesExtrapolation.class);
    }

    public Type repeatSeriesExtrapolationType(int index) {
        return repeatSeriesExtrapolationTypes().get(index);
    }

    public magritte.Set<Type> linearExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.LinearExtrapolation.class);
    }

    public Type linearExtrapolationType(int index) {
        return linearExtrapolationTypes().get(index);
    }

    public magritte.Set<Type> polynomialExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.PolynomialExtrapolation.class);
    }

    public Type polynomialExtrapolationType(int index) {
        return polynomialExtrapolationTypes().get(index);
    }

    public magritte.Set<Type> splineExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.SplineExtrapolation.class);
    }

    public Type splineExtrapolationType(int index) {
        return splineExtrapolationTypes().get(index);
    }

    public magritte.Set<Type> stepExtrapolationTypes() {
        return _aggregables(tafat.TableFunction.StepExtrapolation.class);
    }

    public Type stepExtrapolationType(int index) {
        return stepExtrapolationTypes().get(index);
    }

    public tafat.TableFunction.Interpolation interpolation() {
        return _component(tafat.TableFunction.Interpolation.class);
    }

    public tafat.TableFunction.NoneInterpolation noneInterpolation() {
        return _component(tafat.TableFunction.NoneInterpolation.class);
    }

    public tafat.TableFunction.LinearInterpolation linearInterpolation() {
        return _component(tafat.TableFunction.LinearInterpolation.class);
    }

    public tafat.TableFunction.PolynomialInterpolation polynomialInterpolation() {
        return _component(tafat.TableFunction.PolynomialInterpolation.class);
    }

    public tafat.TableFunction.SplineInterpolation splineInterpolation() {
        return _component(tafat.TableFunction.SplineInterpolation.class);
    }

    public tafat.TableFunction.StepInterpolation stepInterpolation() {
        return _component(tafat.TableFunction.StepInterpolation.class);
    }

    public tafat.TableFunction.Extrapolation extrapolation() {
        return _component(tafat.TableFunction.Extrapolation.class);
    }

    public tafat.TableFunction.NoneExtrapolation noneExtrapolation() {
        return _component(tafat.TableFunction.NoneExtrapolation.class);
    }

    public tafat.TableFunction.CustomExtrapolation customExtrapolation() {
        return _component(tafat.TableFunction.CustomExtrapolation.class);
    }

    public tafat.TableFunction.NearestPointExtrapolation nearestPointExtrapolation() {
        return _component(tafat.TableFunction.NearestPointExtrapolation.class);
    }

    public tafat.TableFunction.RepeatSeriesExtrapolation repeatSeriesExtrapolation() {
        return _component(tafat.TableFunction.RepeatSeriesExtrapolation.class);
    }

    public tafat.TableFunction.LinearExtrapolation linearExtrapolation() {
        return _component(tafat.TableFunction.LinearExtrapolation.class);
    }

    public tafat.TableFunction.PolynomialExtrapolation polynomialExtrapolation() {
        return _component(tafat.TableFunction.PolynomialExtrapolation.class);
    }

    public tafat.TableFunction.SplineExtrapolation splineExtrapolation() {
        return _component(tafat.TableFunction.SplineExtrapolation.class);
    }

    public tafat.TableFunction.StepExtrapolation stepExtrapolation() {
        return _component(tafat.TableFunction.StepExtrapolation.class);
    }

    public static class Interpolation extends magritte.wraps.Definition {
    }

    public static class NoneInterpolation extends tafat.TableFunction.Interpolation {
    }

    public static class LinearInterpolation extends tafat.TableFunction.Interpolation {
    }

    public static class PolynomialInterpolation extends tafat.TableFunction.Interpolation {
    }

    public static class SplineInterpolation extends tafat.TableFunction.Interpolation {
    }

    public static class StepInterpolation extends tafat.TableFunction.Interpolation {
    }

    public static class Extrapolation extends magritte.wraps.Definition {
    }

    public static class NoneExtrapolation extends tafat.TableFunction.Extrapolation {
    }

    public static class CustomExtrapolation extends tafat.TableFunction.Extrapolation {
        public double function() {
            return ((tafat.natives.Function) _get("function").asNative()).calculate();
        }

        public void function(magritte.NativeCode value) {
            _edit().let("function", value);
        }
    }

    public static class NearestPointExtrapolation extends tafat.TableFunction.Extrapolation {
    }

    public static class RepeatSeriesExtrapolation extends tafat.TableFunction.Extrapolation {
    }

    public static class LinearExtrapolation extends tafat.TableFunction.Extrapolation {
    }

    public static class PolynomialExtrapolation extends tafat.TableFunction.Extrapolation {
    }

    public static class SplineExtrapolation extends tafat.TableFunction.Extrapolation {
    }

    public static class StepExtrapolation extends tafat.TableFunction.Extrapolation {
    }
}
