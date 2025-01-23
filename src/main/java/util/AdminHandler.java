package util;

import course.CourseCatalog;
import user.impl.Administrator;

import java.util.Scanner;

public class AdminHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void handleAdminActions(Administrator admin, CourseCatalog courseCatalog) {
        try {
            System.out.println("Enter email address: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            admin.login(email, password);

            while (true) {
                System.out.println("\nAdministrator Menu:");
                System.out.println("1. Add Course");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Course ID: ");
                        String courseId = scanner.nextLine();
                        System.out.print("Enter Course Name: ");
                        String courseName = scanner.nextLine();
                        courseCatalog.addCourse(courseId, courseName);
                        System.out.println("Course added successfully.");
                    }
                    case 2 -> {
                        admin.logout();
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
