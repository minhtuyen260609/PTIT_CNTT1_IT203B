package Bai4;

import Bai4.DatabaseManager;
import java.sql.*;
import java.util.*;

public class DashboardDAO {

    public List<BenhNhanDTO> getDashboard() throws Exception {

        String sql =
                "SELECT bn.maBenhNhan, bn.tenBenhNhan, " +
                        "dv.maDichVu, dv.tenDichVu " +
                        "FROM BenhNhan bn " +
                        "LEFT JOIN DichVuSuDung sd ON bn.maBenhNhan = sd.maBenhNhan " +
                        "LEFT JOIN DichVu dv ON sd.maDichVu = dv.maDichVu";

        Map<Integer, BenhNhanDTO> map = new LinkedHashMap<>();

        Connection conn = DatabaseManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int maBN = rs.getInt("maBenhNhan");

            BenhNhanDTO bn = map.get(maBN);
            if (bn == null) {
                bn = new BenhNhanDTO(
                        maBN,
                        rs.getString("tenBenhNhan")
                );
                map.put(maBN, bn);
            }

            // bẫy 2
            int maDV = rs.getInt("maDichVu");
            if (!rs.wasNull()) {
                bn.dsDichVu.add(
                        new DichVu(
                                maDV,
                                rs.getString("tenDichVu")
                        )
                );
            }
        }

        rs.close();
        ps.close();
        conn.close();

        return new ArrayList<>(map.values());
    }
}
