/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import static in.spbhat.geometry.Angle.Units.degrees;

public class MachAngle extends Angle {
    public MachAngle(double value, Units units) {
        super(value, units);
    }

    public MachAngle(Angle angle) {
        super(angle.in(degrees), degrees);
    }
}
