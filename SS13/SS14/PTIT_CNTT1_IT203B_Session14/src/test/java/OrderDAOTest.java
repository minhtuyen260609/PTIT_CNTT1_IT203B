package com.example.flashsale.dao;

import com.example.flashsale.constant.SQLConstants;
import com.example.flashsale.dto.OrderItemRequest;
import com.example.flashsale.exception.DataAccessException;

import java.sql.*;
import java.util.List;

public class OrderDAO {
    public int createOrder(Connection conn, int userId, String status) throws DataAccessException {
        try (PreparedStatement ps = conn.prepareStatement(
                SQLConstants.INSERT_ORDER,
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.setString(2, status);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Creating order failed, no rows affected.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // order_id
                } else {
                    throw new DataAccessException("Creating order failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error while creating order", e);
        }
    }
    public void insertOrderDetailsBatch(Connection conn,
                                        int orderId,
                                        List<OrderItemRequest> items,
                                        List<Double> prices) throws DataAccessException {

        try (PreparedStatement ps = conn.prepareStatement(
                SQLConstants.INSERT_ORDER_DETAIL_BATCH)) {

            for (int i = 0; i < items.size(); i++) {
                OrderItemRequest item = items.get(i);

                ps.setInt(1, orderId);
                ps.setInt(2, item.getProductId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, prices.get(i)); // unit_price

                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            throw new DataAccessException("Error while inserting order details batch", e);
        }
    }
    public void updateOrderStatus(Connection conn, int orderId, String status)
            throws DataAccessException {

        try (PreparedStatement ps = conn.prepareStatement(SQLConstants.UPDATE_ORDER_STATUS)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error while updating order status", e);
        }
    }
    public void getOrderById(Connection conn, int orderId) throws DataAccessException {
        try (PreparedStatement ps = conn.prepareStatement(SQLConstants.SELECT_ORDER_BY_ID)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id")
                        + " | User: " + rs.getInt("user_id")
                        + " | Status: " + rs.getString("status"));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error while fetching order", e);
        }
    }
}