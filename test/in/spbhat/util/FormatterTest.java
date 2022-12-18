/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterTest {

    @Test
    void doubleToString_decimals() {
        assertEquals("1.23457", Formatter.doubleToString(1.2345678, 6));
        assertEquals("1.234568", Formatter.doubleToString(1.2345678, 7));
        assertEquals("7.65432", Formatter.doubleToString(7.654321, 6));
        assertEquals("1.2", Formatter.doubleToString(1.200001));
        assertEquals("1457.21", Formatter.doubleToString(1457.214502));
    }

    @Test
    void doubleToString_whole_numbers() {
        assertEquals("12345", Formatter.doubleToString(12345, 6));
        assertEquals("12300", Formatter.doubleToString(12300.0, 7));
        assertEquals("45", Formatter.doubleToString(45.00002, 6));
        assertEquals("4e+06", Formatter.doubleToString(4000000.2, 6));
        assertEquals("4000000", Formatter.doubleToString(4000000.2, 7));

    }

    @Test
    void doubleToString_exponents() {
        assertEquals("1.2e+06", Formatter.doubleToString(1200001));
        assertEquals("-1.2e+06", Formatter.doubleToString(-1200001));
        assertEquals("1.2e-06", Formatter.doubleToString(0.000001200001, 6));
        assertEquals("1.200001e-06", Formatter.doubleToString(0.000001200001, 7));
    }
}