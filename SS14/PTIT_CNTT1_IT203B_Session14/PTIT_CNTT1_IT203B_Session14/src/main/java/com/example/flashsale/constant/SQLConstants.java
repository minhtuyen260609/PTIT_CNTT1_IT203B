package com.example.flashsale.constant;

/**
 * SQL Constants - Tập hợp tất cả các SQL queries sử dụng trong ứng dụng
 */
public class SQLConstants {
    // ==================== USER QUERIES ====================
    public static final String INSERT_USER = "INSERT INTO Users (username, email) VALUES (?, ?)";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE user_id = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM Users";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
    public static final String UPDATE_USER = "UPDATE Users SET username = ?, email = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM Users WHERE user_id = ?";

    // ==================== PRODUCT QUERIES ====================
    public static final String INSERT_PRODUCT = "INSERT INTO Products (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";
    public static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM Products WHERE product_id = ?";
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Products";
    public static final String SELECT_PRODUCTS_BY_CATEGORY = "SELECT * FROM Products WHERE category = ?";
    public static final String UPDATE_PRODUCT = "UPDATE Products SET name = ?, category = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM Products WHERE product_id = ?";
    public static final String UPDATE_STOCK = "UPDATE Products SET stock_quantity = stock_quantity - ? WHERE product_id = ? AND stock_quantity >= ?";
    public static final String RESTORE_STOCK = "UPDATE Products SET stock_quantity = stock_quantity + ? WHERE product_id = ?";
    public static final String GET_STOCK = "SELECT stock_quantity FROM Products WHERE product_id = ?";

    // ==================== ORDER QUERIES ====================
    public static final String INSERT_ORDER = "INSERT INTO Orders (user_id, status) VALUES (?, ?)";
    public static final String SELECT_ORDER_BY_ID = "SELECT * FROM Orders WHERE order_id = ?";
    public static final String SELECT_ALL_ORDERS = "SELECT * FROM Orders";
    public static final String SELECT_ORDERS_BY_USER = "SELECT * FROM Orders WHERE user_id = ? ORDER BY order_date DESC";
    public static final String UPDATE_ORDER_STATUS = "UPDATE Orders SET status = ? WHERE order_id = ?";

    // ==================== ORDER DETAIL QUERIES ====================
    public static final String INSERT_ORDER_DETAIL = "INSERT INTO Order_Details (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
    public static final String INSERT_ORDER_DETAIL_BATCH = "INSERT INTO Order_Details (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ORDER_DETAIL_BY_ID = "SELECT * FROM Order_Details WHERE detail_id = ?";
    public static final String SELECT_ORDER_DETAILS_BY_ORDER = "SELECT * FROM Order_Details WHERE order_id = ?";

    // ==================== STORED PROCEDURES ====================
    public static final String CALL_GET_TOP_BUYERS = "CALL SP_GetTopBuyers()";
    public static final String CALL_CALCULATE_CATEGORY_REVENUE = "CALL FUNC_CalculateCategoryRevenue(?, ?)";

    // ==================== REPORT QUERIES ====================
    public static final String GET_TOP_CATEGORIES = "SELECT category, SUM(od.quantity * od.unit_price) as total_revenue FROM Order_Details od JOIN Products p ON od.product_id = p.product_id JOIN Orders o ON od.order_id = o.order_id WHERE o.status = 'SUCCESS' GROUP BY category ORDER BY total_revenue DESC";
}
