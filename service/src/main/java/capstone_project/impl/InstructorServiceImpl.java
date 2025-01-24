package capstone_project.impl;

import capstone_project.CourseRepository;
import capstone_project.InstructorRepository;
import capstone_project.InstructorService;
import capstone_project.course.Course;
import capstone_project.user.Instructor;


public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean assignInstructorToCourse(String instructorId, String courseId) {
        Instructor instructor = instructorRepository.findById(instructorId);
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
}