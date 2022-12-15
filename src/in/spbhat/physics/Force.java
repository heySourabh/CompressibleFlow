/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.util.Formatter;

import static in.spbhat.physics.Force.Units.N;
import static in.spbhat.physics.Force.Units.pound_force;

public class Force {
    public enum Units {
        N(1.0, "N"),
        pound_force(4.4482216152605, "lbf");

        private final double conversion;
        private final String unitStr;

        Units(double conversion, String unitStr) {
            this.conversion = conversion;
            this.unitStr = unitStr;
        }

        @Override
        public String toString() {
            return unitStr;
        }
    }

    private final double value;
    private final Units units;

    public Force(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double force_N = this.value * this.units.conversion;
        return force_N / units.conversion;
    }

    public Force to(Units units) {
        return new Force(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units.unitStr;
    }

    public static void main(String[] args) {
        System.out.println(new Force(123, N));
        System.out.println(new Force(1.4, pound_force));
        System.out.println(new Force(5.5, N).to(pound_force));
        System.out.println(new Force(82.5, pound_force).to(N));
    }
}
