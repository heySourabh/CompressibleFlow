/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XenonTest {

    @Test
    void gamma() {
        assertEquals(1.667, new Xenon().gamma());
    }

    @Test
    void molarMass() {
        assertEquals(131.3, new Xenon().molarMass().in(moles_gram));
    }
}