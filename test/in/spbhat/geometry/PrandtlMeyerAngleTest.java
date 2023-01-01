/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Angle.Units.degrees;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrandtlMeyerAngleTest {
    @Test
    void constructorTests() {
        assertEquals("10°", new PrandtlMeyerAngle(10, degrees).toString());
        assertEquals("5°", new PrandtlMeyerAngle(new Angle(5, degrees)).toString());
    }
}