package capstone_project;

import capstone_project.user.Student;

import java.util.List;

public interface StudentRepository {

    boolean save(Student student);

    Student findById(String studentId);

    List<Student> findAll();

}
