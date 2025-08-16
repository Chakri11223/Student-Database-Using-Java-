package packages.fileHandler;

import packages.system.Student;
import packages.system.StudentSystem;
import java.io.*;
import java.util.*;

public class SimpleDatabaseHandler {
    private static final String DB_FILE = "students.dat";
    
    public static void initializeDatabase() {
        File dbFile = new File(DB_FILE);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                System.out.println("Database file created successfully.");
            } catch (IOException e) {
                System.out.println("Error creating database file: " + e.getMessage());
            }
        }
    }
    
    public static List<Student> loadStudentsFromDatabase() {
        List<Student> students = new ArrayList<>();
        File dbFile = new File(DB_FILE);
        
        if (!dbFile.exists() || dbFile.length() == 0) {
            return students;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbFile))) {
            @SuppressWarnings("unchecked")
            List<Student> loadedStudents = (List<Student>) ois.readObject();
            students.addAll(loadedStudents);
            System.out.println("Successfully loaded " + students.size() + " students from database.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students from database: " + e.getMessage());
        }
        
        return students;
    }
    
    public static void saveStudentToDatabase(Student student) {
        List<Student> students = loadStudentsFromDatabase();
        
        // Check if student already exists
        boolean exists = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                exists = true;
                break;
            }
        }
        
        if (!exists) {
            students.add(student);
        }
        
        saveAllStudentsToDatabase(students);
    }
    
    public static void deleteStudentFromDatabase(int id) {
        List<Student> students = loadStudentsFromDatabase();
        
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                System.out.println("Student with ID " + id + " deleted from database.");
                saveAllStudentsToDatabase(students);
                return;
            }
        }
        
        System.out.println("Student with ID " + id + " not found in database.");
    }
    
    public static void updateStudentInDatabase(Student student) {
        saveStudentToDatabase(student);
        System.out.println("Student with ID " + student.getId() + " updated in database.");
    }
    
    public static void saveAllStudentsToDatabase(StudentSystem system) {
        saveAllStudentsToDatabase(system.getAllStudents());
    }
    
    private static void saveAllStudentsToDatabase(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DB_FILE))) {
            oos.writeObject(students);
            System.out.println("Successfully saved " + students.size() + " students to database.");
        } catch (IOException e) {
            System.out.println("Error saving students to database: " + e.getMessage());
        }
    }
    
    public static void createSampleData() {
        List<Student> sampleStudents = Arrays.asList(
            new Student("John Doe", 1001, 3.8, "Third", "Computer Science"),
            new Student("Jane Smith", 1002, 3.9, "Fourth", "Electrical Engineering"),
            new Student("Mike Johnson", 1003, 3.2, "Second", "Mechanical Engineering"),
            new Student("Sarah Wilson", 1004, 3.5, "First", "General"),
            new Student("David Brown", 1005, 3.7, "Third", "Civil Engineering")
        );
        
        saveAllStudentsToDatabase(sampleStudents);
        System.out.println("Sample data created successfully in database.");
    }
}
