/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MolarMassTest {

    @Test
    void toStringTest() {
        MolarMass molarMass = new MolarMass(28.9, MolarMass.Units.moles_gram);
        assertEquals("28.9 moles/gram", molarMass.toString());
    }
}