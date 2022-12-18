/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

import in.spbhat.util.Formatter;

public class Area {

    public enum Units {
        sq_m(1.0, "m^2"),
        sq_mm(1e-6, "mm^2"),
        sq_cm(1e-4, "cm^2"),
        sq_inch(6.4516e-4, "in^2"),
        sq_ft(9.290304e-2, "ft^2");

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

    public Area(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double area_sqm = this.value * this.units.conversion;
        return area_sqm / units.conversion;
    }

    public Area to(Units units) {
        return new Area(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
