/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.constants;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MolarMassTest {

    @Test
    void throw_exception_if_MolarMass_is_negative() {
        var error = assertThrows(IllegalArgumentException.class, () -> new MolarMass(-1, moles_gram));
        assertEquals("Molar mass cannot be negative.", error.getMessage());
        assertEquals("0 moles/gram", new MolarMass(0, moles_gram).toString());
    }

    @Test
    void in() {
        assertEquals(365.473, new MolarMass(365.473, moles_gram).in(moles_gram));
    }

    @Test
    void toStringTest() {
        MolarMass molarMass = new MolarMass(28.9, moles_gram);
        assertEquals("28.9 moles/gram", molarMass.toString());
    }
}