package com.example.flashsale.constant;

/**
 * AppConstants - Các hằng số ứng dụng
 */
public class AppConstants {
    // Database
    public static final String DEFAULT_TIMEZONE = "UTC";

    // Order Status
    public static final String ORDER_STATUS_SUCCESS = "SUCCESS";
    public static final String ORDER_STATUS_FAILED = "FAILED";
    public static final String ORDER_STATUS_PENDING = "PENDING";

    // Thread Pool
    public static final int THREAD_POOL_SIZE = 50;

    // Output
    public static final String SEPARATOR = "=".repeat(80);
    public static final String DOUBLE_SEPARATOR = "\n" + SEPARATOR + "\n";

    // Error Messages
    public static final String ERR_DB_CONNECTION = "Failed to connect to database";
    public static final String ERR_RECORD_NOT_FOUND = "Record not found";
    public static final String ERR_OUT_OF_STOCK = "Product out of stock";
    public static final String ERR_INSUFFICIENT_STOCK = "Insufficient stock quantity";
    public static final String ERR_SQL_EXECUTION = "SQL execution failed";

    // Success Messages
    public static final String MSG_ORDER_PLACED_SUCCESS = "Order placed successfully";
    public static final String MSG_ORDER_FAILED = "Order failed";
    public static final String MSG_DATA_SAVED = "Data saved successfully";
}
