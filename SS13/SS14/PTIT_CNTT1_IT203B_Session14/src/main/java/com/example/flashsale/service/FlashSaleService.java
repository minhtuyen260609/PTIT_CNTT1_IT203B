package com.example.flashsale.service;

import com.example.flashsale.dao.OrderDAO;
import com.example.flashsale.dao.ProductDAO;
import com.example.flashsale.dto.OrderItemRequest;
import com.example.flashsale.exception.DataAccessException;
import com.example.flashsale.exception.OrderProcessingException;
import com.example.flashsale.exception.OutOfStockException;
import com.example.flashsale.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.TransactionIsolation;
import java.util.ArrayList;
import java.util.List;

public class FlashSaleService {

    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();

    public void placeOrder(int userId, List<OrderItemRequest> items)
            throws OrderProcessingException, OutOfStockException {

        Connection conn = null;

        try {
            conn = dbManager.getConnection();

            // ===== 1. TẮT AUTO COMMIT =====
            conn.setAutoCommit(false);

            // ===== 2. SET ISOLATION LEVEL =====
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // ===== 3. CREATE ORDER =====
            int orderId = orderDAO.createOrder(conn, userId, "PENDING");

            List<Double> prices = new ArrayList<>();

            // ===== 4. XỬ LÝ TỪNG ITEM =====
            for (OrderItemRequest item : items) {

                // 4.1 Trừ stock (atomic check)
                boolean success = productDAO.decreaseStock(
                        conn,
                        item.getProductId(),
                        item.getQuantity()
                );

                if (!success) {
                    throw new OutOfStockException(
                            "Product " + item.getProductId() + " is out of stock"
                    );
                }

                // 4.2 Lấy giá sản phẩm
                double price = productDAO.getProductPrice(conn, item.getProductId());
                prices.add(price);
            }

            // ===== 5. INSERT ORDER DETAILS (BATCH) =====
            orderDAO.insertOrderDetailsBatch(conn, orderId, items, prices);

            // ===== 6. UPDATE STATUS =====
            orderDAO.updateOrderStatus(conn, orderId, "SUCCESS");

            // ===== 7. COMMIT =====
            conn.commit();

        } catch (OutOfStockException e) {
            rollbackQuietly(conn);
            throw e;

        } catch (DataAccessException | SQLException e) {
            rollbackQuietly(conn);
            throw new OrderProcessingException("Error processing order", e);

        } finally {
            dbManager.closeConnection(conn);
        }
    }

    private void rollbackQuietly(Connection conn) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ex) {
            System.err.println("Rollback failed: " + ex.getMessage());
        }
    }
}