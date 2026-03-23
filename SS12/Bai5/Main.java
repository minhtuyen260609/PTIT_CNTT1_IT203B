package Bai5;


import Bai5.dao.PatientDAO;
import Bai5.model.Patient;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final PatientDAO dao = new PatientDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int choice;
        do {
            System.out.println("\n--- RHMS Menu ---");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Tiếp nhận bệnh nhân mới");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & Tính phí");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> listPatients();
                case 2 -> addPatient();
                case 3 -> updateDiagnosis();
                case 4 -> dischargePatient();
                case 5 -> System.out.println("Thoát chương trình.");
                default -> System.out.println("Chọn không hợp lệ!");
            }
        } while (choice != 5);
    }

    private static void listPatients() throws SQLException {
        List<Patient> patients = dao.getAllPatients();
        System.out.printf("%-5s %-20s %-5s %-15s %-20s\n", "ID", "Tên", "Tuổi", "Khoa", "Chẩn đoán");
        for (Patient p : patients) {
            System.out.printf("%-5d %-20s %-5d %-15s %-20s\n", p.getId(), p.getName(), p.getAge(), p.getDepartment(), p.getDiagnosis());
        }
    }

    private static void addPatient() throws SQLException {
        System.out.print("Tên bệnh nhân: ");
        String name = sc.nextLine();
        System.out.print("Tuổi: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Khoa: ");
        String dept = sc.nextLine();
        System.out.print("Chẩn đoán: ");
        String diag = sc.nextLine();
        Patient patient = new Patient(name, age, dept, diag, LocalDate.now());
        dao.addPatient(patient);
        System.out.println("Thêm bệnh nhân thành công!");
    }

    private static void updateDiagnosis() throws SQLException {
        System.out.print("Mã bệnh nhân: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Chẩn đoán mới: ");
        String diag = sc.nextLine();
        dao.updateDiagnosis(id, diag);
        System.out.println("Cập nhật bệnh án thành công!");
    }

    private static void dischargePatient() throws SQLException {
        System.out.print("Mã bệnh nhân: ");
        int id = Integer.parseInt(sc.nextLine());
        double fee = dao.calculateDischargeFee(id);
        System.out.println("Tổng phí viện: " + fee + " VND");
    }
}
