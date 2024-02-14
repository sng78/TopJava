package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T start, T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

    public static LocalDateTime getStartDate(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay() : LocalDateTime.MIN;
    }

    public static LocalDateTime getEndDate(LocalDate localDate) {
        return localDate != null ? localDate.plusDays(1).atStartOfDay() : LocalDateTime.MAX;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
