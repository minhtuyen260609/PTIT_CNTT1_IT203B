package com.example.flashsale.dao;

import com.example.flashsale.entity.User;
import com.example.flashsale.utils.DatabaseConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO - Data Access Object cho User
 * Sử dụng PreparedStatement để thực hiện CRUD operations
 * Phòng chống SQL Injection
 */
public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO Users (username, email) VALUES (?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE user_id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM Users";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
    private static final String UPDATE_USER = "UPDATE Users SET username = ?, email = ? WHERE user_id = ?";
    private static final String DELETE_USER = "DELETE FROM Users WHERE user_id = ?";

    private final DatabaseConnectionManager connectionManager;

    public UserDAO() {
        this.connectionManager = DatabaseConnectionManager.getInstance();
    }

    /**
     * Thêm User mới
     * 
     * @return ID của user mới tạo, hoặc -1 nếu thất bại
     */
    public int insertUser(User user) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());

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
     * Lấy User theo ID
     */
    public User getUserById(int userId) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } finally {
            connectionManager.closeConnection(connection);
        }
        return null;
    }

    /**
     * Lấy User theo username
     */
    public User getUserByUsername(String username) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } finally {
            connectionManager.closeConnection(connection);
        }
        return null;
    }

    /**
     * Lấy tất cả Users
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        } finally {
            connectionManager.closeConnection(connection);
        }
        return users;
    }

    /**
     * Cập nhật User
     */
    public boolean updateUser(User user) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getUserId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    /**
     * Xóa User theo ID
     */
    public boolean deleteUser(int userId) throws SQLException {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, userId);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    /**
     * Chuyển ResultSet thành User object
     */
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));

        Timestamp timestamp = resultSet.getTimestamp("created_at");
        if (timestamp != null) {
            user.setCreatedAt(timestamp.toLocalDateTime());
        }

        return user;
    }
}
