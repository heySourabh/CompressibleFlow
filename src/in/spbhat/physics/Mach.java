package in.spbhat.physics;

import in.spbhat.geometry.Angle;
import in.spbhat.util.Formatter;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.asin;
import static in.spbhat.geometry.Angle.sin;

public record Mach(double machNumber) {
    public enum MachRegime {
        Subsonic, Supersonic, Sonic
    }

    public Angle machAngle() {
        return asin(1.0 / machNumber);
    }

    public static Mach calculateMach(Angle machAngle) {
        return new Mach(1.0 / sin(machAngle));
    }

    public MachRegime machRegime() {
        if (this.machNumber < 1)
            return MachRegime.Subsonic;
        else if (this.machNumber > 1)
            return MachRegime.Supersonic;
        else
            return MachRegime.Sonic;
    }

    @Override
    public String toString() {
        return "Mach " + Formatter.doubleToString(machNumber);
    }

    public static void main(String[] args) {
        System.out.println(new Mach(5));
        System.out.println(new Mach(2).machAngle());
        System.out.println(Mach.calculateMach(new Angle(30, degrees)));

        System.out.println(new Mach(5).machRegime());
        System.out.println(new Mach(1.00001).machRegime());
        System.out.println(new Mach(1.0).machRegime());
        System.out.println(new Mach(0.999).machRegime());
        System.out.println(new Mach(0.1).machRegime());
    }
}
