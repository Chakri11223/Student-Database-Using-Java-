package packages.system;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int id;
    private double gpa;
    private String year;
    private String department;

    public Student(String name, int id, double gpa, String year, String department) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
        this.year = year;
        this.department = department;
    }

    // Getters
    public String getName() { return name; }
    public int getId() { return id; }
    public double getGpa() { return gpa; }
    public String getYear() { return year; }
    public String getDepartment() { return department; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setId(int id) { this.id = id; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public void setYear(String year) { this.year = year; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | GPA: %.2f | Year: %s | Department: %s", 
                           id, name, gpa, year, department);
    }


}
