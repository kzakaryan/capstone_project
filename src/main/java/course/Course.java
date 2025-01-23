package course;

import lombok.*;
import user.impl.Instructor;
import user.impl.Student;
import java.util.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Course {

    private String courseId;
    private String courseName;
    private String courseDescription;
    private String courseCredits;
    private List<Course> coursePrerequisites;
    private List<Student> enrolledStudents;
    private Instructor instructor;

    public boolean isAvailable() {
        return enrolledStudents != null && !enrolledStudents.isEmpty();
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void withdrawStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void assignPrerequisite(List<Course> courses) {
        coursePrerequisites.addAll(courses);
    }
}