package Bai5;

import java.sql.*;
import java.util.*;
public class DoctorDAO{
    public List<Doctor> findAll() throws Exception{
        List<Doctor> list=new ArrayList<>();
        Connection c=DBContext.getConnection();
        PreparedStatement ps=c.prepareStatement("SELECT * FROM Doctors");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            list.add(new Doctor(rs.getString("id"),rs.getString("name"),rs.getString("specialty")));
        }
        rs.close();ps.close();c.close();
        return list;
    }
    public void insert(Doctor d) throws Exception{
        Connection c=DBContext.getConnection();
        PreparedStatement ps=c.prepareStatement("INSERT INTO Doctors VALUES(?,?,?)");
        ps.setString(1,d.getId());
        ps.setString(2,d.getName());
        ps.setString(3,d.getSpecialty());
        ps.executeUpdate();
        ps.close();c.close();
    }
    public void stats() throws Exception{
        Connection c=DBContext.getConnection();
        PreparedStatement ps=c.prepareStatement("SELECT specialty,COUNT(*) total FROM Doctors GROUP BY specialty");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("specialty")+": "+rs.getInt("total"));
        }
        rs.close();ps.close();c.close();
    }
}
