package in.spbhat.geometry;

import in.spbhat.util.Formatter;

import static in.spbhat.geometry.Length.Units.*;

public class Length {
    enum Units {
        m(1.0), km(1e3), cm(1e-2), ft(0.3048), inches(0.0254);

        private final double conversion;

        Units(double conversion) {
            this.conversion = conversion;
        }
    }

    private final double value;
    private final Units units;

    public Length(double value, Units units) {
        this.value = value;
        this.units = units;
    }

    double in(Units units) {
        double length_m = this.value * this.units.conversion;
        return length_m / units.conversion;
    }

    Length to(Units units) {
        return new Length(in(units), units);
    }

    @Override
    public String toString() {
        return Formatter.doubleToString(this.value) + " " + this.units;
    }

    public static void main(String[] args) {
        System.out.println(new Length(5, m));
        System.out.println(new Length(5, m).to(inches));
        System.out.println(new Length(5, m).to(ft));
        System.out.println(new Length(5, ft).to(m));
        System.out.println(new Length(5, ft).to(cm));
        System.out.println(new Length(5, cm).to(m));
        System.out.println(new Length(1.23, km).to(m));
        System.out.println(new Length(10.23, km).to(cm));
        System.out.println(new Length(10.23, ft).to(km));
        System.out.println(new Length(1.23, km).to(ft));
    }
}
