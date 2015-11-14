package jed.authlab.DataAccess;

/**
 * Created by Jeppe Dickow on 10/28/2015.
 */
public class Credential {
    private String name, password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return String.format("Credentials: name: %s  password: %s", this.name, this.password);
    }
}
