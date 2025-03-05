package capstone_project;

import capstone_project.user.Instructor;
import java.util.List;

public interface InstructorRepository {

    boolean save(Instructor instructor);

    Instructor findById(String instructorId);

    List<Instructor> findAll();

}