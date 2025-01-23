package util;

import user.impl.Instructor;

import java.util.Scanner;

public class InstructorHandler {

    private static final Scanner scanner = new Scanner(System.in);

    public static void handleInstructorActions(Instructor instructor) {
        try {
            System.out.println("Enter email address: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            instructor.login(email, password);

            while (true) {
                System.out.println("\nInstructor Menu:");
                System.out.println("1. Logout");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        instructor.logout();
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
