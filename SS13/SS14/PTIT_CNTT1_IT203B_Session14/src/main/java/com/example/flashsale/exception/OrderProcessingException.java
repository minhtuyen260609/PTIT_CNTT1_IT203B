package com.example.flashsale.exception;

/**
 * OrderProcessingException - Custom Exception khi xử lý đơn hàng lỗi
 */
public class OrderProcessingException extends Exception {
    public OrderProcessingException(String message) {
        super(message);
    }

    public OrderProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
