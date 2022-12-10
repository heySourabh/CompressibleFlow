package in.spbhat.physics;

import in.spbhat.util.Formatter;

import static in.spbhat.physics.Speed.Units.*;

public class Speed {
    enum Units {
        m_s(1.0, "m/s"),
        km_h(1.0 / 3.6, "km/h"),
        ft_s(0.3048, "ft/s");

        private final double conversion;
        private final String unitStr;

        Units(double conversion, String unitStr) {
            this.conversion = conversion;
            this.unitStr = unitStr;
        }

        @Override
        public String toString() {
            return unitStr;
        }
    }

    private final double value;
    private final Units units;

    public Speed(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    public double in(Units units) {
        double speed_m_s = this.value * this.units.conversion;
        return speed_m_s / units.conversion;
    }

    public Speed to(Units units) {
        return new Speed(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units.unitStr;
    }

    public static void main(String[] args) {
        System.out.println(new Speed(123, m_s));
        System.out.println(new Speed(123, m_s).to(ft_s));
        System.out.println(new Speed(4.5, ft_s).to(m_s));
        System.out.println(new Speed(234, km_h).to(m_s));
        System.out.println(new Speed(65.47, m_s).to(km_h));
        System.out.println(new Speed(862.6, km_h).to(ft_s));
    }
}
