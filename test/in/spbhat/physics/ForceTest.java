/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import org.junit.jupiter.api.Test;

import static in.spbhat.physics.Force.Units.N;
import static in.spbhat.physics.Force.Units.pound_force;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ForceTest {

    @Test
    void in() {
        // // N -> N
        assertEquals(-268.211, new Force(-268.211, N).in(N), 1e-12);
        // N -> lbf
        assertEquals(45.071945002, new Force(200.490, N).in(pound_force), 1e-8);
        // lbf -> lbf
        assertEquals(79.5460, new Force(79.5460, pound_force).in(pound_force), 1e-12);
        // lbf -> N
        assertEquals(-1397.3554418, new Force(-314.138, pound_force).in(N), 1e-6);
    }

    @Test
    void to() {
        // // N -> N
        assertEquals("-268.211 N", new Force(-268.211, N).to(N).toString());
        // N -> lbf
        assertEquals("45.0719 lbf", new Force(200.490, N).to(pound_force).toString());
        // lbf -> lbf
        assertEquals("79.546 lbf", new Force(79.5460, pound_force).to(pound_force).toString());
        // lbf -> N
        assertEquals("-1397.36 N", new Force(-314.138, pound_force).to(N).toString());
    }

    @Test
    void toStringTest() {
        assertEquals("-185.009 N", new Force(-185.009, N).toString());
        assertEquals("125.881 lbf", new Force(125.881, pound_force).toString());
    }
}