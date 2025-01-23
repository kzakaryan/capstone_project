package course;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseCatalog {

    private List<Course> courseList;

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(Course course) {
        courseList.remove(course);
    }

    public Course getCourseById(String courseId) {
        return courseList.stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
    }

    public void listAvailableCourses() {
        for (Course course : courseList) {
            System.out.println(course);
        }
    }
}
