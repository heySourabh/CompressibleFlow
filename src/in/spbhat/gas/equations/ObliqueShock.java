/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.geometry.Angle;
import in.spbhat.geometry.DeflectionAngle;
import in.spbhat.geometry.ShockAngle;
import in.spbhat.physics.Mach;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.*;
import static java.lang.Math.pow;

public class ObliqueShock {
    private final double gamma;

    public ObliqueShock(Gas gas) {
        this.gamma = gas.gamma();
    }

    public DeflectionAngle theta(Mach upstreamMach, ShockAngle beta) {
        Angle machAngle = upstreamMach.machAngle();
        Angle rightAngle = new Angle(90, degrees);
        if (beta.isGreaterThan(rightAngle) || beta.isLessThan(machAngle)) {
            throw new IllegalArgumentException(
                    "The value of shock angle is out of range [%s, %s] for %s.".formatted(machAngle, rightAngle, upstreamMach));
        }
        return new DeflectionAngle(arcTan(tanTheta(upstreamMach, beta)));
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
