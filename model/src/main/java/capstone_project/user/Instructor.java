package capstone_project.user;

import capstone_project.course.Course;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class Instructor extends User {

    private Map<String, Course> assignedCourses;

    public Instructor(String instructorId, String instructorFirstName, String instructorLastName, String instructorEmail, String s, String s1, boolean b) {
        super(instructorId, instructorFirstName, instructorLastName, s, s1, b);
        assignedCourses = null;
    }
}
