package Bai4;

import java.sql.*;

public class DatabaseManager {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital",
                "root",
                "123456"
        );
    }
}
