package in.spbhat.geometry;

public record Square(Length side) {
    public Area area() {
        double side_m = side.in(Length.Units.m);
        return new Area(side_m * side_m, Area.Units.sq_m);
    }

    public static void main(String[] args) {
        Square square = new Square(new Length(9.5, Length.Units.inches));
        System.out.println(square);
        System.out.println(square.area());
    }
}
