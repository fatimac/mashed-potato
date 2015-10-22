package mashedpotato.com.lalang.models;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by fatima.g.m.carillo on 10/20/2015.
 */
public class User {

    public static final String COLUMN_FIRSTNAME = "first_name";
    public static final String COLUMN_LASTNAME = "last_name";

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addUserToParse(SignUpCallback callback) {
        ParseUser user = new ParseUser();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.put(COLUMN_FIRSTNAME, this.firstname);
        user.put(COLUMN_LASTNAME, this.lastname);
        user.signUpInBackground(callback);
    }

}
