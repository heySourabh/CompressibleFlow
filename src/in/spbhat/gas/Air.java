package in.spbhat.gas;

import in.spbhat.gas.constants.R;

import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;

public class Air implements Gas {
    @Override
    public R R() {
        return new R(287.0, J_kgK);
    }

    @Override
    public double gamma() {
        return 1.4;
    }
}
