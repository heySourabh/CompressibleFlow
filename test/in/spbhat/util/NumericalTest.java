/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.util;

import in.spbhat.util.Numerical.Function;
import in.spbhat.util.Numerical.Range;
import org.junit.jupiter.api.Test;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumericalTest {

    @Test
    void solveNewtonRaphsonTest() {
        Function f = x -> x + 1;
        Numerical numerical = new Numerical().withTolerance(1e-12);
        assertEquals(-1, numerical.solveNewtonRaphson(f, 0), 1e-12);
        assertEquals(-sqrt(2), numerical.solveNewtonRaphson(x -> x * x - 2, -5), 1e-12);
        assertEquals(sqrt(2), numerical.solveNewtonRaphson(x -> x * x - 2, 1), 1e-12);
        assertEquals(PI / 6, numerical.solveNewtonRaphson(x -> sin(x) - 0.5, 0.0), 1e-12);
    }

    @Test
    void solveNewtonRaphsonTest_convergence_exception() {
        Numerical numerical = new Numerical();
        double guess = 1.0;
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> numerical.solveNewtonRaphson(x -> x * x + 2, guess));
        assertEquals("Newton-Raphson method: did not converge with initial guess of %f.".formatted(guess), error.getMessage());
    }

    @Test
    void solveBisectionTest() {
        Numerical numerical = new Numerical().withTolerance(1e-8);
        assertEquals(-sqrt(2), numerical.solveBisection(x -> x * x - 2, new Range(-2, 0)), 1e-8);
        assertEquals(-sqrt(2), numerical.solveBisection(x -> x * x - 2, new Range(0, -2)), 1e-8);
        assertEquals(sqrt(2), numerical.solveBisection(x -> x * x - 2, new Range(-1, 2)), 1e-8);
    }

    @Test
    void solveBisectionTest_convergence_exception() {
        Numerical numerical = new Numerical()
                .withTolerance(1e-12)
                .withMaxIterations(10);
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> numerical.solveBisection(x -> x * x - 2, new Range(0, 2)));
        assertEquals("Bisection method: did not converge.", error.getMessage());
    }

    @Test
    void solveBisectionTest_invalid_bracket_exception() {
        Numerical numerical = new Numerical();
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> numerical.solveBisection(x -> x * x - 2, new Range(2, 3)));
        assertEquals("Bisection method: provided bracket does not contain single root.", error.getMessage());
    }
}