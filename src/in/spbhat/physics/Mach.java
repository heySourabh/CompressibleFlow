package in.spbhat.physics;

import in.spbhat.geometry.Angle;
import in.spbhat.util.Formatter;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.asin;
import static in.spbhat.geometry.Angle.sin;

public record Mach(double machNumber) {
    public Angle machAngle() {
        return asin(1.0 / machNumber);
    }

    public static Mach calculateMach(Angle machAngle) {
        return new Mach(1.0 / sin(machAngle));
    }

    @Override
    public String toString() {
        return "Mach " + Formatter.doubleToString(machNumber);
    }

    public static void main(String[] args) {
        System.out.println(new Mach(5));
        System.out.println(new Mach(2).machAngle());
        System.out.println(Mach.calculateMach(new Angle(30, degrees)));
    }
}
