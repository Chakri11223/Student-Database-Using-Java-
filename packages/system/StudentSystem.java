package packages.system;

import java.util.*;
import packages.fileHandler.SimpleDatabaseHandler;

public class StudentSystem {
    private List<Student> students;

    public StudentSystem() {
        this.students = new ArrayList<>();
        // Initialize database and load existing data
        SimpleDatabaseHandler.initializeDatabase();
        this.students = SimpleDatabaseHandler.loadStudentsFromDatabase();
    }

    public void addStudent(String name, int id, double gpa, String year, String department, boolean fromFile) {
        Student student = new Student(name, id, gpa, year, department);
        students.add(student);
        // Save to database
        SimpleDatabaseHandler.saveStudentToDatabase(student);
        if (!fromFile) {
            System.out.println("Student added successfully!");
        }
    }

    public void removeStudentByID(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                Student removed = students.remove(i);
                // Remove from database
                SimpleDatabaseHandler.deleteStudentFromDatabase(id);
                System.out.println("Student removed: " + removed.getName());
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void updateStudentByID(int id, String newName, double newGPA, String newYear, String newDepartment) {
        for (Student student : students) {
            if (student.getId() == id) {
                if (newName != null) student.setName(newName);
                if (newGPA != -1) student.setGpa(newGPA);
                if (newYear != null) student.setYear(newYear);
                if (newDepartment != null) student.setDepartment(newDepartment);
                // Update in database
                SimpleDatabaseHandler.updateStudentInDatabase(student);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void searchByID(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Student found:");
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void listAndSortAllStudents(String sortBy) {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        List<Student> sortedStudents = new ArrayList<>(students);
        
        switch (sortBy.toLowerCase()) {
            case "gpa":
                sortedStudents.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
                break;
            case "id":
                sortedStudents.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));
                break;
            case "name":
                sortedStudents.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
                break;
            case "year":
                sortedStudents.sort((s1, s2) -> s1.getYear().compareToIgnoreCase(s2.getYear()));
                break;
            default:
                System.out.println("Invalid sorting criteria.");
                return;
        }

        System.out.println("\nStudents sorted by " + sortBy + ":");
        System.out.println("----------------------------------------");
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
        System.out.println("----------------------------------------");
    }

    public void filterByGPA(double minGPA) {
        List<Student> filtered = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() >= minGPA) {
                filtered.add(student);
            }
        }
        
        if (filtered.isEmpty()) {
            System.out.println("No students found with GPA >= " + minGPA);
        } else {
            System.out.println("\nStudents with GPA >= " + minGPA + ":");
            System.out.println("----------------------------------------");
            for (Student student : filtered) {
                System.out.println(student);
            }
            System.out.println("----------------------------------------");
        }
    }

    public void filterByYear(String year) {
        List<Student> filtered = new ArrayList<>();
        for (Student student : students) {
            if (student.getYear().equalsIgnoreCase(year)) {
                filtered.add(student);
            }
        }
        
        if (filtered.isEmpty()) {
            System.out.println("No students found in " + year + " year.");
        } else {
            System.out.println("\nStudents in " + year + " year:");
            System.out.println("----------------------------------------");
            for (Student student : filtered) {
                System.out.println(student);
            }
            System.out.println("----------------------------------------");
        }
    }

    public void filterByDepartment(String department) {
        List<Student> filtered = new ArrayList<>();
        for (Student student : students) {
            if (student.getDepartment().equalsIgnoreCase(department)) {
                filtered.add(student);
            }
        }
        
        if (filtered.isEmpty()) {
            System.out.println("No students found in " + department + " department.");
        } else {
            System.out.println("\nStudents in " + department + " department:");
            System.out.println("----------------------------------------");
            for (Student student : filtered) {
                System.out.println(student);
            }
            System.out.println("----------------------------------------");
        }
    }

    public void countTotalStudents() {
        System.out.println("Total number of students: " + students.size());
    }

    public void calculateAverageGPA() {
        if (students.isEmpty()) {
            System.out.println("No students to calculate average GPA.");
            return;
        }
        
        double totalGPA = 0;
        for (Student student : students) {
            totalGPA += student.getGpa();
        }
        double averageGPA = totalGPA / students.size();
        System.out.printf("Average GPA: %.2f%n", averageGPA);
    }

    public void displayTop5() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        
        List<Student> sortedByGPA = new ArrayList<>(students);
        sortedByGPA.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        
        System.out.println("\nTop 5 Students by GPA:");
        System.out.println("----------------------------------------");
        int count = Math.min(5, sortedByGPA.size());
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + sortedByGPA.get(i));
        }
        System.out.println("----------------------------------------");
    }

    public void displayFailingStudents() {
        List<Student> failing = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() < 2.0) {
                failing.add(student);
            }
        }
        
        if (failing.isEmpty()) {
            System.out.println("No failing students (GPA < 2.0).");
        } else {
            System.out.println("\nFailing Students (GPA < 2.0):");
            System.out.println("----------------------------------------");
            for (Student student : failing) {
                System.out.println(student);
            }
            System.out.println("----------------------------------------");
        }
    }

    public void generateSummary() {
        if (students.isEmpty()) {
            System.out.println("No students to generate summary.");
            return;
        }
        
        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM SUMMARY ===");
        System.out.println("Total Students: " + students.size());
        
        // Calculate average GPA
        double totalGPA = 0;
        for (Student student : students) {
            totalGPA += student.getGpa();
        }
        double averageGPA = totalGPA / students.size();
        System.out.printf("Average GPA: %.2f%n", averageGPA);
        
        // Count by year
        Map<String, Integer> yearCount = new HashMap<>();
        for (Student student : students) {
            yearCount.put(student.getYear(), yearCount.getOrDefault(student.getYear(), 0) + 1);
        }
        System.out.println("\nStudents by Year:");
        for (Map.Entry<String, Integer> entry : yearCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        // Count by department
        Map<String, Integer> deptCount = new HashMap<>();
        for (Student student : students) {
            deptCount.put(student.getDepartment(), deptCount.getOrDefault(student.getDepartment(), 0) + 1);
        }
        System.out.println("\nStudents by Department:");
        for (Map.Entry<String, Integer> entry : deptCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        // Count failing students
        int failingCount = 0;
        for (Student student : students) {
            if (student.getGpa() < 2.0) {
                failingCount++;
            }
        }
        System.out.println("\nFailing Students (GPA < 2.0): " + failingCount);
        System.out.println("================================================");
    }

    public void mergeStudentSystem(List<Student> newStudents) {
        if (newStudents != null) {
            students.addAll(newStudents);
            // Save all students to database
            SimpleDatabaseHandler.saveAllStudentsToDatabase(this);
            System.out.println("Successfully imported " + newStudents.size() + " students from database.");
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public boolean isNameExists(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdExists(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
