package com.example.flashsale.entity;

import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private int userId;
    private LocalDateTime orderDate;
    private String status;

    public Order() {
    }

    public Order(int userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public Order(int orderId, int userId, LocalDateTime orderDate, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getters & Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }
}