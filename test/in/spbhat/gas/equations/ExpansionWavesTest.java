/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.physics.Mach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpansionWavesTest {
    Gas gas = new Gas() {
        @Override
        public double gamma() {
            return 1.4;
        }

        @Override
        public MolarMass molarMass() {
            return null;
        }
    };

    @Test
    void nuTest_throws_exception_if_not_supersonic() {
        var error = assertThrows(IllegalArgumentException.class,
                () -> new ExpansionWaves(gas).nu(new Mach(0.999)));
        assertEquals("Expansion waves: Mach number is subsonic (Mach 0.999).", error.getMessage());
    }

    @Test
    void nuTest() {
        assertEquals("26.3798°", new ExpansionWaves(gas).nu(new Mach(2)).toString());
        assertEquals("76.9202°", new ExpansionWaves(gas).nu(new Mach(5)).toString());
    }
}