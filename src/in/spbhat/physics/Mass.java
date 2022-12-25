/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.gas.properties.Density;
import in.spbhat.geometry.Volume;
import in.spbhat.util.Formatter;

import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.geometry.Volume.Units.cubic_m;
import static in.spbhat.physics.Mass.Units.kg;

public class Mass {
    public enum Units {
        kg(1.0, "kg"),
        pound(0.45359237, "lbm");

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

    public Mass(double value, Units units) {
        if (value < 0) {
            throw new IllegalArgumentException("Mass cannot be negative.");
        }
        this.value = value;
        this.units = units;
    }

    public Mass(Density density, Volume volume) {
        this(density.in(kg_m3) * volume.in(cubic_m), kg);
    }

    public double in(Units units) {
        double mass_kg = this.value * this.units.conversion;
        return mass_kg / units.conversion;
    }

    public Mass to(Units units) {
        return new Mass(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
