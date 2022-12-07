package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;

public class Argon implements Gas {
    @Override
    public double gamma() {
        return 1.667;
    }

    @Override
    public MolarMass molarMass() {
        return new MolarMass(39.940, moles_gram);
    }
}
