/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import in.spbhat.geometry.PrandtlMeyerAngle;
import in.spbhat.physics.Mach;
import in.spbhat.physics.Speed;
import in.spbhat.util.Numerical;
import in.spbhat.util.Numerical.Function;
import in.spbhat.util.Numerical.Range;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.arcTan;
import static java.lang.Math.pow;

public class ExpansionWaves {
    private final double gamma;
    private final Gas gas;

    public ExpansionWaves(Gas gas) {
        this.gamma = gas.gamma();
        this.gas = gas;
    }

    private static void validateSupersonic(Mach mach) {
        if (mach.machRegime() == Mach.MachRegime.Subsonic) {
            throw new IllegalArgumentException("Expansion waves: Mach number is subsonic (" + mach + ").");
        }
    }

    public PrandtlMeyerAngle nu(Mach mach) {
        validateSupersonic(mach);
        double M = mach.machNumber;
        double Msqr_m1 = M * M - 1;
        double nu_degrees = Math.sqrt((gamma + 1) / (gamma - 1))
                            * arcTan(Math.sqrt((gamma - 1) / (gamma + 1) * (Msqr_m1))).in(degrees)
                            - arcTan(Math.sqrt(Msqr_m1)).in(degrees);
        return new PrandtlMeyerAngle(nu_degrees, degrees);
    }

    public Mach M(PrandtlMeyerAngle nu) {
        Function equation = M -> nu.in(degrees) - nu(new Mach(M)).in(degrees);
        Numerical numerical = new Numerical();
        Range machRange = new Range(1, 100);
        double M = numerical.solveBisection(equation, machRange);
        return new Mach(M);
    }

    public Speed V2(Pressure p1, Pressure p2, Temperature T1, Speed V1) {
        Speed a1 = gas.soundSpeed(T1);
        double exponent = (gamma - 1) / 2 / gamma;
        return V1.add(a1
                .times(2)
                .divide(gamma - 1)
                .times(1 - pow(Pressure.ratio(p2, p1), exponent)));
    }
}
