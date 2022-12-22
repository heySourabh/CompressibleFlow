/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import in.spbhat.geometry.Volume;
import in.spbhat.physics.Mass;
import in.spbhat.util.Formatter;

import static in.spbhat.geometry.Volume.Units.cubic_m;
import static in.spbhat.physics.Mass.Units.kg;

public class Density {
    private final double value;
    private final Units units;

    public enum Units {
        kg_m3(1.0, "kg/m^3"),
        pound_ft3(16.01846337, "lbm/ft^3");

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

    public Density(double value, Units units) {
        if (value <= 0) {
            throw new IllegalArgumentException("Density must be positive.");
        }
        this.value = value;
        this.units = units;
    }

    public Density(Mass mass, Volume volume) {
        this(mass.in(kg) / volume.in(cubic_m), Units.kg_m3);
    }

    public double in(Units units) {
        double density_kg_m3 = this.value * this.units.conversion;
        return density_kg_m3 / units.conversion;
    }

    public Density to(Units units) {
        return new Density(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
