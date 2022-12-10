package in.spbhat.physics;

import in.spbhat.gas.properties.Density;
import in.spbhat.geometry.Volume;
import in.spbhat.util.Formatter;

import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.gas.properties.Density.Units.pound_ft3;
import static in.spbhat.geometry.Volume.Units.*;
import static in.spbhat.physics.Mass.Units.kg;
import static in.spbhat.physics.Mass.Units.pound;

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
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double mass_kg = this.value * this.units.conversion;
        return mass_kg / units.conversion;
    }

    public Mass to(Units units) {
        return new Mass(in(units), units);
    }

    public static Mass calculateMass(Density density, Volume volume) {
        return new Mass(density.in(kg_m3) * volume.in(cubic_m), kg);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units.unitStr;
    }

    public static void main(String[] args) {
        System.out.println(new Mass(123, kg));
        System.out.println(new Mass(1.4, pound));
        System.out.println(new Mass(5.5, kg).to(pound));
        System.out.println(new Mass(82.5, pound).to(kg));

        System.out.println(Mass.calculateMass(new Density(1.2, kg_m3), new Volume(2, cubic_cm)));
        System.out.println(Mass.calculateMass(new Density(12, pound_ft3), new Volume(2, cubic_ft)));
    }
}
