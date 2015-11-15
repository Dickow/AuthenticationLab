package jed.authlab.DataAccess;

import jed.authlab.security.Role;

/**
 * Created by Jeppe Dickow on 10/28/2015.
 */
public class Credential {
    private String name, password;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toString(){
        return String.format("Credentials: name: %s  password: %s role: %s", this.name, this.password, this.role.name());
    }

}
