package Bai3;
import java.sql.*;

public class Main {
    public void xuatVienVaThanhToan(int maBenhNhan,double tienVienPhi){
        Connection conn=null;
        PreparedStatement psCheck=null;
        PreparedStatement psTruTien=null;
        PreparedStatement psGiuong=null;
        PreparedStatement psBenhNhan=null;
        ResultSet rs=null;

        try{
            conn=DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            String sqlCheck="SELECT so_du FROM TamUng WHERE ma_benh_nhan=?";
            psCheck=conn.prepareStatement(sqlCheck);
            psCheck.setInt(1,maBenhNhan);
            rs=psCheck.executeQuery();

            if(!rs.next()) throw new Exception("Khong tim thay benh nhan");

            double soDu=rs.getDouble("so_du");

            // bẫy 1
            if(soDu<tienVienPhi) throw new Exception("So du khong du thanh toan");

            String sqlTruTien="UPDATE TamUng SET so_du=so_du-? WHERE ma_benh_nhan=?";
            psTruTien=conn.prepareStatement(sqlTruTien);
            psTruTien.setDouble(1,tienVienPhi);
            psTruTien.setInt(2,maBenhNhan);
            int r1=psTruTien.executeUpdate();

            //bẫy 2
            if(r1==0) throw new Exception("Tru tien that bai");

            String sqlGiuong="UPDATE GiuongBenh SET trang_thai='TRONG' WHERE ma_benh_nhan=?";
            psGiuong=conn.prepareStatement(sqlGiuong);
            psGiuong.setInt(1,maBenhNhan);
            int r2=psGiuong.executeUpdate();

            // bẫy 2
            if(r2==0) throw new Exception("Cap nhat giuong that bai");

            String sqlBenhNhan="UPDATE BenhNhan SET trang_thai='DA_XUAT_VIEN' WHERE ma_benh_nhan=?";
            psBenhNhan=conn.prepareStatement(sqlBenhNhan);
            psBenhNhan.setInt(1,maBenhNhan);
            int r3=psBenhNhan.executeUpdate();

            // bẫy 2
            if(r3==0) throw new Exception("Cap nhat benh nhan that bai");

            conn.commit();
            System.out.println("Xuat vien va thanh toan thanh cong");

        }catch(Exception e){
            try{if(conn!=null)conn.rollback();}catch(Exception ex){}
            System.out.println("Giao dich that bai: "+e.getMessage());
        }finally{
            try{
                if(rs!=null)rs.close();
                if(psCheck!=null)psCheck.close();
                if(psTruTien!=null)psTruTien.close();
                if(psGiuong!=null)psGiuong.close();
                if(psBenhNhan!=null)psBenhNhan.close();
                if(conn!=null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            }catch(Exception e){}
        }
    }
}
//1
//input
//hàm nhận vào
//maBenhNhan (int): mã bệnh nhân cần xuất viện
//tienVienPhi (double): số tiền viện phí cần thanh toán
//output:
//nếu thao tác thành công thông báo xuất viện và thanh toán hoàn tất
//nếu xảy ra lỗi giao dịch bị hủy dữ liệu giữ nguyên thông báo thất bại
//2
//3 thao tác (trừ tiền, trả giường, cập nhật bệnh nhân) thuộc cùng một nghiệp vụ nên phải cho chạy trong cùng một transaction
//dùng JDBC tắt AutoCommit để gom các lệnh SQL lại
//nếu tất cả lệnh chạy thành công thì commit
//nếu có lỗi ở bất kỳ bước nào thì rollback để dữ liệu quay về ban đầu
//3
//mở kết nối database
//tắt AutoCommit
//lấy số dư tạm ứng của bệnh nhân
//nếu số dư < tiền viện phí → báo lỗi
//trừ tiền tạm ứng
//cập nhật trạng thái giường bệnh
//cập nhật trạng thái bệnh nhân
//nếu có bước nào không cập nhật được dữ liệu → báo lỗi
//nếu mọi thứ ổn → commit
//nếu có lỗi → rollback
//dóng kết nối