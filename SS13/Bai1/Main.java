package Bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main{
    public void capPhatThuoc(int medicineId,int patientId){
        Connection conn=null;
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        try{
            conn=DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            String sql1="UPDATE Medicine_Inventory SET quantity=quantity-1 WHERE medicine_id=?";
            ps1=conn.prepareStatement(sql1);
            ps1.setInt(1,medicineId);
            ps1.executeUpdate();

            String sql2="INSERT INTO Prescription_History(patient_id,medicine_id,date) VALUES(?,?,NOW())";
            ps2=conn.prepareStatement(sql2);
            ps2.setInt(1,patientId);
            ps2.setInt(2,medicineId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Cap phat thuoc thanh cong");
        }catch(Exception e){
            try{if(conn!=null)conn.rollback();}catch(Exception ex){}
            System.out.println("Co loi xay ra: "+e.getMessage());
        }finally{
            try{
                if(ps1!=null)ps1.close();
                if(ps2!=null)ps2.close();
                if(conn!=null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            }catch(Exception e){}
        }
    }
}
//JDBC mặc định bật Auto-Commit nên lệnh SQL chạy xong sẽ được lưu vào cơ sở dữ liệu
// lệnh thuốc đã commit thao tác ghi lịch sử (ps2.executeUpdate()) không được thực hiện
