/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.geometry.Angle;
import in.spbhat.geometry.ShockAngle;
import in.spbhat.geometry.TurnAngle;
import in.spbhat.physics.Mach;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.equations.ObliqueShock.Strength.STRONG_SHOCK;
import static in.spbhat.gas.equations.ObliqueShock.Strength.WEAK_SHOCK;
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
    void validateSupersonic() {
        var error = assertThrows(IllegalArgumentException.class,
                () -> new ObliqueShock(gas).beta(new Mach(0.5), null));
        assertEquals("Oblique shock: Upstream Mach number is subsonic (Mach 0.5).", error.getMessage());
        error = assertThrows(IllegalArgumentException.class,
                () -> new ObliqueShock(gas).theta(new Mach(0.75), null));
        assertEquals("Oblique shock: Upstream Mach number is subsonic (Mach 0.75).", error.getMessage());
    }

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

    @Test
    void betaTest_theta_out_of_range() {
        var error = assertThrows(IllegalArgumentException.class,
                () -> new ObliqueShock(gas).beta(new Mach(2), new TurnAngle(-1, degrees)));
        String msg = "Attached shock solution is not possible for turn angle -1°.\n" +
                     "The turn angle must be in range [0°, 22.9735°] for Mach 2.";
        assertEquals(msg, error.getMessage());
        error = assertThrows(IllegalArgumentException.class,
                () -> new ObliqueShock(gas).beta(new Mach(2), new TurnAngle(22.9736, degrees)));
        msg = "Attached shock solution is not possible for turn angle 22.9736°.\n" +
              "The turn angle must be in range [0°, 22.9735°] for Mach 2.";
        assertEquals(msg, error.getMessage());
    }

    @Test
    void betaTest_weak_shocks() {
        assertEquals("52.0138°",
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(30, degrees), WEAK_SHOCK).toString());
        assertEquals("52.0138°",
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(30, degrees)).toString());
        assertEquals("44.1359°",
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(25, degrees)).toString());
        assertEquals("20.1576°",
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(1, degrees)).toString());
        assertEquals(new Mach(3).machAngle().toString(),
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(0, degrees)).toString());
    }

    @Test
    void betaTest_strong_shocks() {
        assertEquals("75.2394°",
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(30, degrees), STRONG_SHOCK).toString());
        assertEquals("79.3262°",
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(25, degrees), STRONG_SHOCK).toString());
        assertEquals("89.6499°",
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(1, degrees), STRONG_SHOCK).toString());
        assertEquals(new Angle(90, degrees).toString(),
                new ObliqueShock(gas).beta(new Mach(3), new TurnAngle(0, degrees), STRONG_SHOCK).toString());
    }

    @Test
    void betaAtThetaMaxTest() {
        assertEquals("64.669°", new ObliqueShock(gas).betaAtThetaMax(new Mach(2)).toString());
    }

    @Test
    void thetaMaxTest() {
        assertEquals("22.9735°", new ObliqueShock(gas).thetaMax(new Mach(2)).toString());
    }
}