/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Area.Units.sq_inch;
import static in.spbhat.geometry.Length.Units.inches;
import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    @Test
    void areaTest() {
        Square square = new Square(new Length(9.5, inches));
        assertEquals(9.5 * 9.5, square.area().in(sq_inch));
    }
}