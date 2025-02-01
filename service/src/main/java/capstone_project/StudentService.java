package capstone_project;

import capstone_project.user.Student;

public interface StudentService {

    boolean enrollInCourse(String studentId, String courseId);
    boolean dropCourse(String studentId, String courseId);

}
