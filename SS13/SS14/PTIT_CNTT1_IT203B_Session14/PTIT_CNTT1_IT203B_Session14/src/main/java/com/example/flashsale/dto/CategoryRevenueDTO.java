package com.example.flashsale.dto;

import java.math.BigDecimal;

/**
 * CategoryRevenueDTO - DTO để hiển thị doanh thu theo danh mục sản phẩm
 * Dùng cho Stored Procedure FUNC_CalculateCategoryRevenue
 */
public class CategoryRevenueDTO {
    private String category;
    private BigDecimal totalRevenue;

    public CategoryRevenueDTO() {
    }

    public CategoryRevenueDTO(String category, BigDecimal totalRevenue) {
        this.category = category;
        this.totalRevenue = totalRevenue;
    }

    // Getters & Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "CategoryRevenueDTO{" +
                "category='" + category + '\'' +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
