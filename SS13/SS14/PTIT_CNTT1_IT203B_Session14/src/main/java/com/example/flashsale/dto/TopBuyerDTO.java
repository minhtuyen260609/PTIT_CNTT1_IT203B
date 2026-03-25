package com.example.flashsale.dto;

/**
 * TopBuyerDTO - DTO để hiển thị top 5 người dùng mua nhiều nhất
 * Dùng cho Stored Procedure SP_GetTopBuyers
 */
public class TopBuyerDTO {
    private int userId;
    private String username;
    private String email;
    private long totalQuantityBought;
    private int totalOrders;

    public TopBuyerDTO() {
    }

    public TopBuyerDTO(int userId, String username, String email, long totalQuantityBought, int totalOrders) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.totalQuantityBought = totalQuantityBought;
        this.totalOrders = totalOrders;
    }

    // Getters & Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTotalQuantityBought() {
        return totalQuantityBought;
    }

    public void setTotalQuantityBought(long totalQuantityBought) {
        this.totalQuantityBought = totalQuantityBought;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    @Override
    public String toString() {
        return "TopBuyerDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", totalQuantityBought=" + totalQuantityBought +
                ", totalOrders=" + totalOrders +
                '}';
    }
}
