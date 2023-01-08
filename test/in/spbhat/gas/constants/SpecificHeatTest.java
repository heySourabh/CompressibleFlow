/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.constants;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.SpecificHeat.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpecificHeatTest {
    @Test
    void throw_exception_when_value_is_negative() {
        var error = assertThrows(IllegalArgumentException.class, () -> new SpecificHeat(-1, J_kgK) {
        });
        assertEquals("SpecificHeat cannot be negative.", error.getMessage());
        assertEquals("0 J/kg-K",
                new SpecificHeat(0, J_kgK) {
                }.toString());
    }

    @Test
    void conversionTest() {
        double value = 123.25;
        SpecificHeat specificHeat = new SpecificHeat(value, J_kgK) {
        };
        assertEquals(value / 4186.8, specificHeat.in(btu_lbmR));
        specificHeat = new SpecificHeat(value, btu_lbmR) {
        };
        assertEquals(value * 4186.8, specificHeat.in(J_kgK));
        specificHeat = new SpecificHeat(value, ftlbf_lbmR) {
        };
        assertEquals(value * 5.380320456, specificHeat.in(J_kgK));
    }

    @Test
    void toStringTest() {
        double value = 123.25;
        SpecificHeat specificHeat = new SpecificHeat(value, J_kgK) {
        };
        assertEquals("123.25 J/kg-K", specificHeat.toString());
        assertEquals("0.0294378 btu/lbm-°R", specificHeat.to(btu_lbmR).toString());
        assertEquals("22.9076 ft-lbf/lbm-°R", specificHeat.to(ftlbf_lbmR).toString());
        specificHeat = new SpecificHeat(value, btu_lbmR) {
        };
        assertEquals("123.25 btu/lbm-°R", specificHeat.toString());
        specificHeat = new SpecificHeat(value, ftlbf_lbmR) {
        };
        assertEquals("123.25 ft-lbf/lbm-°R", specificHeat.toString());
    }
}