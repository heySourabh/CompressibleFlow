package in.spbhat.gas.constants;

import in.spbhat.util.Formatter;

public abstract class SpecificHeat {
    private final double value;
    private final Units units;

    public enum Units {
        J_kgK("J/kg-K");

        private final String unitStr;

        Units(String unitStr) {
            this.unitStr = unitStr;
        }

        @Override
        public String toString() {
            return unitStr;
        }
    }

    public SpecificHeat(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        if (units != Units.J_kgK) {
            throw new IllegalArgumentException("Not implemented yet!");
        }
        return this.value;
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
