import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseRegistrationSystem {
    private Map<String, Course> courses;
    private Map<String, Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
        scanner = new Scanner(System.in);
        loadData();
    }

    private void seedData() {
        // Add some sample courses
        courses.put("CS101", new Course("CS101", "Intro to Computer Science", "Basics of CS", 3, "Mon 9-11"));
        courses.put("MATH201", new Course("MATH201", "Calculus I", "Differential Calculus", 2, "Tue 10-12"));
        courses.put("ENG301", new Course("ENG301", "English Literature", "Study of English Lit", 2, "Wed 1-3"));
        courses.put("ECE101", new Course("ECE101", "Circuits and Systems", "Introduction to Circuits", 3, "Thu 9-11"));
        courses.put("ECE102", new Course("ECE102", "Digital Electronics", "Basics of Digital Electronics", 3, "Fri 10-12"));
        courses.put("CSE101", new Course("CSE101", "Data Structures", "Study of Data Structures", 3, "Mon 1-3"));
        courses.put("CSE102", new Course("CSE102", "Algorithms", "Algorithm Design and Analysis", 3, "Tue 1-3"));

        // Add some sample students
        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));
    }

    public void start() {
        while (true) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Course for Student");
            System.out.println("4. Exit");
            System.out.println("5. Add New Student");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayAvailableCourses();
                    break;
                case "2":
                    registerStudentForCourse();
                    break;
                case "3":
                    dropCourseForStudent();
                    break;
                case "4":
                    saveData();
                    System.out.println("Exiting...");
                    return;
                case "5":
                    addNewStudent();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        }
        for (Course course : courses.values()) {
            System.out.printf("Code: %s, Title: %s, Description: %s, Schedule: %s, Capacity: %d, Available Slots: %d%n",
                    course.getCourseCode(), course.getTitle(), course.getDescription(),
                    course.getSchedule(), course.getCapacity(), course.getAvailableSlots());
        }
    }

    private void registerStudentForCourse() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.getAvailableSlots() <= 0) {
            System.out.println("No available slots in this course.");
            return;
        }

        if (student.getRegisteredCourseCodes().contains(courseCode)) {
            System.out.println("Student already registered for this course.");
            return;
        }

        boolean courseRegistered = course.registerStudent(studentId);
        boolean studentRegistered = student.registerCourse(courseCode);

        if (courseRegistered && studentRegistered) {
            System.out.println("Student registered for the course successfully.");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private void dropCourseForStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (!student.getRegisteredCourseCodes().contains(courseCode)) {
            System.out.println("Student is not registered for this course.");
            return;
        }

        boolean courseRemoved = course.removeStudent(studentId);
        boolean studentDropped = student.dropCourse(courseCode);

        if (courseRemoved && studentDropped) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Failed to drop course.");
        }
    }

    private void addNewStudent() {
        System.out.print("Enter new Student ID: ");
        String studentId = scanner.nextLine();
        if (students.containsKey(studentId)) {
            System.out.println("Student ID already exists.");
            return;
        }
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        Student newStudent = new Student(studentId, name);
        students.put(studentId, newStudent);
        System.out.println("Student added successfully.");
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.start();
    }

    private void saveData() {
        try (java.io.PrintWriter courseWriter = new java.io.PrintWriter("courses.txt");
             java.io.PrintWriter studentWriter = new java.io.PrintWriter("students.txt")) {

            // Save courses
            for (Course course : courses.values()) {
                // Format: courseCode|title|description|capacity|schedule|registeredStudentIds(comma separated)
                StringBuilder sb = new StringBuilder();
                sb.append(course.getCourseCode()).append("|")
                  .append(course.getTitle()).append("|")
                  .append(course.getDescription()).append("|")
                  .append(course.getCapacity()).append("|")
                  .append(course.getSchedule()).append("|");
                List<String> registered = course.getRegisteredStudentIds();
                for (int i = 0; i < registered.size(); i++) {
                    sb.append(registered.get(i));
                    if (i < registered.size() - 1) {
                        sb.append(",");
                    }
                }
                courseWriter.println(sb.toString());
            }

            // Save students in table format
            studentWriter.println(String.format("%-10s | %-20s | %s", "StudentID", "Name", "Registered Courses"));
            studentWriter.println("--------------------------------------------------------------");
            for (Student student : students.values()) {
                String registeredCourses = String.join(", ", student.getRegisteredCourseCodes());
                studentWriter.println(String.format("%-10s | %-20s | %s", student.getStudentId(), student.getName(), registeredCourses));
            }

        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadData() {
        try (java.util.Scanner courseScanner = new java.util.Scanner(new java.io.File("courses.txt"));
             java.util.Scanner studentScanner = new java.util.Scanner(new java.io.File("students.txt"))) {

            // Load courses
            System.out.println("Loading courses...");
            while (courseScanner.hasNextLine()) {
                String line = courseScanner.nextLine();
                System.out.println("Course line: " + line);
                String[] parts = line.split("\\|", -1);
                if (parts.length >= 6) {
                    String courseCode = parts[0];
                    String title = parts[1];
                    String description = parts[2];
                    int capacity = Integer.parseInt(parts[3]);
                    String schedule = parts[4];
                    Course course = new Course(courseCode, title, description, capacity, schedule);
                    if (!parts[5].isEmpty()) {
                        String[] registeredStudents = parts[5].split(",");
                        for (String studentId : registeredStudents) {
                            course.registerStudent(studentId);
                        }
                    }
                    courses.put(courseCode, course);
                    System.out.println("Loaded course: " + courseCode);
                }
            }

            // Load students - skip header lines
            if (studentScanner.hasNextLine()) studentScanner.nextLine(); // skip header
            if (studentScanner.hasNextLine()) studentScanner.nextLine(); // skip separator

            while (studentScanner.hasNextLine()) {
                String line = studentScanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String studentId = parts[0].trim();
                    String name = parts[1].trim();
                    String registeredCoursesStr = parts[2].trim();
                    Student student = new Student(studentId, name);
                    if (!registeredCoursesStr.isEmpty()) {
                        String[] registeredCourses = registeredCoursesStr.split(",");
                        for (String courseCode : registeredCourses) {
                            student.registerCourse(courseCode.trim());
                        }
                    }
                    students.put(studentId, student);
                }
            }

        } catch (java.io.FileNotFoundException e) {
            // Files not found, seed initial data
            seedData();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            seedData();
        }
    }
}
