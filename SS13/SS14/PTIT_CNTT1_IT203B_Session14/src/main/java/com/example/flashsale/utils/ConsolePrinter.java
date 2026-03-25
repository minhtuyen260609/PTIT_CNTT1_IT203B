package com.example.flashsale.utils;

import com.example.flashsale.constant.AppConstants;

/**
 * ConsolePrinter - Tiện ích in thông tin ra console với format đẹp
 */
public class ConsolePrinter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";

    /**
     * In tiêu đề
     */
    public static void printTitle(String title) {
        System.out.println(AppConstants.SEPARATOR);
        System.out.println(ANSI_BLUE + title + ANSI_RESET);
        System.out.println(AppConstants.SEPARATOR);
    }

    /**
     * In thông báo thành công (màu xanh)
     */
    public static void printSuccess(String message) {
        System.out.println(ANSI_GREEN + "✓ " + message + ANSI_RESET);
    }

    /**
     * In thông báo lỗi (màu đỏ)
     */
    public static void printError(String message) {
        System.out.println(ANSI_RED + "✗ " + message + ANSI_RESET);
    }

    /**
     * In thông báo cảnh báo (màu vàng)
     */
    public static void printWarning(String message) {
        System.out.println(ANSI_YELLOW + "⚠ " + message + ANSI_RESET);
    }

    /**
     * In thông báo thông thường (màu cyan)
     */
    public static void printInfo(String message) {
        System.out.println(ANSI_CYAN + "ℹ " + message + ANSI_RESET);
    }

    /**
     * In dòng separator
     */
    public static void printSeparator() {
        System.out.println(AppConstants.SEPARATOR);
    }

    /**
     * In bảng dữ liệu
     */
    public static void printTable(String[] headers, String[][] data) {
        int[] columnWidths = new int[headers.length];

        // Tính độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }

        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());
            }
        }

        // In header
        for (int i = 0; i < headers.length; i++) {
            System.out.print(String.format("%-" + columnWidths[i] + "s | ", headers[i]));
        }
        System.out.println();

        // In separator
        for (int width : columnWidths) {
            System.out.print("-".repeat(width) + " + ");
        }
        System.out.println();

        // In data
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(String.format("%-" + columnWidths[i] + "s | ", row[i]));
            }
            System.out.println();
        }
    }
}
