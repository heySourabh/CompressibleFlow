/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.properties;

import in.spbhat.util.Formatter;

public class Temperature {
    public enum Units {
        K("K") {
            @Override
            double toKelvin(double K) {
                return K;
            }

            @Override
            double fromKelvin(double K) {
                return K;
            }
        },
        C("°C") {
            @Override
            double toKelvin(double C) {
                return C + 273.15;
            }

            @Override
            double fromKelvin(double K) {
                return K - 273.15;
            }
        },
        F("°F") {
            @Override
            double toKelvin(double F) {
                return (F - 32) * 5.0 / 9.0 + 273.15;
            }

            @Override
            double fromKelvin(double K) {
                return (K - 273.15) * 9.0 / 5.0 + 32;
            }
        },
        R("°R") {
            @Override
            double toKelvin(double R) {
                return R * 5.0 / 9.0;
            }

            @Override
            double fromKelvin(double K) {
                return K * 1.8;
            }
        };

        final String unitString;

        Units(String unitString) {
            this.unitString = unitString;
        }

        abstract double toKelvin(double value);

        abstract double fromKelvin(double K);

        @Override
        public String toString() {
            return unitString;
        }
    }

    private final double temperature;
    private final Units units;

    public Temperature(double value, Units units) {
        this.temperature = value;
        this.units = units;
    }

    public double in(Units units) {
        double temperature_K = this.units.toKelvin(temperature);
        return units.fromKelvin(temperature_K);
    }

    public Temperature to(Units units) {
        double temperature_K = this.units.toKelvin(temperature);
        return new Temperature(units.fromKelvin(temperature_K), units);
    }

    public Temperature times(double ratio) {
        return new Temperature(in(Units.K) * ratio, Units.K).to(this.units);
    }

    public Temperature divide(double ratio) {
        return new Temperature(in(Units.K) / ratio, Units.K).to(this.units);
    }

    @Override
    public String toString() {
        String temperatureStr = Formatter.doubleToString(this.temperature);
        return temperatureStr + "" + this.units;
    }

    public static void main(String[] args) {
        Temperature inletTemperature = new Temperature(35, Units.C);
        System.out.println(inletTemperature);
        System.out.println(inletTemperature.to(Units.K));
        System.out.println(inletTemperature.to(Units.F));
        System.out.println(inletTemperature.to(Units.R));

        System.out.println("-----");

        Temperature outletTemperature = new Temperature(300, Units.K);
        System.out.println(outletTemperature);
        System.out.println(outletTemperature.to(Units.C));
        System.out.println(outletTemperature.to(Units.F));
        System.out.println(outletTemperature.to(Units.R));

        System.out.println("-----");

        Temperature someTemperature = new Temperature(245, Units.F);
        System.out.println(someTemperature);
        System.out.println(someTemperature.to(Units.K));
        System.out.println(someTemperature.to(Units.C));
        System.out.println(someTemperature.to(Units.R));

        System.out.println("-----");

        Temperature anyTemperature = new Temperature(123, Units.R);
        System.out.println(anyTemperature);
        System.out.println(anyTemperature.to(Units.K));
        System.out.println(anyTemperature.to(Units.C));
        System.out.println(anyTemperature.to(Units.F));

        System.out.println(anyTemperature.in(Units.F));
        System.out.println(inletTemperature.times(5));
        System.out.println(inletTemperature.divide(5));
    }
}
