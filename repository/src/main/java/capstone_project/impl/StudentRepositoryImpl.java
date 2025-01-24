package capstone_project.impl;

import capstone_project.StudentRepository;
import capstone_project.course.Course;
import capstone_project.user.Student;

public class StudentRepositoryImpl implements StudentRepository {
    Student student;

    @Override
    public void login(String email, String password) {
        if (email.equals(student.getEmail()) &&
                password.equals(student.getPassword()) &&
                student.isActive()) {
            System.out.println(
                    "Student " +
                    student.getFirstName() +
                    " " +
                    student.getLastName() +
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
