package in.spbhat.gas;

import in.spbhat.gas.constants.GasConstant;

import static in.spbhat.gas.constants.GasConstant.Units.J_kgK;

public class Air implements Gas {
    @Override
    public GasConstant R() {
        return new GasConstant(287.0, J_kgK);
    }

    @Override
    public double gamma() {
        return 1.4;
    }
}
