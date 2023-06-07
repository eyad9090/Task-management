package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this is  user model
 */

public class User {
    private int id;
    private String email;
    private String password;
    private String role;

    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        if(Validation.isValidId(id))
        {
            this.id = id;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }


    public boolean setEmail(String email) {
        if(Validation.isValidEmail(email))
        {
            this.email = email;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if(Validation.isValidPassword(password))
        {
            this.password = password;
            return true;
        }
        return false;
    }

    public String getRole() {
        return role;
    }

    public boolean setRole(String role) {
        if(Validation.isValidRole(role))
        {
            this.role = role;
            return true;
        }
        return false;
    }
}
