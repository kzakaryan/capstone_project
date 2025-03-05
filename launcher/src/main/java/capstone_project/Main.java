package capstone_project;

import capstone_project.*;
import capstone_project.impl.*;
import capstone_project.user.Administrator;

public class Main {

    public static void main(String[] args) {
        // Initialize repositories
        CourseRepositoryImpl courseRepository = new CourseRepositoryImpl();
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        InstructorRepositoryImpl instructorRepository = new InstructorRepositoryImpl();
        AdministratorRepositoryImpl adminRepository = new AdministratorRepositoryImpl();

        // Initialize services with repository injection
        CourseServiceImpl courseService = new CourseServiceImpl(courseRepository, instructorRepository, studentRepository);
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository);
        InstructorServiceImpl instructorService = new InstructorServiceImpl(instructorRepository, courseRepository);
        AdministratorServiceImpl adminService = new AdministratorServiceImpl(adminRepository, courseRepository, studentRepository, instructorRepository);

        // Initialize and start the admin console controller
        AdministratorController adminConsoleController = new AdministratorController(adminService, courseService, studentService, instructorService);
        adminConsoleController.start();
    }
}