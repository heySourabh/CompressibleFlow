/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.util.Formatter;

import static java.lang.Math.abs;

public class Speed {
    public enum Units {
        m_s(1.0, "m/s"),
        km_h(1.0 / 3.6, "km/h"),
        ft_s(0.3048, "ft/s");

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

    public Speed(double value, Units units) {
        this.value = abs(value);
        this.units = units;
    }

    public double in(Units units) {
        double speed_m_s = this.value * this.units.conversion;
        return speed_m_s / units.conversion;
    }

    public Speed to(Units units) {
        return new Speed(in(units), units);
    }

    public Speed add(Speed other) {
        return new Speed(this.value + other.in(this.units), this.units);
    }

    public Speed times(double scalar) {
        return new Speed(this.value * scalar, this.units);
    }

    public Speed divide(double scalar) {
        return new Speed(this.value / scalar, this.units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
