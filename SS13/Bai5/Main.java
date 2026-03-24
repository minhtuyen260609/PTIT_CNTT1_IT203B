package Bai5;

public class Main {
    public static void main(String[] args) {
        LeTan view = new LeTan();
        view.runMenu();
    }
}
//1
//nhập sai kiểu dữ liệu ví dụ gõ chữ vào ô tuổi hoặc tiền
//nhập mã giường không tồn tại
//giường đã có người

//2
//Các bước làm
//nhập tên, tuổi, mã giường, tiền tạm ứng
//kiểm tra nhập có hợp lệ không
//kết nối database
//tắt tự động lưu
//thêm bệnh nhân
//cập nhật giường sang “Đã có người”
//lưu tiền tạm ứng
//nếu mọi bước hoàn tất → lưu dữ liệu
//nếu có lỗi → hủy toàn bộ
//đóng kết nối

//cấu trúc bảng dữ liệu
//BenhNhan: maBenhNhan (INT PK auto), hoTen (VARCHAR), tuoi (INT)
//GiuongBenh: maGiuong (VARCHAR PK), trangThai (VARCHAR) → 'Trống' hoặc 'Đã có người'
//TaiChinh: maGiaoDich (INT PK auto), maBenhNhan (INT), soTienTamUng (DECIMAL)