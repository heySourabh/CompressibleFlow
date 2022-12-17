/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AirTest {
    @Test
    void gamma() {
        assertEquals(1.4, new Air().gamma());
    }

    @Test
    void R() {
        assertEquals(287, new Air().R().in(J_kgK));
    }

    @Test
    void molarMass() {
        assertEquals(28.96, new Air().molarMass().in(moles_gram));
    }
}