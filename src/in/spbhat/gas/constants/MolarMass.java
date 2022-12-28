/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.constants;

import in.spbhat.util.Formatter;

public class MolarMass {
    private final double value;
    private final Units units;

    public enum Units {
        moles_gram("moles/gram");

        private final String unitStr;

        Units(String unitStr) {
            this.unitStr = unitStr;
        }

        @Override
        public String toString() {
            return this.unitStr;
        }
    }

    public MolarMass(double value, Units units) {
        if (value < 0) {
            throw new IllegalArgumentException("MolarMass cannot be negative.");
        }
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        // Only moles/gram is available, hence no need of conversion.
        return switch (units) {
            case moles_gram -> value;
        };
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
