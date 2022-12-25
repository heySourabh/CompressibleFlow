/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.gas.properties.Density;
import in.spbhat.geometry.Volume;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.geometry.Volume.Units.cubic_m;
import static in.spbhat.physics.Mass.Units.kg;
import static in.spbhat.physics.Mass.Units.pound;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MassTest {

    @Test
    void throw_exception_if_mass_is_negative() {
        var error = assertThrows(IllegalArgumentException.class, () -> new Mass(-1, kg));
        assertEquals("Mass cannot be negative.", error.getMessage());
        assertEquals("0 kg", new Mass(0, kg).toString());
    }

    @Test
    void in() {
        // kg -> kg
        assertEquals(437.920, new Mass(437.920, kg).in(kg));
        // kg -> pound
        assertEquals(60.030330757, new Mass(27.2293, kg).in(pound), 1e-8);
        // pound -> pound
        assertEquals(158.859, new Mass(158.859, pound).in(pound));
        // pound -> kg
        assertEquals(200.03332799, new Mass(440.998, pound).in(kg), 1e-8);
    }

    @Test
    void to() {
        // kg -> kg
        assertEquals("437.92 kg", new Mass(437.920, kg).to(kg).toString());
        // kg -> pound
        assertEquals("60.0303 lbm", new Mass(27.2293, kg).to(pound).toString());
        // pound -> pound
        assertEquals("158.859 lbm", new Mass(158.859, pound).to(pound).toString());
        // pound -> kg
        assertEquals("200.033 kg", new Mass(440.998, pound).to(kg).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("332.897 kg", new Mass(332.897, kg).toString());
        assertEquals("436.388 lbm", new Mass(436.388, pound).toString());
        assertEquals("3 kg", new Mass(
                new Density(1.5, kg_m3),
                new Volume(2, cubic_m)).toString());
    }
}