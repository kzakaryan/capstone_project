package capstone_project.impl;

import capstone_project.AdministratorRepository;
import capstone_project.user.Administrator;

public class AdministratorRepositoryImpl implements AdministratorRepository {

    Administrator administrator;

    public void login(String email, String password) {
        if (email.equals(administrator.getEmail()) &&
                password.equals(administrator.getPassword()) &&
                administrator.isActive()) {
            System.out.println(
                    "Admin " +
                            administrator.getFirstName() +
                            " " +
                            administrator.getLastName() +
                            "logged in successfully!");
        } else {
            System.out.println("Wrong Email or Password!");
        }

    }

    public void logout() {
        System.out.println("Logged out successfully!");
    }
}
