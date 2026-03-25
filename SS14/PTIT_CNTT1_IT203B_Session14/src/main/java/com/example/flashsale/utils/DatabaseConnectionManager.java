package com.example.flashsale.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton Database Connection Manager
 * Thread-safe double-checked locking pattern
 */
public class DatabaseConnectionManager {
    private static volatile DatabaseConnectionManager instance;

    private DatabaseConnectionManager() {
        try {
            Class.forName(DBConfig.get("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    /**
     * Double-checked locking - Thread-safe Singleton
     */
    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionManager.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    /**
     * Lấy connection từ database
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBConfig.get("db.url"),
                DBConfig.get("db.username"),
                DBConfig.get("db.password"));
    }

    /**
     * Đóng connection
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}