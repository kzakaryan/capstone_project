package capstone_project;

public interface AdministratorRepository {

    void login(String email, String password);

    void logout();

}