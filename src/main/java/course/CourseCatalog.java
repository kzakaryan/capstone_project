package course;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Setter
@NoArgsConstructor
public class CourseCatalog {

    private Map<String, String> courses = new HashMap<>();

    public void addCourse(String courseId, String courseName) {
        courses.put(courseId, courseName);
    }

    public Map<String, String> getCourses() {
        return courses;
    }

    public String findCourseById(String courseId) {
        return courses.get(courseId);
    }
}