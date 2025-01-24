package capstone_project.user;

import capstone_project.course.Course;
import lombok.*;
import java.util.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Student extends User {

    private int credits;
    private Map<String, Course> enrolledCourses;

    public Student(String studentId, String studentFirstName, String studentLastName, String studentEmail, String password, boolean b, int i) {
        super(studentId, studentFirstName, studentLastName, studentEmail, password, b);
        this.credits = i;
        this.enrolledCourses = null;
    }
}