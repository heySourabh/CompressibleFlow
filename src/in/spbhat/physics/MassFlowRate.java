/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.physics;

import in.spbhat.gas.properties.Density;
import in.spbhat.geometry.Area;
import in.spbhat.util.Formatter;

import static in.spbhat.gas.properties.Density.Units.kg_m3;
import static in.spbhat.geometry.Area.Units.sq_m;
import static in.spbhat.physics.MassFlowRate.Units.kg_s;
import static in.spbhat.physics.Speed.Units.m_s;

public class MassFlowRate {
    public enum Units {
        kg_s(1.0, "kg/s"),
        pound_s(0.45359237, "lbm/s");

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

    public MassFlowRate(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public MassFlowRate(Density density, Area area, Speed velocity) {
        this(density.in(kg_m3) * area.in(sq_m) * velocity.in(m_s), kg_s);
    }

    public double in(Units units) {
        double massFlowRate_kg_s = this.value * this.units.conversion;
        return massFlowRate_kg_s / units.conversion;
    }

    public MassFlowRate to(Units units) {
        return new MassFlowRate(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }
}
