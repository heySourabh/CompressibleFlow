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

class AngleTest {

    @Test
    void convertInTest() {
        assertEquals(PI / 3, new Angle(60, degrees).in(radians));
        assertEquals(60, new Angle(PI / 3.0, radians).in(degrees), 1e-12);
    }

    @Test
    void convertToTest() {
        Angle angle1 = new Angle(90, degrees).to(radians);
        assertEquals("0.5π rad", angle1.toString());

        Angle angle2 = new Angle(PI, radians).to(degrees);
        assertEquals("180°", angle2.toString());

        Angle angle3 = new Angle(60, degrees).to(radians);
        assertEquals("0.333333π rad", angle3.toString());

        Angle angle4 = new Angle(PI / 3.0, radians).to(degrees);
        assertEquals("60°", angle4.toString());
    }

    @Test
    void sinTest() {
        assertEquals(0.5, Angle.sin(new Angle(30, degrees)), 1e-12);
    }

    @Test
    void cosTest() {
        assertEquals(0.5, Angle.cos(new Angle(60, degrees)), 1e-12);
        assertEquals(0.5, Angle.cos(new Angle(PI / 3, radians)), 1e-12);
    }

    @Test
    void asinTest() {
        assertEquals(30, Angle.asin(0.5).in(degrees), 1e-12);
    }

    @Test
    void acosTest() {
        assertEquals(60, Angle.acos(0.5).in(degrees), 1e-12);
    }

    @Test
    void toStringTest() {
        Angle angle1 = new Angle(45, degrees);
        assertEquals("45°", angle1.toString());
        Angle angle2 = new Angle(3.0 / 2.0 * PI, radians);
        assertEquals("1.5π rad", angle2.toString());
    }
}
