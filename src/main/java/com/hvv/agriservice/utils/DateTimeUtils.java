package com.hvv.agriservice.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // Chuyen doi kieu LocalDate thanh String
    public static String formatLocalDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    // Chuyen doi kieu String thanh LocalDate
    public static LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    // Chuyen doi kieu LocalDateTime thanh String
    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    // Chuyen kieu string thanh kieu localDateTime
    public static LocalDateTime parseLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
    }

    // Them ngay vao LocalDate
    public static LocalDate addDaysToLocalDate(LocalDate date, long days) {
        return date.plusDays(days);
    }

    // Bot ngay trong LocalDateTime
    public static LocalDate substractDaysFromLocalDate(LocalDate date, long days) {
        return date.minusDays(days);
    }

    // Them ngay vao LocalDateTime
    public static LocalDateTime addDaysToLocalDateTime(LocalDateTime dateTime, long days) {
        return dateTime.plusDays(days);
    }

    // Bot ngay trong LocalDateTime
    public static LocalDateTime substractDaysFromLocalDateTime(LocalDateTime dateTime, long days) {
        return dateTime.minusDays(days);
    }

    // Them gio vao LocalDateTime
    public static LocalDateTime addHoursToLocalDateTime(LocalDateTime dateTime, long hours) {
        return dateTime.plusHours(hours);
    }

    // Bot gio tu LocalDateTime
    public static LocalDateTime substractHoursFromLocalDateTime(LocalDateTime dateTime, long hours) {
        return dateTime.minusHours(hours);
    }

    // Tinh so ngay giua 2 ngay LocalDate
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // Tinh so ngay giua 2 ngay LocalDateTime
    public static long daysBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // Tinh so gio giua 2 LocalDateTime
    public static long hoursBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }
}
