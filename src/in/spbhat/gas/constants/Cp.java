package in.spbhat.gas.constants;

import in.spbhat.util.Formatter;

public class Cp {
    private final double value;
    private final SpecificHeatUnits unit;

    public Cp(double value, SpecificHeatUnits unit) {
        this.value = value;
        this.unit = unit;
    }

    public double in(SpecificHeatUnits units) {
        if (units != SpecificHeatUnits.J_kgK) {
            throw new IllegalArgumentException("Not implemented yet!");
        }
        return this.value;
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.unit;
    }
}
