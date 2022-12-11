package in.spbhat.gas.equations;

import in.spbhat.gas.Air;
import in.spbhat.gas.Gas;
import in.spbhat.gas.properties.Pressure;
import in.spbhat.gas.properties.Temperature;

import static in.spbhat.gas.properties.Pressure.Units.Pa;
import static in.spbhat.gas.properties.Temperature.Units.C;
import static java.lang.Math.pow;

public class IsentropicFlow {
    private final double gamma;

    public IsentropicFlow(Gas gas) {
        this.gamma = gas.gamma();
    }

    public double p2_by_p1(double T2_by_T1) {
        return pow(T2_by_T1, gamma / (gamma - 1));
    }

    public Pressure p2(Pressure p1, double T2_T1) {
        return p1.times(p2_by_p1(T2_T1));
    }

    public double T2_by_T1(double p2_by_p1) {
        return pow(p2_by_p1, (gamma - 1) / gamma);
    }

    public Temperature T2(Temperature T1, double p2_by_p1) {
        return T1.times(T2_by_T1(p2_by_p1));
    }

    public static void main(String[] args) {
        IsentropicFlow isentropic = new IsentropicFlow(new Air());
        System.out.println(isentropic.p2(new Pressure(101325, Pa), 1.2));
        System.out.println(isentropic.T2(new Temperature(10, C), 1.5));
    }
}
