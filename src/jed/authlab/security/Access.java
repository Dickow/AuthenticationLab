package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/5/2015.
 */
public class Access {

    private String userName;
    private String[] accessLevels;

    public Access(String userName, String[] accessLevels){
        this.userName = userName;
        this.accessLevels = accessLevels;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getAccessLevels() {
        return accessLevels;
    }

    public void setAccessLevels(String[] accessLevels) {
        this.accessLevels = accessLevels;
    }
}


