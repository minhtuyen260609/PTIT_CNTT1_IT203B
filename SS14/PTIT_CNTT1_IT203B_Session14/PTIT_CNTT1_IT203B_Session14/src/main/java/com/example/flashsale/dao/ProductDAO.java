package com.example.flashsale.dao;

import com.example.flashsale.entity.Product;
import com.example.flashsale.utils.DatabaseConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDAO - Data Access Object cho Product
 * Sử dụng PreparedStatement để thực hiện CRUD operations
 * Phòng chống SQL Injection
 */
public class ProductDAO {
    private static final String INSERT_PRODUCT = "INSERT INTO Products (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM Products WHERE product_id = ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Products";
    private static final String SELECT_PRODUCTS_BY_CATEGORY = "SELECT * FROM Products WHERE category = ?";
    private static final String UPDATE_PRODUCT = "UPDATE Products SET name = ?, category = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM Products WHERE product_id = ?";
    private static final String UPDATE_STOCK = "UPDATE Products SET stock_quantity = stock_quantity - ? WHERE product_id = ? AND stock_quantity >= ?";
    private static final String RESTORE_STOCK = "UPDATE Products SET stock_quantity = stock_quantity + ? WHERE product_id = ?";
    private static final String GET_STOCK = "SELECT stock_quantity FROM Products WHERE product_id = ?";

    private final DatabaseConnectionManager connectionManager;

    public ProductDAO() {
        this.connectionManager = DatabaseConnectionManager.getInstance();
    }

    /**
     * Thêm Product mới
     * 
     * @return ID của product mới tạo, hoặc -1 nếu thất bại
     */
    public int insertProduct(Product product) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getStockQuantity());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } finally {
            connectionManager.closeConnection(connection);
        }
        return -1;
    }

    /**
     * Lấy Product theo ID
     */
    public Product getProductById(int productId) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProduct(resultSet);
                }
            }
        } finally {
            connectionManager.closeConnection(connection);
        }
        return null;
    }

    /**
     * Lấy tất cả Products
     */
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapResultSetToProduct(resultSet));
            }
        } finally {
            connectionManager.closeConnection(connection);
        }
        return products;
    }

    /**
     * Lấy Products theo Category
     */
    public List<Product> getProductsByCategory(String category) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCTS_BY_CATEGORY)) {
            statement.setString(1, category);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapResultSetToProduct(resultSet));
                }
            }
        } finally {
            connectionManager.closeConnection(connection);
        }
        return products;
    }

    /**
     * Cập nhật Product
     */
    public boolean updateProduct(Product product) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getStockQuantity());
            statement.setInt(5, product.getProductId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    /**
     * Xóa Product theo ID
     */
    public boolean deleteProduct(int productId) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setInt(1, productId);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    /**
     * Trừ stock cho một product (dùng trong transaction)
     * 
     * @return true nếu còn hàng, false nếu hết hàng
     */
    public boolean deductStock(Connection connection, int productId, int quantity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STOCK)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Hoàn trả stock (rollback stock) - dùng trong transaction
     */
    public void restoreStock(Connection connection, int productId, int quantity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(RESTORE_STOCK)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }

    /**
     * Lấy stock hiện tại của product
     */
    public int getStockQuantity(Connection connection, int productId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_STOCK)) {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("stock_quantity");
                }
            }
        }
        return -1;
    }

    /**
     * Chuyển ResultSet thành Product object
     */
    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("product_id"));
        product.setName(resultSet.getString("name"));
        product.setCategory(resultSet.getString("category"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setStockQuantity(resultSet.getInt("stock_quantity"));

        return product;
    }
}
