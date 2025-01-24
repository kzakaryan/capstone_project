package capstone_project;

import capstone_project.impl.AdministratorServiceImpl;
import capstone_project.impl.CourseServiceImpl;
import capstone_project.impl.InstructorServiceImpl;
import capstone_project.impl.StudentServiceImpl;
import capstone_project.user.Student;
import capstone_project.user.Instructor;
import capstone_project.course.Course;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class AdministratorController {

    private final AdministratorServiceImpl adminService;
    private final CourseServiceImpl courseService;
    private final StudentServiceImpl studentService;
    private final InstructorServiceImpl instructorService;


    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Add Student");
            System.out.println("4. Add Instructor");
            System.out.println("5. Remove Instructor");
            System.out.println("6. Remove Student");
            System.out.println("7. Remove Course");
            System.out.println("8. View All Students");
            System.out.println("9. View All Instructors");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    addStudent(scanner);
                    break;
                case 4:
                    addInstructor(scanner);
                    break;
                case 5:
                    removeInstructor(scanner);
                    break;
                case 6:
                    removeStudent(scanner);
                    break;
                case 7:
                    removeCourse(scanner);
                    break;
                case 8:
                    viewAllStudents();
                    break;
                case 9:
                    viewAllInstructors();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addCourse(Scanner scanner) {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Course Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Course Credits: ");
        String credits = scanner.nextLine();
        System.out.print("Enter Course Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Course course = new Course(courseId, courseName, description, credits, null, null, null, capacity);
        if (adminService.addCourse(course)) {
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Failed to add course.");
        }
    }

    private void viewAllCourses() {
        var courses = adminService.viewAllCourses();
        for (Course course : courses) {
            System.out.println("Course ID: " + course.getCourseId() + ", Name: " + course.getCourseName());
        }
    }

    private void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student First Name: ");
        String studentFirstName = scanner.nextLine();
        System.out.print("Enter Student Last Name: ");
        String studentLastName = scanner.nextLine();
        System.out.print("Enter Student Email: ");
        String studentEmail = scanner.nextLine();

        Student student = new Student(studentId, studentFirstName, studentLastName, studentEmail, " ", true, 0);
        if (adminService.addStudent(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    private void addInstructor(Scanner scanner) {
        System.out.print("Enter Instructor ID: ");
        String instructorId = scanner.nextLine();
        System.out.print("Enter Instructor First Name: ");
        String instructorFirstName = scanner.nextLine();
        System.out.print("Enter Instructor Last Name: ");
        String instructorLastName = scanner.nextLine();
        System.out.print("Enter Instructor Email: ");
        String instructorEmail = scanner.nextLine();

        Instructor instructor = new Instructor(instructorId, instructorFirstName, instructorLastName, instructorEmail, "", "", true);
        if (adminService.addInstructor(instructor)) {
            System.out.println("Instructor added successfully.");
        } else {
            System.out.println("Failed to add instructor.");
        }
    }

    private void removeInstructor(Scanner scanner) {
        System.out.print("Enter Instructor ID: ");
        String instructorId = scanner.nextLine();

        if (adminService.removeInstructor(instructorId)) {
            System.out.println("Instructor removed successfully.");
        } else {
            System.out.println("Failed to remove instructor.");
        }
    }

    private void removeStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (adminService.removeStudent(studentId)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Failed to remove student.");
        }
    }

    private void removeCourse(Scanner scanner) {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();

        if (adminService.removeCourse(courseId)) {
            System.out.println("Course removed successfully.");
        } else {
            System.out.println("Failed to remove course.");
        }
    }

    private void viewAllStudents() {
        var students = adminService.viewAllStudents();
        for (Student student : students) {
            System.out.println("Student ID: " + student.getUserId() + ", Name: " + student.getFirstName());
        }
    }

    private void viewAllInstructors() {
        var instructors = adminService.viewAllInstructors();
        for (Instructor instructor : instructors) {
            System.out.println("Instructor ID: " + instructor.getUserId() + ", Name: " + instructor.getFirstName());
        }
    }
}
