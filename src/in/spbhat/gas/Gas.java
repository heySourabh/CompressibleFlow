package in.spbhat.gas;

import in.spbhat.gas.constants.Cp;
import in.spbhat.gas.constants.Cv;
import in.spbhat.gas.constants.R;

import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;

public interface Gas {
    R R();

    double gamma();

    default Cp cp() {
        return new Cp(gamma() * cv().in(J_kgK), J_kgK);
    }

    default Cv cv() {
        return new Cv(R().in(J_kgK) / (gamma() - 1.0), J_kgK);
    }
}
