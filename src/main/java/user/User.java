package user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@AllArgsConstructor
public abstract class User {

    protected String userId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;

    public User() {}


    public abstract void login(String email, String password);

    public void logout() {
        System.out.println("Logged out");
    }
}
