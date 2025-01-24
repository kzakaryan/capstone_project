package capstone_project;

public interface InstructorRepository {

    void login(String email, String password);

    void logout();

}