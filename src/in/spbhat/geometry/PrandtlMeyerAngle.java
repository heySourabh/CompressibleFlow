/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import static in.spbhat.geometry.Angle.Units.degrees;

public class PrandtlMeyerAngle extends Angle {
    public PrandtlMeyerAngle(double value, Units units) {
        super(value, units);
    }

    public PrandtlMeyerAngle(Angle angle) {
        this(angle.in(degrees), degrees);
    }
}
