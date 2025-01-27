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

}