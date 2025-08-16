package packages.system;

import java.util.Scanner;

public class InputValidator {
    
    public static int inputValidChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 0) {
                    return choice;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static int inputValidID(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter student ID: ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                if (id > 0) {
                    return id;
                } else {
                    System.out.println("Please enter a positive ID number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid ID number.");
            }
        }
    }

    public static double inputValidGPA(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter GPA (0.0 - 10.0): ");
                double gpa = Double.parseDouble(scanner.nextLine().trim());
                if (gpa >= 0.0 && gpa <= 10.0) {
                    return gpa;
                } else {
                    System.out.println("Please enter a GPA between 0.0 and 10.0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid GPA number.");
            }
        }
    }

    public static String inputValidYear(Scanner scanner) {
        String[] validYears = {"First", "Second", "Third", "Fourth"};
        while (true) {
            System.out.print("Enter year (First/Second/Third/Fourth): ");
            String year = scanner.nextLine().trim();
            for (String validYear : validYears) {
                if (year.equalsIgnoreCase(validYear)) {
                    return validYear;
                }
            }
            System.out.println("Please enter a valid year: First, Second, Third, or Fourth.");
        }
    }

    public static String inputValidDepartment(Scanner scanner) {
        String[] validDepartments = {"Computer Science", "Electrical Engineering", "Mechanical Engineering", 
                                   "Civil Engineering", "Chemical Engineering", "General"};
        while (true) {
            System.out.print("Enter department: ");
            String department = scanner.nextLine().trim();
            for (String validDept : validDepartments) {
                if (department.equalsIgnoreCase(validDept)) {
                    return validDept;
                }
            }
            System.out.println("Please enter a valid department from the list.");
            System.out.println("Valid departments: Computer Science, Electrical Engineering, Mechanical Engineering, Civil Engineering, Chemical Engineering, General");
        }
    }

    public static String addUniqueName(Scanner scanner, StudentSystem system) {
        while (true) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                if (!system.isNameExists(name)) {
                    return name;
                } else {
                    System.out.println("A student with this name already exists. Please enter a different name.");
                }
            } else {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }
    }

    public static int addUniqueID(Scanner scanner, StudentSystem system) {
        while (true) {
            int id = inputValidID(scanner);
            if (!system.isIdExists(id)) {
                return id;
            } else {
                System.out.println("A student with this ID already exists. Please enter a different ID.");
            }
        }
    }
}
