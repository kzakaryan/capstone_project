package capstone_project.impl;

import capstone_project.InstructorRepository;
import capstone_project.user.Instructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorRepositoryImpl implements InstructorRepository {

    private final Map<String, Instructor> instructors = new HashMap<>();

    @Override
    public boolean save(Instructor instructor) {
        if (instructor != null) {
            instructors.put(instructor.getUserId(), instructor);
            return true;
        }
        return false;
    }

    @Override
    public Instructor findById(String instructorId) {
        return instructors.get(instructorId);
    }

    @Override
    public List<Instructor> findAll() {
        return new ArrayList<>(instructors.values());
    }

}
