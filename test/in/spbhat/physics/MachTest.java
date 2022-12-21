/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.geometry.Angle;
import org.junit.jupiter.api.Test;

import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.physics.Mach.MachRegime.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MachTest {

    @Test
    void machAngle() {
        assertEquals("90°", new Mach(1).machAngle().toString());
        assertEquals("30°", new Mach(2).machAngle().toString());

        IllegalStateException error = assertThrows(IllegalStateException.class,
                () -> new Mach(0.15).machAngle());
        assertEquals("Cannot compute Mach angle for 'subsonic' flow: Mach 0.15", error.getMessage());
    }

    @Test
    void machRegime() {
        assertEquals(Subsonic, new Mach(0.000001).machRegime());
        assertEquals(Subsonic, new Mach(0.5).machRegime());
        assertEquals(Subsonic, new Mach(0.999999999).machRegime());
        assertEquals(Subsonic, new Mach(-0.000001).machRegime());
        assertEquals(Subsonic, new Mach(-0.5).machRegime());
        assertEquals(Subsonic, new Mach(-0.999999999).machRegime());

        assertEquals(Sonic, new Mach(1).machRegime());
        assertEquals(Sonic, new Mach(-1).machRegime());

        assertEquals(Supersonic, new Mach(1.000001).machRegime());
        assertEquals(Supersonic, new Mach(5.8).machRegime());
        assertEquals(Supersonic, new Mach(-1.000001).machRegime());
        assertEquals(Supersonic, new Mach(-5.8).machRegime());
    }

    @Test
    void toStringTest() {
        assertEquals("Mach 2", new Mach(2).toString());
        assertEquals("Mach 2", new Mach(new Angle(30, degrees)).toString());
        assertEquals("Mach 2", new Mach(-2).toString());
        assertEquals("Mach 2", new Mach(new Angle(-30, degrees)).toString());
    }
}