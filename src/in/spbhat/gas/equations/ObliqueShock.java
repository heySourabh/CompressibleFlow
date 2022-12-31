/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.geometry.Angle;
import in.spbhat.geometry.ShockAngle;
import in.spbhat.geometry.TurnAngle;
import in.spbhat.physics.Mach;
import in.spbhat.util.Numerical;
import in.spbhat.util.Numerical.Function;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ObliqueShock {
    public enum Strength {
        WEAK_SHOCK, STRONG_SHOCK
    }

    private final double gamma;

    public ObliqueShock(Gas gas) {
        this.gamma = gas.gamma();
    }

    private static void validateSupersonic(Mach mach) {
        if (mach.machRegime() == Mach.MachRegime.Subsonic) {
            throw new IllegalArgumentException("Oblique shock: Upstream Mach number is subsonic (" + mach + ").");
        }
    }

    public TurnAngle theta(Mach upstreamMach, ShockAngle beta) {
        validateSupersonic(upstreamMach);
        Angle machAngle = upstreamMach.machAngle();
        Angle rightAngle = new Angle(90, degrees);
        if (beta.isLessThan(machAngle) || beta.isGreaterThan(rightAngle)) {
            throw new IllegalArgumentException(
                    "The value of shock angle is out of range [%s, %s] for %s.".formatted(machAngle, rightAngle, upstreamMach));
        }
        return new TurnAngle(arcTan(tanTheta(upstreamMach, beta)));
    }

    public ShockAngle beta(Mach upstreamMach, TurnAngle theta, Strength shockStrength) {
        validateSupersonic(upstreamMach);
        Angle thetaMax = thetaMax(upstreamMach);
        Angle zero = new Angle(0, degrees);
        if (theta.isLessThan(zero) || theta.isGreaterThan(thetaMax)) {
            String message = "Attached shock solution is not possible for turn angle %s.\n".formatted(theta);
            message += "The turn angle must be in range [%s, %s] for %s.".formatted(zero, thetaMax, upstreamMach);
            throw new IllegalArgumentException(message);
        }

        ShockAngle initBeta = switch (shockStrength) {
            case WEAK_SHOCK -> new ShockAngle(upstreamMach.machAngle());
            case STRONG_SHOCK -> new ShockAngle(90, degrees);
        };

        double tanTheta = tan(theta);
        Function equation = (beta) -> tanTheta - tanTheta(upstreamMach, new ShockAngle(beta, degrees));
        Numerical numerical = new Numerical();
        double beta_degrees = numerical.solveNewtonRaphson(equation, initBeta.in(degrees));

        Angle beta = new Angle(beta_degrees, degrees);
        return new ShockAngle(beta);
    }

    public ShockAngle beta(Mach upstreamMach, TurnAngle theta) {
        return beta(upstreamMach, theta, Strength.WEAK_SHOCK);
    }

    public TurnAngle thetaMax(Mach upstreamMach) {
        return theta(upstreamMach, betaAtThetaMax(upstreamMach));
    }

    public ShockAngle betaAtThetaMax(Mach upstreamMach) {
        double M1 = upstreamMach.machNumber;
        double M1sqr = M1 * M1;

        double t1 = (gamma + 1) / (4 * gamma);
        double t2 = 1.0 / (gamma * M1sqr);
        double t3 = (gamma + 1) * (1 + (gamma - 1) / 2 * M1sqr + (gamma + 1) / 16 * M1sqr * M1sqr);

        double sinBetaMax = sqrt(t1 - t2 * (1 - sqrt(t3)));

        return new ShockAngle(arcSin(sinBetaMax));
    }

    private double tanTheta(Mach upstreamMach, ShockAngle beta) {
        double M1 = upstreamMach.machNumber;
        double M1sqr = M1 * M1;
        double sinBetaSqr = pow(sin(beta), 2);
        double numerator = 2 * cot(beta) * (M1sqr * sinBetaSqr - 1);
        double denominator = 2 + M1sqr * (gamma + cos(beta.times(2.0)));

        return numerator / denominator;
    }

    private double cot(Angle angle) {
        return 1 / tan(angle);
    }
}
