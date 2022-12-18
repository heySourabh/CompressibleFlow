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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumericalTest {

    @Test
    void solveNewtonRaphsonTest() {
        Function f = x -> x + 1;
        Numerical.setTolerance(1e-12);
        assertEquals(-1, solveNewtonRaphson(f, 0), 1e-12);
        assertEquals(-Math.sqrt(2), solveNewtonRaphson(x -> x * x - 2, -5), 1e-12);
        assertEquals(Math.sqrt(2), solveNewtonRaphson(x -> x * x - 2, 1), 1e-12);
    }

    @Test
    void solveNewtonRaphsonTest_convergence_exception() {
        assertThrows(RuntimeException.class, () -> solveNewtonRaphson(x -> x * x + 2, 1));
    }

    @Test
    void solveBisectionTest() {
        Numerical.setTolerance(1e-8);
        assertEquals(-Math.sqrt(2), solveBisection(x -> x * x - 2, new Range(-2, 0)), 1e-8);
        assertEquals(Math.sqrt(2), solveBisection(x -> x * x - 2, new Range(-1, 2)), 1e-8);
    }

    @Test
    void solveBisectionTest_invalid_range_exception() {
        assertThrows(RuntimeException.class,
                () -> solveBisection(x -> x * x - 2, new Range(0, -2)));
    }

    @Test
    void solveBisectionTest_convergence_exception() {
        Numerical.setTolerance(1e-12);
        assertThrows(RuntimeException.class,
                () -> solveBisection(x -> x * x - 2, new Range(0, 1e200)));
    }

    @Test
    void solveBisectionTest_invalid_bracket_exception() {
        assertThrows(RuntimeException.class,
                () -> solveBisection(x -> x * x - 2, new Range(2, 3)));
    }
}