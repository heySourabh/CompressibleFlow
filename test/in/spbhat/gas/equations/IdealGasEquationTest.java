/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.gas.constants.R;
import in.spbhat.gas.properties.Density;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;
import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.gas.properties.Density.Units.pound_ft3;
import static in.spbhat.gas.properties.Pressure.Units.*;
import static in.spbhat.gas.properties.Temperature.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IdealGasEquationTest {

    Pressure pressure = new Pressure(101325, Pa);
    Density density = new Density(1.125, kg_m3);
    Temperature temperature = new Temperature(30, C);

    Gas fictitiousGas = new Gas() {
        @Override
        public R R() {
            return new R(pressure.in(Pa) / temperature.in(K) / density.in(kg_m3), J_kgK);
        }

        @Override
        public double gamma() {
            return 0.0;
        }

        @Override
        public MolarMass molarMass() {
            return null;
        }
    };

    @Test
    void pressure() {
        IdealGasEquation eos = new IdealGasEquation(fictitiousGas);
        assertEquals(pressure.in(Pa), eos.pressure(density, temperature).in(Pa));
        assertEquals(pressure.in(kPa), eos.pressure(density, temperature).in(kPa));
        assertEquals(pressure.in(MPa), eos.pressure(density, temperature).in(MPa));
        assertEquals(pressure.in(bar), eos.pressure(density, temperature).in(bar));
        assertEquals(pressure.in(atm), eos.pressure(density, temperature).in(atm));
        assertEquals(pressure.in(psi), eos.pressure(density, temperature).in(psi));
    }

    @Test
    void density() {
        IdealGasEquation eos = new IdealGasEquation(fictitiousGas);
        assertEquals(density.in(kg_m3), eos.density(pressure, temperature).in(kg_m3), 1e-15);
        assertEquals(density.in(pound_ft3), eos.density(pressure, temperature).in(pound_ft3), 1e-15);
    }

    @Test
    void temperature() {
        IdealGasEquation eos = new IdealGasEquation(fictitiousGas);
        assertEquals(temperature.in(K), eos.temperature(pressure, density).in(K));
        assertEquals(temperature.in(C), eos.temperature(pressure, density).in(C));
        assertEquals(temperature.in(F), eos.temperature(pressure, density).in(F));
        assertEquals(temperature.in(R), eos.temperature(pressure, density).in(R));
    }
}