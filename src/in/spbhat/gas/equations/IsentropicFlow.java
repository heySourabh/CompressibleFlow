/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Air;
import in.spbhat.gas.Gas;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import in.spbhat.physics.Mach;
import in.spbhat.physics.Mach.MachRegime;
import in.spbhat.util.Numerical.Function;
import in.spbhat.util.Numerical.Range;

import static in.spbhat.gas.properties.Pressure.Units.Pa;
import static in.spbhat.gas.properties.Pressure.Units.bar;
import static in.spbhat.gas.properties.Temperature.Units.C;
import static in.spbhat.physics.Mach.MachRegime.*;
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
        double T0_T = T0_by_T(mach);
        double M = mach.machNumber;
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
            case Subsonic -> new Range(0, 1);
            case Supersonic -> new Range(1, 150);
            case Sonic -> throw new IllegalStateException("Flow cannot be 'sonic' for A/A* = " + A_by_ACritical);
        };
        Function eqn = M -> A_by_ACritical(new Mach(M)) - A_by_ACritical;
        double machNumber = solveBisection(eqn, M_range);
        return new Mach(machNumber);
    }

    public static void main(String[] args) {
        IsentropicFlow isentropic = new IsentropicFlow(new Air());
        System.out.println(isentropic.p2(new Pressure(101325, Pa), 1.2));
        System.out.println(isentropic.T2(new Temperature(10, C), 1.5));
        System.out.println(isentropic.T0_by_T(new Mach(2)));
        System.out.println(isentropic.p0_by_p(new Mach(0.5)));

        Temperature T0 = new Temperature(50, C);
        System.out.println(isentropic.T(new Mach(2), T0));
        Pressure p0 = new Pressure(100, bar);
        System.out.println(isentropic.p(new Mach(0.7), p0));

        Mach machSubsonic = new Mach(0.1);
        Mach machSupersonic = new Mach(2.5);

        System.out.println(isentropic.A_by_ACritical(machSubsonic));
        System.out.println(isentropic.A_by_ACritical(machSupersonic));

        System.out.println(isentropic.M_using_T0_by_T(1.08712));
        System.out.println(isentropic.M_using_T0_by_T(1.8));

        System.out.println(isentropic.M_using_p0_by_p(1.33959));
        System.out.println(isentropic.M_using_p0_by_p(7.82443));

        System.out.println(isentropic.M_using_A_by_ACritical(2.96352, Subsonic));
        System.out.println(isentropic.M_using_A_by_ACritical(2.40310, Supersonic));
        System.out.println(isentropic.M_using_A_by_ACritical(1.0, Sonic));
        System.out.println(isentropic.M_using_A_by_ACritical(2.96352, Sonic)); // throws exception
    }
}
