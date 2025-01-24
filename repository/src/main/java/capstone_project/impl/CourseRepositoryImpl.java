package capstone_project.impl;

import capstone_project.CourseRepository;
import capstone_project.course.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRepositoryImpl implements CourseRepository {

    private final Map<String, Course> courses = new HashMap<>();

    @Override
    public boolean save(Course course) {
        if (course != null) {
            courses.put(course.getCourseId(), course);
            return true;
        }
        return false;
    }

    @Override
    public Course findById(String courseId) {
        return courses.get(courseId);
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }


}