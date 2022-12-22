/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.util;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Numerical {
    @FunctionalInterface
    public interface Function {
        double eval(double x);
    }

    private double tolerance;
    private int maxIterations;

    public Numerical() {
        this.tolerance = 1e-8;
        this.maxIterations = 500;
    }

    public Numerical withTolerance(double tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public Numerical withMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public record Range(double low, double high) {
    }

    private double diff(Function f, double x) {
        double h = 1e-3;
        return (f.eval(x + h) - f.eval(x - h)) / (2.0 * h);
    }

    public double solveNewtonRaphson(Function f, double x0) {
        double x = x0;
        for (int i = 0; i < maxIterations; i++) {
            double fx = f.eval(x);
            if (Math.abs(fx) < tolerance)
                break;

            x = x - fx / diff(f, x);

            if (i == maxIterations - 1) {
                throw new RuntimeException("Newton-Raphson method: did not converge with initial guess of %f.".formatted(x0));
            }
        }

        return x;
    }

    public double solveBisection(Function f, Range bracket) {
        double a = min(bracket.low, bracket.high);
        double b = max(bracket.low, bracket.high);

        double fa = f.eval(a);
        double fb = f.eval(b);
        if (fa * fb > 0) {
            throw new RuntimeException("Bisection method: provided bracket does not contain single root.");
        }

        for (int i = 0; i < maxIterations; i++) {
            double c = (a + b) / 2.0;
            double fc = f.eval(c);

            if (Math.abs(fc) < 1e-15 || (b - a) / 2.0 < tolerance) {
                return c;
            }

            if (fc * fa > 0) {
                a = c;
            } else {
                b = c;
            }
        }

        throw new RuntimeException("Bisection method: did not converge.");
    }
}
