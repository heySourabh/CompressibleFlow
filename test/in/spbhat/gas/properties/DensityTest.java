/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import in.spbhat.geometry.Volume;
import in.spbhat.physics.Mass;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.gas.properties.Density.Units.pound_ft3;
import static in.spbhat.geometry.Volume.Units.cubic_ft;
import static in.spbhat.geometry.Volume.Units.cubic_m;
import static in.spbhat.physics.Mass.Units.kg;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DensityTest {
    @Test
    void convertToTest() {
        assertEquals("8673.2 kg/m^3", new Density(541.45, pound_ft3).to(kg_m3).toString());
        assertEquals("2.85296 lbm/ft^3", new Density(45.7, kg_m3).to(pound_ft3).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("10.5799 kg/m^3", new Density(10.5798789, kg_m3).toString());
    }

    @Test
    void non_positive_density_throws_exception() {
        var error = assertThrows(IllegalArgumentException.class, () -> new Density(-1.22, kg_m3));
        assertEquals("Density must be positive.", error.getMessage());
    }

    @Test
    void densityCalculation_using_Mass_and_Volume() {
        Mass mass = new Mass(1.5, kg);
        Volume volume = new Volume(0.5, cubic_m).to(cubic_ft);
        assertEquals("3 kg/m^3", new Density(mass, volume).toString());
    }
}