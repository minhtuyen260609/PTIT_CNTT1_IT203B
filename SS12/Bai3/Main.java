package Bai3;

public class Main {
    //Lỗi hiện tại
    //
    //Code gọi Stored Procedure nhưng:
    //
    //Chưa đăng ký tham số OUT
    //JDBC không biết kiểu dữ liệu trả về
    //→ Lỗi column index is out of range hoặc không lấy được giá trị.
    //✅ Vì sao phải registerOutParameter()?
    //Tham số OUT là dữ liệu DB trả về
    //JDBC bắt buộc phải:
    //Khai báo vị trí
    //Khai báo kiểu dữ liệu
    //→ Mới đọc được kết quả sau execute()

// CallableStatement cstmt = conn.prepareCall("{call GET_SURGERY_FEE(?, ?)}");
//
//cstmt.setInt(1, 505);
//cstmt.registerOutParameter(2, Types.DECIMAL);
//
//cstmt.execute();
//
//double cost = cstmt.getDouble(2);
//System.out.println(cost);
}
