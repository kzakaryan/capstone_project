package util;

import course.Course;
import course.CourseCatalog;
import user.User;
import user.impl.Administrator;
import user.impl.Instructor;
import user.impl.Student;

import java.util.Scanner;

import static util.AdminHandler.handleAdminActions;
import static util.InstructorHandler.handleInstructorActions;
import static util.StudentHandler.handleStudentActions;

public class Launcher {

    private final static Scanner scanner = new Scanner(System.in);
    private final CourseCatalog courseCatalog = new CourseCatalog();
    private User student;
    private User instructor;
    private User admin;

    public void start() {

        while (true) {
            System.out.println("Welcome to the Class Registration System!");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Instructor");
            System.out.println("3. Login as Administrator");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> handleStudentActions((Student) student, courseCatalog);
                case 2 -> handleInstructorActions((Instructor) instructor);
                case 3 -> handleAdminActions((Administrator) admin, courseCatalog);
                case 4 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
