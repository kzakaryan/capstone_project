package capstone_project;

import capstone_project.course.Course;
import capstone_project.user.Student;

import java.util.List;

public interface CourseRepository {

    boolean save(Course course);

    Course findById(String courseId);

    List<Course> findAll();

}
