package Bai1;

//Lỗi SQL Injection
//Code nối chuỗi trực tiếp dữ liệu người dùng vào câu SQL 
//Ví dụ nhập mật khẩu:
//OR 1=1
//Đăng nhập trái phép.
// Vì sao PreparedStatement an toàn?
//SQL và dữ liệu tách riêng
//Dấu ? là tham số không phải cú pháp SQL

//String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";
//PreparedStatement ps = conn.prepareStatement(sql);
//ps.setString(1, code);
//ps.setString(2, pass);
//ResultSet rs = ps.executeQuery();
