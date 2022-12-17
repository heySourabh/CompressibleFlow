/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HydrogenTest {

    @Test
    void gamma() {
        assertEquals(1.407, new Hydrogen().gamma());
    }

    @Test
    void molarMass() {
        assertEquals(2.016, new Hydrogen().molarMass().in(moles_gram));
    }
}