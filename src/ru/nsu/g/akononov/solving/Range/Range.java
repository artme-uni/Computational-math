package ru.nsu.g.akononov.solving.Range;

import ru.nsu.g.akononov.solving.Equation.Equation;

public class Range {

    private double beginning;
    private double end;
    private Double root = null;

    public Range(double beginning, double end) {
        this.beginning = beginning;
        this.end = end;
    }

    public double getRoot() {
        return root;
    }

    private double dichotomy(double epsilon, double delta, Equation func) {
        double estimatedRoot = (beginning + end) / 2;
        if (Math.abs(func.calculate(estimatedRoot)) < epsilon) {
            return estimatedRoot;
        } else {
            if (func.calculate(beginning) < 0) {
                if (func.calculate(estimatedRoot) < -epsilon) {
                    beginning = estimatedRoot;
                } else {
                    end = estimatedRoot;
                }
            } else {
                if (func.calculate(estimatedRoot) < -epsilon) {
                    end = estimatedRoot;
                } else {
                    beginning = estimatedRoot;
                }
            }
            estimatedRoot = dichotomy(epsilon, delta, func);
        }

        return estimatedRoot;
    }

    public void findRoot(double epsilon, double delta, Equation func) {
        makeFinite(delta, func);
        root = dichotomy(epsilon, delta, func);
    }

    private void makeFinite(double delta, Equation func) {
        if (end == Double.POSITIVE_INFINITY || beginning == Double.NEGATIVE_INFINITY) {
            double arg;
            boolean right = end == Double.POSITIVE_INFINITY;

            if (right) {
                arg = beginning;
            } else {
                arg = end;
                delta = delta * -1;
            }

            if (func.calculate(arg) < 0) {
                while (func.calculate(arg) < 0) {
                    arg += delta;
                }
            } else {
                while (func.calculate(arg) > 0) {
                    arg += delta;
                }
            }

            if (right) {
                end = arg;
                beginning = arg - delta;
            } else {
                beginning = arg;
                end = arg - delta;
            }
        }
    }

    @Override
    public String toString() {
        if (root != null) {
            return "Root: " + root;
        } else
            return "ru.nsu.g.akononov.Range.Range{" + "b=" + beginning + ", e=" + end + '}';
    }
}
