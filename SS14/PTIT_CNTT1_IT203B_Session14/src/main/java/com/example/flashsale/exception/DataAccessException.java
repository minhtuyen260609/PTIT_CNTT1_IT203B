package com.example.flashsale.exception;

/**
 * DataAccessException - Custom Exception cho Database Access Layer
 */
public class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
