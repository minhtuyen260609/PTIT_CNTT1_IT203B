package Bai1;

//Lỗi SQL Injection
//Code nối chuỗi trực tiếp dữ liệu người dùng vào câu SQL → kẻ xấu chèn thêm điều kiện luôn đúng:
//Ví dụ nhập mật khẩu:
//' OR '1'='1
//→ Đăng nhập trái phép.
// Vì sao PreparedStatement an toàn?
//SQL và dữ liệu tách riêng
//Dấu ? là tham số, không phải cú pháp SQL
//DB biên dịch sẵn câu lệnh (pre-compiled)
//Dữ liệu nhập vào chỉ được coi là giá trị
// Không thể chèn lệnh SQL độc hại.

//String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";
//PreparedStatement ps = conn.prepareStatement(sql);
//ps.setString(1, code);
//ps.setString(2, pass);
//ResultSet rs = ps.executeQuery();
