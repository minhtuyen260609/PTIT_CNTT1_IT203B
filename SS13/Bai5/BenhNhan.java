package Bai5;

import java.sql.*;

public class BenhNhan{

    public void tiepNhanBenhNhan(String ten, int tuoi, int maGiuong, double tien) {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            conn.setAutoCommit(false);

            // 1. Them benh nhan
            String sql1 = "INSERT INTO BenhNhan(tenBenhNhan, tuoi, maGiuong) VALUES(?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, ten);
            ps1.setInt(2, tuoi);
            ps1.setInt(3, maGiuong);
            ps1.executeUpdate();

            // 2. Cap nhat giuong
            String sql2 = "UPDATE GiuongBenh SET trangThai='DaCoNguoi' " +
                    "WHERE maGiuong=? AND trangThai='Trong'";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, maGiuong);
            int row = ps2.executeUpdate();

            if (row == 0) {
                throw new Exception("Giuong khong hop le hoac da co nguoi");
            }

            // 3. Thu tien tam ung
            String sql3 = "INSERT INTO TaiChinh(maBenhNhan, soTienTamUng, ngayThu) " +
                    "VALUES(LAST_INSERT_ID(),?,NOW())";
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setDouble(1, tien);
            ps3.executeUpdate();

            conn.commit();
            System.out.println("Tiep nhan thanh cong!");

        } catch (Exception e) {
            try { if (conn != null) conn.rollback(); } catch (Exception ex) {}
            System.out.println("That bai: " + e.getMessage());

        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    public void xemGiuongTrong() {
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM GiuongBenh WHERE trangThai='Trong'");
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Danh sach giuong trong:");
            while (rs.next()) {
                System.out.println("- Ma giuong: " + rs.getInt("maGiuong"));
            }

        } catch (Exception e) {
            System.out.println("Loi tai du lieu");
        }
    }
}
