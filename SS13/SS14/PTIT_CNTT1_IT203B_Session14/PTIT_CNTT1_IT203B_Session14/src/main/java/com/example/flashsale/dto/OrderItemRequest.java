package com.example.flashsale.dto;

import java.io.Serializable;

/**
 * OrderItemRequest - DTO để nhận request đặt hàng từ client
 * Chứa thông tin một line item trong đơn hàng
 */
public class OrderItemRequest implements Serializable {
    private int productId;
    private int quantity;

    public OrderItemRequest() {}

    public OrderItemRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters & Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "OrderItemRequest{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
