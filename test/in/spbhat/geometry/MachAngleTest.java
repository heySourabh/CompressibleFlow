/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.Units.radians;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MachAngleTest {
    @Test
    void constructorTests() {
        assertEquals("10°", new MachAngle(10, degrees).toString());
        assertEquals("%.6g".formatted(1.25 / PI) + "π rad",
                new MachAngle(1.25, radians).toString());

        assertEquals("5°", new MachAngle(new Angle(5, degrees)).toString());
        assertEquals("45°", new MachAngle(new Angle(PI / 4, radians)).toString());
    }
}