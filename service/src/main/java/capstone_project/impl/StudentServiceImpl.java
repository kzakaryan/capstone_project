package capstone_project.impl;

import capstone_project.CourseRepository;
import capstone_project.StudentRepository;
import capstone_project.StudentService;

import capstone_project.course.Course;
import capstone_project.user.Student;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean enrollInCourse(String studentId, String courseId) {
        var student = studentRepository.findById(studentId);
        Course course = courseRepository.findById(courseId);
        if (student != null && course != null && course.getCapacity() > 0) {
            student.getEnrolledCourses().put(courseId, course);
            course.getEnrolledStudents().add(student);
            course.setCapacity(course.getCapacity() - 1);
            studentRepository.save(student);
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    @Override
    public boolean dropCourse(String studentId, String courseId) {
        var student = studentRepository.findById(studentId);
        Course course = courseRepository.findById(courseId);
        if (student != null && course != null) {
            student.getEnrolledCourses().remove(courseId);
            course.getEnrolledStudents().remove(student);
            course.setCapacity(course.getCapacity() + 1);
            studentRepository.save(student);
            courseRepository.save(course);
            return true;
        }
        return false;
    }
}
