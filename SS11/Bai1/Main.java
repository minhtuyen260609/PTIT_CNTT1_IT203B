package Bai1;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//public class DBContext{
//    private static final String URL="jdbc:mysql://localhost:3306/Hospital_DB";
//    private static final String USER="root";
//    private static final String PASS="123456";
//    public static Connection getConnection() throws SQLException{
//        return DriverManager.getConnection(URL,USER,PASS);
//    }
//}
//Lập trình viên tạo kết nối CSDL liên tục nhưng không đóng và không quản lý tập trung sẽ gây:
//
//Rò rỉ tài nguyên: Kết nối chiếm RAM và tài nguyên DB nhưng không được giải phóng
//Hết giới hạn kết nối: Vượt max_connections → hệ thống từ chối truy cập
//Treo hệ thống: Ứng dụng bệnh viện không truy xuất được dữ liệu
//Khó bảo trì: Thông tin CSDL hard-code nhiều nơi, khó sửa đổi