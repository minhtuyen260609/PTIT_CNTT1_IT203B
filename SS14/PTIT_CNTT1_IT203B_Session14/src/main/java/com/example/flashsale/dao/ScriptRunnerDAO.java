package com.example.flashsale.dao;

import com.example.flashsale.exception.DataAccessException;
import com.example.flashsale.utils.DatabaseConnectionManager;
import com.example.flashsale.utils.SQLFileReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * ScriptRunnerDAO - Chạy SQL scripts khởi tạo database từ file
 * Sử dụng Statement vì các SQL scripts là tĩnh, không có tham số động
 * Dùng cho: CREATE TABLE, INSERT data, Stored Procedures
 */
public class ScriptRunnerDAO {
    private static final String SCHEMA_FILE = "sql/schema.sql";
    private static final String DATA_FILE = "sql/data.sql";
    private static final String PROCEDURES_FILE = "sql/procedures.sql";

    private final DatabaseConnectionManager connectionManager;

    public ScriptRunnerDAO() {
        this.connectionManager = DatabaseConnectionManager.getInstance();
    }

    /**
     * Khởi tạo database schema (tạo bảng)
     */
    public void initializeSchema() throws DataAccessException {
        runSQLScript(SCHEMA_FILE, "Initializing Database Schema");
    }

    /**
     * Thêm dữ liệu mẫu vào database
     */
    public void insertSampleData() throws DataAccessException {
        runSQLScript(DATA_FILE, "Inserting Sample Data");
    }

    /**
     * Tạo Stored Procedures
     */
    public void createStoredProcedures() throws DataAccessException {
        runSQLScript(PROCEDURES_FILE, "Creating Stored Procedures");
    }

    /**
     * Khởi tạo toàn bộ database (schema + data + procedures)
     */
    public void initializeDatabase() throws DataAccessException {
        try {
            System.out.println("[INFO] Starting database initialization...");

            initializeSchema();
            System.out.println("[SUCCESS] Schema initialized");

            insertSampleData();
            System.out.println("[SUCCESS] Sample data inserted");

            createStoredProcedures();
            System.out.println("[SUCCESS] Stored procedures created");

            System.out.println("[SUCCESS] Database initialization completed!");
        } catch (DataAccessException e) {
            throw new DataAccessException("Database initialization failed: " + e.getMessage(), e);
        }
    }

    /**
     * Chạy SQL script từ file
     */
    private void runSQLScript(String filePath, String taskName) throws DataAccessException {
        String sqlContent;
        try {
            sqlContent = SQLFileReader.readSQLScript(filePath);
        } catch (IOException e) {
            throw new DataAccessException("Failed to read SQL file: " + filePath, e);
        }

        String[] statements = SQLFileReader.splitStatements(sqlContent);

        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            connection.setAutoCommit(true); // Auto-commit cho init scripts

            try (Statement statement = connection.createStatement()) {
                for (String sql : statements) {
                    if (sql.trim().isEmpty())
                        continue;

                    try {
                        // Xử lý DELIMITER (nếu có trong file)
                        if (sql.toLowerCase().startsWith("delimiter")) {
                            continue; // Bỏ qua DELIMITER statements
                        }

                        statement.execute(sql);
                    } catch (SQLException e) {
                        // Log error nhưng tiếp tục chạy các statement khác
                        // (VD: CREATE TABLE nếu đã tồn tại sẽ báo lỗi nhưng không block)
                        if (e.getMessage().contains("already exists")) {
                            System.out.println("[WARNING] " + e.getMessage());
                        } else {
                            throw e;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to execute SQL script: " + taskName, e);
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    /**
     * Kiểm tra database connection (test kết nối)
     */
    public boolean testDatabaseConnection() {
        try {
            Connection connection = connectionManager.getConnection();
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery("SELECT 1");
            }
            connectionManager.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("[ERROR] Database connection failed: " + e.getMessage());
            return false;
        }
    }
}
