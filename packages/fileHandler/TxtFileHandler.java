package packages.fileHandler;

import packages.system.Student;
import packages.system.StudentSystem;
import java.io.*;
import java.util.*;

public class TxtFileHandler {
    
    public static void writeTxtFile(StudentSystem system) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the report file path (or press Enter for 'student_report.txt'): ");
        String filePath = scanner.nextLine().trim();
        
        if (filePath.isEmpty()) {
            filePath = "student_report.txt";
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            List<Student> students = system.getAllStudents();
            
            if (students.isEmpty()) {
                writer.println("=== STUDENT MANAGEMENT SYSTEM REPORT ===");
                writer.println("No students to report.");
                writer.println("=========================================");
            } else {
                // Write header
                writer.println("=== STUDENT MANAGEMENT SYSTEM REPORT ===");
                writer.println("Generated on: " + new java.util.Date());
                writer.println();
                
                // Basic statistics
                writer.println("SUMMARY STATISTICS:");
                writer.println("Total Students: " + students.size());
                
                // Calculate average GPA
                double totalGPA = 0;
                for (Student student : students) {
                    totalGPA += student.getGpa();
                }
                double averageGPA = totalGPA / students.size();
                writer.printf("Average GPA: %.2f%n", averageGPA);
                writer.println();
                
                // Count by year
                Map<String, Integer> yearCount = new HashMap<>();
                for (Student student : students) {
                    yearCount.put(student.getYear(), yearCount.getOrDefault(student.getYear(), 0) + 1);
                }
                writer.println("STUDENTS BY YEAR:");
                for (Map.Entry<String, Integer> entry : yearCount.entrySet()) {
                    writer.println("  " + entry.getKey() + ": " + entry.getValue());
                }
                writer.println();
                
                // Count by department
                Map<String, Integer> deptCount = new HashMap<>();
                for (Student student : students) {
                    deptCount.put(student.getDepartment(), deptCount.getOrDefault(student.getDepartment(), 0) + 1);
                }
                writer.println("STUDENTS BY DEPARTMENT:");
                for (Map.Entry<String, Integer> entry : deptCount.entrySet()) {
                    writer.println("  " + entry.getKey() + ": " + entry.getValue());
                }
                writer.println();
                
                // Count failing students
                int failingCount = 0;
                List<Student> failingStudents = new ArrayList<>();
                for (Student student : students) {
                    if (student.getGpa() < 2.0) {
                        failingCount++;
                        failingStudents.add(student);
                    }
                }
                writer.println("FAILING STUDENTS (GPA < 2.0): " + failingCount);
                if (!failingStudents.isEmpty()) {
                    for (Student student : failingStudents) {
                        writer.println("  " + student);
                    }
                }
                writer.println();
                
                // Top 5 students
                List<Student> sortedByGPA = new ArrayList<>(students);
                sortedByGPA.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
                writer.println("TOP 5 STUDENTS BY GPA:");
                int count = Math.min(5, sortedByGPA.size());
                for (int i = 0; i < count; i++) {
                    writer.println("  " + (i + 1) + ". " + sortedByGPA.get(i));
                }
                writer.println();
                
                // Complete student list
                writer.println("COMPLETE STUDENT LIST:");
                writer.println("----------------------------------------");
                for (Student student : students) {
                    writer.println(student);
                }
                writer.println("----------------------------------------");
                writer.println();
                
                writer.println("=========================================");
                writer.println("Report generated successfully.");
            }
            
            System.out.println("Successfully generated report: " + filePath);
            
        } catch (IOException e) {
            System.out.println("Error writing report file: " + e.getMessage());
        }
    }
}
