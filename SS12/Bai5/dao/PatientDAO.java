package Bai5.dao;

import com.rikkei.rhms.model.Patient;
import com.rikkei.rhms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("diagnosis"),
                        rs.getDate("admission_date").toLocalDate()
                ));
            }
        }
        return patients;
    }

    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients(name, age, department, diagnosis, admission_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getDepartment());
            pstmt.setString(4, patient.getDiagnosis());
            pstmt.setDate(5, Date.valueOf(patient.getAdmissionDate()));
            pstmt.executeUpdate();
        }
    }

    public void updateDiagnosis(int patientId, String diagnosis) throws SQLException {
        String sql = "UPDATE patients SET diagnosis = ? WHERE patient_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, diagnosis);
            pstmt.setInt(2, patientId);
            pstmt.executeUpdate();
        }
    }

    public double calculateDischargeFee(int patientId) throws SQLException {
        String sql = "{CALL CALCULATE_DISCHARGE_FEE(?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, patientId);
            stmt.registerOutParameter(2, Types.DECIMAL);
            stmt.execute();
            return stmt.getDouble(2);
        }
    }
}
