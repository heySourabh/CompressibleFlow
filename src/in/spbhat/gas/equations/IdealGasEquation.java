/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas.equations;

import in.spbhat.gas.Air;
import in.spbhat.gas.Gas;
import in.spbhat.gas.properties.Density;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;

import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;
import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.gas.properties.Pressure.Units.*;
import static in.spbhat.gas.properties.Temperature.Units.C;
import static in.spbhat.gas.properties.Temperature.Units.K;

public class IdealGasEquation {
    private final Gas gas;

    public IdealGasEquation(Gas gas) {
        this.gas = gas;
    }

    public Pressure pressure(Density density, Temperature temperature) {
        double p = density.in(kg_m3) * gas.R().in(J_kgK) * temperature.in(K);
        return new Pressure(p, Pa);
    }

    public Density density(Pressure pressure, Temperature temperature) {
        double rho = pressure.in(Pa) / gas.R().in(J_kgK) / temperature.in(K);
        return new Density(rho, kg_m3);
    }

    public Temperature temperature(Pressure pressure, Density density) {
        double T = pressure.in(Pa) / density.in(kg_m3) / gas.R().in(J_kgK);
        return new Temperature(T, K);
    }

    public static void main(String[] args) {
        Gas air = new Air();
        var eos = new IdealGasEquation(air);
        System.out.println(eos.density(new Pressure(1, atm), new Temperature(15, C)));
        System.out.println(eos.pressure(new Density(1.225, kg_m3), new Temperature(300, K)));
        System.out.println(eos.temperature(new Pressure(1, bar), new Density(1.2, kg_m3)));
    }
}
