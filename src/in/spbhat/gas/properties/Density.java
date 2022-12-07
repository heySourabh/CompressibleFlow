package in.spbhat.gas.properties;

import in.spbhat.util.Formatter;

public class Density {
    private final double value;
    private final Units units;

    public enum Units {
        kg_m3("kg/m^3");

        private final String unitStr;

        Units(String unitStr) {
            this.unitStr = unitStr;
        }

        @Override
        public String toString() {
            return unitStr;
        }
    }

    public Density(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        if (units != Units.kg_m3) {
            throw new IllegalArgumentException("Not implemented yet!");
        }
        return value;
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }

    public static void main(String[] args) {
        Density density = new Density(10.5798789, Units.kg_m3);
        System.out.println(density);
        System.out.println(density.in(Units.kg_m3));
    }
}
