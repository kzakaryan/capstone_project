package user.impl;

import course.Course;
import exceptions.IncorrectPasswordException;
import lombok.*;
import user.Notifiable;
import user.User;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Instructor extends User implements Notifiable {

    private List<Course> assignedCourses;
    private boolean isActive;

    public Instructor(String userId, String firstName, String lastName, String email, String password) {
        super(userId, firstName, lastName, email, password);
    }

    @Override
    public void login(String email, String password) throws IncorrectPasswordException {
        if ((!this.password.equals(password))&&(!this.email.equals(email))) {
            throw new IncorrectPasswordException("Incorrect password for Instructor " + firstName + " " + lastName);
        }
        System.out.println(firstName + " " + lastName + " has logged in.");
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Notification to Instructor " + firstName + " " + lastName + ": " + message);
    }
}
