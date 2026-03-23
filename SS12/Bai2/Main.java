package Bai2;

public class Main {

//Lỗi hiện tại
//Nối chuỗi số vào SQL:
//"temperature = " + temp
//Máy dùng Locale khác nhau
//Có nơi dùng 37.5, có nơi dùng 37,5
//SQL chỉ chấp nhận dấu .
//Dễ lỗi cú pháp.
//Vì sao setDouble() / setInt() giải quyết được?
//Không nối chuỗi thủ công
//Giá trị số được truyền đúng kiểu dữ liệu
//JDBC tự chuyển sang định dạng chuẩn của DB
//Không phụ thuộc dấu chấm / dấu phẩy hệ điều hành

// String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";
//PreparedStatement ps = conn.prepareStatement(sql);
//ps.setDouble(1, temp);
//ps.setInt(2, heartRate);
//ps.setInt(3, patientId);
//ps.executeUpdate();
}
