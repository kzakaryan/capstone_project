package capstone_project;

import capstone_project.course.Course;
import java.util.List;

public interface CourseService {

    boolean addCourse(Course course);
    Course getCourseById(String courseId);
    List<Course> getAllCourses();
    boolean enrollStudentInCourse(String studentId, String courseId);
    boolean assignInstructorToCourse(String courseId, String instructorId);
    boolean checkCourseAvailability(String courseId);

}
