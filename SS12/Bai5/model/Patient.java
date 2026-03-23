package Bai5.model;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String department;
    private String diagnosis;
    private LocalDate admissionDate;

    public Patient(String name, int age, String department, String diagnosis, LocalDate admissionDate) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
    }

    public Patient(int id, String name, int age, String department, String diagnosis, LocalDate admissionDate) {
        this(name, age, department, diagnosis, admissionDate);
        this.id = id;
    }

    // Getters và Setters
}
