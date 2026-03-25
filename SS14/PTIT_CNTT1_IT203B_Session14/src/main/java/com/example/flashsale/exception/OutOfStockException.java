package com.example.flashsale.exception;

/**
 * OutOfStockException - Custom Exception khi sản phẩm hết hàng
 */
public class OutOfStockException extends Exception {
    private int productId;
    private int requestedQuantity;
    private int availableQuantity;

    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfStockException(String message, int productId, int requestedQuantity, int availableQuantity) {
        super(message);
        this.productId = productId;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    // Getters
    public int getProductId() {
        return productId;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    @Override
    public String toString() {
        return "OutOfStockException{" +
                "message='" + getMessage() + '\'' +
                ", productId=" + productId +
                ", requestedQuantity=" + requestedQuantity +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
