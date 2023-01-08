/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import in.spbhat.util.Formatter;

public class Temperature {
    public enum Units {
        Kelvin("K") {
            @Override
            double toKelvin(double K) {
                return K;
            }

            @Override
            double fromKelvin(double K) {
                return K;
            }
        },
        Celsius("°C") {
            @Override
            double toKelvin(double C) {
                return C + 273.15;
            }

            @Override
            double fromKelvin(double K) {
                return K - 273.15;
            }
        },
        Fahrenheit("°F") {
            @Override
            double toKelvin(double F) {
                return (F - 32) * 5.0 / 9.0 + 273.15;
            }

            @Override
            double fromKelvin(double K) {
                return (K - 273.15) * 9.0 / 5.0 + 32;
            }
        },
        Rankine("°R") {
            @Override
            double toKelvin(double R) {
                return R * 5.0 / 9.0;
            }

            @Override
            double fromKelvin(double K) {
                return K * 1.8;
            }
        };

        private final String unitStr;

        Units(String unitStr) {
            this.unitStr = unitStr;
        }

        abstract double toKelvin(double value);

        abstract double fromKelvin(double K);

        @Override
        public String toString() {
            return unitStr;
        }
    }

    private final double temperature;
    private final Units units;

    public Temperature(double value, Units units) {
        if (units.toKelvin(value) <= 0) {
            throw new IllegalArgumentException("Absolute temperature must be positive.");
        }
        this.temperature = value;
        this.units = units;
    }

    public double in(Units units) {
        double temperature_K = this.units.toKelvin(temperature);
        return units.fromKelvin(temperature_K);
    }

    public Temperature to(Units units) {
        return new Temperature(in(units), units);
    }

    public Temperature times(double ratio) {
        return new Temperature(in(Units.Kelvin) * ratio, Units.Kelvin).to(this.units);
    }

    public Temperature divide(double ratio) {
        return new Temperature(in(Units.Kelvin) / ratio, Units.Kelvin).to(this.units);
    }

    @Override
    public String toString() {
        String temperatureStr = Formatter.doubleToString(this.temperature);
        return temperatureStr + "" + this.units;
    }
}
