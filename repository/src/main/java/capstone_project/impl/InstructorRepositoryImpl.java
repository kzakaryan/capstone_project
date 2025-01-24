package capstone_project.impl;

import capstone_project.InstructorRepository;
import capstone_project.user.Instructor;

public class InstructorRepositoryImpl implements InstructorRepository {

    Instructor instructor;

    @Override
    public void login(String email, String password) {
        if (email.equals(instructor.getEmail()) &&
                password.equals(instructor.getPassword()) &&
                instructor.isActive()) {
            System.out.println(
                    "Student " +
                            instructor.getFirstName() +
                            " " +
                            instructor.getLastName() +
                            "logged in successfully!");
        } else {
            System.out.println("Wrong Email or Password!");
        }

    }

    @Override
    public void logout() {
        System.out.println("Logged out successfully!");
    }
}
