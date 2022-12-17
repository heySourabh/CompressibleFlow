/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Freon12Test {

    @Test
    void gamma() {
        assertEquals(1.139, new Freon12().gamma());
    }

    @Test
    void molarMass() {
        assertEquals(120.9, new Freon12().molarMass().in(moles_gram));
    }
}