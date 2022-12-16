/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Air;
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
            throw new IllegalArgumentException("Normal shock: Upstream Mach number is subsonic!");
        }
    }

    public double p2_by_p1(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber();
        return (2 * gamma * M1 * M1 - (gamma - 1)) / (gamma + 1);
    }

    public double rho2_by_rho1(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber();
        double M1sqr = M1 * M1;
        return (gamma + 1) * M1sqr / (2 + (gamma - 1) * M1sqr);
    }

    public double T2_by_T1(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber();

        double M1sqr = M1 * M1;
        double gm1 = gamma - 1;
        double gp1 = gamma + 1;
        double gp1sqr = gp1 * gp1;

        double numer = (2 * gamma * M1sqr - gm1) * (2 + gm1 * M1sqr);
        double denom = gp1sqr * M1sqr;

        return numer / denom;
    }

    public Mach M2(Mach upstreamMach) {
        validateSupersonic(upstreamMach);
        double M1 = upstreamMach.machNumber();
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
        double M1 = Numerical.solveBisection(eqn, new Range(1, 150));
        return new Mach(M1);
    }

    public static void main(String[] args) {
        NormalShock ns = new NormalShock(new Air());
        System.out.println(ns.p2_by_p1(new Mach(2.5)));
        System.out.println(ns.rho2_by_rho1(new Mach(2)));
        System.out.println(ns.T2_by_T1(new Mach(1.5)));
        System.out.println(ns.M2(new Mach(4.1)));
        System.out.println(ns.p02_by_p01(new Mach(2.7)));
        System.out.println(ns.p02_by_p1(new Mach(3.2)));

        System.out.println(ns.M1_using_p2_by_p1(10.33332));
        System.out.println(ns.M1_using_p02_by_p1(18.09504));

        // System.out.println(ns.p2_by_p1(new Mach(0.5))); // throws exception
        // System.out.println(ns.rho2_by_rho1(new Mach(0.999))); // throws exception
        // System.out.println(ns.T2_by_T1(new Mach(0.75))); // throws exception
        // System.out.println(ns.M2(new Mach(0.1))); // throws exception
        // System.out.println(ns.p02_by_p01(new Mach(0.7))); // throws exception
        // System.out.println(ns.p02_by_p1(new Mach(0.2)));  // throws exception
        // System.out.println(ns.M1_using_p2_by_p1(0.999999));  // throws exception
        // System.out.println(ns.M1_using_p02_by_p1(1.89292));  // throws exception for air
    }
}
