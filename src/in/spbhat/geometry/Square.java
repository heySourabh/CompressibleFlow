/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.geometry;

public record Square(Length side) {
    public Area area() {
        double side_m = side.in(Length.Units.m);
        return new Area(side_m * side_m, Area.Units.sq_m);
    }
}
