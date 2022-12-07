package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;

public class Xenon implements Gas {
    @Override
    public double gamma() {
        return 1.667;
    }

    @Override
    public MolarMass molarMass() {
        return new MolarMass(131.3, moles_gram);
    }
}
