package in.spbhat.gas;

import in.spbhat.gas.constants.Cp;
import in.spbhat.gas.constants.Cv;
import in.spbhat.gas.constants.MolarMass;
import in.spbhat.gas.constants.R;

import static in.spbhat.gas.constants.MolarMass.Units.moles_gram;
import static in.spbhat.gas.constants.SpecificHeat.Units.J_kgK;

public interface Gas {

    double gamma();

    MolarMass molarMass();

    default R R() {
        double universalR = 8314.462618; // J/kmol-K
        double RValue = universalR / molarMass().in(moles_gram); // J/kg-K
        return new R(RValue, J_kgK);
    }

    default Cp cp() {
        return new Cp(gamma() * cv().in(J_kgK), J_kgK);
    }

    default Cv cv() {
        return new Cv(R().in(J_kgK) / (gamma() - 1.0), J_kgK);
    }
}
