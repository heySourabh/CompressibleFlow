/*
 * Copyright (c) 2023.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Air;
import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.gas.constants.R;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import in.spbhat.geometry.PrandtlMeyerAngle;
import in.spbhat.physics.Mach;
import in.spbhat.physics.Speed;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.constants.SpecificHeat.Units.ftlbf_lbmR;
import static in.spbhat.gas.properties.Pressure.Units.Pa;
import static in.spbhat.gas.properties.Pressure.Units.psi;
import static in.spbhat.gas.properties.Temperature.Units.Kelvin;
import static in.spbhat.gas.properties.Temperature.Units.Rankine;
import static in.spbhat.geometry.Angle.Units.degrees;
import static in.spbhat.physics.Speed.Units.ft_s;
import static in.spbhat.physics.Speed.Units.m_s;
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

    @Test
    void V2Test() {
        Pressure p1 = new Pressure(200, Pa);
        Pressure p2 = new Pressure(100, Pa);
        Speed V1 = new Speed(0, m_s);
        Temperature T1 = new Temperature(303, Kelvin);
        assertEquals("164.475 m/s", new ExpansionWaves(new Air()).V2(p1, p2, T1, V1).toString());

        Gas gas = new Gas() {
            @Override
            public double gamma() {
                return 1.4;
            }

            @Override
            public R R() {
                return new R(20, ftlbf_lbmR);
            }

            @Override
            public MolarMass molarMass() {
                return null;
            }
        };
        p1 = new Pressure(14.7, psi);
        p2 = new Pressure(0, psi);
        V1 = new Speed(2673.91, ft_s);
        T1 = new Temperature(912.692, Rankine);
        assertEquals("7207.73 ft/s", new ExpansionWaves(gas).V2(p1, p2, T1, V1).toString());
    }
}