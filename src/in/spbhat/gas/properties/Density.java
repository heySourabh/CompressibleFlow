package in.spbhat.gas.properties;

import in.spbhat.geometry.Volume;
import in.spbhat.physics.Mass;
import in.spbhat.util.Formatter;

import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.gas.properties.Density.Units.pound_ft3;
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
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double density_kg_m3 = this.value * this.units.conversion;
        return density_kg_m3 / units.conversion;
    }

    public Density to(Units units) {
        return new Density(in(units), units);
    }

    public static Density calculateDensity(Mass mass, Volume volume) {
        return new Density(mass.in(kg) / volume.in(cubic_m), kg_m3);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }

    public static void main(String[] args) {
        Density density = new Density(10.5798789, kg_m3);
        System.out.println(density);
        System.out.println(density.in(kg_m3));
        System.out.println(new Density(541.45, pound_ft3).to(kg_m3));
        System.out.println(new Density(45.7, kg_m3).to(pound_ft3));

        System.out.println(Density.calculateDensity(new Mass(1.5, kg), new Volume(0.5, cubic_m)));
    }
}
