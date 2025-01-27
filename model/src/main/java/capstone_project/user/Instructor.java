package capstone_project.user;

import capstone_project.course.Course;
import lombok.*;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User {

    private Map<String, Course> assignedCourses;

}
