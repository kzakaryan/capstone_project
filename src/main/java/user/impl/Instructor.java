package user.impl;

import course.Course;
import lombok.*;
import user.User;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Instructor extends User {

    private List<Course> assignedCourses;
    private boolean isActive;

}
