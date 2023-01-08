/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.properties.Temperature.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureTest {

    @Test
    void throw_exception_if_absolute_temperature_is_non_positive() {
        var error = assertThrows(IllegalArgumentException.class, () -> new Temperature(-12, Kelvin));
        assertEquals("Absolute temperature must be positive.", error.getMessage());
        error = assertThrows(IllegalArgumentException.class, () -> new Temperature(0, Kelvin));
        assertEquals("Absolute temperature must be positive.", error.getMessage());
        error = assertThrows(IllegalArgumentException.class, () -> new Temperature(-273.5, Celsius));
        assertEquals("Absolute temperature must be positive.", error.getMessage());
        error = assertThrows(IllegalArgumentException.class, () -> new Temperature(0, Rankine));
        assertEquals("Absolute temperature must be positive.", error.getMessage());
        error = assertThrows(IllegalArgumentException.class, () -> new Temperature(-5, Rankine));
        assertEquals("Absolute temperature must be positive.", error.getMessage());
        assertEquals("-10°C", new Temperature(-10, Celsius).toString());
    }

    @Test
    void in() {
        // K -> C
        assertEquals(64.108, new Temperature(337.258, Kelvin).in(Celsius));
        // C -> K
        assertEquals(71.073, new Temperature(-202.077, Celsius).in(Kelvin), 1e-12);
        // K -> F
        assertEquals(-312.75436, new Temperature(81.6198, Kelvin).in(Fahrenheit), 1e-12);
        // F -> K
        assertEquals(473.543333333333, new Temperature(392.708, Fahrenheit).in(Kelvin), 1e-12);
        // C -> F
        assertEquals(-337.2034, new Temperature(-205.113, Celsius).in(Fahrenheit), 1e-12);
        // F -> C
        assertEquals(13.65938888888889, new Temperature(56.5869, Fahrenheit).in(Celsius), 1e-12);
        // K -> R
        assertEquals(655.2846, new Temperature(364.047, Kelvin).in(Rankine), 1e-12);
        // R -> K
        assertEquals(274.15, new Temperature(493.470, Rankine).in(Kelvin), 1e-12);
        // C -> R
        assertEquals(55.062, new Temperature(-242.560, Celsius).in(Rankine), 1e-12);
        // R -> C
        assertEquals(-28.216111111111, new Temperature(440.881, Rankine).in(Celsius), 1e-12);
        // F -> R
        assertEquals(434.781, new Temperature(-24.889, Fahrenheit).in(Rankine), 1e-12);
        // R -> F
        assertEquals(-87.278, new Temperature(372.392, Rankine).in(Fahrenheit), 1e-12);
    }

    @Test
    void to() {
        // K -> C
        assertEquals("64.108°C", new Temperature(337.258, Kelvin).to(Celsius).toString());
        // C -> K
        assertEquals("71.073K", new Temperature(-202.077, Celsius).to(Kelvin).toString());
        // K -> F
        assertEquals("-312.754°F", new Temperature(81.6198, Kelvin).to(Fahrenheit).toString());
        // F -> K
        assertEquals("473.543K", new Temperature(392.708, Fahrenheit).to(Kelvin).toString());
        // C -> F
        assertEquals("-337.203°F", new Temperature(-205.113, Celsius).to(Fahrenheit).toString());
        // F -> C
        assertEquals("13.6594°C", new Temperature(56.5869, Fahrenheit).to(Celsius).toString());
        // K -> R
        assertEquals("655.285°R", new Temperature(364.047, Kelvin).to(Rankine).toString());
        // R -> K
        assertEquals("274.15K", new Temperature(493.470, Rankine).to(Kelvin).toString());
        // C -> R
        assertEquals("55.062°R", new Temperature(-242.560, Celsius).to(Rankine).toString());
        // R -> C
        assertEquals("-28.2161°C", new Temperature(440.881, Rankine).to(Celsius).toString());
        // F -> R
        assertEquals("434.781°R", new Temperature(-24.889, Fahrenheit).to(Rankine).toString());
        // R -> F
        assertEquals("-87.278°F", new Temperature(372.392, Rankine).to(Fahrenheit).toString());
        // K, C, F, R: Identity tests
        assertEquals("269.579K", new Temperature(269.579, Kelvin).to(Kelvin).toString());
        assertEquals("-93.257°C", new Temperature(-93.2570, Celsius).to(Celsius).toString());
        assertEquals("-191.171°F", new Temperature(-191.171, Fahrenheit).to(Fahrenheit).toString());
        assertEquals("231.258°R", new Temperature(231.258, Rankine).to(Rankine).toString());
    }

    @Test
    void times() {
        // K, C, F, R
        assertEquals("703.602K", new Temperature(112.110, Kelvin).times(6.276).toString());
        assertEquals("256.173°C", new Temperature(-46.070, Celsius).times(2.331).toString());
        assertEquals("1600.65°F", new Temperature(-82.460, Fahrenheit).times(5.462).toString());
        assertEquals("1499.41°R", new Temperature(323.289, Rankine).times(4.638).toString());
    }

    @Test
    void divide() {
        // K, C, F, R
        assertEquals("17.8633K", new Temperature(112.110, Kelvin).divide(6.276).toString());
        assertEquals("-175.733°C", new Temperature(-46.070, Celsius).divide(2.331).toString());
        assertEquals("-390.609°F", new Temperature(-82.460, Fahrenheit).divide(5.462).toString());
        assertEquals("69.7044°R", new Temperature(323.289, Rankine).divide(4.638).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("123K", new Temperature(123, Kelvin).toString());
        assertEquals("123°C", new Temperature(123, Celsius).toString());
        assertEquals("123°F", new Temperature(123, Fahrenheit).toString());
        assertEquals("123°R", new Temperature(123, Rankine).toString());
    }
}