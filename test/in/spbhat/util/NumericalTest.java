/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.util;

import in.spbhat.util.Numerical.Function;
import in.spbhat.util.Numerical.Range;
import org.junit.jupiter.api.Test;

import static in.spbhat.util.Numerical.solveBisection;
import static in.spbhat.util.Numerical.solveNewtonRaphson;
import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumericalTest {

    @Test
    void solveNewtonRaphsonTest() {
        Function f = x -> x + 1;
        Numerical.setTolerance(1e-12);
        assertEquals(-1, solveNewtonRaphson(f, 0), 1e-12);
        assertEquals(-sqrt(2), solveNewtonRaphson(x -> x * x - 2, -5), 1e-12);
        assertEquals(sqrt(2), solveNewtonRaphson(x -> x * x - 2, 1), 1e-12);
        assertEquals(PI / 6, solveNewtonRaphson(x -> sin(x) - 0.5, 0.0), 1e-12);
        Numerical.setTolerance(1e-8);
    }

    @Test
    void solveNewtonRaphsonTest_convergence_exception() {
        double guess = 1.0;
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> solveNewtonRaphson(x -> x * x + 2, guess));
        assertEquals("Newton-Raphson method: did not converge with initial guess of %f.".formatted(guess), error.getMessage());
    }

    @Test
    void solveBisectionTest() {
        Numerical.setTolerance(1e-8);
        assertEquals(-sqrt(2), solveBisection(x -> x * x - 2, new Range(-2, 0)), 1e-8);
        assertEquals(-sqrt(2), solveBisection(x -> x * x - 2, new Range(0, -2)), 1e-8);
        assertEquals(sqrt(2), solveBisection(x -> x * x - 2, new Range(-1, 2)), 1e-8);
    }

    @Test
    void solveBisectionTest_convergence_exception() {
        Numerical.setTolerance(1e-12);
        Numerical.setMaxIterations(10);
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> solveBisection(x -> x * x - 2, new Range(0, 2)));
        assertEquals("Bisection method: did not converge.", error.getMessage());
        Numerical.setTolerance(1e-8);
        Numerical.setMaxIterations(500);
    }

    @Test
    void solveBisectionTest_invalid_bracket_exception() {
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> solveBisection(x -> x * x - 2, new Range(2, 3)));
        assertEquals("Bisection method: provided bracket does not contain single root.", error.getMessage());
    }
}