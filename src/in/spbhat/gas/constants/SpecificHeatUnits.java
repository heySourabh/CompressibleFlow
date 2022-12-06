package in.spbhat.gas.constants;

public enum SpecificHeatUnits {
    J_kgK("J/kg-K");

    private final String unitStr;

    SpecificHeatUnits(String unitStr) {
        this.unitStr = unitStr;
    }

    @Override
    public String toString() {
        return unitStr;
    }
}
