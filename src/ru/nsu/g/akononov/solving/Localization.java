package ru.nsu.g.akononov.solving;

import ru.nsu.g.akononov.solving.Equation.Equation;
import ru.nsu.g.akononov.solving.Range.RangeSet;

import java.util.Set;

public class Localization {
    private final RangeSet ranges = new RangeSet();
    private final Equation equation;

    public Localization(Equation equation) {
        this.equation = equation;
    }

    private void localize(double epsilon, double delta) {

        Equation derivative = equation.getDerivative();
        double D = derivative.getDiscriminant();

        if (D < epsilon) {
            double f0 = equation.calculate(0);
            if (Math.abs(f0) < epsilon) {
                ranges.add(0, 0);
            }
            if (f0 < -epsilon) {
                ranges.add(0, Double.POSITIVE_INFINITY);
            }
            if (f0 > epsilon) {
                ranges.add(Double.NEGATIVE_INFINITY, 0);
            }
        } else {
            Set<Double> derivativeRoots = derivative.solve(epsilon, delta);
            double a = derivativeRoots.stream().min(Double::compareTo).get();
            double b = derivativeRoots.stream().max(Double::compareTo).get();

            double fa = equation.calculate(a);
            double fb = equation.calculate(b);

            if (fa > epsilon) {
                if (fb > epsilon) {
                    ranges.add(Double.NEGATIVE_INFINITY, a);
                }
                if (fb < -epsilon) {
                    ranges.add(Double.NEGATIVE_INFINITY, a);
                    ranges.add(b, Double.POSITIVE_INFINITY);
                    ranges.add(a, b);
                }
                if (Math.abs(fb) < epsilon) {
                    ranges.add(b, b);
                    ranges.add(Double.NEGATIVE_INFINITY, a);
                }
            }
            if (fa < -epsilon && fb < -epsilon) {
                ranges.add(b, Double.POSITIVE_INFINITY);
            }
            if (Math.abs(fa) < epsilon) {
                if(fb < -epsilon) {
                    ranges.add(a, a);
                    ranges.add(b, Double.POSITIVE_INFINITY);
                }
                if(Math.abs(fb) < epsilon){
                    ranges.add((a+b)/2,(a+b)/2);
                }
            }
        }
    }

    public RangeSet getRanges(double epsilon, double delta){
        localize(epsilon, delta);
        return ranges;
    }

}
