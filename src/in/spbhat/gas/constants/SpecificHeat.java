package in.spbhat.gas.constants;

import in.spbhat.util.Formatter;

import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;
import static in.spbhat.gas.constants.SpecificHeat.Units.btu_lbmR;

public abstract class SpecificHeat {
    public enum Units {
        J_kgK(1.0, "J/kg-K"),
        btu_lbmR(4.1868, "btu/lbm-°R");

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

    public SpecificHeat(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double specificHeat_J_kgK = this.value * this.units.conversion;
        return specificHeat_J_kgK / units.conversion;
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }

    public static void main(String[] args) {
        var specificHeat_SI = new SpecificHeat(1, J_kgK){};
        var specificHeat_nonStandard = new SpecificHeat(1, btu_lbmR){};
        System.out.println(specificHeat_SI);
        System.out.println(specificHeat_nonStandard);
        System.out.println(specificHeat_SI.in(btu_lbmR) + " " + btu_lbmR);
        System.out.println(specificHeat_nonStandard.in(J_kgK) + " " + J_kgK);
    }
}
