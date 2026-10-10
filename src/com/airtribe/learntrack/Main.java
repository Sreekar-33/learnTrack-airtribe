package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;

    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(enrollmentRepository);

        System.out.println(AppConstants.SEPARATOR);
        System.out.println("  Welcome to " + AppConstants.APP_NAME);
        System.out.println(AppConstants.SEPARATOR);

        boolean running = true;
        while (running) {
            displayMainMenu();
            try {
                int choice = InputValidator.parseMenuOption(scanner.nextLine());
                switch (choice) {
                    case MenuOptions.STUDENT_MANAGEMENT:
                        handleStudentMenu();
                        break;
                    case MenuOptions.COURSE_MANAGEMENT:
                        handleCourseMenu();
                        break;
                    case MenuOptions.ENROLLMENT_MANAGEMENT:
                        handleEnrollmentMenu();
                        break;
                    case MenuOptions.EXIT:
                        running = false;
                        System.out.println("\nThank you for using LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-4.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n" + AppConstants.THIN_SEPARATOR);
        System.out.println("                    MAIN MENU");
        System.out.println(AppConstants.THIN_SEPARATOR);
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  4. Exit");
        System.out.println(AppConstants.THIN_SEPARATOR);
        System.out.print("Enter your choice: ");
    }

    // ======================== STUDENT MENU ========================

    private static void handleStudentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n" + AppConstants.THIN_SEPARATOR);
            System.out.println("              STUDENT MANAGEMENT");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.println("  1. Add New Student");
            System.out.println("  2. View All Students");
            System.out.println("  3. Search Student by ID");
            System.out.println("  4. Deactivate a Student");
            System.out.println("  5. Back to Main Menu");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.print("Enter your choice: ");

            try {
                int choice = InputValidator.parseMenuOption(scanner.nextLine());
                switch (choice) {
                    case MenuOptions.ADD:
                        addStudent();
                        break;
                    case MenuOptions.VIEW_ALL:
                        viewAllStudents();
                        break;
                    case MenuOptions.SEARCH_BY_ID:
                        searchStudentById();
                        break;
                    case MenuOptions.DEACTIVATE:
                        deactivateStudent();
                        break;
                    case MenuOptions.BACK:
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-5.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            InputValidator.validateNotEmpty(firstName, "First Name");

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            InputValidator.validateNotEmpty(lastName, "Last Name");

            System.out.print("Enter Email (or press Enter to skip): ");
            String email = scanner.nextLine();

            System.out.print("Enter Batch: ");
            String batch = scanner.nextLine();
            InputValidator.validateNotEmpty(batch, "Batch");

            Student student;
            if (email.trim().isEmpty()) {
                student = studentService.addStudent(firstName, lastName, batch);
            } else {
                student = studentService.addStudent(firstName, lastName, email, batch);
            }
            System.out.println("Student added successfully!");
            System.out.println(student);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        ArrayList<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Total: " + students.size() + " student(s)");
    }

    private static void searchStudentById() {
        try {
            System.out.print("Enter Student ID: ");
            int id = InputValidator.parsePositiveInt(scanner.nextLine(), "Student ID");
            Student student = studentService.findStudentById(id);
            System.out.println(student);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deactivateStudent() {
        try {
            System.out.print("Enter Student ID to deactivate: ");
            int id = InputValidator.parsePositiveInt(scanner.nextLine(), "Student ID");
            studentService.deactivateStudent(id);
            System.out.println("Student with ID " + id + " has been deactivated.");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ======================== COURSE MENU ========================

    private static void handleCourseMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n" + AppConstants.THIN_SEPARATOR);
            System.out.println("              COURSE MANAGEMENT");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.println("  1. Add New Course");
            System.out.println("  2. View All Courses");
            System.out.println("  3. Search Course by ID");
            System.out.println("  4. Deactivate a Course");
            System.out.println("  5. Back to Main Menu");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.print("Enter your choice: ");

            try {
                int choice = InputValidator.parseMenuOption(scanner.nextLine());
                switch (choice) {
                    case MenuOptions.ADD:
                        addCourse();
                        break;
                    case MenuOptions.VIEW_ALL:
                        viewAllCourses();
                        break;
                    case MenuOptions.SEARCH_BY_ID:
                        searchCourseById();
                        break;
                    case MenuOptions.DEACTIVATE:
                        deactivateCourse();
                        break;
                    case MenuOptions.BACK:
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-5.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addCourse() {
        try {
            System.out.print("Enter Course Name: ");
            String courseName = scanner.nextLine();
            InputValidator.validateNotEmpty(courseName, "Course Name");

            System.out.print("Enter Description: ");
            String description = scanner.nextLine();
            InputValidator.validateNotEmpty(description, "Description");

            System.out.print("Enter Duration (in weeks): ");
            int duration = InputValidator.parsePositiveInt(scanner.nextLine(), "Duration");

            Course course = courseService.addCourse(courseName, description, duration);
            System.out.println("Course added successfully!");
            System.out.println(course);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllCourses() {
        ArrayList<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\n--- All Courses ---");
        for (Course course : courses) {
            System.out.println(course);
        }
        System.out.println("Total: " + courses.size() + " course(s)");
    }

    private static void searchCourseById() {
        try {
            System.out.print("Enter Course ID: ");
            int id = InputValidator.parsePositiveInt(scanner.nextLine(), "Course ID");
            Course course = courseService.findCourseById(id);
            System.out.println(course);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deactivateCourse() {
        try {
            System.out.print("Enter Course ID to deactivate: ");
            int id = InputValidator.parsePositiveInt(scanner.nextLine(), "Course ID");
            courseService.deactivateCourse(id);
            System.out.println("Course with ID " + id + " has been deactivated.");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ======================== ENROLLMENT MENU ========================

    private static void handleEnrollmentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n" + AppConstants.THIN_SEPARATOR);
            System.out.println("            ENROLLMENT MANAGEMENT");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.println("  1. Enroll a Student in a Course");
            System.out.println("  2. View Enrollments for a Student");
            System.out.println("  3. Mark Enrollment as Completed");
            System.out.println("  4. Mark Enrollment as Cancelled");
            System.out.println("  5. Back to Main Menu");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.print("Enter your choice: ");

            try {
                int choice = InputValidator.parseMenuOption(scanner.nextLine());
                switch (choice) {
                    case MenuOptions.ENROLL_STUDENT:
                        enrollStudent();
                        break;
                    case MenuOptions.VIEW_ENROLLMENTS:
                        viewEnrollmentsForStudent();
                        break;
                    case MenuOptions.MARK_COMPLETED:
                        markEnrollmentCompleted();
                        break;
                    case MenuOptions.MARK_CANCELLED:
                        markEnrollmentCancelled();
                        break;
                    case MenuOptions.BACK_FROM_ENROLLMENT:
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-5.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void enrollStudent() {
        try {
            System.out.print("Enter Student ID: ");
            int studentId = InputValidator.parsePositiveInt(scanner.nextLine(), "Student ID");

            studentService.findStudentById(studentId);

            System.out.print("Enter Course ID: ");
            int courseId = InputValidator.parsePositiveInt(scanner.nextLine(), "Course ID");

            courseService.findCourseById(courseId);

            String enrollmentDate = LocalDate.now().toString();
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId, enrollmentDate);
            System.out.println("Enrollment successful!");
            System.out.println(enrollment);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewEnrollmentsForStudent() {
        try {
            System.out.print("Enter Student ID: ");
            int studentId = InputValidator.parsePositiveInt(scanner.nextLine(), "Student ID");

            studentService.findStudentById(studentId);

            ArrayList<Enrollment> enrollments = enrollmentService.findEnrollmentsByStudentId(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for Student ID " + studentId + ".");
                return;
            }
            System.out.println("\n--- Enrollments for Student ID " + studentId + " ---");
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void markEnrollmentCompleted() {
        try {
            System.out.print("Enter Enrollment ID: ");
            int enrollmentId = InputValidator.parsePositiveInt(scanner.nextLine(), "Enrollment ID");
            enrollmentService.markAsCompleted(enrollmentId);
            System.out.println("Enrollment " + enrollmentId + " marked as COMPLETED.");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void markEnrollmentCancelled() {
        try {
            System.out.print("Enter Enrollment ID: ");
            int enrollmentId = InputValidator.parsePositiveInt(scanner.nextLine(), "Enrollment ID");
            enrollmentService.markAsCancelled(enrollmentId);
            System.out.println("Enrollment " + enrollmentId + " marked as CANCELLED.");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
