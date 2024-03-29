/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.geometry.Angle.Units.radians;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class AngleTest {
    @Test
    void test_that_angle_is_constrained_within_negative_to_positive_180_degrees() {
        // +0 to +180
        assertEquals(0, new Angle(0, degrees).in(degrees));
        assertEquals(1, new Angle(1, degrees).in(degrees));
        assertEquals(45, new Angle(45, degrees).in(degrees));
        assertEquals(89, new Angle(89, degrees).in(degrees));
        assertEquals(90, new Angle(90, degrees).in(degrees));
        assertEquals(91, new Angle(91, degrees).in(degrees));
        assertEquals(135, new Angle(135, degrees).in(degrees));
        assertEquals(179, new Angle(179, degrees).in(degrees));
        assertEquals(180, new Angle(180, degrees).in(degrees));

        // -0 to -180
        assertEquals(0, new Angle(-0, degrees).in(degrees));
        assertEquals(-1, new Angle(-1, degrees).in(degrees));
        assertEquals(-45, new Angle(-45, degrees).in(degrees));
        assertEquals(-89, new Angle(-89, degrees).in(degrees));
        assertEquals(-90, new Angle(-90, degrees).in(degrees));
        assertEquals(-91, new Angle(-91, degrees).in(degrees));
        assertEquals(-135, new Angle(-135, degrees).in(degrees));
        assertEquals(-179, new Angle(-179, degrees).in(degrees));
        assertEquals(-180, new Angle(-180, degrees).in(degrees));

        // > +360 n
        assertEquals(10, new Angle(360 + 10, degrees).in(degrees));
        assertEquals(105, new Angle(360 + 105, degrees).in(degrees));
        assertEquals(-10, new Angle(360 - 10, degrees).in(degrees));
        assertEquals(-105, new Angle(360 - 105, degrees).in(degrees));

        assertEquals(10, new Angle(360 * 2 + 10, degrees).in(degrees));
        assertEquals(105, new Angle(360 * 4 + 105, degrees).in(degrees));
        assertEquals(-10, new Angle(360 * 8 - 10, degrees).in(degrees));
        assertEquals(-105, new Angle(360 * 7 - 105, degrees).in(degrees));

        // > -360 n
        assertEquals(10, new Angle(-360 + 10, degrees).in(degrees));
        assertEquals(105, new Angle(-360 + 105, degrees).in(degrees));
        assertEquals(-10, new Angle(-360 - 10, degrees).in(degrees));
        assertEquals(-105, new Angle(-360 - 105, degrees).in(degrees));

        assertEquals(10, new Angle(-360 * 5 + 10, degrees).in(degrees));
        assertEquals(105, new Angle(-360 * 2 + 105, degrees).in(degrees));
        assertEquals(-10, new Angle(-360 * 6 - 10, degrees).in(degrees));
        assertEquals(-105, new Angle(-360 * 3 - 105, degrees).in(degrees));

        // +180 to +360
        assertEquals(180, new Angle(180, degrees).in(degrees));
        assertEquals(-179, new Angle(180 + 1, degrees).in(degrees));
        assertEquals(-135, new Angle(180 + 45, degrees).in(degrees));
        assertEquals(-91, new Angle(180 + 89, degrees).in(degrees));
        assertEquals(-90, new Angle(180 + 90, degrees).in(degrees));
        assertEquals(-89, new Angle(180 + 91, degrees).in(degrees));
        assertEquals(-45, new Angle(180 + 135, degrees).in(degrees));
        assertEquals(-1, new Angle(180 + 179, degrees).in(degrees));
        assertEquals(0, new Angle(180 + 180, degrees).in(degrees));

        // -180 to -360
        assertEquals(-180, new Angle(-180, degrees).in(degrees));
        assertEquals(179, new Angle(-180 - 1, degrees).in(degrees));
        assertEquals(135, new Angle(-180 - 45, degrees).in(degrees));
        assertEquals(91, new Angle(-180 - 89, degrees).in(degrees));
        assertEquals(90, new Angle(-180 - 90, degrees).in(degrees));
        assertEquals(89, new Angle(-180 - 91, degrees).in(degrees));
        assertEquals(45, new Angle(-180 - 135, degrees).in(degrees));
        assertEquals(1, new Angle(-180 - 179, degrees).in(degrees));
        assertEquals(-0.0, new Angle(-180 - 180, degrees).in(degrees));
    }

    @Test
    void test_that_angle_is_constrained_within_negative_to_positive_PI_radians() {
        // +0 to +PI
        assertEquals(0, new Angle(0, radians).in(radians));
        assertEquals(PI / 4, new Angle(PI / 4, radians).in(radians));
        assertEquals(PI / 2, new Angle(PI / 2, radians).in(radians));
        assertEquals(3 * PI / 4, new Angle(3 * PI / 4, radians).in(radians));
        assertEquals(PI, new Angle(PI, radians).in(radians));

        // -0 to -PI
        assertEquals(0, new Angle(-0, radians).in(radians));
        assertEquals(-PI / 4, new Angle(-PI / 4, radians).in(radians));
        assertEquals(-PI / 2, new Angle(-PI / 2, radians).in(radians));
        assertEquals(-3 * PI / 4, new Angle(-3 * PI / 4, radians).in(radians));
        assertEquals(-PI, new Angle(-PI, radians).in(radians));

        // > +2 PI n
        assertEquals(1, new Angle(2 * PI + 1, radians).in(radians));
        assertEquals(2, new Angle(2 * PI + 2, radians).in(radians));
        assertEquals(-1, new Angle(2 * PI - 1, radians).in(radians));
        assertEquals(-2, new Angle(2 * PI - 2, radians).in(radians));

        assertEquals(1, new Angle(2 * PI * 2 + 1, radians).in(radians));
        assertEquals(2, new Angle(2 * PI * 4 + 2, radians).in(radians));
        assertEquals(-1, new Angle(2 * PI * 8 - 1, radians).in(radians));
        assertEquals(-2, new Angle(2 * PI * 7 - 2, radians).in(radians));

        // > -2 PI n
        assertEquals(1, new Angle(-2 * PI + 1, radians).in(radians));
        assertEquals(2, new Angle(-2 * PI + 2, radians).in(radians));
        assertEquals(-1, new Angle(-2 * PI - 1, radians).in(radians));
        assertEquals(-2, new Angle(-2 * PI - 2, radians).in(radians));

        assertEquals(1, new Angle(-2 * PI * 5 + 1, radians).in(radians));
        assertEquals(2, new Angle(-2 * PI * 2 + 2, radians).in(radians));
        assertEquals(-1, new Angle(-2 * PI * 6 - 1, radians).in(radians));
        assertEquals(-2, new Angle(-2 * PI * 3 - 2, radians).in(radians));

        // +PI to +2 PI
        assertEquals(PI, new Angle(PI, radians).in(radians));
        assertEquals(-3 * PI / 4, new Angle(PI + PI / 4, radians).in(radians));
        assertEquals(-PI / 2, new Angle(PI + PI / 2, radians).in(radians));
        assertEquals(-PI / 4, new Angle(PI + 3 * PI / 4, radians).in(radians));
        assertEquals(0, new Angle(PI + PI, radians).in(radians));

        // -PI to -2 PI
        assertEquals(-PI, new Angle(-PI, radians).in(radians));
        assertEquals(3 * PI / 4, new Angle(-PI - PI / 4, radians).in(radians));
        assertEquals(PI / 2, new Angle(-PI - PI / 2, radians).in(radians));
        assertEquals(PI / 4, new Angle(-PI - 3 * PI / 4, radians).in(radians));
        assertEquals(-0.0, new Angle(-PI - PI, radians).in(radians));
    }

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
    void tanTest() {
        assertEquals(1, Angle.tan(new Angle(45, degrees)), 1e-12);
        assertEquals(1, Angle.tan(new Angle(PI / 4, radians)), 1e-12);
    }

    @Test
    void arcSinTest() {
        assertEquals(30, Angle.arcSin(0.5).in(degrees), 1e-12);
    }

    @Test
    void arcCosTest() {
        assertEquals(60, Angle.arcCos(0.5).in(degrees), 1e-12);
    }

    @Test
    void arcTanTest() {
        assertEquals(45, Angle.arcTan(1).in(degrees));
        assertEquals(PI / 4, Angle.arcTan(1).in(radians));
    }

    @Test
    void timesTest() {
        assertEquals(20, new Angle(10, degrees).times(2).in(degrees));
        assertEquals(30, new Angle(10, degrees).times(3).in(degrees));
        assertEquals(120, new Angle(10, degrees).times(12).in(degrees));
        assertEquals(180, new Angle(10, degrees).times(18).in(degrees));
        assertEquals(-170, new Angle(10, degrees).times(19).in(degrees));
        assertEquals(0, new Angle(10, degrees).times(36).in(degrees));
        assertEquals(10, new Angle(10, degrees).times(37).in(degrees));

        assertEquals(90, new Angle(PI / 4, radians).times(2).in(degrees));
        assertEquals(PI / 2, new Angle(PI / 4, radians).times(2).in(radians));
        assertEquals(PI / 4, new Angle(PI / 8, radians).times(18).in(radians));
    }

    @Test
    void greaterThanTest() {
        assertTrue(new Angle(45, degrees).isGreaterThan(new Angle(44, degrees)));
        assertFalse(new Angle(44, degrees).isGreaterThan(new Angle(44, degrees)));
        assertFalse(new Angle(43, degrees).isGreaterThan(new Angle(44, degrees)));
        assertTrue(new Angle(46, degrees).isGreaterThan(new Angle(PI / 4, radians)));

        assertTrue(new Angle(-44, degrees).isGreaterThan(new Angle(-45, degrees)));
        assertFalse(new Angle(-44, degrees).isGreaterThan(new Angle(-44, degrees)));
        assertFalse(new Angle(-44, degrees).isGreaterThan(new Angle(-43, degrees)));
        assertTrue(new Angle(-PI / 4, radians).isGreaterThan(new Angle(-46, degrees)));
    }

    @Test
    void lessThanTest() {
        assertTrue(new Angle(40, degrees).isLessThan(new Angle(45, degrees)));
        assertFalse(new Angle(45, degrees).isLessThan(new Angle(45, degrees)));
        assertFalse(new Angle(46, degrees).isLessThan(new Angle(45, degrees)));
        assertTrue(new Angle(45, degrees).isLessThan(new Angle(PI / 2, radians)));

        assertTrue(new Angle(-45, degrees).isLessThan(new Angle(-40, degrees)));
        assertFalse(new Angle(-45, degrees).isLessThan(new Angle(-45, degrees)));
        assertFalse(new Angle(-45, degrees).isLessThan(new Angle(-46, degrees)));
        assertTrue(new Angle(-PI / 2, radians).isLessThan(new Angle(-45, degrees)));
    }

    @Test
    void toStringTest() {
        assertEquals("45°", new Angle(45, degrees).toString());
        assertEquals("-0.5π rad", new Angle(1.5 * PI, radians).toString());
    }
}
