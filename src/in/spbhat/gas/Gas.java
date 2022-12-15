/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import in.spbhat.gas.constants.Cp;
import in.spbhat.gas.constants.Cv;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.gas.constants.R;
import in.spbhat.gas.properties.Density;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;
import in.spbhat.physics.Speed;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;
import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.gas.properties.Pressure.Units.Pa;
import static in.spbhat.gas.properties.Pressure.Units.atm;
import static in.spbhat.gas.properties.Temperature.Units.C;
import static in.spbhat.gas.properties.Temperature.Units.K;

public interface Gas {

    double gamma();

    MolarMass molarMass();

    default R R() {
        double universalR = 8314.462618; // J/kmol-K
        double RValue = universalR / molarMass().in(moles_gram); // J/kg-K
        return new R(RValue, J_kgK);
    }

    default Cp cp() {
        return new Cp(gamma() * cv().in(J_kgK), J_kgK);
    }

    default Cv cv() {
        return new Cv(R().in(J_kgK) / (gamma() - 1.0), J_kgK);
    }

    default Speed soundSpeed(Temperature temperature) {
        double speed_m_s = Math.sqrt(gamma() * R().in(J_kgK) * temperature.in(K));
        return new Speed(speed_m_s, Speed.Units.m_s);
    }

    default Speed soundSpeed(Pressure pressure, Density density) {
        double speed_m_s = Math.sqrt(gamma() * pressure.in(Pa) / density.in(kg_m3));
        return new Speed(speed_m_s, Speed.Units.m_s);
    }

    public static void main(String[] args) {
        System.out.println(new Air().R());
        System.out.println(new Air().cp());
        System.out.println(new Air().cv());
        System.out.println(new Air().gamma());
        System.out.println(new Air().molarMass());

        System.out.println(new Air().soundSpeed(new Temperature(15, C)));
        System.out.println(new Air().soundSpeed(new Pressure(1, atm), new Density(1.22, kg_m3)));
    }
}
