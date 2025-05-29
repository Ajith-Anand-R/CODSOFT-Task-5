# Course Registration System

## Description
This is a Java console application that simulates a course registration system. It allows managing courses and students, enabling students to register for courses, drop courses, and add new students. The system persists data in text files to maintain state between sessions.

## Features
- Display available courses with details such as course code, title, description, schedule, capacity, and available slots.
- Register a student for a course.
- Drop a course for a student.
- Add new students to the system.
- Data persistence through text files (`courses.txt` and `students.txt`).
- Console-based menu-driven interface for easy interaction.

## Prerequisites
- Java Development Kit (JDK) installed on your system.
- Basic knowledge of running Java applications from the command line.

## How to Run the Application
1. Compile the Java source files:
   ```bash
   javac Course.java Student.java CourseRegistrationSystem.java
   ```
2. Run the main application:
   ```bash
   java CourseRegistrationSystem
   ```

## Usage Instructions
Upon running the application, you will see a menu with the following options:

1. Display Available Courses
   Lists all courses with their details and available slots.

2. Register Student for Course
   Prompts for Student ID and Course Code to register the student for the selected course.

3. Drop Course for Student
   Prompts for Student ID and Course Code to drop the course for the student.

4. Exit
   Saves all data to text files and exits the application.

5. Add New Student**
   Allows adding a new student by entering a unique Student ID and name.

## Data Persistence
- **courses.txt**: Stores course information in the format:  
  `courseCode|title|description|capacity|schedule|registeredStudentIds(comma separated)`  
- **students.txt**: Stores student information in a table format with columns for Student ID, Name, and Registered Courses.

The system loads data from these files on startup. If the files are not found, it seeds the system with sample courses and students.

## Sample Data
If no data files are found, the system seeds the following sample data:

### Courses
- CS101: Intro to Computer Science
- MATH201: Calculus I
- ENG301: English Literature
- ECE101: Circuits and Systems
- ECE102: Digital Electronics
- CSE101: Data Structures
- CSE102: Algorithms

### Students
- S001: Alice
- S002: Bob
