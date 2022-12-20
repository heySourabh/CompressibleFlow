/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import in.spbhat.gas.properties.Pressure.Units;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.properties.Pressure.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PressureTest {

    @Test
    void in() {
        double value = 1234.987;
        for (Units unit : Units.values()) {
            Pressure p = new Pressure(value, unit);
            assertEquals(value, p.in(unit));
        }

        assertEquals(value / 1000, new Pressure(value, Pa).in(kPa));
        assertEquals(value * 1000, new Pressure(value, kPa).in(Pa));
        assertEquals(value / 1000000, new Pressure(value, Pa).in(MPa));
        assertEquals(value * 1000000, new Pressure(value, MPa).in(Pa));
        assertEquals(value / 6894.7572932, new Pressure(value, Pa).in(psi), 1e-8);
        assertEquals(value * 6894.7572932, new Pressure(value, psi).in(Pa), 1e-4);
        assertEquals(value * 6894.7572932 / 1e5, new Pressure(value, psi).in(bar), 1e-8);
        assertEquals(value * 6894.7572932 / 101325, new Pressure(value, psi).in(atm), 1e-8);
        assertEquals(value / 1e5, new Pressure(value, Pa).in(bar));
        assertEquals(value * 1e5, new Pressure(value, bar).in(Pa));
        assertEquals(value * 1e5 / 6894.7572932, new Pressure(value, bar).in(psi), 1e-6);
        assertEquals(value * 1e5 / 101325, new Pressure(value, bar).in(atm));
        assertEquals(value / 101325, new Pressure(value, Pa).in(atm));
        assertEquals(value * 101325, new Pressure(value, atm).in(Pa));
        assertEquals(value * 101325 / 6894.7572932, new Pressure(value, atm).in(psi), 1e-6);
    }

    @Test
    void to() {
        double value = 875.24;
        assertEquals(new Pressure(value / 1000, kPa).toString(),
                new Pressure(value, Pa).to(kPa).toString());
        assertEquals(new Pressure(value * 1000, Pa).toString(),
                new Pressure(value, kPa).to(Pa).toString());
        assertEquals(new Pressure(value / 1000000, MPa).toString(),
                new Pressure(value, Pa).to(MPa).toString());
        assertEquals(new Pressure(value * 1000000, Pa).toString(),
                new Pressure(value, MPa).to(Pa).toString());
        assertEquals(new Pressure(value / 6894.7572932, psi).toString(),
                new Pressure(value, Pa).to(psi).toString());
        assertEquals(new Pressure(value * 6894.7572932, Pa).toString(),
                new Pressure(value, psi).to(Pa).toString());
        assertEquals(new Pressure(value * 6894.7572932 / 1e5, bar).toString(),
                new Pressure(value, psi).to(bar).toString());
        assertEquals(new Pressure(value * 6894.7572932 / 101325, atm).toString(),
                new Pressure(value, psi).to(atm).toString());
        assertEquals(new Pressure(value / 1e5, bar).toString(),
                new Pressure(value, Pa).to(bar).toString());
        assertEquals(new Pressure(value * 1e5, Pa).toString(),
                new Pressure(value, bar).to(Pa).toString());
        assertEquals(new Pressure(value * 1e5 / 6894.7572932, psi).toString(),
                new Pressure(value, bar).to(psi).toString());
        assertEquals(new Pressure(value * 1e5 / 101325, atm).toString(),
                new Pressure(value, bar).to(atm).toString());
        assertEquals(new Pressure(value / 101325, atm).toString(),
                new Pressure(value, Pa).to(atm).toString());
        assertEquals(new Pressure(value * 101325, Pa).toString(),
                new Pressure(value, atm).to(Pa).toString());
        assertEquals(new Pressure(value * 101325 / 6894.7572932, psi).toString(),
                new Pressure(value, atm).to(psi).toString());
    }

    @Test
    void times() {
        assertEquals(235.87 * 6894.7572932 * 3,
                new Pressure(235.87, psi).times(3).in(Pa), 1e-4);
    }

    @Test
    void divide() {
        assertEquals(235.87 * 6894.7572932 / 3,
                new Pressure(235.87, psi).divide(3).in(Pa), 1e-4);
    }

    @Test
    void toStringTest() {
        assertEquals("5 Pa", new Pressure(5, Pa).toString());
        assertEquals("1 kPa", new Pressure(1000, Pa).toString());
        assertEquals("5 kPa", new Pressure(5, kPa).toString());
        assertEquals("1 MPa", new Pressure(1000000, Pa).toString());
        assertEquals("5 MPa", new Pressure(5, MPa).toString());
        assertEquals("5 psi", new Pressure(5, psi).toString());
        assertEquals("5 bar", new Pressure(5, bar).toString());
        assertEquals("5 atm", new Pressure(5, atm).toString());
    }
}