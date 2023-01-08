/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.constants;

import in.spbhat.util.Formatter;

public abstract class SpecificHeat {
    public enum Units {
        J_kgK(1.0, "J/kg-K"),
        btu_lbmR(4186.8, "btu/lbm-°R"),
        ftlbf_lbmR(5.380320456, "ft-lbf/lbm-°R");

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

    public SpecificHeat(double value, Units units) {
        if (value < 0) {
            throw new IllegalArgumentException("SpecificHeat cannot be negative.");
        }
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double specificHeat_J_kgK = this.value * this.units.conversion;
        return specificHeat_J_kgK / units.conversion;
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
