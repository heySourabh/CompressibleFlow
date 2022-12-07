package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;

public class Freon12 implements Gas {
    @Override
    public double gamma() {
        return 1.139;
    }

    @Override
    public MolarMass molarMass() {
        return new MolarMass(120.9, moles_gram);
    }
}
