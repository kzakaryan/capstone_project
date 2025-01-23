package util;

import course.CourseCatalog;
import user.impl.Student;

import java.util.Scanner;

public class StudentHandler {

    public static final Scanner scanner = new Scanner(System.in);

    public static void handleStudentActions(Student student, CourseCatalog courseCatalog) {
        try {
            System.out.println("Enter email address: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            student.login(email, password);

            while (true) {
                System.out.println("\nStudent Menu:");
                System.out.println("1. View Available Courses");
                System.out.println("2. Register for a Course");
                System.out.println("3. Withdraw from a Course");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.println("Available Courses:");
                        courseCatalog.getCourses().forEach((id, name) -> System.out.println(id + ": " + name));
                    }
                    case 2 -> {
                        System.out.print("Enter Course ID to register: ");
                        String courseId = scanner.nextLine();
                        student.register(courseId);
                    }
                    case 3 -> {
                        System.out.print("Enter Course ID to withdraw: ");
                        String courseId = scanner.nextLine();
                        student.withdraw(courseId);
                    }
                    case 4 -> {
                        student.logout();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
