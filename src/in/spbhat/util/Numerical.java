package in.spbhat.util;

public class Numerical {
    @FunctionalInterface
    public interface Function {
        double eval(double x);
    }

    public record Range(double low, double high) {
    }

    public static double diff(Function f, double x) {
        double h = 1e-3;
        return (f.eval(x + h) - f.eval(x - h)) / (2.0 * h);
    }

    private static double tolerance = 1e-8;

    public static void setTolerance(double tolerance) {
        Numerical.tolerance = tolerance;
    }

    public static double solveNewtonRaphson(Function f, double x0) {
        int maxIterations = 500;
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

    public static double solveBisection(Function f, Range bracket) {
        int maxIterations = 500;

        double a = bracket.low;
        double b = bracket.high;

        if (a >= b) {
            throw new RuntimeException("Bisection method: invalid bracket %s.".formatted(bracket));
        }

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

    public static void main(String[] args) {
        Function f = x -> x + 1;
        Numerical.setTolerance(1e-12);
        System.out.println(Numerical.solveNewtonRaphson(f, 0));
        System.out.println(Numerical.solveNewtonRaphson(x -> x*x - 2, -5));
        System.out.println(Numerical.solveNewtonRaphson(x -> x*x - 2, 1));

        Numerical.setTolerance(1e-8);
        System.out.println(Numerical.solveBisection(x -> x*x - 2, new Range(-2, 0)));
        System.out.println(Numerical.solveBisection(x -> x*x - 2, new Range(-1, 2)));
    }
}
