package capstone_project;

import capstone_project.course.Course;
import java.util.Map;

public interface InstructorService {

    boolean assignInstructorToCourse(String instructorId, String courseId);
}
