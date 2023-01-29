/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.physics.Mach;
import in.spbhat.util.Formatter;
import in.spbhat.util.Numerical;
import in.spbhat.util.Numerical.Function;
import in.spbhat.util.Numerical.Range;

import static java.lang.Math.sqrt;

public class NormalShock {
    private final Gas gas;
    private final double gamma;

    public NormalShock(Gas gas) {
        this.gas = gas;
        this.gamma = gas.gamma();
    }

    private static void validateSupersonic(Mach mach) {
        if (mach.machRegime() == Mach.MachRegime.Subsonic) {
            throw new IllegalArgumentException("Normal shock: Upstream Mach number is subsonic (" + mach + ").");
        }
    }

    public double p2_by_p1(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber;
        return (2 * gamma * M1 * M1 - (gamma - 1)) / (gamma + 1);
    }

    public double rho2_by_rho1(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber;
        double M1sqr = M1 * M1;
        return (gamma + 1) * M1sqr / (2 + (gamma - 1) * M1sqr);
    }

    public double T2_by_T1(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber;

        double M1sqr = M1 * M1;
        double gm1 = gamma - 1;
        double gp1 = gamma + 1;
        double gp1sqr = gp1 * gp1;

        double numerator = (2 * gamma * M1sqr - gm1) * (2 + gm1 * M1sqr);
        double denominator = gp1sqr * M1sqr;

        return numerator / denominator;
    }

    public Mach M2(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber;
        double M1sqr = M1 * M1;
        double M2sqr = ((gamma - 1) * M1sqr + 2) / (2 * gamma * M1sqr - (gamma - 1));

        return new Mach(sqrt(M2sqr));
    }

    public double p02_by_p01(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        Mach M2 = M2(upstreamMach);
        IsentropicFlow isentropicFlow = new IsentropicFlow(gas);
        double p02_p2 = isentropicFlow.p0_by_p(M2);
        double p01_p1 = isentropicFlow.p0_by_p(upstreamMach);
        double p2_p1 = p2_by_p1(upstreamMach);

        return p02_p2 / p01_p1 * p2_p1;
    }

    public double p02_by_p1(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        Mach M2 = M2(upstreamMach);
        IsentropicFlow isentropicFlow = new IsentropicFlow(gas);
        double p02_p2 = isentropicFlow.p0_by_p(M2);
        double p2_p1 = p2_by_p1(upstreamMach);

        return p02_p2 * p2_p1;
    }

    public Mach M1_using_p2_by_p1(double p2_by_p1) {
        if (p2_by_p1 < 1) {
            throw new IllegalArgumentException("Normal shock: p2/p1 must be greater than or equal to 1.");
        }
        double M1sqr = ((gamma + 1) * p2_by_p1 + (gamma - 1)) / (2 * gamma);
        return new Mach(sqrt(M1sqr));
    }

    public Mach M1_using_p02_by_p1(double p02_by_p1) {
        double p02_p1_sonic = p02_by_p1(new Mach(1));
        if (p02_by_p1 < p02_p1_sonic) {
            throw new IllegalArgumentException("Normal shock: p02/p1 must be greater than or equal to " +
                                               Formatter.doubleToString(p02_p1_sonic));
        }
        Function eqn = M1 -> p02_by_p1(new Mach(M1)) - p02_by_p1;
        Numerical numerical = new Numerical();
        double M1 = numerical.solveBisection(eqn, new Range(1, 150));
        return new Mach(M1);
    }

    public Mach M1_using_p02_by_p01(double p02_by_p01) {
        if (p02_by_p01 > 1) {
            throw new IllegalArgumentException("Normal shock: p02/p01 must be less than or equal to 1.");
        }
        Function eqn = M1 -> p02_by_p01(new Mach(M1)) - p02_by_p01;
        Numerical numerical = new Numerical();
        double M1 = numerical.solveBisection(eqn, new Range(1, 150));
        return new Mach(M1);
    }

    public Mach M1_using_M2(Mach downstreamMach) {
        double M2 = downstreamMach.machNumber;
        if (M2 > 1) {
            throw new IllegalArgumentException("Normal shock: downstream Mach must be less than 1.");
        }
        Function eqn = M1 -> M2(new Mach(M1)).machNumber - M2;
        Numerical numerical = new Numerical();
        double M1 = numerical.solveBisection(eqn, new Range(1, 156));
        return new Mach(M1);
    }
}
