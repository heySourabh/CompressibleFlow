/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import static in.spbhat.geometry.Angle.Units.degrees;

public class ShockAngle extends Angle {
    public ShockAngle(double value, Units units) {
        super(value, units);
    }

    public ShockAngle(Angle angle) {
        super(angle.in(degrees), degrees);
    }
}
