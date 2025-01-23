package util;
import exceptions.IncorrectPasswordException;
import user.User;

public class AuthenticationService {

    public void validateUserCredentials(User user, String email, String password) throws IncorrectPasswordException {
        user.login(email, password);
    }
}
