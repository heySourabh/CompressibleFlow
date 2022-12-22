/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import in.spbhat.physics.Mach;
import in.spbhat.physics.Mach.MachRegime;
import in.spbhat.util.Numerical.Function;
import in.spbhat.util.Numerical.Range;

import static in.spbhat.util.Numerical.solveBisection;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class IsentropicFlow {
    private final double gamma;

    public IsentropicFlow(Gas gas) {
        this.gamma = gas.gamma();
    }

    public double p2_by_p1(double T2_by_T1) {
        return pow(T2_by_T1, gamma / (gamma - 1));
    }

    public Pressure p2(Pressure p1, double T2_T1) {
        return p1.times(p2_by_p1(T2_T1));
    }

    public double T2_by_T1(double p2_by_p1) {
        return pow(p2_by_p1, (gamma - 1) / gamma);
    }

    public Temperature T2(Temperature T1, double p2_by_p1) {
        return T1.times(T2_by_T1(p2_by_p1));
    }

    public double T0_by_T(Mach mach) {
        double M = mach.machNumber;
        return 1.0 + (gamma - 1) / 2.0 * M * M;
    }

    public Temperature T(Mach mach, Temperature totalTemperature) {
        double T0_T = T0_by_T(mach);
        return totalTemperature.divide(T0_T);
    }

    public double p0_by_p(Mach mach) {
        double T0_T = T0_by_T(mach);
        return pow(T0_T, gamma / (gamma - 1));
    }

    public Pressure p(Mach mach, Pressure totalPressure) {
        double p0_p = p0_by_p(mach);
        return totalPressure.divide(p0_p);
    }

    public double A_by_ACritical(Mach mach) {
        double M = mach.machNumber;
        if (M <= 0) {
            throw new IllegalArgumentException("Isentropic flow: A/A* is not defined for " + mach);
        }
        double T0_T = T0_by_T(mach);
        return 1.0 / M * pow(2.0 / (gamma + 1) * T0_T, (gamma + 1) / 2.0 / (gamma - 1));
    }

    public Mach M_using_T0_by_T(double T0_by_T) {
        return new Mach(sqrt(2.0 / (gamma - 1) * (T0_by_T - 1)));
    }

    public Mach M_using_p0_by_p(double p0_by_p) {
        double T0_T = T2_by_T1(p0_by_p);
        return M_using_T0_by_T(T0_T);
    }

    public Mach M_using_A_by_ACritical(double A_by_ACritical, MachRegime machRegime) {
        if (A_by_ACritical == 1.0) return new Mach(1);
        Range M_range = switch (machRegime) {
            case Subsonic -> new Range(1e-15, 1);
            case Supersonic -> new Range(1, 150);
            case Sonic -> throw new IllegalStateException("Flow cannot be 'sonic' for A/A* = " + A_by_ACritical);
        };
        Function eqn = M -> A_by_ACritical(new Mach(M)) - A_by_ACritical;
        double machNumber = solveBisection(eqn, M_range);
        return new Mach(machNumber);
    }
}
