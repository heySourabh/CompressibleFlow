/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import in.spbhat.util.Formatter;

public class Length {
    public enum Units {
        m(1.0), km(1e3), cm(1e-2), ft(0.3048), inches(0.0254);

        private final double conversion;

        Units(double conversion) {
            this.conversion = conversion;
        }
    }

    private final double value;
    private final Units units;

    public Length(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double length_m = this.value * this.units.conversion;
        return length_m / units.conversion;
    }

    public Length to(Units units) {
        return new Length(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
