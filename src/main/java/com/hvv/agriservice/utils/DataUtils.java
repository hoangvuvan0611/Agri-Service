package com.hvv.agriservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class DataUtils {

    public static int safeToInt(String input, int defaultValue) {
        if (input == null || input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("STID000-1: Loi khi convert String to int: " + e);
            return defaultValue;
        }
    }

    public static int safeToInt(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("STI000-1: Loi khi convert String to int: " + e);
            return 0;
        }
    }

    public static long safeToLong(String input, long defaultValue) {
        if (input == null || input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("STLD000-1: Loi khi convert String to long: " + e);
            return defaultValue;
        }
    }

    public static long safeToLong(Object input) {
        String inputStr = String.valueOf(input.toString());
        if (inputStr == null || inputStr.isEmpty()) {
            return 0L;
        }

        try {
            return Long.parseLong(inputStr);
        } catch (NumberFormatException e) {
            System.out.println("STL000-1: Loi khi convert String to long: " + e);
            return 0L;
        }
    }

    public static double safeToDouble(String input, double defaultValue) {
        if (input == null || input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("STDD000-1: Loi khi convert String to double: " + e);
            return defaultValue;
        }
    }


    public static double safeToDouble(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("STD000-1: Loi khi thuc hien convert String to double");
            return 0;
        }
    }


    public static BigDecimal safeToBigDecimal(String input, BigDecimal defaultValue) {
        if (input == null || input.isEmpty()) {
            return defaultValue;
        }

        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            System.out.println("STBDD000-1: Loi khi thuc hien convert String to BigDecimal: " + e);
            return defaultValue;
        }
    }

    public static BigDecimal safeToBigDecimal(String input) {
        if (input == null || input.isEmpty()) {
            return BigDecimal.valueOf(0);
        }

        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            System.out.println("STBD000-1: Loi khi thuc hien convert String to BigDecimal: " + e);
            return BigDecimal.valueOf(0);
        }
    }
}
