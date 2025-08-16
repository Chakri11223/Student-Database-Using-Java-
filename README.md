# Student Database Management System

A Java-based Student Database Management System that allows users to manage student records with a simple console interface.

## Features

- Add new student records
- View all student records
- Search for specific students
- Update student information
- Delete student records
- Persistent data storage using file handling

## Project Structure

```
SMS/
├── App.java                 # Main application entry point
├── App.class               # Compiled main class
├── students.dat            # Data file storing student records
└── packages/
    ├── fileHandler/        # File handling utilities
    │   ├── SimpleDatabaseHandler.java
    │   └── TxtFileHandler.java
    └── system/             # Core system components
        ├── InputValidator.java
        ├── Student.java
        └── StudentSystem.java
```

## How to Run

1. Ensure you have Java installed on your system
2. Compile the project: `javac App.java`
3. Run the application: `java App`

## Usage

The application provides a menu-driven interface where you can:
- Enter '1' to add a new student
- Enter '2' to view all students
- Enter '3' to search for a student
- Enter '4' to update a student record
- Enter '5' to delete a student record
- Enter '6' to exit the application

## Data Storage

Student records are stored in the `students.dat` file using a simple text-based format for persistence across application sessions.
