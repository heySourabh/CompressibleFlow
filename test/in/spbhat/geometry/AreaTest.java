/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Area.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AreaTest {

    @Test
    void areaConversionTest() {
        assertEquals("1.23e+06 cm^2", new Area(123, sq_m).to(sq_cm).toString());
        assertEquals("0.0123 m^2", new Area(123, sq_cm).to(sq_m).toString());
        assertEquals("670000 mm^2", new Area(0.67, sq_m).to(sq_mm).toString());
        assertEquals("0.000452 m^2", new Area(452, sq_mm).to(sq_m).toString());
        assertEquals("0.0148387 m^2", new Area(23, sq_inch).to(sq_m).toString());
        assertEquals("35650.1 in^2", new Area(23, sq_m).to(sq_inch).toString());
        assertEquals("338.4 in^2", new Area(2.35, sq_ft).to(sq_inch).toString());
        assertEquals("0.218322 m^2", new Area(2.35, sq_ft).to(sq_m).toString());
        assertEquals("25.2952 ft^2", new Area(2.35, sq_m).to(sq_ft).toString());
    }

}