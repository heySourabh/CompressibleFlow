/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;

public class CarbonDioxide implements Gas {
    @Override
    public double gamma() {
        return 1.3;
    }

    @Override
    public MolarMass molarMass() {
        return new MolarMass(44.010, moles_gram);
    }
}
