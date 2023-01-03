/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.geometry.PrandtlMeyerAngle;
import in.spbhat.physics.Mach;
import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Angle.Units.degrees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals("0°", new ExpansionWaves(gas).nu(new Mach(1)).toString());
        assertEquals("26.3798°", new ExpansionWaves(gas).nu(new Mach(2)).toString());
        assertEquals("76.9202°", new ExpansionWaves(gas).nu(new Mach(5)).toString());
        assertEquals("127.59°", new ExpansionWaves(gas).nu(new Mach(100)).toString());
    }

    @Test
    void MTest() {
        assertEquals("Mach 1",
                new ExpansionWaves(gas).M(new PrandtlMeyerAngle(0, degrees)).toString());
        assertEquals("Mach 2.13391",
                new ExpansionWaves(gas).M(new PrandtlMeyerAngle(30, degrees)).toString());
    }
}