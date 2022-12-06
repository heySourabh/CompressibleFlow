package in.spbhat.gas.constants;

import in.spbhat.util.Formatter;

public class GasConstant {
    private final double value;
    private final Units unit;

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

    public GasConstant(double value, Units unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.unit;
    }
}
