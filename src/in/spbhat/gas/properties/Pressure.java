package in.spbhat.gas.properties;

import in.spbhat.util.Formatter;

public class Pressure {
    public enum Units {
        Pa(1), kPa(1e3), MPa(1e6),
        psi(6894.757), bar(1e5), atm(101325);

        final double conversion;

        Units(double conversion) {
            this.conversion = conversion;
        }
    }

    private final double value;
    private final Units units;

    public Pressure(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double pressure_Pa = this.value * this.units.conversion;
        return pressure_Pa / units.conversion;
    }

    public Pressure to(Units units) {
        return new Pressure(in(units), units);
    }

    @Override
    public String toString() {
        double pressure_Pa = this.value * this.units.conversion;
        Units units;
        if (pressure_Pa > 1e6) {
            units = Units.MPa;
        } else if (pressure_Pa > 1e3) {
            units = Units.kPa;
        } else {
            units = Units.Pa;
        }
        return Formatter.doubleToString(in(units)) + " " + units;
    }

    public static void main(String[] args) {
        Pressure inletPressure = new Pressure(0.5, Units.MPa);
        System.out.println(inletPressure);

        Pressure outletPressure = new Pressure(550, Units.psi);
        System.out.println(outletPressure);

        Pressure somePressure = new Pressure(0.10001, Units.bar);
        System.out.println(somePressure);

        Pressure anyPressure = new Pressure(1, Units.atm);
        System.out.println(anyPressure);

        Pressure lowPressure = new Pressure(0.5, Units.kPa);
        System.out.println(lowPressure);
    }
}
