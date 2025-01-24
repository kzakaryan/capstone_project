package capstone_project.course;

import capstone_project.user.Instructor;
import capstone_project.user.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String courseId;
    private String courseName;
    private String courseDescription;
    private String courseCredits;
    private List<Course> coursePrerequisites;
    private List<Student> enrolledStudents;
    private Instructor instructor;
    private int capacity;

}
