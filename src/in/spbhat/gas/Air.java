/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.gas;

import in.spbhat.gas.constants.MolarMass;
import in.spbhat.gas.constants.R;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;

public class Air implements Gas {
    @Override
    public double gamma() {
        return 1.4;
    }

    @Override
    public R R() {
        return new R(287.0, J_kgK);
    }

    @Override
    public MolarMass molarMass() {
        return new MolarMass(28.960, moles_gram);
    }
}
