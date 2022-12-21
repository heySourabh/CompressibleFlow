/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.geometry.Angle;
import in.spbhat.util.Formatter;

import static in.spbhat.geometry.Angle.asin;
import static in.spbhat.geometry.Angle.sin;
import static java.lang.Math.abs;

public class Mach {
    public final double machNumber;

    public Mach(double machNumber) {
        this.machNumber = abs(machNumber);
    }

    public Mach(Angle machAngle) {
        this(1.0 / sin(machAngle));
    }

    public enum MachRegime {
        Subsonic, Supersonic, Sonic
    }

    public Angle machAngle() {
        if (machRegime() == MachRegime.Subsonic) {
            throw new IllegalStateException("Cannot compute Mach angle for 'subsonic' flow: " + this);
        }
        return asin(1.0 / machNumber);
    }

    public MachRegime machRegime() {
        if (this.machNumber < 1) return MachRegime.Subsonic;
        if (this.machNumber > 1) return MachRegime.Supersonic;
        return MachRegime.Sonic;
    }

    @Override
    public String toString() {
        return "Mach " + Formatter.doubleToString(machNumber);
    }
}
