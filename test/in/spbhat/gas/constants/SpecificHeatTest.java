/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.constants;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;
import static in.spbhat.gas.constants.SpecificHeat.Units.btu_lbmR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecificHeatTest {
    @Test
    void conversionTest() {
        double value = 123.25;
        SpecificHeat specificHeat = new SpecificHeat(value, J_kgK) {
        };
        assertEquals(value / 4.1868, specificHeat.in(btu_lbmR));
        specificHeat = new SpecificHeat(value, btu_lbmR) {
        };
        assertEquals(value * 4.1868, specificHeat.in(J_kgK));
    }

    @Test
    void toStringTest() {
        double value = 123.25;
        SpecificHeat specificHeat = new SpecificHeat(value, J_kgK) {
        };
        assertEquals("123.25 J/kg-K", specificHeat.toString());
        specificHeat = new SpecificHeat(value, btu_lbmR) {
        };
        assertEquals("123.25 btu/lbm-°R", specificHeat.toString());
    }
}