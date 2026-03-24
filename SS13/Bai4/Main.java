package Bai4;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        DashboardDAO dao = new DashboardDAO();
        List<BenhNhanDTO> ds = dao.getDashboard();

        for (BenhNhanDTO bn : ds) {
            System.out.println("Bệnh nhân: " + bn.tenBenhNhan);
            for (DichVu dv : bn.dsDichVu) {
                System.out.println("   - " + dv.tenDichVu);
            }
        }
    }
}
//1
//input
//không có tham số
//lấy toàn bộ bệnh nhân đang nằm viện trong ngày
//output
//trả về danh sách bệnh nhân
//mỗi bệnh nhân gồm
//thông tin cơ bản
//danh sách thuốc đã dùng

//2
//GP1: Query 1: Lấy danh sách bệnh nhân
//Sau đó lặp từng bệnh nhân
//GP2: Dùng join để lấy bệnh nhân và dịch vụ trong 1 query
//dùng Map để gom nhóm dữ liệu trên Java
//chọn join vì bệnh nhân đông cần tải nhanh
//Bước làm
//Mở kết nối DB
//Chạy câu lệnh JOIN
//Duyệt từng dòng dữ liệu
//Nếu bệnh nhân chưa có → tạo mới
//Nếu có dịch vụ → thêm vào danh sách
//Trả kết quả
//Đóng kết nối