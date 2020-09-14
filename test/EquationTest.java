import org.junit.jupiter.api.Test;
import ru.nsu.g.akononov.solving.Equation.Equation;
import ru.nsu.g.akononov.solving.Equation.EquationType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {
    double epsilon = 10E-10;

    @Test
    void solveQuadratic1() {
        Equation equation = new Equation(EquationType.quadratic, 1,1,1);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(0, roots.size());
    }

    @Test
    void solveQuadratic2() {
        Equation equation = new Equation(EquationType.quadratic, 1,-11,28);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(roots.contains((double) 7));
        assertTrue(roots.contains((double) 4));
    }

    @Test
    void solveQuadratic3() {
        Equation equation = new Equation(EquationType.quadratic, 4,-44,121);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(1, roots.size());
        assertTrue(roots.contains(5.5));
    }

    @Test
    void solveQuadratic4() {
        Equation equation = new Equation(EquationType.quadratic, 1,-1,-40200);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(roots.contains((double) 201));
        assertTrue(roots.contains((double) -200));
    }

    @Test
    void solveCubic1() {
        Equation equation = new Equation(EquationType.cubic, 0,0,0);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(1, roots.size());
        assertTrue(roots.contains((double) 0));
    }

    @Test
    void solveCubic2() {
        Equation equation = new Equation(EquationType.cubic, 100.25,0,0);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(isRoot(roots, -100.25));
        assertTrue(isRoot(roots, 0));
    }

    @Test
    void solveCubic3() {
        Equation equation = new Equation(EquationType.cubic, -7.31,0,0);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(isRoot(roots, 7.31));
        assertTrue(isRoot(roots, 0));
    }

    @Test
    void solveCubic4() {
        Equation equation = new Equation(EquationType.cubic, -3.4,-4.95,0);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(3, roots.size());
        assertTrue(isRoot(roots, 0));
        assertTrue(isRoot(roots, 4.5));
        assertTrue(isRoot(roots, -1.1));
    }

    @Test
    void solveCubic5() {
        Equation equation = new Equation(EquationType.cubic, 171.37,1,171.37);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(1, roots.size());
        assertTrue(isRoot(roots, -171.37));
    }

    @Test
    void solveCubic6() {
        Equation equation = new Equation(EquationType.cubic, -0.375,2,-0.75);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(1, roots.size());
        assertTrue(isRoot(roots, 0.375));
    }

    @Test
    void solveCubic7() {
        Equation equation = new Equation(EquationType.cubic, -1,2,-2);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(1, roots.size());
        assertTrue(isRoot(roots, 1));
    }

    @Test
    void solveCubic8() {
        Equation equation = new Equation(EquationType.cubic, 3.37,-25.48,29.48);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(isRoot(roots, 2));
        assertTrue(isRoot(roots, -7.37));
    }

    @Test
    void solveCubic9() {
        Equation equation = new Equation(EquationType.cubic, 4.003,4.012,0.012);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(isRoot(roots, -2));
        assertTrue(isRoot(roots, -0.003));
    }

    @Test
    void solveCubic10() {
        Equation equation = new Equation(EquationType.cubic, -4.003,4.012,-0.012);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(isRoot(roots, 2));
        assertTrue(isRoot(roots, 0.003));
    }

    @Test
    void solveCubic11() {
        Equation equation = new Equation(EquationType.cubic, 2000,1000000,0);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(2, roots.size());
        assertTrue(isRoot(roots, 0));
        assertTrue(isRoot(roots, -1000));
    }

    @Test
    void solveCubic12() {
        Equation equation = new Equation(EquationType.cubic, -6.6,13.31,-7.986);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(3, roots.size());
        assertTrue(isRoot(roots, 1.1));
        assertTrue(isRoot(roots, 2.2));
        assertTrue(isRoot(roots, 3.3));
    }

    @Test
    void solveCubic13() {
        Equation equation = new Equation(EquationType.cubic, 6.6,13.31,7.986);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(3, roots.size());
        assertTrue(isRoot(roots, -1.1));
        assertTrue(isRoot(roots, -2.2));
        assertTrue(isRoot(roots, -3.3));
    }

    @Test
    void solveCubic14() {
        Equation equation = new Equation(EquationType.cubic, 100000,-1.2321,-123210);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(3, roots.size());
        assertTrue(isRoot(roots, -1.11));
        assertTrue(isRoot(roots, 1.11));
        assertTrue(isRoot(roots, -100000));
    }

    @Test
    void solveCubic15() {
        Equation equation = new Equation(EquationType.cubic, -100000,-1.2321,123210);
        Set<Double> roots = equation.solve(epsilon, 1);
        assertEquals(3, roots.size());
        assertTrue(isRoot(roots, -1.11));
        assertTrue(isRoot(roots, 1.11));
        assertTrue(isRoot(roots, 100000));
    }

    public boolean isRoot(Set<Double> roots, double estimatedRoot){
        return roots.stream()
                .filter(x -> estimatedRoot - epsilon < x && x < estimatedRoot + epsilon)
                .count() == 1;
    }
}