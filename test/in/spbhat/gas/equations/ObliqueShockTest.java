/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.geometry.ShockAngle;
import in.spbhat.physics.Mach;
import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Angle.Units.degrees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ObliqueShockTest {
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
    void thetaTest_weak_shocks() {
        assertEquals("5.74643°", new ObliqueShock(gas)
                .theta(new Mach(2), new ShockAngle(35, degrees))
                .toString());
        assertEquals("0°", new ObliqueShock(gas)
                .theta(new Mach(2.5), new ShockAngle(new Mach(2.5).machAngle()))
                .toString());
    }

    @Test
    void thetaTest_strong_shocks() {
        assertEquals("14.8074°", new ObliqueShock(gas)
                .theta(new Mach(2), new ShockAngle(80, degrees))
                .toString());
        assertEquals("0°", new ObliqueShock(gas)
                .theta(new Mach(1.5), new ShockAngle(90, degrees))
                .toString());
    }

    @Test
    void thetaTest_beta_out_of_range() {
        var error = assertThrows(IllegalArgumentException.class,
                () -> new ObliqueShock(gas).theta(new Mach(2), new ShockAngle(25, degrees)));
        assertEquals("The value of shock angle is out of range [30°, 90°] for Mach 2.", error.getMessage());
        error = assertThrows(IllegalArgumentException.class,
                () -> new ObliqueShock(gas).theta(new Mach(1.5), new ShockAngle(30, degrees)));
        assertEquals("The value of shock angle is out of range [41.8103°, 90°] for Mach 1.5.", error.getMessage());
        error = assertThrows(IllegalArgumentException.class,
                () -> new ObliqueShock(gas).theta(new Mach(1.5), new ShockAngle(91, degrees)));
        assertEquals("The value of shock angle is out of range [41.8103°, 90°] for Mach 1.5.", error.getMessage());
    }
}