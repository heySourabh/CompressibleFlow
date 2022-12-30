/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.Units.radians;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShockAngleTest {
    @Test
    void constructorTests() {
        Angle angle = new Angle(20, degrees);
        assertEquals(angle.toString(), new ShockAngle(angle).toString());
        assertEquals(angle.toString(), new ShockAngle(20, degrees).toString());

        angle = new Angle(PI / 4, radians);
        assertEquals(angle.to(degrees).toString(), new ShockAngle(angle).toString());
        assertEquals(angle.toString(), new ShockAngle(PI / 4, radians).toString());
    }
}