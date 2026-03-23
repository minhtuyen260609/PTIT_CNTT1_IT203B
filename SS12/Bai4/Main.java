package Bai4;

public class Main {
    //Vấn đề
    //Trong vòng lặp:
    //Mỗi lần tạo Statement
    //DB phải Parse + Compile + tạo Execution Plan lại
    //Nếu 1.000 bản ghi → làm lại 1.000 lần
    //→ Tốn CPU, RAM, thời gian xử lý.
    //Lãng phí chính
    //Cùng cấu trúc SQL
    //Chỉ khác dữ liệu
    //Nhưng DB vẫn phải lập kế hoạch thực thi nhiều lần.
//String sql = "INSERT INTO Results(data) VALUES(?)";
//PreparedStatement ps = conn.prepareStatement(sql);
//
//for (TestResult tr : list) {
//    ps.setString(1, tr.getData());
//    ps.executeUpdate();
//}
}
