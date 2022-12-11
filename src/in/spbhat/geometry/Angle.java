package in.spbhat.geometry;

import in.spbhat.util.Formatter;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.Units.radians;

public class Angle {
    public enum Units {
        degrees(1.0, "°"),
        radians(180.0 / Math.PI, "rad");

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
        this.value = value;
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

    public static Angle asin(double value) {
        return new Angle(Math.asin(value), radians).to(degrees);
    }

    public static Angle acos(double value) {
        return new Angle(Math.acos(value), radians).to(degrees);
    }

    @Override
    public String toString() {
        if (this.units == radians)
            return Formatter.doubleToString(this.value / Math.PI) + "π rad";
        else
            return Formatter.doubleToString(this.value) + this.units.unitStr;
    }

    public static void main(String[] args) {
        System.out.println(new Angle(90, degrees).to(radians));
        System.out.println(new Angle(Math.PI, radians).to(degrees));
        System.out.println(new Angle(60, degrees).to(radians));
        System.out.println(new Angle(Math.PI / 3.0, radians).to(degrees));

        System.out.println(new Angle(60, degrees).in(radians));
        System.out.println(new Angle(Math.PI / 3.0, radians).in(degrees));

        System.out.println(sin(new Angle(30, degrees)));
        System.out.println(cos(new Angle(60, degrees)));
        System.out.println(cos(new Angle(Math.PI / 3, radians)));

        System.out.println(asin(0.5));
        System.out.println(acos(0.5));
    }
}
