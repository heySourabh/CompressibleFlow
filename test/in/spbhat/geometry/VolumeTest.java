/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Volume.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VolumeTest {

    @Test
    void convertToTest() {
        assertEquals("1.23e+08 cm^3", new Volume(123, cubic_m).to(cubic_cm).toString());
        assertEquals("0.000123 m^3", new Volume(123, cubic_cm).to(cubic_m).toString());
        assertEquals("6.7e+08 mm^3", new Volume(0.67, cubic_m).to(cubic_mm).toString());
        assertEquals("4.52e-07 m^3", new Volume(452, cubic_mm).to(cubic_m).toString());
        assertEquals("0.000376902 m^3", new Volume(23, cubic_inch).to(cubic_m).toString());
        assertEquals("1.40355e+06 in^3", new Volume(23, cubic_m).to(cubic_inch).toString());
        assertEquals("4060.8 in^3", new Volume(2.35, cubic_ft).to(cubic_inch).toString());
        assertEquals("0.0665446 m^3", new Volume(2.35, cubic_ft).to(cubic_m).toString());
        assertEquals("82.9895 ft^3", new Volume(2.35, cubic_m).to(cubic_ft).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("5.8 m^3", new Volume(5.8, cubic_m).toString());
        assertEquals("5.8 mm^3", new Volume(5.8, cubic_mm).toString());
        assertEquals("5.8 cm^3", new Volume(5.8, cubic_cm).toString());
        assertEquals("5.8 in^3", new Volume(5.8, cubic_inch).toString());
        assertEquals("5.8 ft^3", new Volume(5.8, cubic_ft).toString());
    }
}