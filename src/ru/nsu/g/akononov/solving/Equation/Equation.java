package ru.nsu.g.akononov.solving.Equation;

import ru.nsu.g.akononov.solving.*;
import ru.nsu.g.akononov.solving.Range.*;

import java.util.HashSet;
import java.util.Set;

public class Equation {
    private final double a;
    private final double b;
    private final double c;
    private final EquationType type;

    public Equation(EquationType type, double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.type = type;
    }

    public Set<Double> solve(double epsilon, double delta){
        Set<Double> roots = new HashSet<>();

        switch (type){
            case quadratic:
                solveQuadratic(epsilon, roots);
                break;
            case cubic:
                solveCubic(epsilon, delta, roots);
            default:
                break;
        }

        return roots;
    }

    private void solveQuadratic(double epsilon, Set<Double> roots ){
        double D = getDiscriminant();
        if (Math.abs(D) < epsilon) {
            roots.add(-b / (2 * a));
        }
        if (D > epsilon) {
            roots.add((-b + Math.sqrt(D)) / (2 * a));
            roots.add((-b - Math.sqrt(D)) / (2 * a));
        }
    }

    private void solveCubic(double epsilon, double delta, Set<Double> roots ){
        Localization localization = new Localization(this);
        RangeSet ranges = localization.getRanges(epsilon, delta);
        ranges.findRoot(delta, epsilon, this);
        ranges.getRoots(roots);
    }

    public Equation getDerivative(){
        if(type == EquationType.cubic) {
            return new Equation(EquationType.quadratic, 3, 2 * a, b);
        }
        return null;
    }

    public double calculate(double x){
        double solution;

        switch (type){
            case cubic:
                solution = x*x*x + a*x*x + b*x + c;
                break;
            case quadratic:
                solution = a*x*x + b*x + c;
                break;
            default:
                throw new IllegalStateException("Unexpected type: " + type);
        }
        return solution;
    }

    public double getDiscriminant(){
        if(type == EquationType.cubic){
            return 4*a*a - 12*c;
        } else {
            return b*b - 4*a*c;
        }
    }
}
