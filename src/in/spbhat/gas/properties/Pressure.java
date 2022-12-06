package in.spbhat.gas.properties;

public class Pressure {
    public enum Units {
        Pa(1), kPa(1e3), MPa(1e6),
        psi(6894.757), bar(1e5), atm(101325);
        final double conversion;

        Units(double conversion) {
            this.conversion = conversion;
        }
    }

    private final double value_Pa;

    public Pressure(double value, Units units) {
        value_Pa = value * units.conversion;
    }

    public double in(Units units) {
        return value_Pa / units.conversion;
    }

    @Override
    public String toString() {
        Units units;
        if (value_Pa > 1e6) {
            units = Units.MPa;
        } else if (value_Pa > 1e3) {
            units = Units.kPa;
        } else {
            units = Units.Pa;
        }
        return String.format("%.6g %s", in(units), units);
    }

    public static void main(String[] args) {
        Pressure inletPressure = new Pressure(0.5, Units.MPa);
        System.out.println(inletPressure);

        Pressure outletPressure = new Pressure(550, Units.psi);
        System.out.println(outletPressure);

        Pressure somePressure = new Pressure(0.1, Units.bar);
        System.out.println(somePressure);

        Pressure anyPressure = new Pressure(1, Units.atm);
        System.out.println(anyPressure);

        Pressure lowPressure = new Pressure(0.5, Units.kPa);
        System.out.println(lowPressure);
    }
}
