/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

public record Circle(Length radius) {
    public Area area() {
        double radius_m = radius.in(Length.Units.m);
        double area_sqm = Math.PI * radius_m * radius_m;
        return new Area(area_sqm, Area.Units.sq_m);
    }

    public static void main(String[] args) {
        Length radius = new Length(1.6, Length.Units.cm);
        Circle circle = new Circle(radius);
        System.out.println(circle);
        System.out.println(circle.area());
    }
}
