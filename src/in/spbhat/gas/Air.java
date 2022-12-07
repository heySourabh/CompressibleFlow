package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;

public class Air implements Gas {
    @Override
    public double gamma() {
        return 1.404;
    }

    @Override
    public MolarMass molarMass() {
        return new MolarMass(28.960, moles_gram);
    }
}
