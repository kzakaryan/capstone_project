package course;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CourseCatalog {

    private Map<String, String> courses = new HashMap<>();

    public void addCourse(String courseId, String courseName) {
        courses.put(courseId, courseName);
    }

    public String findCourseById(String courseId) {
        return courses.get(courseId);
    }

}
