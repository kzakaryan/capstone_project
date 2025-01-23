package user.impl;

import course.Course;
import lombok.*;
import user.Registrable;
import user.User;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Student extends User implements Registrable {

    private List<Course> enrolledCourses;
    private boolean isActive;

    public void viewCourses () {
        for (Course course : enrolledCourses) {
            System.out.println(course);
        }
    }

    @Override
    public void register(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    @Override
    public void withdraw(Course course) {
        enrolledCourses.remove(course);
    }

//    @Override
//    public String toString() {
//
//    }
}
