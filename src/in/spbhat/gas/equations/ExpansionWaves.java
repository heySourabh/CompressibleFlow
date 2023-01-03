/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.geometry.PrandtlMeyerAngle;
import in.spbhat.physics.Mach;
import in.spbhat.util.Numerical;
import in.spbhat.util.Numerical.Function;
import in.spbhat.util.Numerical.Range;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.arcTan;

public class ExpansionWaves {
    private final double gamma;

    public ExpansionWaves(Gas gas) {
        this.gamma = gas.gamma();
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
}
