package user.impl;

import exceptions.IncorrectPasswordException;
import lombok.*;
import user.User;

@Setter
@Getter
public class Administrator extends User {

    @Override
    public void login(String email, String password) throws IncorrectPasswordException {
        if ((!this.password.equals(password))&&(!this.email.equals(email))) {
            throw new IncorrectPasswordException("Incorrect password for Admin " + firstName + " " + lastName);
        }
        System.out.println(firstName + " " + lastName + " has logged in.");
    }
}
