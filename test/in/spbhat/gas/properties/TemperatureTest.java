/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import org.junit.jupiter.api.Test;

import static in.spbhat.gas.properties.Temperature.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureTest {

    @Test
    void in() {
        // K -> C
        assertEquals(64.108, new Temperature(337.258, K).in(C));
        // C -> K
        assertEquals(71.073, new Temperature(-202.077, C).in(K), 1e-12);
        // K -> F
        assertEquals(-312.75436, new Temperature(81.6198, K).in(F), 1e-12);
        // F -> K
        assertEquals(473.543333333333, new Temperature(392.708, F).in(K), 1e-12);
        // C -> F
        assertEquals(-337.2034, new Temperature(-205.113, C).in(F), 1e-12);
        // F -> C
        assertEquals(13.65938888888889, new Temperature(56.5869, F).in(C), 1e-12);
        // K -> R
        assertEquals(655.2846, new Temperature(364.047, K).in(R), 1e-12);
        // R -> K
        assertEquals(274.15, new Temperature(493.470, R).in(K), 1e-12);
        // C -> R
        assertEquals(55.062, new Temperature(-242.560, C).in(R), 1e-12);
        // R -> C
        assertEquals(-28.216111111111, new Temperature(440.881, R).in(C), 1e-12);
        // F -> R
        assertEquals(434.781, new Temperature(-24.889, F).in(R), 1e-12);
        // R -> F
        assertEquals(-87.278, new Temperature(372.392, R).in(F), 1e-12);
    }

    @Test
    void to() {
        // K -> C
        assertEquals("64.108°C", new Temperature(337.258, K).to(C).toString());
        // C -> K
        assertEquals("71.073K", new Temperature(-202.077, C).to(K).toString());
        // K -> F
        assertEquals("-312.754°F", new Temperature(81.6198, K).to(F).toString());
        // F -> K
        assertEquals("473.543K", new Temperature(392.708, F).to(K).toString());
        // C -> F
        assertEquals("-337.203°F", new Temperature(-205.113, C).to(F).toString());
        // F -> C
        assertEquals("13.6594°C", new Temperature(56.5869, F).to(C).toString());
        // K -> R
        assertEquals("655.285°R", new Temperature(364.047, K).to(R).toString());
        // R -> K
        assertEquals("274.15K", new Temperature(493.470, R).to(K).toString());
        // C -> R
        assertEquals("55.062°R", new Temperature(-242.560, C).to(R).toString());
        // R -> C
        assertEquals("-28.2161°C", new Temperature(440.881, R).to(C).toString());
        // F -> R
        assertEquals("434.781°R", new Temperature(-24.889, F).to(R).toString());
        // R -> F
        assertEquals("-87.278°F", new Temperature(372.392, R).to(F).toString());
        // K, C, F, R: Identity tests
        assertEquals("269.579K", new Temperature(269.579, K).to(K).toString());
        assertEquals("-93.257°C", new Temperature(-93.2570, C).to(C).toString());
        assertEquals("-191.171°F", new Temperature(-191.171, F).to(F).toString());
        assertEquals("231.258°R", new Temperature(231.258, R).to(R).toString());
    }

    @Test
    void times() {
        // K, C, F, R
        assertEquals("703.602K", new Temperature(112.110, K).times(6.276).toString());
        assertEquals("256.173°C", new Temperature(-46.070, C).times(2.331).toString());
        assertEquals("1600.65°F", new Temperature(-82.460, F).times(5.462).toString());
        assertEquals("1499.41°R", new Temperature(323.289, R).times(4.638).toString());
    }

    @Test
    void divide() {
        // K, C, F, R
        assertEquals("17.8633K", new Temperature(112.110, K).divide(6.276).toString());
        assertEquals("-175.733°C", new Temperature(-46.070, C).divide(2.331).toString());
        assertEquals("-390.609°F", new Temperature(-82.460, F).divide(5.462).toString());
        assertEquals("69.7044°R", new Temperature(323.289, R).divide(4.638).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("123K", new Temperature(123, K).toString());
        assertEquals("123°C", new Temperature(123, C).toString());
        assertEquals("123°F", new Temperature(123, F).toString());
        assertEquals("123°R", new Temperature(123, R).toString());
    }
}