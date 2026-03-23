package Bai5.service;

import Bai5.PatientDAO;
import Bai5.model.Patient;

import java.sql.SQLException;
import java.util.List;

public class PatientService {
    private final PatientDAO dao = new PatientDAO();

    // Lấy danh sách bệnh nhân
    public List<Patient> getAllPatients() throws SQLException {
        return dao.getAllPatients();
    }

    // Thêm bệnh nhân mới
    public void addPatient(Patient patient) throws SQLException {
        if (patient.getName() == null || patient.getName().isEmpty()) {
            throw new IllegalArgumentException("Tên bệnh nhân không được để trống!");
        }
        dao.addPatient(patient);
    }

    // Cập nhật chẩn đoán
    public void updateDiagnosis(int patientId, String diagnosis) throws SQLException {
        if (diagnosis == null) diagnosis = "";
        dao.updateDiagnosis(patientId, diagnosis);
    }

    // Tính phí xuất viện
    public double calculateDischargeFee(int patientId) throws SQLException {
        return dao.calculateDischargeFee(patientId);
    }

    // Hiển thị danh sách bệnh nhân theo format đẹp
    public void printPatientList(List<Patient> patients) {
        System.out.printf("%-5s %-20s %-5s %-15s %-20s\n", "ID", "Tên", "Tuổi", "Khoa", "Chẩn đoán");
        for (Patient p : patients) {
            System.out.printf("%-5d %-20s %-5d %-15s %-20s\n",
                    p.getId(), p.getName(), p.getAge(), p.getDepartment(), p.getDiagnosis());
        }
    }
}
