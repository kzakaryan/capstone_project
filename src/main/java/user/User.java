package user;

public abstract class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public void login(String email, String password) {

        if (!(this.email.equals(email) && this.password.equals(password))) {
            System.out.println("Incorrect email or password");
        }
        else {
            System.out.println("Login successful");
        }


    }

    public void logout() {

        System.out.println("Logout successful");

    }


}
