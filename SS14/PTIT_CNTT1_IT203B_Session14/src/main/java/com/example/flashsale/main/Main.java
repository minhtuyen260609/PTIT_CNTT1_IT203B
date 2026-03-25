package com.example.flashsale.main;

import com.example.flashsale.constant.AppConstants;
import com.example.flashsale.dao.ProductDAO;
import com.example.flashsale.dao.ScriptRunnerDAO;
import com.example.flashsale.dao.UserDAO;
import com.example.flashsale.entity.Product;
import com.example.flashsale.entity.User;
import com.example.flashsale.exception.DataAccessException;
import com.example.flashsale.utils.ConsolePrinter;
import com.example.flashsale.utils.DatabaseConnectionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Main - Entry point của ứng dụng Flash Sale
 * Demo tất cả tính năng DAO operations (CRUD)
 */
public class Main {
    private static final DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
    private static final ScriptRunnerDAO scriptRunnerDAO = new ScriptRunnerDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final ProductDAO productDAO = new ProductDAO();

    public static void main(String[] args) {
        try {
            ConsolePrinter.printTitle("FLASH SALE E-COMMERCE SYSTEM");

            // 1. Test Database Connection
            testDatabaseConnection();

            // 2. Initialize Database (Schema, Data, Procedures)
            initializeDatabase();

            // 3. Test UserDAO CRUD Operations
            testUserDAOOperations();

            // 4. Test ProductDAO CRUD Operations
            testProductDAOOperations();

            ConsolePrinter.printSuccess("All operations completed successfully!");

        } catch (Exception e) {
            ConsolePrinter.printError("Application failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test database connection
     */
    private static void testDatabaseConnection() throws SQLException {
        ConsolePrinter.printTitle("Testing Database Connection");

        boolean connected = scriptRunnerDAO.testDatabaseConnection();
        if (connected) {
            ConsolePrinter.printSuccess("Database connection OK!");
        } else {
            ConsolePrinter.printError("Database connection FAILED!");
            throw new SQLException("Cannot connect to database. Check db.properties configuration.");
        }
    }

    /**
     * Initialize database schema, data, and procedures
     */
    private static void initializeDatabase() throws DataAccessException {
        ConsolePrinter.printTitle("Initializing Database");

        try {
            scriptRunnerDAO.initializeDatabase();
            ConsolePrinter.printSuccess("Database initialized successfully!");
        } catch (DataAccessException e) {
            ConsolePrinter.printWarning("Database initialization warning: " + e.getMessage());
            // Lỗi có thể do bảng đã tồn tại - không block, tiếp tục
        }
    }

    /**
     * Test UserDAO CRUD operations
     */
    private static void testUserDAOOperations() throws SQLException {
        ConsolePrinter.printTitle("Testing UserDAO - CRUD Operations");

        // INSERT - Thêm user mới
        ConsolePrinter.printInfo("1. Testing INSERT USER");
        User newUser = new User("testuser123", "testuser@email.com");
        int newUserId = userDAO.insertUser(newUser);
        if (newUserId > 0) {
            ConsolePrinter.printSuccess("User inserted with ID: " + newUserId);
        }

        // SELECT BY ID
        ConsolePrinter.printInfo("2. Testing SELECT USER BY ID");
        User user = userDAO.getUserById(1);
        if (user != null) {
            ConsolePrinter.printSuccess("User found: " + user);
        } else {
            ConsolePrinter.printWarning("User not found");
        }

        // SELECT BY USERNAME
        ConsolePrinter.printInfo("3. Testing SELECT USER BY USERNAME");
        User foundUser = userDAO.getUserByUsername("alice");
        if (foundUser != null) {
            ConsolePrinter.printSuccess("User found by username: " + foundUser);
        }

        // SELECT ALL
        ConsolePrinter.printInfo("4. Testing SELECT ALL USERS");
        List<User> allUsers = userDAO.getAllUsers();
        ConsolePrinter.printSuccess("Total users in database: " + allUsers.size());

        // UPDATE
        ConsolePrinter.printInfo("5. Testing UPDATE USER");
        if (newUserId > 0) {
            User userToUpdate = new User(newUserId, "testuser_updated", "updated@email.com", null);
            boolean updated = userDAO.updateUser(userToUpdate);
            if (updated) {
                ConsolePrinter.printSuccess("User updated successfully");
            }
        }

        // DELETE
        ConsolePrinter.printInfo("6. Testing DELETE USER");
        if (newUserId > 0) {
            boolean deleted = userDAO.deleteUser(newUserId);
            if (deleted) {
                ConsolePrinter.printSuccess("User deleted successfully");
            }
        }
    }

    /**
     * Test ProductDAO CRUD operations
     */
    private static void testProductDAOOperations() throws SQLException {
        ConsolePrinter.printTitle("Testing ProductDAO - CRUD Operations");

        // INSERT - Thêm product mới
        ConsolePrinter.printInfo("1. Testing INSERT PRODUCT");
        Product newProduct = new Product(
                "Test Laptop",
                "Laptop",
                new BigDecimal("25000000"),
                5);
        int newProductId = productDAO.insertProduct(newProduct);
        if (newProductId > 0) {
            ConsolePrinter.printSuccess("Product inserted with ID: " + newProductId);
        }

        // SELECT BY ID
        ConsolePrinter.printInfo("2. Testing SELECT PRODUCT BY ID");
        Product product = productDAO.getProductById(1);
        if (product != null) {
            ConsolePrinter.printSuccess("Product found: " + product);
        } else {
            ConsolePrinter.printWarning("Product not found");
        }

        // SELECT ALL
        ConsolePrinter.printInfo("3. Testing SELECT ALL PRODUCTS");
        List<Product> allProducts = productDAO.getAllProducts();
        ConsolePrinter.printSuccess("Total products in database: " + allProducts.size());

        // SELECT BY CATEGORY
        ConsolePrinter.printInfo("4. Testing SELECT PRODUCTS BY CATEGORY");
        List<Product> laptops = productDAO.getProductsByCategory("Laptop");
        ConsolePrinter.printSuccess("Total Laptop products: " + laptops.size());

        // UPDATE
        ConsolePrinter.printInfo("5. Testing UPDATE PRODUCT");
        if (newProductId > 0) {
            Product productToUpdate = new Product(
                    newProductId,
                    "Test Laptop Updated",
                    "Laptop",
                    new BigDecimal("26000000"),
                    10);
            boolean updated = productDAO.updateProduct(productToUpdate);
            if (updated) {
                ConsolePrinter.printSuccess("Product updated successfully");
            }
        }

        // DELETE
        ConsolePrinter.printInfo("6. Testing DELETE PRODUCT");
        if (newProductId > 0) {
            boolean deleted = productDAO.deleteProduct(newProductId);
            if (deleted) {
                ConsolePrinter.printSuccess("Product deleted successfully");
            }
        }

        ConsolePrinter.printInfo("7. Testing GET STOCK QUANTITY");
        try {
            var connection = dbManager.getConnection();
            int stock = productDAO.getStockQuantity(connection, 1);
            ConsolePrinter.printSuccess("Stock of product 1: " + stock);
            dbManager.closeConnection(connection);
        } catch (SQLException e) {
            ConsolePrinter.printWarning("Cannot get stock: " + e.getMessage());
        }
    }
}