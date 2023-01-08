/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import in.spbhat.physics.Mach;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.properties.Pressure.Units.*;
import static in.spbhat.gas.properties.Temperature.Units.Celsius;
import static in.spbhat.gas.properties.Temperature.Units.Kelvin;
import static in.spbhat.physics.Mach.MachRegime.*;
import static in.spbhat.util.Formatter.doubleToString;
import static java.lang.Math.pow;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsentropicFlowTest {

    double gamma = 1.4;
    IsentropicFlow isentropicFlow = new IsentropicFlow(new Gas() {
        @Override
        public double gamma() {
            return gamma;
        }

        @Override
        public MolarMass molarMass() {
            return null;
        }
    });

    @Test
    void p2_by_p1() {
        assertEquals(pow(2.1, gamma / (gamma - 1)), isentropicFlow.p2_by_p1(2.1));
        assertEquals(pow(0.1, gamma / (gamma - 1)), isentropicFlow.p2_by_p1(0.1));
        assertEquals(1, isentropicFlow.p2_by_p1(1));
    }

    @Test
    void p2() {
        assertEquals("191.801 kPa", isentropicFlow.p2(new Pressure(101325, Pa), 1.2).toString());
    }

    @Test
    void T2_by_T1() {
        assertEquals(pow(2.1, (gamma - 1) / gamma), isentropicFlow.T2_by_T1(2.1));
        assertEquals(pow(0.1, (gamma - 1) / gamma), isentropicFlow.T2_by_T1(0.1));
        assertEquals(1, isentropicFlow.T2_by_T1(1));
    }

    @Test
    void T2() {
        assertEquals("316.042K", isentropicFlow.T2(new Temperature(300, Kelvin), 1.2).toString());
    }

    @Test
    void T0_by_T() {
        assertEquals(1.032, isentropicFlow.T0_by_T(new Mach(0.4)));
        assertEquals(1.578, isentropicFlow.T0_by_T(new Mach(1.7)), 1e-15);
        assertEquals(1.2, isentropicFlow.T0_by_T(new Mach(1)));
        assertEquals(1, isentropicFlow.T0_by_T(new Mach(0)));
    }

    @Test
    void T() {
        assertEquals(format("%s°C", doubleToString((30 + 273.15) / 1.032 - 273.15)),
                isentropicFlow.T(new Mach(0.4), new Temperature(30, Celsius)).toString());
        assertEquals(format("%s°C", doubleToString((30 + 273.15) / 1.578 - 273.15)),
                isentropicFlow.T(new Mach(1.7), new Temperature(30, Celsius)).toString());
        assertEquals(format("%s°C", doubleToString((30 + 273.15) / 1.2 - 273.15)),
                isentropicFlow.T(new Mach(1), new Temperature(30, Celsius)).toString());
        assertEquals("30°C",
                isentropicFlow.T(new Mach(0), new Temperature(30, Celsius)).toString());
    }

    @Test
    void p0_by_p() {
        assertEquals(1.27550, isentropicFlow.p0_by_p(new Mach(0.6)), 1e-4);
        assertEquals(3.36780, isentropicFlow.p0_by_p(new Mach(1.44)), 1e-4);
        assertEquals(1.89293, isentropicFlow.p0_by_p(new Mach(1)), 1e-4);
        assertEquals(1, isentropicFlow.p0_by_p(new Mach(0)));
    }

    @Test
    void p() {
        assertEquals(doubleToString(101325 / 1.275504 / 1000) + " kPa",
                isentropicFlow.p(new Mach(0.6), new Pressure(101325, Pa)).toString());
        assertEquals(doubleToString(101325 / 3.36780 / 1000) + " kPa",
                isentropicFlow.p(new Mach(1.44), new Pressure(101325, Pa)).toString());
        assertEquals(doubleToString(101325 / 1.892926) + " psi",
                isentropicFlow.p(new Mach(1), new Pressure(101325, psi)).toString());
        assertEquals(doubleToString(101325) + " bar",
                isentropicFlow.p(new Mach(0), new Pressure(101325, bar)).toString());
    }

    @Test
    void A_by_ACritical() {
        assertEquals(2.707602, isentropicFlow.A_by_ACritical(new Mach(0.22)), 1e-5);
        assertEquals(1.396698, isentropicFlow.A_by_ACritical(new Mach(1.76)), 1e-5);
        assertEquals(1, isentropicFlow.A_by_ACritical(new Mach(1)));
        var error = assertThrows(IllegalArgumentException.class,
                () -> isentropicFlow.A_by_ACritical(new Mach(0)));
        assertEquals("Isentropic flow: A/A* is not defined for Mach 0", error.getMessage());
    }

    @Test
    void M_using_T0_by_T() {
        assertEquals(0.4, isentropicFlow.M_using_T0_by_T(1.032).machNumber, 1e-15);
        assertEquals(1.6, isentropicFlow.M_using_T0_by_T(1.512).machNumber, 1e-15);
        assertEquals(1, isentropicFlow.M_using_T0_by_T(1.2).machNumber, 1e-15);
        assertEquals(0, isentropicFlow.M_using_T0_by_T(1).machNumber);
    }

    @Test
    void M_using_p0_by_p() {
        assertEquals(0.6, isentropicFlow.M_using_p0_by_p(1.27550).machNumber, 1e-4);
        assertEquals(2.72, isentropicFlow.M_using_p0_by_p(24.00952).machNumber, 1e-4);
        assertEquals(1, isentropicFlow.M_using_p0_by_p(1.89293).machNumber, 1e-4);
        assertEquals(0, isentropicFlow.M_using_p0_by_p(1).machNumber);
    }

    @Test
    void M_using_A_by_ACritical() {
        assertEquals(0.22, isentropicFlow.M_using_A_by_ACritical(2.70760, Subsonic).machNumber, 1e-4);
        assertEquals(2.54, isentropicFlow.M_using_A_by_ACritical(2.73722, Supersonic).machNumber, 1e-4);
        assertEquals(1, isentropicFlow.M_using_A_by_ACritical(1, Subsonic).machNumber);
        assertEquals(1, isentropicFlow.M_using_A_by_ACritical(1, Supersonic).machNumber);
        assertEquals(1, isentropicFlow.M_using_A_by_ACritical(1, Sonic).machNumber);
        var error = assertThrows(IllegalStateException.class,
                () -> isentropicFlow.M_using_A_by_ACritical(1.2, Sonic));
        assertEquals("Flow cannot be 'sonic' for A/A* = 1.2", error.getMessage());
    }
}