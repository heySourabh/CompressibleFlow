package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;

public class Hydrogen implements Gas {
    @Override
    public double gamma() {
        return 1.407;
    }

    @Override
    public MolarMass molarMass() {
        return new MolarMass(2.016, moles_gram);
    }
}
