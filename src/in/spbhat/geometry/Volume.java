/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import in.spbhat.util.Formatter;

import static java.lang.Math.pow;

public class Volume {
    public enum Units {
        cubic_m(1.0, "m^3"),
        cubic_mm(1e-9, "mm^3"),
        cubic_cm(1e-6, "cm^3"),
        cubic_inch(pow(0.0254, 3), "in^3"),
        cubic_ft(pow(0.0254 * 12, 3), "ft^3");

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

    public Volume(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double volume_cubicm = this.value * this.units.conversion;
        return volume_cubicm / units.conversion;
    }

    public Volume to(Units units) {
        return new Volume(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
