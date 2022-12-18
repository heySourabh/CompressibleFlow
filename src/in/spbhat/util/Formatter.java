/*
 * Copyright (c) 2022.
 * author: Sourabh Bhat <heySourabh@gmail.com>
 */

package in.spbhat.util;

public class Formatter {
    public static String doubleToString(double value, int numSignificantDigits) {
        String formatStr = String.format("%%.%dg", numSignificantDigits);
        String valueStr = String.format(formatStr, value);
        return removeTrailingZeros(valueStr);
    }

    public static String doubleToString(double value) {
        return doubleToString(value, 6);
    }

    private static String removeTrailingZeros(String doubleValueStr) {
        if (doubleValueStr.contains("e") || doubleValueStr.contains("E")) {
            String[] parts = doubleValueStr.split("[eE]");
            return removeTrailingZeros(parts[0]) + "e" + parts[1];
        }

        if (doubleValueStr.contains(".") && doubleValueStr.endsWith("0")) {
            return removeTrailingZeros(doubleValueStr.substring(0, doubleValueStr.length() - 1));
        } else if (doubleValueStr.endsWith(".")) {
            return doubleValueStr.substring(0, doubleValueStr.length() - 1);
        } else {
            return doubleValueStr;
        }
    }
}
