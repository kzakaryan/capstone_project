package capstone_project.impl;

import capstone_project.*;
import capstone_project.course.Course;
import capstone_project.user.Instructor;
import capstone_project.user.Student;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepositoryImpl adminRepository;
    private final CourseRepositoryImpl courseRepository;
    private final StudentRepositoryImpl studentRepository;
    private final InstructorRepositoryImpl instructorRepository;


    @Override
    public boolean addInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public boolean removeInstructor(String instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId);
        if (instructor != null) {
            instructorRepository.save(null); // Remove the instructor
            return true;
        }
        return false;
    }

    @Override
    public boolean addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean removeStudent(String studentId) {
        Student student = studentRepository.findById(studentId);
        if (student != null) {
            studentRepository.save(null); // Remove student
            return true;
        }
        return false;
    }

    @Override
    public boolean addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean removeCourse(String courseId) {
        Course course = courseRepository.findById(courseId);
        if (course != null) {
            courseRepository.save(null); // Remove course
            return true;
        }
        return false;
    }

    @Override
    public List<Course> viewAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Student> viewAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Instructor> viewAllInstructors() {
        return instructorRepository.findAll();
    }
}
