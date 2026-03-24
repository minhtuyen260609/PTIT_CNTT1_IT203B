package Bai2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Main {
    public void thanhToanVienPhi(int patientId,int invoiceId,double amount){
        Connection conn=null;
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        try{
            conn=DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            String sqlDeductWallet="UPDATE Patient_Wallet SET balance=balance-? WHERE patient_id=?";
            ps1=conn.prepareStatement(sqlDeductWallet);
            ps1.setDouble(1,amount);
            ps1.setInt(2,patientId);
            ps1.executeUpdate();

            String sqlUpdateInvoice="UPDATE Invoices SET status='PAID' WHERE invoice_id=?";
            ps2=conn.prepareStatement(sqlUpdateInvoice);
            ps2.setInt(1,invoiceId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Thanh toan hoan tat");
        }catch(SQLException e){
            try{
                if(conn!=null)conn.rollback();
            }catch(SQLException ex){
                System.out.println("Rollback that bai: "+ex.getMessage());
            }
            System.out.println("Loi he thong: Khong the hoan tat thanh toan. Chi tiet: "+e.getMessage());
        }finally{
            try{
                if(ps1!=null)ps1.close();
                if(ps2!=null)ps2.close();
                if(conn!=null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            }catch(SQLException e){}
        }
    }
}
//nếu trong khối catch chỉ dùng System.out.println() để in lỗi mà không thực hiện rollback() thì giao dịch đang mở không được hủy đúng cách
//Hành động thiết yếu bị bỏ quên là rollback transaction.
//phải gọi conn.rollback() để hủy toàn bộ thay đổi dữ liệu khi xảy ra SQLException đưa cơ sở dữ liệu về trạng thái ban đầu trước giao dịch.