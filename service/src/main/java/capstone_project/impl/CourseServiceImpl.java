package capstone_project.impl;

import capstone_project.CourseRepository;
import capstone_project.CourseService;
import capstone_project.InstructorRepository;
import capstone_project.StudentRepository;
import capstone_project.course.Course;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean addCourse(Course course) {
        // Logic for adding a course (validations, etc.)
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public boolean enrollStudentInCourse(String studentId, String courseId) {
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
    public boolean assignInstructorToCourse(String courseId, String instructorId) {
        var instructor = instructorRepository.findById(instructorId);
        Course course = courseRepository.findById(courseId);
        if (instructor != null && course != null) {
            instructor.getAssignedCourses().put(courseId, course);
            course.setInstructor(instructor);
            instructorRepository.save(instructor);
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCourseAvailability(String courseId) {
        Course course = courseRepository.findById(courseId);
        return course != null && course.getCapacity() > 0;
    }
}
