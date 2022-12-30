/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import static in.spbhat.geometry.Angle.Units.degrees;

public class DeflectionAngle extends Angle {
    public DeflectionAngle(double value, Units units) {
        super(value, units);
    }

    public DeflectionAngle(Angle angle) {
        super(angle.in(degrees), degrees);
    }
}
