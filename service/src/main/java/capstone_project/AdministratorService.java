package capstone_project;

import capstone_project.course.Course;
import capstone_project.user.Instructor;
import capstone_project.user.Student;
import java.util.List;

public interface AdministratorService {

    boolean addInstructor(Instructor instructor);
    boolean removeInstructor(String instructorId);
    boolean addStudent(Student student);
    boolean removeStudent(String studentId);
    boolean addCourse(Course course);
    boolean removeCourse(String courseId);
    List<Course> viewAllCourses();
    List<Student> viewAllStudents();
    List<Instructor> viewAllInstructors();

}
