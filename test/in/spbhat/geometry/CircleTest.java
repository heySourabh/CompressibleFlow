/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Area.Units.sq_cm;
import static in.spbhat.geometry.Length.Units.cm;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleTest {

    @Test
    void areaTest() {
        Length r = new Length(2, cm);
        Circle circle = new Circle(r);
        assertEquals(PI * r.in(cm) * r.in(cm), circle.area().in(sq_cm), 1e-12);
    }
}