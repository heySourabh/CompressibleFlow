/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.gas.properties.Density;
import in.spbhat.geometry.Area;
import org.junit.jupiter.api.Test;

import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.geometry.Area.Units.sq_m;
import static in.spbhat.physics.MassFlowRate.Units.kg_s;
import static in.spbhat.physics.MassFlowRate.Units.pound_s;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MassFlowRateTest {

    @Test
    void in() {
        // kg/s -> kg/s
        assertEquals(437.920, new MassFlowRate(437.920, kg_s).in(kg_s));
        // kg/s -> pound/s
        assertEquals(60.030330757, new MassFlowRate(27.2293, kg_s).in(pound_s), 1e-8);
        // pound/s -> pound/s
        assertEquals(158.859, new MassFlowRate(158.859, pound_s).in(pound_s));
        // pound/s -> kg/s
        assertEquals(200.03332799, new MassFlowRate(440.998, pound_s).in(kg_s), 1e-8);
    }

    @Test
    void to() {
        // kg/s -> kg/s
        assertEquals("437.92 kg/s", new MassFlowRate(437.920, kg_s).to(kg_s).toString());
        // kg/s -> pound/s
        assertEquals("60.0303 lbm/s", new MassFlowRate(27.2293, kg_s).to(pound_s).toString());
        // pound/s -> pound/s
        assertEquals("158.859 lbm/s", new MassFlowRate(158.859, pound_s).to(pound_s).toString());
        // pound/s -> kg/s
        assertEquals("200.033 kg/s", new MassFlowRate(440.998, pound_s).to(kg_s).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("332.897 kg/s", new MassFlowRate(332.897, kg_s).toString());
        assertEquals("436.388 lbm/s", new MassFlowRate(436.388, pound_s).toString());
        assertEquals("12 kg/s", new MassFlowRate(
                new Density(1.2, kg_m3), new Area(2, sq_m),
                new Speed(5, Speed.Units.m_s)).toString());
    }
}