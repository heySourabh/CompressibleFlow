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
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        if (units != Units.moles_gram) {
            throw new IllegalArgumentException("Not implemented yet!");
        }
        return this.value;
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }

    public static void main(String[] args) {
        MolarMass molarMass = new MolarMass(28.9, Units.moles_gram);
        System.out.println(molarMass);
        System.out.println(molarMass.in(Units.moles_gram));
    }
}
