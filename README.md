# LearnTrack - Student & Course Management System

LearnTrack is a console-based Student & Course Management System built using Core Java. It allows admins to manage Students, Courses, and Enrollments through a menu-driven interface.

## Class Diagram

```
┌─────────────────────────────┐
│         <<abstract>>        │
│           Person            │
├─────────────────────────────┤
│ - id: int                   │
│ - firstName: String         │
│ - lastName: String          │
│ - email: String             │
├─────────────────────────────┤
│ + getDisplayName(): String  │
│ + getters/setters           │
└──────────────┬──────────────┘
               │ extends
               ▼
┌─────────────────────────────┐
│          Student            │
├─────────────────────────────┤
│ - batch: String             │
│ - active: boolean           │
├─────────────────────────────┤
│ + getDisplayName(): String  │  ← overrides Person
│ + getters/setters           │
└─────────────────────────────┘
        │                              ┌─────────────────────────────┐
        │                              │          Course             │
        │                              ├─────────────────────────────┤
        │                              │ - id: int                   │
        │                              │ - courseName: String        │
        │                              │ - description: String       │
        │                              │ - durationInWeeks: int      │
        │                              │ - active: boolean           │
        │                              ├─────────────────────────────┤
        │                              │ + getters/setters           │
        │                              └─────────────────────────────┘
        │                                        │
        │         ┌──────────────────────────┐    │
        └────────►│       Enrollment         │◄───┘
         studentId├──────────────────────────┤courseId
                  │ - id: int                │
                  │ - studentId: int         │
                  │ - courseId: int           │
                  │ - enrollmentDate: String  │
                  │ - status: EnrollmentStatus│
                  ├──────────────────────────┤
                  │ + getters/setters        │
                  └──────────────────────────┘

┌──────────────────────┐      ┌──────────────────────┐      ┌──────────────────────────┐
│  StudentRepository   │      │  CourseRepository     │      │  EnrollmentRepository    │
├──────────────────────┤      ├──────────────────────┤      ├──────────────────────────┤
│ - students: ArrayList│      │ - courses: ArrayList │      │ - enrollments: ArrayList │
├──────────────────────┤      ├──────────────────────┤      ├──────────────────────────┤
│ + add()              │      │ + add()              │      │ + add()                  │
│ + findAll()          │      │ + findAll()          │      │ + findAll()              │
│ + findById()         │      │ + findById()         │      │ + findById()             │
│ + removeById()       │      │ + removeById()       │      │ + findByStudentId()      │
└──────────┬───────────┘      └──────────┬───────────┘      │ + findByCourseId()       │
           │ uses                        │ uses              └────────────┬─────────────┘
           ▼                             ▼                               │ uses
┌──────────────────────┐      ┌──────────────────────┐                   ▼
│   StudentService     │      │   CourseService       │      ┌──────────────────────────┐
├──────────────────────┤      ├──────────────────────┤      │   EnrollmentService      │
│ + addStudent()       │      │ + addCourse()        │      ├──────────────────────────┤
│ + listStudents()     │      │ + listCourses()      │      │ + enrollStudent()        │
│ + findStudentById()  │      │ + findCourseById()   │      │ + listEnrollments()      │
│ + updateStudent()    │      │ + activateCourse()   │      │ + findEnrollmentById()   │
│ + deactivateStudent()│      │ + deactivateCourse() │      │ + markAsCompleted()      │
└──────────────────────┘      └──────────────────────┘      │ + markAsCancelled()      │
                                                            └──────────────────────────┘

┌──────────────────┐    ┌──────────────────┐    ┌────────────────────────┐
│  <<enum>>        │    │  <<exception>>   │    │  <<utility>>           │
│ EnrollmentStatus │    │ EntityNotFound   │    │  IdGenerator           │
├──────────────────┤    │   Exception      │    ├────────────────────────┤
│ ACTIVE           │    └──────────────────┘    │ - studentIdCounter: int│
│ COMPLETED        │    ┌──────────────────┐    │ - courseIdCounter: int  │
│ CANCELLED        │    │  <<exception>>   │    │ - enrollmentIdCounter  │
└──────────────────┘    │ InvalidInput     │    ├────────────────────────┤
                        │   Exception      │    │ + getNextStudentId()   │
                        └──────────────────┘    │ + getNextCourseId()    │
                                                │ + getNextEnrollmentId()│
                                                └────────────────────────┘

                    ┌─────────────────────────────┐
                    │           Main               │
                    ├─────────────────────────────┤
                    │ - scanner: Scanner           │
                    │ - studentService             │
                    │ - courseService               │
                    │ - enrollmentService           │
                    ├─────────────────────────────┤
                    │ + main()                     │
                    │ - displayMainMenu()          │
                    │ - handleStudentMenu()        │
                    │ - handleCourseMenu()         │
                    │ - handleEnrollmentMenu()     │
                    └─────────────────────────────┘
```

## Features

- **Student Management**: Add, view, search, and deactivate students
- **Course Management**: Add, view, search, and deactivate courses
- **Enrollment Management**: Enroll students in courses, view enrollments, mark as completed or cancelled
- **Input Validation**: Handles invalid input gracefully with custom exceptions
- **Clean Architecture**: Separation of concerns with entity, repository, service, and UI layers

## Project Structure

```
src/
└── com/
    └── airtribe/
        └── learntrack/
            ├── Main.java
            ├── entity/
            │   ├── Person.java
            │   ├── Student.java
            │   ├── Course.java
            │   └── Enrollment.java
            ├── repository/
            │   ├── StudentRepository.java
            │   ├── CourseRepository.java
            │   └── EnrollmentRepository.java
            ├── service/
            │   ├── StudentService.java
            │   ├── CourseService.java
            │   └── EnrollmentService.java
            ├── exception/
            │   ├── EntityNotFoundException.java
            │   └── InvalidInputException.java
            ├── util/
            │   ├── IdGenerator.java
            │   └── InputValidator.java
            ├── constants/
            │   ├── MenuOptions.java
            │   └── AppConstants.java
            └── enums/
                ├── EnrollmentStatus.java
                └── CourseStatus.java
```

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Terminal / Command Prompt

## How to Compile and Run

### Using the run script (recommended)

```bash
chmod +x run.sh
./run.sh
```

### Manual compilation

```bash
# From the project root directory
javac -d out src/com/airtribe/learntrack/**/*.java src/com/airtribe/learntrack/Main.java

# Run the application
java -cp out com.airtribe.learntrack.Main
```

## Usage

When the application starts, you'll see the main menu:

1. **Student Management** - Add new students, view all students, search by ID, or deactivate students
2. **Course Management** - Add new courses, view all courses, search by ID, or deactivate courses
3. **Enrollment Management** - Enroll students in courses, view enrollments by student, mark enrollments as completed or cancelled
4. **Exit** - Close the application

All data is stored in-memory using ArrayList collections.
