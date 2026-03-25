package com.example.flashsale.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeUtil - Utility class cho Date/Time operations
 */
public class DateTimeUtil {
    private static final DateTimeFormatter FORMATTER_DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter FORMATTER_DATE_ONLY = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter FORMATTER_TIME_ONLY = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Format LocalDateTime to string (yyyy-MM-dd HH:mm:ss)
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null)
            return "";
        return dateTime.format(FORMATTER_DEFAULT);
    }

    /**
     * Format LocalDateTime to date only (yyyy-MM-dd)
     */
    public static String formatDate(LocalDateTime dateTime) {
        if (dateTime == null)
            return "";
        return dateTime.format(FORMATTER_DATE_ONLY);
    }

    /**
     * Format LocalDateTime to time only (HH:mm:ss)
     */
    public static String formatTime(LocalDateTime dateTime) {
        if (dateTime == null)
            return "";
        return dateTime.format(FORMATTER_TIME_ONLY);
    }

    /**
     * Parse string to LocalDateTime
     */
    public static LocalDateTime parse(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty())
            return null;
        return LocalDateTime.parse(dateTimeStr, FORMATTER_DEFAULT);
    }

    /**
     * Get current LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
