/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Length.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LengthTest {
    @Test
    void convertToTest() {
        assertEquals("196.85 inches", new Length(5, m).to(inches).toString());
        assertEquals("16.4042 ft", new Length(5, m).to(ft).toString());
        assertEquals("1.524 m", new Length(5, ft).to(m).toString());
        assertEquals("152.4 cm", new Length(5, ft).to(cm).toString());
        assertEquals("0.05 m", new Length(5, cm).to(m).toString());
        assertEquals("1230 m", new Length(1.23, km).to(m).toString());
        assertEquals("1.023e+06 cm", new Length(10.23, km).to(cm).toString());
        assertEquals("0.0031181 km", new Length(10.23, ft).to(km).toString());
        assertEquals("4035.43 ft", new Length(1.23, km).to(ft).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("5 m", new Length(5, m).toString());
        assertEquals("5 km", new Length(5, km).toString());
        assertEquals("5 cm", new Length(5, cm).toString());
        assertEquals("5 ft", new Length(5, ft).toString());
        assertEquals("5 inches", new Length(5, inches).toString());
    }
}