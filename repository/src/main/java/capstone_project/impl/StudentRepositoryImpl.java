package capstone_project.impl;

import capstone_project.StudentRepository;
import capstone_project.user.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {

    private final Map<String, Student> students = new HashMap<>();

    @Override
    public boolean save(Student student) {
        if (student != null) {
            students.put(student.getUserId(), student);
            return true;
        }
        return false;
    }

    @Override
    public Student findById(String studentId) {
        return students.get(studentId);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

}