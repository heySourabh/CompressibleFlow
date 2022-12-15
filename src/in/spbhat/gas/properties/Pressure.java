/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import in.spbhat.util.Formatter;

import java.util.List;

import static in.spbhat.gas.properties.Pressure.Units.*;

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

    public Pressure times(double ratio) {
        return new Pressure(value * ratio, units);
    }

    public Pressure divide(double ratio) {
        return new Pressure(value / ratio, units);
    }

    @Override
    public String toString() {
        if (List.of(MPa, kPa, Pa).contains(this.units)) {
            double pressure_Pa = this.value * this.units.conversion;
            Units units;
            if (pressure_Pa > 1e6) {
                units = MPa;
            } else if (pressure_Pa > 1e3) {
                units = kPa;
            } else {
                units = Pa;
            }
            return Formatter.doubleToString(in(units)) + " " + units;
        } else {
            return Formatter.doubleToString(this.value) + " " + this.units;
        }
    }

    public static void main(String[] args) {
        Pressure inletPressure = new Pressure(0.5, MPa);
        System.out.println(inletPressure);

        Pressure outletPressure = new Pressure(550, psi);
        System.out.println(outletPressure);

        Pressure somePressure = new Pressure(0.10001, bar);
        System.out.println(somePressure);

        Pressure anyPressure = new Pressure(1, atm);
        System.out.println(anyPressure);

        Pressure lowPressure = new Pressure(0.5, kPa);
        System.out.println(lowPressure);

        System.out.println(lowPressure.times(2));
        System.out.println(lowPressure.divide(2));
    }
}
