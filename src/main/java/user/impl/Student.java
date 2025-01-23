package user.impl;

import exceptions.CurrentLevelMismatchException;
import exceptions.IncorrectPasswordException;
import exceptions.NoSpaceAvailableException;
import lombok.*;
import user.Notifiable;
import user.Registrable;
import user.User;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Student extends User implements Notifiable, Registrable {

    private List<String> enrolledCourses = new ArrayList<>();

    public Student() {
        super();
    }


    @Override
    public void login(String email, String password) throws IncorrectPasswordException {
        if ((!this.password.equals(password))&&(!this.email.equals(email))) {
            throw new IncorrectPasswordException("Incorrect password for student " + firstName + " " + lastName);
        }
        System.out.println(firstName + " " + lastName + " has logged in.");
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Notification to Student " + firstName + " " + lastName + ": " + message);
    }

    @Override
    public void register(String courseId) throws NoSpaceAvailableException, CurrentLevelMismatchException {
        System.out.println(firstName + " " + lastName + " registered for course " + courseId);
        enrolledCourses.add(courseId);
    }

    @Override
    public void withdraw(String courseId) {
        System.out.println(firstName + " " + lastName + " withdrew from course " + courseId);
        enrolledCourses.remove(courseId);
    }
}
