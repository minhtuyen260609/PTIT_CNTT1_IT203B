package Bai5;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/hospital";
        String user = "root";
        String pass = "123456";
        return DriverManager.getConnection(url, user, pass);
    }
}
