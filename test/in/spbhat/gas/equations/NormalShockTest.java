/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Gas;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.physics.Mach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalShockTest {

    NormalShock ns = new NormalShock(new Gas() {
        @Override
        public double gamma() {
            return 1.4;
        }

        @Override
        public MolarMass molarMass() {
            return null;
        }
    });

    @Test
    void p2_by_p1() {
        assertEquals(4.5, ns.p2_by_p1(new Mach(2)));
        assertEquals(1, ns.p2_by_p1(new Mach(1)));
        var error = assertThrows(IllegalArgumentException.class,
                () -> ns.p2_by_p1(new Mach(0.9999)));
        assertEquals("Normal shock: Upstream Mach number is subsonic (Mach 0.9999).",
                error.getMessage());
    }

    @Test
    void rho2_by_rho1() {
        assertEquals(10.0 / 3.0, ns.rho2_by_rho1(new Mach(2.5)));
        assertEquals(1, ns.rho2_by_rho1(new Mach(1)));
        var error = assertThrows(IllegalArgumentException.class,
                () -> ns.rho2_by_rho1(new Mach(0.5)));
        assertEquals("Normal shock: Upstream Mach number is subsonic (Mach 0.5).",
                error.getMessage());
    }

    @Test
    void T2_by_T1() {
        assertEquals(2.13750, ns.T2_by_T1(new Mach(2.5)));
        assertEquals(1, ns.T2_by_T1(new Mach(1)));
        var error = assertThrows(IllegalArgumentException.class,
                () -> ns.T2_by_T1(new Mach(0.25)));
        assertEquals("Normal shock: Upstream Mach number is subsonic (Mach 0.25).",
                error.getMessage());
    }

    @Test
    void M2() {
        assertEquals(0.41670, ns.M2(new Mach(4.9)).machNumber, 1e-4);
        assertEquals(1, ns.M2(new Mach(1)).machNumber);
        var error = assertThrows(IllegalArgumentException.class,
                () -> ns.M2(new Mach(0.25)));
        assertEquals("Normal shock: Upstream Mach number is subsonic (Mach 0.25).",
                error.getMessage());
    }

    @Test
    void p02_by_p01() {
        assertEquals(0.31450, ns.p02_by_p01(new Mach(3.05)), 1e-5);
        assertEquals(1, ns.p02_by_p01(new Mach(1)));
        var error = assertThrows(IllegalArgumentException.class,
                () -> ns.p02_by_p01(new Mach(0.25)));
        assertEquals("Normal shock: Upstream Mach number is subsonic (Mach 0.25).",
                error.getMessage());
    }

    @Test
    void p02_by_p1() {
        assertEquals(10.14208, ns.p02_by_p1(new Mach(2.74)), 1e-5);
        assertEquals(1.89293, ns.p02_by_p1(new Mach(1)), 1e-5);
        var error = assertThrows(IllegalArgumentException.class,
                () -> ns.p02_by_p1(new Mach(0.25)));
        assertEquals("Normal shock: Upstream Mach number is subsonic (Mach 0.25).",
                error.getMessage());
    }

    @Test
    void M1_using_p2_by_p1() {
        assertEquals(1.6, ns.M1_using_p2_by_p1(2.82).machNumber);
        assertEquals(1, ns.M1_using_p2_by_p1(1).machNumber);
        var error = assertThrows(IllegalArgumentException.class, () -> ns.M1_using_p2_by_p1(0.8));
        assertEquals("Normal shock: p2/p1 must be greater than or equal to 1.", error.getMessage());
    }

    @Test
    void M1_using_p02_by_p1() {
        assertEquals(1.2, ns.M1_using_p02_by_p1(2.40750).machNumber, 1e-5);
        assertEquals(1, ns.M1_using_p02_by_p1(1.89293).machNumber, 1e-5);
        var error = assertThrows(IllegalArgumentException.class, () -> ns.M1_using_p02_by_p1(1.8));
        assertEquals("Normal shock: p02/p1 must be greater than or equal to 1.89293", error.getMessage());
    }

    @Test
    void M1_using_p02_by_p01() {
        var error = assertThrows(IllegalArgumentException.class, () -> ns.M1_using_p02_by_p01(1.1));
        assertEquals("Normal shock: p02/p01 must be less than or equal to 1.", error.getMessage());
        assertEquals("Mach 1.42", ns.M1_using_p02_by_p01(0.953063).toString());
        assertEquals("Mach 5", ns.M1_using_p02_by_p01(0.0617163).toString());
        assertEquals(1, ns.M1_using_p02_by_p01(1).machNumber, 1e-5);
    }

    @Test
    void M1_using_M2() {
        var error = assertThrows(IllegalArgumentException.class, () -> ns.M1_using_M2(new Mach(1.1)));
        assertEquals("Normal shock: downstream Mach must be less than 1.", error.getMessage());
        assertEquals("Mach 1", ns.M1_using_M2(new Mach(1)).toString());
        assertEquals("Mach 1.22", ns.M1_using_M2(new Mach(0.829985)).toString());
        assertEquals("Mach 4", ns.M1_using_M2(new Mach(0.4349588)).toString());
    }
}