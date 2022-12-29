/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import in.spbhat.util.Formatter;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.Units.radians;
import static java.lang.Math.PI;

public class Angle {
    public enum Units {
        degrees(1.0, "°"),
        radians(180.0 / PI, "rad");

        private final double conversion;
        private final String unitStr;

        Units(double conversion, String unitStr) {
            this.conversion = conversion;
            this.unitStr = unitStr;
        }
    }

    private final double value;
    private final Units units;

    public Angle(double value, Units units) {
        double totalAngle = switch (units) {
            case degrees -> 360;
            case radians -> 2 * PI;
        };
        double halfTotalAngle = totalAngle / 2;

        double validAngle = value % totalAngle;
        double constrainedAngle;
        if (validAngle > halfTotalAngle) {
            constrainedAngle = validAngle - totalAngle;
        } else if (validAngle < -halfTotalAngle) {
            constrainedAngle = validAngle + totalAngle;
        } else {
            constrainedAngle = validAngle;
        }

        this.value = constrainedAngle;
        this.units = units;
    }

    public double in(Units units) {
        double angle_degrees = this.value * this.units.conversion;
        return angle_degrees / units.conversion;
    }

    public Angle to(Units units) {
        return new Angle(in(units), units);
    }

    public static double sin(Angle angle) {
        return Math.sin(angle.in(radians));
    }

    public static double cos(Angle angle) {
        return Math.cos(angle.in(radians));
    }

    public static double tan(Angle angle) {
        return Math.tan(angle.in(radians));
    }

    public static Angle arcSin(double value) {
        return new Angle(Math.asin(value), radians).to(degrees);
    }

    public static Angle arcCos(double value) {
        return new Angle(Math.acos(value), radians).to(degrees);
    }

    public static Angle arcTan(double value) {
        return new Angle(Math.atan(value), radians).to(degrees);
    }

    public Angle times(double multiple) {
        return new Angle(value * multiple, units);
    }

    @Override
    public String toString() {
        if (this.units == radians)
            return Formatter.doubleToString(this.value / PI) + "π rad";
        else
            return Formatter.doubleToString(this.value) + this.units.unitStr;
    }
}
