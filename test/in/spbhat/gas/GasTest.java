/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;
import in.spbhat.gas.properties.Density;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import in.spbhat.physics.Speed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;
import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.gas.properties.Pressure.Units.Pa;
import static in.spbhat.gas.properties.Temperature.Units.Kelvin;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GasTest {
    Gas gas;

    @BeforeEach
    void initGas() {
        gas = new Gas() {
            @Override
            public double gamma() {
                return 1.6;
            }

            @Override
            public MolarMass molarMass() {
                return new MolarMass(12.45, moles_gram);
            }
        };
    }

    @Test
    void R() {
        double expectedRValue = 8314.462618 / gas.molarMass().in(moles_gram);
        assertEquals(expectedRValue, gas.R().in(J_kgK));
    }

    @Test
    void cp() {
        double expectedCp = gas.R().in(J_kgK) / (gas.gamma() - 1) * gas.gamma();
        assertEquals(expectedCp, gas.cp().in(J_kgK));
    }

    @Test
    void cv() {
        double expectedCv = gas.R().in(J_kgK) / (gas.gamma() - 1);
        assertEquals(expectedCv, gas.cv().in(J_kgK));
    }

    @Test
    void soundSpeed_using_temperature() {
        Temperature temperature = new Temperature(300, Kelvin);
        double expectedSpeed = Math.sqrt(gas.gamma() * gas.R().in(J_kgK) * temperature.in(Kelvin));
        assertEquals(expectedSpeed, gas.soundSpeed(temperature).in(Speed.Units.m_s));
    }

    @Test
    void soundSpeed_using_pressure_and_density() {
        Pressure pressure = new Pressure(101235, Pa);
        Density density = new Density(1.225, kg_m3);
        double expectedSpeed = Math.sqrt(gas.gamma() * pressure.in(Pa) / density.in(kg_m3));
        assertEquals(expectedSpeed, gas.soundSpeed(pressure, density).in(Speed.Units.m_s));
    }
}
