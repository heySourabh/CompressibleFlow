/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarbonDioxideTest {

    @Test
    void gamma() {
        assertEquals(1.3, new CarbonDioxide().gamma());
    }

    @Test
    void molarMass() {
        assertEquals(44.01, new CarbonDioxide().molarMass().in(moles_gram));
    }
}