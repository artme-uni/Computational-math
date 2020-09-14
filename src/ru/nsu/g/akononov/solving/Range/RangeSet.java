package ru.nsu.g.akononov.solving.Range;

import ru.nsu.g.akononov.solving.Equation.Equation;

import java.util.HashSet;
import java.util.Set;

public class RangeSet {
    private int estimatedSize;
    private final Set<Range> ranges = new HashSet<>();

    public void add(double beginning, double end){
        ranges.add(new Range(beginning, end));
    }

    public void findRoot(double delta, double epsilon, Equation equation) {
        for (Range range : ranges){
            range.findRoot(epsilon, delta,equation);
        }
    }

    public void getRoots(Set<Double> roots){
        for (Range range : ranges){
            roots.add(range.getRoot());
        }
    }

    @Override
    public String toString() {
        return "ru.nsu.g.akononov.Range.RangeSet{" + "ranges=" + ranges + '}';
    }
}
