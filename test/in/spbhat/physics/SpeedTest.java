/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import org.junit.jupiter.api.Test;

import static in.spbhat.physics.Speed.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpeedTest {

    @Test
    void in() {
        // m/s -> m/s
        assertEquals(162.440, new Speed(162.440, m_s).in(m_s));
        // m/s -> km/h
        assertEquals(1753.398, new Speed(-487.055, m_s).in(km_h));
        // km/h -> m/s
        assertEquals(72.0197222222222, new Speed(259.271, km_h).in(m_s), 1e-12);
        // m/s -> ft/s
        assertEquals(384.51771654, new Speed(117.201, m_s).in(ft_s), 1e-8);
        // ft/s -> m/s
        assertEquals(39.2417808, new Speed(-128.746, ft_s).in(m_s), 1e-8);
        // km/h -> km/h
        assertEquals(444.247, new Speed(-444.247, km_h).in(km_h), 1e-8);
        // km/h -> ft/s
        assertEquals(452.71033829, new Speed(496.750, km_h).in(ft_s), 1e-8);
        // ft/s -> km/h
        assertEquals(493.8089184, new Speed(450.030, ft_s).in(km_h), 1e-8);
        // ft/s -> ft/s
        assertEquals(134.889, new Speed(-134.889, ft_s).in(ft_s), 1e-8);
    }

    @Test
    void to() {
        // m/s -> m/s
        assertEquals("162.44 m/s", new Speed(162.440, m_s).to(m_s).toString());
        // m/s -> km/h
        assertEquals("1753.4 km/h", new Speed(-487.055, m_s).to(km_h).toString());
        // km/h -> m/s
        assertEquals("72.0197 m/s", new Speed(259.271, km_h).to(m_s).toString());
        // m/s -> ft/s
        assertEquals("384.518 ft/s", new Speed(117.201, m_s).to(ft_s).toString());
        // ft/s -> m/s
        assertEquals("39.2418 m/s", new Speed(-128.746, ft_s).to(m_s).toString());
        // km/h -> km/h
        assertEquals("444.247 km/h", new Speed(-444.247, km_h).to(km_h).toString());
        // km/h -> ft/s
        assertEquals("452.71 ft/s", new Speed(496.750, km_h).to(ft_s).toString());
        // ft/s -> km/h
        assertEquals("493.809 km/h", new Speed(450.030, ft_s).to(km_h).toString());
        // ft/s -> ft/s
        assertEquals("134.889 ft/s", new Speed(-134.889, ft_s).to(ft_s).toString());
    }

    @Test
    void addTest() {
        Speed v1 = new Speed(2, m_s);
        Speed v2 = new Speed(8, m_s);
        assertEquals("10 m/s", v1.add(v2).toString());
        assertEquals("10 m/s", v1.add(v2.to(ft_s)).toString());
        assertEquals(new Speed(10, m_s).to(ft_s).toString(),
                v1.to(ft_s).add(v2).toString());
    }

    @Test
    void timesTest() {
        assertEquals(new Speed(22, m_s).toString(), new Speed(11, m_s).times(2).toString());
        assertEquals(new Speed(10, ft_s).toString(), new Speed(20, ft_s).times(0.5).toString());
    }

    @Test
    void toStringTest() {
        // m_s, km_h, ft_s
        assertEquals("311.797 m/s", new Speed(-311.797, m_s).toString());
        assertEquals("232.029 km/h", new Speed(232.029, Speed.Units.km_h).toString());
        assertEquals("238.493 ft/s", new Speed(-238.493, Speed.Units.ft_s).toString());
    }
}